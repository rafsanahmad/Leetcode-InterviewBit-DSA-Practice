/*
 * *
 *  * Flatten Binary Tree to Linked List.kt
 *  * Created by Rafsan Ahmad on 7/21/25, 2:14PM
 *  * Copyright (c) 2025 . All rights reserved.
 *  *
 *
 */

package kotlinclasses.Tree

import java.util.LinkedList

class FlattenBinaryTreeToLinkedList {
    //https://leetcode.com/problems/flatten-binary-tree-to-linked-list/description/
    //res/flaten_bt.jpg
    /*Given the root of a binary tree, flatten the tree into a "linked list":
The "linked list" should use the same TreeNode class where the right child pointer points to the
next node in the list and the left child pointer is always null.
The "linked list" should be in the same order as a pre-order traversal of the binary tree.


Example 1:
        1                     1
       / \                     \
      2   5                     2
     / \   \                     \
    3   4   6    ----->           3
                                   \
                                    4
                                     \
                                      5
                                       \
                                        6

Input: root = [1,2,5,3,4,null,6]
Output: [1,null,2,null,3,null,4,null,5,null,6]

Example 2:
Input: root = []
Output: []

Example 3:
Input: root = [0]
Output: [0]


Constraints:
The number of nodes in the tree is in the range [0, 2000].
-100 <= Node.val <= 100

Follow up: Can you flatten the tree in-place (with O(1) extra space)?*/

    var prev: TreeNode? = null
    fun flatten(root: TreeNode?): Unit {
        if (root == null) return
        flattenHelper(root)
    }

    fun flattenHelper(node: TreeNode?) {
        if (node == null) return
        flattenHelper(node.right)
        flattenHelper(node.left)
        node.right = prev
        node.left = null
        prev = node
    }


    var prev2: TreeNode? = null

    //Using Preorder
    fun flattenPreorder(root: TreeNode?) {
        if (root == null) return
        preorder(root)
    }

    fun preorder(node: TreeNode?) {
        if (node == null) return

        // If we had a previous node, link it to the current one
        if (prev2 != null) {
            prev2?.right = node
            prev2?.left = null
        }
        prev2 = node

        // save before recursion (otherwise links get messed up)
        val left = node.left
        val right = node.right

        preorder(left)
        preorder(right)
    }


    fun flattenIterative(root: TreeNode?) {
        if (root == null) return
        val stack = LinkedList<TreeNode>()
        stack.push(root)

        var prev: TreeNode? = null
        while (stack.isNotEmpty()) {
            val node = stack.pop()

            if (prev != null) {
                prev.right = node
                prev.left = null
            }
            prev = node

            // push right first so left is processed first
            if (node.right != null) stack.push(node.right)
            if (node.left != null) stack.push(node.left)
        }
    }
}

fun main() {
    val obj = FlattenBinaryTreeToLinkedList()
    // Define a sample binary tree:
    //       1
    //      / \
    //     2   5
    //    / \   \
    //   3   4   6

    val root = TreeNode(1)
    root.left = TreeNode(2)
    root.right = TreeNode(5)
    root.left?.left = TreeNode(3)
    root.left?.right = TreeNode(4)
    root.right?.right = TreeNode(6)

    // Flatten the tree
    val temp = root
    obj.flatten(root)

    // Print the flattened tree
    var current: TreeNode? = root
    while (current != null) {
        print("${current.`val`} ")
        current = current.right
    }

    println()
    obj.flattenPreorder(temp)

    // Print the flattened tree
    var current2: TreeNode? = temp
    while (current2 != null) {
        print("${current2.`val`} ")
        current2 = current2.right
    }
}