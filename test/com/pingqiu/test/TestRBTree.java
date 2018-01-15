package com.pingqiu.test;

import com.pingqiu.RBTree;
import com.pingqiu.RBTree.RBTreeNode;
import org.junit.Test;

public class TestRBTree {	
	
	@Test
	public void testinsert() {
		RBTree<Integer> tree = new RBTree<Integer>();
        tree.insert(new RBTreeNode(25));
        tree.insert(new RBTreeNode(13));
        tree.insert(new RBTreeNode(78));
        tree.insert(new RBTreeNode(36));
        tree.insert(new RBTreeNode(89));
        tree.insert(new RBTreeNode(11));
        tree.insert(new RBTreeNode(14));
        tree.insert(new RBTreeNode(33));
        tree.insert(new RBTreeNode(35));
        tree.insert(new RBTreeNode(47));
        tree.insert(new RBTreeNode(42));
        tree.insert(new RBTreeNode(48));
        tree.insert(new RBTreeNode(45));

        tree.preorderTraversal(tree.getRoot());
        tree.inorderTraversal(tree.getRoot());
	}

}
