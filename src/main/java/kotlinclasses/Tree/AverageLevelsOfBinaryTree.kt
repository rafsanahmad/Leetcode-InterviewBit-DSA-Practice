/*
 * *
 *  * Average of Levels in Binary Tree.kt
 *  * Created by Rafsan Ahmad on 7/2/25, 12:20PM
 *  * Copyright (c) 2025 . All rights reserved.
 *  *
 *
 */

package kotlinclasses.Tree

import java.util.LinkedList
import java.util.Queue

class AverageLevelsOfBinaryTree {
    //https://leetcode.com/problems/average-of-levels-in-binary-tree/description/
    /*Given the root of a binary tree, return the average value of the nodes on each level
    in the form of an array. Answers within 10-5 of the actual answer will be accepted.


Example 1:
         3
       /   \
      9     20
           /  \
          15   7

Input: root = [3,9,20,null,null,15,7]
Output: [3.00000,14.50000,11.00000]
Explanation: The average value of nodes on level 0 is 3, on level 1 is 14.5, and on level
2 is 11.
Hence return [3, 14.5, 11].

Example 2:
Input: root = [3,9,20,15,7]
Output: [3.00000,14.50000,11.00000]


Constraints:
The number of nodes in the tree is in the range [1, 10^4].
-2^31 <= Node.val <= 2^31 - 1*/

    fun averageOfLevels(root: TreeNode?): DoubleArray {
        if (root == null) return doubleArrayOf()
        val list = mutableListOf<Double>()

        val queue: Queue<TreeNode> = LinkedList()
        queue.add(root)

        while (queue.isNotEmpty()) {
            val size = queue.size
            var sum = 0.0
            for (i in 0 until size) {
                val node = queue.poll()
                sum += node.`val`
                node.right?.let { queue.add(it) }
                node.left?.let { queue.add(it) }
            }

            list.add((sum / size))
        }

        val arr = DoubleArray(list.size)
        for (i in list.indices) {
            arr[i] = list[i]
        }

        return arr
    }
}

fun main() {
    val obj = AverageLevelsOfBinaryTree()
    // Construct the tree: [3,9,20,null,null,15,7]
    val root = TreeNode(3).apply {
        left = TreeNode(9)
        right = TreeNode(20).apply {
            left = TreeNode(15)
            right = TreeNode(7)
        }
    }

    println(obj.averageOfLevels(root).contentToString())
}