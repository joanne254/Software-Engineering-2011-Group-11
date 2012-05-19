package ant_files;
import communication.PlayerInterface;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

public class AntWorld {

	//private final PlayerInterface author;
	//private final ... map; 2-dimensional array representing the world 
	private final boolean tournament_norm;
	String [][] world;
	String strLine;
	
	//public AntWorld(PlayerInterface playerInterface, File file) throws IOException {
	public AntWorld(File file) throws IOException {
		//author = playerInterface;
		// initialise map with a conversion function using the file
		// then proceed to a test on the array to determine whether it is conform
		// to the requirements for ant worlds used in tournaments
		tournament_norm = false;
		createWorld(file);
	}
	public void createWorld(File f) throws IOException
	{
		world = new String[150][150];
		FileReader fis = new FileReader("world.txt");
		BufferedReader br  = new BufferedReader(fis);
			for (int x = 0; x < 150; x++) {
				String line = br.readLine();
				String[] chars = line.split(" ");
				for (int y = 0; y < 150; y++) {
					world[x][y]= chars[y];
					//System.out.println(world[x][y]);
				}
			}
		}
	
	
	public String[][] getAntWorld()
	{
		return world;
	}
	
	
	public static void main(String[] args) {
		
		
	}

}
