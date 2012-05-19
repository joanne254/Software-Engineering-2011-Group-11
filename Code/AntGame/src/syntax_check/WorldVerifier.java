package syntax_check;
import java.awt.FileDialog;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.JFrame;

public class WorldVerifier {

	/* ANT WORLD STRUCTURE */
	
	/* issues :
	 * - maximum dimension allowed ? (for now, 10-999)
	 * - inferior and superior limits for number of rocks and food particles ? (when not in a contest)
	 * - what is a rock ? (sounds a bit existential but it cannot reasonably be just one rocky cell, so what is it exactly ?)
	 */
	
	
	@SuppressWarnings("unused")
	private final String
		dimension = "([1-9][0-9]{1,2})",
		WS = " ",
		rocky_cell = "#",
		clear_cell = "\\.",
		red_anthill_cell = "\\+",
		black_anthill_cell = "\\-",
		food_cell = "[0-9]",
		cell_specifier =
			"(" + rocky_cell +
			"|" + clear_cell +
			"|" + red_anthill_cell +
			"|" + black_anthill_cell +
			"|" + food_cell +
			")";
	
	
	//fields
	private Scanner input;
	private int x, y;


	//constructors
	public WorldVerifier(Scanner s) {
		input = s;
	}
	
	public WorldVerifier() {
		do {
			System.out.print("Ant-world file to open ? ");
			try {
			    FileDialog fd = new FileDialog(new JFrame(), "Choose ant brain...");
			    fd.setVisible(true);
				input = new Scanner(new File((fd.getFile())));
				System.out.println("Opening file.");		
			} catch(FileNotFoundException e) {
				System.out.println("Error opening file.");
			}
		} while( input == null );		
	}
	
	//methods

	public boolean check(){
		String line;
		
		//initial declaration of the world dimensions
		if(input.hasNextLine() && (line = input.nextLine()).matches(dimension+"$")) x = Integer.parseInt(line);
		else return false;
		if(input.hasNextLine() && (line = input.nextLine()).matches(dimension+"$")) y = Integer.parseInt(line); 
		else return false;
		
		System.out.println("World's dimensions are "+x+" x "+y);
		
		//proper map of the world
		int line_no = 0;
		boolean valid_line = true, valid_file = true;
		while (input.hasNextLine()) {
				
		    valid_file &= 
		    	(valid_line = 
		    		(line = input.nextLine()).matches(
		    				"( ){" + Integer.toString(line_no%2) + "}"+
							"(" + cell_specifier + " ){" + Integer.toString(x-1) + "}" + cell_specifier +
							"$"));
		    System.out.println(line_no+++"\t"+ line+"\t\t"+valid_line);	
		}
		
		valid_file &= (line_no == y);
		return valid_file;
	}
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		WorldVerifier test_worldchecker = new WorldVerifier();
		System.out.println("Valid file ? "+test_worldchecker.check());
		System.out.println("Done !");
		System.exit(0);

	}

}
