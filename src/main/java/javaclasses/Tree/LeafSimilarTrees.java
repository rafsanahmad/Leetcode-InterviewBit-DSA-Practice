/*
 * *
 *  * Leaf Similar Trees.java
 *  * Created by Rafsan Ahmad on 12/10/22, 12:18 AM
 *  * Copyright (c) 2022 . All rights reserved.
 *
 */

package javaclasses.Tree;

import java.util.ArrayList;
import java.util.List;

public class LeafSimilarTrees {
    //https://leetcode.com/problems/leaf-similar-trees/description/
    /*Consider all the leaves of a binary tree, from left to right order, the values of those leaves form a leaf value
    sequence.

           3
        /     \
       5       1
     /  \     /  \
    6    2   9   8
       /  \
      7   4

For example, in the given tree above, the leaf value sequence is (6, 7, 4, 9, 8).

Two binary trees are considered leaf-similar if their leaf value sequence is the same.

Return true if and only if the two given trees with head nodes root1 and root2 are leaf-similar.

Example 1:
Input: root1 = [3,5,1,6,2,9,8,null,null,7,4], root2 = [3,5,1,6,7,4,2,null,null,null,null,null,null,9,8]
Output: true

Example 2:
Input: root1 = [1,2,3], root2 = [1,3,2]
Output: false*/

    List<Integer> root1Leafs = new ArrayList<>();
    List<Integer> root2Leafs = new ArrayList<>();

    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        postOrderTraversal(root1, 1);
        postOrderTraversal(root2, 2);
        return root1Leafs.equals(root2Leafs);
    }

    private void postOrderTraversal(TreeNode node, int type) {
        if (node != null) {
            postOrderTraversal(node.left, type);
            postOrderTraversal(node.right, type);
            if (type == 1 && node.left == null && node.right == null) {
                root1Leafs.add(node.val);
            } else if (type == 2 && node.left == null && node.right == null) {
                root2Leafs.add(node.val);
            }
        }
    }

    public static void main(String[] args) {
        LeafSimilarTrees trees = new LeafSimilarTrees();
        TreeNode node = new TreeNode(1);
        node.left = new TreeNode(2);
        node.right = new TreeNode(3);

        TreeNode node2 = new TreeNode(1);
        node2.left = new TreeNode(3);
        node2.right = new TreeNode(2);

        System.out.println(trees.leafSimilar(node, node2));
    }
}
