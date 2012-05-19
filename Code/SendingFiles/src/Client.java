
import java.io.FileNotFoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;


public interface Client extends Remote {
    void receiveMessage (String s) throws RemoteException;
    void uploadWorld (String s) throws RemoteException;
	void uploadBrain() throws RemoteException, FileNotFoundException;
}
