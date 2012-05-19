import java.io.*;
import java.util.*;
public class AntBrain {
	
	File file;
	ArrayList<String> lines;
	String line;
	ClientImplementation author;
	
	public AntBrain(File file){
		this.file = file;
		getBrain();
	}
		
	public void getBrain(){
		try {
			lines = new ArrayList<String>();
			BufferedReader reader = new BufferedReader(new FileReader(file));
			while((line = reader.readLine()) != null){
					lines.add(line);
				}
			} 
		catch (IOException e) {
				System.out.println("File not found");
				}
		}
	public ArrayList<String> convertedBrain(){
		return this.lines;
	}
}
