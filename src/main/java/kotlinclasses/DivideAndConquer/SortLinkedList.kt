/*
 * *
 *  * Sort Linked List.kt
 *  * Created by Rafsan Ahmad on 7/9/25, 3:54AM
 *  * Copyright (c) 2025 . All rights reserved.
 *  *
 *
 */

package kotlinclasses.DivideAndConquer

import kotlinclasses.LinkedList.ListNode
import kotlinclasses.LinkedList.buildList
import kotlinclasses.LinkedList.toList

class SortLinkedList {
    //https://leetcode.com/problems/sort-list/description/
    /*Given the head of a linked list, return the list after sorting it in ascending order.

Example 1:
Input: head = [4,2,1,3]
Output: [1,2,3,4]

Example 2:
Input: head = [-1,5,3,4,0]
Output: [-1,0,3,4,5]

Example 3:
Input: head = []
Output: []


Constraints:
The number of nodes in the list is in the range [0, 5 * 10^4].
-10^5 <= Node.val <= 10^5


Follow up: Can you sort the linked list in O(n logn) time and O(1) memory (i.e. constant space)?*/

    //Time Complexity: O(n log n)
    //Space Complexity: O(log n)
    fun sortList(head: ListNode?): ListNode? {
        if (head == null || head.next == null) return head

        val mid = getMiddle(head)
        val left = sortList(head)
        val right = sortList(mid)

        return merge(left, right)
    }

    fun getMiddle(node: ListNode?): ListNode? {
        var slow = node
        var fast = node
        var prev: ListNode? = null
        while (fast != null && fast.next != null) {
            prev = slow
            slow = slow?.next
            fast = fast.next?.next
        }

        // Disconnect the first half from the second
        if (prev != null)
            prev.next = null

        return slow
    }

    /*
    sortList(4 → 2 → 1 → 3)
      ↳ sortList(4 → 2)
          ↳ sortList(4) → returns 4
          ↳ sortList(2) → returns 2
          ↳ merge(4, 2) → returns 2 → 4
      ↳ sortList(1 → 3)
          ↳ sortList(1) → returns 1
          ↳ sortList(3) → returns 3
          ↳ merge(1, 3) → returns 1 → 3
      ↳ merge(2 → 4, 1 → 3) → returns 1 → 2 → 3 → 4
    * */
    fun merge(left: ListNode?, right: ListNode?): ListNode? {
        val dummy = ListNode(0)
        var tail: ListNode? = dummy
        var i1 = left
        var i2 = right

        while (i1 != null && i2 != null) {
            if (i1.`val` < i2.`val`) {
                tail?.next = i1
                i1 = i1.next
            } else {
                tail?.next = i2
                i2 = i2.next
            }
            tail = tail?.next
        }

        //Attach the remaining non-null list (i1 or i2) to the tail of the merged list.
        tail?.next = i1 ?: i2
        return dummy.next
    }
}

fun main() {
    val obj = SortLinkedList()
    val input = listOf(-1, 5, 3, 4, 0)
    val head = buildList(input)
    val result = obj.sortList(head)
    println(toList(result))
}