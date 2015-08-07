/**
 * 
 */
package server;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Properties;

/**
 * @author Li Pei
 * @andrew_id lip
 */

public class DefaultSocketServer extends Thread implements
		SocketServerInterface, SocketServerConstants {

	private int iPort;
	private Socket clientSocket = null;

	private ObjectInputStream objectInputStream = null;
	private ObjectOutputStream objectOutputStream = null;

	public DefaultSocketServer(Socket clientSocket) {
		this.clientSocket = clientSocket;
		this.iPort = iDAYTIME_PORT;
	}

	// run
	public void run() {
		if (openConnection()) {
			handleSession();
			// closeSession();
		}
	}

	public boolean openConnection() {
		try {
			objectOutputStream = new ObjectOutputStream(
					clientSocket.getOutputStream());
			objectInputStream = new ObjectInputStream(clientSocket.getInputStream());
		} catch (Exception e) {
			if (DEBUG)
				System.err.println("Unable to obtain stream to/from Echo Port"
						+ iPort);
			return false;
		}
		return true;
	}


	public void handleSession() {
		String strInput;
		String strOutput;
		if (DEBUG)
			System.out.println("Handling Session in" + iPort);
		
		// 0.0 display the successful connection 
		strOutput = "connect to " + iPort;
		try {
			objectOutputStream.writeObject(strOutput);
		} catch (IOException e) {
			e.printStackTrace();
		}
		BuildCarModelOptions buildCarModelOptions = new BuildCarModelOptions();
		
		
		while(true){// outer while
			try {
				// 0.1 get option
				strInput = (String)objectInputStream.readObject();
			} catch (ClassNotFoundException | IOException e) {
				continue;
			}
			
			if (strInput.equals("0")){
				// 0.1 get quit here
				break;
			} else if(strInput.equals("1") || strInput.equals("2")){
				
			}else{
				System.out.println("Illegal Input");
				continue;
			}
			
			
			
			// inner while
			while(true){
				
				if (strInput.equals("1")) {
					// 1.1 send get request reply
					strOutput = "Get upload automobile request, Please input the .properties file number";
					try {
						objectOutputStream.writeObject(strOutput);
					} catch (IOException e) {
						e.printStackTrace();
					}
					
					
					// 1.2 get file name and choose what to do 
					String fileName = null;
					try {
						fileName = (String)objectInputStream.readObject();
						// 1.3 write back the file name
						String output = "Get File Name: " + fileName;
						objectOutputStream.writeObject(output);
					} catch (ClassNotFoundException | IOException e) {
//						e.printStackTrace();
						fileName = "null";
					}
					
					
					
					
					// 1 upload .Properties file
					if (fileName.matches("[a-zA-Z0-9]+.Properties")) {
						// 1.4 read in a properties and build car
						Properties prop = null;
						try {
							prop = (Properties) objectInputStream.readObject();
						} catch (ClassNotFoundException | IOException e1) {
							e1.printStackTrace();
						}
						// add car in linked hash map
						buildCarModelOptions.buildAutoWithProperty(prop);
						
						// 1.5 reply build automobile successful
						try {
							strOutput = "build Auto successfully";
							objectOutputStream.writeObject(strOutput);
						} catch (IOException e) {
							e.printStackTrace();
						}
						
						
						break;
						
					} // 1 upload .Properties file
					
					else{ // 1 upload .txt file
						
						
						try {
							File file = new File(fileName);
							FileOutputStream fileOutputStream = new FileOutputStream(file);
							BufferedInputStream bufferedInputStream = new BufferedInputStream(clientSocket.getInputStream());
							
							byte[] buf = new byte[10240];
							int len = 0;
							while ((len = bufferedInputStream.read(buf, 0, 10240)) > 0) {
								// 1.4 read file from client and write it in file
								fileOutputStream.write(buf, 0, len);
								fileOutputStream.flush();
								break;
							}
							fileOutputStream.close();

							
						} catch (IOException e) {
							e.printStackTrace();
						}
						// read the file back
						buildCarModelOptions.buildAutoWithTxt(fileName);
						
						// delete the file in server end
						File file = new File(fileName);
						file.delete();
						
						// 1.5 reply build automobile successful
						strOutput = "Build Car From Txt Successfuly";
						try {
							objectOutputStream.writeObject(strOutput);
						} catch (IOException e) {
							e.printStackTrace();
						}

						
						break;
					} // 1 upload .txt file
					
					
				} // 1 upload
				
				else {//2 if you get configure request 
					// 2.1 send get request reply
					strOutput = "Get configure request";
					try {
						objectOutputStream.writeObject(strOutput);
					} catch (IOException e) {
						e.printStackTrace();
					}
					
					// 2.2 get all auto name in server and send back
					ArrayList<String> autoNameList = buildCarModelOptions.getAllModelList();
					try {
						objectOutputStream.writeObject(autoNameList);
					} catch (IOException e) {
						e.printStackTrace();
					}
					//2.3 send the saved auto list to client
					strOutput = "transfer AutoList successfully";
					try {
						objectOutputStream.writeObject(strOutput);
					} catch (IOException e) {
						e.printStackTrace();
					}
					// 2.4 if no saved car, break as the client
					if (autoNameList.size() == 0) {
						break;
					}
					
					// 2.5 get model name from server
					String modelName = null;
					try {
						modelName = (String)objectInputStream.readObject();
					} catch (ClassNotFoundException | IOException e) {
//						e.printStackTrace();
					}
					
					// 2.6 send the selected auto mobile to client
					try {
						buildCarModelOptions.sendSelectedModel(objectOutputStream, modelName);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					// 2.7 send the successful reply to client
					
					strOutput = "Transfer automobile succesfully";
					try {
						objectOutputStream.writeObject(strOutput);
					} catch (IOException e) {
//						e.printStackTrace();
					}
			
					break;
				}// 2 configure
				
			} // inner while
		}// outer while
	}// handle session

	public void closeSession() {
		try {
			clientSocket.close();
			
		} catch (IOException e) {
			if (DEBUG)
				System.err.println("Error closing socket");
		}
	}
}
