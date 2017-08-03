/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package listeners;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import java.util.ArrayList;
import java.util.List;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import javax.swing.event.MouseInputAdapter;
import redoUndo.move;
import shapes.Circle;
import shapes.Line;
import shapes.shape;
import testgui.mypanel;
import testgui.shape_node;

/**
 *
 * @author carnival
 */

class Circle_node{
    private Circle circle;
    private int id ;
    private boolean rectbool ;
          // Fares
    public Circle_node(Circle circle , int id){
        rectbool = circle.getshowrec() ;
        this.circle = circle ;
        this.id = id ;
    }
    
    public void setrectbool(boolean b){
        rectbool = b ;
        circle.setshowrec(b);
    }
    
    public boolean getrecbool(){
         return rectbool ;
    }
    public void setCircle(Circle circle){
        this.circle = circle ;
    }
    public Circle getCircle(){
        return this.circle ;
    }
    public void setid(int id){
        this.id = id ;
    }
    public int getid(){
        return this.id ;
    }
}


public class CircleHandler extends MouseInputAdapter{
    private List<Circle> Circle_in_panel ;
    private mypanel panel ;
    int S = 6;
        private move obj = new move();
    Rectangle net = new Rectangle(S,S);
    int selectedIndex = -1;
    Point2D.Double offset = new Point2D.Double();
    boolean dragging = false;
    int selected_id = -1 ;
    Circle intersect = null;
    private String oldstat = new String();
    public CircleHandler(mypanel panel){
        Circle_in_panel = new ArrayList<>();
        this.panel = panel;
    }
    
    public void addCircle(Circle circle , int id){
        circle.setid(id);
        Circle_in_panel.add(circle);
    }
    
    public void DeleteCircle(int id){
        for( Circle node : Circle_in_panel ) {
             if(node.getid() == id){
                 Circle_in_panel.remove(node);
                 break ;
             }
        }
    }
    
    public void clearAll(){
        Circle_in_panel = new ArrayList<>();
        intersect = null;
        dragging = false ;
        selected_id = -1 ;
        selectedIndex = -1;
    } 
    
    private void update(int id , Circle c){
        c.setid(id);
        for(Circle node :Circle_in_panel){
             if(node.getid() == id){
                 Circle_in_panel.remove(node);
                 break;
             }
        }
        Circle_in_panel.add(c);
    }
     
    Circle state1 , state2 ;
    boolean flag2 = false ;
    public void mousePressed(MouseEvent e) {
        
        Point p = e.getPoint();
        net.setFrameFromCenter(p.x, p.y, p.x+S/2, p.y+S/2);
        boolean flag= false ;
        if(intersect == null){
        for(Circle node : Circle_in_panel){
                 
                if(((Ellipse2D.Double)node.getshape()).intersects(net)) {
                      System.out.println("ok ffff");
                      intersect = node;
                      node.setshowrec(true);
                      panel.setShowRects(true);
                      selected_id=node.getid();
                      XStream xml = new XStream(new StaxDriver());
                        oldstat = xml.toXML(intersect);
         
                  }else{
                    /* if(node.getCircle().getshowrec()){
                         intersect = node.getCircle();
                         flag=true ;
                     }else{*/
                      node.setshowrec(false);
                      panel.setShowRects(false); 
                     
                 }
            }
        }
       else{
            Rectangle2D.Double[] rects = intersect.getrects();
            for(int j = 0; j < rects.length; j++) {
                if(rects[j].contains(p)) {
                    selectedIndex = j;
                    offset.x = p.x - rects[j].x;
                    offset.y = p.y - rects[j].y;
                    dragging = true;
                    break ;
                }
          }
           if(selectedIndex == -1) {
                panel.setShowRects(false);
                intersect.setshowrec(false);
                intersect = null ;
           }
       }
    }
 
    public void mouseReleased(MouseEvent e) {
        selectedIndex = -1;
        dragging = false;
        if(flag2 == true){
            XStream xml = new XStream(new StaxDriver());
            obj.set(new shape_node((shape)xml.fromXML(oldstat) , selected_id), 2);
            obj.set(new shape_node(state2 , selected_id), 4);
            oldstat = xml.toXML(state2);
            flag2 = false;
        }
    }
 
    public void mouseDragged(MouseEvent e) {
        if(dragging) {
            double x = e.getX() - offset.x;
            double y = e.getY() - offset.y;
            intersect.setRect(selectedIndex, x, y);
            state2 = (Circle)intersect;
            flag2 = true ;
            panel.updateshape(selected_id,intersect);
            update(selected_id,intersect);
        }
    }
}
