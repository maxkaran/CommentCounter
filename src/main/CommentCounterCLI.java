package main;

import java.io.File;
import java.io.IOException;
import java.nio.file.ReadOnlyFileSystemException;
import java.util.Scanner;

public class CommentCounterCLI {
	private CommentCounter commentCounter;
	Scanner scanner;
	private String filepath;
	
	public CommentCounterCLI() throws ClassNotFoundException, IOException, filetypeNotInCommentSetException{
		scanner = new Scanner(System.in);

		System.out.println("Welcome to Comment Counter CLI by Max Karan! Analyze your code with unparalleled efficiency.");
	}
	
	public void readNewFile() throws ClassNotFoundException, IOException, filetypeNotInCommentSetException, notValidFiletypeException {
		System.out.println("Choose a file: Either enter the filepath manually, or just hit enter to use the file explorer");
		String input = scanner.nextLine();
		if(input.equals("") || input.equals(" ")) {
			FileOpener fileopener = new FileOpener();
			input = fileopener.getFilePath();
			System.out.println("You have chosen: " +input);
			input = new File(input).getAbsoluteFile().toString();

		}
		analyzeFile(input);
	}
	
	public void analyzeFile(String filepath) throws IOException, ClassNotFoundException, filetypeNotInCommentSetException, notValidFiletypeException {
		try {
			commentCounter = new CommentCounter(filepath);
		} catch (ClassNotFoundException | IOException | filetypeNotInCommentSetException e) {
			//e.printStackTrace();
			if(e instanceof filetypeNotInCommentSetException) { //if filetype is not in commentSet
				System.out.println("This is not a valid file extension for analysis, would you like to add it? (Y/N)");
				String input = scanner.next();
				input = input.toLowerCase(); //make it all lowercase
				while(true){
					if(input.equals("yes") || input.equals("y")) {
						addFileType(commentCounter.getFileType());
						commentCounter = new CommentCounter(filepath);
						System.out.println("Performing analysis now...\n");
						System.out.println(commentCounter.Analyze()+"\nAnalysis Complete!");
						return;
					}else if(input.equals("no") || input.equals("n")) {
						System.out.println("Aborting Analysis");
						return;
					}
				}
			}else if (e instanceof IOException) {
				System.out.println("Incorrect filename");
				return;
			}
		} catch (notValidFiletypeException e) {
			System.out.println(e.getMessage()+"\n");
			return;
		}
		System.out.println("Performing analysis now...\n");
		System.out.println(commentCounter.Analyze()+"\n\nAnalysis Complete!\n");
	}

	private void addFileType(String fileExtension) {
		System.out.println("Please enter the single comment line token if there is one and just hit enter if there isn't");
		System.out.println("i.e. // is the single comment token for Java, C, Javascript and many more languages");
		String single = scanner.next();
		
		System.out.println("Please enter the multiline comment START token if there is one and leave blank if there isn't");
		System.out.println("i.e. /* is the token for Java, C, Javascript and many more languages, for HTML <!-- is the token");
		String multiStart = scanner.next();
		
		System.out.println("Please enter the multiline comment END token if there is one and leave blank if there isn't");
		System.out.println("i.e. */ is the token for Java, C, Javascript and many more languages, for HTML --> is the token");
		String multiEnd = scanner.next();
		
		try {
			commentCounter.addLanguage(fileExtension, single, multiStart, multiEnd);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) throws ClassNotFoundException, IOException, filetypeNotInCommentSetException, notValidFiletypeException {
		CommentCounterCLI cli = new CommentCounterCLI();
		CommentCounter commentcounter;
		String input = " ";
		
		while(!input.equals("exit")) {
			cli.readNewFile();
			
			System.out.println("Type exit to quit program, otherwise hit enter");
			input = cli.scanner.nextLine();
		}
		
		cli.scanner.close();
		
	}
}