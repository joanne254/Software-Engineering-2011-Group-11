package communication;


import java.io.FileNotFoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface StartUpInterface extends Remote
    {
        void register(PlayerInterface c) throws RemoteException;
        void broadcast(String s) throws RemoteException;
        void receiveWorld (String s, FilePacketObject fpo) throws RemoteException, FileNotFoundException;
        void receiveBrain(String name, FilePacketObject s) throws RemoteException, FileNotFoundException;
        void send (String s) throws RemoteException;
	}



