
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.LinkedList;


public class ServerImplementation extends UnicastRemoteObject implements Server  {

    //stores all the clients in the game
    private final LinkedList<Client> myclients;
    private ArrayList<String> brain;
    private Hashtable<String, File> brains;
   
    
    public ServerImplementation() throws RemoteException {
    	myclients = new LinkedList<Client>();
    	brains = new Hashtable<String, File>();
    	//BrainChecker bc = new BrainChecker();
    	//WorldChecker wc = new WorldChecker();
    }
    
    
    @Override
    public synchronized void register(Client c) throws RemoteException {
    	myclients.add(c);
    }


    @Override
    public void broadcast(String s) throws RemoteException {
    	// TODO Auto-generated method stub
    	for(int i=0;i<myclients.size();i++)
			myclients.get(i).receiveMessage(s);
	
	
    }

    @Override
    public void send(String s) throws RemoteException {
    	// TODO Auto-generated method stub
	
    }


    @Override
    public void receiveWorld(String s) throws RemoteException {
    	// TODO Auto-generated method stub
	
    }

    @Override
    public void receiveBrain(String name, FilePacketObject brain) throws RemoteException, FileNotFoundException {
		// TODO Auto-generated method stub
		System.out.print("I have received a brain from "+name);
		if(brains.containsKey(name)){
			System.out.println(name+ " already has a brain. This will be replaced by the new brain");
			brains.remove(name);
		}
		brain.writeTo(new FileOutputStream(name + "brain"));
		brains.put(name, new File(name + "brain"));
		System.out.println(brains);
	}
    
    public static void main(String [] args){
	    try {
		    Naming.rebind("Server", new ServerImplementation());
		} catch (Exception e) {
		    System.out.println("Server Problem");
		    e.printStackTrace();
		}
    }

    
    
}