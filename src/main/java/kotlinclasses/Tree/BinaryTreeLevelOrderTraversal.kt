/*
 * *
 *  * Binary Tree Level Order Traversal.kt
 *  * Created by Rafsan Ahmad on 6/28/25, 10:11PM
 *  * Copyright (c) 2025 . All rights reserved.
 *  *
 *
 */

package kotlinclasses.Tree

import java.util.LinkedList
import java.util.Queue


class BinaryTreeLevelOrderTraversal {
    //https://leetcode.com/problems/binary-tree-level-order-traversal/description/
    /*Given the root of a binary tree, return the level order traversal of its nodes' values.
    (i.e., from left to right, level by level).



Example 1:
         3
       /   \
      9     20
           /  \
          15   7
Input: root = [3,9,20,null,null,15,7]
Output: [[3],[9,20],[15,7]]

Example 2:
Input: root = [1]
Output: [[1]]

Example 3:
Input: root = []
Output: []


Constraints:
The number of nodes in the tree is in the range [0, 2000].
-1000 <= Node.val <= 1000*/

    fun levelOrder(root: TreeNode?): List<List<Int>> {
        val result = mutableListOf<List<Int>>()
        if (root == null) return result
        val queue: Queue<TreeNode?> = LinkedList()
        queue.add(root)

        while (queue.isNotEmpty()) {
            val size = queue.size
            val list = mutableListOf<Int>()

            for (i in 0 until size) {
                val node = queue.poll()
                if (node != null) {
                    list.add(node.`val`)
                    node.left?.let { queue.add(it) }
                    node.right?.let { queue.add(it) }
                }
            }

            result.add(list)
        }

        return result
    }

    /*
    Recursive Call Trace:
    traverse(3, 0)                        → result = [[3]]
        ├── traverse(9, 1)                → result = [[3], [9]]
        │   ├── traverse(10, 2)           → result = [[3], [9], [10]]
        │   │   ├── traverse(null, 3)     → result = [[3], [9], [10]]
        │   │   └── traverse(null, 3)     → result = [[3], [9], [10]]
        │   └── traverse(null, 2)         → result = [[3], [9], [10]]
        └── traverse(20, 1)               → result = [[3], [9, 20], [10]]
            ├── traverse(15, 2)           → result = [[3], [9, 20], [10, 15]]
            │   ├── traverse(null, 3)     → result = [[3], [9, 20], [10, 15]]
            │   └── traverse(null, 3)     → result = [[3], [9, 20], [10, 15]]
            └── traverse(7, 2)            → result = [[3], [9, 20], [10, 15, 7]]
                ├── traverse(null, 3)     → result = [[3], [9, 20], [10, 15, 7]]
                └── traverse(null, 3)     → result = [[3], [9, 20], [10, 15, 7]]

    * */
    fun levelOrderRecursive(root: TreeNode?): MutableList<MutableList<Int>> {
        val result: MutableList<MutableList<Int>> = ArrayList()
        traverse(root, 0, result)
        return result
    }

    private fun traverse(node: TreeNode?, level: Int, result: MutableList<MutableList<Int>>) {
        if (node == null) return

        // Ensure the result list has a list for the current level
        if (result.size == level) {
            result.add(ArrayList())
        }

        // Add the current node's value to its level list
        result[level].add(node.`val`)

        // Recurse on left and right children
        traverse(node.left, level + 1, result)
        traverse(node.right, level + 1, result)
    }

    fun printLevelOrder(root: TreeNode?) {
        val queue: Queue<TreeNode> = LinkedList()
        queue.add(root)
        while (!queue.isEmpty()) {
            val tempNode = queue.poll()
            print("${tempNode.`val`} ")

            /*Enqueue left child */
            if (tempNode.left != null) {
                queue.add(tempNode.left)
            }

            /*Enqueue right child */
            if (tempNode.right != null) {
                queue.add(tempNode.right)
            }
        }
    }
}

fun main() {
    val obj = BinaryTreeLevelOrderTraversal()
    // Construct the tree: [3,9,20,null,null,15,7]
    val root = TreeNode(3).apply {
        left = TreeNode(9)
        right = TreeNode(20).apply {
            left = TreeNode(15)
            right = TreeNode(7)
        }
    }

    println(obj.levelOrder(root))
    obj.printLevelOrder(root)
    println()
    println(obj.levelOrderRecursive(root))
}