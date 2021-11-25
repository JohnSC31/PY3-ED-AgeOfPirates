
package ageofpirates.model;

import ageofpirates.controller.MainController;
import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;


public class Game {
    
    // parametros para los tamanos de la matriz
    public static final int SEA_SIZE = 20; // matriz cuadrada de 20x20
    public static final int CELL_SIZE = 25; // cada celda sera de 25x25
    
    
    private Player player;
    private MainController mainController;
    private SeaCell[][] sea; // la parte del mar que le corresponde al jugador
    // private Grafo<Component> components (estructuras del jugador)
    private Graph graph; // grafo de los componentes del mar

    public Game(MainController mainController) {
        this.mainController = mainController;
        this.player = new Player(this.mainController); // se le pasa el controlador principal
        initSea();
//        initGraph();
        this.graph = new Graph();
    }
    
    // ------------------------------------------------- METODOS ------------------------------------------------------------------------
    // realiza una inicializacion de la matriz para el oceano
    private void initSea(){
        this.sea = new SeaCell[SEA_SIZE][SEA_SIZE];
        for(int i = 0; i < SEA_SIZE; i++){
            for(int j = 0; j < SEA_SIZE; j++){
                this.sea[i][j] = new SeaCell(i, j);
            }
        }
    }
    
    // se setean los componentes iniicales del grafo
    public void initGraph(){
        System.out.println("Create vertexes");
        Vertex powerVertex = createVertex("Fuente", 2,2);
        Vertex market = createVertex("Mercado", 2,1);
        Vertex connMarketPower = createVertex("connector", 1,1);
        
//        createArista(powerVertex, connMarketPower);
//        createArista(connMarketPower, market);
    }
    
    public Vertex createVertex(String name, int xDimension, int yDimension){
        // asegurar que tenga un espacio en la matriz del oceano
        int iSea = 0, jSea = 0;
        boolean posRight = false;
        while(!posRight){
            // crea randoms y verifica que la posicion sea correcta
            iSea = new Random().nextInt(SEA_SIZE);
            jSea = new Random().nextInt(SEA_SIZE); // random entre 0 y 19
            if(validSeaComponentPosition(iSea, jSea, xDimension, yDimension)){
                posRight = true;
            }
        }
        
        Vertex newVertex = new Vertex(new TestComponent(iSea, jSea, xDimension, yDimension, name));
        loadTestComponentIcons(newVertex.getComponent()); // carga las imagenes del componente grafico
        this.graph.add(newVertex);
        setSeaComponentPosition(newVertex.getComponent());
        
        return newVertex;
    }
    
    public void createArista(Vertex location, Vertex destiny){
        //uno de los 2 debe ser connector
//        if(location.getComponent().getName() == "connector" || destiny.getComponent().getName() == "connector"){
//            location.createArista(destiny);
//        }else{
//            System.out.println("Creacion de arista invalida");
//        }
    }
    
    // VALIDACIONES PARA EL OCEANOS
    // representa el grafo en la pantalla del jugador
    public void printGraph(){
        for(int i = 0; i < this.graph.size(); i++){
            
        }
    }
    
    private void setSeaComponentPosition(TestComponent element){
        try{
            int iPos = element.getiPos(), jPos = element.getjPos();
            int iconCounter = 0; // posicion para recuperar la imagen
            for(int yDimension = 0; yDimension < element.getyDimension(); yDimension++){
                for(int xDimension = 0; xDimension < element.getxDimension(); xDimension++){
                    sea[iPos][jPos].setIcon(MainController.resizeIcon(element.getIcons().get(iconCounter), CELL_SIZE, CELL_SIZE));
                    sea[iPos][jPos].setBackground(Color.blue);
                    sea[iPos][jPos].setOccupied(true);
                    sea[iPos][jPos].setGElement(element);
//                    sea[iPos][jPos].repaint();
                    iPos++;
                    iconCounter++;
                }
                iPos = element.getiPos();
                jPos++;
            }


        }catch(ArrayIndexOutOfBoundsException e){
            System.out.println("Set component invalid index");
        }
    }
    
    // valida que el componente de dimensiones x y y se pueda colocar en la posicion i, j
    public boolean validSeaComponentPosition(int i, int j, int dimensionRows, int dimensionCols){
        try{
            int iPos = i, jPos = j;
            for(int dimensionRow = 0; dimensionRow < dimensionRows; dimensionRow++){
                for(int dimensionCol = 0; dimensionCol < dimensionCols; dimensionCol++){
                    if(this.sea[i][j].isOccupied()) return false;

                    iPos++;
                }
                iPos = i;
                jPos++;
            }
            return true;
        }catch(ArrayIndexOutOfBoundsException e){
            System.out.println("Error position");
            return false;
        }

    }
    
    // mueve el componente hacia la direccion especificada
    public void moveLeftComponent(){
    
    }
    
    public void moveRightComponent(){
        
    }
    
    public void moveUpComponent(){
        
    }
    
    public void moveDownComponent(){
    
    }
    
    
    // --------------- CARGAR LAS IMAGENES PARA LOS ELEMENTOS -------------------------
    private void loadTestComponentIcons(TestComponent element){
        File iconFile = new File("./src/media/testComponent");
        for(int i = 0; i < element.getyDimension(); i++){
            for(int j = 0; j < element.getxDimension(); j++){
                try {
                    element.getIcons().add(new ImageIcon(iconFile.getCanonicalPath()));
                } catch (IOException ex) {
                    Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
    // ------------------------------------------------- GETTERS AND SETTERS ----------------------------------------------------------

    public Player getPlayer() {
        return player;
    }

    public SeaCell[][] getSea() {
        return sea;
    }
    
    
    
}
