
package ageofpirates.controller;

import ageofpirates.view.*; // se importan todas las vistas
import ageofpirates.model.*; // se importan todos los modelos
import interfaces.iWindow;


public class MainController {
    
    // vistas del juego
    private LobbyWindow lobbyView;
    
    // modelo principal del juego
    private Game game;
    
    // subcontroladores del juego
    private LobbyController lobbyController;
    
    public MainController(){
        this.game = new Game(this);
        
        startLobby();
    }
    
    
    
    // ------------------------------------------- METODOS ------------------------------------------------------------
    
    // recibe 2 iventanas colocar una en invisible y la otra en visible
    public void changeWindow(iWindow fromWindow, iWindow toWindow){
        fromWindow.setVisibility(false); // esconde la anterior
        toWindow.setVisibility(true); // muestra la siguientes
    }
    
    public void showWindow(iWindow window){
        window.setVisibility(true);
    }
    
    public void closeWindow(iWindow window){
        window.setVisibility(false);
    }
    
    
    // METODOS PARA EL INICIO DE PANTALLAS
    
    public void startLobby(){
        this.lobbyView = new LobbyWindow();
        this.lobbyController = new LobbyController(this.lobbyView, this.game, this);
        showWindow(lobbyView);
    }
   
    
    // ----------------------------------------- GETTERS AND SETTERS -----------------------------------------------------------

    public LobbyController getLobbyController() {
        return lobbyController;
    }
    
    
    
}
