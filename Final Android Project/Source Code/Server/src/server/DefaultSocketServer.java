package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;


import dblayout.DatabaseIO;
import model.DatabasePerson;

/**
 * 
 * @team Three Threads
 * @author Li Pei
 * @AndrewID : lip
 * 
 */
public class DefaultSocketServer extends Thread implements ServerConstants{
	
	// port and client socket
	private int iport;
	private Socket clientSocket = null;
	
	// I/O stream
	private ObjectOutputStream objectOutputStream = null;
	private ObjectInputStream objectInputStream = null;
	
	// socket 
	public DefaultSocketServer(Socket s) {
		this.clientSocket = s;
		this.iport = iDAYTIME_PORT;//PORT 2333
		
	}
	
	// run
	public void run() {
		if (openConnection()) {
			// when accept a thread, show this
			System.out.println("Start Connection Thread");
			handleSession();
			closeSession();
		}
	}


	private boolean openConnection() {
		
		try {
			
			// open I/O stream to connect client device
			objectOutputStream = new ObjectOutputStream(
					clientSocket.getOutputStream());
		
			objectInputStream = new ObjectInputStream(clientSocket.getInputStream());
			
		} catch (Exception e) {
			if (DEBUG)
				System.err.println("Unable to obtain stream to/from Echo Port"
						+ iport);
			return false;
		}
		return true;
	}



	private void handleSession() {
		String strOutput = "";
		String strInput = "";
		strOutput = "connect to " + iport;
		
		
		// 0.0 write a connect to string start dialog
		try {
			objectOutputStream.writeObject(strOutput);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		while(true){// outer while
			
			//0.1 read in an option
			try {
				strInput = (String)objectInputStream.readObject();
				System.out.println(strInput);
			} catch (ClassNotFoundException|IOException e) {	
				e.printStackTrace();
			}
			
			String option = strInput;
			
			if (option.equals("getall")) {
				ArrayList<String> persons  = DatabaseIO.selectallfromdatabase();
				// if database is empty, write to client an ArrayList which length is 0
				
				// 1.0 write all users to client
				try {
					objectOutputStream.writeObject(persons);
					objectOutputStream.flush();
				} catch (IOException e) {
					e.printStackTrace();
				}
			} // end of get  all
			
			
			
			else if(option.equals("getone")){
				// 2.0 read a user name from client
				try {
					String username = (String)objectInputStream.readObject();
					
					String returnresult = DatabaseIO.selectonefromdatabase(username);
					
					// if input username is null or length is 0
					// return a string ""
					if (returnresult == null || returnresult.length() == 0) {
						returnresult = "";
						
					}
					
					// 2.1 write a string back
					objectOutputStream.writeObject(returnresult);
					
				} catch (ClassNotFoundException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				
			} // end of get one
			
			else if(option.equals("addone")){
				
				String userinfo = null;
				
				// 3.1 read in the person
				try {
					 userinfo = (String)objectInputStream.readObject();
				} catch (ClassNotFoundException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				// add the DatabasePerson into database
				DatabasePerson saveperson = new DatabasePerson();
				saveperson.generateFromString(userinfo);
				
				DatabaseIO.addonetodatabase(saveperson);
				
			}// end of add one
			
			

			
			//0.2 sentence to show the end of dialog thread
			try {
				strInput = (String)objectInputStream.readObject();
				System.out.println(strInput);
			} catch (ClassNotFoundException|IOException e) {	
				e.printStackTrace();
			}
			if (strInput.equals("finish")) {
				break;
			}
		
		
		
		}
		
		
	}


	// after connection, close stream and  socket
	public void closeSession() {
		try {
			objectInputStream.close();
			objectInputStream.close();
			clientSocket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
}
