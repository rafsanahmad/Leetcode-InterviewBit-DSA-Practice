/*
 * * Binary tree Path Sum.java
 *  * Created by Rafsan Ahmad on 10/25/21, 9:49 PM
 *  * Copyright (c) 2021 . All rights reserved.
 *
 */

package javaclasses.Tree;

public class PathSum {
    //https://leetcode.com/problems/path-sum/description/
    /*
    * Given the root of a binary tree and an integer targetSum, return true
    * if the tree has a root-to-leaf path such that adding up all the values along the path equals targetSum.
A leaf is a node with no children.

Example 1:
Input: root = [5,4,8,11,null,13,4,7,2,null,null,null,1], targetSum = 22
Output: true

Example 2:
Input: root = [1,2,3], targetSum = 5
Output: false

Example 3:
Input: root = [1,2], targetSum = 0
Output: false
*/
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return false;
        }
        if (root.left == null && root.right == null && targetSum - root.val == 0) {
            return true;
        } else {
            return hasPathSum(root.left, targetSum - root.val) ||
                    hasPathSum(root.right, targetSum - root.val);
        }
    }

    public static void main(String[] args) {
        PathSum sum = new PathSum();
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        System.out.println(sum.hasPathSum(root, 5));
        System.out.println(sum.hasPathSum(root, 4));
    }
}
