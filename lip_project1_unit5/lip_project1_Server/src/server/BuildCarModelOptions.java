/**
 * 
 */
package server;
import java.io.*;
import java.util.*;
import adapter.BuildAuto;

/**
 * @author Li Pei
 * @andrew_id lip 
 */
public class BuildCarModelOptions implements AutoServer{
	// 1 accept properties object from client socket over an object stream and create an antomotile
	// 2 add that created Automobile to the LinkedHashMap
	// 3 AutoServer interface should be implemented in BuildAuto and BuildCarModelOptions classes
	
	// static buildAuto object for saving car model and operate the linkedhashmap of automobile
	private static BuildAuto buildAuto;
	
	// constructor
	public BuildCarModelOptions(){
		buildAuto = new BuildAuto();
	}
	
	
	// build auto with .Properties file
	public void buildAutoWithProperty(Properties carProperties){
		
		buildAuto.buildAutoWithProperty(carProperties);
		
	}
	
	// build auto with .txt file
	public void buildAutoWithTxt(String fileName) {
		buildAuto.buildAutoWithTxt(fileName);
		
	}

	// return all available car model in server
	public ArrayList<String> getAllModelList(){
		ArrayList<String> autoNameList = buildAuto.getAllModelList();
		return autoNameList;
	}
	
	// send the selected model to client
	public void sendSelectedModel(ObjectOutputStream objectOutputStream, String modelName) throws IOException{
		
		buildAuto.sendSelectedModel(objectOutputStream, modelName);
	}


	
	

}
