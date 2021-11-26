
package ageofpirates.model;

import java.util.ArrayList;


public class Vertex {
    
    private Island island;
    private ArrayList<Arista> aristas;
    
    public Vertex(Island island){
        this.island = island;
        this.aristas = new ArrayList<>();
    }
    
    public void createArista(Vertex destiny){
        // alguno de los 2 debe ser un conector para que pueda haber conexion
        Arista newArista = new Arista(this, destiny);

        this.aristas.add(newArista);
        destiny.getAristas().add(newArista);
    }
    
    public void removeArista(Arista arista){
        this.aristas.remove(arista);
    }

    public Island getIsland() {
        return island;
    }

    public ArrayList<Arista> getAristas() {
        return aristas;
    }
    
    
    
}
