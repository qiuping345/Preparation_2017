package com.pingqiu.test;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.pingqiu.BinarySearchTree;
import com.pingqiu.BinarySearchTree.Node;

public class TestBinarySearchTree {
    @Test
    public void testInsert_root() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>();
        bst.insert(5);
        assertTrue(5 == bst.getRoot().getValue());
    }
    
    private BinarySearchTree<Integer> buildSimpleTree() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>();
        bst.insert(15);
        bst.insert(13);
        bst.insert(18);
        bst.insert(16);
        bst.insert(19);
        bst.insert(11);
        bst.insert(14);
        return bst;
    }
    
    @Test
    public void testFind_success() {
        BinarySearchTree<Integer> bst = buildSimpleTree();
        Node<Integer> node = bst.find(16);
        assertTrue(16 == node.getValue());        
    }
    
    @Test
    public void testFind_failure() {
        BinarySearchTree<Integer> bst = buildSimpleTree();
        Node<Integer> node = bst.find(17);
        assertTrue(null == node);        
    }
    
    @Test
    public void testPredecessor_mid() {
        BinarySearchTree<Integer> bst = buildSimpleTree();
        Node<Integer> node = bst.find(16);
        Node<Integer> pred = bst.predecessor(node);
        assertTrue(15 == pred.getValue());
    }

    @Test
    public void testPredecessor_min() {
        BinarySearchTree<Integer> bst = buildSimpleTree();
        Node<Integer> node = bst.find(11);
        Node<Integer> pred = bst.predecessor(node);
        assertTrue(pred == null);
    }
    
    @Test
    public void testSuccessor_mid() {
        BinarySearchTree<Integer> bst = buildSimpleTree();
        Node<Integer> node = bst.find(16);
        Node<Integer> pred = bst.successor(node);
        assertTrue(18 == pred.getValue());
    }

    @Test
    public void testPredecessor_max() {
        BinarySearchTree<Integer> bst = buildSimpleTree();
        Node<Integer> node = bst.find(19);
        Node<Integer> pred = bst.successor(node);
        assertTrue(pred == null);
    }
    
    @Test
    public void testMin() {
        BinarySearchTree<Integer> bst = buildSimpleTree();
        Node<Integer> node = bst.min();
        assertTrue(11 == node.getValue());    
    }
    
    @Test
    public void testMax() {
        BinarySearchTree<Integer> bst = buildSimpleTree();
        Node<Integer> node = bst.max();
        assertTrue(19 == node.getValue());    
    }
    
    @Test
    public void testDelete_leaf() {
        BinarySearchTree<Integer> bst = buildSimpleTree();
        bst.delete(14);
        Node<Integer> p = bst.find(13);
        assert(p.getRight() == null);
    }
    
    
    @Test
    public void testDelete_withOnlyLeft() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>();
        bst.insert(25);
        bst.insert(13);
        bst.insert(78);
        bst.insert(36);
        bst.insert(89);
        bst.insert(11);
        bst.insert(14);
        bst.insert(33);
        bst.insert(35);
        
        bst.delete(36);
        assertTrue(null == bst.find(36));
        Node<Integer> p = bst.find(78).getLeft();
        assertTrue(33 == p.getValue());
    }
    
    @Test
    public void testDelete_withOnlyRight() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>();
        bst.insert(25);
        bst.insert(13);
        bst.insert(78);
        bst.insert(36);
        bst.insert(89);
        bst.insert(11);
        bst.insert(14);
        bst.insert(47);
        bst.insert(42);
        bst.insert(48);
        
        bst.delete(36);
        assertTrue(null == bst.find(36));
        Node<Integer> p = bst.find(78).getLeft();
        assertTrue(47 == p.getValue());
    }
    
    @Test
    public void testDelete_withLeftAndRight() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>();
        bst.insert(25);
        bst.insert(13);
        bst.insert(78);
        bst.insert(36);
        bst.insert(89);
        bst.insert(11);
        bst.insert(14);
        bst.insert(33);
        bst.insert(35);
        bst.insert(47);
        bst.insert(42);
        bst.insert(48);
        bst.insert(45);
        
        bst.delete(36);
        assertTrue(null == bst.find(36));
        Node<Integer> p = bst.find(78).getLeft();
        assertTrue(42 == p.getValue());
        p = bst.find(47).getLeft();
        assertTrue(45 == p.getValue());
    }
    
    @Test
    public void testDelete_root() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>();
        bst.insert(25);
        bst.insert(13);
        bst.insert(78);
        bst.insert(36);
        bst.insert(89);
        bst.insert(11);
        bst.insert(14);
        bst.insert(33);
        bst.insert(35);
        bst.insert(47);
        bst.insert(42);
        bst.insert(48);
        bst.insert(45);
        
        bst.delete(25);
        assertTrue(null == bst.find(25));
        assertTrue(33 == bst.getRoot().getValue());
        Node<Integer> p = bst.find(36).getLeft();
        assertTrue(35 == p.getValue());
    }
}
