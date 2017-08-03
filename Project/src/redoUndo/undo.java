/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package redoUndo;
import shapes.shape;

/**
 *
 * @author Fares
 */
public class undo extends redoUndoModel{
    
    //get instance from the controlling class
    redoUndoController Ob = new redoUndoController();
    
    @Override
    public shape execute(){
        return Ob.undo();
    }
}
