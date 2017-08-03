/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testgui;
import java.awt.*;
import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.event.*;
import shapes.*;

public class test2 {
    
    private JPanel gui;
    private Color color = Color.WHITE;
    private JLabel output = new JLabel("Paint");

    private BufferedImage colorSample = new BufferedImage(
            16,16,BufferedImage.TYPE_INT_RGB);
    public static final int SELECTION_TOOL = 0;
    public static final int DRAW_TOOL = 1;
    public static final int TEXT_TOOL = 2;
    private Point selectionStart; 
    private Rectangle selection;
    private boolean dirty = false;
    private Stroke stroke = new BasicStroke(
            3,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND,1.7f);
  
    // menu setter 
    private setMenu menu_setter;
    mypanel p ;
 
    public test2(){
        
    }
    
    
    public void set_menuSetter(setMenu setter){
        menu_setter = setter ;
        p = new mypanel(menu_setter);
    }
    public JComponent getGui() {
        if (gui==null) {
    
            gui = new JPanel(new BorderLayout(4,4));
            gui.setBorder(new EmptyBorder(5,3,5,3));
            p.setBackground(color.white);
            gui.add(p,BorderLayout.CENTER);
            menu_setter.set_Menu(gui);
        }
           return gui;
  }
        
   
   public void callDraw(int flag){
       p.removeSelected();
       p.removeListeners(flag);    
   }
    
   public void Recolor(){
       p.ReColor();
   }
    public void DeleteSelected(){
         p.Delete();
    }
    public void setColor(Color color) {
        this.color = color;
    }
    
    public void REpaint(){
        p.repaint();
    }
    


    public static void main(String[] args) {
        
        Runnable r = new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(
                            UIManager.getSystemLookAndFeelClassName());
                } catch (Exception e) {
                    // use default
                }
                test2 bp = new test2();
                setMenu menusetter = new setMenu(bp);
                bp.set_menuSetter(menusetter);
                JFrame f = new JFrame("ok paint!");
                f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                f.setLocationByPlatform(true);

                f.setContentPane(bp.getGui());
                f.setJMenuBar(menusetter.getMenuBar(false));

                f.pack();
                f.setSize(600,600);
                f.setVisible(true);
            }
        };
        SwingUtilities.invokeLater(r);
    }

   

}
