/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package saveLoad;

import com.thoughtworks.xstream.*;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
import com.thoughtworks.xstream.io.json.JettisonMappedXmlDriver;
import com.thoughtworks.xstream.io.json.JsonHierarchicalStreamDriver;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import testgui.listOfShapes;
import testgui.*;
import testgui.shape_node;
import shapes.*;
/**
 *
 * @author Fares
 */
public class saveLoadController {
    
    private int savemode = -1;
    private int loadmode = -1;
    listOfShapes SHAPes = new listOfShapes();
    
    //accept one for JSON and two for XML
    protected void setSaveMode(int x) throws RuntimeException{
        //che for the right parameters
        if(x == 1 || x == 2)
            this.savemode = x;
        else
            throw new RuntimeException("Error in the save type, one for json and two for xml");
    }
    
    
    protected void save(String location) throws RuntimeException, IOException{
        //if we didn't specify the save fromat
        if(savemode == -1)
            throw new RuntimeException("Error in the save type, one for json and two for xml");
        
        //save the file in the wantes format
        if(savemode == 1){
            this.saveJSON(location);
            savemode = -1;
        }
        else{
            this.saveXML(location);
            savemode = -1;
        }
    }
    
    private void saveJSON(String location) throws IOException{
	
        LinkedList<shape_node> fullList = (LinkedList<shape_node>) SHAPes.getList();	//list of all shapes

        XStream JSON = new XStream(new JettisonMappedXmlDriver());	//start the xml parser
	JSON.setMode(XStream.NO_REFERENCES);

        System.out.println(JSON.toXML(fullList));    //output the fullList to xml

	//write the data to the file
        try (FileWriter file = new FileWriter(location)) {
            file.write(JSON.toXML(fullList));
            System.out.println("Successfully Copied JSON Object to File...");
	}
	
	/*
                LinkedList<shape_node> fullList = (LinkedList<shape_node>) SHAPes.getList();
               
        	@SuppressWarnings("unchecked")
		JSONObject obj = new JSONObject();
                JSONArray shapes = new JSONArray();

                for(shape_node x : fullList){
                    
                    JSONArray arr = new JSONArray();
                    
                    //information for shape_node
                    arr.add(x.getshape().getClass().getName());
                    arr.add(x.getid());
                    arr.add(x.getS());

                    //information for get_all for the shape
                    LinkedList<Object> specs = (LinkedList<Object>) x.getshape().getAll();
                    for(Object y : specs){
                        arr.add(y);
                    }
                    
                    shapes.add(arr);
                }
		obj.put("shapes",shapes);

		// try-with-resources statement based on post comment below :)
		try (FileWriter file = new FileWriter(location)) {
			file.write(obj.toJSONString());
		    System.out.println("Successfully Copied JSON Object to File...");
			System.out.println("\nJSON Object: " + obj);
		}
	*/
    }
    
    private void saveXML(String location) throws IOException{
	
        LinkedList<shape_node> fullList = (LinkedList<shape_node>) SHAPes.getList();	//list of all shapes
	
        XStream xml = new XStream(new StaxDriver());	//start the xml parser

	//write the data to the file
        try (FileWriter file = new FileWriter(location)) {
            file.write(xml.toXML(fullList));
            System.out.println("Successfully Copied XML Object to File...");
	}
    }
    
    //accept one for JSON and two for XML
    protected void setLoadMode(int x) throws RuntimeException{
        //che for the right parameters
        if(x == 1 || x == 2)
            this.loadmode = x;
        else
            throw new RuntimeException("Error in the load type, one for json and two for xml");
    }
    
    protected void load(String location) throws RuntimeException, IOException, ParseException, FileNotFoundException{
        
        //if we didn't specify the load fromat
        if(loadmode == -1)
            throw new RuntimeException("Error in the load type, one for json and two for xml");
        
        //Load the file from the desk with the wanted format
        if(loadmode == 1){
            this.loadJSON(location);
            loadmode = -1;
        }
        else{
            this.loadXML(location);
            loadmode = -1;
        }
    }
    
    private void loadXML(String location) throws FileNotFoundException{
        this.clear();
        XStream xml = new XStream(new StaxDriver());	//start the xml parser
	String loadedXml = new String();
	    
	Scanner in = new Scanner(new File(location));
	while(in.hasNext())
	    loadedXml += in.next()+" ";
	
        LinkedList<shape_node> loadedList = (LinkedList<shape_node>) xml.fromXML(loadedXml);  //load

	for(int i=0;i<loadedList.size();i++)
	{
	    SHAPes.addToList(loadedList.get(i));
            System.out.println(loadedList.get(i).getClass().getName());
            switch(loadedList.get(i).getshape().getClass().getName())
            {
                case "shapes.Line":
                    listOfShapes.lH.addLine((Line)loadedList.get(i).getshape(),loadedList.get(i).getid());
                break;
                case "shapes.Circle":
                    listOfShapes.CH.addCircle((Circle)loadedList.get(i).getshape(),loadedList.get(i).getid());
                break;
                case "shapes.Ellipse":
                    listOfShapes.EH.addEllipse((Ellipse)loadedList.get(i).getshape(),loadedList.get(i).getid());
                break;
                case "shapes.Rectanglee":
                    listOfShapes.RH.addRectangle((Rectanglee)loadedList.get(i).getshape(),loadedList.get(i).getid());
                break;
                case "shapes.Triangle":
                    listOfShapes.TH.addTriangle((Triangle)loadedList.get(i).getshape(),loadedList.get(i).getid());
                break;
            }
        }
	
    }
    
