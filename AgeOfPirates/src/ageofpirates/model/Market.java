
package ageofpirates.model;

import java.util.ArrayList;
import javax.swing.ImageIcon;


public class Market extends Island{

    public Market(int iPos, int jPos, ArrayList<ImageIcon> icons) {
        super(iPos, jPos, icons, 1, 2, "Mercado");
    }

    @Override
    public void action() {
        // despliegue para la pantalla del mercados
    }
    
}
