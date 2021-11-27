
package ageofpirates.model;

import ageofpirates.model.Game.ItemType;
import java.io.Serializable;


public class Inventory implements Serializable{
    
    private int steel;
    private int cannon;
    private int multipleCannon;
    private int bomb;
    private int rBCannon;
    private int ghostShip;
    
    public Inventory(){
        this.steel = 0;
        this.cannon = 0;
        this.multipleCannon = 0;
        this.bomb = 0;
        this.rBCannon = 0;
        this.ghostShip = 0;
    }
    
    
    public int getItemAmount(ItemType type){
        
        if(null != type)switch (type) {
            case STEEL: return steel;
            case CANNON: return cannon;
            case MULTIPLE_CANNON: return multipleCannon;
            case BOMB: return bomb;
            case RED_BEARD_CANNON: return rBCannon;
            case GHOST_SHIP: return ghostShip;
        }
        
        return -1;
    }
    
    public void updateItemAmount(ItemType type, int amount){
        switch (type) {
            case STEEL: 
                steel += amount;
                if(steel < 0) steel = 0;
                break;
            case CANNON: 
                cannon += amount;
                if(cannon < 0) cannon = 0;
                break;
            case MULTIPLE_CANNON:
                multipleCannon += amount;
                if(multipleCannon < 0) multipleCannon = 0;
                break;
            case BOMB: 
                bomb += amount;
                if(bomb < 0) bomb = 0;
                break;
            case RED_BEARD_CANNON: 
                rBCannon += amount;
                if(rBCannon < 0) rBCannon = 0;
                break;
            case GHOST_SHIP: 
                ghostShip += amount;
                if(ghostShip < 0) ghostShip = 0;
                break;
        }
    }
 
}
