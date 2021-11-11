/*
 * *
 *  * Created by Rafsan Ahmad on 11/11/21, 5:03 PM
 *  * Copyright (c) 2021 . All rights reserved.
 *
 */

package javaclasses.Tree;

import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

public class VerticalSumBinaryTree {
    /*Problem Description

You are given the root of a binary tree A.

You have to find the vertical sum of the tree.

A vertical sum denotes an array of sum of the different verticals of a binary tree,

where the leftmost vertical sum is the first element of the array and rightmost vertical is the last.

Problem Constraints
1 <= Number of nodes in the binary tree <= 105
1 <= Ai <= 10^3

Input Format
The first argument is the root of a binary tree A.

Output Format
Return an array denoting the vertical sum of the binary tree.


Example Input
Input 1:
A =     1
      /   \
     2     3
    / \   / \
   4   5 6   7
Input 2:

A =     1
       /
      2
     /
    3


Example Output
Output 1:
[4, 2, 12, 3, 7]
Output 2:

[3, 2, 1]


Example Explanation
Explanation 1:
The element 4 is present in the leftmost vertical.
The middle vertical consists of 3 elements 1, 5, 6.
The resultant array is [4, 2, 12, 3, 7].
Explanation 2:

The leftmost vertical is the element 3. The next verticals are 2 and 1.
Hence, the resultant array is [3, 2, 1].
*/

    /*Approach: We need to check the Horizontal Distances from the root for all nodes.
    If two nodes have the same Horizontal Distance (HD), then they are on the same vertical line.
    The idea of HD is simple. HD for root is 0, a right edge (edge connecting to right subtree) is considered as
    +1 horizontal distance and a left edge is considered as -1 horizontal distance. For example,
    in the above tree, HD for Node 4 is at -2, HD for Node 2 is -1, HD for 5 and 6 is 0 and HD for node 7 is +2.
    We can do an in-order traversal of the given Binary Tree.
    While traversing the tree, we can recursively calculate HDs.
    We initially pass the horizontal distance as 0 for root. For left subtree,
    we pass the Horizontal Distance as Horizontal distance of root minus 1. For right subtree,
    we pass the Horizontal Distance as Horizontal Distance of root plus 1.
    Following is Java implementation for the same. HashMap is used to store the vertical sums for different
    horizontal distances. */

    public int[] verticalSum(TreeNode root) {
        // base case
        if (root == null) {
            return new int[0];
        }

        // Creates an empty TreeMap hM
        TreeMap<Integer, Integer> hM = new TreeMap<Integer, Integer>();

        // Calls the VerticalSumUtil() to store the
        // vertical sum values in hM
        verticalSumUtil(root, 0, hM);

        int[] result = new int[hM.size()];
        int index = 0;
        for (Map.Entry<Integer, Integer> entry : hM.entrySet()) {
            result[index] = entry.getValue();
            index++;
        }
        return result;
    }

    public void verticalSumUtil(TreeNode root, int horizontalDistance, TreeMap<Integer, Integer> map) {
        // base case
        if (root == null) {
            return;
        }

        // Store the values in hM for left subtree
        verticalSumUtil(root.left, horizontalDistance - 1, map);

        // Update vertical sum for hD of this node
        int prevSum = (map.get(horizontalDistance) == null) ? 0 : map.get(horizontalDistance);
        map.put(horizontalDistance, prevSum + root.val);

        // Store the values in hM for right subtree
        verticalSumUtil(root.right, horizontalDistance + 1, map);
    }

    public static void main(String[] args) {
        VerticalSumBinaryTree tree = new VerticalSumBinaryTree();
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);
        int[] result = tree.verticalSum(root);
        System.out.println(Arrays.toString(result));
    }
}
