
package ageofpirates.model;

import ageofpirates.controller.GameController;


public class MineThread extends Thread{
    
    private Mine mine;
    private boolean running;
    private boolean pause;
    private GameController gameController;
    private Game game;
    
    
    public MineThread(Mine mine, Game game, GameController gameController) {
        this.mine = mine;
        this.running = true;
        this.pause = false;
        this.game = game;
        this.gameController = gameController;
    }
    
    public void run(){
        while(running){
            
            try{
                while(pause){
                    sleep(1000);
                }
                
                this.game.getPlayerInventory().updateItemAmount(Game.ItemType.STEEL, mine.getSteelPerTime());
                this.gameController.setPlayerInventory();
                
                sleep(mine.getTime() * 1000); // pasa a milisegundos
                
            } catch(InterruptedException e){
                
            }
            
            
            
            
            
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
