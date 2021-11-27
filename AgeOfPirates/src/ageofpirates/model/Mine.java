
package ageofpirates.model;

import java.util.ArrayList;
import javax.swing.ImageIcon;


public class Mine extends Island{
    
    private int steelPerTime; // (kg)
    private int time; // (segundos)
    
    
    public Mine(int iPos, int jPos, ArrayList<ImageIcon> icons) {
        super(iPos, jPos, icons, 1, 2, "Mina", 1000);
        this.steelPerTime = 50;
        this.time = 60; 
    }

    @Override
    public void action() {
        
    }
    
}
