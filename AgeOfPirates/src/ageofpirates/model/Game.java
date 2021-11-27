
package ageofpirates.model;

import ageofpirates.controller.MainController;
import static ageofpirates.view.ConfigWindow.CELL_SIZE;
import static ageofpirates.view.ConfigWindow.SEA_SIZE;
import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;


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

    // recursos del jugador
    private int budget;
    private Inventory playerInventory;
    
    private Inventory marketInventory; // el inventario del mercado
    
    // enumerable para los objetos del inventario
    public enum ItemType{
        CANNON(1000),
        MULTIPLE_CANNON(2000),
        BOMB(4000),
        RED_BEARD_CANNON(10000),
        GHOST_SHIP(2500),
        STEEL(2);
        
        private int cost;
        
        private ItemType(int cost){
            this.cost = cost;
        }
        
        public int getCost(){
            return this.cost;
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
        this.playerInventory.updateItemAmount(ItemType.STEEL, 100);
        
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
                        }
                        
                    }catch(IOException e){
                        System.out.println("Error al cargar personaje");
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
    
    public void setSea(SeaCell[][] playerSea, Graph graph){
        for(int i = 0; i < graph.size(); i++){
            setIsland(playerSea, graph.get(i));
            // dibujar las aristas
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
