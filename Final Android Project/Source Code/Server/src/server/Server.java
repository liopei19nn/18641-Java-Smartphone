package server;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 
 * @team Three Threads
 * @author Li Pei
 * @AndrewID : lip
 * 
 */
public class Server implements ServerConstants{
	// server socket define
	private ServerSocket serverSocket = null;
	
	public Server(){
		try{
			// server socket listened on 2333
			serverSocket = new ServerSocket(iDAYTIME_PORT);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		// Print when success
		System.out.println("Listening on port " + iDAYTIME_PORT);
	}
	
	public void runServer(){
		// socket communication handler
		DefaultSocketServer defaultSocketServer = null;
		
		try{
			while(true){
				// iterative thread socket communication handler
				Socket clientSocket = serverSocket.accept();
				defaultSocketServer = new DefaultSocketServer(clientSocket);
				defaultSocketServer.start();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
	}
	
	public static void main(String[] args){
		Server server = new Server();
		// start server
		server.runServer();
		
	}
	
	
	
}
