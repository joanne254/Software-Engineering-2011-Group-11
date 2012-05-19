import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.util.concurrent.CopyOnWriteArrayList;

@SuppressWarnings("serial")
public class M4Server extends UnicastRemoteObject
	implements M4ServerIntf {

	//fields
    private final CopyOnWriteArrayList<M4ClientIntf> clients;
    private final CopyOnWriteArrayList<M4AntBrain> antbrain_files;
    private final CopyOnWriteArrayList<M4AntWorld> antworld_files;
	
    //constructor
	public M4Server() throws RemoteException {
		clients = new CopyOnWriteArrayList<M4ClientIntf>();
	    antbrain_files = new CopyOnWriteArrayList<M4AntBrain>() ;
	    antworld_files = new CopyOnWriteArrayList<M4AntWorld>() ;
	}
	
	//methods
	public synchronized void registerClient(M4ClientIntf client) throws RemoteException {
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
			Naming.rebind("RMISERVER", new M4Server());
			System.out.println("PeerServer bound in registry :)");
		} catch (Exception e) {
			System.err.println("RMI Server exception : "+e);
		}	

	}

}
