import java.rmi.Remote;
import java.rmi.RemoteException;


public interface M3ServerIntf extends Remote {
	
	void registerClient(M3ClientIntf client) throws RemoteException;
	
	void broadcast(String message) throws RemoteException;

}
