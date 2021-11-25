
package ageofpirates.model;

//Componente de testeo

import java.awt.Color;
import java.util.ArrayList;
import javax.swing.ImageIcon;


public class TestComponent extends GraphicElement{

    private String name;
    private Color colorBg = Color.ORANGE; // el reemplazo serian las imagenes
    // arreglo de imageicon

    public TestComponent(int i, int j, int xDimension, int yDimension, String name) {
        super(i, j, new ArrayList<ImageIcon>(), xDimension, yDimension);
        this.name = name;
    }
    
    public TestComponent(int i, int j, int xDimension, int yDimension, String name, ArrayList<ImageIcon> icons){
        super(i,j, icons, xDimension, yDimension);
        this.name = name;
    }
    
    
    
    // ----------------------------- METODOS -------------------------------------------------
    
    
    
}
