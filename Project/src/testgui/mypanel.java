/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testgui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import listeners.CircleHandler;
import listeners.EllipseHandler;
import listeners.LineHandler;
import listeners.PointHandler;
import listeners.RectangleHandler;
import listeners.TriangleHandler;
import redoUndo.move;
import shapes.Circle;
import shapes.Ellipse;
import shapes.Line;
import shapes.Rectanglee;
import shapes.Triangle;
import shapes.shape;

/**
 *
 * @author carnival
 */
public class mypanel extends JPanel{
    public move redoundoobj ;
    public listOfShapes LOS ;
    public List<shape_node> SHAPes;
    public Color color =Color.black ;
    public boolean  showRects ;
    private setMenu menu_setter;
    private Point p;
    private int flag ;
    private int drawn =1;
    public mypanel(setMenu menu_setter) {
            redoundoobj = new move();
            LOS = new listOfShapes();
            showRects = false ;
            this.menu_setter = menu_setter ;
            SHAPes = LOS.getList();
            listOfShapes.lH = new LineHandler(this);
            listOfShapes.CH = new CircleHandler(this);
            listOfShapes.PH = new PointHandler(this);
            listOfShapes.RH = new RectangleHandler(this);
            listOfShapes.EH = new EllipseHandler(this);
            listOfShapes.TH = new TriangleHandler(this);
            
            addMouseListener(listOfShapes.lH);
            addMouseMotionListener(listOfShapes.lH);
            addMouseListener(listOfShapes.CH);
            addMouseMotionListener(listOfShapes.CH);
            addMouseListener(listOfShapes.RH);
            addMouseMotionListener(listOfShapes.RH);
            addMouseListener(listOfShapes.EH);
            addMouseMotionListener(listOfShapes.EH);
            addMouseListener(listOfShapes.TH);
            addMouseMotionListener(listOfShapes.TH);
    }
   
    public void setCurrpoint(Point p){
         this.p = p ;
    }
    
    public Point getCurrpoint(){
         return this.p;
    }
    
    
    public void removeListeners(int flag){
        this.flag = flag ;
        System.out.println("remove done");
         removeMouseListener(listOfShapes.lH);
         removeMouseListener(listOfShapes.CH);
         removeMouseListener(listOfShapes.RH);
         removeMouseListener(listOfShapes.EH);
         removeMouseListener(listOfShapes.TH);
         removeMouseMotionListener(listOfShapes.lH);
         removeMouseMotionListener(listOfShapes.CH);
         removeMouseMotionListener(listOfShapes.RH);
         removeMouseMotionListener(listOfShapes.EH);
         removeMouseMotionListener(listOfShapes.TH);
         
         
         addMouseListener(listOfShapes.PH);
         addMouseMotionListener(listOfShapes.PH);
    }
    
    public void addListeners(){
        System.out.println("add done");
         removeMouseListener(listOfShapes.PH);
         removeMouseMotionListener(listOfShapes.PH);
         
         addMouseListener(listOfShapes.lH);
         addMouseMotionListener(listOfShapes.lH);
         addMouseListener(listOfShapes.CH);
         addMouseMotionListener(listOfShapes.CH);
         addMouseListener(listOfShapes.RH);
         addMouseMotionListener(listOfShapes.RH);
         addMouseListener(listOfShapes.EH);
         addMouseMotionListener(listOfShapes.EH);
         addMouseListener(listOfShapes.TH);
         addMouseMotionListener(listOfShapes.TH);
    }
    
   
    
    public void ReColor(){
        for(shape_node node: SHAPes){
              if(node.getshape().getshowrec()){
                   new move().set(node, 2);
                   node.SetColor(menu_setter.color);
                   new move().set(node, 4);
              }
        }
        repaint();
    }
    
    
    public void Draw(){
        Point pnt = getCurrpoint();
         if(flag==1){
            shape s1 = new Line(pnt.getX(),pnt.getY());
            s1.color = color ;
            addShape(s1,drawn); drawn++;
            repaint();
         }
         else if(flag==2){
            shape s1 = new Circle(pnt.getX(),pnt.getY(),50.0); 
            s1.color = color ;
            addShape(s1, drawn); drawn++;
            repaint();
         }else if(flag==3){
            shape s1 = new Rectanglee(pnt.getX(),pnt.getY(),50.0,100.0);  
            s1.color = color ;
            addShape(s1, drawn); drawn++;
            repaint();
         }else if(flag==4){
            shape s1 = new Ellipse(pnt.getX(),pnt.getY(),50.0,150.0);
            s1.color = color ;
            addShape(s1, drawn); drawn++;
            repaint();
         }else if(flag==5){
            shape s1 = new Triangle(pnt.getX(),pnt.getY(),50.0,150.0);
            s1.color = color ;
            addShape(s1, drawn); drawn++;
            repaint();
         }
    }
    
    public void addShape(shape sh , int id){
        shape_node node = new shape_node(sh,id);
        SHAPes.add(node);
        redoundoobj.set(node, 1);
        if(sh instanceof Line) listOfShapes.lH.addLine((Line)sh, id);
        else if(sh instanceof Circle) listOfShapes.CH.addCircle((Circle)sh, id);
        else if(sh instanceof Rectanglee) listOfShapes.RH.addRectangle((Rectanglee)sh,id);
        else if(sh instanceof Ellipse) listOfShapes.EH.addEllipse((Ellipse)sh,id);
        else if(sh instanceof Triangle) listOfShapes.TH.addTriangle((Triangle)sh,id);
        
    }
    
    public void Delete(){
        
      
         for(shape_node node: SHAPes){
              // if(node.getshape()==null) continue ;
              if(node.getshape().getshowrec()){
                  
                    if(node.getshape() instanceof Line) listOfShapes.lH.DeleteLine(node.getid());
                     else if(node.getshape() instanceof Circle) listOfShapes.CH.DeleteCircle(node.getid());
                     else if(node.getshape() instanceof Rectanglee) listOfShapes.RH.DeleteRectangle(node.getid());
                     else if(node.getshape() instanceof Ellipse) listOfShapes.EH.DeleteEllipse(node.getid());
                     else if(node.getshape() instanceof Triangle) listOfShapes.TH.DeleteTriangle(node.getid());

                    SHAPes.remove(node);
                    // System.out.println(SHAPes.size());
                    break;
              }
         }
       
        repaint();
    }
    
    public void removeSelected(){
          for(shape_node node: SHAPes){
             node.getshape().setshowrec(false);
         }
         repaint();
    }
   
    
    
   public void updateshape(int id , shape new_shape){
         for(shape_node node: SHAPes){
              if(node==null) continue ;
              if(node.getid() == id){
              node.setshape(new_shape);
                  break ;
              }
         }
         repaint();
   }
    
  /* 
   public void updateCircle(int id , Circle new_circle){
         for(shape_node node: SHAPes){
              if(node.getid() == id){
              node.setshape(new_circle);
                  break ;
              }
         }
         repaint();
   }*/
    
   
   
   
   public void setShowRects(boolean val){
             showRects = val ;
             repaint();
   }
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        color = menu_setter.color ;
        g2.setColor(color);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                            RenderingHints.VALUE_ANTIALIAS_ON);
        
        for(shape_node node : SHAPes){
             node.getshape().draw(g2);
        }
    }
    
}

