/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shapes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author carnival
 */
public abstract class shape{
     private int id ;
     private boolean showRects ;
     public Rectangle2D.Double[] rects;
     public double S = 10.0;
     public Color color ;
     public shape(Double x,Double y){
     }
     public shape(){
     }
     public abstract void insta(Double a , Double b , Double z);
     
     public void setid(int id){
          this.id=id;
     }
     public int getid(){
          return this.id;
     }
     
     public void setshowrec(boolean val){
          showRects = val ;
          System.out.println("3aaaaaaaaaaaaaaaaaa");
      }
      
      public boolean getshowrec(){
          return showRects ;
      }
      
      public Rectangle2D.Double[] getrects(){
           return rects ;
      }
      
      public Point2D.Double getCenter(Rectangle2D.Double r) {
        return new Point2D.Double(r.getCenterX(), r.getCenterY());
     }
      
     public double getdistance(double x1 , double x2 , double y1 , double y2){
        double dx = x1 - x2;
        double dy = y1 - y2;
        return Math.sqrt(dx * dx + dy * dy);
    }

     public abstract void draw(Graphics2D g2);
     public abstract Object getshape();
     public abstract void setRect(int index, double x, double y) ;
     public abstract void setshape();
     public abstract void setCenter();
     public abstract List<Object> getAll();
     public abstract void setAll(List<Object> x);
     public abstract shape CopyShape();
}
