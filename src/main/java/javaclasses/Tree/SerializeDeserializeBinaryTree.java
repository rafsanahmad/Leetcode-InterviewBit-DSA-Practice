/*
 * * Serialize Deserialize Binary Tree.java
 *  * Created by Rafsan Ahmad on 10/25/21, 9:49 PM
 *  * Copyright (c) 2021 . All rights reserved.
 *
 */

package javaclasses.Tree;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class SerializeDeserializeBinaryTree {
    //Leetcode 297
    /*Serialization is the process of converting a data structure or object into a sequence of bits so that it can
    be stored in a file or memory buffer, or transmitted across a network connection link to be reconstructed later
    in the same or another computer environment.

Design an algorithm to serialize and deserialize a binary tree. There is no restriction on how your serialization/
deserialization algorithm should work. You just need to ensure that a binary tree can be serialized to a string and '
this string can be deserialized to the original tree structure.

         1
       /   \
      2     3
          /  \
         4    5

Input: root = [1,2,3,null,null,4,5]
Output: [1,2,3,null,null,4,5]
    */

    // Encodes a tree to a single string.
    public String splitter = ",";
    public String nullNode = "NN";
    StringBuilder builder;

    public String serialize(TreeNode root) {
        builder = new StringBuilder();
        return buildString(builder, root);
    }

    //Pre-order traversal
    public String buildString(StringBuilder sb, TreeNode node) {
        if (node == null) {
            sb.append(nullNode).append(splitter);
            return sb.toString();
        }

        sb.append(node.val).append(splitter);
        buildString(sb, node.left);
        buildString(sb, node.right);

        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        Queue<String> queue = new LinkedList<>();
        queue.addAll(Arrays.asList(data.split(splitter)));
        return buildTree(queue);
    }

    public TreeNode buildTree(Queue<String> queue) {
        if (queue.isEmpty())
            return null;

        String v = queue.poll();
        if (v.equals(nullNode)) {
            return null;
        }

        TreeNode node = new TreeNode(Integer.parseInt(v));
        node.left = buildTree(queue);
        node.right = buildTree(queue);
        return node;
    }

    // A simple inorder traversal used for testing the constructed tree
    public void preOrder(TreeNode root) {
        if (root != null) {
            System.out.print(root.val + " ");
            preOrder(root.left);
            preOrder(root.right);
        }
    }

    public static void main(String[] args) {
        // Let us construct a tree shown in the above figure
        SerializeDeserializeBinaryTree bst = new SerializeDeserializeBinaryTree();
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.right.left = new TreeNode(4);
        root.right.right = new TreeNode(5);

        String serialized = bst.serialize(root);
        System.out.println("Serialized view of the tree:");
        System.out.println(serialized);

        // Let us deserialize the stored tree into root1
        TreeNode t = bst.deserialize(serialized);
        System.out.println("Preorder Traversal of the tree constructed from serialized String:");
        bst.preOrder(t);
    }
}
