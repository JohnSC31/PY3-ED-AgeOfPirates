
package ageofpirates.controller;

import ageofpirates.model.Game;
import static ageofpirates.model.Game.CELL_SIZE;
import static ageofpirates.model.Game.SEA_SIZE;
import ageofpirates.model.GraphicElement;
import ageofpirates.model.SeaCell;
import ageofpirates.view.ConfigWindow;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;


public class ConfigController extends Controller implements MouseListener{

    private ConfigWindow view;
    
    // variables
    private GraphicElement selectedElement;
    
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
        displaySea();
        
        view.getBtnStartGame().addActionListener(this);
        view.getBtnStartGame().setEnabled(game.getPlayer().isHost());
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(e.getSource().equals(view.getBtnMoveNorth())){
            if(this.selectedElement != null){
                game.moveUpComponent(this.selectedElement);
            }
        }
        
        if(e.getSource().equals(view.getBtnMoveSouth())){
            if(this.selectedElement != null){
                game.moveDownComponent(this.selectedElement);
            }
        }
        
        if(e.getSource().equals(view.getBtnMoveEaste())){
            if(this.selectedElement != null){
                game.moveRightComponent(this.selectedElement);
            }
        }
        
        if(e.getSource().equals(view.getBtnMoveWest())){
            if(this.selectedElement != null){
                game.moveLeftComponent(this.selectedElement);
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
            if(clickedLabel.getGElement() != null){
                view.getLblSelectedElement().setText(clickedLabel.getGElement().getName());
                this.selectedElement = clickedLabel.getGElement();
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
    // se inicia el oceano en la ventana (se setean los labels en el panel)
    private void displaySea(){
        int x = 0, y = 0;
        for(int i = 0; i < SEA_SIZE; i++){
            for(int j = 0; j < SEA_SIZE; j++){
                this.game.getSea()[i][j].setBounds(x , y, CELL_SIZE, CELL_SIZE);
                this.game.getSea()[i][j].addMouseListener(this);
                view.getPnlSea().add(this.game.getSea()[i][j]); 
                
                x += CELL_SIZE;
            }
            x = 0;
            y += CELL_SIZE;
        }
        
        game.initGraph();
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
