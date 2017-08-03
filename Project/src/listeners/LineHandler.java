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
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.event.MouseInputAdapter;
import redoUndo.move;
import shapes.*;
import testgui.mypanel;
import testgui.shape_node;

/**
 *
 * @author carnival
 */
class line_node{
    private Line line;
    private int id ;
    private boolean rectbool ;
          // Fares
    public line_node(Line line , int id){
        rectbool = line.getshowrec() ;
        this.line = line ;
        this.id = id ;
    }
    
    public void setrectbool(boolean b){
        rectbool = b ;
        line.setshowrec(b);
    }
    
    public boolean getrecbool(){
         return rectbool ;
    }
    public void setLine(Line line){
        this.line = line ;
    }
    public Line getLine(){
        return this.line ;
    }
    public void setid(int id){
        this.id = id ;
    }
    public int getid(){
        return this.id ;
    }
}





public class LineHandler extends MouseInputAdapter{
    private List<line_node> lines_in_panel ;
    private move obj = new move();
    private mypanel panel ;
    private String oldstat = new String();
    int S = 6;
    Rectangle net = new Rectangle(S,S);
    int selectedIndex = -1;
    Point2D.Double offset = new Point2D.Double();
    boolean dragging = false;
    int selected_id = -1 ;
    Line intersect ;
    public LineHandler(mypanel panel){
        lines_in_panel = new ArrayList<line_node>();
        this.panel = panel ;   
    }
   
    public void clearAll(){
        lines_in_panel = new ArrayList<>();
        intersect = null;
        dragging = false ;
        selected_id = -1 ;
        selectedIndex = -1;
    } 
    
    public void addLine(Line line , int id){
        line_node node = new line_node(line , id);
        lines_in_panel.add(node);
    }
    
    public void DeleteLine(int id){
        for( line_node node : lines_in_panel){
             if(node.getid() == id){
                 lines_in_panel.remove(node);
                 break ;
             }
        }
    }
    private void update(int id , Line l){
        for(line_node node :lines_in_panel){
             if(node.getid() == id){
                 node.setLine(l);
             }
        }
    }
    
    Line state1 , state2 ;
    boolean flag2 = false ;
    public void mousePressed(MouseEvent e) {
       // System.out.println(lines_in_panel.size() + "**********************");
        Point p = e.getPoint();
        net.setFrameFromCenter(p.x, p.y, p.x+S/2, p.y+S/2);
        boolean flag= false;
        for(line_node node :lines_in_panel){
                  if(((Line2D.Double)node.getLine().getshape()).intersects(net)) {
                     if(node.getrecbool()){
                         intersect = node.getLine();
                         flag=true ;
                     }
                     else {
                      node.setrectbool(true);
                      panel.setShowRects(true);
                      selected_id=node.getid();
                      intersect = node.getLine();
                     }
                  XStream xml = new XStream(new StaxDriver());
                  oldstat = xml.toXML(intersect);
                  }else{
                      node.setrectbool(false);
                      panel.setShowRects(false);   
                  }
            }
            
        if(flag)  {
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
            state2 = (Line)intersect.CopyShape() ;
            flag2 = true ;
            panel.updateshape(selected_id,intersect);
            update(selected_id,intersect);
        }
    } 
}
