
import java.io.FileNotFoundException;
import java.rmi.Remote;
import java.util.*;
import java.rmi.RemoteException;

public interface Server extends Remote
    {
        void register(Client c) throws RemoteException;
        void broadcast(String s) throws RemoteException;
        void receiveWorld (String s) throws RemoteException;
        void receiveBrain(String name, FilePacketObject s) throws RemoteException, FileNotFoundException;
        void send (String s) throws RemoteException;
    }



