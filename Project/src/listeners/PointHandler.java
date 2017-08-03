/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package listeners;

import java.awt.Point;
import java.awt.event.MouseEvent;
import javax.swing.event.MouseInputAdapter;
import testgui.mypanel;

/**
 *
 * @author carnival
 */
public class PointHandler extends MouseInputAdapter{
       private Point p ;
       private mypanel panel ;
       
       public PointHandler (mypanel panel ){
           this.panel = panel ;
       }
       
       public void mousePressed(MouseEvent e) {
           System.out.println("click done");
           Point p = e.getPoint();
           panel.setCurrpoint(p);
           panel.addListeners();
           panel.Draw();
       }
}
