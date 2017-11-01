package com.pingqiu;

import java.util.Collections;

public class BinarySearchTree<ValueType extends Comparable> {
    private Node<ValueType> root;
    
    public BinarySearchTree() {
        // empty
    }
    
    public Node<ValueType> getRoot() {
        return root;
    }
    
    public Node<ValueType> insert(ValueType newValue) {
        Node<ValueType> p = null;
        Node<ValueType> curr = root;
        while(curr != null) {
            int diff = curr.getValue().compareTo(newValue);
            if(diff == 0) {
                // already have the same value
                return curr;
            } else if (diff < 0) {
                p = curr;
                curr = curr.right;
            } else {
                p = curr;
                curr = curr.left;
            }
        }
        
        Node<ValueType> newNode = new Node<ValueType>(newValue);
        newNode.setParent(p);
        if (p == null) {
            root = newNode;
        } else {
            int diff = p.getValue().compareTo(newValue);
            if (diff < 0) {
                p.right = newNode;
            } else {
                p.left = newNode;
            }
        }
        
        return newNode;
    }
    
    public Node<ValueType> find(ValueType value) {
        return find(value, root);
    }
    
    public Node<ValueType> find(ValueType value, Node<ValueType> rootNode) {
        Node<ValueType> n = rootNode;
        while(n != null) {
            int diff = n.getValue().compareTo(value);
            if (diff == 0) {
                return n;
            } else if (diff < 0) {
                n = n.right;
            } else {
                n = n.left;
            }
        }
        return null;
    }
    
    public Node<ValueType> delete(ValueType value) {
        return delete(value, root);
    }
    
    public Node<ValueType> delete(ValueType value, Node<ValueType> rootNode) {
        Node<ValueType> n = find(value, rootNode);
        if (n == null) {
            return null;
        }

        Node<ValueType> replacement = null;
        if (n.getLeft() == null && n.getRight() == null) {
             replacement = null;
        } else if (n.getLeft() != null && n.getRight() != null) {
            replacement = successor(n);
            n.setValue(replacement.getValue());
            delete(replacement.getValue(), n.getRight());
            return n;
        } else if (n.getLeft() == null) {
            replacement = n.getRight();
        } else {
            replacement = n.getLeft();
        }
        
        if(replacement != null) {
            replacement.setParent(n.getParent());
        }
        
        if(n.getParent() != null) {
            if(n == n.getParent().getLeft()) {
                n.getParent().setLeft(replacement);
            } else {
                n.getParent().setRight(replacement);
            }
        } else {
            root = replacement;
        }
        
        return n;
    }
    
    
    public Node<ValueType> min() {
        return min(root);
    }
    
    public Node<ValueType> min(Node<ValueType> node) {
        while (node != null && node.getLeft() != null) {
            node = node.getLeft();
        }
        return node;
    }
    
    public Node<ValueType> max() {
        return max(root);
    }
    
    public Node<ValueType> max(Node<ValueType> node) {
        while (node != null && node.getRight() != null) {
            node = node.getRight();
        }
        return node;
    }
    
    public Node<ValueType> predecessor(Node<ValueType> node) {
        if(node == null) {
            return null;
        }
        
        if(node.getLeft() != null) {
            return max(node.getLeft());
        } else {
            Node<ValueType> p = node.getParent();
            
            while(p != null && node == p.getLeft()) {
                node = p;
                p = p.getParent();
            }
            
            return p;
        }
    }

    public Node<ValueType> successor(Node<ValueType> node) {
        if(node == null) {
            return null;
        }
        
        if(node.getRight() != null) {
            return min(node.getRight());
        } else {
            Node<ValueType> p = node.getParent();
            
            while(p != null && node == p.getRight()) {
                node = p;
                p = p.getParent();
            }
            
            return p;
        }
    }
    
    public static class Node <ValueType extends Comparable> {
        private ValueType value; 
        private Node<ValueType> left, right, parent;

        public Node(ValueType value) {
            this.value = value;
        }

        public Node(ValueType value, Node<ValueType> left, Node<ValueType> right, Node<ValueType> parent) {
            this.value = value;
            this.left = left;
            this.right = right;
            this.parent = parent;
        }

        public ValueType getValue() {
            return value;
        }
        public void setValue(ValueType value) {
            this.value = value;
        }
        public Node getLeft() {
            return left;
        }
        public void setLeft(Node left) {
            this.left = left;
        }
        public Node getRight() {
            return right;
        }
        public void setRight(Node right) {
            this.right = right;
        }
        public Node<ValueType> getParent() {
            return parent;
        }
        public void setParent(Node<ValueType> parent) {
            this.parent = parent;
        }
    }

}
