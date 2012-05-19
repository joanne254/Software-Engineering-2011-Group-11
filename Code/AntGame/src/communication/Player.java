package communication;
import java.awt.FileDialog;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

import javax.swing.JFrame;


@SuppressWarnings("serial")
public class Player extends UnicastRemoteObject implements PlayerInterface, Runnable{
	private final StartUpInterface myc;
    private String line;
    //private AntBrain brain;
    private String name;
    private FilePacketObject fpo;
    
    public Player(StartUpInterface s) throws IOException {
    	 myc = s;
		 name = setName();
		 myc.register(this);
		 System.out.println("**************");
		 setBrain();
		 uploadBrain();
		 setWorld();
		 uploadWorld();
		 myc.broadcast(name+" uploaded their brain");
		// TODO Auto-generated constructor stub
    }
    public void setBrain(){
		FileDialog fd = new FileDialog(new JFrame(), "Choose ant brain...");
		fd.setVisible(true);
		String path = (fd.getDirectory() + fd.getFile());
		File f = new File(path);
		this.fpo = new FilePacketObject(f);
		
	}
    @Override
    public void uploadBrain() 
    {
    	try{
    		myc.receiveBrain(this.name, fpo);
    	}
    	catch (RemoteException e){
    		System.out.println("Connection Error");
    		e.printStackTrace();
    		setBrain();
    	}
    	catch (FileNotFoundException e){
    		System.out.println("File Not Found");
    		e.printStackTrace();
    		setBrain();
    	}
	} 
    
    public void setWorld()
    {
    	FileDialog fd = new FileDialog(new JFrame(), "Choose ant world...");
		fd.setVisible(true);
		String path = (fd.getDirectory() + fd.getFile());
		File f = new File(path);
		this.fpo = new FilePacketObject(f);
    	
    }
    public void uploadWorld()
    {
    	try {
			myc.receiveWorld(this.name, fpo);
		} catch (RemoteException e) {
			System.out.println("Connection Error");
			setWorld();
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			System.out.println("File Not Found");
			e.printStackTrace();
			setWorld();
		}
    }
    
    private String setName() {
		String name = null;
		Scanner in = new Scanner(System.in);
		System.out.print("Name : ");
		while( !(name = in.next()).matches("[a-zA-Z]\\w*"))
			System.out.println("Invalid entry ! Name : ");
		return name;
	} 

    @Override
    //this will be the method that receives the serialised object from the server once it finishes the game.
    public void receiveMessage(String s) throws RemoteException {
	// TODO Auto-generated method stub
	System.out.println(s);
    }
    @Override
    public void run() {
	// TODO Auto-generated method stub
	
    }

    @Override
    public void uploadWorld(String s) throws RemoteException {
	// TODO Auto-generated method stub
	
    }
    
    public static void main(String[] args) {
		String url = "rmi://localhost/StartUpInterface";
		try {
		    StartUpInterface s = (StartUpInterface) Naming.lookup(url);
		    new Thread(new Player(s)).start();
		} catch (Exception e) {
		    System.err.println("Problem on client side");
		    e.printStackTrace();
		}
    }
	@Override
	public void sendMessage() throws RemoteException {
		Scanner in = new Scanner(System.in);
		myc.broadcast(name + in);
		
	}
}
