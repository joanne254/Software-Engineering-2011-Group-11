
//import java.io.FileNotFoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;


public interface PlayerInterface extends Remote {
    public boolean isNamed(String playerName) throws RemoteException;
    void receiveMessage (String s) throws RemoteException;
	void sendMessage() throws RemoteException;
}
