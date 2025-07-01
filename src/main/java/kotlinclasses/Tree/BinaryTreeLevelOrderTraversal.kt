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
}