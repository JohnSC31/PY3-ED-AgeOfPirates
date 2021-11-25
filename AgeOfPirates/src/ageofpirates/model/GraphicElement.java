
package ageofpirates.model;

import java.util.ArrayList;
import javax.swing.ImageIcon;


// TODOS LOS ELEMENTOS QUE VAN A SER IMPRESOS EN LA PANTALLA HEREDAN DE ESTA CLASE

abstract public class GraphicElement {
    
    private int iPos;
    private int jPos;
    private ArrayList<ImageIcon> icons;
    private int xDimension; // dimesiones en columnas
    private int yDimension; // dimensiones en filas
    private String name;

    public GraphicElement(int iPos, int jPos, ArrayList<ImageIcon> icons, int xDimension, int yDimension, String name) {
        this.iPos = iPos;
        this.jPos = jPos;
        this.icons = icons;
        this.xDimension = xDimension;
        this.yDimension = yDimension;
        this.name = name;
    }
    
    
    // ---------------------------------- METODOS ------------------------------------------------
    
    
    // --------------------------------  GETTERS AND SETTERS --------------------------------------

    public int getiPos() {
        return iPos;
    }

    public void setiPos(int iPos) {
        this.iPos = iPos;
    }

    public int getjPos() {
        return jPos;
    }

    public void setjPos(int jPos) {
        this.jPos = jPos;
    }

    public ArrayList<ImageIcon> getIcons() {
        return icons;
    }

    public void setIcons(ArrayList<ImageIcon> icons) {
        this.icons = icons;
    }

    public int getxDimension() {
        return xDimension;
    }

    public void setxDimension(int xDimension) {
        this.xDimension = xDimension;
    }

    public int getyDimension() {
        return yDimension;
    }

    public void setyDimension(int yDimension) {
        this.yDimension = yDimension;
    }

    public String getName() {
        return name;
    }
    
    
    
    
}
