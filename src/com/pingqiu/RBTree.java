package com.pingqiu;

import com.pingqiu.BinarySearchTree.Node;

public class RBTree<ValueType extends Comparable> extends BinarySearchTree {
	
	private int blackHeight;
	
	public static class RBTreeNode<ValueType extends Comparable>
	       extends BinarySearchTree.Node {
		
		
        public RBTreeNode(ValueType value) {
            super(value);
        }

        public RBTreeNode(ValueType value, Node<ValueType> left, Node<ValueType> right, Node<ValueType> parent) {
            super(value, left, right, parent);
        }
		
		private boolean isBlack = false;
		
		public boolean isBlack() {
			return isBlack;
		}
		public boolean isRed() {
			return !isBlack;
		}
		public void setBlack() {
			isBlack = true;
		}
		public void setRed() {
			isBlack = false;
		}
		
		@Override
		public RBTreeNode getParent() {
			return (RBTreeNode) super.getParent();
		}
		
		@Override
		public RBTreeNode getLeft() {
			return (RBTreeNode) super.getLeft();
		}
		
		@Override
		public RBTreeNode getRight() {
			return (RBTreeNode) super.getRight();
		}
		
		@Override
		public String toString() {
			return super.toString() + ", color: " + (isBlack() ? "B" : "R");
		}
	}
	
	public boolean isEmpty() {
		return getRoot() == null;
	}
	
	@Override
	public RBTreeNode getRoot() {
		return (RBTreeNode) super.getRoot();
	}
	

	public void leftRotate(Node node) {
		Node rChild = node.getRight();
		node.setRight(rChild.getLeft());
		
		if(rChild.getLeft() != null) {
			rChild.getLeft().setParent(node);
		}
		
		rChild.setParent(node.getParent());
		
		if(node.getParent() == null) {
			setRoot(rChild);
		} else if (node == node.getParent().getLeft()) {
			node.getParent().setLeft(rChild);
		} else {
			node.getParent().setRight(rChild);
		}
		
		rChild.setLeft(node);
		node.setParent(rChild);
	}

	public void rightRotate(Node node) {
		Node lChild = node.getLeft();
		node.setLeft(lChild.getRight());
		
		if(lChild.getRight() != null) {
			lChild.getRight().setParent(node);
		}
		
		lChild.setParent(node.getParent());
		
		if(node.getParent() == null) {
			setRoot(lChild);
		} else if(node == node.getParent().getLeft()) {
			node.getParent().setLeft(lChild);
		} else {
			node.getParent().setRight(lChild);
		}
		
		lChild.setRight(node);
		node.setParent(lChild);
	}
	
	@Override
	public Node<ValueType> insert(Comparable value) {
		Node newNode = super.insert(value);
		insertFixUp((RBTreeNode)newNode);
		return newNode;
	}
	
	private void insertFixUp(RBTreeNode newNode) {
		RBTreeNode uncle;
		while (newNode.getParent().isRed()) {
			if (newNode.getParent() == newNode.getParent().getParent().getLeft()) {
				uncle = newNode.getParent().getParent().getRight();
				if (!uncle.isBlack()) {
					newNode.getParent().setBlack();
					uncle.setBlack();
					newNode.getParent().getParent().setRed();
					newNode = newNode.getParent().getParent();
				} else {
					if (newNode == newNode.getParent().getRight()) {
						newNode = newNode.getParent();
						leftRotate(newNode);
					}
					
					newNode.getParent().setBlack();;
					newNode.getParent().getParent().setRed();
					rightRotate(newNode.getParent().getParent());
				}
			} else {
				uncle = newNode.getParent().getParent().getLeft();
				if (!uncle.isBlack()) {
					newNode.getParent().setBlack();
					uncle.setBlack();
					newNode.getParent().getParent().setRed();
					newNode = newNode.getParent().getParent();
				} else {
					if (newNode == newNode.getParent().getLeft()) {
						newNode = newNode.getParent();
					    rightRotate(newNode);
					}
					
					newNode.getParent().setBlack();
					newNode.getParent().getParent().setRed();
					leftRotate(newNode.getParent().getParent());
				}
			}
		}
		getRoot().setBlack();
	}

	
	public String preorderTraversal(RBTreeNode node) {
		if (node == null) {
			return "";
		}
		String result = "";
		result += node.toString() + "\n";
		result += preorderTraversal(node.getLeft()) + "\n";
		result += preorderTraversal(node.getLeft());
		return result;
	}
	
	public String inorderTraversal(RBTreeNode node) {
		if (node == null) {
			return "";
		}
		String result = "";
		result += inorderTraversal(node.getLeft());
		result += "\n" + node.toString() + "\n";
		result += inorderTraversal(node.getRight());
		return result;
	}
}
