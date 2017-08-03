/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package redoUndo;

import shapes.shape;
import testgui.*;

/**
 *
 * @author Fares
 */
public class move {

    //get instance from the controlling class
    redoUndoController Ob = new redoUndoController();
    
    public void set(shape_node s , int stat) throws RuntimeException{
        Ob.setMove(s, stat);
    }
}
