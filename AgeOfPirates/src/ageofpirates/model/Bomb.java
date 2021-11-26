
package ageofpirates.model;


public class Bomb extends Weapon{

    public Bomb() {
        super(2000, "Bomb");
    }

    @Override
    public void attack() {
       // un set de 3 cartuchos de dinamita que se colocan en un solo turno, luego explota en 2x1 o 1x2 (random)
    }
    
}
