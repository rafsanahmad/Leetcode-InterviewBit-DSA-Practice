/*
 * *
 *  * Created by Rafsan Ahmad on 10/25/21, 9:49 PM
 *  * Copyright (c) 2021 . All rights reserved.
 *
 */

package javaclasses.Tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class LevelOrderTraversal {

    //Leetcode 102
    /*Given the root of a binary tree, return the level order traversal of its nodes' values.
    (i.e., from left to right, level by level).
Example 1:
Input: root = [3,9,20,null,null,15,7]
Output: [[3],[9,20],[15,7]]

Example 2:
Input: root = [1]
Output: [[1]]

Example 3:
Input: root = []
Output: []*/

    /* Given a binary tree. Print
     its nodes in level order
     using array for implementing queue  */
    void printLevelOrder(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode tempNode = queue.poll();
            System.out.print(tempNode.val + " ");

            /*Enqueue left child */
            if (tempNode.left != null) {
                queue.add(tempNode.left);
            }

            /*Enqueue right child */
            if (tempNode.right != null) {
                queue.add(tempNode.right);
            }
        }
    }

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();

        if (root == null) {
            return result;
        }

        LinkedList<TreeNode> nodeQueue = new LinkedList<>();
        LinkedList<Integer> levelQueue = new LinkedList<>();

        nodeQueue.offer(root);
        levelQueue.offer(1);//start from 1

        while (!nodeQueue.isEmpty()) {
            TreeNode node = nodeQueue.poll();
            int level = levelQueue.poll();

            List<Integer> inner = null;
            if (level > result.size()) {
                inner = new ArrayList<>();
                result.add(inner);
            } else {
                inner = result.get(level - 1);
            }

            inner.add(node.val);

            if (node.left != null) {
                nodeQueue.offer(node.left);
                levelQueue.offer(level + 1);
            }

            if (node.right != null) {
                nodeQueue.offer(node.right);
                levelQueue.offer(level + 1);
            }
        }

        return result;
    }

    public static void main(String args[]) {
        LevelOrderTraversal traversal = new LevelOrderTraversal();
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);

        System.out.print("Level order traversal of binary tree is - ");
        traversal.printLevelOrder(root);
        System.out.println();
        System.out.println("Level order traversal using inner list is -" + traversal.levelOrder(root));
    }
}
