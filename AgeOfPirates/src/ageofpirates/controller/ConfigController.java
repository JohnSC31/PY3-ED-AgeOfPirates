
package ageofpirates.controller;

import ageofpirates.model.Game;
import ageofpirates.model.Island;
import ageofpirates.model.PowerSource;
import ageofpirates.model.SeaCell;
import ageofpirates.model.Vertex;
import ageofpirates.view.ConfigWindow;
import static ageofpirates.view.ConfigWindow.SEA_SIZE;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ConfigController extends Controller implements MouseListener{

    private ConfigWindow view;
    
    // variables
    private Vertex selectedElement;
    private boolean connectIsland;
    private boolean updateGameGraph; // para determinar si actualiza el mar del jugador en la pantalla del juego
    
    private int x1=0;
    private int x2=0;
    private int y1=0;
    private int y2=0;
    
    public ConfigController(ConfigWindow view, Game game, MainController mainController) {
        super(game, mainController);
        this.view = view;
        this.selectedElement = null;
        this.connectIsland = false;
        this.updateGameGraph = false;
        _init_();
    }

    @Override
    public void _init_() {
        // add listeners
        view.getBtnMoveNorth().addActionListener(this);
        view.getBtnMoveSouth().addActionListener(this);
        view.getBtnMoveEaste().addActionListener(this);
        view.getBtnMoveWest().addActionListener(this);
        
        view.getBtnConnectIsland().addActionListener(this);
        view.getBtnConnectIsland().setEnabled(false);
        
        view.getBtnStartGame().addActionListener(this);
        view.getBtnStartGame().setEnabled(game.getPlayer().isHost());
        
        view.getBtnMarket().addActionListener(this);
        
        // listener al oceano
        addMouseListenerSea();
        
        game.initGameGraph();
        setInitialGraph();
    }
    
    

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(e.getSource().equals(view.getBtnMoveNorth())){
            if(this.selectedElement != null){
                game.moveUpIsland(view.getPlayerSea(), this.selectedElement);
                x1=x2=y1=y2=0;//Se evita que la arista quede en el lugar incorrecto
            }
        }
        
        if(e.getSource().equals(view.getBtnMoveSouth())){
            if(this.selectedElement != null){
                game.moveDownIsland(view.getPlayerSea(), this.selectedElement);
                x1=x2=y1=y2=0;
            }
        }
        
        if(e.getSource().equals(view.getBtnMoveEaste())){
            if(this.selectedElement != null){
                game.moveRightIsland(view.getPlayerSea(), this.selectedElement);
                x1=x2=y1=y2=0;
            }
        }
        
        if(e.getSource().equals(view.getBtnMoveWest())){
            if(this.selectedElement != null){
                game.moveLeftIsland(view.getPlayerSea(), this.selectedElement);
                x1=x2=y1=y2=0;
            }
        }
        
        if(e.getSource().equals(view.getBtnConnectIsland())){
            this.connectIsland = true;
            view.getLblConnectStatus().setText("Conectando...");
        }
        
        if(e.getSource().equals(view.getBtnMarket())){
            // se abre el mercado
            mainController.startMarket();
        }
        
        if(e.getSource().equals(view.getBtnStartGame())){
            if(view.getBtnStartGame().getText().equals("Guardar")){
                mainController.closeWindow(view);
                mainController.getGameController().setInitialPlayerSea();
            }else{
                startGame();
            }

        }
            
        
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource().getClass().equals(SeaCell.class)){
            SeaCell clickedLabel = (SeaCell) e.getSource();
            if(clickedLabel.getVertex() != null){
                view.getLblSelectedElement().setText(clickedLabel.getVertex().getIsland().getName());
                if(this.connectIsland){
                    if(x2==0&y2==0){
                        x2=clickedLabel.getX();
                        y2=clickedLabel.getY();
                    }
                    // quiere decir que se conecta el objeto ya seleccionado con este nuevo que se esta seleccionando
                    // se crea la arista y la retorna y con ella se pinta en el panel correspondiente
                    game.setArista(view.getPnlSea(), game.createArista(selectedElement, clickedLabel.getVertex(), x1, y1, x2, y2));
                    this.connectIsland = false; // ya se realizo la conexion
                    x1 = x2= y1= y2=0;
                    //g2.drawLine(0, 0, 100, 100);
                }
                if(x1==0&y1==0){
                    x1=clickedLabel.getX();
                    y1=clickedLabel.getY();
                }
                this.selectedElement = clickedLabel.getVertex();
                view.getBtnConnectIsland().setEnabled(true);
                
            }else{
                x1 = x2= y1= y2=0;
                view.getLblSelectedElement().setText("Selecciona un elemento");
                view.getBtnConnectIsland().setEnabled(false);
                this.selectedElement = null;
            }
            view.getLblConnectStatus().setText("");
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
    
    // ----------------------------------------------------------- METODOS -----------------------------------------------------
    // se agregan los mouse listeners al oceano
    private void addMouseListenerSea(){
        for(int i = 0; i < SEA_SIZE; i++){
            for(int j = 0; j < SEA_SIZE; j++){
                view.getPlayerSea()[i][j].addMouseListener(this);
            }
        }
    }


// setea las islas iniciales de forma aleatoria en el oceano
    private void setInitialGraph(){
        for(int i = 0; i < game.getGraph().size(); i++){
            game.setIslandRandomPosition(view.getPlayerSea(), game.getGraph().get(i));
        }
    }
    
    public void startGame(){
        
        try {
            outputStream.writeInt(2); // opcion del config sea
            outputStream.writeInt(1); // subopcion de config sea
            
        } catch (IOException ex) {
                    Logger.getLogger(LobbyController.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
    
    public void udpatePlayerGraph(){
        this.updateGameGraph = true;
        this.view.getBtnStartGame().setText("Guardar");
        this.view.getBtnStartGame().setEnabled(true);
    
    }
    
    // ---------------------------------------------------------- GETTERS AND SETTERS ------------------------------------------

    
    
    
}
