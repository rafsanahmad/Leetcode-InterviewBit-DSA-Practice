  /*
 * *
 *  * Maximum Subarray.kt
 *  * Created by Rafsan Ahmad on 6/30/25, 7:50PM
 *  * Copyright (c) 2025 . All rights reserved.
 *  *
 *
 */

package kotlinclasses.DivideAndConquer

class MaximumSubarrayUsingDivideAndConquer {
    //https://leetcode.com/problems/maximum-subarray/description/
    /*
    Given an integer array nums, find the contiguous subarray (containing at least one number)
    which has the largest sum and return its sum.

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

Constraints:
1 <= nums.length <= 10^5
-10^4 <= nums[i] <= 10^4

Follow up: If you have figured out the O(n) solution, try coding another solution using the
divide and conquer approach, which is more subtle.
    */

    //Follow UP: Using Divide and conquer approach
    /*📈 Time Complexity:
T(n) = 2T(n/2) + O(n) ⟹ Master Theorem → O(n log n)
Kadane’s is faster (O(n)), but this approach helps understand recursion, binary divide
strategies, and also applies in scenarios like parallel computing.*/

    fun maxSubArray(nums: List<Int>): Int {
        return divideAndConquer(nums, 0, nums.size - 1)
    }

    fun divideAndConquer(nums: List<Int>, left: Int, right: Int): Int {
        if (left == right) return nums[left]

        val mid = (left + right) / 2

        val leftMax = divideAndConquer(nums, left, mid)
        val rightMax = divideAndConquer(nums, mid + 1, right)
        val crossMax = maxCrossingSum(nums, left, mid, right)

        return maxOf(leftMax, rightMax, crossMax)
    }

    /*Purpose of maxCrossingSum
When you divide an array into two halves, the maximum subarray could be:

Entirely in the left half
Entirely in the right half
Crossing the middle (starts in the left, ends in the right)
The first two are handled by recursion.
👉 The maxCrossingSum function handles case 3 — the maximum subarray that spans both halves,
crossing the midpoint.*/

    fun maxCrossingSum(nums: List<Int>, left: Int, mid: Int, right: Int): Int {
        var sum = 0
        var leftSum = Int.MIN_VALUE

        for (i in mid downTo left) {
            sum += nums[i]
            leftSum = maxOf(leftSum, sum)
        }

        sum = 0
        var rightSum = Int.MIN_VALUE

        for (i in mid + 1..right) {
            sum += nums[i]
            rightSum = maxOf(rightSum, sum)
        }

        return leftSum + rightSum
    }

}

fun main() {
    val obj = MaximumSubarrayUsingDivideAndConquer()
    println(obj.maxSubArray(listOf(-2, 1, -3, 4, -1, 2, 1, -5, 4)))
}