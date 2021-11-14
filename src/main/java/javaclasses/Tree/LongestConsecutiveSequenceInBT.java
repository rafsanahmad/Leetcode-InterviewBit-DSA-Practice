/*
 * *
 *  * Created by Rafsan Ahmad on 11/15/21, 12:35 AM
 *  * Copyright (c) 2021 . All rights reserved.
 *
 */

package javaclasses.Tree;

public class LongestConsecutiveSequenceInBT {
    /*Given a Binary Tree find the length of the longest path which comprises of nodes with consecutive values
    in increasing order. Every node is considered as a path of length 1.
Examples:

        6                            1
      /   \                       /    \
          9                      2      4
         / \                    /     /  \
        7  10                  3     5    6
            \                            /
            11                          7

LCP - 9,10,11. Length = 3     LCP - 1,2,3. Length = 3

In below diagram binary tree with longest consecutive path(LCP) are shown :
We can solve above problem recursively. At each node we need information of its parent node,
if current node has value one more than its parent node then it makes a consecutive path,
at each node we will compare node’s value with its parent value and update the longest consecutive path accordingly.
For getting the value of parent node, we will pass the (node_value + 1) as an argument to the recursive
method and compare the node value with this argument value, if satisfies, update the current length of
consecutive path otherwise reinitialize current path length by 1. */

    int maxLength = 0;

    public int longestConsecutive(TreeNode root) {
        if (root == null)
            return 0;

        // call utility method with current length 0
        longestConsecutiveUtil(root, 0, root.val, maxLength);

        return maxLength;
    }

    // Utility method to return length of longest
    // consecutive sequence of tree
    private void longestConsecutiveUtil(TreeNode root, int curlength, int expected, int length) {
        if (root == null)
            return;

        // if root data has one more than its parent
        // then increase current length
        if (root.val == expected)
            curlength++;
        else
            curlength = 1;

        // update the maximum by current length
        maxLength = Math.max(length, curlength);

        // recursively call left and right subtree with
        // expected value 1 more than root data
        longestConsecutiveUtil(root.left, curlength, root.val + 1, length);
        longestConsecutiveUtil(root.right, curlength, root.val + 1, length);
    }

    public static void main(String[] args) {
        LongestConsecutiveSequenceInBT tree = new LongestConsecutiveSequenceInBT();

        TreeNode root = new TreeNode(6);
        root.right = new TreeNode(9);
        root.right.left = new TreeNode(7);
        root.right.right = new TreeNode(10);
        root.right.right.right = new TreeNode(11);

        System.out.println(tree.longestConsecutive(root));
    }
}
