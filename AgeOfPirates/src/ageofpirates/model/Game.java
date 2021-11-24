
package ageofpirates.model;

import ageofpirates.controller.MainController;


public class Game {
    
    // parametros para los tamanos de la matriz
    public static final int SEA_SIZE = 20; // matriz cuadrada de 20x20
    public static final int CELL_SIZE = 25; // cada celda sera de 25x25
    
    
    private Player player;
    private MainController mainController;
    private SeaCell[][] sea; // la parte del mar que le corresponde al jugador
    // private ArrayList<Component> components (estructuras del jugador)

    public Game(MainController mainController) {
        this.mainController = mainController;
        this.player = new Player(this.mainController); // se le pasa el controlador principal
        this.sea = new SeaCell[SEA_SIZE][SEA_SIZE];
        initSea();
    }
    
    // ------------------------------------------------- METODOS ------------------------------------------------------------------------
    // realiza una inicializacion de la matriz para el oceano
    private void initSea(){
        for(int i = 0; i < SEA_SIZE; i++){
            for(int j = 0; j < SEA_SIZE; j++){
                this.sea[i][j] = new SeaCell(i, j);
            }
        }
    }
    
    
    // VALIDACIONES PARA EL OCEANOS
    // coloca el componente en la posicion especificada si esta disponible
    public void setComponent(){
        
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
    
    // ------------------------------------------------- GETTERS AND SETTERS ----------------------------------------------------------

    public Player getPlayer() {
        return player;
    }

    public SeaCell[][] getSea() {
        return sea;
    }
    
    
    
}
