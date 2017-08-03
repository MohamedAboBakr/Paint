/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testgui;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import shapes.shape;

/**
 *
 * @author Fares
 */
public class shape_node{
    
    private double s ;
    private shape sh;
    private int id ;

    
    public shape_node(shape sh , int id ){
        s = 10.0;
        this.sh = sh ;
        this.id = id ;
    }
    
    public void setshape(shape sh){
        this.sh = sh ;
    }
    
    public void SetColor(Color c){
         sh.color = c ;
    }
    public shape getshape(){
        return this.sh ;
    }
    
    public void setid(int id){
        this.id = id ;
    }
    
    public int getid(){
        return this.id ;
    }
    
    public void setS(double s){
        this.s = s;
    }
    
    public double getS(){
        return this.s;
    }
}