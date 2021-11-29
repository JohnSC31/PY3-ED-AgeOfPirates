
package ageofpirates.model;

import interfaces.iAction;
import java.io.Serializable;
import java.util.ArrayList;
import javax.swing.ImageIcon;


abstract public class Island implements iAction, Serializable{
    
    private int iPos;
    private int jPos;
    private ArrayList<ImageIcon> icons;
    private int yDimension; // dimesiones en columnas
    private int xDimension; // dimensiones en filas
    private String name;
    private int price;
    private boolean destroyed; // determina si todos sus cuadrantes estan destruidos

    public Island(int iPos, int jPos, ArrayList<ImageIcon> icons, int yDimension, int xDimension, String name, int price) {
        this.iPos = iPos;
        this.jPos = jPos;
        this.icons = icons;
        this.yDimension = yDimension;
        this.xDimension = xDimension;
        this.name = name;
        this.price = price;
        this.destroyed = false;
    }

    public Island(ArrayList<ImageIcon> icons, int yDimension, int xDimension, String name) {
        this.iPos = -1;
        this.jPos = -1;
        this.icons = icons;
        this.yDimension = yDimension;
        this.xDimension = xDimension;
        this.name = name;
    }
    
    
    
    // ----------------------------------------- GETTERS ANS SETTERS ---------------------------------------------

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

    public int getyDimension() {
        return yDimension;
    }


    public int getxDimension() {
        return xDimension;
    }


    public String getName() {
        return name;
    } 

    public ArrayList<ImageIcon> getIcons() {
        return icons;
    }

    public int getPrice() {
        return price;
    }

    public boolean isDestroyed() {
        return destroyed;
    }

    public void setDestroyed(boolean destroyed) {
        this.destroyed = destroyed;
    }
    
    
    
}
