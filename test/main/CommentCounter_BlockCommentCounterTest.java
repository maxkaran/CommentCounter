package main;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CommentCounter_BlockCommentCounterTest {

	@Test
	void test1() {
		CommentCounter c = new CommentCounter("test");
		int count = c.testBlockCommentsCount("/**//**//**/");
		assertEquals(3, count);
	}
	
	@Test
	void test2() {
		CommentCounter c = new CommentCounter("test");
		int count = c.testBlockCommentsCount("/*/**/*//**//**/");
		assertEquals(3, count);
	}
	
	@Test
	void test3() {
		CommentCounter c = new CommentCounter("test");
		int count = c.testBlockCommentsCount("/**/*//**//*");
		assertEquals(3, count);
	}
	
	@Test
	void test4() {
		CommentCounter c = new CommentCounter("test");
		int count = c.testBlockCommentsCount("/*/**//**//*");
		assertEquals(3, count);
	}
	
	@Test
	void test5() {
		CommentCounter c = new CommentCounter("test");
		int count = c.testBlockCommentsCount("/*//*/");
		assertEquals(1, count);
	}
	
	@Test
	void test6() {
		CommentCounter c = new CommentCounter("test");
		int count = c.testBlockCommentsCount("/*");
		assertEquals(1, count);
	}
	
	@Test
	void test7() {
		CommentCounter c = new CommentCounter("test");
		int count = c.testBlockCommentsCount("*//*");
		assertEquals(1, count);
	}
	
	@Test
	void test8() {
		CommentCounter c = new CommentCounter("test");
		int count = c.testBlockCommentsCount("/* jkfpsjgfkl;sgjfk sjklfaf *");
		assertEquals(1, count);
	}
	
	@Test
	void test9() {
		CommentCounter c = new CommentCounter("test");
		int count = c.testBlockCommentsCount("/* jkfpsjgfkl;sgjfk sjklfaf */");
		assertEquals(1, count);
	}
	
	@Test
	void test10() {
		CommentCounter c = new CommentCounter("test");
		int count = c.testBlockCommentsCount("/*jkfpsjgfkl;sgjfk");
		assertEquals(1, count);
	}
	
	@Test
	void test11() {
		CommentCounter c = new CommentCounter("test");
		int count = c.testBlockCommentsCount("test /*");
		assertEquals(1, count);
	}
	
	@Test
	void test12() {
		CommentCounter c = new CommentCounter("test");
		int count = c.testBlockCommentsCount("test");
		assertEquals(0, count);
	}
	
	@Test
	void test13() {
		CommentCounter c = new CommentCounter("test");
		int count = c.testBlockCommentsCount("test */");
		assertEquals(0, count);
	}
	
	@Test
	void test14() {
		CommentCounter c = new CommentCounter("test");
		int count = c.testBlockCommentsCount("*/ test /*");
		assertEquals(1, count);
	}

}
