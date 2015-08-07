package client;

import java.net.*;
import java.util.*;
import java.io.*;
import util.FileIO;
import model.Automobile;

public class SocketClient extends Thread implements SocketClientInterface,
		SocketClientConstants {

	private Socket clientSocket;// client socket
	private String strHost;//server IP
	private int iPort;// socket port for this service 
	
	ObjectOutputStream objectOutputStream = null; // for all output 
	ObjectInputStream objectInputStream = null; // for all input
	

	public SocketClient(String strHost, int iPort) {
		setPort(iPort);
		setHost(strHost);
	}

	public void setHost(String strHost) {
		this.strHost = strHost;
	}

	public void setPort(int iPort) {
		this.iPort = iPort;
	}

	// run
	public void run() {
		if (openConnection()) {
			handleSession();
			closeSession();
		}
	}
	/*
	 * openConnection()
	 * 
	 * open input and output stream and client socket
	 * 
	 * */
	public boolean openConnection() {
		try {
			clientSocket = new Socket(strHost, iPort); // connected to server
		} catch (IOException socketError) {
			if (DEBUG)
				System.err.println("Unable to connect to " + strHost);
			return false;
		}

		try {
			// open input and output stream
			objectInputStream = new ObjectInputStream(
					clientSocket.getInputStream());
			objectOutputStream = new ObjectOutputStream(
					clientSocket.getOutputStream());

		} catch (Exception e) {
			if (DEBUG)
				System.err
						.println("Unable to obtain stream to/from " + strHost);
			return false;
		}
		return true;
	}

	@SuppressWarnings("unchecked")
	public void handleSession() {
		String fromServer = "";
		String fromUser = "";
		BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));

		if (DEBUG)
			System.out
					.println("Handling session with " + strHost + ":" + iPort);

		// 0 display the successful connection
		try {
			fromServer = (String) objectInputStream.readObject();
			System.out.println("Server: " + fromServer);
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
		while (true) {// outer while

			// print list for what you could do
			printWhatToDoList();

			// input what you could do and transfer to server
			try {
				fromUser = stdIn.readLine();
			} catch (IOException e) {
				continue;
			}

			if (fromUser.equals("0")) {
				// 0.1 sent your quit to server
				try {
					objectOutputStream.writeObject(fromUser);
				} catch (IOException e) {
					e.printStackTrace();
				}
				break;
			} else if (fromUser.equals("1") || fromUser.equals("2")) {

			} else {
				System.out.println("Illegal Input");
				continue;
			}

			// 0.1 sent your option to server
			try {
				objectOutputStream.writeObject(fromUser);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			
			// below is the real handler for session, 
			// there are 2 types of operation you could do below
			// -- 1 upload a file of car (.txt or .Properties)
			// -- 2 Ask for a car to configure in client
			
			while (true) {// inner while
				
				if (fromUser.equals("1")) { // if upload
					try {
						// 1.1 get successful operation reply
						fromServer = (String) objectInputStream.readObject();
						System.out.println("Server: " + fromServer);
					} catch (ClassNotFoundException | IOException e) {
						e.printStackTrace();
					}
					// get file name and choose function to build car in server
					String[] autoFileList = printFileList();
					try {
						fromUser = stdIn.readLine();
						while (!fromUser.matches("[0-9]+")
								|| Integer.parseInt(fromUser) < 0
								|| Integer.parseInt(fromUser) > autoFileList.length - 1) {
							System.out.println("please input a legal number");
							fromUser = stdIn.readLine();
						}
					} catch (IOException e1) {
						e1.printStackTrace();
					}

					int fileIndex = Integer.parseInt(fromUser);

					String fileName = autoFileList[fileIndex];
					// 1.2 send the file name to server
					try {
						objectOutputStream.writeObject(fileName);
						// 1.3 get file name received reply
						fromServer = (String) objectInputStream.readObject();
						System.out.println("Server: " + fromServer);
					} catch (IOException | ClassNotFoundException e) {
						e.printStackTrace();
					}
					
					// below is the handler for 2 situation
					// -- 1 input a .Properties file
					// -- 2 input a .txt file
					

					// if upload .Properties
					if (fileName.matches("[a-zA-Z0-9]+.Properties")) {
						Properties prop = new Properties();

						try {
							// upload the car file in client
							FileInputStream fileInputStream = new FileInputStream(
									fileName);
							prop.load(fileInputStream);
							fileInputStream.close();
							// 1.4 write the properties object to server
							objectOutputStream.writeObject(prop);

						} catch (IOException e) {
							e.printStackTrace();
						}

						// 1.5 get the success reply
						try {
							fromServer = (String) objectInputStream
									.readObject();
							System.out.println("Server: " + fromServer);
						} catch (ClassNotFoundException | IOException e) {
							e.printStackTrace();
						}

					} // if upload .Properties
					
					else {// if upload .txt

						try {
							FileInputStream fileInputStream = new FileInputStream(new File(fileName));
							BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(clientSocket.getOutputStream());
							byte[] buf = new byte[1024];
							int len = 0;
							
							// upload the car file in client
							while ((len = fileInputStream.read(buf, 0, 1024)) !=-1) {
								// 1.4 write the txt file to server
								bufferedOutputStream.write(buf,0,len);
								bufferedOutputStream.flush();
							}
							fileInputStream.close();
							
						} catch (IOException e) {
							e.printStackTrace();
						} 
						
						// 1.5 get the success reply
						try {
							fromServer = (String) objectInputStream.readObject();
						} catch (ClassNotFoundException | IOException e) {
							e.printStackTrace();
						}
						System.out.println(fromServer);
						
					}// if upload .txt

				} // if upload
				
				else {// "2" configure
					try {
						// 2.1 get successful operation reply
						fromServer = (String) objectInputStream.readObject();
						System.out.println("Server: " + fromServer);
					} catch (ClassNotFoundException | IOException e) {
						e.printStackTrace();
					}

					// 2.2 get auto name list from stream
					ArrayList<String> autoNameList = null;
					try {
						autoNameList = (ArrayList<String>) objectInputStream
								.readObject();
					} catch (ClassNotFoundException | IOException e) {
						e.printStackTrace();
					}

					// 2.3 get transfer name list back success reply
					try {
						fromServer = (String) objectInputStream.readObject();
						System.out.println("Server: " + fromServer);
					} catch (ClassNotFoundException | IOException e) {
						e.printStackTrace();
					}

					// 2.4 if no saved car, break as the client and please
					// upload a car first by any
					// terminal
					if (autoNameList.size() == 0) {
						System.out.println("Empty List in Server， please upload a car first");
						break;
					}

					// print all available list
					System.out.println("Auto Model Options :");
					for (int i = 0; i < autoNameList.size(); i++) {
						System.out.println("Model " + i + " : "
								+ autoNameList.get(i));
					}
					// choose a car to configure
					System.out.println("Select a number of model to configure");
					try {
						// read in a automobile name index
						fromUser = stdIn.readLine();
					} catch (IOException e) {
						e.printStackTrace();
					}

					while (!fromUser.matches("[0-9]+")
							|| Integer.parseInt(fromUser) < 0
							|| Integer.parseInt(fromUser) > autoNameList.size() - 1) {
						System.out.println("please input a legal number");
						try {
							fromUser = stdIn.readLine();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
					int configureAutoIndex = Integer.parseInt(fromUser);
					String modelName = autoNameList.get(configureAutoIndex);

					// 2.5 write the required model name to server
					try {
						objectOutputStream.writeObject(modelName);
					} catch (IOException e) {
						e.printStackTrace();
					}

					// 2.6 get selected automobile from server
					Automobile selectedAuto = null;
					try {
						selectedAuto = (Automobile) objectInputStream
								.readObject();
					} catch (ClassNotFoundException | IOException e) {
						e.printStackTrace();
					}

					// 2.7 get transfer successful reply
					try {
						fromServer = (String) objectInputStream.readObject();
						System.out.println("Server: " + fromServer);
					} catch (ClassNotFoundException | IOException e) {
						e.printStackTrace();
					}

					
					// start configure your car in SelectCarOptionClass
					System.out.println("Start Configure the Car");
					new SelectCarOption().configureCarChoice(selectedAuto);

					// get the configured car and print all your choice and price
					System.out.println("Configured Car For Your Choice");
					System.out.println(selectedAuto.getName());
					selectedAuto.printChoice();
					System.out.println();
				}
				
				break;

			}// inner while
		}// outer while

	}
	
	/*
	 * print what you could do in client
	 * */
	public void printWhatToDoList() {
		System.out.print("Input The Number What You Want To Do? : ");
		System.out.print("1.Upload\t");
		System.out.print("2.Configure\t");
		System.out.println("0.Quit");
	}
	
	/*
	 * print all available files in client
	 * */
	public String[] printFileList() {
		
		String[] list = new FileIO().getAutoFileList(automobileFileList);
		for (int i = 0; i < list.length; i++) {
			System.out.println(i + " " + list[i]);
		}
		return list;
	}
	
	/*
	 * closeSession()
	 * 
	 * close input and output stream and client socket
	 * 
	 * */

	public void closeSession() {
		try {
			clientSocket.close();
			objectOutputStream.close();
			objectInputStream.close();
			System.out.println("closed!");
		} catch (IOException e) {
			if (DEBUG)
				System.err.println("Error closing socket to " + strHost);
		}
	}

	public static void main(String arg[]) {
		String strLocalHost = "";
		// get local IP address
		try {
			strLocalHost = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			System.err.println("Unable to find local host");
		}
		// start this client
		SocketClient client = new SocketClient(strLocalHost, iDAYTIME_PORT);
		client.start();
	}
}// class SocketClient