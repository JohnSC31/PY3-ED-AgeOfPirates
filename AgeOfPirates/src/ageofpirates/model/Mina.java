/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ageofpirates.model;

/**
 *
 * @author PC
 */
public class Mina {
    private static int precio=1000;
    //Son configurables
    private int kilos=50;
    private int tiempo=60;
    
    public void configurarVelocidad(int tiempo){
        this.tiempo=tiempo;
    }
    public void configurarCantidad(int cantidad){
        this.kilos=cantidad;
    }
}
