/*
 * *
 *  * Merge Sorted Array.kt
 *  * Created by Rafsan Ahmad on 5/18/25, 10:16PM
 *  * Copyright (c) 2025 . All rights reserved.
 *  *
 *
 */

package kotlinclasses.Array

class MergeSortedArray {
    //https://leetcode.com/problems/merge-sorted-array/description/
    /*You are given two integer arrays nums1 and nums2, sorted in non-decreasing order, and two
    integers m and n, representing the number of elements in nums1 and nums2 respectively.

Merge nums1 and nums2 into a single array sorted in non-decreasing order.

The final sorted array should not be returned by the function, but instead be stored inside the
array nums1. To accommodate this, nums1 has a length of m + n, where the first m elements denote
the elements that should be merged, and the last n elements are set to 0 and should be ignored.
nums2 has a length of n.



Example 1:

Input: nums1 = [1,2,3,0,0,0], m = 3, nums2 = [2,5,6], n = 3
Output: [1,2,2,3,5,6]
Explanation: The arrays we are merging are [1,2,3] and [2,5,6].
The result of the merge is [1,2,2,3,5,6] with the underlined elements coming from nums1.
Example 2:

Input: nums1 = [1], m = 1, nums2 = [], n = 0
Output: [1]
Explanation: The arrays we are merging are [1] and [].
The result of the merge is [1].
Example 3:

Input: nums1 = [0], m = 0, nums2 = [1], n = 1
Output: [1]
Explanation: The arrays we are merging are [] and [1].
The result of the merge is [1].
Note that because m = 0, there are no elements in nums1. The 0 is only there to ensure the merge
result can fit in nums1.


Constraints:

nums1.length == m + n
nums2.length == n
0 <= m, n <= 200
1 <= m + n <= 200
-10^9 <= nums1[i], nums2[j] <= 10^9*/

    fun merge(nums1: IntArray, m: Int, nums2: IntArray, n: Int): Unit {
        var total = m + n - 1
        var m1 = m
        var n1 = n
        while (m1 > 0 && n1 > 0) {
            if (nums1[m1 - 1] < nums2[n1 - 1]) {
                nums1[total] = nums2[n1 - 1]
                n1--
            } else {
                nums1[total] = nums1[m1 - 1]
                m1--
            }
            total--
        }

        while (n1 > 0) {
            nums1[total] = nums2[n1 - 1]
            n1--
            total--
        }
    }
}

fun main(args: Array<String>) {
    val mergeSortedArray = MergeSortedArray()
    val arr1 = intArrayOf(1, 2, 3, 0, 0, 0)
    val arr2 = intArrayOf(2, 5, 6)
    mergeSortedArray.merge(arr1, 3, arr2, 3)
    println(arr1.contentToString())
}