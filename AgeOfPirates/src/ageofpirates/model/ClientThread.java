
package ageofpirates.model;

import ageofpirates.controller.MainController;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;


public class ClientThread extends Thread{
    
    private ObjectInputStream objInputStream;
    private DataInputStream inputStream;
    private Player player;
    private MainController mainController;

    public ClientThread(Player player, MainController mainController) {
        this.player = player;
        this.mainController = mainController;
        
        // entradas de sockets
        this.inputStream = this.player.getInputStream();
        this.objInputStream = this.player.getObjInputStream();
    }
    
    
    @Override
    public void run(){
        try{
            
            // configuracion inicial desde el server
            player.setPlayerId(inputStream.readInt());
            player.setHost(inputStream.readBoolean());
            
            // vars
            int option = 0;
            
            while(true){
                System.out.println("Cliente espera opcion");
                option = inputStream.readInt();
                 
                switch(option){
                    case 0: 
                        helperServer(inputStream.readInt());
                        break;
                    case 1: // lobby
                        lobby(inputStream.readInt());
                        break;
                    case 2: //Config sea
                        configSea(inputStream.readInt());
                        break;
                    case 3: // juego
                        game(inputStream.readInt());
                        break;
                    case 4: // mercado
                        market(inputStream.readInt());
                    
                } 
            }
            
        }catch(IOException ex){
            System.out.println("Error en la comunicaci�n "+"Informaci�n para el usuario");
        }catch(ClassNotFoundException ex){
            System.out.println("No se encontro la clase");
            // catch para errores en el object input/output stream
        }
        
        // se desconecta del servidor
        System.out.println("se desconecto el servidor");
    }
    
    
    // ----------------------------------------------------- METODOS PARA EL CLIENTE ------------------------------------------------------
    public void helperServer(int option) throws IOException{
        
         switch(option){
            case 0:
                inputStream.readInt(); // ejemplificacion (no ha nada)
                break;
            case 1: // setear el turno de todos los jugadores
                boolean playerTurn = inputStream.readBoolean();
                
                mainController.getGameController().setPlayerTurn(playerTurn);
                break;
            default:
                System.out.println("Option " + option +" en serverHelper inexistente");
                break;
        }
    }
    
    
    public void lobby(int option) throws IOException{
        
        switch(option){
            case 0:
                inputStream.readInt(); // ejemplificacion (no hace nada)
                break;
            case 1:
                int playerId = inputStream.readInt();
                boolean host = inputStream.readBoolean();
                
                mainController.getLobbyController().addPlayerLobby(playerId, host);
                
                break;
            case 2: // pasar a la pantalla de configuracion
                mainController.startConfigSea(mainController.getLobbyView());
                break;
            default:
                System.out.println("Option " + option +" en lobby inexistente");
                break;
        }
    }
    
    public void configSea(int option) throws IOException{
        
        switch(option){
            case 0:
                inputStream.readInt(); // ejemplificacion (no hace nada)
                break;
            case 1: // pasar a la pantalla de juego
                
                mainController.startGame();
                
                break;
            default:
                System.out.println("Option " + option +" en configSea inexistente");
                break;
        }
    }
    
    public void game(int option) throws IOException, ClassNotFoundException{
        
        switch(option){
            case 0:
                inputStream.readInt(); // ejemplificacion (no hace nada)
                break;
            case 1: // recibir un mensaje
                String message = inputStream.readUTF();
                int playerId = inputStream.readInt();
                
                this.mainController.getGameController().recieveMessage(message, playerId);
                
                break;
            case 2: // recibir la lista de jugadores para el juego
                ArrayList enemies = (ArrayList) objInputStream.readObject();
                this.mainController.getGameController().loadEnemiesButtons(enemies);
                break;
            case 3: // enviar mis datos al enemigo
                int sendTo = inputStream.readInt();
                
                this.mainController.getGameController().sendToEnemyMySea(sendTo);
                
                
                break;
            case 4: // recibir los datos de un jugador y cargarlo
                
                SeaCell[][] enemySea = (SeaCell[][]) objInputStream.readObject();
                Graph enemyGraph = (Graph) objInputStream.readObject();
                
                this.mainController.getGameController().setEnemySea(enemySea, enemyGraph);
                
                break;
            default:
                System.out.println("Option " + option +" en game inexistente");
                break;
        }
    }
    
    public void market(int option) throws IOException, ClassNotFoundException{
        switch(option){
            case 0:
                inputStream.readInt(); // ejemplificacion (no hace nada)
                break;
            case 1: // actualiza el mercado
                Inventory marketInventory = (Inventory) objInputStream.readObject();
                mainController.getGame().setMarketInventory(marketInventory);
                
                if(this.mainController.getMarketController() != null){
                    this.mainController.getMarketController().setMarketInventory();
                }
                
                break;
            default:
                System.out.println("Option " + option +" en market inexistente");
                break;
        }
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
