
package ageofpirates.controller;

import static ageofpirates.controller.MainController.PALLETE;
import ageofpirates.model.Arista;
import ageofpirates.model.Game;
import ageofpirates.model.Game.ItemType;
import ageofpirates.model.Graph;
import ageofpirates.model.SeaCell;
import ageofpirates.model.Target;
import ageofpirates.model.Vertex;
import static ageofpirates.view.ConfigWindow.SEA_SIZE;
import ageofpirates.view.GameWindow;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JButton;


public class GameController extends Controller implements KeyListener, MouseListener{
    
    private GameWindow view;
    private boolean playerTurn;
    private Vertex selectedVertex;
    private int weaponTargetAmount; // la cantidad de objetivos targetables por el arma seleccionada (celdas en el mar enemigo)
    private ItemType weaponType;
    private ArrayList<Target> targets;
    // cuando termina de disparar colocarlo en -1
    
    private ObjectOutputStream objOutputStream = null;

    public GameController(GameWindow view, Game game, MainController mainController) {
        super(game, mainController);
        this.view = view;
        setPlayerTurn(game.getPlayer().isHost());
        this.selectedVertex = null;
        this.weaponTargetAmount = -1; // estado en el que esta disponble seleccionar una nueva arma
        this.targets = new ArrayList<>();
        
        this.objOutputStream = game.getPlayer().getObjOutputStream();
        
        _init_();
    }

    @Override
    public void _init_() {
        // action listeners
        this.view.getBtnSendMessage().addActionListener(this);
        this.view.getTxtfMessage().addKeyListener(this);
        this.view.getBtnOpen().setEnabled(false);
        
        // botones para el armamento
        this.view.getBtnCannon().addActionListener(this);
        this.view.getBtnMultipleCannon().addActionListener(this);
        this.view.getBtnBomb().addActionListener(this);
        this.view.getBtnRBCannon().addActionListener(this);
        this.view.getBtnGhostShip().addActionListener(this);
        this.view.getBtnComodin().addActionListener(this);
        
        this.view.getBtnAttack().addActionListener(this);
        this.view.getBtnAttack().setEnabled(false);
        
        this.view.getBtnOpen().addActionListener(this);
        this.view.getBtnConfig().addActionListener(this);

        setInitialPlayerSea(); // se inicia el oceano del jugador
        showLines(); //Se dibujan las lineas
        setPlayerInventory(); // seteo del inventario inicial
        
        addMouseListenerSea();
        
        requestEnemies(); // peticion de mis enemigos
    }
    
    private void addMouseListenerSea(){
        for(int i = 0; i < SEA_SIZE; i++){
            for(int j = 0; j < SEA_SIZE; j++){
                view.getPlayerSea()[i][j].addMouseListener(this);
                view.getEnemySea()[i][j].addMouseListener(this);
            }
        }
        //inicializacion del tablero
        //setInitialSea();
    }
    
