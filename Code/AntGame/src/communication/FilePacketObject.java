package communication;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;

public class FilePacketObject implements Serializable {
	File file;
	byte[] data;
	
	public FilePacketObject(File f) {
	this.file = f;
		data = new byte[(int) f.length()];
		try
		{
			FileInputStream fis = new FileInputStream(f);
			fis.read(data);
			
		}
		catch(FileNotFoundException e)
		{
			System.out.println("File not found");
		}
		catch(IOException e)
		{
			System.out.println("byte data error");
		}
	}
	public void writeTo( FileOutputStream out  ){
	  	try{
	    	out.write( data );
	  	}catch( Exception e ){
	   		e.printStackTrace();
	   	}
}
}
