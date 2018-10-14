package main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.platform.commons.util.StringUtils;

public class CommentCounter {
	private Path filePath;
	private String fileName;
	private String fileType;
	
	//delimiters or strings that represent the comment for each specific programming language
	//e.g. for C++ singleComment = "//", blockCommentStart = "/*", blockCommentEnd = "*/"
	private String singleComment;
	private String blockCommentStart; 
	private String blockCommentEnd;
	
	boolean inBlockComment = false;

	
	//Main Constructor
	public CommentCounter(String filepath){
		this.filePath= Paths.get(filepath);
		this.fileName = filePath.getFileName().toString();
		
		//get the file's extension and store it in filetype
		int indexOfPeriod = fileName.lastIndexOf('.'); //get the index of the last '.' in the filename, which will precede the file type extension
		if(indexOfPeriod > 0 && fileName.charAt(0) != '.') //if first character is '.', file can be ignored
			this.fileType = fileName.substring(indexOfPeriod+1, fileName.length()); //get the rest of the string after the last '.'
		else //if the number returned is negative then there is no '.', which means there is no file extension and it can also be ignored
			this.fileType = null;
		
		//For now, comment strings will be hard coded
		//TODO dynamically generate these based on file input
		singleComment = "//";
		blockCommentStart = "/*";
		blockCommentEnd = "*/";
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

	public String Analyze() throws IOException {
		BufferedReader buffer;
		
		try {
			buffer = new BufferedReader(new FileReader(getFilePath())); //will read the input file			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("File could not be found :(");
			return "File could not be found :(";
		}
		
		String line = buffer.readLine();
		
		//initialize boolean values to keep track of different scenarios
		inBlockComment = false; //is the current line in a block of comments comment
		
		
		//initialize all the count variables
		int TODOcount = 0; //count of TODOs
		int singleCommentCount = 0; //count of single line comments
		int blockCommentCount = 0; //count of blocks of comments
		int blockCommentLineCount = 0; //count of lines of comments that are in a block
		
		//loop through all lines in the file
		while(line != null) {
			//check for a TODO
			if(line.contains("TODO"))
				TODOcount += CommentCounter.countOccurences(line, "TODO");
			
			if(!inBlockComment) { //if not currently in a block comment
				if(line.contains(singleComment) && line.contains(blockCommentStart)) { //if line contains both types of comments, see which comes first
					int singlePos, blockPos;
					singlePos = line.indexOf(singleComment);
					blockPos = line.indexOf(blockCommentStart);
					if(singlePos < blockPos) //single line comes first
						singleCommentCount++;
					else { //block comment comes first
						blockCommentLineCount++; //add to count of lines
						blockCommentCount += blockCommentsCount(line); //this method counts the number of blocks per line
					}
				}
			}else
			
			line = buffer.readLine(); //read next line in the file
		}
		
		System.out.println(TODOcount);
		
		return ""; //TODO return an actual output
		
	}

	//helper function that will count how many blocks of comments are in a line
	private int blockCommentsCount(String line) {
		int blockCount = 0;
		
		int index = 0;
		
		while(index >= 0) {

			index = line.indexOf(blockCommentStart);
			
			if(index >= 0) { //enter start of block comment
				line = line.substring(index+2, line.length()); //slice of the start of the string
				blockCount++;
				inBlockComment = true;
			}
			index = line.indexOf(blockCommentEnd); //exit a block comment
			if(index >= 0) {
				inBlockComment = false;
				line = line.substring(index+2, line.length());
			}
			
		}
		
		return blockCount;
	}

	//helper function that counts the number of occurences of a substring in a string
	private static int countOccurences(String str, String subString) {
		int count = 0;
		
		for (int pos = str.indexOf(subString); pos >= 0; pos = str.indexOf(subString, pos + 1))
		    count++;
		
		return count;
	}
	
	//_________________________________________Tester Functions_________________________________________________________
	//These functions don't help with the functionality of the code, but allow access to private methods that are used in the code
	public int testBlockCommentsCount(String line) {
		return blockCommentsCount(line);
	}

	public static void main(String[] args) throws IOException {
		CommentCounter c = new CommentCounter("C:\\Users\\Max\\workspace\\CommentCounter\\test\\input_files\\javaTest1.java");
		System.out.println(c.getFileType());
		c.Analyze();
	}

}
