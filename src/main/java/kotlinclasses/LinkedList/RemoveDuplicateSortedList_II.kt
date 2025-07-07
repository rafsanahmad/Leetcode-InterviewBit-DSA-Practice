/*
 * *
 *  * Remove Duplicates from Sorted List II.kt
 *  * Created by Rafsan Ahmad on 7/8/25, 4:30AM
 *  * Copyright (c) 2025 . All rights reserved.
 *  *
 *
 */

package kotlinclasses.LinkedList

class RemoveDuplicateSortedList_II {
    //https://leetcode.com/problems/remove-duplicates-from-sorted-list-ii/description/
    /*Given the head of a sorted linked list, delete all nodes that have duplicate numbers,
    leaving only distinct numbers from the original list. Return the linked list sorted as well.


Example 1:
Input: head = [1,2,3,3,4,4,5]
Output: [1,2,5]

Example 2:
Input: head = [1,1,1,2,3]
Output: [2,3]


Constraints:
The number of nodes in the list is in the range [0, 300].
-100 <= Node.val <= 100
The list is guaranteed to be sorted in ascending order.*/

    fun deleteDuplicates(head: ListNode?): ListNode? {
        if (head == null) return null

        val dummy = ListNode(0)
        dummy.next = head
        var temp = head
        var prev: ListNode?
        var lastUnique: ListNode? = dummy

        while (temp != null) {
            var hasDuplicate = false
            prev = temp
            temp = temp.next
            if (prev.`val` != temp?.`val`) {
                lastUnique = prev
            }
            while (prev?.`val` == temp?.`val`) {
                hasDuplicate = true
                prev = temp
                temp = temp?.next
            }

            //Connect
            if (hasDuplicate) {
                //Duplicate found
                lastUnique?.next = temp
            }
        }

        return dummy.next
    }
}

fun main() {
    val obj = RemoveDuplicateSortedList_II()
    val input = listOf(1, 2, 3, 3, 4, 4, 5)
    val head = buildList(input)
    val result = obj.deleteDuplicates(head)
    println(toList(result))

    val input2 = listOf(1, 1, 1, 2, 3)
    val head2 = buildList(input2)
    val result2 = obj.deleteDuplicates(head2)
    println(toList(result2))
}