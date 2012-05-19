import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.RMISecurityManager;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.registry.*;
import java.util.LinkedList;

@SuppressWarnings("serial")
public class M2Server extends UnicastRemoteObject
	implements M2ServerIntf {
	
	//fields
    private final LinkedList<M2ClientIntf> clients;
	
    //constructor
	public M2Server() throws RemoteException {
		clients = new LinkedList<M2ClientIntf>();
	}
	
	//methods
	public synchronized void registerClient(M2ClientIntf client) throws RemoteException {
		clients.add(client);
	}
	
	public synchronized void broadcast(String message) throws RemoteException {
		for(int i=0;i<clients.size();i++)
			clients.get(i).receiveMessage(message);
	}
	
	//MAIN
	public static void main(String[] args) {
		
		// security manager
		if( System.getSecurityManager() ==  null ) {
			System.setSecurityManager(new RMISecurityManager());
			System.out.println("Security manager installed :)");
		} else {
			System.out.println("Security manager already installed :|");
		}
		
		// registry
		try {
			LocateRegistry.createRegistry(1099);
			System.out.println("RMI registry created :)");
		} catch(RemoteException e) {
			System.out.println("RMI registry already created :|");
		}
		
		try {
			// naming
			Naming.rebind("RMISERVER", new M2Server());
			System.out.println("PeerServer bound in registry :)");
		} catch (Exception e) {
			System.err.println("RMI Server exception : "+e);
		}	

	}

}
