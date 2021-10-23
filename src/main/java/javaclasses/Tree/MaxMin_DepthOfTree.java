package javaclasses.Tree;

public class MaxMin_DepthOfTree {
    //Leetcode 104
    /*Given the root of a binary tree, return its maximum depth.

A binary tree's maximum depth is the number of nodes along the longest path from the root node down to the
farthest leaf node.

Example 1:
Input: root = [3,9,20,null,null,15,7]
         3
        / \
       9  20
          / \
        15   7

Output: 3

Example 2:
Input: root = [1,null,2]
Output: 2

Example 3:
Input: root = []
Output: 0

Example 4:
Input: root = [0]
Output: 1*/


    public int maxDepth(TreeNode node) {
        return maxDepthHelp(node);
    }

    public int maxDepthHelp(TreeNode node) {
        if (node == null) return 0;
        if (node.left == null && node.right == null) return 1;
        int lDepth = Integer.MIN_VALUE;
        int rDepth = Integer.MIN_VALUE;
        if (node.left != null) lDepth = maxDepthHelp(node.left) + 1;
        if (node.right != null) rDepth = maxDepthHelp(node.right) + 1;
        return Math.max(lDepth, rDepth);
    }

    //Leetcode 111
    //https://www.interviewbit.com/problems/min-depth-of-binary-tree/
    /*
    Given a binary tree, find its minimum depth.

The minimum depth is the number of nodes along the shortest path from the root node down to the nearest leaf node.
Note: A leaf is a node with no children.

 Example 1:
Input: root = [3,9,20,null,null,15,7]
         3
        / \
       9  20
          / \
        15   7
Output: 2

Example 2:
Input: root = [2,null,3,null,4,null,5,null,6]
Output: 5
     */

    public int minDepth(TreeNode node) {
        return minDepthHelp(node);
    }

    public int minDepthHelp(TreeNode node) {
        if (node == null) return 0;
        if (node.left == null && node.right == null) return 1;
        int lDepth = Integer.MAX_VALUE;
        int rDepth = Integer.MAX_VALUE;
        if (node.left != null) lDepth = minDepthHelp(node.left) + 1;
        if (node.right != null) rDepth = minDepthHelp(node.right) + 1;
        return Math.min(lDepth, rDepth);
    }

    public static void main(String[] args) {
        MaxMin_DepthOfTree tree = new MaxMin_DepthOfTree();
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);
        System.out.println("Max depth: " + tree.maxDepth(root));
        System.out.println("Min Depth: " + tree.minDepth(root));
    }
}
