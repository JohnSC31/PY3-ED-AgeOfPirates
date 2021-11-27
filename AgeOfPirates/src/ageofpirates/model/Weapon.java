
package ageofpirates.model;

import ageofpirates.model.Game.ItemType;


public abstract class Weapon {
    
    private int cost;
    private String type;
    private ItemType itemType;
    
    public Weapon(int cost, String type, ItemType itemType){
        this.cost = cost;
        this.type = type;
        this.itemType = itemType;
    }
    
    public abstract void attack();

    public int getCost() {
        return cost;
    }

    public String getType() {
        return type;
    }
    
    public ItemType getItemType(){
        return itemType;
    }
    
    
}
