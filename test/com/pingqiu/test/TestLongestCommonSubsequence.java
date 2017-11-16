package com.pingqiu.test;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.*;
import com.pingqiu.LongestCommonSubsequence;

public class TestLongestCommonSubsequence {

	@Test
	public void testLengthOfLCS() {
		LongestCommonSubsequence lcs = new LongestCommonSubsequence();
		assertEquals(6, lcs.lengthOfLCS("10010101", "010110110"));
		assertEquals(4, lcs.lengthOfLCS("BDCABA", "ABCBDAB"));
	}
	
	
	@Test
	public void testOneOfLCS() {
		LongestCommonSubsequence lcs = new LongestCommonSubsequence();
	
		assertEquals(6, lcs.oneOfLCS("10010101", "010110110").length());
		assertEquals(4, lcs.oneOfLCS("BDCABA", "ABCBDAB").length());		
	}
	
	
	@Test
	public void testAllLCS() {
		LongestCommonSubsequence lcs = new LongestCommonSubsequence();
		List<String> l = lcs.allLCS("10010101", "010110110");
		for(String s : l) {
			System.out.println(s);
		}
		
		List<String> l2 = lcs.allLCS("BDCABA", "ABCBDAB");
		for(String s : l2) {
			System.out.println(s);
		}
	}
}
