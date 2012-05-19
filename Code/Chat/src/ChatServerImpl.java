import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.LinkedList;

public class ChatServerImpl extends UnicastRemoteObject implements ChatServer {
    private final LinkedList<ChatClient> myclients;

    public ChatServerImpl() throws RemoteException {
	myclients = new LinkedList();
    }

    @Override
    public synchronized void register(ChatClient c) throws RemoteException {
	myclients.add(c);
    }

    @Override
    public synchronized void broadcast(String s) throws RemoteException {
	for (int i = 0; i < myclients.size(); i++) {
	    myclients.get(i).receive(s);
	}
    }

    public static void main(String[] args) {
	try {
	    Naming.rebind("ChatServer", new ChatServerImpl());
	} catch (Exception e) {
	    System.err.println("chat server Problem");
	}
    }

}
