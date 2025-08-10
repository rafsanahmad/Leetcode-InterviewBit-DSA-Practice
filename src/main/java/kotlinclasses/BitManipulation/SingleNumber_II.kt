/*
 * *
 *  * Single Number II.kt
 *  * Created by Rafsan Ahmad on 8/4/25, 2:49PM
 *  * Copyright (c) 2025 . All rights reserved.
 *  *
 *
 */

package kotlinclasses.BitManipulation

import java.util.Arrays

class SingleNumber_II {
    //https://leetcode.com/problems/single-number-ii/description/
    /*Given an integer array nums where every element appears three times except for one, which
    appears exactly once. Find the single element and return it.

You must implement a solution with a linear runtime complexity and use only constant extra space.

Example 1:
Input: nums = [2,2,3,2]
Output: 3

Example 2:
Input: nums = [0,1,0,1,0,1,99]
Output: 99


Constraints:
1 <= nums.length <= 3 * 10^4
-2^31 <= nums[i] <= 2^31 - 1
Each element in nums appears exactly three times except for one element which appears once.*/

    //Time Complexity: O(n log n)
    //Space Complexity: O(1) (in-place)
    fun singleNumber(nums: IntArray): Int {
        if (nums.isEmpty()) return 0
        Arrays.sort(nums)
        val len = nums.size
        var index = 1

        while (index < len) {
            if (nums[index] != nums[index - 1]) {
                return nums[index - 1]
            } else if (nums[index] != nums[index + 1]) {
                return nums[index + 1]
            }
            index += 3
        }

        return nums[len - 1]
    }


    //Time Complexity: O(32 × n) = O(n)
    //Space Complexity: O(1)
    fun singleNumberUsingBitManipulation(nums: IntArray): Int {
        if (nums.isEmpty()) return 0  // Handle edge case: empty input

        var result = 0  // This will hold the final unique number

        // Loop over all 32 bits (assuming 32-bit integers)
        for (i in 0 until 32) {
            var bitCount = 0  // Count how many numbers have the i-th bit set

            // For each number, check if the i-th bit is set
            for (num in nums) {
                if ((num shr i) and 1 == 1) {
                    bitCount++  // Increment if the i-th bit is set
                }
            }

            // If this bit is set in a number that appears once (not three times),
            // then bitCount % 3 will not be 0 — so we set it in result
            if (bitCount % 3 != 0) {
                result = result or (1 shl i)  // Set the i-th bit in the result
            }
        }

        return result  // Return the unique number
    }
}

fun main() {
    val obj = SingleNumber_II()
    val arr = intArrayOf(0, 1, 0, 1, 0, 1, 99)
    println(obj.singleNumber(arr))
    println(obj.singleNumberUsingBitManipulation(arr))
}