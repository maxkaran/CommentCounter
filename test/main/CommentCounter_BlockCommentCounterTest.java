package main;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.Test;

class CommentCounter_BlockCommentCounterTest {

	@Test
	void test1() throws ClassNotFoundException, IOException {
		CommentCounter c = new CommentCounter("test");
		int count = c.testBlockCommentsCount("/**//**//**/");
		assertEquals(3, count);
	}
	
	@Test
	void test2() throws ClassNotFoundException, IOException {
		CommentCounter c = new CommentCounter("test");
		int count = c.testBlockCommentsCount("/*/**/*//**//**/");
		assertEquals(3, count);
	}
	
	@Test
	void test3() throws ClassNotFoundException, IOException {
		CommentCounter c = new CommentCounter("test");
		int count = c.testBlockCommentsCount("/**/*//**//*");
		assertEquals(3, count);
	}
	
	@Test
	void test4() throws ClassNotFoundException, IOException {
		CommentCounter c = new CommentCounter("test");
		int count = c.testBlockCommentsCount("/*/**//**//*");
		assertEquals(3, count);
	}
	
	@Test
	void test5() throws ClassNotFoundException, IOException {
		CommentCounter c = new CommentCounter("test");
		int count = c.testBlockCommentsCount("/*//*/");
		assertEquals(1, count);
	}
	
	@Test
	void test6() throws ClassNotFoundException, IOException {
		CommentCounter c = new CommentCounter("test");
		int count = c.testBlockCommentsCount("/*");
		assertEquals(1, count);
	}
	
	@Test
	void test7() throws ClassNotFoundException, IOException {
		CommentCounter c = new CommentCounter("test");
		int count = c.testBlockCommentsCount("*//*");
		assertEquals(1, count);
	}
	
	@Test
	void test8() throws ClassNotFoundException, IOException {
		CommentCounter c = new CommentCounter("test");
		int count = c.testBlockCommentsCount("/* jkfpsjgfkl;sgjfk sjklfaf *");
		assertEquals(1, count);
	}
	
	@Test
	void test9() throws ClassNotFoundException, IOException {
		CommentCounter c = new CommentCounter("test");
		int count = c.testBlockCommentsCount("/* jkfpsjgfkl;sgjfk sjklfaf */");
		assertEquals(1, count);
	}
	
	@Test
	void test10() throws ClassNotFoundException, IOException {
		CommentCounter c = new CommentCounter("test");
		int count = c.testBlockCommentsCount("/*jkfpsjgfkl;sgjfk");
		assertEquals(1, count);
	}
	
	@Test
	void test11() throws ClassNotFoundException, IOException {
		CommentCounter c = new CommentCounter("test");
		int count = c.testBlockCommentsCount("test /*");
		assertEquals(1, count);
	}
	
	@Test
	void test12() throws ClassNotFoundException, IOException {
		CommentCounter c = new CommentCounter("test");
		int count = c.testBlockCommentsCount("test");
		assertEquals(0, count);
	}
	
	@Test
	void test13() throws ClassNotFoundException, IOException {
		CommentCounter c = new CommentCounter("test");
		int count = c.testBlockCommentsCount("test */");
		assertEquals(0, count);
	}
	
	@Test
	void test14() throws ClassNotFoundException, IOException {
		CommentCounter c = new CommentCounter("test");
		int count = c.testBlockCommentsCount("*/ test /*");
		assertEquals(1, count);
	}

}
