import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;


@SuppressWarnings("serial")
public class M3Client extends UnicastRemoteObject
	implements M3ClientIntf, Runnable {

	//fields
	private String name;	
	private M3ServerIntf server;
	
	//constructor
	public M3Client(M3ServerIntf s) throws RemoteException {
		name = setName();
		server = s;
		server.registerClient(this);
    }
	
	
	//methods    
    public synchronized void receiveMessage(String message) throws RemoteException {
    	System.out.println(message);
    }  

	private String setName() {
		String name = null;
		Scanner in = new Scanner(System.in);
		System.out.print("Name : ");
		while( !(name = in.next()).matches("[a-zA-Z][a-zA-Z0-9]*"))
			System.out.print("Invalid entry ! Name : ");
		return name;
	}
	
	public void run() {
		
    	//BROADCAST CLIENT JOINING
		try {
	    	server.broadcast(name+" joined. ");
	    } catch (Exception e) {
			System.err.println("RMI Server exception : "+e);
			e.printStackTrace();
	    }
	    
		//BROADCAST ANY MESSAGE	    
		Scanner in = new Scanner(System.in);
		String message = "";	
		while ( !message.equals("exit") ) {
			try {
		    	message = in.nextLine();
		    	server.broadcast(name+" : "+message);
		    } catch (Exception e) {
				System.err.println("RMI Server exception : "+e);
				e.printStackTrace();
		    }
		}
		
    	//BROADCAST CLIENT LEAVING
		try {
	    	server.broadcast(name+" left. ");
	    } catch (Exception e) {
			System.err.println("RMI Server exception : "+e);
			e.printStackTrace();
	    }
    	
	}
	
	public static void main(String[] args) {
		
		// security manager
		if( System.getSecurityManager() == null) {
			System.setSecurityManager(new RMISecurityManager());
		}
		
		// Instantiate an RMI Server
		try {
			M3ServerIntf server = (M3ServerIntf)Naming.lookup("//localhost/RMISERVER");
			new Thread(new M3Client(server)).start();
		} catch (Exception e) {
			System.err.println("RMI Client exception : "+e);
			e.printStackTrace();
	    }

	}

}
