package main;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import main.CommentCounter;

class CommentCounter_FileTypeTest {
	
	@Test
	void testExtension1() throws ClassNotFoundException, IOException {
		CommentCounter comment = new CommentCounter("test.txt");
		assertEquals("txt", comment.getFileType());
	}
	
	@Test
	void testExtension2() throws ClassNotFoundException, IOException {
		CommentCounter comment = new CommentCounter(".test");
		assertEquals(null, comment.getFileType());		
	}

	@Test
	void testExtension3() throws ClassNotFoundException, IOException {
		CommentCounter comment = new CommentCounter(".test.txt");
		assertEquals(null, comment.getFileType());		
	}
	
	@Test
	void testExtension4() throws ClassNotFoundException, IOException {
		CommentCounter comment = new CommentCounter("test");
		assertEquals(null, comment.getFileType());		
	}
	
	
	@Test
	void testExtension5() throws ClassNotFoundException, IOException {
		CommentCounter comment = new CommentCounter("zippedfile.tar.gz");
		assertEquals("gz", comment.getFileType());
	}
	
	//test a number of common coding language extensions
	@Test
	void testExtension6() throws ClassNotFoundException, IOException {
		CommentCounter comment = new CommentCounter("test.java");
		assertEquals("java", comment.getFileType());
	}
	@Test
	void testExtension7() throws ClassNotFoundException, IOException {
		CommentCounter comment = new CommentCounter("test.cpp");
		assertEquals("cpp", comment.getFileType());
	}
	@Test
	void testExtension8() throws ClassNotFoundException, IOException {
		CommentCounter comment = new CommentCounter("test.py");
		assertEquals("py", comment.getFileType());
	}

}
