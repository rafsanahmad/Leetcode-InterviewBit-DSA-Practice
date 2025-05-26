/*
 * *
 *  * Add Two Numbers.kt
 *  * Created by Rafsan Ahmad on 5/26/25, 9:25PM
 *  * Copyright (c) 2025 . All rights reserved.
 *  *
 *
 */

package kotlinclasses.LinkedList

class AddTwoNumbers {
    /*You are given two non-empty linked lists representing two non-negative integers.
    The digits are stored in reverse order, and each of their nodes contains a single digit.
    Add the two numbers and return the sum as a linked list.

You may assume the two numbers do not contain any leading zero, except the number 0 itself.

Example 1:
Input: l1 = [2,4,3], l2 = [5,6,4]
Output: [7,0,8]
Explanation: 342 + 465 = 807.

Example 2:
Input: l1 = [0], l2 = [0]
Output: [0]

Example 3:
Input: l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
Output: [8,9,9,9,0,0,0,1]

Constraints:
The number of nodes in each linked list is in the range [1, 100].
0 <= Node.val <= 9
It is guaranteed that the list represents a number that does not have leading zeros.*/

    fun addTwoNumbers(l1: ListNode?, l2: ListNode?): ListNode? {
        val dummy = ListNode(-1)
        var current: ListNode? = dummy
        var p1 = l1
        var p2 = l2
        var carry = 0

        while (p1 != null || p2 != null || carry != 0) {
            val x = p1?.`val` ?: 0
            val y = p2?.`val` ?: 0
            val sum = x + y + carry
            carry = sum / 10
            current?.next = ListNode(sum % 10)
            current = current?.next
            p1 = p1?.next
            p2 = p2?.next
        }

        return dummy.next
    }

    fun printList(node: ListNode?) {
        var temp: ListNode? = node
        while (temp != null) {
            print("${temp.`val`} ")
            temp = temp.next
        }
    }
}

fun main() {
    val obj = AddTwoNumbers()
    val node1 = ListNode(9)
    node1.next = ListNode(9)
    node1.next?.next = ListNode(9)
    node1.next?.next?.next = ListNode(9)

    val node2 = ListNode(9)
    node2.next = ListNode(9)

    obj.printList(obj.addTwoNumbers(node1, node2))
}