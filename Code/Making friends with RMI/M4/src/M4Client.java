import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.InputMismatchException;
import java.util.Scanner;


@SuppressWarnings("serial")
public class M4Client extends UnicastRemoteObject
	implements M4ClientIntf, Runnable {

	//fields
	private String name;	
	private final M4ServerIntf server;
	
	//constructor
	public M4Client(M4ServerIntf s) throws RemoteException {
		name = setName();
		server = s;
		server.registerClient(this);
    }
	
	
	//methods  
    public synchronized void receiveMessage(String message) throws RemoteException {
    	System.out.println(message);
    } 
    
    public void manageFiles() {
    	
    	System.err.println("--> manage files here");
   	 	/* GUI
   	 	 * ---
   	 	 * 1) display a list of all the files currently on the server with 
   	 	 * 	- their type (world/brain) (or in 2 different sections)
   	 	 * 	- and their author
   	 	 * 
   	 	 * 2) have options :
   	 	 *  (- "download" for all files ?)
   	 	 *  - "delete" for files uploaded by this client ("author" field identical)
   	 	 * 
   	 	 * 3) and finally a button "add" to upload files, first choosing their type
   	 	 *  - the "author" field is set at creation
   	 	 *  - the file is only valid when the corresponding syntax check returns true
   	 	 *  ( - additional testing after creation for world files in order to determine
   	 	 *  whether they're suitable for tournaments ?)
   	 	 */
    }

	private String setName() {
		String name = null;
		Scanner in = new Scanner(System.in);
		System.out.print("Name : ");
		while( !(name = in.next()).matches("[a-zA-Z]\\w*"))
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
  
	    
	 // Start menu
		Scanner input = new Scanner(System.in);
		int choice;
		do {
			System.out.print(" * MENU *\n"+
					"  1 - Manage files\n"+
					"  2 - Play against another player\n" +
					"  3 - Play in a tournament\n" +
					"  4 - QUIT -> ");
			try {
				choice = input.nextInt();
				if( choice>=1 && choice<=4 )
					switch(choice) {
						case 1 : manageFiles(); break;
						case 2 : break;
						case 3 : break;
						case 4 : break;
					}
				else System.out.print("Error : value not a choice\n -> ");
			}
			catch (InputMismatchException e)
			{
				System.out.print("Error : "+e.toString()+"\n -> ");
				input.nextLine();
				choice = 0;
			}
		} while( choice != 4 );
	      
	    /*
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
		}*/
		
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
			M4ServerIntf server = (M4ServerIntf)Naming.lookup("//localhost/RMISERVER");
			new Thread(new M4Client(server)).start();
		} catch (Exception e) {
			System.err.println("RMI Client exception : "+e);
			e.printStackTrace();
	    }

	}

}
