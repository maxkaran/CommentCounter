package main;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CommentCounter_CountOccurencesOfTODOTest {

	@Test
	void test1() {
		String line = "TODO";
		int count = CommentCounter.testCountOccurencesOfTODO(line);
		assertEquals(1, count);
	}

	@Test
	void test2() {
		String line = "sTODO";
		int count = CommentCounter.testCountOccurencesOfTODO(line);
		System.out.println(count);
		assertEquals(0, count);
	}
	
	@Test
	void test3() {
		String line = "TODOs";
		int count = CommentCounter.testCountOccurencesOfTODO(line);
		assertEquals(0, count);
	}
	
	@Test
	void test4() {
		String line = "TODO TODO TODO";
		int count = CommentCounter.testCountOccurencesOfTODO(line);
		assertEquals(3, count);
	}
	
	@Test
	void test5() {
		String line = "TODO:";
		int count = CommentCounter.testCountOccurencesOfTODO(line);
		assertEquals(1, count);
	}
	
	@Test
	void test6() {
		String line = "TODO,";
		int count = CommentCounter.testCountOccurencesOfTODO(line);
		assertEquals(1, count);
	}
	
	@Test
	void test7() {
		String line = "//TODO,";
		int count = CommentCounter.testCountOccurencesOfTODO(line);
		assertEquals(1, count);
	}
	
	@Test
	void test8() {
		String line = "/*TODO*/";
		int count = CommentCounter.testCountOccurencesOfTODO(line);
		assertEquals(1, count);
	}
	
	@Test
	void test9() {
		String line = "//TODOTODOTODO";
		int count = CommentCounter.testCountOccurencesOfTODO(line);
		assertEquals(0, count);
	}
	
	@Test
	void test10() {
		String line = "TODOTODOTODO";
		int count = CommentCounter.testCountOccurencesOfTODO(line);
		assertEquals(0, count);
	}
}
