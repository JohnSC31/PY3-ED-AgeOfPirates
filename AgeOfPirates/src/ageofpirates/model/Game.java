
package ageofpirates.model;

import ageofpirates.controller.MainController;


public class Game {
    
    private Player player;
    private MainController mainController;

    public Game(MainController mainController) {
        this.mainController = mainController;
        this.player = new Player(this.mainController); // se le pasa el controlador principal
    }
    
    
    
    // ------------------------------------------------- GETTERS AND SETTERS ----------------------------------------------------------

    public Player getPlayer() {
        return player;
    }
    
    
    
}
