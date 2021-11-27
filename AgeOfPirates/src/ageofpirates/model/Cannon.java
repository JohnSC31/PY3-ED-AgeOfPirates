
package ageofpirates.model;

import ageofpirates.model.Game.ItemType;

public class Cannon extends Weapon{

    public Cannon() {
        super(500, "Cannon", ItemType.CANNON);
    }

    @Override
    public void attack() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
