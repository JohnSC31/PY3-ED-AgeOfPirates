
package ageofpirates.model;

//Componente de testeo

import java.awt.Color;


public class TestComponent {
    private int i;
    private int j; 
    private int xDimension;
    private int yDimension;
    private String name;
    private Color colorBg = Color.ORANGE; // el reemplazo serian las imagenes
    // arreglo de imageicon

    public TestComponent(int i, int j, int xDimension, int yDimension, String name) {
        this.xDimension = xDimension;
        this.yDimension = yDimension;
        this.name = name;
        this.i = i;
        this.j = j;
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public int getJ() {
        return j;
    }

    public void setJ(int j) {
        this.j = j;
    }

    public int getxDimension() {
        return xDimension;
    }

    public int getyDimension() {
        return yDimension;
    }

    public String getName() {
        return name;
    }
    
    
    
    
    
}
