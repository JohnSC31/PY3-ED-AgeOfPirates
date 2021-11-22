
package ageofpirates.controller;

import ageofpirates.model.Game;
import ageofpirates.view.LobbyWindow;
import java.io.DataOutputStream;


public class LobbyController {
    
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
        
    }
    
    
}
