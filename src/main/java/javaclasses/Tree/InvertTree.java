package javaclasses.Tree;

import java.util.LinkedList;
import java.util.Queue;

public class InvertTree {
    //Leetcode 226
    /*Given the root of a binary tree, invert the tree, and return its root.

Example 1:
Input: root = [4,2,7,1,3,6,9]
Output: [4,7,2,9,6,3,1]

Example 2:
Input: root = [2,1,3]
Output: [2,3,1]

Example 3:
Input: root = []
Output: []*/

    //Iterative approach
    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return root;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode temp = queue.poll();

            //swap
            TreeNode tempNode = temp.left;
            temp.left = temp.right;
            temp.right = tempNode;

            if (temp.left != null)
                queue.add(temp.left);
            if (temp.right != null) {
                queue.add(temp.right);
            }
        }
        return root;
    }

    //Recursive Approach
    public TreeNode invertTree_Recursive(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode right = invertTree(root.right);
        TreeNode left = invertTree(root.left);
        root.left = right;
        root.right = left;
        return root;
    }
}
