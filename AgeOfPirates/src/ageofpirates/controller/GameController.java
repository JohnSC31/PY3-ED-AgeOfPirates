
package ageofpirates.controller;

import ageofpirates.model.Game;
import ageofpirates.view.GameWindow;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class GameController extends Controller implements KeyListener{
    
    private GameWindow view;

    public GameController(GameWindow view, Game game, MainController mainController) {
        super(game, mainController);
        this.view = view;
        _init_();
    }

    @Override
    public void _init_() {
        // action listeners
        this.view.getBtnSendMessage().addActionListener(this);
        this.view.getTxtfMessage().addKeyListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(e.getSource().equals(this.view.getBtnSendMessage())){
            if(!this.view.getTxtfMessage().getText().equals("")){
                sendMessage();
            }
        }
        
    }
    
        @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_ENTER){
            if(!this.view.getTxtfMessage().getText().equals("")){
                sendMessage();
            }
        }
    }
    // -------------------------------------------------- METODOS ---------------------------------------------------------------
    private void sendMessage(){
        try {
            outputStream.writeInt(3); // opcion del juego
            outputStream.writeInt(1); // subopcion de juego
            outputStream.writeUTF(this.view.getTxtfMessage().getText());
            
        } catch (IOException ex) {
            Logger.getLogger(LobbyController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        this.view.getTxtfMessage().setText("");
    }
    
    public void recieveMessage(String message, int playerId){
        String player = playerId == game.getPlayer().getPlayerId() ? "Tu: "  : "Jugador " + playerId + ": ";
        
        this.view.getTxtaChat().setText(this.view.getTxtaChat().getText() + player + message + "\n");
    }
    
    // ----------------------------------------------- GETTERS AND SETTERS ------------------------------------------------------


    
    
}
