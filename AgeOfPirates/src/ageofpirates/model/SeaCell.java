
package ageofpirates.model;

import static ageofpirates.controller.MainController.PALLETE;
import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;


public class SeaCell extends JLabel{
    private int i;
    private int j;
    private boolean occupied;
    private ImageIcon icon;
    private GraphicElement element; //(sera nullo hasta que lo ocupe)

    public SeaCell(int i, int j) {
        this.i = i;
        this.j = j;
        this.occupied = false;
        this.element = null;
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        this.setBackground(PALLETE[1]);
        this.setOpaque(true);
    }
    
    
    
    // ---------------------------------------------- METODOS ------------------------------------------------
    
    // -------------------------------------- GETTERS AND SETTERS -----------------------------------------------

    public int getI() {
        return i;
    }

    public int getJ() {
        return j;
    }

    public boolean isOccupied() {
        return occupied;
    }

    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }
    
    public void setGElement(GraphicElement newElement){
        this.element = newElement;
    }
    
    public GraphicElement getGElement(){
        return element;
    }
    
}
