/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testgui;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Stroke;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JToolBar;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import redoUndo.redo;
import redoUndo.undo;
import saveLoad.load;
import saveLoad.save;
import shapes.Circle;
import shapes.shape;
import shapes.u;



public class setMenu {
      private JToolBar tb = new JToolBar();
      private JButton colorButton = new JButton("Color");
      private JSpinner strokeSize ;
      private JLabel strokeLabel = new JLabel("Stroke");
      private JButton clearButton = new JButton("Clear");
      private   BufferedImage colorSample = new BufferedImage(
            16,16,BufferedImage.TYPE_INT_RGB);
      private final SpinnerNumberModel strokeModel = new SpinnerNumberModel(3,1,16,1);
      private  Stroke stroke = new BasicStroke(
            3,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND,1.7f);
       public Color color = Color.black;
       private JPanel panel ;
       private test2 workPanel ;
       
       public setMenu(test2 workPanel){
            this.workPanel = workPanel ;
       }
       public void set_Menu(JPanel panel){
           this.panel = panel ;
           addToolBar();
           addToolBarActions();
       }
       
       public Color getcolor(){
           return color;
       }
       
       private void addToolBar(){
            tb.setFloatable(false);
          //  colorButton.setMnemonic('o');
            colorButton.setToolTipText("Choose a Color");
            colorButton.setIcon(new ImageIcon(colorSample));
            tb.add(colorButton);
            strokeSize = new JSpinner(strokeModel);        
            strokeSize.setMaximumSize(strokeSize.getPreferredSize());
            strokeLabel.setLabelFor(strokeSize);
           // strokeLabel.setDisplayedMnemonic('t');
            tb.add(strokeLabel);
            tb.add(strokeSize);
            tb.addSeparator();
            tb.add(clearButton);
            panel.add(tb, BorderLayout.PAGE_START);

       }
       private void addToolBarActions(){
           ActionListener colorListener = new ActionListener() {
                @Override 
                public void actionPerformed(ActionEvent arg0) {
                    color = JColorChooser.showDialog(
                            panel, "Choose a color", color);
                            workPanel.Recolor();
                    if (color==null) {
                        color = Color.black;
                    }
                }

            };
      
            colorButton.addActionListener(colorListener);
            
            
            
                     
            ChangeListener strokeListener = new ChangeListener() {
                @Override
                public void stateChanged(ChangeEvent arg0) {
                    Object o = strokeModel.getValue();
                    Integer i = (Integer)o; 
                    stroke = new BasicStroke(
                            i.intValue(),
                            BasicStroke.CAP_ROUND,
                            BasicStroke.JOIN_ROUND,
                            1.7f);
                }
            };
            strokeSize.addChangeListener(strokeListener); 
            
            
            
            ActionListener clearListener = new ActionListener() {
                public void actionPerformed(ActionEvent arg0) {
                    int result = JOptionPane.OK_OPTION;
                        result = JOptionPane.showConfirmDialog(
                                panel, "Erase the current painting?");
                    
                    if (result==JOptionPane.OK_OPTION) {
                        // 
                    }
                }
            };
            clearButton.addActionListener(clearListener);

       }
       
       
       
       
    private JMenu getShapeMenu(boolean webstart){
        JMenu shapes = new JMenu("Shape");
        
        JMenuItem line =  new JMenuItem("Line");
        ActionListener LineListener = new ActionListener() {
                public void actionPerformed(ActionEvent arg0) {
                      System.out.println("button line done");
                      workPanel.callDraw(1);
                }
            };
        line.addActionListener(LineListener);  ///// bnt kalb 5ales 
        shapes.add(line);
        
        JMenuItem circle =  new JMenuItem("circle");
        ActionListener CircleListener = new ActionListener() {
                public void actionPerformed(ActionEvent arg0) {
                      System.out.println("button circle done");
                      workPanel.callDraw(2);
                }
            };
        circle.addActionListener(CircleListener);
        shapes.add(circle);
        
        
        JMenuItem rectangle =  new JMenuItem("Rectangle");
        ActionListener rectangleListener = new ActionListener() {
                public void actionPerformed(ActionEvent arg0) {
                      System.out.println("button rec done");
                      workPanel.callDraw(3);
                }
            };
        rectangle.addActionListener(rectangleListener);
        shapes.add(rectangle);
        
        JMenuItem ellipse =  new JMenuItem("Ellipse");
        ActionListener ellipseListener = new ActionListener() {
                public void actionPerformed(ActionEvent arg0) {
                      System.out.println("button ellipse done");
                      workPanel.callDraw(4);
                }
         };
        ellipse.addActionListener(ellipseListener);
        shapes.add(ellipse);
        
        
        
        JMenuItem triangle =  new JMenuItem("Triangle");
        ActionListener triangleListener = new ActionListener() {
                public void actionPerformed(ActionEvent arg0) {
                      System.out.println("button triangle done");
                      workPanel.callDraw(5);
                }
         };
        triangle.addActionListener(triangleListener);
        shapes.add(triangle);
        

        
        return shapes;
    }
           
   
    private JMenu getFileMenu(boolean webstart){
        JMenu file = new JMenu("File");
         file.setMnemonic('f');


        if (webstart) {
            //TODO Add open/save functionality using JNLP API
        } else {
            //TODO Add save functionality using J2SE API
            file.addSeparator();
            ActionListener openListener = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent arg0) {
                    
                        JFileChooser ch = getFileChooser();
                        int result = ch.showOpenDialog(panel);
                        if (result==JFileChooser.APPROVE_OPTION ) {
                    
                        }
    
                }
            };
          
