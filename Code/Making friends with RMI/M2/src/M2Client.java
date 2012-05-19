/* HELP
 * public interface Runnable
 * 
 * The Runnable interface should be implemented by any class whose instances
 * are intended to be executed by a thread. The class must define a method of 
 * no arguments called run.
 * 
 * This interface is designed to provide a common protocol for objects that 
 * wish to execute code while they are active. For example, Runnable is implemented 
 * by class Thread. Being active simply means that a thread has been started and 
 * has not yet been stopped.
 */

import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

@SuppressWarnings("serial")
public class M2Client extends UnicastRemoteObject
	implements M2ClientIntf, Runnable {
	
	//fields
    private M2ServerIntf server = null;
    
    //methods
    public M2Client(M2ServerIntf s) throws RemoteException {
		server = s;
		server.registerClient(this);
    }
    
    public synchronized void receiveMessage(String message) throws RemoteException {
    	System.out.println("Message :" + message);
    }  
    
	public void run() {
		Scanner in = new Scanner(System.in);
		String message = null;
	
		while ( message != "exit" ) {
		    try {
		    	message = in.nextLine();
		    	server.broadcast(message);
		    } catch (Exception e) {
				System.err.println("RMI Server exception : "+e);
				e.printStackTrace();
		    }
		}
		
		System.out.println("Bye !");
	}
	
	//MAIN
	public static void main(String[] args) {
		// security manager
		if( System.getSecurityManager() == null) {
			System.setSecurityManager(new RMISecurityManager());
		}

		// Instantiate an RMI Server
		try {
			M2ServerIntf server = (M2ServerIntf)Naming.lookup("//localhost/RMISERVER");
			new Thread(new M2Client(server)).start();
		} catch (Exception e) {
			System.err.println("RMI Client exception : "+e);
			e.printStackTrace();
	    }

		
	}

}
