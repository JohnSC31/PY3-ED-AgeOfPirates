
package ageofpirates.controller;

import ageofpirates.view.*; // se importan todas las vistas
import ageofpirates.model.*; // se importan todos los modelos
import interfaces.iWindow;
import java.awt.Color;
import java.awt.Image;
import javax.swing.ImageIcon;


public class MainController {
    
    // vistas del juego
    private LobbyWindow lobbyView;
    private ConfigWindow configView;
    private GameWindow gameView;
    private MarketWindow marketView;
    private MineWindow mineView;
    private ArmoryWindow armoryView;
    
    // modelo principal del juego
    private Game game;
    
    // subcontroladores del juego
    private LobbyController lobbyController;
    private ConfigController configController;
    private GameController gameController;
    private MarketController marketController;
    private MineController mineController;
    private ArmoryController armoryController;
    
    // colores
                                     // Rich black          Police Blue             verdigris               Diamond                     Caramel                 Saddle Brown
    public static final Color[] PALLETE = {new Color(1, 3, 7), new Color(57, 84, 97), new Color(68, 159, 175), new Color(177, 244, 252), new Color(244, 212, 153), new Color(131, 82, 17)};
    
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
    
    public void startConfigSea(iWindow fromWindow){
        this.configView = new ConfigWindow();
        this.configController = new ConfigController(this.configView, this.game, this);
        changeWindow(fromWindow, configView);
    }
    
    public void startGame(){
        this.gameView = new GameWindow();
        this.gameController = new GameController(gameView, this.game, this);
        changeWindow(configView, gameView);
    }
    
    public void startMarket(){
        this.marketView = new MarketWindow();
        this.marketController = new MarketController(this.marketView, this.game, this);
        showWindow(marketView);
    }
    
    public void startMine(Island island){
        // se abre la mina seleccionada
        this.mineView = new MineWindow();
        this.mineController = new MineController(mineView, this.game, this, island);
        showWindow(mineView);
    }
    
    public void startArmory(Island island){
        this.armoryView = new ArmoryWindow();
        this.armoryController = new ArmoryController(armoryView, this.game, this, island);
        showWindow(armoryView);
    }
   
    
    // ----------------------------------------- GETTERS AND SETTERS -----------------------------------------------------------

    public Game getGame() {
        return game;
    }

    public LobbyController getLobbyController() {
        return lobbyController;
    }

    public LobbyWindow getLobbyView() {
        return lobbyView;
    }

    public ConfigWindow getConfigView() {
        return configView;
    }

    public ConfigController getConfigController() {
        return configController;
    }

    public GameWindow getGameView() {
        return gameView;
    }

    public GameController getGameController() {
        return gameController;
    }

    public MarketWindow getMarketView() {
        return marketView;
    }

    public MarketController getMarketController() {
        return marketController;
    }
    
    
    
    
    
    // ---------------------------------------------------------- METODOS VARIOS ---------------------------------------------------------------
    
    // recibe un imageicon y le cambia el tamano al correspondiente
    public static ImageIcon resizeIcon(ImageIcon icon, int width, int height ){
        Image iconImage = icon.getImage();
        Image resizedIconImage = iconImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(resizedIconImage);
    }
    
    
    
    
}
