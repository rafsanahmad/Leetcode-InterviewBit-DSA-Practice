/*
 * *
 *  * Remove Nth Node From End of List.kt
 *  * Created by Rafsan Ahmad on 7/6/25, 5:59PM
 *  * Copyright (c) 2025 . All rights reserved.
 *  *
 *
 */

package kotlinclasses.LinkedList

class RemoveNthNodeFromEndOfList {
    //https://leetcode.com/problems/remove-nth-node-from-end-of-list/description/
    /*Given the head of a linked list, remove the nth node from the end of the list and
    return its head.

Example 1:
Input: head = [1,2,3,4,5], n = 2
Output: [1,2,3,5]

Example 2:
Input: head = [1], n = 1
Output: []

Example 3:
Input: head = [1,2], n = 1
Output: [1]


Constraints:
The number of nodes in the list is sz.
1 <= sz <= 30
0 <= Node.val <= 100
1 <= n <= sz


Follow up: Could you do this in one pass?*/

    fun removeNthFromEnd(head: ListNode?, n: Int): ListNode? {
        if (head == null) return null

        // Step 1: Count total number of nodes
        var total = 0
        var temp = head
        while (temp != null) {
            total++
            temp = temp.next
        }

        // Step 2: Calculate the position to remove from start (1-based)
        val removeIndex = total - n

        // Step 3: If we need to remove the head node
        if (removeIndex == 0) {
            return head.next
        }

        // Step 4: Traverse to node just before the one we want to remove
        temp = head
        var i = 0
        while (i < removeIndex - 1) {
            temp = temp?.next
            i++
        }

        // Step 5: Skip the node to remove
        temp?.next = temp?.next?.next

        return head
    }


    //One pass solution
    /*Explanation:
Dummy Node: Handles edge cases like removing the head node.
fast goes n+1 steps ahead of slow, so there's a gap of n between them.
When fast reaches the end, slow is just before the target node.
slow.next = slow.next.next removes the N-th node from the end.*/

    fun removeNthFromEndOnePass(head: ListNode?, n: Int): ListNode? {
        val dummy = ListNode(0)
        dummy.next = head

        var fast: ListNode? = dummy
        var slow: ListNode? = dummy

        // Move fast pointer n+1 steps ahead
        for (i in 0..n) {
            fast = fast?.next
        }

        // Move both fast and slow until fast reaches the end
        while (fast != null) {
            fast = fast.next
            slow = slow?.next
        }

        // Skip the nth node from end
        slow?.next = slow?.next?.next

        return dummy.next
    }
}

fun main() {
    val obj = RemoveNthNodeFromEndOfList()

    val list1 = buildList(listOf(1, 2, 3, 4, 5))
    val result1 = obj.removeNthFromEnd(list1, 2)
    println("${toList(result1)}")  // Expected: [1, 2, 3, 5]

    // Example 2
    val list2 = buildList(listOf(1))
    val result2 = obj.removeNthFromEnd(list2, 1)
    println("${toList(result2)}")  // Expected: []
}