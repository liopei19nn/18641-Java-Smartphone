/**
 * 
 */
package server;

/**
 * @author Li Pei
 * @andrew_id lip 
 */
public interface SocketServerInterface {
	boolean openConnection(); // open connection
    void handleSession();	// handle session
    void closeSession();		// close session
}
