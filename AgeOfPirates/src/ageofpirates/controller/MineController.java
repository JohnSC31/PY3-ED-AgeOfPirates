
package ageofpirates.controller;

import ageofpirates.model.Game;
import ageofpirates.model.Island;
import ageofpirates.model.Mine;
import ageofpirates.view.MineWindow;
import java.awt.event.ActionEvent;


public class MineController extends Controller{
    
    private Mine mine;
    private MineWindow view;

    public MineController(MineWindow view, Game game, MainController mainController, Island island) {
        super(game, mainController);
        this.mine = (Mine)island;
        this.view = view;
        _init_();
    }

    
    // ------------------------------------------- METODOS  -------------------------------------------------------------------
    @Override
    public void _init_() {
        // listeners
        this.view.getBtnBack().addActionListener(this);
        this.view.getBtnUpdate().addActionListener(this);
        
        // cargar los datos de la mina
        this.view.getInpKgPerTime().setText(this.mine.getSteelPerTime() + "");
        this.view.getInpTime().setText(this.mine.getTime() + "");
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(e.getSource().equals(view.getBtnUpdate())){
            mine.setSteelPerTime(Integer.parseInt(this.view.getInpKgPerTime().getText()));
            mine.setTime(Integer.parseInt(this.view.getInpTime().getText()));
        }
        
        if(e.getSource().equals(view.getBtnBack())){
            mainController.closeWindow(view);
        }
        
    }
    
    // ------------------------------------------- GETTERS AND SETTERS ----------------------------------------------------------
    
}
