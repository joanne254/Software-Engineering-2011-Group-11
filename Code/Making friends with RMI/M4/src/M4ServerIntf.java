import java.rmi.Remote;
import java.rmi.RemoteException;


public interface M4ServerIntf extends Remote {
	
	void registerClient(M4ClientIntf client) throws RemoteException;
	
	void broadcast(String message) throws RemoteException;

}
