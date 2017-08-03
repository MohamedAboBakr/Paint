/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testgui;

import java.util.LinkedList;
import java.util.List;
import listeners.CircleHandler;
import listeners.EllipseHandler;
import listeners.LineHandler;
import listeners.PointHandler;
import listeners.RectangleHandler;
import listeners.TriangleHandler;

/**
 *
 * @author Fares
 */
public class listOfShapes {
    
    private static List<shape_node> SHAPes = new LinkedList<shape_node>();
    
    public static LineHandler lH;
    public static CircleHandler CH ;
    public static PointHandler PH ;
    public static RectangleHandler RH ;
    public static EllipseHandler EH ;
    public static TriangleHandler TH ;
    
    
   
    public void addToList(shape_node x){
        SHAPes.add(x);
    }
    
    public void removeFromListById(int id){
        for(shape_node nody : SHAPes){
            if(nody.getid() == id){
                SHAPes.remove(nody);
                break;
            }
        }
    }
    
    public void removeFromListByIndex(int index){
        SHAPes.remove(index);
    }
    
    public void removeListAll(){
        SHAPes.clear();
    }

    public List<shape_node> getList(){
        return SHAPes;
    }
}
