
package ageofpirates.model;

import ageofpirates.controller.GameController;
import java.util.ArrayList;
import javax.swing.ImageIcon;


public class Temple extends Island{
    private transient TempleThread templeThread;

    public Temple(int iPos, int jPos) {
        super(iPos, jPos, 1, 2, "Templo", 2500);
    }

    
    public void startTemple(Game game, GameController gameController){
        this.templeThread= new TempleThread(this, game, gameController);
        this.templeThread.start();
    }
    public TempleThread getTempleThread(){
        return templeThread;
    }

}