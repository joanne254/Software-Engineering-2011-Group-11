/* RmiServerIntf class
 * Defines the interface that is used by the client and implemented by the server. */

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface M1ServerIntf extends Remote {
	
	public String getMessage() throws RemoteException;
	
}
