/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package redoUndo;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import shapes.shape;
import testgui.shape_node;
import java.util.LinkedList;
import shapes.Circle;
import shapes.Ellipse;
import shapes.Line;
import shapes.Rectanglee;
import shapes.Triangle;
import testgui.listOfShapes;

public class redoUndoController {

    private static int savemode = 0;
    private static LinkedList<String> redoUndoShapes = new LinkedList<>();
    private static LinkedList<Integer> redoUndoStats = new LinkedList<>();
    private static int curr = -1;
    private listOfShapes SHAPes = new listOfShapes();
	
    public void setMove(shape_node s , int stat) throws RuntimeException{
        //check the parameters
        if(stat != 1 && stat != 2 && stat != 3 && stat != 4)
            throw new RuntimeException("error in the state of the process, one for creation and two for edit and three for Delete");
    
        //remove the rest of the list
        int rz = redoUndoShapes.size();
        for(int i=curr+1;i<rz;i++){
            redoUndoShapes.removeLast();
            redoUndoStats.removeLast();
        }
        

        //add the step
        XStream xml = new XStream(new StaxDriver());
        redoUndoShapes.addLast(xml.toXML(s));
        
        //add the stat
        switch(stat){
            case 1:
                redoUndoStats.addLast(1);
                break;
            case 2:
                redoUndoStats.addLast(2);
                break;
            case 3:
                redoUndoStats.addLast(3);     
                break;
            case 4:
                redoUndoStats.addLast(4);
                break;
        }
        curr++;
        this.debug();
    }
    
    protected void debug(){
        for(int i=0;i<redoUndoShapes.size();i++)
        {
            System.out.println(redoUndoStats.get(i));
            System.out.println(redoUndoShapes.get(i).toString());
        }
                    System.out.println();

        /*
        LinkedList<shape_node> x = (LinkedList<shape_node>) SHAPes.getList();
        for(int i=0;i<x.size();i++){
            System.out.println(x.get(i).toString());
        }
        System.out.println();
        */
    }
    
    protected shape undo(){
        if(curr == -1)
            return null;
        
        //get the needed data
        XStream xml = new XStream(new StaxDriver());
        shape_node neededShape = (shape_node) xml.fromXML(redoUndoShapes.get(curr));
        int stat = redoUndoStats.get(curr);

        
        //modify the data
        switch(stat){
            case 1:
                SHAPes.removeFromListById(neededShape.getid());
                break;
            case 2:
                SHAPes.removeFromListById(neededShape.getid());
                SHAPes.addToList(neededShape);
            switch(neededShape.getshape().getClass().getName())
            {
                case "shapes.Line":
                    listOfShapes.lH.addLine((Line)neededShape.getshape(),neededShape.getid());
                break;
                case "shapes.Circle":
                    listOfShapes.CH.addCircle((Circle)neededShape.getshape(),neededShape.getid());
                break;
                case "shapes.Ellipse":
                    listOfShapes.EH.addEllipse((Ellipse)neededShape.getshape(),neededShape.getid());
                break;
                case "shapes.Rectanglee":
                    listOfShapes.RH.addRectangle((Rectanglee)neededShape.getshape(),neededShape.getid());
                break;
                case "shapes.Triangle":
                    listOfShapes.TH.addTriangle((Triangle)neededShape.getshape(),neededShape.getid());
                break;
            }
                break;
            case 4:
                curr--;
                neededShape = (shape_node) xml.fromXML(redoUndoShapes.get(curr));
                SHAPes.removeFromListById(neededShape.getid());
                SHAPes.addToList(neededShape);
                            switch(neededShape.getshape().getClass().getName())
            {
                case "shapes.Line":
                    listOfShapes.lH.addLine((Line)neededShape.getshape(),neededShape.getid());
                break;
                case "shapes.Circle":
                    listOfShapes.CH.addCircle((Circle)neededShape.getshape(),neededShape.getid());
                break;
                case "shapes.Ellipse":
                    listOfShapes.EH.addEllipse((Ellipse)neededShape.getshape(),neededShape.getid());
                break;
                case "shapes.Rectanglee":
                    listOfShapes.RH.addRectangle((Rectanglee)neededShape.getshape(),neededShape.getid());
                break;
                case "shapes.Triangle":
                    listOfShapes.TH.addTriangle((Triangle)neededShape.getshape(),neededShape.getid());
                break;
            }
                break;
            case 3:
                SHAPes.addToList(neededShape);
                            switch(neededShape.getshape().getClass().getName())
            {
                case "shapes.Line":
                    listOfShapes.lH.addLine((Line)neededShape.getshape(),neededShape.getid());
                break;
                case "shapes.Circle":
                    listOfShapes.CH.addCircle((Circle)neededShape.getshape(),neededShape.getid());
                break;
                case "shapes.Ellipse":
                    listOfShapes.EH.addEllipse((Ellipse)neededShape.getshape(),neededShape.getid());
                break;
                case "shapes.Rectanglee":
                    listOfShapes.RH.addRectangle((Rectanglee)neededShape.getshape(),neededShape.getid());
                break;
                case "shapes.Triangle":
                    listOfShapes.TH.addTriangle((Triangle)neededShape.getshape(),neededShape.getid());
                break;
            }
                break;                
        }   
	
        //remove and modify the rest of the data
        curr--;
        
	return null;
    }
  
