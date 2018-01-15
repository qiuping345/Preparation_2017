package com.pingqiu.test;

import org.junit.Test;

import timebench.example.*;

public class TestTimeBenchRedBlackTree {

	@Test
	public void testInsert() {
		RedBlackTree rbtree = new RedBlackTree();
		rbtree.insert(2);
		rbtree.insert(5);
		rbtree.insert(7);
		rbtree.insert(11);
		rbtree.insert(1);
		rbtree.insert(14);
		
		assert(rbtree.blackHeight() > 1);
	}
	
}
