/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shapes;

/**
 *
 * @author Mohamed_AboBakr
 */

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.LinkedList;
import java.util.List;
import javax.swing.event.MouseInputAdapter;
import listeners.CircleHandler;

public class Circle extends shape{
    
    private Ellipse2D.Double circle;
    private Double CenterX ,CenterY ;
    private Double R ;
    public Circle(){
        
    }
    public void insta(Double x, Double y , Double R){
         this.CenterX = x; this.CenterY = y ; 
         this.R = R;
         rects = new Rectangle2D.Double[9];
          for(int j = 0; j < rects.length; j++) rects[j] = new Rectangle2D.Double();
          circle = new Ellipse2D.Double(CenterX , CenterY , this.R , this.R);
          double x1 = circle.getMaxX();
          double y1 = circle.getMaxY();
          double x2 = circle.getMinX();
          double y2 = circle.getMinY(); 
          rects[0] = new Rectangle2D.Double(x1-S/2, circle.getCenterY()-S/2, S, S);
          rects[1] = new Rectangle2D.Double(circle.getCenterX()-S/2, y2-S/2, S, S);
          rects[2] = new Rectangle2D.Double(x2-S/2, circle.getCenterY()-S/2, S, S);
          rects[3] = new Rectangle2D.Double(circle.getCenterX()-S/2, y1-S/2, S, S);
          rects[5] = new Rectangle2D.Double(x1-S/2, y2-S/2, S, S);
          rects[6] = new Rectangle2D.Double(x2-S/2, y2-S/2, S, S);
          rects[7] = new Rectangle2D.Double(x2-S/2, y1-S/2, S, S);
          rects[8] = new Rectangle2D.Double(x1-S/2, y1-S/2, S, S);
          rects[4] = new Rectangle2D.Double();
          setCenter();
    }
    public Circle(Double x, Double y , Double R) {
         super(x, y);
         this.CenterX = x; this.CenterY = y ; 
         this.R = R;
         rects = new Rectangle2D.Double[9];
          for(int j = 0; j < rects.length; j++) rects[j] = new Rectangle2D.Double();
          circle = new Ellipse2D.Double(CenterX , CenterY , this.R , this.R);
          double x1 = circle.getMaxX();
          double y1 = circle.getMaxY();
          double x2 = circle.getMinX();
          double y2 = circle.getMinY(); 
          rects[0] = new Rectangle2D.Double(x1-S/2, circle.getCenterY()-S/2, S, S);
          rects[1] = new Rectangle2D.Double(circle.getCenterX()-S/2, y2-S/2, S, S);
          rects[2] = new Rectangle2D.Double(x2-S/2, circle.getCenterY()-S/2, S, S);
          rects[3] = new Rectangle2D.Double(circle.getCenterX()-S/2, y1-S/2, S, S);
          rects[5] = new Rectangle2D.Double(x1-S/2, y2-S/2, S, S);
          rects[6] = new Rectangle2D.Double(x2-S/2, y2-S/2, S, S);
          rects[7] = new Rectangle2D.Double(x2-S/2, y1-S/2, S, S);
          rects[8] = new Rectangle2D.Double(x1-S/2, y1-S/2, S, S);
          rects[4] = new Rectangle2D.Double();
          setCenter();
    }
   
      public void setCenter() {
        double x1  = rects[2].getCenterX() + (rects[0].getCenterX() - rects[2].getCenterX())/2 ;
        double y1  = rects[2].getCenterY() + (rects[0].getCenterY() - rects[2].getCenterY())/2 ;
        rects[4].setFrameFromCenter( x1 , y1 , x1+S/2, y1+S/2);
    }
      
