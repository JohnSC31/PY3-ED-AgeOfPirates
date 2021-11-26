
package ageofpirates.model;


public abstract class Weapon {
    
    private int cost;
    private String type;
    
    public Weapon(int cost, String type){
        this.cost = cost;
        this.type = type;
    }
    
    public abstract void attack();
    
}
