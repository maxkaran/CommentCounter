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

}
