package com.pingqiu.test;
import org.junit.Test;

import com.pingqiu.LongestIncreasingSubsequence;
public class TestLongestIncreasingSubsequence {

	@Test
	public void testLengthOfLIS_nlgn() {
		LongestIncreasingSubsequence inst = new LongestIncreasingSubsequence();
		inst.lengthOfLIS_nlgn(new int[] {1,3,8,6,7,9,4,10,5,6}); 
	}
}
