/*
 * *
 *  * Created by Rafsan Ahmad on 12/2/21, 5:01 PM
 *  * Copyright (c) 2021 . All rights reserved.
 *
 */

package javaclasses.Tree;

import java.util.ArrayList;
import java.util.List;

public class MaximumAverageSubTree {
    /*Given a binary tree, find the subtree with maximum average. Return the root of the subtree.
It's guaranteed that there is only one subtree with maximum average.

Example

Given a binary tree:

     1
   /   \
 -5     11
 / \   /  \
1   2 4    -2
return the node11*/

    class TreeSum {
        int sum;
        int count;

        public TreeSum(int sum, int count) {
            this.sum = sum;
            this.count = count;
        }
    }

    public TreeNode maxSubtree;
    public TreeSum maxResult;

    public TreeNode findSubtree(TreeNode root) {
        helper(root);
        return maxSubtree;
    }

    private TreeSum helper(TreeNode node) {
        if (node == null) {
            return new TreeSum(0, 0);
        }
        TreeSum leftResult = helper(node.left);
        TreeSum rightResult = helper(node.right);
        int sum = leftResult.sum + rightResult.sum + node.val;
        int count = leftResult.count + rightResult.count + 1;
        TreeSum result = new TreeSum(sum, count);
        if (maxSubtree == null || maxResult.sum * count < sum * maxResult.count) {
            maxSubtree = node;
            maxResult = result;
        }
        return result;
    }

    public List<Integer> printTree(TreeNode root) {
        ArrayList<Integer> result = new ArrayList<>();
        return traversalUtil(root, result);
    }

    public List<Integer> traversalUtil(TreeNode node, ArrayList<Integer> list) {
        if (node != null) {
            list.add(node.val);
            traversalUtil(node.left, list);
            traversalUtil(node.right, list);
        }
        return list;
    }

    public static void main(String[] args) {
        MaximumAverageSubTree tree = new MaximumAverageSubTree();
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(-5);
        root.right = new TreeNode(11);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(2);
        root.right.left = new TreeNode(4);
        root.right.right = new TreeNode(-2);
        TreeNode result = tree.findSubtree(root);

        //Traverse tree
        List<Integer> nodes = tree.printTree(result);
        System.out.println(nodes);
    }
}
