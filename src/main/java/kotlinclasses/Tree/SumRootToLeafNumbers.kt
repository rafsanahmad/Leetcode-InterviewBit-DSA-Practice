/*
 * *
 *  * Sum Root to Leaf Numbers.kt
 *  * Created by Rafsan Ahmad on 7/11/25, 5:03PM
 *  * Copyright (c) 2025 . All rights reserved.
 *  *
 *
 */

package kotlinclasses.Tree

class SumRootToLeafNumbers {
    //https://leetcode.com/problems/sum-root-to-leaf-numbers/description/
    /*You are given the root of a binary tree containing digits from 0 to 9 only.

Each root-to-leaf path in the tree represents a number.

For example, the root-to-leaf path 1 -> 2 -> 3 represents the number 123.
Return the total sum of all root-to-leaf numbers. Test cases are generated so that the answer
will fit in a 32-bit integer.

A leaf node is a node with no children.

Example 1:
Input: root = [1,2,3]
Output: 25
Explanation:
The root-to-leaf path 1->2 represents the number 12.
The root-to-leaf path 1->3 represents the number 13.
Therefore, sum = 12 + 13 = 25.

Example 2:
Input: root = [4,9,0,5,1]
Output: 1026
Explanation:
The root-to-leaf path 4->9->5 represents the number 495.
The root-to-leaf path 4->9->1 represents the number 491.
The root-to-leaf path 4->0 represents the number 40.
Therefore, sum = 495 + 491 + 40 = 1026.


Constraints:
The number of nodes in the tree is in the range [1, 1000].
0 <= Node.val <= 9
The depth of the tree will not exceed 10.*/

    fun sumNumbers(root: TreeNode?): Int {
        if (root == null) return 0
        return sumNumbersHelper(root, 0)
    }

    fun sumNumbersHelper(node: TreeNode?, current: Int): Int {
        if (node == null)
            return 0

        val sum = current * 10 + node.`val`

        // Only return when it's a real leaf node (both children null)
        if (node.left == null && node.right == null) {
            return sum
        }

        return sumNumbersHelper(node.left, sum) +
                sumNumbersHelper(node.right, sum)
    }
}

fun main() {
    val obj = SumRootToLeafNumbers()
    // Create tree: [4,9,0,5,1]
    val root = TreeNode(4)
    root.left = TreeNode(9)
    root.right = TreeNode(0)
    root.left?.left = TreeNode(5)
    root.left?.right = TreeNode(1)

    println(obj.sumNumbers(root))// Expected: 1026
}