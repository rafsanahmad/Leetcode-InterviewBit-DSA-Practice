/*
 * *
 *  * Second Minimum Node In a Binary Tree.java
 *  * Created by Rafsan Ahmad on 4/14/22, 8:53 PM
 *  * Copyright (c) 2022 . All rights reserved.
 *
 */

package javaclasses.Tree;

public class SecondMinimumNodeInBinaryTree {
    //Leetcode 671
    /*Given a non-empty special binary tree consisting of nodes with the non-negative value, where each node in
    this tree has exactly two or zero sub-node. If the node has two sub-nodes, then this node's value is the smaller
    value among its two sub-nodes. More formally,
    the property root.val = min(root.left.val, root.right.val) always holds.

Given such a binary tree, you need to output the second minimum value in the set made of all the nodes' value in the
whole tree.

If no such second minimum value exists, output -1 instead.

Example 1:
          2
        /   \
       2     5
           /  \
          5    7
Input: root = [2,2,5,null,null,5,7]
Output: 5
Explanation: The smallest value is 2, the second smallest value is 5.

Example 2:
          2
        /   \
       2     2
Input: root = [2,2,2]
Output: -1
Explanation: The smallest value is 2, but there isn't any second smallest value.
*/
    static Long ans = Long.MAX_VALUE;

    public int findSecondMinimumValue(TreeNode root) {
        if (root == null) return -1;
        int rootVal = root.val;
        findSecondMinimumDFS(root, rootVal);
        return ans < Long.MAX_VALUE ? Math.toIntExact(ans) : -1;
    }

    public void findSecondMinimumDFS(TreeNode node, int rootVal) {
        if (node != null) {
            if (rootVal < node.val && node.val < ans) {
                ans = (long) node.val;
            } else if (rootVal == node.val) {
                findSecondMinimumDFS(node.left, rootVal);
                findSecondMinimumDFS(node.right, rootVal);
            }
        }
    }

    public static void main(String[] args) {
        SecondMinimumNodeInBinaryTree tree = new SecondMinimumNodeInBinaryTree();
        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(2);
        root.right = new TreeNode(5);
        root.right.left = new TreeNode(5);
        root.right.left = new TreeNode(7);
        System.out.println(tree.findSecondMinimumValue(root));

        ans = Long.MAX_VALUE;
        TreeNode root2 = new TreeNode(2);
        root2.left = new TreeNode(2);
        root2.right = new TreeNode(2);
        System.out.println(tree.findSecondMinimumValue(root2));
    }

}
