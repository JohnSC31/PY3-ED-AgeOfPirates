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
public class Cannon extends Arma{
    public Cannon(){
        super.grafico="";
        super.precio=500;
    }
    @Override
    public void disparar(){
        System.out.println("Se dispara la bomba");
    }
}
