/*
 * *
 *  * Diameter of a Binary Tree.java
 *  * Created by Rafsan Ahmad on 5/18/24, 5:16 PM
 *  * Copyright (c) 2024 . All rights reserved.
 *  *
 *
 */

package javaclasses.Tree;

public class DiameterBinaryTree {
    //https://leetcode.com/problems/diameter-of-binary-tree/description/
    /*Given the root of a binary tree, return the length of the diameter of the tree.

The diameter of a binary tree is the length of the longest path between any two nodes in a tree.
This path may or may not pass through the root.

The length of a path between two nodes is represented by the number of edges between them.

Example 1:
          1
       /     \
     2        3
   /   \
  4     5

Input: root = [1,2,3,4,5]
Output: 3
Explanation: 3 is the length of the path [4,2,1,3] or [5,2,1,3].

Example 2:

Input: root = [1,2]
Output: 1

Constraints:
The number of nodes in the tree is in the range [1, 10^4].
-100 <= Node.val <= 100*/

    int res = 0;

    public int diameterOfBinaryTree(TreeNode root) {
        if (root == null) return 0;
        maxLength(root);
        return res;
    }

    public int maxLength(TreeNode node) {
        if (node == null) return 0;

        int left = maxLength(node.left);
        int right = maxLength(node.right);

        res = Math.max(res, left + right);
        return Math.max(left, right) + 1;
    }

    public static void main(String[] args) {
        DiameterBinaryTree tree = new DiameterBinaryTree();
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);

        root.left.right.left = new TreeNode(6);
        root.left.right.left.left = new TreeNode(7);
        root.left.right.left.left.left = new TreeNode(8);
        root.left.right.left.left.left.left = new TreeNode(9);

        System.out.println(tree.diameterOfBinaryTree(root));
    }
}
