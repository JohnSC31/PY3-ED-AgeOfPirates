
package ageofpirates.model;

import java.util.ArrayList;


public class Graph extends ArrayList<Vertex>{
    
    public Graph(){
        
    }
    
    // metodos de busqueda para los vertices etc
    
    // ve si existe al menos un tipo de esa isla o no
    public boolean isIsland(String name){
        for(int i = 0; i < this.size(); i++){
            if(this.get(i).getIsland().getName().equals(name)) return true;
        }
        return false;
    }
}
