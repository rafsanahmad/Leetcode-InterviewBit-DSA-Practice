/*
 * * Lowest Common Ancestor Binary Tree.java
 *  * Created by Rafsan Ahmad on 10/25/21, 9:49 PM
 *  * Copyright (c) 2021 . All rights reserved.
 *
 */

package javaclasses.Tree;

public class LowestCommonAncestorBT {
    //Leetcode 236
    /*Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.

According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between two nodes p and q
as the lowest node in T that has both p and q as descendants (where we allow a node to be a descendant of itself).”

Example 1:

               3
            /     \
          5        1
        /  \     /  \
      6    2    0    8
          / \
         7   4

Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
Output: 3
Explanation: The LCA of nodes 5 and 1 is 3.


Example 2:
               3
            /     \
          5        1
        /  \     /  \
      6    2    0    8
          / \
         7   4

Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
Output: 5
Explanation: The LCA of nodes 5 and 4 is 5, since a node can be a descendant of itself according to the
LCA definition.

Example 3:
Input: root = [1,2], p = 1, q = 2
Output: 1
*/
    //Lowest Common Ancestor using post order traversal
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) {
            return root;
        }
        //Use post order traversal to find LCA
        TreeNode leftNode = lowestCommonAncestor(root.left, p, q);
        TreeNode rightNode = lowestCommonAncestor(root.right, p, q);

        if (leftNode != null && rightNode != null) {
            return root;
        }
        if (leftNode == null) {
            return rightNode;
        } else {
            return leftNode;
        }
    }

    public static void main(String[] args) {
        LowestCommonAncestorBT bt = new LowestCommonAncestorBT();

        TreeNode root = new TreeNode(3);
        TreeNode p1 = new TreeNode(5);
        TreeNode q1 = new TreeNode(1);
        root.left = p1;
        root.right = q1;
        root.left.left = new TreeNode(6);
        root.left.right = new TreeNode(2);
        root.right.left = new TreeNode(0);
        root.right.right = new TreeNode(8);
        root.left.right.left = new TreeNode(7);
        root.left.right.right = new TreeNode(4);

        TreeNode result = bt.lowestCommonAncestor(root, p1, q1);
        System.out.println(result.val);


        TreeNode root2 = new TreeNode(3);
        TreeNode p2 = new TreeNode(5);
        TreeNode q2 = new TreeNode(4);
        root2.left = p2;
        root2.right = new TreeNode(1);
        root2.left.left = new TreeNode(6);
        root2.left.right = new TreeNode(2);
        root2.right.left = new TreeNode(0);
        root2.right.right = new TreeNode(8);
        root2.left.right.left = new TreeNode(7);
        root2.left.right.right = q2;

        TreeNode result2 = bt.lowestCommonAncestor(root2, p2, q2);
        System.out.println(result2.val);
    }
}
