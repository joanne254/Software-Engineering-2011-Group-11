
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface StartUpInterface extends Remote
    {
        void register(PlayerInterface c) throws RemoteException;
        void broadcast(String s) throws RemoteException;
        void send (String s) throws RemoteException;
        
        /* Predicates */
        public boolean isRegistered(String playerName) throws RemoteException;
	}