    private void clear(){
        SHAPes.removeListAll();
        listOfShapes.CH.clearAll();
        listOfShapes.EH.clearAll();
        listOfShapes.TH.clearAll();
        listOfShapes.lH.clearAll();
        listOfShapes.RH.clearAll();
        
    }
    
    private void loadJSON(String location) throws ParseException, IOException, FileNotFoundException{
                this.clear();
        XStream JSON = new XStream(new JettisonMappedXmlDriver());	//start the JSON parser
	String loadedJSON = new String();
	    
	Scanner in = new Scanner(new File(location));
	while(in.hasNext())
	    loadedJSON += in.next();
	
	System.out.println(loadedJSON);
	
        LinkedList<Object> loadedList = (LinkedList<Object>) JSON.fromXML(loadedJSON);  //load
	LinkedList<shape_node> x = (LinkedList<shape_node>) loadedList.get(0);
	for(shape_node yy : x){
            switch(yy.getshape().getClass().getName())
            {
                case "shapes.Line":
                    listOfShapes.lH.addLine((Line)yy.getshape(),yy.getid());
                break;
                case "shapes.Circle":
                    listOfShapes.CH.addCircle((Circle)yy.getshape(),yy.getid());
                break;
                case "shapes.Ellipse":
                    listOfShapes.EH.addEllipse((Ellipse)yy.getshape(),yy.getid());
                break;
                case "shapes.Rectanglee":
                    listOfShapes.RH.addRectangle((Rectanglee)yy.getshape(),yy.getid());
                break;
                case "shapes.Triangle":
                    listOfShapes.TH.addTriangle((Triangle)yy.getshape(),yy.getid());
                break;
            }
	    SHAPes.addToList(yy);
	}
	/*	
	JSONParser parser = new JSONParser();

		Object obj = parser.parse(new FileReader(location));

		JSONObject jsonObject = (JSONObject) obj;

		// loop array
		JSONArray allShapes = (JSONArray) jsonObject.get("shapes"); //iterate on every shape
		Iterator<Object> iterator = allShapes.iterator();   //the iterator
                
                
		while (iterator.hasNext()) {
                
                    JSONArray shape = (JSONArray) iterator.next();  //get the array including the shape attributes
                    Iterator<Object> iterator2 = shape.iterator();  //the iterator through the shape attributes
                    String classname = (String) iterator2.next();   //the name of the shape class
                    long id = (long) iterator2.next();                //the id of the shape
                    double s = (double) iterator2.next();           //the s of the shape
                    
                    LinkedList<Object> specs = new LinkedList<>();  //the linked list containg the attributes which will be send to set all                    
                    while(iterator2.hasNext()){
                        Object x = iterator2.next();
                        specs.addLast(x);
                    }
                    
                    System.out.println(specs.toString());
		}
	*/
    }
    
    public static void main(String[] args) {
        /*
        listOfShapes SHAPes2 = new listOfShapes();
        shape sh1 = new Line(100.0,200.0);
        shape_node s_n1 = new shape_node(sh1,1);
        SHAPes2.addToList(s_n1);
        
        shape sh2 = new Line(200.0,300.0);
        shape_node s_n2 = new shape_node(sh2,2);
        SHAPes2.addToList(s_n2);
        
        saveLoadController x = new saveLoadController();

        x.setSaveMode(1);
        try {
            x.save("try.JSON");
        } catch (RuntimeException ex) {
            Logger.getLogger(saveLoadController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(saveLoadController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        
        x.setLoadMode(1);
        try {
            x.load("try.JSON");
        } catch (RuntimeException ex) {
            Logger.getLogger(saveLoadController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(saveLoadController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(saveLoadController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (org.json.simple.parser.ParseException ex) {
            Logger.getLogger(saveLoadController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        x.setSaveMode(2);
        try {
            x.save("try.xml");
        } catch (RuntimeException ex) {
            Logger.getLogger(saveLoadController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(saveLoadController.class.getName()).log(Level.SEVERE, null, ex);
        }
	
	x.setLoadMode(2);
	try {
            x.load("try.xml");
        } catch (RuntimeException ex) {
            Logger.getLogger(saveLoadController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(saveLoadController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(saveLoadController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (org.json.simple.parser.ParseException ex) {
            Logger.getLogger(saveLoadController.class.getName()).log(Level.SEVERE, null, ex);
        }
    
*/}
    
}
