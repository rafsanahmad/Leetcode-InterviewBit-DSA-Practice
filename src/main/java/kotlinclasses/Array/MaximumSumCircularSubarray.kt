/*
 * *
 *  * Maximum Sum Circular Subarray.kt
 *  * Created by Rafsan Ahmad on 7/2/25, 2:02AM
 *  * Copyright (c) 2025 . All rights reserved.
 *  *
 *
 */

package kotlinclasses.Array

class MaximumSumCircularSubarray {
    //https://leetcode.com/problems/maximum-sum-circular-subarray/description/
    /*Given a circular integer array nums of length n, return the maximum possible sum of a
    non-empty subarray of nums.

A circular array means the end of the array connects to the beginning of the array. Formally,
the next element of nums[i] is nums[(i + 1) % n] and the previous element of
nums[i] is nums[(i - 1 + n) % n].

A subarray may only include each element of the fixed buffer nums at most once. Formally, for a
subarray nums[i], nums[i + 1], ..., nums[j], there does not exist i <= k1, k2 <= j
with k1 % n == k2 % n.


Example 1:
Input: nums = [1,-2,3,-2]
Output: 3
Explanation: Subarray [3] has maximum sum 3.

Example 2:
Input: nums = [5,-3,5]
Output: 10
Explanation: Subarray [5,5] has maximum sum 5 + 5 = 10.

Example 3:
Input: nums = [-3,-2,-3]
Output: -2
Explanation: Subarray [-2] has maximum sum -2.


Constraints:
n == nums.length
1 <= n <= 3 * 10^4
-3 * 10^4 <= nums[i] <= 3 * 10^4*/

    /*There are two scenarios to consider:
Case 1: Maximum Subarray does NOT wrap (normal)
Just use Kadane’s algorithm to find the maximum subarray sum.

Case 2: Maximum Subarray wraps around(circular)
In this case, the maximum circular subarray sum is:

total sum of array - minimum subarray sum
That’s because removing the minimum subarray from the middle gives you the maximum circular
subarray (like removing the dip in the middle).*/

    fun maxSubarraySumCircular(nums: IntArray): Int {
        if (nums.isEmpty()) return 0

        var total = 0
        var maxSum = nums[0]
        var curMax = 0
        var minSum = nums[0]
        var curMin = 0

        for (num in nums) {
            curMax = maxOf(curMax + num, num)
            maxSum = maxOf(maxSum, curMax)

            curMin = minOf(curMin + num, num)
            minSum = minOf(minSum, curMin)

            total += num
        }

        // If all numbers are negative, total - minSum will be 0, so return maxSum in that case
        return if (maxSum < 0) maxSum else maxOf(maxSum, total - minSum)
    }
}

fun main() {
    val obj = MaximumSumCircularSubarray()
    println(obj.maxSubarraySumCircular(intArrayOf(5, -3, 5)))
    println(obj.maxSubarraySumCircular(intArrayOf(1, -2, 3, -2)))
}