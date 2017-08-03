/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package saveLoad;

import java.io.IOException;

/**
 *
 * @author Fares
 */
public class save extends saveLoadModel{
    
    saveLoadController Ob = new saveLoadController();
    
    @Override
    public void execute(String location) throws RuntimeException, IOException{
        Ob.save(location);
    }
    
    @Override
    public void setMode(int x) throws RuntimeException{
        Ob.setSaveMode(x);
    }


}
