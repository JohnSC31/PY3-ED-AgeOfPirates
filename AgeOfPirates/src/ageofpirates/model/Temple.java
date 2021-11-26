
package ageofpirates.model;

import java.util.ArrayList;
import javax.swing.ImageIcon;


public class Temple extends Island{

    public Temple(int iPos, int jPos, ArrayList<ImageIcon> icons) {
        super(iPos, jPos, icons, 1, 2, "Templo");
    }

    @Override
    public void action() {
    }
    
}
