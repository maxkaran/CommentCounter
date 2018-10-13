import java.nio.file.Path;
import java.nio.file.Paths;

public class CommentCounter {
	private Path filePath;
	private String fileName;
	private String fileType;
	
	//Main Constructor
	CommentCounter(String filepath){
		this.filePath= Paths.get(filepath);
		this.fileName = filePath.getFileName().toString();
		
		//get the file's extension and store it in filetype
		int indexOfPeriod = fileName.lastIndexOf('.'); //get the index of the last '.' in the filename, which will precede the file type extension
		if(indexOfPeriod > 0)
			this.fileType = fileName.substring(indexOfPeriod+1, fileName.length());
		else //if the index is equal to zero, the filename starts with a '.' and can be ignored
			//if the number returned is negative then there is no '.', which means there is no file extension and it can also be ignored
			this.fileType = null;
	}
	
	//______________________________________________GETTERS AND SETTERS____________________________________________________________

	public String getFilePath() {
		return filePath.toString();
	}
	
	public String getFileName() {
		return fileName;
	}
	
	public String getFileType() {
		return fileType;
	}
	
	//______________________________________________END GETTERS AND SETTERS________________________________________________________


	public static void main(String[] args) {
		CommentCounter c = new CommentCounter("kljdksa.txt");
		System.out.println(c.getFileType());
	}

}
