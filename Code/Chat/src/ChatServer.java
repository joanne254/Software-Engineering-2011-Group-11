import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ChatServer extends Remote
{
    void register(ChatClient c) throws RemoteException;
    void broadcast(String s) throws RemoteException;
}
