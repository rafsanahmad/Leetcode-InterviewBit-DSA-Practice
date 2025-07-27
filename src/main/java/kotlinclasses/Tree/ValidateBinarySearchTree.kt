/*
 * *
 *  * ValidateBinarySearchTree.kt
 *  * Created by Rafsan Ahmad on 7/26/25, 1:24AM
 *  * Copyright (c) 2025 . All rights reserved.
 *  *
 *
 */

package kotlinclasses.Tree

class ValidateBinarySearchTree {
    /*Given the root of a binary tree, determine if it is a valid binary search tree (BST).

A valid BST is defined as follows:

The left subtree of a node contains only nodes with keys less than the node's key.
The right subtree of a node contains only nodes with keys greater than the node's key.
Both the left and right subtrees must also be binary search trees.


Example 1:
    2
   / \
  1   3

Input: root = [2,1,3]
Output: true

Example 2:
      5
     / \
    1   4
       / \
      3   6
Input: root = [5,1,4,null,null,3,6]
Output: false
Explanation: The root node's value is 5 but its right child's value is 4.


Constraints:
The number of nodes in the tree is in the range [1, 10^4].
-2^31 <= Node.val <= 2^31 - 1*/

    //Time Complexity: O(n)
    //Space Complexity: Worst-case: O(n) (list) + O(n) (stack) → O(n)
    fun isValidBST(root: TreeNode?): Boolean {
        if (root == null) return true
        val list = mutableListOf<Int>()
        isValidBSTHelper(root, list)

        for (i in 0 until list.size - 1) {
            if (list[i] >= list[i + 1])
                return false
        }

        return true
    }

    fun isValidBSTHelper(node: TreeNode?, list: MutableList<Int>) {
        if (node != null) {
            isValidBSTHelper(node.left, list)
            list.add(node.`val`)
            isValidBSTHelper(node.right, list)
        }
    }

    //Time Complexity: O(n)
    fun isValidBSTOptimized(root: TreeNode?): Boolean {
        if (root == null) return true
        return isValidBSTHelper(root, null, null)
    }

    fun isValidBSTHelper(node: TreeNode?, max: Int?, min: Int?): Boolean {
        if (node == null) return true

        if (max != null && node.`val` >= max)
            return false

        if (min != null && node.`val` <= min)
            return false

        return isValidBSTHelper(node.left, node.`val`, min) &&
                isValidBSTHelper(node.right, max, node.`val`)
    }
}

fun main() {
    val obj = ValidateBinarySearchTree()
    // Example 2: Invalid BST [5, 1, 4, null, null, 3, 6]
    val root2 = TreeNode(5)
    root2.left = TreeNode(1)
    root2.right = TreeNode(4)
    root2.right?.left = TreeNode(3)
    root2.right?.right = TreeNode(6)
    println(obj.isValidBST(root2))
    println(obj.isValidBSTOptimized(root2))
}