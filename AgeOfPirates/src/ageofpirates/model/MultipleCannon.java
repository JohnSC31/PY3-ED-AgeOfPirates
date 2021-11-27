
package ageofpirates.model;

import ageofpirates.model.Game.ItemType;


public class MultipleCannon extends Weapon{

    public MultipleCannon() {
        super(1000, "MultipleCannon", ItemType.MULTIPLE_CANNON);
    }

    @Override
    public void attack() {
        // si acierta un disparo de este tipo se crean 4 disparos torpedos de forma aleatoria
    }
    
}
