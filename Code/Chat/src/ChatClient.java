import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ChatClient extends Remote
{
    void receive (String s) throws RemoteException;
}
