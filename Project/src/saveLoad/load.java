/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package saveLoad;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Fares
 */
public class load extends saveLoadModel{
    
    saveLoadController Ob = new saveLoadController();
    
    @Override
    public void execute(String location) throws RuntimeException, IOException, FileNotFoundException, ParseException{
            Ob.load(location);

    }
    
    @Override
    public void setMode(int x) throws RuntimeException{
        Ob.setLoadMode(x);
    }
}
