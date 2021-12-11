/*
 * * Tree Traversal.java
 *  * Created by Rafsan Ahmad on 10/25/21, 9:49 PM
 *  * Copyright (c) 2021 . All rights reserved.
 *
 */

package javaclasses.Tree;

import java.util.ArrayList;
import java.util.List;

public class TreeTraversal {

    //Leetcode 144
    /*Given the root of a binary tree, return the preorder traversal of its nodes' values.
    * Example 1:
Input: root = [1,null,2,3]
Output: [1,2,3]

Example 2:
Input: root = []
Output: []

Example 3:
Input: root = [1]
Output: [1]

Example 4:
Input: root = [1,2]
Output: [1,2]

Example 5:
Input: root = [1,null,2]
Output: [1,2]*/

    public List<Integer> preorderTraversal(TreeNode root) {
        ArrayList<Integer> result = new ArrayList<>();
        return traversalUtil(root, result, "pre");
    }

    //Leetcode 93
    /*
    * Given the root of a binary tree, return the inorder traversal of its nodes' values.

     Example 1:
    Input: root = [1,null,2,3]
    Output: [1,3,2]
    Example 2:

    Input: root = []
    Output: []
    Example 3:

    Input: root = [1]
    Output: [1]*/

    public List<Integer> inorderTraversal(TreeNode root) {
        ArrayList<Integer> result = new ArrayList<>();
        return traversalUtil(root, result, "in");
    }

    //Leetcode 145
    /*Given the root of a binary tree, return the postorder traversal of its nodes' values.
Example 1:
Input: root = [1,null,2,3]
Output: [3,2,1]

Example 2:
Input: root = []
Output: []

Example 3:
Input: root = [1]
Output: [1]

Example 4:
Input: root = [1,2]
Output: [2,1]

Example 5:
Input: root = [1,null,2]
Output: [2,1]*/

    public List<Integer> postorderTraversal(TreeNode root) {
        ArrayList<Integer> result = new ArrayList<>();
        return traversalUtil(root, result, "post");
    }

    public ArrayList<Integer> traversalUtil(TreeNode node, ArrayList<Integer> list, String type) {
        switch (type) {
            case "pre":
                //Root->Left->Right
                if (node != null) {
                    list.add(node.val);
                    traversalUtil(node.left, list, type);
                    traversalUtil(node.right, list, type);
                }
                break;
            case "in":
                //Left->Root->Right
                if (node != null) {
                    traversalUtil(node.left, list, type);
                    list.add(node.val);
                    traversalUtil(node.right, list, type);
                }
                break;
            case "post":
                //Left->Right->Root
                if (node != null) {
                    traversalUtil(node.left, list, type);
                    traversalUtil(node.right, list, type);
                    list.add(node.val);
                }
                break;
        }
        return list;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = null;
        root.right = new TreeNode(2);
        root.right.left = new TreeNode(3);
        TreeTraversal traversal = new TreeTraversal();
        System.out.println("Pre order traversal: " + traversal.preorderTraversal(root));
        System.out.println("In order traversal: " + traversal.inorderTraversal(root));
        System.out.println("Post order traversal: " + traversal.postorderTraversal(root));
    }
}
