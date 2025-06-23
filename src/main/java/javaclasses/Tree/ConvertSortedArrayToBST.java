/*
 * *
 *  * Convert Sorted Array To BST.java
 *  * Created by Rafsan Ahmad on 12/13/21, 7:10 PM
 *  * Copyright (c) 2021 . All rights reserved.
 *
 */

package javaclasses.Tree;

public class ConvertSortedArrayToBST {
    //https://leetcode.com/problems/convert-sorted-array-to-binary-search-tree/description/
    /*Given an integer array nums where the elements are sorted in ascending order, convert it to a height-balanced
    binary search tree.

A height-balanced binary tree is a binary tree in which the depth of the two subtrees of every node never differs
by more than one.

Example 1:
                  0
                /   \
              -3     9
             /      /
          -10      5

Input: nums = [-10,-3,0,5,9]
Output: [0,-3,9,-10,null,5]
Explanation: [0,-10,5,null,-3,null,9] is also accepted:
                  0
                /   \
              -10    5
               \      \
               -3      9


Example 2:
Input: nums = [1,3]
Output: [3,1]
Explanation: [1,3] and [3,1] are both a height-balanced BSTs.
*/

    public TreeNode sortedArrayToBST(int[] nums) {
        int len = nums.length;
        return sortedArrayToBSTUtil(nums, 0, len - 1);
    }

    private TreeNode sortedArrayToBSTUtil(int[] arr, int start, int end) {

        /* Base Case */
        if (start > end) {
            return null;
        }

        /* Get the middle element and make it root */
        int mid = (start + end) / 2;
        TreeNode node = new TreeNode(arr[mid]);

        /* Recursively construct the left subtree and make it
         left child of root */
        node.left = sortedArrayToBSTUtil(arr, start, mid - 1);

        /* Recursively construct the right subtree and make it
         right child of root */
        node.right = sortedArrayToBSTUtil(arr, mid + 1, end);

        return node;
    }

    /* A utility function to print preorder traversal of BST */
    void preOrder(TreeNode node) {
        if (node == null) {
            return;
        }
        System.out.print(node.val + " ");
        preOrder(node.left);
        preOrder(node.right);
    }

    public static void main(String[] args) {
        ConvertSortedArrayToBST bst = new ConvertSortedArrayToBST();
        int[] nums = {-10, -3, 0, 5, 9};
        TreeNode result = bst.sortedArrayToBST(nums);
        bst.preOrder(result);
    }

}
