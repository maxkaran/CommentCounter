package main;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;

public class CommentCounter {
	private Path filePath;
	private String fileName;
	private String fileType;
	
	//delimiters or strings that represent the comment for each specific programming language
	//e.g. for C++ singleComment = "//", blockCommentStart = "/*", blockCommentEnd = "*/"
	private String singleComment;
	private String blockCommentStart; 
	private String blockCommentEnd;
	
	private final String serializedFileName = "./serializedHash.ser";
	private HashMap<String, String[]> commentSet; //this is the serialized data object that will store all possible comment tokens for different languages
												  //The key will be the file extension, and the value will be an array
	
	boolean inBlockComment = false;
	boolean potentialBlockComment = false; //this is for only single line comment files (like python), it tracks if the last line started with a single line comment, which is potentially the start of a block comment
	
	//count variables used in Analyze() method
	private int TODOcount = 0; //count of TODOs
	private int singleCommentCount = 0; //count of single line comments
	private int blockCommentCount = 0; //count of blocks of comments
	private int blockCommentLineCount = 0; //count of lines of comments that are in a block
	private int lineCount = 0; //total number of lines

	
	//Main Constructor
	@SuppressWarnings("unchecked")
	public CommentCounter(String filepath) throws ClassNotFoundException, IOException{
		this.filePath= Paths.get(filepath);
		this.fileName = filePath.getFileName().toString();
		
		//get the file's extension and store it in filetype
		int indexOfPeriod = fileName.lastIndexOf('.'); //get the index of the last '.' in the filename, which will precede the file type extension
		if(indexOfPeriod > 0 && fileName.charAt(0) != '.') //if first character is '.', file can be ignored
			this.fileType = fileName.substring(indexOfPeriod+1, fileName.length()); //get the rest of the string after the last '.'
		else //if the number returned is negative then there is no '.', which means there is no file extension and it can also be ignored
			this.fileType = null;
		
		//Get the hashMap
		try {
			FileInputStream fis = new FileInputStream(serializedFileName);
			ObjectInputStream ois = new ObjectInputStream(fis);
			this.commentSet = (HashMap<String, String[]>) ois.readObject();
			ois.close();
		} catch (IOException e) {//if file does not exist, make a new hashmap
			e.printStackTrace();
			System.out.println("File not found, generating a fresh Hashmap with some presets");
			commentSet = new HashMap<String, String[]>();
			//add comment tokens for java, ts, js, and py file extensions
			commentSet.put("java", new String[]{"//","/*","*/"});
			commentSet.put("ts", new String[]{"//","/*","*/"});
			commentSet.put("js", new String[]{"//","/*","*/"});
			commentSet.put("py", new String[]{"#",null,null});
			
			serializeCommentSet(); //write this new table to file
		}
		
		//dynamically generate comment strings based on file input
		if(commentSet.containsKey(fileType)) {
			String[] commentTokens = commentSet.get(fileType);
			singleComment = commentTokens[0];
			blockCommentStart = commentTokens[1];
			blockCommentEnd = commentTokens[2];
		}

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
		potentialBlockComment = false;
		
		//initialize all the count variables
		TODOcount = 0; 
		singleCommentCount = 0;
		blockCommentCount = 0; 
		blockCommentLineCount = 0; 
		lineCount = 0;
		
		//loop through all lines in the file
		while(line != null) {
			lineCount++; //increment total number of lines
			line = line.trim(); //remove any leading or trailing white space
			
			//check for TODOs
			TODOcount += CommentCounter.countOccurencesOfTODO(line);
			
			//This part of the function can get confusing now
			//There are two ways comments will be processed, either there are single line comment tokens and multiline comment tokens
			//or there are languages with only one comment token, the following if statement will process them differently depending
			if(blockCommentStart == null || blockCommentEnd == null) {
				AnalyzeSingleOnly(line);
			}else if(singleComment != null) {
				AnalyzeSingleAndMulti(line);
			}else {
				return "Problem with comment tokens";
			}
			
			line = buffer.readLine(); //read next line in the file
		}
		
		buffer.close(); //close the buffer
		
		String returnString = "Total # of lines: " +lineCount+System.getProperty("line.separator")+
				"Total # of comment lines: " +(singleCommentCount+blockCommentLineCount)+System.getProperty("line.separator")+
				"Total # of single line comments: " +singleCommentCount+System.getProperty("line.separator")+
				"Total # of comment lines within block comments: " +blockCommentLineCount+System.getProperty("line.separator")+
				"Total # of block line comments: " +blockCommentCount+System.getProperty("line.separator")+
				"Total # of TODO’s: "+TODOcount;
		
		return returnString; //TODO return an actual output
		
	}
	
	//______________________________________________HELPER FUNCTIONS__________________________________________________________
	
