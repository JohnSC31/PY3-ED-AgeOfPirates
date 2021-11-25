
package ageofpirates.controller;

import ageofpirates.model.Game;
import ageofpirates.view.GameWindow;
import java.awt.event.ActionEvent;


public class GameController extends Controller{
    
    private GameWindow view;

    public GameController(GameWindow view, Game game, MainController mainController) {
        super(game, mainController);
        this.view = view;
    }

    @Override
    public void _init_() {
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
    }
    
    
    // -------------------------------------------------- METODOS ---------------------------------------------------------------
    
    
    
    // ----------------------------------------------- GETTERS AND SETTERS ------------------------------------------------------
    
    
}
