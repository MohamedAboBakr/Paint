/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shapes;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

/**
 *
 * @author N0007
 */
public class u {
    // Create a File object on the root of the directory containing the class file  
public Class load(String directory , String name) throws MalformedURLException, ClassNotFoundException, InstantiationException, IllegalAccessException{
   // File file = new File("E:\\Paint\\test2.Class");
  directory = directory.replace("\\", "");
   File file = new File(directory);
   name = name.replace(".class", "");
    // Convert File to a URL
    URL url; 
    try{        
      url = file.toURI().toURL();           
    URL[] urls = new URL[]{url};
    // Create a new class loader with the directory
    URLClassLoader cl = new URLClassLoader(urls);
     // Load in the class; MyClass.class should be located in
    // the directory file:/c:/myclasses/com/mycompany
   return cl.loadClass("shapes." + name); // name of class without (.class) & pkgtry is my package ya fady
    }catch(ClassNotFoundException e){
        e.printStackTrace();
    }
    return null;
}
}


