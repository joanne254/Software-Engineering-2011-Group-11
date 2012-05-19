import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;

/*
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Hashtable;
*/
import java.util.concurrent.CopyOnWriteArrayList;


@SuppressWarnings("serial")
public class StartUp extends UnicastRemoteObject
	implements StartUpInterface {

    //stores all the clients in the game
    private final CopyOnWriteArrayList<PlayerInterface> myclients;

    public StartUp() throws RemoteException {
    	myclients = new CopyOnWriteArrayList<PlayerInterface>();
    }
    
    
    @Override
    public synchronized void register(PlayerInterface c) throws RemoteException {
    	myclients.add(c);
    }


    @Override
    public void broadcast(String s) throws RemoteException {
    	// TODO Auto-generated method stub
	
    }

    @Override
    public void send(String s) throws RemoteException {
    	// TODO Auto-generated method stub
	
    }
    
    /**
     * This predicate determines if there already is a client whose
     * name is the one passed as a parameter.
     * @param playerName
     * @return boolean
     */
    public boolean isRegistered(String playerName) throws RemoteException {
    	for (PlayerInterface player:this.myclients)
    		if (player.isNamed(playerName))
    			return true;
    	return false;
    }

    
    public static void main(String [] args){

		/* Security manager */
		if( System.getSecurityManager() ==  null ) {
			System.setSecurityManager(new RMISecurityManager());
			System.out.println("Security manager installed :)");
		} else {
			System.out.println("Security manager already installed :|");
		}
		
		/* Registry */
		try {
			LocateRegistry.createRegistry(1099);
			System.out.println("RMI registry created :)");
		} catch(RemoteException e) {
			System.out.println("RMI registry already created :|");
		}
		
		/* Naming */
	    try {
		    Naming.rebind("StartUpInterface", new StartUp());
			System.out.println("PeerServer bound in registry :)");
		} catch (Exception e) {
		    System.err.println("StartUpInterface Problem : "+e);
		}
    }
    
    
}