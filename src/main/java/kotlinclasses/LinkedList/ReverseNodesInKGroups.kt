/*
 * *
 *  *  Reverse Nodes in k-Group.kt
 *  * Created by Rafsan Ahmad on 9/1/25, 2:59PM
 *  * Copyright (c) 2025 . All rights reserved.
 *  *
 *
 */

package kotlinclasses.LinkedList

class ReverseNodesInKGroups {
    //https://leetcode.com/problems/reverse-nodes-in-k-group/description/
    /*Given the head of a linked list, reverse the nodes of the list k at a time, and return the
    modified list.

k is a positive integer and is less than or equal to the length of the linked list. If the number of
nodes is not a multiple of k then left-out nodes, in the end, should remain as it is.

You may not alter the values in the list's nodes, only nodes themselves may be changed.

Example 1:
Input: head = [1,2,3,4,5], k = 2
Output: [2,1,4,3,5]

Example 2:
Input: head = [1,2,3,4,5], k = 3
Output: [3,2,1,4,5]

Constraints:
The number of nodes in the list is n.
1 <= k <= n <= 5000
0 <= Node.val <= 1000

Follow-up: Can you solve the problem in O(1) extra memory space?*/

    /*Time Complexity:
Outer loop: traverses each node once → O(n)
Inner reversal function (reverseBetween) reverses k nodes at a time,
total nodes reversed = n → O(n)
Overall: O(n)

Space Complexity:
Only a few pointers are used; no extra list or recursion
Overall: O(1) auxiliary space
*/
    fun reverseKGroup(head: ListNode?, k: Int): ListNode? {
        // Edge case: empty list or k <= 1, no need to reverse
        if (head == null || k <= 1) return head
        val result = ListNode(0) // Dummy node to simplify head operations
        result.next = head
        var temp = head
        var index = 0
        var begin: ListNode? = result // Points to node before current group

        while (temp != null) {
            index++
            if (index % k == 0) {
                val groupStart = begin?.next        // First node of the current k-group
                val groupNext = temp.next           // Node after the current group (exclusive)
                val newHead = reverseBetween(groupStart, groupNext) // Reverse current group
                // Connect previous part to new head of reversed group
                begin?.next = newHead
                groupStart?.next = groupNext        // Connect end of reversed group to next part
                begin = groupStart                  // Move 'begin' pointer to end of reversed group
                // Move temp pointer to next node after reversed group
                temp = groupNext
            } else {
                temp = temp.next                     // Keep traversing if k nodes not reached yet
            }
        }

        return result.next
    }

    fun reverseBetween(begin: ListNode?, end: ListNode?): ListNode? {
        // prev initially points to 'end' to handle connection after reversal
        var prev: ListNode? = end
        var curr = begin
        while (curr != end) {
            val next = curr?.next
            curr?.next = prev      // Reverse the pointer
            prev = curr
            curr = next
        }
        return prev                // New head of reversed group
    }

    fun createList(arr: IntArray): ListNode? {
        val dummy = ListNode(0)
        var current: ListNode? = dummy
        for (num in arr) {
            current?.next = ListNode(num)
            current = current?.next
        }
        return dummy.next
    }

    fun printList(head: ListNode?) {
        var curr = head
        while (curr != null) {
            print("${curr.`val`} ")
            curr = curr.next
        }
        println()
    }
}

fun main() {
    val obj = ReverseNodesInKGroups()

    val head1 = obj.createList(intArrayOf(1, 2, 3, 4, 5))
    val result1 = obj.reverseKGroup(head1, 2)
    obj.printList(result1)

    val head2 = obj.createList(intArrayOf(1, 2, 3, 4, 5))
    val result2 = obj.reverseKGroup(head2, 3)
    obj.printList(result2)
}