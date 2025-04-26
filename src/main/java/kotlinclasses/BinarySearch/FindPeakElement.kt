/*
 * *
 *  * Find Peak Element.kt
 *  * Created by Rafsan Ahmad on 4/26/25, 8:50PM
 *  * Copyright (c) 2025 . All rights reserved.
 *  *
 *
 */

package kotlinclasses.BinarySearch

class FindPeakElement {
    //res/find_peak_element.png
    /*A peak element is an element that is strictly greater than its neighbors.

Given a 0-indexed integer array nums, find a peak element, and return its index. If the array
contains multiple peaks, return the index to any of the peaks.

You may imagine that nums[-1] = nums[n] = -∞. In other words, an element is always considered to
be strictly greater than a neighbor that is outside the array.

You must write an algorithm that runs in O(log n) time.



Example 1:
Input: nums = [1,2,3,1]
Output: 2
Explanation: 3 is a peak element and your function should return the index number 2.

Example 2:
Input: nums = [1,2,1,3,5,6,4]
Output: 5
Explanation: Your function can return either index number 1 where the peak element is 2, or
index number 5 where the peak element is 6.


Constraints:
1 <= nums.length <= 1000
-2^31 <= nums[i] <= 2^31 - 1
nums[i] != nums[i + 1] for all valid i.*/

    /*Given an array, we need to find the peak element.
As, the subportions of the array are increasing/decreasing ( only then we would be able to find
peak ), there are subportions of array which are sorted, so we could use binary search to get
this problem done. But exactly how ?

This is an interesting part.

For a mid element, there could be three possible cases :
image

Case 1 : mid lies on the right of our result peak ( Observation : Our peak element search space
is leftside ) - Increasing line
Case 2 : mid is equal to the peak element ( Observation : mid element is greater than its
neighbors ) - return index
Case 3 : mid lies on the left. ( Observation : Our peak element search space is rightside ) -
Decreasing line
*/
    fun findPeakElement(nums: IntArray): Int {
        val n: Int = nums.size // Size of array

        // Edge cases:
        if (n == 1) return 0
        if (nums[0] > nums[1]) return 0
        if (nums[n - 1] > nums[n - 2]) return n - 1

        var low = 1
        var high = n - 2
        while (low <= high) {
            val mid = (low + high) / 2

            // If arr[mid] is the peak:
            if (nums[mid - 1] < nums[mid] && nums[mid] > nums[mid + 1])
                return mid

            // Increasing line - Peak should be in Right
            if (nums[mid] > nums[mid - 1]) low = mid + 1

            // Decreasing line - Peak should be in left
            else high = mid - 1
        }

        // Dummy return statement
        return -1
    }
}

fun main() {
    val element = FindPeakElement()
    println(element.findPeakElement(intArrayOf(1, 2, 1, 3, 5, 6, 4)))
}