
package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;


public class Server {
    
    public final static int SERVER_PORT = 8081;
    
    private ArrayList<ServerThread> players;
    private Socket socketPlayer;
    private boolean start;
    
    public Server(){
        this.players = new ArrayList<>();
        this.start = false;
    }
    
    private void runServer(){
        try {
            ServerSocket server = new ServerSocket(SERVER_PORT);
            boolean playerHost;
            
            while(!start){
                System.out.println("Esperando jugador...");
                this.socketPlayer = server.accept();
                if(!start && players.size() + 1 <= 4){
                    playerHost = players.isEmpty(); // el primero en llegar es el host
                    new ServerThread(socketPlayer, this,players, playerHost, players.size() + 1); // se agrega a lista de jugadores
                }else{
                    startGame();
                    break;
                }
                
            }
            
            System.out.println("A jugar");
            
            
            while(true){
                // mantiene vivo el server
            }
           
       } catch (IOException ex) {
           
           System.out.println("Error en el servidor");
       }
        
    }
    
    private void startGame(){
        this.start = true;
    }
    
    
    
    
    
    
    public static void main(String[] args) {
        Server server = new Server();
        server.runServer(); // inicia el servidor
    }
}



