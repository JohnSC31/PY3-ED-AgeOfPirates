
package ageofpirates.model;


public class MultipleCannon extends Weapon{

    public MultipleCannon() {
        super(1000, "MultipleCannon");
    }

    @Override
    public void attack() {
        // si acierta un disparo de este tipo se crean 4 disparos torpedos de forma aleatoria
    }
    
}
