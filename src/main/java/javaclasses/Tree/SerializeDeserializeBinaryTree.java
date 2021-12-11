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

    /*Serialization is to store tree in a file so that it can be later restored.
    The structure of tree must be maintained. Deserialization is reading tree back from file.

    Let the marker for NULL pointers be '-1'
Input:
     12
    /
  13
Output: 12 13 -1 -1 -1

Input:
      20
    /   \
   8     22
Output: 20 8 -1 -1 22 -1 -1

Input:
         20
       /
      8
     / \
    4  12
      /  \
     10  14
Output: 20 8 4 -1 -1 12 10 -1 -1 14 -1 -1 -1

Input:
          20
         /
        8
      /
    10
    /
   5
Output: 20 8 10 5 -1 -1 -1 -1 -1

Input:
          20
            \
             8
              \
               10
                 \
                  5
Output: 20 -1 8 -1 10 -1 5 -1 -1
    */

    TreeNode root;

    // Encodes a tree to a single string.
    public static String serialize(TreeNode root) {
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
                s.push(t.right);
                s.push(t.left);
            }
        }
        return String.join(",", l);
    }

    static int t;

    // Decodes your encoded data to tree.
    public static TreeNode deserialize(String data) {
        if (data == null)
            return null;
        t = 0;
        String[] arr = data.split(",");
        return helper(arr);
    }

    public static TreeNode helper(String[] arr) {
        if (arr[t].equals("#"))
            return null;
        // create node with this item and recur for children
        TreeNode root = new TreeNode(Integer.parseInt(arr[t]));
        t++;
        root.left = helper(arr);
        t++;
        root.right = helper(arr);
        return root;
    }

    // A simple inorder traversal used for testing the
    // constructed tree
    static void inorder(TreeNode root) {
        if (root != null) {
            inorder(root.left);
            System.out.print(root.val + " ");
            inorder(root.right);
        }
    }

    /* Driver program to test above functions*/
    public static void main(String args[]) {
        // Let us construct a tree shown in the above figure
        SerializeDeserializeBinaryTree tree = new SerializeDeserializeBinaryTree();
        tree.root = new TreeNode(20);
        tree.root.left = new TreeNode(8);
        tree.root.right = new TreeNode(22);
        tree.root.left.left = new TreeNode(4);
        tree.root.left.right = new TreeNode(12);
        tree.root.left.right.left = new TreeNode(10);
        tree.root.left.right.right = new TreeNode(14);

        String serialized = serialize(tree.root);
        System.out.println("Serialized view of the tree:");
        System.out.println(serialized);
        System.out.println();

        // Let us deserialize the stored tree into root1
        TreeNode t = deserialize(serialized);

        System.out.println("Inorder Traversal of the tree constructed from serialized String:");
        inorder(t);
    }
}