            ActionListener deleteListener = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent arg0) {
                          workPanel.DeleteSelected();
                }
            };
            JMenuItem deleteItem = new JMenuItem("Delete");
            deleteItem.setMnemonic('d');
            deleteItem.addActionListener(deleteListener);
            file.add(deleteItem);
            
            ActionListener undoListener = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent arg0) {
                          new undo().execute();
                          workPanel.REpaint();
                }
            };
            JMenuItem undoItem = new JMenuItem("Undo");
            undoItem.setMnemonic('U');
            undoItem.addActionListener(undoListener);
            file.add(undoItem);
            
            
            
            
            
            ActionListener dynamicloadListener = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent arg0) {
                           JFileChooser ch = new JFileChooser();
                            ch.showOpenDialog(null);
                            String selectedFile = ch.getCurrentDirectory().toString();
                             String name = ch.getSelectedFile().getName();
                            u c = new u();
                            Class c1 = null;
                    try {
                        c1 = c.load(selectedFile, name);
                        
                    } catch (MalformedURLException ex) {
                        Logger.getLogger(setMenu.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(setMenu.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (InstantiationException ex) {
                        Logger.getLogger(setMenu.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IllegalAccessException ex) {
                        Logger.getLogger(setMenu.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    try {
                        shape cc = (shape)c1.newInstance();
                    } catch (InstantiationException ex) {
                        Logger.getLogger(setMenu.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IllegalAccessException ex) {
                        Logger.getLogger(setMenu.class.getName()).log(Level.SEVERE, null, ex);
                    }
                            
                }
            };
            JMenuItem dynamicloadItem = new JMenuItem("Include Shape");
            dynamicloadItem.setMnemonic('I');
            dynamicloadItem.addActionListener(dynamicloadListener);
            file.add(dynamicloadItem);
            
            
            ActionListener redoListener = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent arg0) {
                          new redo().execute();
                          workPanel.REpaint();
                }
            };
            JMenuItem redoItem = new JMenuItem("Redo");
            redoItem.setMnemonic('R');
            redoItem.addActionListener(redoListener);
            file.add(redoItem);
            
            
           ActionListener SaveXml = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent arg0) {
                            JFileChooser fileChooser = new JFileChooser();
                            int returnValue = fileChooser.showOpenDialog(null);
                            if (returnValue == JFileChooser.APPROVE_OPTION) {
                            File selectedFile = fileChooser.getSelectedFile();
                            String path = selectedFile.getAbsolutePath();
                            save sv = new save();
                            sv.setMode(2);
                                try {
                                    sv.execute(path);
                                } catch (RuntimeException ex) {
                                    Logger.getLogger(setMenu.class.getName()).log(Level.SEVERE, null, ex);
                                } catch (IOException ex) {
                                    Logger.getLogger(setMenu.class.getName()).log(Level.SEVERE, null, ex);
                                }
                          }
                }
            };
            JMenuItem xml = new JMenuItem("SaveXml");
            xml.setMnemonic('X');
            xml.addActionListener(SaveXml);
            file.add(xml);
            
             ActionListener SaveJson = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent arg0) {
                            JFileChooser fileChooser = new JFileChooser();
                            int returnValue = fileChooser.showOpenDialog(null);
                            if (returnValue == JFileChooser.APPROVE_OPTION) {
                            File selectedFile = fileChooser.getSelectedFile();
                            String path = selectedFile.getAbsolutePath();
                            save sv = new save();
                            sv.setMode(1);
                                try {
                                    sv.execute(path);
                                } catch (RuntimeException ex) {
                                    Logger.getLogger(setMenu.class.getName()).log(Level.SEVERE, null, ex);
                                } catch (IOException ex) {
                                    Logger.getLogger(setMenu.class.getName()).log(Level.SEVERE, null, ex);
                                }
                          }
                }
            };
            JMenuItem json = new JMenuItem("SaveJson");
            json.setMnemonic('J');

            json.addActionListener(SaveJson);
            file.add(json);
            
            
             ActionListener loadListner = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent arg0) {
                            JFileChooser fileChooser = new JFileChooser();
                            int returnValue = fileChooser.showOpenDialog(null);
                            if (returnValue == JFileChooser.APPROVE_OPTION) {
                            File selectedFile = fileChooser.getSelectedFile();
                            String path = selectedFile.getAbsolutePath();
                            load sv = new load();
                            sv.setMode(2);
                                try {
                                    sv.execute(path);
                                } catch (RuntimeException ex) {
                                    Logger.getLogger(setMenu.class.getName()).log(Level.SEVERE, null, ex);
                                } catch (IOException ex) {
                                    Logger.getLogger(setMenu.class.getName()).log(Level.SEVERE, null, ex);
                                } catch (ParseException ex) {
                                    Logger.getLogger(setMenu.class.getName()).log(Level.SEVERE, null, ex);
                                }
                          }
                }
            };
            JMenuItem loaditem = new JMenuItem("LoadXML");
            loaditem.setMnemonic('L');
            loaditem.addActionListener(loadListner);
            file.add(loaditem);  
            
            
             ActionListener loadJson = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent arg0) {
                            JFileChooser fileChooser = new JFileChooser();
                            int returnValue = fileChooser.showOpenDialog(null);
                            if (returnValue == JFileChooser.APPROVE_OPTION) {
                            File selectedFile = fileChooser.getSelectedFile();
                            String path = selectedFile.getAbsolutePath();
                            load sv = new load();
                            sv.setMode(1);
                                try {
                                    sv.execute(path);
                                } catch (RuntimeException ex) {
                                    Logger.getLogger(setMenu.class.getName()).log(Level.SEVERE, null, ex);
                                } catch (IOException ex) {
                                    Logger.getLogger(setMenu.class.getName()).log(Level.SEVERE, null, ex);
                                } catch (ParseException ex) {
                                    Logger.getLogger(setMenu.class.getName()).log(Level.SEVERE, null, ex);
                                }
                          }
                }
            };
            JMenuItem loadj = new JMenuItem("LoadJson");
            loadj.setMnemonic('L');
            loadj.addActionListener(loadJson);
            file.add(loadj); 
            
            

        }

        return file;
    }

    
    
    private void showError(Throwable t) {
        JOptionPane.showMessageDialog(
                panel, 
                t.getMessage(), 
                t.toString(), 
                JOptionPane.ERROR_MESSAGE);
    }

    JFileChooser chooser = null;

    public JFileChooser getFileChooser() {
        if (chooser==null) {
            chooser = new JFileChooser();
            FileFilter ff = new FileNameExtensionFilter("Image files", ImageIO.getReaderFileSuffixes());
            chooser.setFileFilter(ff);
        }
        return chooser;

    }

    public boolean canExit() {
        boolean canExit = false;
        SecurityManager sm = System.getSecurityManager();
        if (sm==null) {
            canExit = true;
        } else {
            try {
                sm.checkExit(0);
                canExit = true; 
            } catch(Exception stayFalse) {
            }
        }

        return canExit;
    }

    public JMenuBar getMenuBar(boolean webstart){
        JMenuBar mb = new JMenuBar();
        mb.add(this.getFileMenu(webstart));
        mb.add(this.getShapeMenu(webstart));
        return mb;
    }
}
