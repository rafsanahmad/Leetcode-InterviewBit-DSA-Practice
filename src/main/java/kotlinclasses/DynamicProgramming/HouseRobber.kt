/*
 * *
 *  * House Robber.kt
 *  * Created by Rafsan Ahmad on 6/27/25, 2:06PM
 *  * Copyright (c) 2025 . All rights reserved.
 *  *
 *
 */

package kotlinclasses.DynamicProgramming

import kotlin.math.max

class HouseRobber {
    //https://leetcode.com/problems/house-robber/description/
    /*You are a professional robber planning to rob houses along a street. Each house has
    a certain amount of money stashed, the only constraint stopping you from robbing each
    of them is that adjacent houses have security systems connected and it will
    automatically contact the police if two adjacent houses were broken into on the same
    night.

Given an integer array nums representing the amount of money of each house, return the
maximum amount of money you can rob tonight without alerting the police.


Example 1:
Input: nums = [1,2,3,1]
Output: 4
Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
Total amount you can rob = 1 + 3 = 4.

Example 2:
Input: nums = [2,7,9,3,1]
Output: 12
Explanation: Rob house 1 (money = 2), rob house 3 (money = 9) and rob house 5 (money = 1).
Total amount you can rob = 2 + 9 + 1 = 12.


Constraints:
1 <= nums.length <= 100
0 <= nums[i] <= 400*/

    //Top down
    fun rob(nums: IntArray): Int {
        val dp = IntArray(nums.size) { -1 }
        return helper(nums, 0, dp)
    }

    fun helper(nums: IntArray, index: Int, dp: IntArray): Int {
        if (index >= nums.size) return 0
        if (dp[index] != -1) return dp[index]

        //Rob
        var rob = nums[index] + helper(nums, index + 2, dp)
        //Do not rob
        var notRob = helper(nums, index + 1, dp)

        var maxTotal = max(rob, notRob)
        dp[index] = maxTotal
        return maxTotal
    }


    //Bottom up
    fun rob2(nums: IntArray): Int {
        val n = nums.size
        val dp = IntArray(n + 2) // dp[n] and dp[n+1] = 0

        for (i in n - 1 downTo 0) {
            dp[i] = maxOf(dp[i + 1], nums[i] + dp[i + 2])
        }

        return dp[0]
    }
}

fun main() {
    val obj = HouseRobber()
    println(obj.rob(intArrayOf(2, 7, 9, 3, 1)))
    println(obj.rob2(intArrayOf(2, 7, 9, 3, 1)))
}