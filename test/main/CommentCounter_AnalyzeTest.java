package main;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;

import org.junit.jupiter.api.Test;

class CommentCounter_AnalyzeTest {
	 private String readFile(String filename) {
         File f = new File(filename);
         try {
             byte[] bytes = Files.readAllBytes(f.toPath());
             return new String(bytes,"UTF-8");
         } catch (FileNotFoundException e) {
             e.printStackTrace();
         } catch (IOException e) {
             e.printStackTrace();
         }
         return "";
	 }

	@Test
	void test1() throws ClassNotFoundException, IOException, filetypeNotInCommentSetException {
		String CorrectOutput, Output;
		String InputFilename = ".\\test\\input_files\\pythonTest1.py";
		String CorrectOutputFilename = ".\\test\\input_files\\pythonTest1_out.txt";
		
		CommentCounter c = new CommentCounter(InputFilename);
		
		Output = c.Analyze();
		CorrectOutput = readFile(CorrectOutputFilename);
		
		CorrectOutput = CorrectOutput.replaceAll("\r\n", System.getProperty("line.separator")); //replace default windows line terminator with line terminator that is used in this machines OS
		
		assertEquals(CorrectOutput, Output);
		
	}
	
	@Test
	void test2() throws ClassNotFoundException, IOException, filetypeNotInCommentSetException {
		String CorrectOutput, Output;
		String InputFilename = ".\\test\\input_files\\typescriptTest1.ts";
		String CorrectOutputFilename = ".\\test\\input_files\\typescriptTest1_out.txt";
		
		CommentCounter c = new CommentCounter(InputFilename);
		
		Output = c.Analyze();
		CorrectOutput = readFile(CorrectOutputFilename);
		
		CorrectOutput = CorrectOutput.replaceAll("\r\n", System.getProperty("line.separator")); //replace default windows line terminator with line terminator that is used in this machines OS
		
		assertEquals(CorrectOutput, Output);
		
	}
	
	@Test
	void test3() throws ClassNotFoundException, IOException, filetypeNotInCommentSetException {
		String CorrectOutput, Output;
		String InputFilename = ".\\test\\input_files\\javaTest1.java";
		String CorrectOutputFilename = ".\\test\\input_files\\javaTest1_out.txt";
		
		CommentCounter c = new CommentCounter(InputFilename);
		
		Output = c.Analyze();
		CorrectOutput = readFile(CorrectOutputFilename);
		
		CorrectOutput = CorrectOutput.replaceAll("\r\n", System.getProperty("line.separator")); //replace default windows line terminator with line terminator that is used in this machines OS
		
		assertEquals(CorrectOutput, Output);
		
	}
	
	@Test
	void test4() throws ClassNotFoundException, IOException, filetypeNotInCommentSetException {
		String CorrectOutput, Output;
		String InputFilename = ".\\test\\input_files\\javaTest2.java";
		String CorrectOutputFilename = ".\\test\\input_files\\javaTest2_out.txt";
		
		CommentCounter c = new CommentCounter(InputFilename);
		
		Output = c.Analyze();
		CorrectOutput = readFile(CorrectOutputFilename);
		
		CorrectOutput = CorrectOutput.replaceAll("\r\n", System.getProperty("line.separator")); //replace default windows line terminator with line terminator that is used in this machines OS
		assertEquals(CorrectOutput, Output);
		
	}
	
	@Test
	void test5() throws ClassNotFoundException, IOException {
		String CorrectOutput, Output;
		String InputFilename = ".\\test\\input_files\\htmlTest1.html";
		String CorrectOutputFilename = ".\\test\\input_files\\htmlTest1_out.txt";
		
		CommentCounter c;
		try {
			c = new CommentCounter(InputFilename);
			Output = c.Analyze();
			CorrectOutput = readFile(CorrectOutputFilename);
			
			CorrectOutput = CorrectOutput.replaceAll("\r\n", System.getProperty("line.separator")); //replace default windows line terminator with line terminator that is used in this machines OS
			assertEquals(CorrectOutput, Output);
		} catch (filetypeNotInCommentSetException e) {
			System.out.println(e.getMessage());
		}
		
	}

}
