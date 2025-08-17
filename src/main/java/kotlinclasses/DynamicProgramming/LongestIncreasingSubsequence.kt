/*
 * *
 *  * Longest Increasing Subsequence.kt
 *  * Created by Rafsan Ahmad on 8/16/25, 4:10AM
 *  * Copyright (c) 2025 . All rights reserved.
 *  *
 *
 */

package kotlinclasses.DynamicProgramming

import kotlin.math.max

class LongestIncreasingSubsequence {
    //https://leetcode.com/problems/longest-increasing-subsequence/description/
    /*Given an integer array nums, return the length of the longest strictly increasing subsequence.

Example 1:
Input: nums = [10,9,2,5,3,7,101,18]
Output: 4
Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4.

Example 2:
Input: nums = [0,1,0,3,2,3]
Output: 4

Example 3:
Input: nums = [7,7,7,7,7,7,7]
Output: 1


Constraints:
1 <= nums.length <= 2500
-10^4 <= nums[i] <= 10^4

Follow up: Can you come up with an algorithm that runs in O(n log(n)) time complexity?*/

    //dp[i] = the length of the Longest Increasing Subsequence that ends at index i (nums[i]).
    fun lengthOfLISTopDown(nums: IntArray): Int {
        if (nums.isEmpty()) return 0
        if (nums.size == 1) return 1
        val len = nums.size
        val dp = IntArray(len) { -1 }
        var result = Integer.MIN_VALUE
        for (i in nums.indices) {
            result = max(result, lisHelper(nums, i, dp))
        }
        return result
    }

    fun lisHelper(nums: IntArray, index: Int, dp: IntArray): Int {
        if (index >= nums.size)
            return 0

        if (dp[index] != -1)
            return dp[index]

        var maxLen = 1
        for (j in index + 1 until nums.size) {
            if (nums[index] < nums[j]) {
                val take = lisHelper(nums, j, dp) + 1
                maxLen = max(maxLen, take)
            }
        }

        dp[index] = maxLen
        return dp[index]
    }


    //Time Complexity: O(n²)
    //Space Complexity: O(n)
    fun lengthOfLIS(nums: IntArray): Int {
        if (nums.isEmpty()) return 0      // Edge case: empty input
        if (nums.size == 1) return 1      // Edge case: only one element → LIS = 1

        val len = nums.size
        val dp = IntArray(len) { 1 }      // dp[i] = LIS ending at index i, init with 1
        dp[0] = 1                         // First element itself forms subsequence of length 1

        // Build LIS bottom-up
        for (i in 1 until len) {          // For each element nums[i]
            for (j in i - 1 downTo 0) {   // Check all previous elements nums[j]
                if (nums[j] < nums[i]) {  // Can nums[i] extend LIS ending at nums[j]?
                    dp[i] = max(dp[i], dp[j] + 1) // Take max LIS length ending at i
                }
            }
        }

        // Find the overall maximum LIS length
        var result = -1
        for (i in dp.indices) {
            result = max(result, dp[i])
        }
        return result
    }


    //Time Complexity: O(n log n)
    //We maintain an array list where:
    //list[k] = the smallest possible tail value of an increasing subsequence of length k+1.
    /*Example Walkthrough
Input:
nums = [10, 9, 2, 5, 3, 7, 101, 18]

Step by step:
list = [10]
(LIS length 1: [10])

list = [9]
(replace 10 → 9, smaller tail is better)

list = [2]
(replace 9 → 2, again smaller tail)

list = [2, 5]
(extend: subsequence of length 2: [2, 5])

list = [2, 3]
(replace 5 → 3, better tail for LIS length 2)

list = [2, 3, 7]
(extend: LIS length 3: [2, 3, 7])

list = [2, 3, 7, 101]
(extend: LIS length 4: [2, 3, 7, 101])

list = [2, 3, 7, 18]
(replace 101 → 18, smaller tail is better)

Final length = 4 ✅*/
    fun lengthOfLISOptimized(nums: IntArray): Int {
        if (nums.isEmpty()) return 0

        val list = ArrayList<Int>()

        for (num in nums) {
            // Case 1: if num is greater than the last element → extend LIS
            if (list.isEmpty() || num > list[list.size - 1]) {
                list.add(num)
            } else {
                // Case 2: replace element in 'list' using binary search
                var left = 0
                var right = list.size - 1
                while (left < right) {
                    val mid = (left + right) / 2
                    if (list[mid] < num) {
                        left = mid + 1
                    } else {
                        right = mid
                    }
                }
                // Replace the first element >= num
                list[left] = num
            }
        }

        return list.size
    }
}

fun main() {
    val obj = LongestIncreasingSubsequence()
    val nums = intArrayOf(10, 9, 2, 5, 3, 7, 101, 18)
    println(obj.lengthOfLISTopDown(nums))
    println(obj.lengthOfLIS(nums))
    println(obj.lengthOfLISOptimized(nums))
}