/*
 * *
 *  * Count Nodes Equal to Average of Subtree.java
 *  * Created by Rafsan Ahmad on 12/12/23, 2:46 AM
 *  * Copyright (c) 2023 . All rights reserved.
 *  *
 *
 */

package javaclasses.Tree;

import javaclasses.Utility.TestClass;

public class CountNodesEqualToAverageSubtree {
    //https://leetcode.com/problems/count-nodes-equal-to-average-of-subtree/
    /*Given the root of a binary tree, return the number of nodes where the value of the node is
    equal to the average of the values in its subtree.

Note:

The average of n elements is the sum of the n elements divided by n and rounded down to the
 nearest integer.A subtree of root is a tree consisting of root and all of its descendants.


Example 1:
          4
       /     \
     8        5
   /   \     /  \
  0    1         6

Input: root = [4,8,5,0,1,null,6]
Output: 5
Explanation:
For the node with value 4: The average of its subtree is (4 + 8 + 5 + 0 + 1 + 6) / 6 = 24 / 6 = 4.
For the node with value 5: The average of its subtree is (5 + 6) / 2 = 11 / 2 = 5.
For the node with value 0: The average of its subtree is 0 / 1 = 0.
For the node with value 1: The average of its subtree is 1 / 1 = 1.
For the node with value 6: The average of its subtree is 6 / 1 = 6.
Example 2:


Input: root = [1]
Output: 1
Explanation: For the node with value 1: The average of its subtree is 1 / 1 = 1.


Constraints:

The number of nodes in the tree is in the range [1, 1000].
0 <= Node.val <= 1000*/

    int result = 0;

    public int averageOfSubtree(TreeNode root) {
        if (root == null) return 0;
        avgSubtreeHelper(root);
        return result;
    }

    public int[] avgSubtreeHelper(TreeNode node) {
        if (node == null) return new int[]{0, 0};
        int[] left = avgSubtreeHelper(node.left);
        int[] right = avgSubtreeHelper(node.right);

        int sum = node.val + left[0] + right[0];
        int count = 1 + left[1] + right[1];

        if (sum / count == node.val) result++;
        return new int[]{sum, count};
    }

    public static void main(String[] args) {
        CountNodesEqualToAverageSubtree subtree = new CountNodesEqualToAverageSubtree();

        TreeNode node = new TreeNode(4);
        node.left = new TreeNode(8);
        node.right = new TreeNode(5);
        node.left.left = new TreeNode(0);
        node.left.right = new TreeNode(1);
        node.right.right = new TreeNode(6);

        System.out.println(subtree.averageOfSubtree(node));
    }
}
