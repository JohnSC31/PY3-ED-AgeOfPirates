
package ageofpirates.controller;

import ageofpirates.model.Arista;
import ageofpirates.model.Game;
import ageofpirates.model.Vertex;
import ageofpirates.view.GameWindow;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class GameController extends Controller implements KeyListener{
    
    private GameWindow view;

    public GameController(GameWindow view, Game game, MainController mainController) {
        super(game, mainController);
        this.view = view;
        _init_();
    }

    @Override
    public void _init_() {
        // action listeners
        this.view.getBtnSendMessage().addActionListener(this);
        this.view.getTxtfMessage().addKeyListener(this);
        //inicializacion del tablero
        setInitialSea();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(e.getSource().equals(this.view.getBtnSendMessage())){
            if(game.getGraph()!=null){
                System.out.println("El grafo tiene contenido");
            }
            if(!this.view.getTxtfMessage().getText().equals("")){
                sendMessage();
            }
        }
        
    }
    
        @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_ENTER){
            if(!this.view.getTxtfMessage().getText().equals("")){
                sendMessage();
            }
        }
    }
    // -------------------------------------------------- METODOS ---------------------------------------------------------------
    private void sendMessage(){
        try {
            outputStream.writeInt(3); // opcion del juego
            outputStream.writeInt(1); // subopcion de juego
            outputStream.writeUTF(this.view.getTxtfMessage().getText());
            
        } catch (IOException ex) {
            Logger.getLogger(LobbyController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        this.view.getTxtfMessage().setText("");
    }
    
    public void recieveMessage(String message, int playerId){
        String player = playerId == game.getPlayer().getPlayerId() ? "Tu: "  : "Jugador " + playerId + ": ";
        
        this.view.getTxtaChat().setText(this.view.getTxtaChat().getText() + player + message + "\n");
    }
    
    private void setInitialSea(){
        for(int i = 0; i < game.getGraph().size(); i++){
            game.setIsland(view.getPlayerSea(), game.getGraph().get(i));
        }
        view.setBackground(Color.yellow);
        view.getPnlSea().getGraphics().drawLine(0, 100, 150, 200);
        showLines();
    }
    private void showLines(){
        Graphics2D g2 =(Graphics2D)view.getPnlSea().getGraphics();
        g2.setColor(Color.WHITE);
        ArrayList<Vertex> losVertex=game.getGraph();
        for (int i = 0; i < losVertex.size(); i++) {
            System.out.println("El elemento numero"+(i+1));
            ArrayList<Arista> lasAristas=losVertex.get(i).getAristas();
            for (int j = 0; j < lasAristas.size(); j++) {
                ArrayList<Integer> coordenadas=lasAristas.get(j).getCoord();
                System.out.println("Coordenada x1: "+coordenadas.get(0));
                System.out.println("Coordenada y1: "+coordenadas.get(1));
                System.out.println("Coordenada x2: "+coordenadas.get(2));
                System.out.println("Coordenada y2: "+coordenadas.get(3));
                g2.drawLine(coordenadas.get(0), coordenadas.get(1),
                        coordenadas.get(2), coordenadas.get(3));
            }
        }
    }
    // ----------------------------------------------- GETTERS AND SETTERS ------------------------------------------------------


    
    
}
