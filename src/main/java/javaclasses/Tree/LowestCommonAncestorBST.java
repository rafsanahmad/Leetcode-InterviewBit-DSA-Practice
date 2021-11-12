/*
 * *
 *  * Created by Rafsan Ahmad on 10/25/21, 9:49 PM
 *  * Copyright (c) 2021 . All rights reserved.
 *
 */

package javaclasses.Tree;

public class LowestCommonAncestorBST {
    //Lowest Common Ancestor
    //Leetcode 235
    /*
Given a binary search tree (BST), find the lowest common ancestor (LCA) of two given nodes in the BST.

According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between two nodes p and q
as the lowest node in T that has both p and q as descendants (where we allow a node to be a descendant of itself).”


Example 1:
Input: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 8
Output: 6
Explanation: The LCA of nodes 2 and 8 is 6.

Example 2:
Input: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 4
Output: 2
Explanation: The LCA of nodes 2 and 4 is 2, since a node can be a descendant of itself according to the LCA
definition.

Example 3:
Input: root = [2,1], p = 2, q = 1
Output: 2*/

/*Algorithm

Start traversing the tree from the root node.
If both the nodes p and q are in the right subtree,
then continue the search with right subtree starting step 1.
If both the nodes p and q are in the left subtree,
then continue the search with left subtree starting step 1.
If both step 2 and step 3 are not true, this means we have found the node which is common to node p's and q's subtrees.
and hence we return this common node as the LCA.
*/

    //Iterative Approach
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return root;
        }
        int pVal = p.val;
        int qVal = q.val;
        while (root != null) {
            if (pVal < root.val && qVal < root.val) {
                //left subtree
                root = root.left;
            } else if (pVal > root.val && qVal > root.val) {
                //right subtree
                root = root.right;
            } else {
                //found LCA
                return root;
            }
        }
        return null;
    }

    //Recursive Approach
    public TreeNode lowestCommonAncestorRecursive(TreeNode root, TreeNode p, TreeNode q) {
        // Value of current node or parent node.
        int parentVal = root.val;

        // Value of p
        int pVal = p.val;

        // Value of q;
        int qVal = q.val;

        if (pVal > parentVal && qVal > parentVal) {
            // If both p and q are greater than parent
            return lowestCommonAncestor(root.right, p, q);
        } else if (pVal < parentVal && qVal < parentVal) {
            // If both p and q are lesser than parent
            return lowestCommonAncestor(root.left, p, q);
        } else {
            // We have found the split point, i.e. the LCA node.
            return root;
        }
    }

}
