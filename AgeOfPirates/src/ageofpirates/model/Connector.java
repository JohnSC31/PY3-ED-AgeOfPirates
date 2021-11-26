
package ageofpirates.model;

import java.util.ArrayList;
import javax.swing.ImageIcon;


public class Connector extends Island{

    public Connector(int iPos, int jPos, ArrayList<ImageIcon> icons) {
        super(iPos, jPos, icons, 1, 1, "Conector");
    }

    @Override
    public void action() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
