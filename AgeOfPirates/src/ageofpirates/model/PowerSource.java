
package ageofpirates.model;

import java.util.ArrayList;
import javax.swing.ImageIcon;


public class PowerSource extends Island{

    public PowerSource(int iPos, int jPos, ArrayList<ImageIcon> icons) {
        super(iPos, jPos, icons, 2, 2, "Fuente de Energia", 12000);
    }

    @Override
    public void action() {
        // accion para la fuente de energia
    }
    
}
