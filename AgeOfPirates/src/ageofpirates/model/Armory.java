/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ageofpirates.model;

import java.util.ArrayList;
import javax.swing.ImageIcon;


public class Armory extends Island{
    
    private String weapon;
    
    public Armory(int iPos, int jPos, ArrayList<ImageIcon> icons, String weapon) {
        super(iPos, jPos, icons, 1, 2, "Armeria", 1500);
        this.weapon = weapon;
    }

    @Override
    public void action() {
        
    }
    
}
