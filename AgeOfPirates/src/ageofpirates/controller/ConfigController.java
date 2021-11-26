
package ageofpirates.controller;

import ageofpirates.model.Game;
import ageofpirates.model.Island;
import ageofpirates.model.PowerSource;
import ageofpirates.model.SeaCell;
import ageofpirates.view.ConfigWindow;
import static ageofpirates.view.ConfigWindow.SEA_SIZE;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ConfigController extends Controller implements MouseListener{

    private ConfigWindow view;
    
    // variables
    private Island selectedElement;
    
    public ConfigController(ConfigWindow view, Game game, MainController mainController) {
        super(game, mainController);
        this.view = view;
        this.selectedElement = null;
        _init_();
    }

    @Override
    public void _init_() {
        // add listeners
        view.getBtnMoveNorth().addActionListener(this);
        view.getBtnMoveSouth().addActionListener(this);
        view.getBtnMoveEaste().addActionListener(this);
        view.getBtnMoveWest().addActionListener(this);
        
        view.getBtnStartGame().addActionListener(this);
        view.getBtnStartGame().setEnabled(game.getPlayer().isHost());
        
        // listener al oceano
        addMouseListenerSea();
        
        game.initGameGraph();
        setInitialGraph();
    }
    
    

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(e.getSource().equals(view.getBtnMoveNorth())){
            if(this.selectedElement != null){
                game.moveUpIsland(view.getPlayerSea(), this.selectedElement);
            }
        }
        
        if(e.getSource().equals(view.getBtnMoveSouth())){
            if(this.selectedElement != null){
                game.moveDownIsland(view.getPlayerSea(), this.selectedElement);
            }
        }
        
        if(e.getSource().equals(view.getBtnMoveEaste())){
            if(this.selectedElement != null){
                game.moveRightIsland(view.getPlayerSea(), this.selectedElement);
            }
        }
        
        if(e.getSource().equals(view.getBtnMoveWest())){
            if(this.selectedElement != null){
                game.moveLeftIsland(view.getPlayerSea(), this.selectedElement);
            }
        }
        
        if(e.getSource().equals(view.getBtnStartGame())){
            startGame();
        }
            
        
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource().getClass().equals(SeaCell.class)){
            SeaCell clickedLabel = (SeaCell) e.getSource();
            if(clickedLabel.getIsland() != null){
                view.getLblSelectedElement().setText(clickedLabel.getIsland().getName());
                this.selectedElement = clickedLabel.getIsland();
                // setear la accion
            }else{
                view.getLblSelectedElement().setText("Selecciona un elemento");
                this.selectedElement = null;
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
    
    // ----------------------------------------------------------- METODOS -----------------------------------------------------
    // se agregan los mouse listeners al oceano
    private void addMouseListenerSea(){
        for(int i = 0; i < SEA_SIZE; i++){
            for(int j = 0; j < SEA_SIZE; j++){
                view.getPlayerSea()[i][j].addMouseListener(this);
            }
        }
    }


// setea las islas iniciales de forma aleatoria en el oceano
    private void setInitialGraph(){
        for(int i = 0; i < game.getGraph().size(); i++){
            game.setIslandRandomPosition(view.getPlayerSea(), game.getGraph().get(i).getIsland());
        }
    }
    
    public void startGame(){
        
        try {
            outputStream.writeInt(2); // opcion del config sea
            outputStream.writeInt(1); // subopcion de config sea
            
        } catch (IOException ex) {
                    Logger.getLogger(LobbyController.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
    
    // ---------------------------------------------------------- GETTERS AND SETTERS ------------------------------------------
    
}
