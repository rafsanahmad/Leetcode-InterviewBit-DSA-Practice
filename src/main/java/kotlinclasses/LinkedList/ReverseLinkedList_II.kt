/*
 * *
 *  * Reverse Linked List II.kt
 *  * Created by Rafsan Ahmad on 5/30/25, 2:18PM
 *  * Copyright (c) 2025 . All rights reserved.
 *  *
 *
 */

package kotlinclasses.LinkedList

class ReverseLinkedList_II {
    //https://leetcode.com/problems/reverse-linked-list-ii/
    /*Given the head of a singly linked list and two integers left and right where left <= right,
    reverse the nodes of the list from position left to position right, and return the reversed
    list.

Example 1:
Input: head = [1,2,3,4,5], left = 2, right = 4
Output: [1,4,3,2,5]

Example 2:
Input: head = [5], left = 1, right = 1
Output: [5]

Constraints:
The number of nodes in the list is n.
1 <= n <= 500
-500 <= Node.val <= 500
1 <= left <= right <= n

Follow up: Could you do it in one pass?*/

    fun reverseBetween(head: ListNode?, left: Int, right: Int): ListNode? {
        val dummy = ListNode(0)
        dummy.next = head

        // 1) Reach node at position "left"
        /*We move to node before left:
        leftPrev = node 1
        cur = node 2

        dummy → 1 →  2  →  3 →  4 →  5
                ↑    ↑
           leftPrev cur
           */
        var leftPrev: ListNode? = dummy
        var cur: ListNode? = head
        for (i in 0 until left - 1) {
            leftPrev = cur
            cur = cur?.next
        }

        // Now cur="left", leftPrev="node before left"
        // 2) Reverse from left to right

        /*
        dummy → 1    2   3 → 4 → 5
         \   ↑
       leftPrev
           prev

        dummy → 1    2 ← 3   4 → 5
                \        ↑
             leftPrev   prev

        dummy → 1    2 ← 3 ← 4   5
                \            ↑   ↑
             leftPrev      prev cur
        Now the reversed part is: 4 → 3 → 2*/

        var prev: ListNode? = null
        for (i in 0..(right - left)) {
            val tmpNext = cur?.next
            cur?.next = prev
            prev = cur
            cur = tmpNext
        }

        // 3) Update pointers
        /*
        dummy-1 4 → 3 → 2 5
        leftPrev=1
        leftPrev.next.next = cur
        1 → 2 = 5
        leftPrev.next = prev
        1 → next = 4

        Final list:
        dummy → 1 → 4 → 3 → 2 → 5
        */

        leftPrev?.next?.next = cur // cur is node after "right"
        leftPrev?.next = prev      // prev is "right"

        return dummy.next
    }

    // Helper function to create a linked list from a list of integers
    fun createLinkedList(values: List<Int>): ListNode? {
        if (values.isEmpty()) return null
        val head = ListNode(values[0])
        var current: ListNode? = head
        for (i in 1 until values.size) {
            current?.next = ListNode(values[i])
            current = current?.next
        }
        return head
    }

    // Helper function to print linked list
    fun printLinkedList(head: ListNode?) {
        var current = head
        while (current != null) {
            print("${current.`val`} ")
            current = current.next
        }
        println()
    }
}

fun main() {
    val ll = ReverseLinkedList_II()
    val head = ll.createLinkedList(listOf(1, 2, 3, 4, 5))
    val left = 2
    val right = 4

    println("Original list:")
    ll.printLinkedList(head)

    val newHead = ll.reverseBetween(head, left, right)

    println("List after reversing from $left to $right:")
    ll.printLinkedList(newHead)
}