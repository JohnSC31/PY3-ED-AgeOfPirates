
package ageofpirates.model;

import java.util.ArrayList;


public class Vertex {
    
    private TestComponent component;
    private ArrayList<Arista> aristas;
    
    public Vertex(TestComponent component){
        this.component = component;
        this.aristas = new ArrayList<>();
    }
    
    public void createArista(Vertex destiny){
        // alguno de los 2 debe ser un conector para que pueda haber conexion
        Arista newArista = new Arista(this, destiny);

        this.aristas.add(newArista);
        destiny.getAristas().add(newArista);
    }
    
    public void removeArista(Arista deleteArista){
        this.aristas.remove(deleteArista);
    }

    public TestComponent getComponent() {
        return component;
    }

    public ArrayList<Arista> getAristas() {
        return aristas;
    }
    
    
    
}
