import java.rmi.Remote;
import java.rmi.RemoteException;

public interface M2ServerIntf extends Remote {
	
	void registerClient(M2ClientIntf client) throws RemoteException;
	
	void broadcast(String message) throws RemoteException;
}
