/*
 * *
 *  * ListNode.kt
 *  * Created by Rafsan Ahmad on 5/26/25, 9:22PM
 *  * Copyright (c) 2025 . All rights reserved.
 *  *
 *
 */

package kotlinclasses.LinkedList

class ListNode(var `val`: Int) {
    var next: ListNode? = null
}

// Utility to build a linked list from an array
fun buildList(arr: List<Int>): ListNode? {
    if (arr.isEmpty()) return null
    val head = ListNode(arr[0])
    var current = head
    for (i in 1 until arr.size) {
        current.next = ListNode(arr[i])
        current = current.next!!
    }
    return head
}

// Utility to convert linked list to list of values
fun toList(head: ListNode?): List<Int> {
    val result = mutableListOf<Int>()
    var current = head
    while (current != null) {
        result.add(current.`val`)
        current = current.next
    }
    return result
}