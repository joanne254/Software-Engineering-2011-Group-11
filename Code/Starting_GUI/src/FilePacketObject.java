import java.io.File;
import java.io.FileOutputStream;


public class FilePacketObject {

	@SuppressWarnings("unused")
	private File file;

	public FilePacketObject(File file) {
		super();
		this.file = file;
	}
	
	public void writeTo(FileOutputStream out) {
		
	}
}