    //in case of return a non null object you have to add this element to the linked list of the shapes in main linked list
    protected shape redo(){
        if(curr+1 == redoUndoShapes.size())
            return null;
        curr++;
        
        //get the needed data
        XStream xml = new XStream(new StaxDriver());
        shape_node neededShape = (shape_node) xml.fromXML(redoUndoShapes.get(curr));
        int stat = redoUndoStats.get(curr);
        
        //modify the data
        switch(stat){
            case 1:
                SHAPes.addToList(neededShape);
                            switch(neededShape.getshape().getClass().getName())
            {
                case "shapes.Line":
                    listOfShapes.lH.addLine((Line)neededShape.getshape(),neededShape.getid());
                break;
                case "shapes.Circle":
                    listOfShapes.CH.addCircle((Circle)neededShape.getshape(),neededShape.getid());
                break;
                case "shapes.Ellipse":
                    listOfShapes.EH.addEllipse((Ellipse)neededShape.getshape(),neededShape.getid());
                break;
                case "shapes.Rectanglee":
                    listOfShapes.RH.addRectangle((Rectanglee)neededShape.getshape(),neededShape.getid());
                break;
                case "shapes.Triangle":
                    listOfShapes.TH.addTriangle((Triangle)neededShape.getshape(),neededShape.getid());
                break;
            }
                break;                
            case 2:
                curr++;
                neededShape = (shape_node) xml.fromXML(redoUndoShapes.get(curr));
                SHAPes.removeFromListById(neededShape.getid());
                SHAPes.addToList(neededShape);
                            switch(neededShape.getshape().getClass().getName())
            {
                case "shapes.Line":
                    listOfShapes.lH.addLine((Line)neededShape.getshape(),neededShape.getid());
                break;
                case "shapes.Circle":
                    listOfShapes.CH.addCircle((Circle)neededShape.getshape(),neededShape.getid());
                break;
                case "shapes.Ellipse":
                    listOfShapes.EH.addEllipse((Ellipse)neededShape.getshape(),neededShape.getid());
                break;
                case "shapes.Rectanglee":
                    listOfShapes.RH.addRectangle((Rectanglee)neededShape.getshape(),neededShape.getid());
                break;
                case "shapes.Triangle":
                    listOfShapes.TH.addTriangle((Triangle)neededShape.getshape(),neededShape.getid());
                break;
            }
                break;
            case 4:
                SHAPes.removeFromListById(neededShape.getid());
                SHAPes.addToList(neededShape);
                            switch(neededShape.getshape().getClass().getName())
            {
                case "shapes.Line":
                    listOfShapes.lH.addLine((Line)neededShape.getshape(),neededShape.getid());
                break;
                case "shapes.Circle":
                    listOfShapes.CH.addCircle((Circle)neededShape.getshape(),neededShape.getid());
                break;
                case "shapes.Ellipse":
                    listOfShapes.EH.addEllipse((Ellipse)neededShape.getshape(),neededShape.getid());
                break;
                case "shapes.Rectanglee":
                    listOfShapes.RH.addRectangle((Rectanglee)neededShape.getshape(),neededShape.getid());
                break;
                case "shapes.Triangle":
                    listOfShapes.TH.addTriangle((Triangle)neededShape.getshape(),neededShape.getid());
                break;
            }
                break;
            case 3:
                SHAPes.removeFromListById(neededShape.getid());
                break;
        }
        return null;
        
    }
    
    public static void main (String args[]){

    }
    
}