/*
 * *
 *  * Rotate List.kt
 *  * Created by Rafsan Ahmad on 7/8/25, 8:18PM
 *  * Copyright (c) 2025 . All rights reserved.
 *  *
 *
 */

package kotlinclasses.LinkedList

class RotateList {
    //https://leetcode.com/problems/rotate-list/description/
    /*Given the head of a linked list, rotate the list to the right by k places.

Example 1:
Input: head = [1,2,3,4,5], k = 2
Output: [4,5,1,2,3]

Example 2:
Input: head = [0,1,2], k = 4
Output: [2,0,1]


Constraints:
The number of nodes in the list is in the range [0, 500].
-100 <= Node.val <= 100
0 <= k <= 2 * 10^9*/

    fun rotateRight(head: ListNode?, k: Int): ListNode? {
        if (head == null || k == 0) return head
        var len = 0
        val dummy = ListNode(0)
        dummy.next = head
        var temp = head

        //Step 1: Find Length
        while (temp != null) {
            len++
            temp = temp.next
        }

        temp = head
        var prev: ListNode? = null
        if (k % len == 0) return head
        var rotatePoint = len - k % len

        //Step:2 - Find rotate point
        //1, 2, 3, 4, 5, k=2, rotatePoint = 4
        while (rotatePoint > 0) {
            prev = temp
            temp = temp?.next
            rotatePoint--
        }

        //Step:3 - Connect head and set null for last element
        //1, 2, 3, 4, 5, k=2
        //1-2-3-Null
        prev?.next = null
        dummy.next = temp

        //Step:4 - Go until last elemnt in list and connect head
        //1, 2, 3, 4, 5 -> 4,5-1,2,3
        while (temp?.next != null) {
            temp = temp.next
        }
        temp?.next = head
        return dummy.next
    }
}

fun main() {
    val obj = RotateList()
    val input = listOf(1, 2, 3, 4, 5)
    val head = buildList(input)
    val result = obj.rotateRight(head, 2)
    println(toList(result))

    val input2 = listOf(0, 1, 2)
    val head2 = buildList(input2)
    val result2 = obj.rotateRight(head2, 4)
    println(toList(result2))
}