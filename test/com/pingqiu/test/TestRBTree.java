package com.pingqiu.test;

import static org.junit.Assert.*;

import java.util.Comparator;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import sun.jvm.hotspot.utilities.*;
//import com.pingqiu.RBTree;

public class TestRBTree {

	RBTree tree = new RBTree();
	
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}
	

	@Test
	public void testInsert() {
		tree.insertNode(new RBNode(13));
		tree.insertNode(new RBNode(5));
		tree.insertNode(new RBNode(12));
		tree.insertNode(new RBNode(17));
		tree.insertNode(new RBNode(8));
		
		tree.print();
		tree.verify();


	}

}
