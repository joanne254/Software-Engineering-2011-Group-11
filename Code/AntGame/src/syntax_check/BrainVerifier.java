package syntax_check;
import java.awt.FileDialog;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.JFrame;

public class BrainVerifier {
	
	/* ANT BRAIN GRAMMAR */
	
	// IMPORTANT : 
	// 1) supposed to be CASE-INSENSITIVE !!!
	
	private final String
		WS = " ",
		lr = "((Left)|(Right))",
		st = "([0-9]|([1-9][0-9]{1,2}))",
		i = "[0-5]",
		p = "([0-9]|([1-9][0-9]{1,}))",
		sensedir =
			"((Here)" +
			"|(Ahead)" +
			"|(LeftAhead)" +
			"|(RightAhead))",
		cond =
			"((Friend)" +
			"|(Foe)" +
			"|(FriendWithFood)" +
			"|(FoeWithFood)" +
			"|(Food)" +
			"|(Rock)" +
			"|((Marker)" + WS + i +
			")|(FoeMarker)" +
			"|(Home)" +
			"|(FoeHome))",
	sense_instruction = "((Sense)" + WS + sensedir + WS + st + WS + st + WS + cond + ")",
	mark_instruction = "((Mark)" + WS + i + WS + st + ")",
	unmark_instruction = "((Unmark)" + WS + i + WS + st + ")",
	pickup_instruction = "((PickUp)" + WS + st + WS + st + ")",
	drop_instruction = "((Drop)" + WS + st + ")",
	turn_instruction = "((Turn)" + WS + lr + WS + st + ")",
	move_instruction = "((Move)" + WS + st + WS + st + ")",
	flip_instruction = "((Flip)" + WS + p + WS + st + WS + st + ")",
	comment = "( ;.*)?$",
	instruction =
		"(" + sense_instruction +
		"|" + mark_instruction +
		"|" + unmark_instruction +
		"|" + pickup_instruction +
		"|" + drop_instruction +
		"|" + turn_instruction +
		"|" + move_instruction +
		"|" + flip_instruction + 
		")" + comment; 

	//fields
	private Scanner input;
	
	//constructors
	public BrainVerifier(Scanner s) {
		input = s;
	}
	
	public BrainVerifier() {
		do {
			System.out.print("Please select the file for your antbrain... ");
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
		int line_no = 0;
		boolean valid_line, valid_file = true;
		
		while (input.hasNextLine()) {
		    valid_file &= 
		    	(valid_line = 
		    		(line = input.nextLine()).matches(instruction));
		    System.out.println("state "+line_no+++" : <"+ line+"> valid ? "+valid_line);
		}

		return valid_file;
	}
	
	

	//////////////////////////////////////////////////////////////////////////////////////
	// TESTING
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	    	BrainVerifier test_brainchecker = new BrainVerifier();
		System.out.println("Valid file ? "+test_brainchecker.check());
		System.out.println("Done !");
		System.exit(0);
	}
	
}
