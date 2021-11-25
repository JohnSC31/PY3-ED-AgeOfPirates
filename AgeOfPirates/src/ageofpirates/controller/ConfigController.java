
package ageofpirates.controller;

import ageofpirates.model.Game;
import static ageofpirates.model.Game.CELL_SIZE;
import static ageofpirates.model.Game.SEA_SIZE;
import ageofpirates.model.SeaCell;
import ageofpirates.view.ConfigWindow;
import java.awt.Color;
import java.awt.event.ActionEvent;
import javax.swing.BorderFactory;


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
        displaySea();
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
    }
    
    // ----------------------------------------------------------- METODOS -----------------------------------------------------
    // se inicia el oceano en la ventana (se setean los labels en el panel)
    private void displaySea(){
        int x = 0, y = 0;
        for(int i = 0; i < SEA_SIZE; i++){
            for(int j = 0; j < SEA_SIZE; j++){
                this.game.getSea()[i][j].setBounds(x , y, CELL_SIZE, CELL_SIZE);
                this.game.getSea()[i][j].setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
                this.game.getSea()[i][j].setBackground(Color.orange);
                this.game.getSea()[i][j].setOpaque(true);
                view.getPnlSea().add(this.game.getSea()[i][j]);
                
                x += CELL_SIZE;
            }
            x = 0;
            y += CELL_SIZE;
        }
        
        game.initGraph();
        this.view.getPnlSea().repaint();
        System.out.println("Sea updated x4");
//        updateSea();
    }
    
    public void updateSea(){
        for(int i = 0; i < SEA_SIZE; i++){
            for(int j = 0; j < SEA_SIZE; j++){
                this.game.getSea()[i][j].repaint();
            }
        }
    }
    
    
    // ---------------------------------------------------------- GETTERS AND SETTERS -------------------------------------------
    
}
