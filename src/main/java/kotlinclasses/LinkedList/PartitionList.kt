/*
 * *
 *  * Partition List.kt
 *  * Created by Rafsan Ahmad on 7/21/25, 2:36AM
 *  * Copyright (c) 2025 . All rights reserved.
 *  *
 *
 */

package kotlinclasses.LinkedList

class PartitionList {
    //https://leetcode.com/problems/partition-list/description/
    //res/partitionList.jpg
    /*Given the head of a linked list and a value x, partition it such that all nodes less than x
    ome before nodes greater than or equal to x.

You should preserve the original relative order of the nodes in each of the two partitions.

Example 1:
1 → 4 → 3 → 2 → 5 → 2
        ↓
1 → 2 → 2 → 4 → 3 → 5

Input: head = [1,4,3,2,5,2], x = 3
Output: [1,2,2,4,3,5]

Example 2:
Input: head = [2,1], x = 2
Output: [1,2]

Constraints:
The number of nodes in the list is in the range [0, 200].
-100 <= Node.val <= 100
-200 <= x <= 200*/

    fun partition(head: ListNode?, x: Int): ListNode? {
        if (head == null) return head
        var left: ListNode? = ListNode(0)
        val result = left
        var right: ListNode? = ListNode(0)
        val connectPoint: ListNode? = right
        var temp = head

        while (temp != null) {
            if (temp.`val` < x) {
                left?.next = ListNode(temp.`val`)
                left = left?.next
            } else {
                right?.next = ListNode(temp.`val`)
                right = right?.next
            }
            temp = temp.next
        }

        left?.next = connectPoint?.next
        return result?.next
    }
}

fun main() {
    val obj = PartitionList()
    val input = listOf(1, 4, 3, 2, 5, 2)
    val head = buildList(input)
    println(toList(obj.partition(head, 3)))
}