
package server;

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
                System.out.println("Server espera opcion");
                option = inputStream.readInt();
                switch(option){
                    case 0: // opciones y ayuda del server
                        serverHelper(inputStream.readInt());
                        break;
                    case 1: // lobby
                        lobby(inputStream.readInt());
                        break;
                
                }
            }
        
        } catch (IOException ex) {
            System.out.println("Se termino la conexion");
        }/*catch(ClassNotFoundException ex){
            System.out.println("No se encontro la clase");
        }*/
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
            default:
                System.out.println("Option " + option +" en lobby inexistente");
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
