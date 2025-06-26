/*
 * *
 *  * Count Complete Tree Nodes.kt
 *  * Created by Rafsan Ahmad on 6/26/25, 1:41AM
 *  * Copyright (c) 2025 . All rights reserved.
 *  *
 *
 */

package kotlinclasses.Tree

class CountCompleteTreeNodes {
    //https://leetcode.com/problems/count-complete-tree-nodes/description/
    /*Given the root of a complete binary tree, return the number of the nodes in the tree.

According to Wikipedia, every level, except possibly the last, is completely filled in a complete
binary tree, and all nodes in the last level are as far left as possible. It can have between 1
and 2h nodes inclusive at the last level h.

Design an algorithm that runs in less than O(n) time complexity.

         1
       /   \
      2     3
    /  \     \
   4    5    6

Example 1:
Input: root = [1,2,3,4,5,6]
Output: 6

Example 2:
Input: root = []
Output: 0

Example 3:
Input: root = [1]
Output: 1


Constraints:
The number of nodes in the tree is in the range [0, 5 * 10^4].
0 <= Node.val <= 5 * 10^4
The tree is guaranteed to be complete.*/

    //Number of nodes in a perfect binary tree of height h = 2^h - 1
    /*total time complexity:
✅ O((log n)^2)

Because:
At each recursive call, you spend O(log n) time (to compute depth),
And you do this for O(log n) levels at most.
*/
    fun countNodes(root: TreeNode?): Int {
        val leftDepth = leftDepth(root)
        val rightDepth = rightDepth(root)

        return if (leftDepth == rightDepth) {
            (1 shl leftDepth) - 1
        } else {
            1 + countNodes(root?.left) + countNodes(root?.right)
        }
    }

    private fun leftDepth(node: TreeNode?): Int {
        var depth = 0
        var current = node
        while (current != null) {
            current = current.left
            depth++
        }
        return depth
    }

    private fun rightDepth(node: TreeNode?): Int {
        var depth = 0
        var current = node
        while (current != null) {
            current = current.right
            depth++
        }
        return depth
    }
}

fun main() {
    // Create a complete binary tree:
    //        1
    //       / \
    //      2   3
    //     / \  /
    //    4  5 6

    val root = TreeNode(1)
    root.left = TreeNode(2)
    root.right = TreeNode(3)
    root.left?.left = TreeNode(4)
    root.left?.right = TreeNode(5)
    root.right?.left = TreeNode(6)

    val obj = CountCompleteTreeNodes()
    println(obj.countNodes(root))
}