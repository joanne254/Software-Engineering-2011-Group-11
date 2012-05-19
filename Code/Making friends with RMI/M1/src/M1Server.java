/* RmiServer class
 * 
 * Listens to RMI requests and implements the interface which is used by the client
 * to invoke remote methods.
 */

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.RMISecurityManager;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.registry.*; 


@SuppressWarnings("serial")
public class M1Server extends UnicastRemoteObject 
	implements M1ServerIntf {
	
	// fields
	public static final String MESSAGE = "Hello world !";
	
	// exceptions
	public M1Server() throws RemoteException {
	}
	
	// methods
	public String getMessage() {
		return MESSAGE;
	}
	
	// MAIN
	public static void main(String[] args) {
		
		System.out.println("RMI Server started :)");
		
		// Create and install a security manager
		if(System.getSecurityManager() == null) {
			System.setSecurityManager(new RMISecurityManager());
			System.out.println("Security manager installed :)");
		} else {
			System.out.println("Security manager already installed :|");
		}
		
		// Create the registry
		try {
			LocateRegistry.createRegistry(1099);
			System.out.println("RMI registry created :)");
		} catch(RemoteException e) {
			// only one possible error : already existing registry ==> do nothing !
			System.out.println("RMI registry already created :|");
		}
		
		// now the tricky stuff...
		try {
			// Instantiate an RMI Server
			M1Server our_server = new M1Server();
			
			// Bind this instance to the name "M1Server"
			Naming.rebind("//localhost/M1Server", our_server);
			
			System.out.println("PeerServer bound in registry :)");
		} catch (Exception e) {
			System.err.println("RMI Server exception : "+e);
			e.printStackTrace();
		}

	}

}