     public void setRect(int index, double x, double y) {
        double dy = y - rects[index].y;
        double dx = x - rects[index].x;
        switch(index) {
            case 4:
                rects[0].setFrame(rects[0].x+dx, rects[0].y+dy, S, S);
                rects[1].setFrame(rects[1].x+dx, rects[1].y+dy, S, S);
                rects[2].setFrame(rects[2].x+dx, rects[2].y+dy, S, S);
                rects[3].setFrame(rects[3].x+dx, rects[3].y+dy, S, S);
                rects[5].setFrame(rects[5].x+dx, rects[5].y+dy, S, S);
                rects[6].setFrame(rects[6].x+dx, rects[6].y+dy, S, S);
                rects[7].setFrame(rects[7].x+dx, rects[7].y+dy, S, S);
                rects[8].setFrame(rects[8].x+dx, rects[8].y+dy, S, S);
                setshape();
                break;
            case 0:
            case 2:
                rects[index].setFrame(x,rects[index].getY(),S,S);
                rects[5].setFrame(rects[0].x, rects[5].y, S, S);
                rects[8].setFrame(rects[0].x, rects[8].y, S, S);
                rects[6].setFrame(rects[2].x, rects[6].y, S, S);
                rects[7].setFrame(rects[2].x, rects[7].y, S, S);
                rects[1].setFrame(rects[4].x+dx, rects[1].y, S, S);
                rects[3].setFrame(rects[4].x+dx, rects[3].y, S, S);
                setshape();
                break;
            case 1:
            case 3:
                rects[index].setFrame(rects[index].getX(),y,S,S);
                rects[5].setFrame(rects[5].x, rects[1].y, S, S);
                rects[6].setFrame(rects[6].x, rects[1].y, S, S);
                rects[7].setFrame(rects[7].x, rects[3].y, S, S);
                rects[8].setFrame(rects[8].x, rects[3].y, S, S);
                rects[0].setFrame(rects[0].x, rects[4].y+dy/2, S, S);
                rects[2].setFrame(rects[2].x, rects[4].y+dy/2, S, S);
                rects[4].setFrame(rects[4].x, rects[2].y, S, S);
                setshape();
                break ;
            case 5:
                rects[5].setFrame(rects[5].x+dx ,rects[5].y+dy , S,S);
                rects[4].setFrame(rects[4].x+dx, rects[4].y+dy/2, S, S);
                rects[0].setFrame(rects[0].x+dx, rects[0].y+dy/2, S, S);
                rects[1].setFrame(rects[4].x   , rects[5].y, S, S);
                rects[2].setFrame(rects[2].x   , rects[2].y+dy/2, S, S);
                rects[3].setFrame(rects[4].x, rects[3].y, S, S);
                rects[6].setFrame(rects[6].x, rects[6].y+dy, S, S);
                rects[7].setFrame(rects[7].x, rects[7].y, S, S);
                rects[8].setFrame(rects[8].x+dx, rects[8].y, S, S);
                setshape();
                break;
           case 6:
                rects[6].setFrame(rects[6].x+dx, rects[6].y+dy, S, S);
                rects[5].setFrame(rects[5].x ,rects[5].y+dy , S,S);
                rects[4].setFrame(rects[4].x+dx, rects[4].y+dy/2, S, S);
                rects[0].setFrame(rects[0].x, rects[4].y, S, S);
                rects[1].setFrame(rects[4].x  , rects[5].y, S, S);
                rects[2].setFrame(rects[2].x+dx  , rects[4].y, S, S);
                rects[3].setFrame(rects[4].x, rects[3].y, S, S);
                rects[7].setFrame(rects[6].x, rects[7].y, S, S);
                rects[8].setFrame(rects[8].x, rects[8].y, S, S);
                setshape();
                break;
           case 7:
                rects[7].setFrame(rects[7].x+dx, rects[7].y+dy, S, S);
                rects[5].setFrame(rects[5].x ,rects[5].y, S,S);
                rects[4].setFrame(rects[4].x+dx, rects[4].y+dy/2, S, S);
                rects[0].setFrame(rects[0].x, rects[4].y , S, S);
                rects[1].setFrame(rects[4].x  , rects[5].y, S, S);
                rects[2].setFrame(rects[7].x  , rects[4].y, S, S);
                rects[3].setFrame(rects[4].x, rects[7].y, S, S);
                rects[6].setFrame(rects[7].x, rects[1].y, S, S);
                rects[8].setFrame(rects[8].x, rects[7].y, S, S);
               setshape();
               break ;
           case 8:
                rects[8].setFrame(rects[8].x+dx, rects[8].y+dy, S, S);
                rects[5].setFrame(rects[8].x ,rects[5].y, S,S);
                rects[4].setFrame(rects[4].x+dx, rects[4].y+dy/2, S, S);
                rects[0].setFrame(rects[8].x, rects[4].y , S, S);
                rects[1].setFrame(rects[4].x  , rects[5].y, S, S);
                rects[2].setFrame(rects[2].x  , rects[4].y, S, S);
                rects[3].setFrame(rects[4].x, rects[8].y, S, S);
                rects[6].setFrame(rects[2].x, rects[1].y, S, S);
                rects[7].setFrame(rects[7].x, rects[8].y, S, S);
                setshape();
               break ; 
            default:{}
        }
       // setcircle();
    }
 
    public void setshape() {
        double x1  = rects[2].getCenterX() + (rects[0].getCenterX() - rects[2].getCenterX())/2 ;
        double y1  = rects[2].getCenterY() + (rects[0].getCenterY() - rects[2].getCenterY())/2 ;
        double dist1 = getdistance(rects[0].getCenterX() , rects[2].getCenterX() , rects[0].getCenterY() , rects[2].getCenterY() );
        double dist2 = getdistance(rects[1].getCenterX() , rects[3].getCenterX() , rects[1].getCenterY() , rects[3].getCenterY() );
        circle.setFrameFromCenter(x1, y1, rects[1].getCenterX()-dist1/2, rects[2].getCenterY()-dist2/2);
        setCenter();
      //  repaint();
    }



     public Ellipse2D.Double getshape(){
         return this.circle ;
     }
     
    @Override
    public void draw(Graphics2D g2) {
        System.out.println("draw done");
        Color c = g2.getColor();
        g2.setColor(super.color);
        g2.draw(circle);
        if(getshowrec()) {
            g2.setPaint(Color.red);
            for(int j = 0; j < rects.length; j++)
                g2.draw(rects[j]);

        }             
      g2.setPaint(c);
    }

@Override
    public List<Object> getAll(){
        List<Object> x = new LinkedList<>();
        x.add(super.getid());
        x.add(super.getshowrec());
        x.add(rects);
        x.add(CenterX);
        x.add(CenterY);
        x.add(R);
        x.add(circle);
        return x;
    }
    
    @Override
    public void setAll(List<Object> x){
        setid((Integer)x.get(0));
        setshowrec((Boolean)x.get(1));
        rects = (Rectangle2D.Double[])x.get(2);
        CenterX = (Double)x.get(3);
        CenterY = (Double)x.get(4);
        R = (Double)x.get(5);
        circle = (Ellipse2D.Double)x.get(6);
    }

    @Override
    public shape CopyShape() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