    // establece el grafo en la matriz y ademas inicia los thread de las minas que no han iniciado
    public void setInitialPlayerSea(){
        game.setSea(view.getPnlSea(), view.getPlayerSea(), game.getGraph()); // seteo inicial del mar del jugador actual
        game.startTemple(this);
        game.startMining(view.getLblSteel(), this);
        // comenzar el templo
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(e.getSource().equals(this.view.getBtnSendMessage())){
            if(!this.view.getTxtfMessage().getText().equals("")){
                sendMessage();
            }
        }
        
        if(e.getSource().equals(view.getBtnCannon())){
            
            if(game.getPlayerInventory().getItemAmount(ItemType.CANNON) > 0 && weaponTargetAmount == -1){
                game.getPlayerInventory().updateItemAmount(ItemType.CANNON, -1);
                setPlayerInventory(); // se actualiza todo el inventario
                
                this.weaponTargetAmount = 1; // cantidad de targets 
                this.weaponType = ItemType.CANNON;
                this.view.getLblWeaponSelected().setText("Cañon");
            }
        }
        if(e.getSource().equals(view.getBtnMultipleCannon())){
            if(game.getPlayerInventory().getItemAmount(ItemType.MULTIPLE_CANNON) > 0 && weaponTargetAmount == -1){
                game.getPlayerInventory().updateItemAmount(ItemType.MULTIPLE_CANNON, -1);
                setPlayerInventory(); // se actualiza todo el inventario
                
                this.weaponTargetAmount = 1; // cantidad de targets 
                this.weaponType = ItemType.MULTIPLE_CANNON;
                this.view.getLblWeaponSelected().setText("Cañon Multiple");
            }
        }
        if(e.getSource().equals(view.getBtnBomb())){
            if(game.getPlayerInventory().getItemAmount(ItemType.CANNON) > 0 && weaponTargetAmount == -1){
                game.getPlayerInventory().updateItemAmount(ItemType.CANNON, -1);
                setPlayerInventory(); // se actualiza todo el inventario
                
                this.weaponTargetAmount = 3; // cantidad de targets 
                this.weaponType = ItemType.BOMB;
                this.view.getLblWeaponSelected().setText("Bomba");
            }
        }
        if(e.getSource().equals(view.getBtnRBCannon())){
            if(game.getPlayerInventory().getItemAmount(Game.ItemType.CANNON) > 0 && weaponTargetAmount == -1){
                game.getPlayerInventory().updateItemAmount(Game.ItemType.CANNON, -1);
                setPlayerInventory(); // se actualiza todo el inventario
                
                this.weaponTargetAmount = 10; // cantidad de targets 
                this.weaponType = ItemType.RED_BEARD_CANNON;
                this.view.getLblWeaponSelected().setText("Cañon Barba Roja");
            }
        }
        if(e.getSource().equals(view.getBtnGhostShip())){
            if(game.getPlayerInventory().getItemAmount(Game.ItemType.CANNON) > 0 && weaponTargetAmount == -1){
                game.getPlayerInventory().updateItemAmount(Game.ItemType.CANNON, -1);
                setPlayerInventory(); // se actualiza todo el inventario
                
                this.weaponTargetAmount = 1; // cantidad de targets 
                this.view.getLblWeaponSelected().setText("Cañon");
            }
        }
        if(e.getSource().equals(view.getBtnComodin())){
            
        }
        
        if(e.getSource().equals(view.getBtnAttack())){
            // se presiona atacar al rival
            attackEnemySea();
        }
        
        if(e.getSource().equals(view.getBtnOpen())){
            // se abre un elemento
            if(this.selectedVertex != null){
                
                if(selectedVertex.getIsland().getName().equals("Mina")){
                    // se abre la mina
                    mainController.startMine(selectedVertex.getIsland());
                }else if(selectedVertex.getIsland().getName().equals("Templo")){
                    // se abre el templo
                }else if(selectedVertex.getIsland().getName().equals("Armeria")){
                    // se abre la armeria
                    mainController.startArmory(selectedVertex.getIsland());
                }else{
                    System.out.println("Isla no encontrada");
                }
                
            }
        }
        
        if(e.getSource().equals(view.getBtnConfig())){
            // se abre la configuracion
            mainController.showWindow(mainController.getConfigView());
            mainController.getConfigController().udpatePlayerGraph();
        }
        
        if(this.view.getEnemies().indexOf(e.getSource()) != -1){
            // se presiona un boton para cargar el oceano del enemigo
            JButton clickedButton =  (JButton) e.getSource();
            requestEnemySea(Integer.parseInt(clickedButton.getText()));
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
    
     @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource().getClass().equals(SeaCell.class)){
           SeaCell clickedLabel = (SeaCell) e.getSource();
           if(!clickedLabel.isEnemySea()){
               // se presiona una celda de mi mar
               if(clickedLabel.getVertex() != null){
                    // se hace click a una isla
                    String islandName  = clickedLabel.getVertex().getIsland().getName();
                    view.getLblSelectedVertex().setText(islandName); // setea el nombre
                    
                    if(islandName.equals("Mina") || islandName.equals("Templo") || islandName.equals("Armeria")){             
                        view.getBtnOpen().setEnabled(true);
                    }
                    
                    this.selectedVertex = clickedLabel.getVertex();

               }else{
                   // hace click a al mar sin nada
                   view.getLblSelectedVertex().setText("No seleccionado");
                   view.getBtnOpen().setEnabled(false);
               }
           }else{
               // se presiona una celda del enemigo
               if(this.weaponTargetAmount > 0){
                    // puede targetear
                    clickedLabel.setBorder(BorderFactory.createLineBorder(PALLETE[3], 1));
                    this.targets.add(new Target(clickedLabel.getI(), clickedLabel.getJ()));
                    this.weaponTargetAmount--;
                    if(this.weaponTargetAmount == 0){
                        this.view.getBtnAttack().setEnabled(true); 
                    }
               }
           }
         
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
    
    // metodos para el manejo del turno
    public void setPlayerTurn(boolean turn){
        this.playerTurn = turn;
        if(playerTurn){
            view.getLblPlayerTurn().setText("Tu turno");
        }else{
            view.getLblPlayerTurn().setText("Turno de Jugador x");
        }
    }

    // termina el turno del jugador actual y avisa al servidor que pase al siguiente
    public void nextPlayerTurn(){
        try {
            outputStream.writeInt(0); // opcion del helper server
            outputStream.writeInt(1); // subipcion para pasar el siguiente turno
            
        } catch(IOException ex) {
            Logger.getLogger(LobbyController.class.getName()).log(Level.SEVERE, null, ex);
        }
         
    }
    
    // setea los valores correspondientes para los items del inventario
    public void setPlayerInventory(){
        view.getBtnCannon().setText(game.getPlayerInventory().getItemAmount(Game.ItemType.CANNON) + "");
        view.getBtnMultipleCannon().setText(game.getPlayerInventory().getItemAmount(Game.ItemType.MULTIPLE_CANNON) + "");
        view.getBtnBomb().setText(game.getPlayerInventory().getItemAmount(Game.ItemType.BOMB) + "");
        view.getBtnRBCannon().setText(game.getPlayerInventory().getItemAmount(Game.ItemType.CANNON) + "");
        view.getBtnGhostShip().setText(game.getPlayerInventory().getItemAmount(Game.ItemType.GHOST_SHIP) + "");
        
        view.getLblSteel().setText(game.getPlayerInventory().getItemAmount(Game.ItemType.STEEL) + "");
        view.getLblMoney().setText(game.getBudget() + "");
        
    }
    
    // actualiza los cambios realizados desde la configuracion luego de haber comenzado el juego
    public void updateConfigSea(){
        this.game.setSea(view.getPnlSea(), view.getPlayerSea(), this.game.getGraph());
    }
    
    //Se muestra la ventana del jugador A
    public void showPlayerA(){
        try {
            outputStream.writeInt(3); // opcion del juego
            outputStream.writeInt(2); // subopcion de juego
            System.out.println("Se presiono el boton 1");
        } catch (IOException ex) {
            Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // --------------------------------------- METODOS PARA LA JUGABILIDAD --------------------------------------------------

    // Realiza el ataque preparado al enemigo seleccionado
    private void attackEnemySea(){
        System.out.println("Ataco bitch");
        
        // enviar el arraylist de los targets
 
        this.view.getBtnAttack().setEnabled(false);
        this.weaponTargetAmount = -1;
        this.view.getLblWeaponSelected().setText("Selecciona un arma");
        resetEnemySeaTargets();
    }
    
    private void resetEnemySeaTargets(){
        this.targets.removeAll(targets);
         for(int i = 0; i < SEA_SIZE; i++){
            for(int j = 0; j < SEA_SIZE; j++){
                view.getEnemySea()[i][j].setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
            }
        }
    }
    
    // procesa y recibe los ataques
    public void recieveAttack(){
        // validar el escudo
    }
    
    // pedir a mis enemigos para que se cargen sus botones respectivos en la pantalla
    private void requestEnemies(){
        try {
            outputStream.writeInt(0); // opcion del helper server
            outputStream.writeInt(2); // subipcion para pasar pedir a los enemigos
            
        } catch(IOException ex) {
            Logger.getLogger(LobbyController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void loadEnemiesButtons(ArrayList enemies){
        int x = 0, y = 0;
        
        for(int i = 0; i < enemies.size(); i++){
            
            JButton newEnemyButton = new JButton(enemies.get(i) + "");
            newEnemyButton.setBounds(x, y, this.view.getPnlEnemies().getWidth(), 45);
            newEnemyButton.addActionListener(this);
            this.view.getEnemies().add(newEnemyButton);
            this.view.getPnlEnemies().add(newEnemyButton);
            
            y += 50;
        }
    }
    
    
    public void requestEnemySea(int enemyId){
        try {
            outputStream.writeInt(3); // opcion del helper server
            outputStream.writeInt(2); // subipcion para pasar pedir la matriz y grafo del enemigo
            outputStream.writeInt(enemyId);
            
        } catch(IOException ex) {
            Logger.getLogger(LobbyController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void sendToEnemyMySea(int sendTo){
        try {
            outputStream.writeInt(3); // opcion del helper server
            outputStream.writeInt(3); // subipcion para pasar enviar la matriz y grafo del enemigo
            outputStream.writeInt(sendTo);
            
            objOutputStream.writeObject(this.view.getPlayerSea());
            objOutputStream.writeObject(this.game.getGraph());
            
        } catch(IOException ex) {
            Logger.getLogger(LobbyController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    // setea los datos del mar en la matriz de labels
    public void setEnemySea(SeaCell[][] enemySea, Graph enemyGraph){
        
        game.setEnemySea(view.getEnemySea(), enemySea, enemyGraph);
        
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
        int x1, x2, y1, y2 =0;
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
                x1=coordenadas.get(0);
                y1=coordenadas.get(1);
                x2=coordenadas.get(2);
                y2=coordenadas.get(3);
                Graphics2D g2 =(Graphics2D)view.getPnlSea().getGraphics();
                g2.setColor(Color.WHITE);
                g2.drawLine(x1, y1, x2, y2);
            }
        }
    }
    
    public void setComodin(String comodin){
        view.getLblComodin().setText(comodin);
    }
    // ----------------------------------------------- GETTERS AND SETTERS ------------------------------------------------------


    
    
}
