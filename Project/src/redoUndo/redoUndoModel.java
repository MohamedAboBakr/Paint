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
public abstract class redoUndoModel {
    
    //execute either undo or redo for both class
    protected abstract shape execute();
    
}
