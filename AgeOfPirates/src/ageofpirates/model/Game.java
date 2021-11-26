
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

    public Game(MainController mainController) {
        this.mainController = mainController;
        this.player = new Player(this.mainController); // se le pasa el controlador principal
        this.graph = new Graph();
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
        Connector connector = new Connector(-1, -1, connectorIcons);
        createVertex(power);
        createVertex(market);
        createVertex(connector);
    }
    
    public void setSea(SeaCell[][] playerSea, Graph graph){
        for(int i = 0; i < graph.size(); i++){
            setIsland(playerSea, graph.get(i).getIsland());
            // dibujar las aristas
        }
    }
    
    // elimina todas las islas del oceano
    public void unSetSea(SeaCell[][] playerSea){
        for(int i = 0; i < SEA_SIZE ;i++){
            for(int j = 0; j < SEA_SIZE; j++){
                playerSea[i][j].setIsland(null);
                playerSea[i][j].setIcon(null);
            }
        }
    }
    
    // nuevas funciones para la adminsitracion del grafo y del oceano
    // addVertex(specificElement) agrega un nuevo vertice al grafo
    // addArista(vertexOrigin, vertexDestiny) genera una arista entre los vertices dados en el grafo
    
    // pasar la matriz de labels para la pantalla y tener una referencia en el la clase game para hacer display a los vertices y aristas
    // displayVertex(graphicElement) pinta el elemento de las dimensiones estitupulada con los iconos cargados
    // displayArista(Arista) toma los elementos de las aristas encuentra las celdas correspondientes y digbuja una linea encima para conectarlos
    // displaySea(graph) recorre el los vertices del grafo y cada uno de sus aristas y las representa en la matriz de labels
    // displayEnemySea(graph) toma un grafo enemigo y lo pinta en el oceano correspondiente
    // tener un arreglo de visibilidad con el id de los jugadores que pueden ver el elemento en el oceano enemigo
    // para facilitar el barco fantasma las desconexiones de la fuente de poder y destrucciones totales
    
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
    public void setIslandRandomPosition(SeaCell[][]  playerSea, Island island){
        int iSea = 0, jSea = 0;
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
        
        setIsland(playerSea, island);
    }
    
    // setea la isla en la ubicacion correspondiente
    public void setIsland(SeaCell[][] playerSea, Island island){
        try{
            int iPos = island.getiPos(), jPos = island.getjPos();
            int iconCounter = 0; // posicion para recuperar la imagen
            for(int yDimension = 0; yDimension < island.getyDimension(); yDimension++){
                for(int xDimension = 0; xDimension < island.getxDimension(); xDimension++){
                    playerSea[iPos][jPos].setIcon(MainController.resizeIcon(island.getIcons().get(iconCounter), CELL_SIZE, CELL_SIZE));
                    playerSea[iPos][jPos].setIsland(island);
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
    private void unSetIsland(SeaCell[][] playerSea, Island island){
        try{
            int iPos = island.getiPos(), jPos = island.getjPos();
            for(int yDimension = 0; yDimension < island.getyDimension(); yDimension++){
                for(int xDimension = 0; xDimension < island.getxDimension(); xDimension++){
                    playerSea[iPos][jPos].setIcon(null);
                    playerSea[iPos][jPos].setIsland(null);
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
        try{
            int iPos = i, jPos = j;
            for(int dimensionRow = 0; dimensionRow < yDimension; dimensionRow++){
                for(int dimensionCol = 0; dimensionCol < xDimension; dimensionCol++){
                    if(playerSea[iPos][jPos].getIsland() != null) return false;

                    jPos++;
                }
                jPos = i;
                iPos++;
            }
            return true;
        }catch(ArrayIndexOutOfBoundsException e){
            System.out.println("Error position");
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
    public void moveLeftIsland(SeaCell[][] playerSea, Island island){
        unSetIsland(playerSea, island);
        if(validIslandPosition(playerSea, island.getiPos(), island.getjPos() - 1, island.getxDimension(), island.getyDimension())){
            island.setjPos(island.getjPos() - 1);
        }
        
        setIsland(playerSea, island);
    }
    
    public void moveRightIsland(SeaCell[][] playerSea, Island island){
        unSetIsland(playerSea, island);
        if(validIslandPosition(playerSea, island.getiPos(), island.getjPos() + 1, island.getxDimension(), island.getyDimension())){
            island.setjPos(island.getjPos() + 1);
        }
        
        setIsland(playerSea, island);
    }
    
    public void moveUpIsland(SeaCell[][] playerSea, Island island){
        unSetIsland(playerSea, island);
        if(validIslandPosition(playerSea, island.getiPos(), island.getiPos() - 1, island.getxDimension(), island.getyDimension())){
            island.setiPos(island.getiPos() - 1);
        }
        
        setIsland(playerSea, island);
    }
    
    public void moveDownIsland(SeaCell[][] playerSea, Island island){
        unSetIsland(playerSea, island);
            if(validIslandPosition(playerSea, island.getiPos() + 1, island.getjPos(), island.getxDimension(), island.getyDimension())){
                island.setiPos(island.getiPos() + 1);
            }

            setIsland(playerSea, island);
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
    
    
    
    
    
}
