
package ageofpirates.model;

import ageofpirates.controller.GameController;
import ageofpirates.controller.MainController;
import static ageofpirates.view.ConfigWindow.CELL_SIZE;
import static ageofpirates.view.ConfigWindow.SEA_SIZE;
import java.awt.Color;
import java.awt.Graphics2D;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class Game {

    private Player player;
    private MainController mainController;
    // private Grafo<Component> components (estructuras del jugador)
    private Graph graph; // grafo de los componentes del mar
    
    private ArrayList<ImageIcon> sourcePowerIcons;
    private ArrayList<ImageIcon> connectorIcons;
    private ArrayList<ImageIcon> marketIcons;
    private ArrayList<ImageIcon> mineIcons;
    private ArrayList<ImageIcon> templeIcons;
    private ArrayList<ImageIcon> armoryIcons;
    private ArrayList<ImageIcon> swirlIcons;
    private ArrayList<ImageIcon> destroyedIcons;

    // recursos del jugador
    private int budget;
    private Inventory playerInventory;
    
    private Inventory marketInventory; // el inventario del mercado
    
    // enumerable para los objetos del inventario
    public enum ItemType{
        CANNON(1000, "Cañon"),
        MULTIPLE_CANNON(2000, "Cañon Multiple"),
        BOMB(4000, "Bomba"),
        RED_BEARD_CANNON(10000, "Cañon Barba Roja"),
        GHOST_SHIP(2500, "Barco Fantasma"),
        STEEL(2, "Acero");
        
        private int cost;
        private String name;
        
        private ItemType(int cost, String name){
            this.cost = cost;
            this.name = name;
        }
        
        public int getCost(){
            return this.cost;
        }
        
        public String getName(){
            return this.name;
        }
    }

    public Game(MainController mainController) {
        this.mainController = mainController;
        this.player = new Player(this.mainController); // se le pasa el controlador principal
        this.graph = new Graph();
        this.playerInventory = new Inventory();
        this.marketInventory = new Inventory();
        
        this.playerInventory.updateItemAmount(ItemType.BOMB, 5);
        this.playerInventory.updateItemAmount(ItemType.CANNON, 10);
        this.playerInventory.updateItemAmount(ItemType.STEEL, 1000);
        
        this.budget = 4000; // se inicia con 4000 (dolares)
        
        loadIcons();
    }
    
    // ------------------------------------------------- METODOS ------------------------------------------------------------------------
    // carga los iconos para las islas
    private void loadIcons(){
        sourcePowerIcons = new ArrayList<>();
        connectorIcons = new ArrayList<>();
        marketIcons = new ArrayList<>();
        mineIcons = new ArrayList<>();
        templeIcons = new ArrayList<>();
        armoryIcons = new ArrayList<>();
        swirlIcons = new ArrayList<>();
        destroyedIcons = new ArrayList<>();
        
        File folder = new File("./src/media");
        ImageIcon cardIcon;
        for(File subFolder: folder.listFiles()){
            if(subFolder.isDirectory() && !subFolder.getName().replaceFirst("[.][^.]+$", "").equals("backupImg")){
                for(File file: subFolder.listFiles()){
                    try{
                        ImageIcon icon = new ImageIcon(file.getCanonicalPath());
                        if(subFolder.getName().replaceFirst("[.][^.]+$", "").equals("sourcePower")){
                            sourcePowerIcons.add(icon);
                        }else if(subFolder.getName().replaceFirst("[.][^.]+$", "").equals("connector")){
                            connectorIcons.add(icon);
                        }else if(subFolder.getName().replaceFirst("[.][^.]+$", "").equals("market")){
                            marketIcons.add(icon);
                        }else if(subFolder.getName().replaceFirst("[.][^.]+$", "").equals("mine")){
                            mineIcons.add(icon);
                        }else if(subFolder.getName().replaceFirst("[.][^.]+$", "").equals("temple")){
                            templeIcons.add(icon);
                        }else if(subFolder.getName().replaceFirst("[.][^.]+$", "").equals("armory")){
                            armoryIcons.add(icon);
                        }else if(subFolder.getName().replaceFirst("[.][^.]+$", "").equals("swirl")){
                            swirlIcons.add(icon);
                        }else if(subFolder.getName().replaceFirst("[.][^.]+$", "").equals("destroyed")){
                            destroyedIcons.add(icon);
                        }
                        
                    }catch(IOException e){
                        System.out.println("Error al cargar iconos");
                    }
                }
                
 
            }
        }
    }
    
    // crea un mercado, funete de poder y un connector y los setea en la matriz dada
    public void initGameGraph(){
        PowerSource power = new PowerSource(-1,-1, sourcePowerIcons);
        Market market = new Market(-1, -1, marketIcons);
        createVertex(power);
        createVertex(market);
    }
    
    public void setSea(JPanel seaPanel, SeaCell[][] playerSea, Graph graph){
        unSetSea(playerSea); // se resetea el mar anterior
        for(int i = 0; i < graph.size(); i++){
            setIsland(playerSea, graph.get(i));
            for(int j = 0; j < graph.get(i).getAristas().size(); j++){
                setArista(seaPanel, graph.get(i).getAristas().get(j));
            }
        }
    }
    
    // elimina todas las islas del oceano
    public void unSetSea(SeaCell[][] playerSea){
        for(int i = 0; i < SEA_SIZE ;i++){
            for(int j = 0; j < SEA_SIZE; j++){
                playerSea[i][j].setVertex(null);
                playerSea[i][j].setIcon(null);
            }
        }
    }
    
    public Vertex createVertex(Island newIsland){
        Vertex newVertex = new Vertex(newIsland);
        this.graph.add(newVertex);
        return newVertex;
    }
    
    public Arista createArista(Vertex origin, Vertex destiny){
        Arista newArista = new Arista(origin, destiny);
        return newArista;
    }
    public Arista createArista(Vertex origin, Vertex destiny, int x1, int y1, int x2, int y2){
        Arista newArista = new Arista(origin, destiny, x1, y1, x2, y2);
        return newArista;
    }
    // sea una issla de forma aleatoria en el oceano dado
    public void setIslandRandomPosition(SeaCell[][]  playerSea, Vertex vertex){
        int iSea = 0, jSea = 0;
        Island island = vertex.getIsland();
        boolean posRight = false;
        while(!posRight){
            // crea randoms y verifica que la posicion sea correcta
            iSea = new Random().nextInt(SEA_SIZE);
            jSea = new Random().nextInt(SEA_SIZE); // random entre 0 y 19
            if(validIslandPosition(playerSea, iSea, jSea, island.getxDimension(), island.getyDimension())){
                posRight = true;
                island.setiPos(iSea);
                island.setjPos(jSea);
            }
        }
        
        setIsland(playerSea, vertex);
    }
    
    // setea la isla en la ubicacion correspondiente
    public void setIsland(SeaCell[][] playerSea, Vertex vertex){
        try{
            Island island = vertex.getIsland();
            int iPos = island.getiPos(), jPos = island.getjPos();
            int iconCounter = 0; // posicion para recuperar la imagen
            for(int yDimension = 0; yDimension < island.getyDimension(); yDimension++){
                for(int xDimension = 0; xDimension < island.getxDimension(); xDimension++){
                    playerSea[iPos][jPos].setIcon(MainController.resizeIcon(island.getIcons().get(iconCounter), CELL_SIZE, CELL_SIZE));
                    playerSea[iPos][jPos].setVertex(vertex);
                    jPos++;
                    iconCounter++;
                }
                jPos = island.getjPos();
                iPos++;
            }


        }catch(ArrayIndexOutOfBoundsException e){
            System.out.println("Set component invalid index");
        }
        
    }
    
    public void setDestroyedIsland(SeaCell[][] sea, Vertex vertex){
        try{
            Island island = vertex.getIsland();
            int iPos = island.getiPos(), jPos = island.getjPos();
            int iconCounter = 0; // posicion para recuperar la imagen
            for(int yDimension = 0; yDimension < island.getyDimension(); yDimension++){
                for(int xDimension = 0; xDimension < island.getxDimension(); xDimension++){
                    sea[iPos][jPos].setIcon(MainController.resizeIcon(destroyedIcons.get(0), CELL_SIZE, CELL_SIZE));
                    sea[iPos][jPos].setVertex(vertex);
                    jPos++;
                    iconCounter++;
                }
                jPos = island.getjPos();
                iPos++;
            }


        }catch(ArrayIndexOutOfBoundsException e){
            System.out.println("Set component invalid index");
        }
    }
    
    public void setArista(JPanel seaPanel, Arista arista){
        Graphics2D g2 =(Graphics2D) seaPanel.getGraphics();
        g2.setColor(Color.WHITE);
        g2.drawLine(arista.getCoord().get(0), arista.getCoord().get(1), arista.getCoord().get(2), arista.getCoord().get(3));
        System.out.println("Se setea arista en :" + arista.getCoord().get(0) +":"+ arista.getCoord().get(1) +":"+ arista.getCoord().get(2) +":"+ arista.getCoord().get(3));
    }
    
    // quita el elemento grafico actual
    private void unSetIsland(SeaCell[][] playerSea, Vertex vertex){
        try{
            Island island = vertex.getIsland();
            int iPos = island.getiPos(), jPos = island.getjPos();
            for(int yDimension = 0; yDimension < island.getyDimension(); yDimension++){
                for(int xDimension = 0; xDimension < island.getxDimension(); xDimension++){
                    playerSea[iPos][jPos].setIcon(null);
                    playerSea[iPos][jPos].setVertex(null);
                    jPos++;
                }
                jPos = island.getjPos();
                iPos++;
            }


        }catch(ArrayIndexOutOfBoundsException e){
            System.out.println("Set component invalid index");
        }
    }
    
    // valida que el componente de dimensiones x y y se pueda colocar en la posicion i, j
    public boolean validIslandPosition(SeaCell[][] playerSea, int i, int j, int xDimension, int yDimension){
        int iPos = i, jPos = j;
        try{
            
            for(int dimensionRow = 0; dimensionRow < yDimension; dimensionRow++){
                for(int dimensionCol = 0; dimensionCol < xDimension; dimensionCol++){
                    if(playerSea[iPos][jPos].getVertex() != null) return false;

                    jPos++;
                }
                jPos = i;
                iPos++;
            }
            return true;
        }catch(ArrayIndexOutOfBoundsException e){
            System.out.println("Error position " + iPos +":"+ jPos);
            return false;
        }

    }
    
    // dibuja una linea entre las islas dadas
    public void setArista(Vertex location, Vertex destiny){
    //uno de los 2 debe ser connector
    //        if(location.getComponent().getName() == "connector" || destiny.getComponent().getName() == "connector"){
    //            location.createArista(destiny);
    //        }else{
    //            System.out.println("Creacion de arista invalida");
    //        }
    }
    
    
    
    // mueve el componente hacia la direccion especificada
    public void moveLeftIsland(SeaCell[][] playerSea, Vertex vertex){
        unSetIsland(playerSea, vertex);
        Island island = vertex.getIsland();
        if(validIslandPosition(playerSea, island.getiPos(), island.getjPos() - 1, island.getxDimension(), island.getyDimension())){
            island.setjPos(island.getjPos() - 1);
        }
        
        setIsland(playerSea, vertex);
    }
    
    public void moveRightIsland(SeaCell[][] playerSea, Vertex vertex){
        unSetIsland(playerSea, vertex);
        Island island = vertex.getIsland();
        if(validIslandPosition(playerSea, island.getiPos(), island.getjPos() + 1, island.getxDimension(), island.getyDimension())){
            island.setjPos(island.getjPos() + 1);
        }
        
        setIsland(playerSea, vertex);
    }
    
    public void moveUpIsland(SeaCell[][] playerSea, Vertex vertex){
        unSetIsland(playerSea, vertex);
        Island island = vertex.getIsland();
        if(validIslandPosition(playerSea, island.getiPos() - 1, island.getiPos(), island.getxDimension(), island.getyDimension())){
            island.setiPos(island.getiPos() - 1);
        }
        
        setIsland(playerSea, vertex);
    }
    
    public void moveDownIsland(SeaCell[][] playerSea, Vertex vertex){
        unSetIsland(playerSea, vertex);
        Island island = vertex.getIsland();
        if(validIslandPosition(playerSea, island.getiPos() + 1, island.getjPos(), island.getxDimension(), island.getyDimension())){
            island.setiPos(island.getiPos() + 1);
        }

            setIsland(playerSea, vertex);
    }
    
    
    // metodo para el inicio de las minas para que comiencen a minar
    public void startMining(JLabel steelLabel, GameController gameController){
        for(int i = 0; i < graph.size(); i++){
            if(graph.get(i).getIsland().getName().equals("Mina")){
                // es una mina
                Mine mine = (Mine) graph.get(i).getIsland();
                if(mine.getMineThread() == null){
                    mine.startMining(this, gameController);
                }
            }
        }
    }
   
    // metodo para el inicio de los templos
    public void startTemple(GameController gameController){
        for(int i = 0; i < graph.size(); i++){
            if(graph.get(i).getIsland().getName().equals("Templo")){
                Temple temple = (Temple) graph.get(i).getIsland();
                if(temple.getTempleThread() == null){
                    temple.startTemple(this, gameController);
                }
            }
        }
    }
    
    // valida si el vertice dado tiene conexion con la fuente de poder
    public boolean isConnectedToPower(Vertex vertex){
        if(vertex.getIsland().getName().equals("Fuente de Energia")) return true;
        
        for(int i = 0; i < vertex.getAristas().size(); i++){
            if(vertex.getAristas().get(i).getDestiny().getIsland().getName().equals("Fuente de Energia")) return true;
            
            for(int j = 0; j < vertex.getAristas().get(i).getDestiny().getAristas().size(); j++){
                if(vertex.getAristas().get(i).getDestiny().getAristas().get(j).getDestiny().equals("Fuente de Energia")) return true;
            }
        }
        
        return false;
    }
    
    // setea oceano del enemigo
    public void setEnemySea(SeaCell[][] enemiesSea, SeaCell[][] enemySea, Graph enemyGraph){
        
        // se recorre la matriz de celdas del enemigo para ver cual esta destruida y seterla
        for(int i = 0; i < SEA_SIZE; i++){
            for(int j = 0; j < SEA_SIZE; j++){
                if(enemySea[i][j].isDestroyed()){
                    enemiesSea[i][j].setIcon(destroyedIcons.get(0));
                }
            }
        }
        
        for(int i = 0; i < enemyGraph.size(); i++){
            if(!isConnectedToPower(enemyGraph.get(i))){
                setIsland(enemiesSea, enemyGraph.get(i));
            }else if(enemyGraph.get(i).getIsland().isDestroyed()){
                setDestroyedIsland(enemiesSea, enemyGraph.get(i));
            }
        }
        
    }
    
    // ------------------------------------------------- GETTERS AND SETTERS ----------------------------------------------------------

    public Player getPlayer() {
        return player;
    }

    public Graph getGraph() {
        return graph;
    }

    public ArrayList<ImageIcon> getSourcePowerIcons() {
        return sourcePowerIcons;
    }

    public ArrayList<ImageIcon> getConnectorIcons() {
        return connectorIcons;
    }

    public ArrayList<ImageIcon> getMarketIcons() {
        return marketIcons;
    }

    public ArrayList<ImageIcon> getMineIcons() {
        return mineIcons;
    }

    public ArrayList<ImageIcon> getTempleIcons() {
        return templeIcons;
    }

    public ArrayList<ImageIcon> getArmoryIcons() {
        return armoryIcons;
    }

    public ArrayList<ImageIcon> getSwirlIcons() {
        return swirlIcons;
    }

    public int getBudget() {
        return budget;
    }
    
    

    public Inventory getPlayerInventory() {
        return playerInventory;
    }
    
    public Inventory getMarketInventory(){
        return marketInventory;
    }
    
    public void setMarketInventory(Inventory newMarket){
        this.marketInventory = newMarket;
    }

    public void setBudget(int budget) {
        this.budget = budget;
    }
    
    
    
    
    
}
