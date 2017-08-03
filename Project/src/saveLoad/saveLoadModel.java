/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package saveLoad;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;

/**
 *
 * @author Fares
 */
public abstract class saveLoadModel {
    
    //override in save or load
    protected abstract void execute(String location) throws RuntimeException, IOException, FileNotFoundException, ParseException;
    //set the mode for save or load, 1 for JSON , 2 for xml
    protected abstract void setMode(int x) throws RuntimeException;
}
