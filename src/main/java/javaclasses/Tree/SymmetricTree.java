/*
 * *
 *  * Symmetric Tree.java
 *  * Created by Rafsan Ahmad on 12/28/21, 9:34 PM
 *  * Copyright (c) 2021 . All rights reserved.
 *
 */

package javaclasses.Tree;

import java.util.LinkedList;
import java.util.Queue;

public class SymmetricTree {
    //https://leetcode.com/problems/symmetric-tree/
    /*Given the root of a binary tree, check whether it is a mirror of itself (i.e.,
    symmetric around its center).

Example 1:

          1
        /   \
       2     2
     /  \   / \
    3    4 4   3

Input: root = [1,2,2,3,4,4,3]
Output: true

Example 2:

          1
        /  \
       2    2
       \     \
        3     3

Input: root = [1,2,2,null,3,null,3]
Output: false
*/
    //Iterative
    public boolean isSymmetric(TreeNode root) {
        if (root == null) return true;
        Queue<TreeNode> left = new LinkedList();
        Queue<TreeNode> right = new LinkedList();
        left.add(root.left);
        right.add(root.right);

        while (!left.isEmpty() && !right.isEmpty()) {
            TreeNode l = left.poll();
            TreeNode r = right.poll();
            if (l == null && r == null) continue;
                //If one of them is null
            else if (l == null || r == null) return false;
            if (l.val != r.val) {
                return false;
            } else {
                //Match left with right
                left.add(l.left);
                right.add(r.right);
                left.add(l.right);
                right.add(r.left);
            }
        }
        return true;
    }

    //Recursive
    public boolean isSymmetricRecursive(TreeNode root) {
        if (root == null)
            return true;
        return isSymmetricRecursiveUtil(root.left, root.right);
    }

    public boolean isSymmetricRecursiveUtil(TreeNode l, TreeNode r) {
        if (l == null && r == null) {
            return true;
        } else if (r == null || l == null) {
            return false;
        }

        if (l.val != r.val)
            return false;

        if (!isSymmetricRecursiveUtil(l.left, r.right))
            return false;
        if (!isSymmetricRecursiveUtil(l.right, r.left))
            return false;

        return true;
    }

    public static void main(String[] args) {
        SymmetricTree tree = new SymmetricTree();
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(2);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(4);
        root.right.left = new TreeNode(4);
        root.right.right = new TreeNode(3);

        System.out.println(tree.isSymmetric(root));
        System.out.println(tree.isSymmetricRecursive(root));

        TreeNode root2 = new TreeNode(1);
        root2.left = new TreeNode(2);
        root2.right = new TreeNode(2);
        root2.left.right = new TreeNode(3);
        root2.right.right = new TreeNode(3);
        System.out.println(tree.isSymmetric(root2));
        System.out.println(tree.isSymmetricRecursive(root2));
    }
}
