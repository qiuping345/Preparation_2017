package com.pingqiu.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.pingqiu.Util;

public class TestUtil {

	@Test
	public void testIsSortedAscend_OK_no_duplicate() {
		int[] arr = new int[] {1, 3, 7, 8, 22, 28, 3445, 7777, 99911};
		assert(Util.isSortedAscend(arr));
	}

	@Test
	public void testIsSortedAscend_OK_with_duplicate() {
		int[] arr = new int[] {1, 3, 7, 8, 22, 22, 28, 3445, 3445, 7777, 99911};
		assert(Util.isSortedAscend(arr));
	}

	@Test
	public void testIsSortedAscend_fail_with_duplicate() {
		int[] arr = new int[] {1, 3, 7, 8, 22, 21, 22, 28, 3445, 3445, 7777, 99911};
		assertFalse(Util.isSortedAscend(arr));
	}
	
	@Test
	public void testIsSortedDescend_OK_no_duplicate() {
		int[] arr = new int[] {1008, 999, 998, 889, 733, 100, 85, 23, 12, 9};
		assert(Util.isSortedDescend(arr));
	}
	
	@Test
	public void testIsSortedDescend_OK_with_duplicate() {
		int[] arr = new int[] {1008, 999, 998, 889, 889, 733, 100, 85, 85, 23, 12, 9};
		assert(Util.isSortedDescend(arr));
	}

	@Test
	public void testIsSortedDescend_fail() {
		int[] arr = new int[] {1008, 999, 998, 999,889, 889, 733, 100, 85, 85, 23, 12, 9};
		assertFalse(Util.isSortedDescend(arr));
	}
	
	
}
