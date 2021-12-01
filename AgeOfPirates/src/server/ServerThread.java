
package server;

import ageofpirates.model.Game.ItemType;
import ageofpirates.model.Inventory;
import ageofpirates.model.SeaCell;
import ageofpirates.model.Target;
import static ageofpirates.view.ConfigWindow.SEA_SIZE;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ServerThread extends Thread{
    
    private Socket socketPlayer = null;//referencia a socket de comunicacion de cliente
    private DataInputStream inputStream = null; //Para leer comunicacion
    private DataOutputStream outputStream = null; //Para enviar comunicacion
    
    private ObjectOutputStream objOutputStream = null;
    private ObjectInputStream objInputStream = null;
    private Server server;// referencia al servidor

    private ArrayList<ServerThread> players; // saber cuales son los jugadores
    
    private int playerId;
    private boolean host;
    
    public ServerThread(Socket socketPlayer, Server server, ArrayList<ServerThread> players, boolean host, int playerId){
        this.socketPlayer = socketPlayer;
        this.server = server;
        this.playerId = playerId;
        this.players = players;
        this.host = host;
        this.players.add(this); // se agrega a si mismo al array de jugadores
        this.start();
    }
    
    
    @Override
    public void run(){
        
        try {
            outputStream = new DataOutputStream(socketPlayer.getOutputStream());
            inputStream = new DataInputStream(socketPlayer.getInputStream());

            objOutputStream = new ObjectOutputStream(socketPlayer.getOutputStream());
            objInputStream = new ObjectInputStream(socketPlayer.getInputStream());
            
            //configuracion inicial del jugador
            outputStream.writeInt(playerId);
            outputStream.writeBoolean(host);
            
            int option = 0;
            
            while(true){
//                System.out.println("Server espera opcion");
                option = inputStream.readInt();
                switch(option){
                    case 0: // opciones y ayuda del server
                        serverHelper(inputStream.readInt());
                        break;
                    case 1: // lobby
                        lobby(inputStream.readInt());
                        break;
                        
                    case 2: // configuracion del oceano
                        configSea(inputStream.readInt());
                        break;
                    case 3: // opcion del juego
                        game(inputStream.readInt());
                        break;
                    case 4: // opcion para el mercado
                        market(inputStream.readInt());
                
                }
            }
        
        } catch (IOException ex) {
            System.out.println("Se termino la conexion");
        }catch(ClassNotFoundException ex){
            System.out.println("No se encontro la clase");
        }
        // Excepcion se necesitara cuando se pasen objetos por el servidor
        
        System.out.println("Se removio al cliente");
        
        try{
          socketPlayer.close();
    	}catch(Exception et){
            System.out.println("No se puedo cerrar conexion");
        }  
    }
    
    
    // --------------------------------------------- METODOS PARA LOS SOCKETS ----------------------------------------------------------------
    public void serverHelper(int option) throws IOException{
        switch(option){
            case 0:
                inputStream.readInt(); // ejemplificacion (no ha nada)
                break;
            case 1: // setear el turno de todos los jugadores
                int playerInTurnId = server.passPlayerTurn();
                for(int i = 0; i < players.size(); i++){
                    players.get(i).outputStream.writeInt(0);
                    players.get(i).outputStream.writeInt(1);
                    players.get(i).outputStream.writeInt(playerInTurnId);
                }
                System.out.println("update turn");
                break;
            case 2: // envia un arreglo con todos los enemigos del jugador que los pidio
                ArrayList enemies = new ArrayList<>();
                for(int i = 0; i < players.size(); i++){
                    if(players.get(i).getPlayerId() != playerId){
                        enemies.add(players.get(i).getPlayerId());
                    }
                }
                outputStream.writeInt(3);
                outputStream.writeInt(2);
                objOutputStream.writeObject(enemies);
                
                break;
            default:
                System.out.println("Option " + option +" en serverHelper inexistente");
                break;
        }
    }
    
    public void lobby(int option) throws IOException{
        
        switch(option){
            case 0:
                inputStream.readInt(); // ejemplificacion (no ha nada)
                break;
            case 1: // se une un nuego jugador al lobby
                for(int i = 0; i < players.size(); i++){
                    players.get(i).outputStream.writeInt(1);
                    players.get(i).outputStream.writeInt(1);
                    players.get(i).outputStream.writeInt(this.playerId);
                    players.get(i).outputStream.writeBoolean(this.host);
                }
                break;
            case 2: // inicia el juego para todos los jugadores conectados
                for(int i = 0; i < players.size(); i++){
                    players.get(i).outputStream.writeInt(1);
                    players.get(i).outputStream.writeInt(2);
                }
                break;
            default:
                System.out.println("Option " + option +" en lobby inexistente");
                break;
        }
    }
    
    public void configSea(int option) throws IOException{
    
        switch(option){
            case 0:
                inputStream.readInt(); // ejemplificacion (no ha nada)
                break;
            case 1: // pasar a todos los jugadores a la pantalla de juego
                for(int i = 0; i < players.size(); i++){
                    players.get(i).outputStream.writeInt(2);
                    players.get(i).outputStream.writeInt(1);
                }
                break;
            default:
                System.out.println("Option " + option +" en configSea inexistente");
                break;
        }
    
    }
    
    
    public void game(int option) throws IOException, ClassNotFoundException{
        
        int enemyId = 0;
        ArrayList<Target> targets = new ArrayList<>();
        
        switch(option){
            case 0:
                inputStream.readInt(); // ejemplificacion (no ha nada)
                break;
            case 1: // enviar un mensaje
                String message = inputStream.readUTF();
                for(int i = 0; i < players.size(); i++){
                    players.get(i).outputStream.writeInt(3); // opcion del juego
                    players.get(i).outputStream.writeInt(1); // recibir mensaje
                    players.get(i).outputStream.writeUTF(message);
                    players.get(i).outputStream.writeInt(playerId); 
                }
                break;
            case 2: // pedir los datos de un enemigo
                enemyId = inputStream.readInt();
                
                for(int i = 0; i < players.size(); i++){
                    if(players.get(i).getPlayerId() == enemyId){
                        players.get(i).outputStream.writeInt(3); // opcion del juego
                        players.get(i).outputStream.writeInt(3); // enviar datos
                        players.get(i).outputStream.writeInt(playerId);
                    }
                }
                break;
            case 3: // enviar mis datos a otro jugador
                enemyId = inputStream.readInt();
                SeaCell[][] sea = new SeaCell[SEA_SIZE][SEA_SIZE];
                
                for(int i = 0; i < players.size(); i++){
                    if(players.get(i).getPlayerId() == enemyId){
                        players.get(i).outputStream.writeInt(3); // opcion del juego
                        players.get(i).outputStream.writeInt(4); // enviar datos
                        
                        players.get(i).outputStream.writeInt(inputStream.readInt()); // player Id
                        players.get(i).outputStream.writeBoolean(inputStream.readBoolean()); // si tiene escudo o no
                        
                        for(int iSea = 0; iSea < SEA_SIZE; iSea++){
                            for(int jSea = 0; jSea < SEA_SIZE; jSea++){
                                players.get(i).objOutputStream.writeObject(objInputStream.readObject()); // pasa el grafo
                            }
                        }

                        players.get(i).objOutputStream.writeObject(objInputStream.readObject()); // pasa el grafo
                    }
                }
                
                break;
            case 4: // atacar a un enemigo
                enemyId = inputStream.readInt(); // al jugador que se va atacar
                ItemType weapon = (ItemType) objInputStream.readObject();
                targets = (ArrayList) objInputStream.readUnshared();
                
                for(int i = 0; i < targets.size(); i++){
                    System.out.println("Target recieved server  - " + targets.get(i).getI() +" : "+ targets.get(i).getJ());
                }
                
                for(int i = 0; i < players.size(); i++){
                    if(players.get(i).getPlayerId() == enemyId){
                        players.get(i).outputStream.writeInt(3); // juego
                        players.get(i).outputStream.writeInt(5);// recibir el ataque de un eemigo
                        players.get(i).outputStream.writeInt(playerId); // el id del que lo ataca
                        players.get(i).objOutputStream.writeObject(weapon);// el tipo de arma
                        players.get(i).objOutputStream.writeUnshared(targets);// el arrayList de targets
                    }
                }
                
                break;
                
            case 5: // enviar la bitacora al enemigo
                enemyId = inputStream.readInt();
                for(int i = 0; i < players.size(); i++){
                    if(players.get(i).getPlayerId() == enemyId){
                        players.get(i).outputStream.writeInt(3); // juego
                        players.get(i).outputStream.writeInt(6);// recibir la bitacora
                        players.get(i).outputStream.writeUTF(inputStream.readUTF());
                    }
                }
                break;
            case 6: // envio del kraken
                enemyId = inputStream.readInt();
                for(int i = 0; i < players.size(); i++){
                    if(players.get(i).getPlayerId() == enemyId){
                        players.get(i).outputStream.writeInt(3); // juego
                        players.get(i).outputStream.writeInt(7);// recibir al kraken
                        players.get(i).outputStream.writeInt(inputStream.readInt());
                        players.get(i).outputStream.writeInt(playerId); // id de atacante
                    }
                }
                break;
            default:
                System.out.println("Option " + option +" en serverHelper inexistente");
                break;
        }
    }
    
    public void market(int option) throws IOException, ClassNotFoundException{
        
        switch(option){
            case 0:
                inputStream.readInt(); // ejemplificacion (no ha nada)
                break;
            case 1: // actualizar el mercado
                
                Inventory marketInventory = (Inventory) this.objInputStream.readObject();
                for(int i = 0; i < players.size(); i++){
                    players.get(i).outputStream.writeInt(4); // opcion del market
                    players.get(i).outputStream.writeInt(1); // recibir inventario
                    players.get(i).objOutputStream.writeObject(marketInventory);

                }
                break;
            default:
                System.out.println("Option " + option +" en serverHelper inexistente");
                break;
        }
        
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    // ------------------------------------------------- GETTERS AND SETTERS ---------------------------------------------------------------------

    public DataInputStream getInputStream() {
        return inputStream;
    }

    public DataOutputStream getOutputStream() {
        return outputStream;
    }

    public ObjectOutputStream getObjOutputStream() {
        return objOutputStream;
    }

    public ObjectInputStream getObjInputStream() {
        return objInputStream;
    }

    public int getPlayerId() {
        return playerId;
    }

    public boolean isHost() {
        return host;
    }
    
    
    
    
}
