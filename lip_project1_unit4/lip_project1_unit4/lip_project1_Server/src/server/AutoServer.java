/**
 * 
 */
package server;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Properties;

/**
 * @author Li Pei
 * @andrew_id lip 
 */
public interface AutoServer {
	// build a automobile from .Properties file
	public void buildAutoWithProperty(Properties carProperties);
	// build a automobile from .txt file
	public void buildAutoWithTxt(String fileName);
	// return all available automobile in LinkedHashMap
	public ArrayList<String> getAllModelList();
	// return selected model to client
	public void sendSelectedModel(ObjectOutputStream objectOutputStream, String modelName) throws IOException;
	
}
