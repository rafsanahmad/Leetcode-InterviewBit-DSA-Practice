/*
 * *
 *  * Construct Binary Tree from Inorder and Postorder Traversal.kt
 *  * Created by Rafsan Ahmad on 6/26/25, 8:37PM
 *  * Copyright (c) 2025 . All rights reserved.
 *  *
 *
 */

package kotlinclasses.Tree

class ConstructBinaryTreeFromPostorderInorder {
    //https://leetcode.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal/description/
    /*Given two integer arrays inorder and postorder where inorder is the inorder traversal
     of a binary tree and postorder is the postorder traversal of the same tree, construct
     and return the binary tree.

Example 1:

         3
       /   \
      9     20
           /  \
          15   7
Input: inorder = [9,3,15,20,7], postorder = [9,15,7,20,3]
Output: [3,9,20,null,null,15,7]

Example 2:
Input: inorder = [-1], postorder = [-1]
Output: [-1]


Constraints:
1 <= inorder.length <= 3000
postorder.length == inorder.length
-3000 <= inorder[i], postorder[i] <= 3000
inorder and postorder consist of unique values.
Each value of postorder also appears in inorder.
inorder is guaranteed to be the inorder traversal of the tree.
postorder is guaranteed to be the postorder traversal of the tree.*/

    /*Time Complexity: O(n)
Each node and each index is visited once.
*/
    fun buildTree(inorder: IntArray, postorder: IntArray): TreeNode? {
        //val inorderIndexMap = inorder.withIndex().associate { it.value to it.index }

        //Much faster than using associate
        val inorderIndexMap = HashMap<Int, Int>(inorder.size)
        for (i in inorder.indices) {
            inorderIndexMap[inorder[i]] = i
        }
        var postorderIndex = postorder.size - 1

        fun helper(left: Int, right: Int): TreeNode? {
            if (left > right) return null
            val rootVal = postorder[postorderIndex--]
            val treeNode = TreeNode(rootVal)
            val index = inorderIndexMap[rootVal]

            if (index != null) {
                //Order Matters Root->Right->Left
                /*This way, you're consuming the postorder array from the end
                (root → right → left), and constructing the tree in the correct order.*/
                treeNode.right = helper(index + 1, right)
                treeNode.left = helper(left, index - 1)
            }

            return treeNode
        }

        return helper(0, postorder.size - 1)
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
    val postorder = intArrayOf(9, 15, 7, 20, 3)
    val inorder = intArrayOf(9, 3, 15, 20, 7)
    val obj = ConstructBinaryTreeFromPostorderInorder()
    val root = obj.buildTree(inorder, postorder)

    obj.printLevelOrder(root)
}