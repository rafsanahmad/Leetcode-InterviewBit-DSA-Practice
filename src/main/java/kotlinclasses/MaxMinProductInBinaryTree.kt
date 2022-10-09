/*
 * *
 *  * MaxMinProductInBinaryTree.kt
 *  * Created by Rafsan Ahmad on 10/9/22, 9:21 PM
 *  * Copyright (c) 2022 . All rights reserved.
 *
 */

package kotlinclasses

class MaxMinProductInBinaryTree {
    /*Given a binary tree of N nodes, the task is to find the absolute difference between maximum and minimum product
     of the elements of any path in the binary tree.

Note: A path starts from the root and ends at any leaf in the tree.

Examples:

Input:
          3
       /   \
     3     4
  /  \    /  \
5   1  3

Output: 45 - 3 = 42
*/

    fun maxMinProduct(root: TreeNode): Int {
        // Write your code from here
        val maxValue = Math.max(root.value, findMaxProduct(root))
        val minValue = Math.min(root.value, findMinProduct(root))
        return Math.abs(maxValue - minValue)
    }

    fun findMaxProduct(node: TreeNode): Int {
        val leftValue: Int = node.left?.let { findMaxProduct(it) } ?: 1
        val rightValue: Int = node.right?.let { findMaxProduct(it) } ?: 1
        return node.value * Math.max(leftValue, rightValue)
    }

    fun findMinProduct(node: TreeNode): Int {
        val leftValue: Int = node.left?.let { findMinProduct(it) } ?: 1
        val rightValue: Int = node.right?.let { findMinProduct(it) } ?: 1
        return node.value * Math.min(leftValue, rightValue)
        //return Math.min(node.value, node.value * Math.min(leftValue, rightValue))
    }

    fun solution(A: Array<String>): Int {
        return maxMinProduct(TreeNode(A))
    }

    class TreeNode {
        var value: Int
        var left: TreeNode? = null
        var right: TreeNode? = null

        constructor(`val`: Int) {
            this.value = `val`
        }

        constructor(vals: Array<String>) {
            value = vals[0].toInt()
            left = constructFromArray(vals, 1)
            right = constructFromArray(vals, 2)
        }

        private fun constructFromArray(vals: Array<String>, k: Int): TreeNode? {
            if (k >= vals.size || vals[k] == "null") {
                return null
            }
            val root = TreeNode(vals[k].toInt())
            val leftIndex = 2 * k + 1
            root.left = constructFromArray(vals, leftIndex)
            val rightIndex = 2 * k + 2
            root.right = constructFromArray(vals, rightIndex)
            return root
        }
    }
}

fun main(args: Array<String>) {
    val array = arrayOf("3", "3", "4", "5", "1", "3", "null", "null")
    println(MaxMinProductInBinaryTree().solution(array))
}