/*
 * * Serialize Deserialize Binary Tree.java
 *  * Created by Rafsan Ahmad on 10/25/21, 9:49 PM
 *  * Copyright (c) 2021 . All rights reserved.
 *
 */

package javaclasses.Tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

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

    //Approach: Using Preorder Traversal
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null) {
            return null;
        }
        Stack<TreeNode> s = new Stack<>();
        s.push(root);

        List<String> l = new ArrayList<>();
        while (!s.isEmpty()) {
            TreeNode t = s.pop();
            // If current node is NULL, store marker
            if (t == null) {
                l.add("#");
            } else {
                // Else, store current node and recur for
                // its children
                l.add("" + t.val);
                s.push(t.right);  //Key point to note - to reverse the order while serializing
                s.push(t.left);
            }
        }
        return String.join(",", l);
    }

    int index = 0;

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data == null)
            return null;
        String[] arr = data.split(",");
        return helper(arr);
    }

    public TreeNode helper(String[] arr) {
        if (arr[index].equals("#"))
            return null;
        // create node with this item and recur for children
        TreeNode root = new TreeNode(Integer.parseInt(arr[index]));
        index++;
        root.left = helper(arr);
        index++;
        root.right = helper(arr);
        return root;
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
