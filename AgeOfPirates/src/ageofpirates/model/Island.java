
package ageofpirates.model;

import java.io.Serializable;

abstract public class Island implements Serializable{
    
    private int iPos;
    private int jPos;
    private int yDimension; // dimesiones en columnas
    private int xDimension; // dimensiones en filas
    private String name;
    private int price;
    private boolean destroyed; // determina si todos sus cuadrantes estan destruidos

    public Island(int iPos, int jPos, int yDimension, int xDimension, String name, int price) {
        this.iPos = iPos;
        this.jPos = jPos;
        this.yDimension = yDimension;
        this.xDimension = xDimension;
        this.name = name;
        this.price = price;
        this.destroyed = false;
    }

    public Island(int yDimension, int xDimension, String name) {
        this.iPos = -1;
        this.jPos = -1;
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
