import java.rmi.Remote;
import java.rmi.RemoteException;

public interface M4ClientIntf extends Remote {
	
    void receiveMessage(String message) throws RemoteException;

}
