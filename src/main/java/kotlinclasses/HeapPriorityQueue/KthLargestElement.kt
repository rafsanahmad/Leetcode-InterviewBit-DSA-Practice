/*
 * *
 *  * Kth Largest Element in an Array.kt
 *  * Created by Rafsan Ahmad on 7/2/25, 5:21PM
 *  * Copyright (c) 2025 . All rights reserved.
 *  *
 *
 */

package kotlinclasses.HeapPriorityQueue

import java.util.PriorityQueue

class KthLargestElement {
    //https://leetcode.com/problems/kth-largest-element-in-an-array/description/
    /*Given an integer array nums and an integer k, return the kth largest element in the
    array.

Note that it is the kth largest element in the sorted order, not the kth distinct element.

Can you solve it without sorting?

Example 1:
Input: nums = [3,2,1,5,6,4], k = 2
Output: 5

Example 2:
Input: nums = [3,2,3,1,2,4,5,5,6], k = 4
Output: 4

Constraints:
1 <= k <= nums.length <= 10^5
-10^4 <= nums[i] <= 10^4*/

    //Using MAX heap
    //Time complexity: O(n log n) + O(k log n) = O(n log n)
    //Space complexity: O(n)
    fun findKthLargest(nums: IntArray, k: Int): Int {
        if (nums.isEmpty()) return 0
        var result = 0
        val pq = PriorityQueue<Int> { a, b -> b - a }
        for (i in nums.indices) {
            pq.add(nums[i])
        }

        var t = k
        while (t > 0) {
            result = pq.poll()
            t--
        }
        return result
    }


    //Using Min Heap of Size k
    //Time complexity: O(n log k)
    //Space Complexity: O(K)
    fun findKthLargestOptimized(nums: IntArray, k: Int): Int {
        if (nums.isEmpty()) return 0
        //Min heap
        val pq = PriorityQueue<Int>(k)
        for (i in nums.indices) {
            pq.add(nums[i])
            if (pq.size > k) {
                pq.poll()
            }
        }

        return pq.peek()
    }
}

fun main() {
    val obj = KthLargestElement()
    val arr = intArrayOf(3, 2, 1, 5, 6, 4)
    println(obj.findKthLargest(arr, 2))
    println(obj.findKthLargestOptimized(arr, 2))
}