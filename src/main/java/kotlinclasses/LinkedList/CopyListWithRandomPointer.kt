/*
 * *
 *  * Copy List with Random Pointer.kt
 *  * Created by Rafsan Ahmad on 5/30/25, 12:39AM
 *  * Copyright (c) 2025 . All rights reserved.
 *  *
 *
 */

package kotlinclasses.LinkedList

import kotlinclasses.LinkedList.CopyListWithRandomPointer.Node

class CopyListWithRandomPointer {
    //https://leetcode.com/problems/copy-list-with-random-pointer/
    /*A linked list of length n is given such that each node contains an additional random pointer,
     which could point to any node in the list, or null.

Construct a deep copy of the list. The deep copy should consist of exactly n brand new nodes,
where each new node has its value set to the value of its corresponding original node. Both the
next and random pointer of the new nodes should point to new nodes in the copied list such that
the pointers in the original list and copied list represent the same list state. None of the
pointers in the new list should point to nodes in the original list.

For example, if there are two nodes X and Y in the original list, where X.random --> Y, then for
the corresponding two nodes x and y in the copied list, x.random --> y.

Return the head of the copied linked list.

The linked list is represented in the input/output as a list of n nodes. Each node is represented
as a pair of [val, random_index] where:

val: an integer representing Node.val
random_index: the index of the node (range from 0 to n-1) that the random pointer points to, or
null if it does not point to any node.
Your code will only be given the head of the original linked list.



Example 1:
Input: head = [[7,null],[13,0],[11,4],[10,2],[1,0]]
Output: [[7,null],[13,0],[11,4],[10,2],[1,0]]

Example 2:
Input: head = [[1,1],[2,1]]
Output: [[1,1],[2,1]]

Example 3:
Input: head = [[3,null],[3,0],[3,null]]
Output: [[3,null],[3,0],[3,null]]


Constraints:
0 <= n <= 1000
-10^4 <= Node.val <= 10^4
Node.random is null or is pointing to some node in the linked list.*/

    class Node(var `val`: Int) {
        var next: Node? = null
        var random: Node? = null
    }

    fun copyRandomList(node: Node?): Node? {
        if (node == null) return null

        val map = hashMapOf<Node, Node>()
        var curr = node

        // First pass: create a mapping from original node to its copy
        while (curr != null) {
            map[curr] = Node(curr.`val`)
            curr = curr.next
        }

        // Second pass: assign next and random pointers
        curr = node
        while (curr != null) {
            map[curr]?.next = map[curr.next]
            map[curr]?.random = map[curr.random]
            curr = curr.next
        }

        return map[node]
    }

    fun copyRandomListSpaceOptimized(node: Node?): Node? {
        if (node == null) return null

        var curr = node

        // Step 1: Interleave copied nodes
        /* We create a copy node right after each original node:
A -> A' -> B -> B' -> C -> C' -> null
A' is copy of A
B' is copy of B
C' is copy of C
At this point, only .next pointers are linked. Random pointers are still null.*/
        while (curr != null) {
            val copy = Node(curr.`val`)
            copy.next = curr.next
            curr.next = copy
            curr = copy.next
        }

        // Step 2: Assign random pointers
        /*Now, for each original node, we set:
curr.next.random = curr.random?.next
That means:
A'.random = A.random?.next = C.next = C'
B'.random = B.random?.next = A.next = A'
C'.random = C.random?.next = B.next = B'

So now:
A -> A' -> B -> B' -> C -> C'
     |          |          |
     v          v          v
     C'         A'         B'
*/
        curr = node
        while (curr != null) {
            curr.next?.random = curr.random?.next
            curr = curr.next?.next
        }

        // Step 3: Separate the lists
        /*Finally, we detach the copy list from the interleaved list:
Restore original list: A -> B -> C
Extract copy list: A' -> B' -> C'
Both lists now have the correct .next and .random relationships.

Final Result:
Original: A -> B -> C
           |    |    |
           v    v    v
           C    A    B

Copy:     A' -> B' -> C'
           |     |     |
           v     v     v
           C'    A'    B'
*/
        curr = node
        val dummy = Node(0)
        var copyCurr: Node? = dummy

        while (curr != null) {
            val copy = curr.next
            curr.next = copy?.next
            copyCurr?.next = copy
            copyCurr = copy
            curr = curr.next
        }

        return dummy.next
    }
}

fun main() {
    val copyRandomList = CopyListWithRandomPointer()
    // Create nodes
    val node1 = Node(7)
    val node2 = Node(13)
    val node3 = Node(11)
    val node4 = Node(10)
    val node5 = Node(1)

    // Connect next pointers
    node1.next = node2
    node2.next = node3
    node3.next = node4
    node4.next = node5

    // Connect random pointers
    node1.random = null
    node2.random = node1
    node3.random = node5
    node4.random = node3
    node5.random = node1

    // Make a deep copy
    val copiedHead = copyRandomList.copyRandomListSpaceOptimized(node1)

    // Print result
    var original: Node? = node1
    var copied = copiedHead
    println("Original vs Copied:")

    while (original != null && copied != null) {
        println("Original: val=${original.`val`}, random=${original.random?.`val`}")
        println("Copied  : val=${copied.`val`}, random=${copied.random?.`val`}")
        println("---")
        original = original.next
        copied = copied.next
    }
}