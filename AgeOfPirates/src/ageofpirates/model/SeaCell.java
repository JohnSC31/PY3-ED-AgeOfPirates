
package ageofpirates.model;

import static ageofpirates.controller.MainController.PALLETE;
import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;


public class SeaCell extends JLabel{
    private int i;
    private int j;
    private boolean enemySea; // determina si es mi oceano o el del enemigo
    private Vertex vertex; //(sera nullo hasta que lo ocupe)

    public SeaCell(int i, int j, boolean enemySea) {
        this.i = i;
        this.j = j;
        this.enemySea = enemySea;

        this.vertex = null;
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        this.setOpaque(false);
    }

    // ---------------------------------------------- METODOS ------------------------------------------------
    
    // -------------------------------------- GETTERS AND SETTERS -----------------------------------------------

    public int getI() {
        return i;
    }

    public int getJ() {
        return j;
    }
    
    public void setVertex(Vertex vertex){
        this.vertex = vertex;
    }
    
    public Vertex getVertex(){
        return vertex;
    }

    public boolean isEnemySea() {
        return enemySea;
    }
    
    
    
}
