
package ageofpirates.model;

import java.io.Serializable;


public class SeaCellData implements Serializable{
    
    private int i;
    private int j;
    private boolean enemySea; // determina si es mi oceano o el del enemigo
    private Vertex vertex; //(sera nullo hasta que lo ocupe)
    private boolean destroyed;

    public SeaCellData(int i, int j, boolean enemySea, Vertex vertex, boolean destroyed) {
        this.i = i;
        this.j = j;
        this.enemySea = enemySea;
        this.vertex = vertex;
        this.destroyed = destroyed;
    }

    public int getI() {
        return i;
    }

    public int getJ() {
        return j;
    }

    public boolean isEnemySea() {
        return enemySea;
    }

    public Vertex getVertex() {
        return vertex;
    }

    public boolean isDestroyed() {
        return destroyed;
    }
    
    
    
    
    
}
