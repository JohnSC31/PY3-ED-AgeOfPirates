
package ageofpirates.model;

import ageofpirates.controller.MainController;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;


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
                        break;
                    case 1: // lobby
                        lobby(inputStream.readInt());
                        break;
                    case 2: //Config sea
                        configSea(inputStream.readInt());
                        break;
                    
                } 
            }
            
        }catch(IOException ex){
            System.out.println("Error en la comunicaci�n "+"Informaci�n para el usuario");
        }/*catch(ClassNotFoundException ex){
            System.out.println("No se encontro la clase");
            // catch para errores en el object input/output stream
        }*/
        
        // se desconecta del servidor
        System.out.println("se desconecto el servidor");
    }
    
    
    // ----------------------------------------------------- METODOS PARA EL CLIENTE ------------------------------------------------------
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
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
