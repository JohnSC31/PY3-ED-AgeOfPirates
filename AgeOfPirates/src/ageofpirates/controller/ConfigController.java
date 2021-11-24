
package ageofpirates.controller;

import ageofpirates.model.Game;
import ageofpirates.view.ConfigWindow;
import java.awt.event.ActionEvent;


public class ConfigController extends Controller{

    private ConfigWindow view;
    
    public ConfigController(ConfigWindow view, Game game, MainController mainController) {
        super(game, mainController);
        this.view = view;
        _init_();
    }

    @Override
    public void _init_() {
        // add listeners
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
    }
    
}
