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
public class Bomba extends Arma{
    public Bomba(){
        super.grafico="";
        super.precio=2000;
        
    }
    @Override
    public void disparar(){
        System.out.println("Se dispara la bomba");
    }
}
