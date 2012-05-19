import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

import java.io.IOException;

/*import java.awt.FileDialog;
import java.io.File;
import java.io.FileNotFoundException;
import javax.swing.JFrame;
*/

@SuppressWarnings("serial")
public class Player extends UnicastRemoteObject 
	implements PlayerInterface, Runnable{
	
	public final StartUpInterface myc;
    //private String line;
    private String name;
    //private FilePacketObject fpo;
    
    public Player(StartUpInterface s) throws IOException {
    	 myc = s;
    	 name = null;
    	 
		// TODO Auto-generated constructor stub
    }

    
    public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	

    @Override
    //this will be the method that receives the serialised object from the server once it finishes the game.
    public void receiveMessage(String s) throws RemoteException {
	// TODO Auto-generated method stub
	System.out.println(s);
    }
    
    
    

    public boolean isNamed(String playerName) {
    	return (this.name).compareTo(playerName) == 0;
    }
    
    
	
	public void sendMessage() throws RemoteException {
		Scanner in = new Scanner(System.in);
		myc.broadcast(name + in);
		
	}
    
	
    public void run() {
	// TODO Auto-generated method stub
    	
   	 	/*start the GUI here */
    	WelcomeScreen welcomeScreen = new WelcomeScreen(this);
 		welcomeScreen.setVisible(true);		
	
		 try { 	
			 myc.register(this);
			 System.out.println("**************");
			 /*
			 setBrain();
			 uploadBrain();
			 setWorld();
			 uploadWorld();
			 */

			myc.broadcast(name+" uploaded their brain");
		} catch (RemoteException e) {
			System.out.println("Problem on client side");
			e.printStackTrace();
		}
	
    }
	
	/**
	 * This method launches the game on the CLIENT side.
	 * @param args
	 */
    public static void main(String[] args) {
    	
    	/* security manager */
		if( System.getSecurityManager() == null)
			System.setSecurityManager(new RMISecurityManager());
		
		/* instantiate an RMI Server */
		String url = "rmi://localhost/StartUpInterface";
		
		try {
		    StartUpInterface s = (StartUpInterface) Naming.lookup(url);
		    new Thread(new Player(s)).start();
		} catch (Exception e) {
		    System.err.println("Problem on client side");
		    e.printStackTrace();
		}
    }
}
