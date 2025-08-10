/*
 * *
 *  * Binary Tree Zigzag Level Order Traversal.kt
 *  * Created by Rafsan Ahmad on 8/9/25, 4:37AM
 *  * Copyright (c) 2025 . All rights reserved.
 *  *
 *
 */

package kotlinclasses.Tree

import java.util.LinkedList

class BinaryTreeZigzagLevelTraversal {
    //https://leetcode.com/problems/binary-tree-zigzag-level-order-traversal/description/
    /*Given the root of a binary tree, return the zigzag level order traversal of its nodes' values.
    (i.e., from left to right, then right to left for the next level and alternate between).

Example 1:
        3
       / \
      9  20
         /  \
        15   7
Input: root = [3,9,20,null,null,15,7]
Output: [[3],[20,9],[15,7]]

Example 2:
Input: root = [1]
Output: [[1]]

Example 3:
Input: root = []
Output: []


Constraints:
The number of nodes in the tree is in the range [0, 2000].
-100 <= Node.val <= 100*/

    // Time Complexity: O(n)
    // Space Complexity: O(n)
    fun zigzagLevelOrder(root: TreeNode?): List<List<Int>> {
        if (root == null) return listOf()
        val result: MutableList<List<Int>> = mutableListOf()
        val queue: LinkedList<TreeNode> = LinkedList()
        queue.add(root)
        var leftToRight = true

        while (queue.isNotEmpty()) {
            var size = queue.size
            val list: MutableList<Int> = mutableListOf()
            while (size > 0) {
                val node = queue.poll()
                list.add(node.`val`)
                node.left?.let { queue.add(it) }
                node.right?.let { queue.add(it) }
                size--
            }
            if (!leftToRight)
                list.reverse()
            leftToRight = !leftToRight
            result.add(list)
        }
        return result
    }
}

fun main() {
    val obj = BinaryTreeZigzagLevelTraversal()
    // Construct tree
    val root = TreeNode(3)
    root.left = TreeNode(9)
    root.right = TreeNode(20)
    root.right!!.left = TreeNode(15)
    root.right!!.right = TreeNode(7)

    val output = obj.zigzagLevelOrder(root)
    println(output)  // Should print: [[3], [20, 9], [15, 7]]
}