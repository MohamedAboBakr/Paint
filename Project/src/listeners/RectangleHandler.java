/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package listeners;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;
import javax.swing.event.MouseInputAdapter;
import redoUndo.move;
import shapes.Rectanglee;
import shapes.shape;
import testgui.mypanel;
import testgui.shape_node;

/**
 *
 * @author carnival
 */

public class RectangleHandler extends MouseInputAdapter{
    private List<Rectanglee> Rects_in_panel ;
    private mypanel panel ;
    int S = 6;
    Rectangle net = new Rectangle(S,S);
    int selectedIndex = -1;
    Point2D.Double offset = new Point2D.Double();
    boolean dragging = false;
    int selected_id = -1 ;
    Rectanglee intersect = null;
    private move obj = new move();
    private String oldstat = new String();
    public RectangleHandler(mypanel panel){
        Rects_in_panel = new ArrayList<>();
        this.panel = panel;
    }
    
    public void addRectangle(Rectanglee rec , int id){
        rec.setid(id);
        Rects_in_panel.add(rec);
    }
    
     public void clearAll(){
        Rects_in_panel = new ArrayList<>();
        intersect = null;
        dragging = false ;
        selected_id = -1 ;
        selectedIndex = -1;
    } 
    public void DeleteRectangle (int id){
        for(Rectanglee node : Rects_in_panel){
             if(node.getid() == id){
                 Rects_in_panel.remove(node);
                 break;
             }
        }
    }
    private void update(int id ,  Rectanglee rec){
        rec.setid(id);
        for(Rectanglee node : Rects_in_panel){
             if(node.getid() == id){
                 Rects_in_panel.remove(node);
                 break;
             }
        }
        Rects_in_panel.add(rec);
    }
    
    
    Rectanglee state1 , state2 ;
    boolean flag2 = false ;
      public void mousePressed(MouseEvent e) {
          
        Point p = e.getPoint();
        net.setFrameFromCenter(p.x, p.y, p.x+S/2, p.y+S/2);
        boolean flag= false ;
        for(Rectanglee node : Rects_in_panel){
                  if(((Rectangle2D.Double)node.getshape()).intersects(net)) {
                     System.out.println("ganners");
                     if(node.getshowrec()){
                         intersect = node;
                         flag=true ;
                     }
                     else {
                      node.setshowrec(true);
                      panel.setShowRects(true);
                      selected_id=node.getid();
                      intersect = node;
                     }
                     XStream xml = new XStream(new StaxDriver());
                  oldstat = xml.toXML(intersect);
                  }else{
                      node.setshowrec(false);
                      panel.setShowRects(false);   
                  }
        } 
          
      if(intersect != null){
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
           }
       }
    }
 
    public void mouseReleased(MouseEvent e) {
        selectedIndex = -1;
        dragging = false;
        if(flag2 == true){
            XStream xml = new XStream(new StaxDriver());
            obj.set((shape_node) new shape_node((shape)xml.fromXML(oldstat) , selected_id), 2);
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
            state2 = (Rectanglee)intersect;
            flag2 = true ;
            panel.updateshape(selected_id,intersect);
            update(selected_id,intersect);
        }
    }
}
