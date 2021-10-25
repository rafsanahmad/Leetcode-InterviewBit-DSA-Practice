/*
 * *
 *  * Created by Rafsan Ahmad on 10/25/21, 9:49 PM
 *  * Copyright (c) 2021 . All rights reserved.
 *
 */

package javaclasses.Tree;

import java.util.ArrayList;
import java.util.List;

public class KthSmallestElementInBst {
    //Leetcode 230
    //res/kthtree1.jpeg
    /*Given the root of a binary search tree, and an integer k, return the kth (1-indexed) smallest
    element in the tree.

Example 1:
Input: root = [3,1,4,null,2], k = 1
Output: 1

Example 2:
Input: root = [5,3,6,2,4,null,null,1], k = 3
Output: 3*/

    public int kthSmallest(TreeNode root, int k) {
        if (root == null || k < 1) {
            return 0;
        }
        List<Integer> nodes = new ArrayList<>();
        InOrderTraversal(root, nodes);
        return nodes.get(k - 1);
    }

    //Recursive In-order traversal - gives Ascending order list
    public void InOrderTraversal(TreeNode node, List<Integer> list) {
        if (node == null) {
            return;
        }
        InOrderTraversal(node.left, list);
        list.add(node.val);
        InOrderTraversal(node.right, list);
    }
}
