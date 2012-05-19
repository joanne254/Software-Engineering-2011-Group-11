import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;


public class AntWorldGenerator {

	/**
	 * @param args
	 */
	char[][] world;
	Random random;
	int selection;
	public AntWorldGenerator() throws Exception{	
		generateWorld();
		writeWorld();
	}
	public void generateWorld(){
		world = new char[150][150];
		random = new Random();
		for(int i=0;i<150;i++){
			for(int j = 0;j<150;j++){
			if((i==0) | (j==0) | (i==149) | (j==149)) {
				world[i][j] = '#';
			}
				else{
					selection = random.nextInt(3);
					switch(selection){
					case 0:
						world[i][j] = '.';
						break;
					case 1:
						world[i][j] = '+';
						break;
					case 2:
						world[i][j] = '-';
						break;
				}
				}
			}
		}
	}
				
		
	public void writeWorld() 
	{
		FileWriter fw;
		try {
			fw = new FileWriter("world.txt");
		BufferedWriter bw = new BufferedWriter(fw);
		for(int i=0;i<150;i++){
			for(int j=0; j<150;j++){
				fw.write(Character.toString(world[i][j]));
				fw.write(" ");
			}
			System.out.println("new line at"+i);
			fw.write(System.getProperty("line.separator"));
		}
		fw.close();
	
	} catch (IOException e) {
		System.out.println("Problem writing world");
	}
	}
	public static void main(String[] args) throws Exception {
		AntWorldGenerator generator = new AntWorldGenerator();
	}

}
