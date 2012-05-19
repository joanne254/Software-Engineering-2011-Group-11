import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

public class ChatClientImpl extends UnicastRemoteObject implements ChatClient,
	Runnable {
    private final ChatServer mycs;

    public ChatClientImpl(ChatServer cs) throws RemoteException {
	mycs = cs;
	mycs.register(this);
    }

    @Override
    public synchronized void receive(String s) throws RemoteException {
	System.out.println("Message :" + s);
    }

    @Override
    public void run() {
	Scanner in = new Scanner(System.in);
	String msg;

	while (true) {
	    try {
		msg = in.nextLine();
		mycs.broadcast(msg);
	    } catch (Exception e) {
		System.err.println("problem");
	    }
	}
    }

    public static void main(String[] args) {
	String url = "rmi://localhost/ChatServer";
	try {
	    ChatServer cs = (ChatServer) Naming.lookup(url);
	    new Thread(new ChatClientImpl(cs)).start();
	} catch (Exception e) {
	    System.err.println("Problem");
	}
    }
}
