/**
 * 
 */
package client;

/**
 * @author Li Pei
 * @andrew_id lip 
 */
public interface SocketClientInterface {
	boolean openConnection(); // open socket
	void handleSession();	// handle session
	void closeSession();		// close this session
}
