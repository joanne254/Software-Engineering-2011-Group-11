
import java.awt.FileDialog;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

import javax.swing.JFrame;


@SuppressWarnings("serial")
public class ClientImplementation extends UnicastRemoteObject implements Client, Runnable{
    private final Server myc;
    private String line;
    private AntBrain brain;
    private String name;
   
    private FilePacketObject fpo;
    
	public ClientImplementation(Server s) throws IOException {
		 myc = s;
		 name = setName();
		 myc.register(this);
		 System.out.println("**************");
		 setBrain();
		 uploadBrain();
		 myc.broadcast(name+" uploaded their brain");
    }
	
	public void setBrain(){
		FileDialog fd = new FileDialog(new JFrame(), "Choose ant brain...");
		fd.setVisible(true);
		String path = (fd.getDirectory() + fd.getFile());
		File f = new File(path);
		this.fpo = new FilePacketObject(f);
		
	}
	
	public void setWorld(){
		FileDialog fd = new FileDialog(new JFrame(), "Choose ant world...");
		fd.setVisible(true);
		String path = (fd.getDirectory() + fd.getFile());
		File f = new File(path);
		//this.world = new AntWorld(f);
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
    public void uploadBrain() throws RemoteException, FileNotFoundException {
    	myc.receiveBrain(this.name, fpo);
    }
  
    public void changeBrain() throws RemoteException, FileNotFoundException{
    	setBrain();
    	uploadBrain();
    }
    @Override
    public void run() {
	// TODO Auto-generated method stub
	
    }

    @Override
    public void uploadWorld(String s) throws RemoteException {
	// TODO Auto-generated method stub
	
    }
    
    public static void main(String[] args) throws IOException {
    	String url = "rmi://localhost/Server";
		try {
		    Server s = (Server) Naming.lookup(url);
		    new Thread(new ClientImplementation(s)).start();
		} catch (Exception e) {
		    System.err.println("Problem on client side");
		    e.printStackTrace();
		}
    }

	@Override
	public void receiveMessage(String s) throws RemoteException {
		System.out.println(s);
		
	}
 }

