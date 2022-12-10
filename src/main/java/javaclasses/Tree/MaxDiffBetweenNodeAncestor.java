/*
 * *
 *  * Maximum Difference Between Node and Ancestor.java
 *  * Created by Rafsan Ahmad on 12/10/22, 2:14 PM
 *  * Copyright (c) 2022 . All rights reserved.
 *
 */

package javaclasses.Tree;

public class MaxDiffBetweenNodeAncestor {
    //https://leetcode.com/problems/maximum-difference-between-node-and-ancestor/description/
    /*Given the root of a binary tree, find the maximum value v for which there exist different nodes a and b
    where v = |a.val - b.val| and a is an ancestor of b.

A node a is an ancestor of b if either: any child of a is equal to b or any child of a is an ancestor of b.

Example 1:

           8
        /     \
       3       10
     /  \       \
    1    6      14
       /  \     /
      4   7    13

Input: root = [8,3,10,1,6,null,14,null,null,4,7,13]
Output: 7

Explanation: We have various ancestor-node differences, some of which are given below :
|8 - 3| = 5
|3 - 7| = 4
|8 - 1| = 7
|10 - 13| = 3
Among all possible differences, the maximum value of 7 is obtained by |8 - 1| = 7.

Example 2:

           1
            \
             2
              \
               0
              /
             3

Input: root = [1,null,2,null,0,3]
Output: 3
*/

    int result = Integer.MIN_VALUE;

    public int maxAncestorDiff(TreeNode root) {
        return calculateDiff(root, Integer.MIN_VALUE, Integer.MAX_VALUE, 0);
    }

    public int calculateDiff(TreeNode node, int max, int min, int diff) {
        if (node != null) {
            max = Math.max(node.val, max);
            min = Math.min(node.val, min);
            diff = Math.max(Math.abs(max - node.val), Math.abs(min - node.val));
            calculateDiff(node.left, max, min, diff);
            calculateDiff(node.right, max, min, diff);
        }
        result = Math.max(result, diff);
        return result;
    }

    public static void main(String[] args) {
        MaxDiffBetweenNodeAncestor ancestor = new MaxDiffBetweenNodeAncestor();
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        root.right.right = new TreeNode(0);
        root.right.right.left = new TreeNode(3);

        System.out.println(ancestor.maxAncestorDiff(root));
    }
}
