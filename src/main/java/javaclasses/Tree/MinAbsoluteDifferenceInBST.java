/*
 * *
 *  * Minimum Absolute Difference in BST.java
 *  * Created by Rafsan Ahmad on 2/18/23, 2:32 AM
 *  * Copyright (c) 2023 . All rights reserved.
 *
 */

package javaclasses.Tree;

import java.util.TreeSet;

public class MinAbsoluteDifferenceInBST {
    //https://leetcode.com/problems/minimum-absolute-difference-in-bst/description/
    /*Given the root of a Binary Search Tree (BST), return the minimum absolute difference between the values of any
    two different nodes in the tree.


Example 1:
          4
       /     \
     2        6
   /   \
  1     3
Input: root = [4,2,6,1,3]
Output: 1


Example 2:
          1
       /     \
     0       48
            /   \
           12    49
Input: root = [1,0,48,null,null,12,49]
Output: 1


Constraints:
The number of nodes in the tree is in the range [2, 10^4].
0 <= Node.val <= 10^5*/

    int result = Integer.MAX_VALUE;
    Integer prev = null;

    //Solution 1 - In-Order traverse, time complexity O(N), space complexity O(1).
    public int getMinimumDifference(TreeNode root) {
        if (root == null) return result;
        getMinimumDifference(root.left);
        if (prev != null) {
            result = Math.min(result, root.val - prev);
        }
        prev = root.val;
        getMinimumDifference(root.right);
        return result;
    }

    TreeSet<Integer> set = new TreeSet<>();
    int min = Integer.MAX_VALUE;

    /*What if it is not a BST? (Follow up of the problem)
    The idea is to put values in a TreeSet and then every time we can use O(lgN) time to lookup for the nearest values.
    Solution 2 - Pre-Order traverse, time complexity O(NlgN), space complexity O(N).
    */
    public int getMinimumDifferenceUsingTreeSet(TreeNode root) {
        if (root == null) return min;

        if (!set.isEmpty()) {
            if (set.floor(root.val) != null) {
                min = Math.min(min, root.val - set.floor(root.val));
            }
            if (set.ceiling(root.val) != null) {
                min = Math.min(min, set.ceiling(root.val) - root.val);
            }
        }

        set.add(root.val);

        getMinimumDifferenceUsingTreeSet(root.left);
        getMinimumDifferenceUsingTreeSet(root.right);

        return min;
    }

    public static void main(String[] args) {
        MinAbsoluteDifferenceInBST bst = new MinAbsoluteDifferenceInBST();
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.right = new TreeNode(6);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);
        System.out.println(bst.getMinimumDifference(root));
        System.out.println(bst.getMinimumDifferenceUsingTreeSet(root));
    }
}
