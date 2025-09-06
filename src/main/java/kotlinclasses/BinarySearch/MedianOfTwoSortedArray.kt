/*
 * *
 *  * Median of Two Sorted Arrays.kt
 *  * Created by Rafsan Ahmad on 9/6/25, 6:36PM
 *  * Copyright (c) 2025 . All rights reserved.
 *  *
 *
 */

package kotlinclasses.BinarySearch

import kotlin.math.max
import kotlin.math.min

class MedianOfTwoSortedArray {
    /*//https://leetcode.com/problems/median-of-two-sorted-arrays/description/
    /*Given two sorted arrays nums1 and nums2 of size m and n respectively, return the median of the
    two sorted arrays.

The overall run time complexity should be O(log (m+n)).

Example 1:
Input: nums1 = [1,3], nums2 = [2]
Output: 2.00000
Explanation: merged array = [1,2,3] and median is 2.

Example 2:
Input: nums1 = [1,2], nums2 = [3,4]
Output: 2.50000
Explanation: merged array = [1,2,3,4] and median is (2 + 3) / 2 = 2.5.


Constraints:
nums1.length == m
nums2.length == n
0 <= m <= 1000
0 <= n <= 1000
1 <= m + n <= 2000
-10^6 <= nums1[i], nums2[i] <= 10^6*/*/

    /*Time Complexity:
Binary search runs on the smaller array → O(log(min(n, m)))
(n = nums1.size, m = nums2.size)
Space Complexity:
Only a few variables stored → O(1) (constant space)*/

    /*Partition Trace
Step 1 → partitionX = 2, partitionY = 4
nums1: [ 1   3 |  8   9  15 ]
             ↑    ↑
          maxLx  minRx

nums2: [ 7  11  18  19 | 21  25 ]
                     ↑    ↑
                   maxLy  minRy
Values → maxLx=3, minRx=8, maxLy=19, minRy=21
Check: 3<=21 ✔, but 19<=8 ❌ → move right


Step 2 → partitionX = 3, partitionY = 3
nums1: [ 1   3   8 |  9  15 ]
                 ↑    ↑
              maxLx  minRx

nums2: [ 7  11  18 | 19  21  25 ]
                 ↑    ↑
              maxLy  minRy
Values → maxLx=8, minRx=9, maxLy=18, minRy=19
Check: 8<=19 ✔, but 18<=9 ❌ → move right


Step 3 → partitionX = 4, partitionY = 2
nums1: [ 1   3   8   9 | 15 ]
                     ↑    ↑
                   maxLx  minRx

nums2: [ 7  11 | 18  19  21  25 ]
             ↑    ↑
         maxLy  minRy
Values → maxLx=9, minRx=15, maxLy=11, minRy=18
Check: 9<=18 ✔ and 11<=15 ✔ → ✅ correct partition

🔹 Final Result
Since total = 11 (odd), median = max(maxLx, maxLy) = max(9, 11) = 11.*/
    fun findMedianSortedArrays(nums1: IntArray, nums2: IntArray): Double {
        val len1 = nums1.size
        val len2 = nums2.size

        // Ensure nums1 is the smaller array (binary search runs on the smaller one)
        if (len1 > len2) {
            return findMedianSortedArrays(nums2, nums1)
        }

        var low = 0
        var high = len1

        // Binary search on nums1
        while (low <= high) {
            // Partition nums1
            val partitionX = (low + high) / 2
            // Partition nums2 must complement nums1’s partition so that left side has half elements
            val partitionY = (len1 + len2 + 1) / 2 - partitionX

            // Border values: if partition at edge, take MIN/MAX sentinel
            val maxLeftX = if (partitionX == 0) Int.MIN_VALUE else nums1[partitionX - 1]
            val minRightX = if (partitionX == len1) Int.MAX_VALUE else nums1[partitionX]

            val maxLeftY = if (partitionY == 0) Int.MIN_VALUE else nums2[partitionY - 1]
            val minRightY = if (partitionY == len2) Int.MAX_VALUE else nums2[partitionY]

            //Found the correct partition
            if (maxLeftX <= minRightY && maxLeftY <= minRightX) {
                return if ((len1 + len2) % 2 == 0) {
                    // Even total length: median is avg of middle two
                    val mid1 = max(maxLeftX, maxLeftY)
                    val mid2 = min(minRightX, minRightY)
                    (mid1 + mid2) / 2.0
                } else {
                    // Odd total length: median is max of left side
                    max(maxLeftX, maxLeftY).toDouble()
                }
            }
            //Too many elements from nums1, move left
            else if (maxLeftX > minRightY) {
                high = partitionX - 1
            }
            //Too few elements from nums1, move right
            else {
                low = partitionX + 1
            }
        }

        return 0.0 // should never happen if inputs are valid
    }
}

fun main() {
    val obj = MedianOfTwoSortedArray()
    val nums1 = intArrayOf(1, 2)
    val nums2 = intArrayOf(3, 4)

    println(obj.findMedianSortedArrays(nums1, nums2)) // Expected: 2.5
}
