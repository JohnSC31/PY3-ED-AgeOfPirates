
package ageofpirates.model;

import ageofpirates.controller.MainController;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Player {
    public static final String IP_SERVER = "localhost";
    public static final int SERVER_PORT = 8081;
    
    private DataInputStream inputStream = null;//leer comunicacion
    private DataOutputStream outputStream = null;//escribir comunicacion

    private ObjectInputStream objInputStream = null;
    private ObjectOutputStream objOutputStream = null;
    
    private Socket player = null;//para la comunicacion
    
    private int playerId;
    private boolean host;
    
    private int budget;
    
    
    public Player(MainController mainController){
        
        // conexion con el servidor
      try {
            player = new Socket(IP_SERVER, SERVER_PORT);
            
            outputStream = new DataOutputStream(player.getOutputStream());
            inputStream = new DataInputStream(player.getInputStream());
            
            objOutputStream = new ObjectOutputStream(player.getOutputStream());
            objInputStream = new ObjectInputStream(player.getInputStream());
            

        } catch (IOException ex) {
            Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error");
        }
        
        // inicializacion de variables
        this.budget = 4000; // se inicia con 4000 (dolares)
        
        
        new ClientThread(this, mainController).start();
    }
    
    
    
    
    
    
    // -------------------------------------------------------- GETTERS AND SETTERS---------------------------------------------------------------

    public DataInputStream getInputStream() {
        return inputStream;
    }

    public DataOutputStream getOutputStream() {
        return outputStream;
    }

    public ObjectInputStream getObjInputStream() {
        return objInputStream;
    }

    public ObjectOutputStream getObjOutputStream() {
        return objOutputStream;
    }

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public boolean isHost() {
        return host;
    }

    public void setHost(boolean host) {
        this.host = host;
    }
    
    
    
    
}
