import java.util.Scanner;

public class M4AntWorld {

	private final M4ClientIntf author;
	//private final ... map; 2-dimensional array representing the world 
	private final boolean tournament_norm;
	
	
	public M4AntWorld(M4ClientIntf client, Scanner file) {
		author = client;
		// initialise map with a conversion function using the file
		// then proceed to a test on the array to determine whether it is conform
		// to the requirements for ant worlds used in tournaments
		tournament_norm = false;
		
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
