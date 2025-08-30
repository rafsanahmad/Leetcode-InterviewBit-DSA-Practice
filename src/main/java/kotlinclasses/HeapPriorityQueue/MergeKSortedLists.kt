/*
 * *
 *  * Merge k Sorted Lists.kt
 *  * Created by Rafsan Ahmad on 8/28/25, 10:02PM
 *  * Copyright (c) 2025 . All rights reserved.
 *  *
 *
 */

package kotlinclasses.HeapPriorityQueue

import kotlinclasses.LinkedList.ListNode
import java.util.PriorityQueue

class MergeKSortedLists {
    //https://leetcode.com/problems/merge-k-sorted-lists/description/
    /*You are given an array of k linked-lists lists, each linked-list is sorted in ascending order.

Merge all the linked-lists into one sorted linked-list and return it.

Example 1:
Input: lists = [[1,4,5],[1,3,4],[2,6]]
Output: [1,1,2,3,4,4,5,6]
Explanation: The linked-lists are:
[
  1->4->5,
  1->3->4,
  2->6
]
merging them into one sorted linked list:
1->1->2->3->4->4->5->6

Example 2:
Input: lists = []
Output: []

Example 3:
Input: lists = [[]]
Output: []


Constraints:
k == lists.length
0 <= k <= 10^4
0 <= lists[i].length <= 500
-10^4 <= lists[i][j] <= 10^4
lists[i] is sorted in ascending order.
The sum of lists[i].length will not exceed 10^4.*/

    /*Time Complexity:
Let N = total number of nodes across all lists.
Let k = number of lists.
Each poll() and add() on heap costs O(log k).
We perform N such operations.
Total: O(N log k)

Space Complexity:
Heap stores at most k nodes → O(k)
Result list reuses existing nodes → O(1) extra
Overall: O(k)*/
    fun mergeKLists(lists: Array<ListNode?>): ListNode? {
        if (lists.isEmpty()) return null

        // Min-heap (priority queue) based on node values
        val queue: PriorityQueue<ListNode> = PriorityQueue { a, b -> a.`val` - b.`val` }

        // Dummy node to simplify result construction
        val result = ListNode(0)
        var tail: ListNode? = result

        // Step 1: Push the head of each list into the min-heap
        for (i in lists.indices) {
            if (lists[i] != null)
                queue.add(lists[i])
        }

        // Step 2: Process heap until empty
        while (!queue.isEmpty()) {
            // Extract the smallest node among the k lists
            val currNode = queue.poll()

            // Append it to the result list
            tail?.next = currNode
            tail = tail?.next

            // KEY STEP:
            // If current node has a next, push it into heap.
            // This ensures that at any time, heap contains at most one "active" node from each list.
            // Hence, the heap always knows the global minimum among remaining nodes.
            if (currNode.next != null)
                queue.add(currNode.next)
        }

        // Skip dummy node and return merged head
        return result.next
    }
}

fun main() {
    val obj = MergeKSortedLists()
    // Build test input: lists = [[1,4,5],[1,3,4],[2,6]]
    val l1 = ListNode(1).apply {
        next = ListNode(4).apply {
            next = ListNode(5)
        }
    }
    val l2 = ListNode(1).apply {
        next = ListNode(3).apply {
            next = ListNode(4)
        }
    }
    val l3 = ListNode(2).apply {
        next = ListNode(6)
    }

    val lists = arrayOf<ListNode?>(l1, l2, l3)
    val merged = obj.mergeKLists(lists)

    // Print output
    var curr = merged
    print("Output: [")
    while (curr != null) {
        print(curr.`val`)
        curr = curr.next
        if (curr != null) print(",")
    }
    println("]")
}