package communication;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.LinkedList;


public class StartUp implements StartUpInterface {

    //stores all the clients in the game
    private final LinkedList<PlayerInterface> myclients;
    private Hashtable<String, File> brains;
    private Hashtable<String, File> worlds;

    public StartUp() throws RemoteException {
    	myclients = new LinkedList<PlayerInterface>();
    	//BrainVerifier bc = new BrainVerifier();
    	//WorldVerifier wc = new WorldVerifier();
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


    @Override
    public void receiveWorld(String name, FilePacketObject fpo) throws RemoteException, FileNotFoundException {
    	System.out.print("I have received a world from "+name);
		if(worlds.containsKey(name)){
			System.out.println(name+ " already has a world. This will be replaced by the new brain");
			brains.remove(name);
		}
		fpo.writeTo(new FileOutputStream(name + "world"));
		worlds.put(name, new File(name + "world"));
		System.out.println(worlds);
	
    }

    @Override
    public void receiveBrain(String name, FilePacketObject fpo) throws RemoteException, FileNotFoundException {
    	// TODO Auto-generated method stub
		System.out.print("I have received a brain from "+name);
		if(brains.containsKey(name)){
			System.out.println(name+ " already has a brain. This will be replaced by the new brain");
			brains.remove(name);
		}
		fpo.writeTo(new FileOutputStream(name + "brain"));
		brains.put(name, new File(name + "brain"));
		System.out.println(brains);
	}
		
    
    
    public static void main(String [] args){
	    try {
		    Naming.rebind("StartUpInterface", new StartUp());
		} catch (Exception e) {
		    System.err.println("StartUpInterface Problem");
		}
    }
    
    
}