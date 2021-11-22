
package ageofpirates.controller;

import ageofpirates.model.Game;
import ageofpirates.view.LobbyWindow;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class LobbyController implements ActionListener{
    
    private LobbyWindow view;
    private Game game;
    private MainController mainController;
    private DataOutputStream outputStream;

    public LobbyController(LobbyWindow view, Game game, MainController mainController) {
        this.view = view;
        this.game = game;
        this.mainController = mainController;
        this.outputStream = game.getPlayer().getOutputStream();
        
        _init_();
    }
    
    // ----------------------------------------------- METODOS ----------------------------------------------------------------
    
   
    private void _init_(){
        
        view.getBtnStartGame().addActionListener(this);
        view.getBtnStartGame().setEnabled(this.game.getPlayer().isHost());  // solo disponible para el host
        
        joinLobby();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(e.getSource().equals(view.getBtnStartGame())){
            // se inicia el juego
            startGame();
        }
    }
    
    // avisa a todos los jugadores que se ha unido al lobby
    private void joinLobby(){
        try {
            outputStream.writeInt(1); // opcion del lobby
            outputStream.writeInt(1); // subopcion de lobby
            
        } catch (IOException ex) {
                    Logger.getLogger(LobbyController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    // se agrega un jugador al lobby
    public void addPlayerLobby(int playerId, boolean host){
        String str;
        
        if(playerId == game.getPlayer().getPlayerId()){
            str = "Te has unido correctamente."; 
        }else{
            str = "Jugador " + playerId + " se ha unido";
        }

        str += host ? " (host)\n " : " \n";
        this.view.getTxtaLobbyPlayers().setText(this.view.getTxtaLobbyPlayers().getText() + str);
    }
    
    // se pasa de pantalla y se inicia el juego para todos los jugadores
    private void startGame(){
        try {
            outputStream.writeInt(1); // opcion del lobby
            outputStream.writeInt(2); // subopcion de lobby
            
        } catch (IOException ex) {
                    Logger.getLogger(LobbyController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}
