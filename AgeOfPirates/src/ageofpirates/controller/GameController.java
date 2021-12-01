
package ageofpirates.controller;

import static ageofpirates.controller.MainController.PALLETE;
import ageofpirates.model.Arista;
import ageofpirates.model.Game;
import ageofpirates.model.Game.ItemType;
import ageofpirates.model.Graph;
import ageofpirates.model.SeaCell;
import ageofpirates.model.SeaCellData;
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
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;


public class GameController extends Controller implements KeyListener, MouseListener{
    
    private GameWindow view;
    private boolean playerTurn;
    private Vertex selectedVertex;
    private int weaponTargetAmount; // la cantidad de objetivos targetables por el arma seleccionada (celdas en el mar enemigo)
    private ItemType weaponType;
    private ArrayList<Target> targets;
    private int enemyIdSelected;
    private int enemyGraphSize;
    // cuando termina de disparar colocarlo en -1
    private int shield; // comodin del escudo
    
    private ObjectOutputStream objOutputStream = null;

    public GameController(GameWindow view, Game game, MainController mainController) {
        super(game, mainController);
        this.view = view;
        setPlayerTurn(1); // siempre inicia el jugador 1
        this.selectedVertex = null;
        this.weaponTargetAmount = -1; // estado en el que esta disponble seleccionar una nueva arma
        this.targets = new ArrayList<>();
        this.enemyIdSelected = -1;
        this.shield = 0;
        
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
    }
    
