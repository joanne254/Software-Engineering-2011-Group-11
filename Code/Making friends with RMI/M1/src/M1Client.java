/* RmiClient class
 * 
 * This is the client which gets the reference (a proxy) to the remote object
 * living on the server and invokes its method to get a message.
 * If the server object implemented java.io.Serializable instead of java.rmi.Remote,
 * it would be serialized and passed to the client as a value.
 */

import java.rmi.Naming;
import java.rmi.RMISecurityManager;

public class M1Client {

	// "server" is the reference of the remote object
	private M1ServerIntf server = null;
	
	public String getMessage() {
		
		try {
			// look for an instance of M1ServerIntf with the name chosen in the M1Server class
			server = (M1ServerIntf)Naming.lookup("//localhost/M1Server");
			return server.getMessage();
		} catch(Exception e) {
			System.err.println("RMI Client exception : "+e);
			e.printStackTrace();
			return e.getMessage();
		}
	}
	
	public static void main(String[] args) {
		
		// Create and install a security manager
		if(System.getSecurityManager() == null) {
			System.setSecurityManager(new RMISecurityManager());
		}
		
		// Instantiate an RMI Client
		M1Client new_client = new M1Client();
			
		System.out.println(new_client.getMessage());
	}

}
