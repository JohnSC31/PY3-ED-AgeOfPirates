
package ageofpirates.model;

import ageofpirates.controller.GameController;


public class Mine extends Island{
    
    private int steelPerTime; // (kg)
    private int time; // (segundos)
    // PASARLE UNA REFERENCIA DEL JUEGO PARA CON EL THREAD SUMAR EL STEEL,
    private transient MineThread mineThread;
    
    public Mine(int iPos, int jPos) {
        super(iPos, jPos, 1, 2, "Mina", 1000);
        this.steelPerTime = 50;
        this.time = 60; 
        this.mineThread = null;
    }

    
    public void startMining(Game game, GameController gameController){
        // inicia el thread para minar
        this.mineThread = new MineThread(this, game, gameController);
        this.mineThread.start();
    }
    
    // -------------------------------------------------- GETTERS AND SETTERS -----------------------------------------------

    public int getSteelPerTime() {
        return steelPerTime;
    }

    public void setSteelPerTime(int steelPerTime) {
        this.steelPerTime = steelPerTime;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public MineThread getMineThread() {
        return mineThread;
    }
    
    
    
    
    
}
