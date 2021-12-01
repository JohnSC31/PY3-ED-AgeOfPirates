/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ageofpirates.model;

import ageofpirates.controller.GameController;
import java.util.Random;

/**
 *
 * @author PC
 */
public class TempleThread extends Thread{
    private Temple temple;
    private boolean running;
    private boolean pause;
    private GameController gameController;
    private Game game;
    
    public TempleThread(Temple temple, Game game, GameController gameController){
        this.temple=temple;
        this.game=game;
        this.gameController=gameController;
        this.running=true;
        this.pause = false;
    }
    
    public void run(){
        while(running){
            try{
                while(pause){
                    sleep(1000);
                }
                sleep(300000);//Se pausa por 5 minutos
                Random aleatorio= new Random();
                boolean comodin=aleatorio.nextBoolean();
                if (comodin){
                    this.gameController.setComodin("Escudo");
                } else{
                    this.gameController.setComodin("Kraken");
                }
            } catch(InterruptedException e){}
        }
        
    }
        public void stopThread(){
        this.running = false;
    }
    
    public void pauseThread(){
        this.pause = true;
    }
    
    public void resumeThread(){
        this.pause = false;
    }
}
