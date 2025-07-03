/*
 * *
 *  * Kadane Algorithm.kt
 *  * Created by Rafsan Ahmad on 6/30/25, 7:38PM
 *  * Copyright (c) 2025 . All rights reserved.
 *  *
 *
 */

package kotlinclasses.Algorithm

class KadaneAlgorithm {
    /*Kadane's Algorithm is a dynamic programming approach used to solve the problem:

Find the maximum sum of a contiguous subarray in a 1D array of integers.
It runs in O(n) time and O(1) space.

✅ Intuition
At each position in the array, you have two choices:
Start a new subarray from the current element.
Extend the previous subarray by including the current element.
You pick whichever gives you a larger sum.

✅ Step-by-Step Logic
Variables used:
currentSum: the maximum sum ending at the current index
maxSum: the overall maximum sum found so far

Steps:
Initialize:
currentSum = nums[0]
maxSum = nums[0]

Loop through the array starting from index 1 to the end:
currentSum = max(nums[i], currentSum + nums[i])

Choose the larger one: start new from nums[i] or add to previous sum
maxSum = max(maxSum, currentSum)

Update the overall max if needed
Return maxSum

    Example 1:
    Input: nums = [-2,1,-3,4,-1,2,1,-5,4]
    Output: 6
    Explanation: [4,-1,2,1] has the largest sum = 6.

    Example 2:
    Input: nums = [1]
    Output: 1

    Example 3:
    Input: nums = [5,4,-1,7,8]
    Output: 23
*/

    //Time complexity: O(n)
    fun maxSubArray(nums: List<Int>): Int {
        if (nums.isEmpty()) return 0
        var currentSum = 0
        var maxSum = Integer.MIN_VALUE
        for (i in 0 until nums.size) {
            currentSum += nums[i]
            if (nums[i] > currentSum)
                currentSum = nums[i]
            maxSum = maxOf(maxSum, currentSum)
        }
        return maxSum
    }
}

fun main() {
    val obj = KadaneAlgorithm()
    println(obj.maxSubArray(listOf(-2, 1, -3, 4, -1, 2, 1, -5, 4)))
    println(obj.maxSubArray(listOf(5, -3, 5)))
    println(obj.maxSubArray(listOf(5, 4, -1, 7, 8)))
}