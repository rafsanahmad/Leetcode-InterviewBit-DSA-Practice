/*
 * *
 *  * Populating Next Right Pointers in Each Node II.kt
 *  * Created by Rafsan Ahmad on 8/7/25, 4:28PM
 *  * Copyright (c) 2025 . All rights reserved.
 *  *
 *
 */

package kotlinclasses.Tree

import java.util.LinkedList

class Node(var `val`: Int) {
    var left: Node? = null
    var right: Node? = null
    var next: Node? = null
}

class PopulatingNextRightPointersII {
    //https://leetcode.com/problems/populating-next-right-pointers-in-each-node-ii/description/
    /*Given a binary tree

struct Node {
  int val;
  Node *left;
  Node *right;
  Node *next;
}
Populate each next pointer to point to its next right node. If there is no next right node, the next
pointer should be set to NULL.

Initially, all next pointers are set to NULL.

Example 1:
        1 → NULL
       / \
      2 → 3 → NULL
     / \    \
    4 → 5 →  7 → NULL
Input: root = [1,2,3,4,5,null,7]
Output: [1,#,2,3,#,4,5,7,#]
Explanation: Given the above binary tree (Figure A), your function should populate each next pointer
to point to its next right node, just like in Figure B. The serialized output is in level order as connected by the next pointers, with '#' signifying the end of each level.

Example 2:
Input: root = []
Output: []


Constraints:
The number of nodes in the tree is in the range [0, 6000].
-100 <= Node.val <= 100


Follow-up:
You may only use constant extra space.
The recursive approach is fine. You may assume implicit stack space does not count as extra space
for this problem.*/

    //Time Complexity: O(N)
    //Space Complexity: O(N)
    fun connect(root: Node?): Node? {
        if (root == null) return null

        val queue: LinkedList<Node> = LinkedList()
        queue.add(root)

        while (queue.isNotEmpty()) {
            val size = queue.size
            var prev: Node? = null
            for (i in 0 until size) {
                val node = queue.poll()
                if (prev != null) {
                    prev.next = node
                }
                prev = node
                node.left?.let { queue.add(it) }
                node.right?.let { queue.add(it) }
            }
        }

        return root
    }

    //Time Complexity: O(N)
    //Space Complexity: O(1)
    fun connectOptimized(root: Node?): Node? {
        if (root == null) return null

        var head = root
        while (head != null) {
            val dummy = Node(0)
            var curr = head
            var tail: Node? = dummy
            while (curr != null) {
                if (curr.left != null) {
                    tail?.next = curr.left
                    tail = tail?.next
                }
                if (curr.right != null) {
                    tail?.next = curr.right
                    tail = tail?.next
                }
                curr = curr.next
            }

            head = dummy.next
        }

        return root
    }

    // Function to print levels using next pointers
    fun printLevels(root: Node?) {
        var levelStart = root
        while (levelStart != null) {
            var curr = levelStart
            while (curr != null) {
                print("${curr.`val`} ")
                curr = curr.next
            }
            println("#") // End of current level
            levelStart = levelStart.left ?: levelStart.right ?: findNext(levelStart)
        }
    }

    // Helper to find next level’s first node
    fun findNext(node: Node?): Node? {
        var curr = node?.next
        while (curr != null) {
            if (curr.left != null) return curr.left
            if (curr.right != null) return curr.right
            curr = curr.next
        }
        return null
    }
}

fun main() {
    val obj = PopulatingNextRightPointersII()
    val root = Node(1)
    root.left = Node(2)
    root.right = Node(3)
    root.left?.left = Node(4)
    root.left?.right = Node(5)
    root.right?.right = Node(7)

    obj.connect(root)
    obj.printLevels(root)
    println()
    obj.connectOptimized(root)
    obj.printLevels(root)
}