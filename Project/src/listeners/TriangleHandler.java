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
import shapes.Triangle;
import shapes.shape;
import testgui.mypanel;
import testgui.shape_node;

/**
 *
 * @author carnival
 */
public class TriangleHandler extends MouseInputAdapter{
   private List<Triangle> Tri_in_panel ;
    int S = 6;
    Rectangle net = new Rectangle(S,S);
    private mypanel panel ;
    int selectedIndex = -1;
     Point2D.Double offset = new Point2D.Double();
    boolean dragging = false;
    int selected_id = -1 ;
    private move obj = new move();
    private Triangle intersect =null;
    private String oldstat = new String();
    public TriangleHandler(mypanel panel) {
         Tri_in_panel = new ArrayList<>();
        this.panel = panel;
    }
  
    public void addTriangle(Triangle tri, int id){
        tri.setid(id);
        Tri_in_panel.add(tri);
    }
    
        public void DeleteTriangle (int id){
        for(Triangle node : Tri_in_panel){
             if(node.getid() == id){
                 Tri_in_panel.remove(node);
                 break;
             }
        }
    }
    
    public void clearAll(){
        Tri_in_panel = new ArrayList<>();
        intersect = null;
        dragging = false ;
        selected_id = -1 ;
        selectedIndex = -1;
    } 
    
    private void update(int id ,  Triangle rec){
        rec.setid(id);
        for(Triangle node : Tri_in_panel){
             if(node.getid() == id){
                 Tri_in_panel.remove(node);
                 break;
             }
        }
        Tri_in_panel.add(rec);
    }
    
    Triangle state1 , state2 ;
    boolean flag2 = false ;
public void mousePressed(MouseEvent e) {
        Point p = e.getPoint();
        net.setFrameFromCenter(p.x, p.y, p.x+S/2, p.y+S/2);
        boolean flag= false ;
        for(Triangle node : Tri_in_panel){
                  if(node.getLine1().intersects(net) || node.getLine2().intersects(net) || node.getLine3().intersects(net) ) {
                     System.out.println("ganners");
                     if(node.getshowrec()){
                         intersect = node ;
                         flag=true ;
                     }
                     else {
                      intersect = node ;   
                      node.setshowrec(true);
                      panel.setShowRects(true);
                      selected_id=node.getid();
                      flag=true ;
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
            state2 = (Triangle)intersect;
            flag2 = true ;
            panel.updateshape(selected_id,intersect);
            update(selected_id,intersect);
        }
    }
}
