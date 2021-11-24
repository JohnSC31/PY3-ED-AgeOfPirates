
// clase para la herencia de los controladores

package ageofpirates.controller;

import ageofpirates.model.Game;
import ageofpirates.view.LobbyWindow;
import interfaces.iWindow;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataOutputStream;


public abstract class Controller implements ActionListener{
    
    public Game game;
    public MainController mainController;
    public DataOutputStream outputStream;
    
    public Controller(Game game, MainController mainController){
        this.game = game;
        this.mainController = mainController;
        this.outputStream = game.getPlayer().getOutputStream();
    }
    
    public abstract void _init_();

    @Override
    public abstract void actionPerformed(ActionEvent e);
}
