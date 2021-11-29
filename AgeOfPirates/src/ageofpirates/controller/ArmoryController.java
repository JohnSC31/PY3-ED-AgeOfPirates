
package ageofpirates.controller;

import ageofpirates.model.Armory;
import ageofpirates.model.Game;
import ageofpirates.model.Game.ItemType;
import ageofpirates.model.Island;
import ageofpirates.view.ArmoryWindow;
import java.awt.event.ActionEvent;


public class ArmoryController extends Controller{

    ArmoryWindow view;
    Armory armory;
    
    public ArmoryController(ArmoryWindow view, Game game, MainController mainController, Island island) {
        super(game, mainController);
        this.view = view;
        this.armory = (Armory)island;
        
        _init_();
    }

    @Override
    public void _init_() {
        this.view.getBtnBack().addActionListener(this);
        this.view.getBtnCreate().addActionListener(this);
        
        // inicializan los datoss
        this.view.getLblTypeWeapon().setText(this.armory.getWeaponType().getName());
        this.view.getBtnCreate().setText(this.armory.getWeaponType().getCost() + "");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(e.getSource().equals(view.getBtnBack())){
            mainController.closeWindow(view);
        }
        
        if(e.getSource().equals(view.getBtnCreate())){
            
            if(this.armory.getWeaponType().getCost() - Integer.parseInt(view.getBtnCreate().getText()) >= 0){
                this.game.getPlayerInventory().updateItemAmount(ItemType.STEEL, - this.armory.getWeaponType().getCost());
                this.game.getPlayerInventory().updateItemAmount(this.armory.getWeaponType(), 1); // suma una arma al inventario
                
                this.mainController.getGameController().setPlayerInventory();
            }

        }
        
    }
    
}
