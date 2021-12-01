
package ageofpirates.model;

import java.io.Serializable;


public class Target implements Serializable{
    int i;
    int j;

    public Target(int i, int j) {
        this.i = i;
        this.j = j;
    }

    public int getI() {
        return i;
    }

    public int getJ() {
        return j;
    }
    
    
}
