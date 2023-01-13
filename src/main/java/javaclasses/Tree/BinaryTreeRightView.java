/*
 * * Binary Tree Right View
 *  * Created by Rafsan Ahmad on 10/25/21, 9:49 PM
 *  * Copyright (c) 2021 . All rights reserved.
 *
 */

package javaclasses.Tree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class BinaryTreeRightView {
    //https://leetcode.com/problems/binary-tree-right-side-view/description/
    /*Given a Binary Tree, print Right view of it. Right view of a Binary Tree is set of nodes visible when tree is
    visited from Right side.


    Iterative Implementation
In the iterative version, perform a level order traversal on the tree. We can modify level order traversal to maintain
nodes at the current level. Then if the current node is the first node of the current level, print it.

Right view of following tree is 1 3 7 8

          1
       /     \
     2        3
   /   \     /  \
  4     5   6    7
                  \
                   8
   */

    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> list = new ArrayList<>();

        if (root == null) return list;

        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            int i = 0;

            while (i < size) {
                TreeNode curr = queue.poll();
                if (i == size - 1) list.add(curr.val);

                if (curr.left != null) queue.add(curr.left);
                if (curr.right != null) queue.add(curr.right);

                i++;
            }
        }

        return list;
    }

    // Iterative function to print the left view of a given binary tree
    public void leftView(TreeNode root) {
        // return if the tree is empty
        if (root == null) {
            return;
        }

        // create an empty queue and enqueue the root node
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);

        // to store the current node
        TreeNode curr;

        // loop till queue is empty
        while (!queue.isEmpty()) {
            // calculate the total number of nodes at the current level
            int size = queue.size();
            int i = 0;

            // process every node of the current level and enqueue their
            // non-empty left and right child
            while (i++ < size) {
                curr = queue.poll();

                // if this is the first node of the current level, print it
                if (i == 1) {
                    System.out.print(curr.val + " ");
                }

                if (curr.left != null) {
                    queue.add(curr.left);
                }

                if (curr.right != null) {
                    queue.add(curr.right);
                }
            }
        }
    }

    public static void main(String[] args) {
        BinaryTreeRightView tree = new BinaryTreeRightView();
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);
        root.right.left.right = new TreeNode(8);

        System.out.println(tree.rightSideView(root));
        tree.leftView(root);
    }
}
