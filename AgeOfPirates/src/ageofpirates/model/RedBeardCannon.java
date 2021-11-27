
package ageofpirates.model;

import ageofpirates.model.Game.ItemType;


public class RedBeardCannon extends Weapon{

    public RedBeardCannon() {
        super(5000, "ReadBeardCannon", ItemType.RED_BEARD_CANNON);
    }

    @Override
    public void attack() {
        // permite 10 canones en un solo turno
    }
    
}
