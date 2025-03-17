/*
 * *
 *  * Vertical Traversal Binary Tree.kt
 *  * Created by Rafsan Ahmad on 3/16/25, 5:00PM
 *  * Copyright (c) 2025 . All rights reserved.
 *  *
 *
 */

package kotlinclasses.Tree

import javaclasses.Tree.TreeNode
import java.util.LinkedList
import java.util.PriorityQueue
import java.util.Queue
import java.util.TreeMap


class VerticalTraversalBinaryTree {
    //https://takeuforward.org/data-structure/vertical-order-traversal-of-binary-tree/
    //res/vertical_travarsal_tree.png
    //res/vertical_travarsal_tree_sol_1
    //res/vertical_travarsal_tree_sol_2
    //res/vertical_travarsal_tree_sol_3
    //res/vertical_travarsal_tree_sol_4
    /*Problem Statement: Given a Binary Tree, return the Vertical Order Traversal of it starting
    from the Leftmost level to the Rightmost level. If there are multiple nodes passing through a
    vertical line, then they should be printed as they appear in level order traversal of the tree.

Examples
Example 1:
Input:Binary Tree: 1 2 3 4 10 9 11 -1 5 -1 -1 -1 -1 -1 -1 -1 6

Output: Vertical Order Traversal: [[4],[2, 5], [1, 10, 9, 6],[3],[11]]
Explanation: Vertical Levels from left to right:

Level -2: [4]
Level -1: [2]
Level 0: [1, 10, 9, 6] (Overlapping nodes are added in their level order sequence)
Level 1: [3]
Level 2: [11]

Example 2:
Input:Binary Tree: 2 7 5 2 6 -1 9 -1 -1 5 11 4 -1
Output : [[2],[7, 5],[2, 6], [5, 11, 4],[9]]
Explanation: Vertical Levels from left to right:

Level -2: [2]
Level -1: [7, 5]
Level 0: [2, 6]
Level 1: [5, 11, 4] (Overlapping nodes are added in their level order sequence)
Level 2: [9]*/

    // Tuple class for the binary tree
    class Tuple(_node: TreeNode, _xAxis: Int, _level: Int) {
        var node: TreeNode = _node
        var xAxis: Int = _xAxis
        var level: Int = _level
    }

    fun verticalTraversal(root: TreeNode): List<MutableList<Int>> {
        //Key - Each Row in the Binary Tree
        //Value - Sorted nodes values in that level
        val map = TreeMap<Int, TreeMap<Int, PriorityQueue<Int>>>()

        //Queue is for Level order traversal
        val q: Queue<Tuple> = LinkedList()
        q.offer(Tuple(root, 0, 0))

        while (!q.isEmpty()) {
            val tuple = q.poll()
            val node = tuple.node
            val xAxis = tuple.xAxis
            val level = tuple.level

            if (!map.containsKey(xAxis)) {
                map[xAxis] = TreeMap()
            }

            map[xAxis]?.let { axis ->
                if (!axis.containsKey(level)) {
                    axis[level] = PriorityQueue()
                }
            }

            map[xAxis]?.get(level)?.offer(node.`val`)

            if (node.left != null) {
                q.offer(Tuple(node.left, xAxis - 1, level + 1))
            }
            if (node.right != null) {
                q.offer(Tuple(node.right, xAxis + 1, level + 1))
            }
        }

        val list: MutableList<MutableList<Int>> = ArrayList()
        for (levelNodes in map.values) {
            list.add(ArrayList())
            for (nodes in levelNodes.values) {
                while (!nodes.isEmpty()) {
                    //println(nodes.peek())
                    list[list.size - 1].add(nodes.poll())
                }
            }
        }
        return list
    }

    companion object {
        // Helper function to
        // print the result
        private fun printResult(result: List<List<Int>>) {
            for (level in result) {
                for (node in level) {
                    print("$node ")
                }
                println()
            }
            println()
        }

        @JvmStatic
        fun main(args: Array<String>) {
            // Creating a sample binary tree
            val root = TreeNode(1)
            root.left = TreeNode(2)
            root.left.left = TreeNode(4)
            root.left.right = TreeNode(10)
            root.left.left.right = TreeNode(5)
            root.left.left.right.right = TreeNode(6)
            root.right = TreeNode(3)
            root.right.right = TreeNode(10)
            root.right.left = TreeNode(9)

            val solution = VerticalTraversalBinaryTree()
            // Get the Vertical traversal
            val verticalTraversal = solution.verticalTraversal(root)

            // Print the result
            print("Vertical Traversal: $\n")
            printResult(verticalTraversal)
        }
    }
}