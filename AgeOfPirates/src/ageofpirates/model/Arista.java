
package ageofpirates.model;

import java.io.Serializable;
import java.util.ArrayList;


public class Arista implements Serializable{
    
    private Vertex location;
    private Vertex destiny;
    //Son la posicion de la arista de origen
    private int Xloc;
    private int Yloc;
    //La posicion de la arista de destino
    private int Xdes;
    private int Ydes;
    public Arista(Vertex location, Vertex destiny) {
        this.location = location;
        this.destiny = destiny;
        location.getAristas().add(this);
    }
    public Arista(Vertex location, Vertex destiny, int x1, int y1,
            int x2, int y2) {
        this.location = location;
        this.destiny = destiny;
        this.Xloc=x1;
        this.Yloc=y1;
        this.Xdes=x2;
        this.Ydes=y2;
        location.getAristas().add(this);
    }
    public ArrayList<Integer> getCoord(){
        ArrayList<Integer> coordenadas= new ArrayList<Integer>();
        coordenadas.add(Xdes);
        coordenadas.add(Ydes);
        coordenadas.add(Xloc);
        coordenadas.add(Yloc);
        return coordenadas;
    }

    public Vertex getLocation() {
        return location;
    }

    public Vertex getDestiny() {
        return destiny;
    }
    
    
    
}
