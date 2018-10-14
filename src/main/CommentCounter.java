package main;

import java.nio.file.Path;
import java.nio.file.Paths;

public class CommentCounter {
	private Path filePath;
	private String fileName;
	private String fileType;
	
	//delimiters or strings that represent the comment for each specific programming language
	//e.g. for C++ singleComment = "//", blockCommentStart = "/*", blockCommentEnd = "*/"
	private String singleComment;
	private String blockCommentStart; 
	private String blockCommentEnd;

	
	//Main Constructor
	public CommentCounter(String filepath){
		this.filePath= Paths.get(filepath);
		this.fileName = filePath.getFileName().toString();
		
		//get the file's extension and store it in filetype
		int indexOfPeriod = fileName.lastIndexOf('.'); //get the index of the last '.' in the filename, which will precede the file type extension
		if(indexOfPeriod > 0 && fileName.charAt(0) != '.') //if first character is '.', it can be ignored
			this.fileType = fileName.substring(indexOfPeriod+1, fileName.length());
		else //if the number returned is negative then there is no '.', which means there is no file extension and it can also be ignored
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
		CommentCounter c = new CommentCounter(".kljdksa.txt");
		System.out.println(c.getFileType());
	}

}
