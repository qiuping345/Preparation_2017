package com.pingqiu;

import java.util.ArrayList;
import java.util.List;

public class GeneralTreeToBinaryTree {
    private static class GeneralNode {
        public int val;
        public List<GeneralNode> children = new ArrayList();

        public GeneralNode(int v) {
            val = v;
        }

        public void addChild(GeneralNode child) {
            children.add(child);
        }
    }


    private static class BTNode {
        public int val;
        public BTNode left;
        public BTNode right;

        public BTNode(int v) {
            val = v;
        }
    }


    public static BTNode convertGeneralTree(GeneralNode gnode) {
        if (gnode == null) {
            return null;
        }
        BTNode btnode = new BTNode(gnode.val);
        BTNode last = null;
        for (int i = gnode.children.size() - 1; i >= 0; i--) {
            BTNode newNode = convertGeneralTree(gnode.children.get(i));
            newNode.right = last;
            last = newNode;
        }
        btnode.left = last;
        return btnode;
    }

    public static void preorder(BTNode root) {
        if(root == null) {
            return;
        }
        System.out.println(root.val);
        preorder(root.left);
        preorder(root.right);
    }

    public static void inorder(BTNode root) {
        if(root == null) {
            return;
        }
        inorder(root.left);
        System.out.println(root.val);
        inorder(root.right);
    }




    public static void main(String[] args) {
        //build the general tree
        GeneralNode groot = new GeneralNode(3);

        GeneralNode node8 = new GeneralNode(8);
        GeneralNode node4 = new GeneralNode(4);
        node4.addChild(node8);

        GeneralNode node9 = new GeneralNode(9);
        GeneralNode node10 = new GeneralNode(10);
        GeneralNode node5 = new GeneralNode(5);
        node5.addChild(node9);
        node5.addChild(node10);

        GeneralNode node6 = new GeneralNode(6);

        GeneralNode node11 = new GeneralNode(11);
        GeneralNode node12 = new GeneralNode(12);
        GeneralNode node13 = new GeneralNode(13);
        GeneralNode node7 = new GeneralNode(7);
        node7.addChild(node11);
        node7.addChild(node12);
        node7.addChild(node13);

        groot.addChild(node4);
        groot.addChild(node5);
        groot.addChild(node6);
        groot.addChild(node7);

        BTNode root = GeneralTreeToBinaryTree.convertGeneralTree(groot);
        GeneralTreeToBinaryTree.preorder(root);
        System.out.println("=====================");
        GeneralTreeToBinaryTree.inorder(root);
    }

}