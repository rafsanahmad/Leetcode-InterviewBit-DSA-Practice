/*
 * *
 *  * Minimum Size Subarray Sum.kt
 *  * Created by Rafsan Ahmad on 6/4/25, 4:45PM
 *  * Copyright (c) 2025 . All rights reserved.
 *  *
 *
 */

package kotlinclasses.TwoPointer_SlidingWindow

import kotlin.math.min

class MinimumSizeSubarraySum {
    //https://leetcode.com/problems/minimum-size-subarray-sum/description/
    /*Given an array of positive integers nums and a positive integer target, return the minimal length of a subarray whose
    sum is greater than or equal to target. If there is no such subarray, return 0 instead.


Example 1:
Input: target = 7, nums = [2,3,1,2,4,3]
Output: 2
Explanation: The subarray [4,3] has the minimal length under the problem constraint.

Example 2:
Input: target = 4, nums = [1,4,4]
Output: 1

Example 3:
Input: target = 11, nums = [1,1,1,1,1,1,1,1]
Output: 0


Constraints:
1 <= target <= 10^9
1 <= nums.length <= 10^5
1 <= nums[i] <= 10^4


Follow up: If you have figured out the O(n) solution, try coding another solution of which the
time complexity is O(n log(n)).*/

    fun minSubArrayLen(target: Int, nums: IntArray): Int {
        var result = Int.MAX_VALUE
        val len = nums.size
        var left = 0
        var right = 0
        var sum = 0
        while (left < len && right < len) {
            sum += nums[right]
            while (sum >= target && left < len) {
                result = min(result, right - left + 1)
                sum -= nums[left]
                left++
            }

            right++
        }

        return if (result == Int.MAX_VALUE) 0 else result
    }
}

fun main() {
    val subarraySum = MinimumSizeSubarraySum()
    println(subarraySum.minSubArrayLen(7, intArrayOf(2, 3, 1, 2, 4, 3)))
    println(subarraySum.minSubArrayLen(4, intArrayOf(1, 4, 4)))
}