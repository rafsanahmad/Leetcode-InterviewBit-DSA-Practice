/*
 * *
 *  * Three Sum.kt
 *  * Created by Rafsan Ahmad on 7/9/25, 8:35PM
 *  * Copyright (c) 2025 . All rights reserved.
 *  *
 *
 */

package kotlinclasses.TwoPointer_SlidingWindow

import java.util.Arrays

class ThreeSum {
    //https://leetcode.com/problems/3sum/description/
    /*Given an integer array nums, return all the triplets [nums[i], nums[j], nums[k]] such
    that i != j, i != k, and j != k, and nums[i] + nums[j] + nums[k] == 0.

Notice that the solution set must not contain duplicate triplets.

Example 1:
Input: nums = [-1,0,1,2,-1,-4]
Output: [[-1,-1,2],[-1,0,1]]
Explanation:
nums[0] + nums[1] + nums[2] = (-1) + 0 + 1 = 0.
nums[1] + nums[2] + nums[4] = 0 + 1 + (-1) = 0.
nums[0] + nums[3] + nums[4] = (-1) + 2 + (-1) = 0.
The distinct triplets are [-1,0,1] and [-1,-1,2].
Notice that the order of the output and the order of the triplets does not matter.

Example 2:
Input: nums = [0,1,1]
Output: []
Explanation: The only possible triplet does not sum up to 0.

Example 3:
Input: nums = [0,0,0]
Output: [[0,0,0]]
Explanation: The only possible triplet sums up to 0.


Constraints:
3 <= nums.length <= 3000
-10^5 <= nums[i] <= 10^5*/

    //Time complexity: O(n^2)
    //Space complexity: O(n^2) (worst-case number of triplets)
    fun threeSum(nums: IntArray): List<List<Int>> {
        val result: MutableList<List<Int>> = mutableListOf()
        val set: MutableSet<List<Int>> = mutableSetOf()
        if (nums.isEmpty()) return result
        Arrays.sort(nums)

        for (i in nums.indices) {
            var left = i + 1
            var right = nums.size - 1

            while (left < right) {
                val sum = nums[i] + nums[left] + nums[right]
                if (sum == 0) {
                    set.add(listOf(nums[i], nums[left], nums[right]))
                    left++
                    right--
                } else if (sum < 0) {
                    left++
                } else {
                    right--
                }
            }
        }

        for (list in set) {
            result.add(list)
        }

        return result
    }

    //Remove Set - Using duplicate check
    //Time complexity: O(n^2)
    /*Overall Space:
    Auxiliary: O(1)
    Output: Up to O(n^2) (same in worst case)*/

    fun threeSumOptimized(nums: IntArray): List<List<Int>> {
        val result: MutableList<List<Int>> = mutableListOf()
        if (nums.isEmpty()) return result
        Arrays.sort(nums)

        for (i in nums.indices) {
            if (i > 0 && nums[i] == nums[i - 1]) continue // Skip duplicate for 'i'
            var left = i + 1
            var right = nums.size - 1

            while (left < right) {
                if (right < nums.size - 1 && nums[right] == nums[right + 1]) {
                    right--
                    continue
                }
                val sum = nums[i] + nums[left] + nums[right]
                if (sum == 0) {
                    result.add(listOf(nums[i], nums[left], nums[right]))
                    left++
                    right--
                } else if (sum < 0) {
                    left++
                } else {
                    right--
                }
            }
        }

        return result
    }
}

fun main() {
    val obj = ThreeSum()
    val arr = intArrayOf(-1, 0, 1, 2, -1, -4)
    println(obj.threeSum(arr))
    println(obj.threeSumOptimized(arr))
}