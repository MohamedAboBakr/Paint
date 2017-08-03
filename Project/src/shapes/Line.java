/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shapes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.event.MouseInputAdapter;
import listeners.LineHandler;

/**
 *
 * @author carnival
 */
public class Line extends shape implements Cloneable {
      private Line2D.Double line ;
      private Double x1,y1,x2,y2 ; 
      public Line(Double x,Double y){
          super(x,y);
          this.x1 = x+30; this.y1 = y;
          this.x2 = x-30; this.y2 = y;
          setshowrec(false) ;
          line = new Line2D.Double(x1,y1,x2,y2);
          rects = new Rectangle2D.Double[3];
          for(int j = 0; j < rects.length; j++) rects[j] = new Rectangle2D.Double();
          rects[0] = new Rectangle2D.Double(x1-S/2, y1-S/2, S, S);
          rects[1] = new Rectangle2D.Double(x2-S/2, y2-S/2, S, S);
          rects[2] = new Rectangle2D.Double();
          setCenter();
      }
      
      public Line2D.Double getshape(){
          return line ;
      }
      
      public void setRect(int index, double x, double y) {
        switch(index) {
            case 2:
                double dy = y - rects[2].y;
                double dx = x - rects[2].x;
                rects[0].setFrame(rects[0].x+dx, rects[0].y+dy, S, S);
                rects[1].setFrame(rects[1].x+dx, rects[1].y+dy, S, S);
                break;
            default:
                rects[index].setFrame(x, y, S, S);
        }
        setshape();
    }
 
    public void setshape() {
        line.setLine(getCenter(rects[0]), getCenter(rects[1]));
        setCenter();
    }
 
    public void setCenter() {
        double cx = line.getX1() + (line.getX2() - line.getX1())/2;
        double cy = line.getY1() + (line.getY2() - line.getY1())/2;
        rects[2].setFrameFromCenter(cx, cy, cx+S/2, cy+S/2);
    }

    @Override
    public void draw(Graphics2D g2) {
        Color c = g2.getColor();
        g2.setColor(super.color);
        g2.draw(line);
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
        x.add(rects.clone());
        x.add(x1);
        x.add(x2);
        x.add(y1);
        x.add(y2);
        x.add(line.clone());
        return x;
    }
    
    @Override
    public void setAll(List<Object> x){
        setid((Integer)x.get(0));
        setshowrec((Boolean)x.get(1));
        rects = (Rectangle2D.Double[])x.get(2);
        x1 = (Double)x.get(3);
        x2 = (Double)x.get(4);
        y1 = (Double)x.get(5);
        y2 = (Double)x.get(6);
        line = (Line2D.Double)x.get(7);
    }

    @Override
    public shape CopyShape() {
          try {
              return (shape)this.clone();
          } catch (CloneNotSupportedException ex) {
              Logger.getLogger(Line.class.getName()).log(Level.SEVERE, null, ex);
          }
          return null ;
    }

    @Override
    public void insta(Double a, Double b, Double z) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

  
    
}
