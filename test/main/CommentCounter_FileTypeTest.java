package main;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import main.CommentCounter;

class CommentCounter_FileTypeTest {
	
	@Test
	void testExtension1() {
		CommentCounter comment = new CommentCounter("test.txt");
		assertEquals("txt", comment.getFileType());
	}
	
	@Test
	void testExtension2() {
		CommentCounter comment = new CommentCounter(".test");
		assertEquals(null, comment.getFileType());		
	}

	@Test
	void testExtension3() {
		CommentCounter comment = new CommentCounter(".test.txt");
		assertEquals(null, comment.getFileType());		
	}
	
	@Test
	void testExtension4() {
		CommentCounter comment = new CommentCounter("test");
		assertEquals(null, comment.getFileType());		
	}
	
	
	@Test
	void testExtension5() {
		CommentCounter comment = new CommentCounter("zippedfile.tar.gz");
		assertEquals("gz", comment.getFileType());
	}
	
	//test a number of common coding language extensions
	@Test
	void testExtension6() {
		CommentCounter comment = new CommentCounter("test.java");
		assertEquals("java", comment.getFileType());
	}
	@Test
	void testExtension7() {
		CommentCounter comment = new CommentCounter("test.cpp");
		assertEquals("cpp", comment.getFileType());
	}
	@Test
	void testExtension8() {
		CommentCounter comment = new CommentCounter("test.py");
		assertEquals("py", comment.getFileType());
	}

}
