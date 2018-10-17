package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class CommentCounterCLI {
	private CommentCounter commentCounter;
	Scanner scanner;
	public CommentCounterCLI() throws ClassNotFoundException, IOException, filetypeNotInCommentSetException{
		scanner = new Scanner(System.in);

		System.out.println("Welcome to Comment Counter CLI by Max Karan! Analyze your code with unparalleled efficiency.\n"
						 + "============================================================================================");
	}
	
	public void readNewFile() throws ClassNotFoundException, IOException, filetypeNotInCommentSetException, notValidFiletypeException {
		System.out.println("Choose a file: Either enter the filepath manually, or just hit enter to use the file explorer");
		String input = scanner.nextLine();
		if("".equals(input) || " ".equals(input)) {
			FileOpener fileopener = new FileOpener();
			input = fileopener.getFilePath();
			
			if(input != null) { //make sure they actually chose a file
				System.out.println("You have chosen: " +input);
				input = new File(input).getAbsoluteFile().toString();	
			}else {
				System.out.println("Please select a valid file\n");
				return;
			}
		}
		try {
			analyzeFile(input);
		}catch (FileNotFoundException e){
			System.out.println("Please select a valid file\n");
		}
	}
	
	//______________________________________________HELPER FUNCTIONS__________________________________________________________
	
	private void analyzeFile(String filepath) throws IOException{
		try {
			commentCounter = new CommentCounter(filepath);
		} catch (ClassNotFoundException | IOException | filetypeNotInCommentSetException e) {
			//e.printStackTrace();
			if (e instanceof IOException) {
				System.out.println("Incorrect filename");
				return;
			}
		} catch (notValidFiletypeException e) {
			System.out.println(e.getMessage()+"\n");
			return;
		}
		try {
			System.out.println("Performing analysis now...\n");
			System.out.println(commentCounter.Analyze()+"\n\nAnalysis Complete!\n");
		}catch(FileNotFoundException e) {
			System.out.println("File was not found\n");
		}
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
		String input = " ";
		
		while(!input.trim().equals("exit")) {
			cli.readNewFile();
			
			System.out.println("Type exit to quit program, otherwise hit enter");
			input = cli.scanner.nextLine();
		}
		System.out.println("\nBye");
		cli.scanner.close();
		
	}
}
