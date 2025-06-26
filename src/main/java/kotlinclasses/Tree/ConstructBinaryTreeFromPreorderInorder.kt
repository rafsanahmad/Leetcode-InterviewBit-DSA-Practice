/*
 * *
 *  * Construct Binary Tree from Preorder and Inorder Traversal.kt
 *  * Created by Rafsan Ahmad on 6/26/25, 6:17PM
 *  * Copyright (c) 2025 . All rights reserved.
 *  *
 *
 */

package kotlinclasses.Tree

class ConstructBinaryTreeFromPreorderInorder {
    //https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/description/
    /*Given two integer arrays preorder and inorder where preorder is the preorder traversal
     of a binary tree and inorder is the inorder traversal of the same tree, construct and
     return the binary tree.

         3
       /   \
      9     20
           /  \
          15   7
Example 1:
Input: preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
Output: [3,9,20,null,null,15,7]

Example 2:
Input: preorder = [-1], inorder = [-1]
Output: [-1]


Constraints:
1 <= preorder.length <= 3000
inorder.length == preorder.length
-3000 <= preorder[i], inorder[i] <= 3000
preorder and inorder consist of unique values.
Each value of inorder also appears in preorder.
preorder is guaranteed to be the preorder traversal of the tree.
inorder is guaranteed to be the inorder traversal of the tree.*/

    fun buildTree(preorder: IntArray, inorder: IntArray): TreeNode? {
        val inorderIndexMap = inorder.withIndex().associate { it.value to it.index }

        var preorderIndex = 0
        fun helper(left: Int, right: Int): TreeNode? {
            if (left > right) return null
            val rootVal = preorder[preorderIndex++]
            val treeNode = TreeNode(rootVal)

            val index = inorderIndexMap[rootVal]

            if (index != null) {
                treeNode.left = helper(left, index - 1)
                treeNode.right = helper(index + 1, right)
            }

            return treeNode
        }

        return helper(0, preorder.size - 1)
    }

    // Helper function to print tree level-order (BFS)
    fun printLevelOrder(root: TreeNode?) {
        if (root == null) return
        val queue = ArrayDeque<TreeNode?>()
        queue.add(root)
        while (queue.isNotEmpty()) {
            val node = queue.removeFirst()
            if (node != null) {
                print("${node.`val`} ")
                queue.add(node.left)
                queue.add(node.right)
            } else {
                print("null ")
            }
        }
        println()
    }
}

fun main() {
    val preorder = intArrayOf(3, 9, 20, 15, 7)
    val inorder = intArrayOf(9, 3, 15, 20, 7)
    val obj = ConstructBinaryTreeFromPreorderInorder()
    val root = obj.buildTree(preorder, inorder)

    obj.printLevelOrder(root)
}