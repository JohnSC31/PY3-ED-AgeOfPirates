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
public class CannonMultiple extends Arma{
   public CannonMultiple(){
       super.precio=1000;
       super.grafico=""/*src\\media\\cannonMult*/;
   }
   @Override
    public void disparar(){
        System.out.println("Se dispara la bomba");
    }
}
