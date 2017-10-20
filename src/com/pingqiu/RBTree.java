package com.pingqiu;

public class RBTree {
	
	private RBTreeNode root;
	private int blackHeight;
	
	public static class RBTreeNode {
		public RBTreeNode left, right, parent;
		public boolean isBlack;
		public int size;
		public Comparable value;
	}
	
	public boolean isEmpty() {
		return root == null;
	}
	
	public void leftRotate(RBTreeNode node) {
		RBTreeNode rChild = node.right;
		node.right = rChild.left;
		
		if(rChild.left != null) {
			rChild.left.parent = node;
		}
		
		rChild.parent = node.parent;
		
		if(node.parent == null) {
			root = rChild;
		} else if (node == node.parent.left) {
			node.parent.left = rChild;
		} else {
			node.parent.right = rChild;
		}
		
		rChild.left = node;
		node.parent = rChild;
	}

	public void rightRotate(RBTreeNode node) {
		RBTreeNode lChild = node.left;
		node.left = lChild.right;
		
		if(lChild.right != null) {
			lChild.right.parent = node;
		}
		
		lChild.parent = node.parent;
		
		if(node.parent == null) {
			root = lChild;
		} else if(node == node.parent.left) {
			node.parent.left = lChild;
		} else {
			node.parent.right = lChild;
		}
		
		lChild.right = node;
		node.parent = lChild;
	}
	
	public void insert(RBTreeNode newNode) {
		RBTreeNode parent = null;
		RBTreeNode current = root;
		while (current != null) {
			parent = current;
			if (newNode.value.compareTo(current.value) < 0) {
				current = current.left;
			} else {
				current = current.right;
			}
		}
		newNode.parent = parent;
		if (parent == null) {
			root = newNode;
		} else if (newNode.value.compareTo(parent.value) < 0) {
			parent.left = newNode;
		} else {
			parent.right = newNode;
		}
		
		newNode.left = null;
		newNode.right = null;
		newNode.isBlack = false;
		insertFixUp(newNode);
	}
	
	private void insertFixUp(RBTreeNode newNode) {
		while (!newNode.parent.isBlack) {
			if (newNode.parent == newNode.parent.parent.left) {
				RBTreeNode rightUncle = newNode.parent.parent.right;
				if (!rightUncle.isBlack) {
					newNode.parent.isBlack = true;
					rightUncle.isBlack = true;
					newNode.parent.parent.isBlack = false;
					newNode = newNode.parent.parent;
				} else {
					if (newNode == newNode.parent.right) {
						newNode = newNode.parent;
						leftRotate(newNode);
					}
					
					newNode.parent.isBlack = true;
					newNode.parent.parent.isBlack = false;
					rightRotate(newNode);
				}
			} else {
				RBTreeNode leftAunt = newNode.parent.parent.left;
				if (!leftAunt.isBlack) {
					newNode.parent.isBlack = true;
					leftAunt.isBlack = true;
					newNode.parent.parent.isBlack = false;
					newNode = newNode.parent.parent;
				} else {
					if (newNode == newNode.parent.left) {
						newNode = newNode.parent;
					    rightRotate(newNode);
					}
					
					newNode.parent.isBlack = true;
					newNode.parent.parent.isBlack = false;
					leftRotate(newNode);
				}
			}
		}
		root.isBlack = true;
	}

}