	private void AnalyzeSingleOnly(String line) {
		if(!inBlockComment) {//currently not in block comment
			if(line.indexOf(singleComment) == 0) { //this could be the start of a block comment
				if(potentialBlockComment) {
					inBlockComment = true; //officially in a block comment
					blockCommentCount++; 
					blockCommentLineCount += 2; //add two to account for previous line
					singleCommentCount--; //subtract the one we added when we thought it was a single line
				}else {
					potentialBlockComment = true;
					singleCommentCount++;
				}
				
			}else if(line.contains(singleComment)) { //otherwise if it is just a line with a single comment
				singleCommentCount++; 
				potentialBlockComment = false;
			}else { //otherwise just a line with no comment
				potentialBlockComment = false;
			}
			
		}else { //currently in a block comment
			if(line.indexOf(singleComment) == 0) {
				blockCommentLineCount++;
			}else if(line.contains(singleComment)) { //there is a single line comment, but it is not at start of the line, so it is not part of the block comment
				inBlockComment = false;
				potentialBlockComment = false;
				singleCommentCount++;
			}else { //if no single line, just say block comment has ended
				inBlockComment = false;
				potentialBlockComment = false;
			}
		}
	}
	
	private void AnalyzeSingleAndMulti(String line) {
		if(!inBlockComment) { //if not currently in a block comment
			
			//these booleans help reduce calls on .contains method
			boolean containsSingleComment = line.contains(singleComment);
			boolean containsBlockCommentStart = line.contains(blockCommentStart);
			
			if(containsSingleComment && containsBlockCommentStart) { //if line contains both types of comments, see which comes first
				int singlePos, blockPos;
				singlePos = line.indexOf(singleComment);
				blockPos = line.indexOf(blockCommentStart);
				if(singlePos < blockPos) //single line comes first
					singleCommentCount++;
				else { //block comment comes first
					blockCommentLineCount++; //add to count of lines
					blockCommentCount += blockCommentsCount(line); //this method counts the number of blocks per line
				}
			}else if(containsSingleComment) {
				singleCommentCount++;
			}else if(containsBlockCommentStart) {
				blockCommentLineCount++;
				blockCommentCount += blockCommentsCount(line);
			}
		}else { //if currently in a block comment
			blockCommentLineCount++; //increment count of lines with a block comment
			int indexBlockCommentEnd = line.indexOf(blockCommentEnd);
			if(indexBlockCommentEnd >= 0) { //if the comment is ending on this line
				line = line.substring(indexBlockCommentEnd+blockCommentEnd.length(), line.length());
				inBlockComment = false; //have now exited the block comment
				blockCommentCount += blockCommentsCount(line);
			}
		}
	}

	//helper function that will count how many blocks of comments are in a line
	private int blockCommentsCount(String line) {
		int blockCount = 0;
		
		int index = 0;
		
		while(index >= 0) {

			index = line.indexOf(blockCommentStart);
			
			if(index >= 0) { //enter start of block comment
				line = line.substring(index+blockCommentStart.length(), line.length()); //slice of the start of the string
				blockCount++;
				inBlockComment = true; //this method will dynamically update this variable as it enters and exits blocks
			}
			index = line.indexOf(blockCommentEnd); //exit a block comment
			if(index >= 0) {
				inBlockComment = false;
				line = line.substring(index+blockCommentEnd.length(), line.length());
			}
			
		}
		
		return blockCount;
	}

	//helper function that counts the number of occurences of a substring in a string
	private static int countOccurencesOfTODO(String str) {
		int count = 0;
		String subString = "TODO";
		
		for (int pos = str.indexOf(subString); pos >= 0; pos = str.indexOf(subString, pos + 1)) {
			
			char characterAfter = ' '; //this is the character immediately after the TODO
			char characterBefore = ' '; //this is the character before
			
			if(pos+subString.length() <= str.length()-1) //check if within bounds of main string
				characterAfter = str.charAt(pos+subString.length()); //this is the character immediately after the TODO
			
			if(pos>0) //check if withing bounds of main string
				characterBefore = str.charAt(pos-1);

			
			//convert to lowercase
			characterAfter = Character.toLowerCase(characterAfter); 
			characterBefore = Character.toLowerCase(characterBefore);
			
			if(!(characterAfter >= 'a' && characterAfter <= 'z') && !(characterAfter >= '0' && characterAfter <= '9')) {
				if(!(characterBefore >= 'a' && characterBefore <= 'z') && !(characterBefore >= '0' && characterBefore <= '9'))
					count++;
			}
		}
		
		return count;
	}
	
	private void serializeCommentSet() throws IOException { //this function will take the hashmap of comment tokens, serialize it and write it to the file
		FileOutputStream fos = new FileOutputStream(serializedFileName);
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(commentSet);
	}
	
	//__________________________________________________END HELPER FUNCTIONS________________________________________________
	
	//_________________________________________Tester Methods_______________________________________________________________
	//These functions don't help with the functionality of the code, but allow access to private methods that are used in the code
	public int testBlockCommentsCount(String line) {
		return blockCommentsCount(line);
	}
	
	public static int testCountOccurencesOfTODO(String line) {
		return countOccurencesOfTODO(line);
	}
	
	//_________________________________________END Tester Methods_____________________________________________________

//	public static void main(String[] args) throws IOException, ClassNotFoundException {
//		CommentCounter c = new CommentCounter("C:\\Users\\Max\\workspace\\CommentCounter\\test\\input_files\\pythonTest1.py");		
//		
//		System.out.println(c.getFileType());
//		System.out.println(c.Analyze());
//	}

}
