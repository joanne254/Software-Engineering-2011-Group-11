import java.rmi.Remote;
import java.rmi.RemoteException;

public interface M2ClientIntf extends Remote {
	
    void receiveMessage(String message) throws RemoteException;

}
