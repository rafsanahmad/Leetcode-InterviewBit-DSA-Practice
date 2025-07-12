/*
 * *
 *  * Binary Tree Right Side View.kt
 *  * Created by Rafsan Ahmad on 7/10/25, 1:15AM
 *  * Copyright (c) 2025 . All rights reserved.
 *  *
 *
 */

package kotlinclasses.Tree

import java.util.LinkedList
import java.util.Queue

class BinaryTreeRightSideView {
    //https://leetcode.com/problems/binary-tree-right-side-view/description/
    /*Given the root of a binary tree, imagine yourself standing on the right side of it,
    return the values of the nodes you can see ordered from top to bottom.

Example 1:
Input: root = [1,2,3,null,5,null,4]
Output: [1,3,4]
Explanation:
       1
      / \
     2   3
      \    \
       5    4


Example 2:
Input: root = [1,2,3,4,null,null,null,5]
Output: [1,3,4,5]

Explanation:
       1
      / \
     2   3
    /
   4
  /
 5

Example 3:
Input: root = [1,null,3]
Output: [1,3]

Example 4:
Input: root = []
Output: []

Constraints:
The number of nodes in the tree is in the range [0, 100].
-100 <= Node.val <= 100*/

    fun rightSideView(root: TreeNode?): List<Int> {
        if (root == null) return listOf()
        val result: MutableList<Int> = mutableListOf()
        val queue: Queue<TreeNode> = LinkedList()
        queue.add(root)
        while (queue.isNotEmpty()) {
            val size = queue.size
            for (i in 0 until size) {
                val node = queue.poll()
                if (i == size - 1) {
                    result.add(node.`val`)
                }
                node?.left?.let { queue.add(it) }
                node?.right?.let { queue.add(it) }
            }
        }

        return result
    }
}

fun main() {
    val obj = BinaryTreeRightSideView()
    // Example 1: [1,2,3,null,5,null,4]
    val root1 = TreeNode(1).apply {
        left = TreeNode(2).apply {
            right = TreeNode(5)
        }
        right = TreeNode(3).apply {
            right = TreeNode(4)
        }
    }
    println(obj.rightSideView(root1)) // [1, 3, 4]

    // Example 2: [1,2,3,4,null,null,null,5]
    val root2 = TreeNode(1).apply {
        left = TreeNode(2).apply {
            left = TreeNode(4).apply {
                left = TreeNode(5)
            }
        }
        right = TreeNode(3)
    }

    println(obj.rightSideView(root2))  // [1, 3, 4, 5]
}