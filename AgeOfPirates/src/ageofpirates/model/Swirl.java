
package ageofpirates.model;

import java.util.ArrayList;
import javax.swing.ImageIcon;

public class Swirl extends Island{

    public Swirl(int iPos, int jPos, ArrayList<ImageIcon> icons) {
        super(iPos, jPos, icons, 1, 1, "Remolino", 0);
    }

    @Override
    public void action() {
    }
    
}