    // establece el grafo en la matriz y ademas inicia los thread de las minas que no han iniciado
    public void setInitialPlayerSea(){
        game.setSea(view.getPnlSea(), view.getPlayerSea(), game.getGraph()); // seteo inicial del mar del jugador actual
        game.startTemple(this);
        game.startMining(view.getLblSteel(), this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(e.getSource().equals(this.view.getBtnSendMessage())){
            if(!this.view.getTxtfMessage().getText().equals("")){
                sendMessage();
            }
        }
        
        if(e.getSource().equals(view.getBtnCannon())){
            
            if(game.getPlayerInventory().getItemAmount(ItemType.CANNON) > 0 && weaponTargetAmount == -1 && playerTurn){
                game.getPlayerInventory().updateItemAmount(ItemType.CANNON, -1);
                setPlayerInventory(); // se actualiza todo el inventario
                
                this.weaponTargetAmount = 1; // cantidad de targets 
                this.weaponType = ItemType.CANNON;
                this.view.getLblWeaponSelected().setText("Cañon");
            }
        }
        if(e.getSource().equals(view.getBtnMultipleCannon())){
            if(game.getPlayerInventory().getItemAmount(ItemType.MULTIPLE_CANNON) > 0 && weaponTargetAmount == -1 && playerTurn){
                game.getPlayerInventory().updateItemAmount(ItemType.MULTIPLE_CANNON, -1);
                setPlayerInventory(); // se actualiza todo el inventario
                
                this.weaponTargetAmount = 1; // cantidad de targets 
                this.weaponType = ItemType.MULTIPLE_CANNON;
                this.view.getLblWeaponSelected().setText("Cañon Multiple");
            }
        }
        if(e.getSource().equals(view.getBtnBomb())){
            if(game.getPlayerInventory().getItemAmount(ItemType.CANNON) > 0 && weaponTargetAmount == -1 && playerTurn){
                game.getPlayerInventory().updateItemAmount(ItemType.CANNON, -1);
                setPlayerInventory(); // se actualiza todo el inventario
                
                this.weaponTargetAmount = 3; // cantidad de targets 
                this.weaponType = ItemType.BOMB;
                this.view.getLblWeaponSelected().setText("Bomba");
            }
        }
        if(e.getSource().equals(view.getBtnRBCannon())){
            if(game.getPlayerInventory().getItemAmount(Game.ItemType.CANNON) > 0 && weaponTargetAmount == -1 && playerTurn){
                game.getPlayerInventory().updateItemAmount(Game.ItemType.CANNON, -1);
                setPlayerInventory(); // se actualiza todo el inventario
                
                this.weaponTargetAmount = 10; // cantidad de targets 
                this.weaponType = ItemType.RED_BEARD_CANNON;
                this.view.getLblWeaponSelected().setText("Cañon Barba Roja");
            }
        }
        if(e.getSource().equals(view.getBtnGhostShip())){
            if(game.getPlayerInventory().getItemAmount(Game.ItemType.CANNON) > 0 && weaponTargetAmount == -1 && playerTurn){
                game.getPlayerInventory().updateItemAmount(Game.ItemType.CANNON, -1);
                setPlayerInventory(); // se actualiza todo el inventario
                
                this.weaponTargetAmount = 1; // cantidad de targets 
                this.view.getLblWeaponSelected().setText("Barco Fantasma");
            }
        }
        // se utiliza uno de los comodines
        if(e.getSource().equals(view.getBtnComodin())){
            if(view.getLblComodin().getText().equals("Kraken")){
                sendPlayerKraken();
                view.getLblComodin().setText("Comodin");
            }
            
            if(view.getLblComodin().getText().equals("Escudo")){
                setPlayerShield();
                view.getLblComodin().setText("Comodin");
            }
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
    
    // metodos para el manejo del turno (pasar el id)
    public void setPlayerTurn(int playerInTurnId){
        this.playerTurn = playerInTurnId == game.getPlayer().getPlayerId();
        if(playerTurn){
            view.getLblPlayerTurn().setText("Tu turno");
        }else{
            view.getLblPlayerTurn().setText("Turno de Jugador " + playerInTurnId);
        }
    }

    // termina el turno del jugador actual y avisa al servidor que pase al siguiente
    public void nextPlayerTurn(){
        try {
            outputStream.writeInt(0); // opcion del helper server
            outputStream.writeInt(1); // subipcion para pasar el siguiente turno
            
            System.out.println("Se actualiza turno");
            
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
    
    // --------------------------------------- METODOS PARA LA JUGABILIDAD --------------------------------------------------

    // Realiza el ataque preparado al enemigo seleccionado
    private void attackEnemySea(){
        if(enemyIdSelected != -1){
            
            // enviar el arraylist de los targets
            try {
                outputStream.writeInt(3); // opcion del juego
                outputStream.writeInt(4); // opcion de atacar a un enemigo
                outputStream.writeInt(enemyIdSelected);
                objOutputStream.writeObject(this.weaponType);
                //objOutputStream.writeObject(this.targets); // los objetivos marcados
                objOutputStream.writeUnshared(targets);
                
                for(int i = 0; i < targets.size(); i++){
                    System.out.println("Target sent " + targets.get(i).getI() +" : "+ targets.get(i).getJ());
                }
                
            } catch(IOException ex) {
                Logger.getLogger(LobbyController.class.getName()).log(Level.SEVERE, null, ex);
            }

            this.view.getBtnAttack().setEnabled(false);
            this.weaponTargetAmount = -1;
            this.view.getLblWeaponSelected().setText("Selecciona un arma");
            resetEnemySeaTargets();
            nextPlayerTurn(); // pasa al siguiente turno
        }
 
    }
    
    private void resetEnemySeaTargets(){
        this.targets.removeAll(this.targets);
        for(int i = 0; i < SEA_SIZE; i++){
           for(int j = 0; j < SEA_SIZE; j++){
               view.getEnemySea()[i][j].setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
            }
        }
    }
    
    private void sendPlayerKraken(){
        int indexIsland = new Random().nextInt(enemyGraphSize);
        // el index de la isla a destruir del enemigo
        try {
            outputStream.writeInt(3); // opcion del helper server
            outputStream.writeInt(6); // subipcion para pasar el siguiente turno
            
            outputStream.writeInt(enemyIdSelected);
            outputStream.writeInt(indexIsland);
            
            this.view.getBtnAttack().setEnabled(false);
            this.weaponTargetAmount = -1;
            this.view.getLblWeaponSelected().setText("Selecciona un arma");
            resetEnemySeaTargets();
            nextPlayerTurn(); // pasa al siguiente turno
            
            
        } catch(IOException ex) {
            Logger.getLogger(LobbyController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void receiveKraken(int islandIndex, int attackerId){
        Vertex vertex = this.game.getGraph().get(islandIndex);
        game.krakenDestroyIsland(this.view.getPlayerSea(), vertex);

        String binnacle = "RECIBIDO: en " + vertex.getIsland().getiPos()+" : " + vertex.getIsland().getjPos() + " de Kraken ha destruido " + vertex.getIsland().getName();
        String enemyBinnacle = "ATAQUE: en " + vertex.getIsland().getiPos()+" : " + vertex.getIsland().getjPos() + " de Kraken ha destruido " + vertex.getIsland().getName();
    
        writeBinnacles(binnacle, enemyBinnacle, attackerId);
    }
    
    // coloca el comodin del escudo
    private void setPlayerShield(){
        this.shield += new Random().nextInt(5 - 2) + 2;
    }
    // procesa y recibe los ataques
    public void recieveAttack(ArrayList<Target> targetsReceived, ItemType weapon, int enemyId){

        String binnacle = "", enemyBinnacle = "", str = "";

        for(int i = 0; i < targetsReceived.size(); i++){
            
            binnacle += "RECIBIDO: en " + targetsReceived.get(i).getI()+" : " + targetsReceived.get(i).getJ() + " ";
            enemyBinnacle += "ATAQUE: en " + targetsReceived.get(i).getI()+" : " + targetsReceived.get(i).getJ() + " ";
            
            if(this.shield > 0){
                binnacle += " detenido por escudo";
                enemyBinnacle += " detenido por escudo";
                this.shield--;
                continue;
            }
            
            switch (weapon) {
                case CANNON:
                    if(view.getPlayerSea()[targetsReceived.get(i).getI()][targetsReceived.get(i).getJ()].getVertex() != null){
                        if(!view.getPlayerSea()[targetsReceived.get(i).getI()][targetsReceived.get(i).getJ()].getVertex().getIsland().getName().equals("Remolino")){
                            game.setDestroyedIsland(view.getPlayerSea(),targetsReceived.get(i).getI(), targetsReceived.get(i).getJ());
                            // binacora
                            str = "de cañon exploto parte de " + view.getPlayerSea()[targetsReceived.get(i).getI()][targetsReceived.get(i).getJ()].getVertex().getIsland().getName();
                            binnacle += str;
                            enemyBinnacle += str;
                        }else{
                            // le pega a un remolino
                            swirlTarget(targetsReceived.get(i).getI(), targetsReceived.get(i).getJ(), enemyId);
                        }
                        

                    }else{
                        // perdio el tiro
                        binnacle += "de cañon fallido ";
                        
                        enemyBinnacle += "de cañon fallido ";
                    }
                    break;
                case MULTIPLE_CANNON:
                    if(view.getPlayerSea()[targetsReceived.get(i).getI()][targetsReceived.get(i).getJ()].getVertex() != null){
                        if(!view.getPlayerSea()[targetsReceived.get(i).getI()][targetsReceived.get(i).getJ()].getVertex().getIsland().getName().equals("Remolino")){
                            // le atina a algo
                            game.setDestroyedIsland(view.getPlayerSea(),targetsReceived.get(i).getI(), targetsReceived.get(i).getJ());
                            // binacora
                            str = "de cañon multiple exploto parte de " + view.getPlayerSea()[targetsReceived.get(i).getI()][targetsReceived.get(i).getJ()].getVertex().getIsland().getName();
                            binnacle += str;
                            enemyBinnacle += str;

                            ArrayList subBinnacles = torpedoesAttack();
                            binnacle += subBinnacles.get(0);
                            enemyBinnacle += subBinnacles.get(1);            
                        }else{
                            swirlTarget(targetsReceived.get(i).getI(), targetsReceived.get(i).getJ(), enemyId);
                        }
  
                    }else{
                        // perdio el tiro
                        binnacle += "de cañon multiple fallido ";
                        enemyBinnacle += "de cañon multiple fallido ";
                    }    
                    break;
                case BOMB:
                    
                    ArrayList binnaclesExplosion = explodeBomb(targetsReceived.get(i).getI(), targetsReceived.get(i).getJ(), enemyId);
                    binnacle += binnaclesExplosion.get(0);
                    enemyBinnacle += binnaclesExplosion.get(1);
                    
                    break;
                case RED_BEARD_CANNON:
                    if(view.getPlayerSea()[targetsReceived.get(i).getI()][targetsReceived.get(i).getJ()].getVertex() != null){
                        if(!view.getPlayerSea()[targetsReceived.get(i).getI()][targetsReceived.get(i).getJ()].getVertex().getIsland().getName().equals("Remolino")){
                            // le atina a algo
                            game.setDestroyedIsland(view.getPlayerSea(),targetsReceived.get(i).getI(), targetsReceived.get(i).getJ());
                            // binacora
                            str = "de cañon barba roja exploto parte de " + view.getPlayerSea()[targetsReceived.get(i).getI()][targetsReceived.get(i).getJ()].getVertex().getIsland().getName();
                            binnacle += str;
                            enemyBinnacle += str;
                        }else{
                            swirlTarget(targetsReceived.get(i).getI(), targetsReceived.get(i).getJ(), enemyId);
                        }
    
                    }else{
                        // perdio el tiro
                        binnacle += "de cañon barba roja fallido ";
                        
                        enemyBinnacle += "de cañon barba roja fallido ";
                    }
                    break;
                default:
                    System.out.println("el arma no existe");
                    break;
            }
            
            binnacle += "\n";
            enemyBinnacle += "\n";
        } // fin del for
        
        writeBinnacles(binnacle, enemyBinnacle, enemyId);
        
    }
    
    // generacion de los 4 torpedos aleatorios despues de acertar un canon multiple
    private ArrayList torpedoesAttack(){
        ArrayList binnacles = new ArrayList<>();
        String binnacle = "\n", enemyBinnacle = "\n";
        String str;
        
        for(int i = 0; i < 4; i++){
            int targetI = new Random().nextInt(SEA_SIZE);
            int targetJ =  new Random().nextInt(SEA_SIZE);
            
            binnacle += "RECIBIDO: en " + targetI +" : " + targetJ + " ";
            enemyBinnacle += "ATAQUE: en " + targetI +" : " + targetJ + " ";
            
            if(view.getPlayerSea()[targetI][targetJ].getVertex() != null){
                
                game.setDestroyedIsland(view.getPlayerSea(),targetI, targetJ);
                
                str = "de torpedo exploto parte de " + view.getPlayerSea()[targetI][targetJ].getVertex().getIsland().getName();
                binnacle += str;
                enemyBinnacle += str;
            }else{
                binnacle += "de torpedo fallido ";
            }
                    
            binnacle += "\n";
            enemyBinnacle += "\n";
            
        }

        
        binnacles.add(binnacle);
        binnacles.add(enemyBinnacle);
        
        return binnacles;
    }
    
    
    private ArrayList explodeBomb(int i, int j, int enemyId){
        
        ArrayList binnacles = new ArrayList<>();
        String binnacle = "\n", enemyBinnacle = "\n";
        String str;
        
        binnacle += "RECIBIDO: en " + i+" : " + j + " ";
        enemyBinnacle += "ATAQUE: en " + i+" : " + j + " ";
            
        
        if(view.getPlayerSea()[i][j].getVertex() != null){
            if(!view.getPlayerSea()[i][j].getVertex().getIsland().getName().equals("Remolino")){
                // le atina a algo
                game.setDestroyedIsland(view.getPlayerSea(),i, j);
                // bitacora
                str = "de bomba exploto parte de " + view.getPlayerSea()[i][j].getVertex().getIsland().getName();
                binnacle += str;
                enemyBinnacle += str;
            
            }else{
                swirlTarget(i, j, enemyId);
            }

            
        }else{
            // perdio el tiro
            binnacle += "de bomba fallido ";

            enemyBinnacle += "de bomba fallido ";
        }
        
        if(new Random().nextInt(2) == 1){
            // explota de forma horizontal
            if(j + 1 < SEA_SIZE){
                if(view.getPlayerSea()[i][j + 1].getVertex() != null){
                    if(!view.getPlayerSea()[i][j].getVertex().getIsland().getName().equals("Remolino")){
                        game.setDestroyedIsland(view.getPlayerSea(),i, j);
                        binnacle += "y en "+ i+" : " + (j + 1) + " exploto parte de " + view.getPlayerSea()[i][j + 1].getVertex().getIsland().getName();
                        enemyBinnacle += "y en " + i+" : " + (j + 1) + " exploto parte de " + view.getPlayerSea()[i][j + 1].getVertex().getIsland().getName();
                    }else{
                        swirlTarget(i, j, enemyId);
                    }

                }else{
                    binnacle += "y en "+ i+" : " + (j + 1) + " fallido";
                    enemyBinnacle += "y en "+ i+" : " + (j + 1) + " fallido ";
                }

            }else{
                if(view.getPlayerSea()[i][j - 1].getVertex() != null){
                    if(!view.getPlayerSea()[i][j].getVertex().getIsland().getName().equals("Remolino")){
                        game.setDestroyedIsland(view.getPlayerSea(),i, j);
                        binnacle += "y en "+ i+" : " + (j - 1) + " exploto parte de " + view.getPlayerSea()[i][j - 1].getVertex().getIsland().getName();
                        enemyBinnacle += "y en " + i+" : " + (j - 1) + " exploto parte de " + view.getPlayerSea()[i][j - 1].getVertex().getIsland().getName();
                    }else{
                        swirlTarget(i, j, enemyId);
                    }
   
                }else{
                    binnacle += "y en "+ i+" : " + (j - 1) + " fallido";
                    enemyBinnacle += "y en "+ i+" : " + (j - 1) + " fallido ";
                }
            }
        }else{
            // explota de forma vertical
            if(i - 1 >= 0){
                if(view.getPlayerSea()[i - 1][j].getVertex() != null){
                    if(!view.getPlayerSea()[i][j].getVertex().getIsland().getName().equals("Remolino")){
                        game.setDestroyedIsland(view.getPlayerSea(),i, j);
                    
                        binnacle += "y en "+ (i - 1) +" : " + j + " exploto parte de " + view.getPlayerSea()[i - 1][j].getVertex().getIsland().getName();
                        enemyBinnacle += "y en " + (i - 1) +" : " + j + " exploto parte de " + view.getPlayerSea()[i - 1][j].getVertex().getIsland().getName();
                    }else{
                        swirlTarget(i, j, enemyId);
                    }

                }else{
                    binnacle += "y en "+ (i - 1) +" : " + j + " fallido";

                    enemyBinnacle += "y en "+ (i - 1) +" : " + j + " fallido ";
                }
            }else{
                if(view.getPlayerSea()[i + 1][j].getVertex() != null){
                    if(!view.getPlayerSea()[i][j].getVertex().getIsland().getName().equals("Remolino")){
                        game.setDestroyedIsland(view.getPlayerSea(),i, j);
                        binnacle += "y en "+ (i + 1) +" : " + j + " exploto parte de " + view.getPlayerSea()[i + 1][j].getVertex().getIsland().getName();
                        enemyBinnacle += "y en " + (i + 1) +" : " + j + " exploto parte de " + view.getPlayerSea()[i + 1][j].getVertex().getIsland().getName();
                    }else{
                        swirlTarget(i, j, enemyId);
                    }

                }else{
                    binnacle += "y en "+ (i + 1) +" : " + j + " fallido";

                    enemyBinnacle += "y en "+ (i + 1) +" : " + j + " fallido ";
                }
            }
        }
        
        binnacle += "\n";
        enemyBinnacle += "\n";
        
        binnacles.add(binnacle);
        binnacles.add(enemyBinnacle);
        return binnacles;
        
    }
    
    // le han acertado a un remolino
    private ArrayList swirlTarget(int iPos, int jPos, int enemyId){
        ArrayList binnacles = new ArrayList<>();
        String binnacle = "\n", enemyBinnacle = "\n";
        
        binnacle += " en remolino";
        enemyBinnacle += "en remolino";
        
        for(int i = 0; i < 3; i++){
            // generan 3 targets aleatorios para el rival
            targets.add(new Target(new Random().nextInt(SEA_SIZE), new Random().nextInt(SEA_SIZE)));
        }
        
        int lastEnemy = this.enemyIdSelected;
        ItemType lastWeaponType = this.weaponType;
        this.enemyIdSelected = enemyId;
        this.weaponType = ItemType.CANNON;
        
        // enviar el ataque
        attackEnemySea();
        
        this.enemyIdSelected = lastEnemy; // devuelve el que estaba
        this.weaponType = lastWeaponType;
        
        binnacles.add(binnacle);
        binnacles.add(enemyBinnacle);
        return binnacles;
    }
    
    // escribe en la bitacora de mi pantalla y en la del enemigo
    private void writeBinnacles(String binnacle, String enemyBinnacle, int enemyId){
        this.view.getTxtaBinnacle().setText(this.view.getTxtaBinnacle().getText() + binnacle);
        
        // enviar al enemigo la suyas
        try {
            outputStream.writeInt(3); // opcion del juego
            outputStream.writeInt(5);
            outputStream.writeInt(enemyId);
            outputStream.writeUTF(enemyBinnacle); // subipcion para pasar el siguiente turno
            
        } catch(IOException ex) {
            Logger.getLogger(LobbyController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    // recibe la bitacora del atacado para imprimirla en pantalla
    public void recieveBinnacle(String binnacle){
        this.view.getTxtaBinnacle().setText(this.view.getTxtaBinnacle().getText() + binnacle);
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
        this.view.getLblEnemy().setText("Cargando...");
        this.enemyIdSelected = enemyId;
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
            
            outputStream.writeInt(game.getPlayer().getPlayerId());
            outputStream.writeBoolean(shield > 0);
            SeaCell[][] mySea = view.getPlayerSea();
            
            for(int i = 0; i < SEA_SIZE; i++){
                for(int j = 0; j < SEA_SIZE; j++){
                    SeaCellData seaCellData = new SeaCellData(i,j, mySea[i][j].isEnemySea(), mySea[i][j].getVertex(), mySea[i][j].isDestroyed());
                    objOutputStream.writeObject(seaCellData);
                }
            }
            objOutputStream.writeObject(this.game.getGraph());
            
        } catch(IOException ex) {
            Logger.getLogger(LobbyController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    // setea los datos del mar en la matriz de labels
    public void setEnemySea(int enemyId, boolean enemyShiled, SeaCellData[][] enemySeaData, Graph enemyGraph){
        String str = "Jugador " + enemyId;
        str += enemyShiled ? " (Escudo)" : " ";
        this.view.getLblEnemy().setText(str);
        this.enemyGraphSize = enemyGraph.size(); // establecer el tamano del grafo que tiene el enemigo seleccionado
        
        SeaCell[][] enemySea = rebuildEnemySea(enemySeaData);
        
        game.setEnemySea(view.getEnemySea(), enemySea, enemyGraph);
        
    }
    
    private SeaCell[][] rebuildEnemySea(SeaCellData[][] enemySeaData){
        SeaCell[][] enemySea = new SeaCell[SEA_SIZE][SEA_SIZE];
        
        for(int iSea = 0; iSea < SEA_SIZE; iSea++){
            for(int jSea = 0; jSea < SEA_SIZE; jSea++){
                enemySea[iSea][jSea] = new SeaCell(iSea,jSea, enemySeaData[iSea][jSea].isEnemySea());
                enemySea[iSea][jSea].setVertex(enemySeaData[iSea][jSea].getVertex());
                enemySea[iSea][jSea].setDestroyed(enemySeaData[iSea][jSea].isDestroyed());
                
            }
        }
        
        return enemySea;
    }
    
    private ArrayList<ImageIcon> getIslandIcons(String islandName){
        if(islandName.equals("Armeria")){
             return game.getArmoryIcons();
        }else if(islandName.equals("Conector")){
            return game.getConnectorIcons();
        } else if(islandName.equals("Mercado")){
             return game.getMarketIcons();
        } else if(islandName.equals("Mina")){
             return game.getMineIcons();
        } else if(islandName.equals("Fuente de Energia")){
             return game.getSourcePowerIcons();
        } else if(islandName.equals("Remolino")){
             return game.getSwirlIcons();
        }else{
            // Templo
            return game.getTempleIcons();
        }
    
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
