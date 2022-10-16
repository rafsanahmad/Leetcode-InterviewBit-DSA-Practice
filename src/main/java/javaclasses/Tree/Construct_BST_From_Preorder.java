/*
 * * Construct BST From Preorder Traversal.java
 *  * Created by Rafsan Ahmad on 11/12/21, 12:54 AM
 *  * Copyright (c) 2021 . All rights reserved.
 *
 */

package javaclasses.Tree;

import java.util.ArrayList;
import java.util.List;

public class Construct_BST_From_Preorder {
    //https://www.interviewbit.com/problems/construct-bst-from-preorder/
    /*Problem Description

Given an integer array A with distinct elements, which represents the preorder traversal of a binary search tree,

construct the tree and return its root.

A binary search tree is a binary tree where for every node, any descendant of Node.left has a value strictly
less than Node.val, and any descendant of Node.right has a value strictly greater than Node.val.

A preorder traversal of a binary tree displays the value of the node first, then traverses Node.left,
then traverses Node.right.


**Problem Constraints**
1 <= |A| <= 10^5
1 <= A.val <= 10^9
The given array is a valid preorder traversal of a BST.


**Input Format**
The first argument is an integer array denoting the preorder traversal.


**Output Format**
Return the root of the Binary Search Tree.


**Example Input**
Input 1:
A = [2, 1, 4, 3, 5]
Input 2:

A = [1, 2, 3]


**Example Output**
Output 1:
    2
   / \
  1   4
     / \
    3   5
Output 2:

      1
       \
        2
         \
          3

**Example Explanation**
Explanation 1:
We can see that is the tree created by the given pre order traversal.
Explanation 2:

We can see that is the tree created by the given pre order traversal.*/

    //Using Recursion
    //Time Complexity = 0(n^2)
    public TreeNode bstFromPreorder(int[] preorder) {
        int n = preorder.length;
        if (n == 0) return null;
        //Take first value as root node
        TreeNode root = new TreeNode(preorder[0]);

        //Traverse from index 1
        for (int i = 1; i < n; i++) {
            root = insert(root, preorder[i]);
        }
        return root;
    }

    private TreeNode insert(TreeNode root, int val) {
        if (root == null) return new TreeNode(val);

        if (val < root.val) {
            root.left = insert(root.left, val);
        } else {
            root.right = insert(root.right, val);
        }

        return root;
    }


    /*Optimized Approach:
    * Let’s improve our solution and solve this problem in O(n) time complexity.
    * We are already familiar with the property of a Binary Search Tree.
    * The idea here is to construct a binary search tree by traversing a preorder array.
    * We take the min and max range, Initially it is Integer.MIN_VALUE and Integer.MAX_VALUE.
    * In Binary Search Tree, the left child should be less than the root node, so the max range would be the
      value of a root node.
    * The right child should be greater than the root node so it’s min range would be the value of a root node.
    * In this way we construct a binary search tree.*/

    int index = 0;

    //Time commplexity = 0(n)
    public TreeNode bstFromPreorderOptimized(int[] preorder) {
        if (preorder.length == 0) {
            return null;
        }
        return constructBSTHelper(preorder, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }


    TreeNode constructBSTHelper(int[] preorder, int min, int max) {
        if (index >= preorder.length) {
            return null;
        }

        int elem = preorder[index];
        TreeNode node = null;

        if (elem > min && elem < max) {
            node = new TreeNode(elem);
            index++;
            node.left = constructBSTHelper(preorder, min, elem);
            node.right = constructBSTHelper(preorder, elem, max);
        }

        return node;
    }

    //Print Pre-order traversal of the tree
    public List<Integer> printTree(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        return traversalUtil(root, list);
    }

    public List<Integer> traversalUtil(TreeNode node, List<Integer> list) {
        if (node != null) {
            list.add(node.val);
            traversalUtil(node.left, list);
            traversalUtil(node.right, list);
        }
        return list;
    }

    public static void main(String[] args) {
        Construct_BST_From_Preorder order = new Construct_BST_From_Preorder();
        int[] arr = {2, 1, 4, 3, 5};
        TreeNode root = order.bstFromPreorderOptimized(arr);
        System.out.println(order.printTree(root));

        TreeNode root2 = order.bstFromPreorder(arr);
        System.out.println(order.printTree(root2));
    }
}
