/*
 * *
 *  * Find First and Last Position of Element in Sorted Array.kt
 *  * Created by Rafsan Ahmad on 7/11/25, 4:04AM
 *  * Copyright (c) 2025 . All rights reserved.
 *  *
 *
 */

package kotlinclasses.BinarySearch

class FindFirstLastElementSortedArray {
    //https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/description/
    /*Given an array of integers nums sorted in non-decreasing order, find the starting and
    ending position of a given target value.

If target is not found in the array, return [-1, -1].

You must write an algorithm with O(log n) runtime complexity.

Example 1:
Input: nums = [5,7,7,8,8,10], target = 8
Output: [3,4]

Example 2:
Input: nums = [5,7,7,8,8,10], target = 6
Output: [-1,-1]

Example 3:
Input: nums = [], target = 0
Output: [-1,-1]


Constraints:
0 <= nums.length <= 10^5
-10^9 <= nums[i] <= 10^9
nums is a non-decreasing array.
-10^9 <= target <= 10^9*/

    //Worst-case time complexity: O(n)
    fun searchRange(nums: IntArray, target: Int): IntArray {
        if (nums.isEmpty()) return intArrayOf(-1, -1)
        var left = 0
        var right = nums.size

        while (left < right) {
            val mid = left + (right - left) / 2
            if (nums[mid] == target) {
                var end = mid
                var start = mid
                while (start >= 0 && nums[start] == target) {
                    start--
                }
                while (end < right && nums[end] == target) {
                    end++
                }
                return when {
                    start == mid && end == mid -> intArrayOf(start, end)
                    start != mid && end == mid -> intArrayOf(start + 1, end)
                    start == mid && end != mid -> intArrayOf(start, end - 1)
                    else -> intArrayOf(start + 1, end - 1)
                }
            } else if (nums[mid] < target) {
                left = mid + 1
            } else {
                right = mid
            }
        }

        return intArrayOf(-1, -1)
    }

    //Time complexity: O(log n)
    fun searchRangeApproach2(nums: IntArray, target: Int): IntArray {
        if (nums.isEmpty()) return intArrayOf(-1, -1)

        // Binary search to find the first (leftmost) index of target
        fun findFirst(): Int {
            var left = 0
            var right = nums.size
            while (left < right) {
                val mid = left + (right - left) / 2
                if (nums[mid] >= target) {
                    right = mid
                } else {
                    left = mid + 1
                }
            }
            return left
        }

        // Binary search to find the last (rightmost) index of target
        fun findLast(): Int {
            var left = 0
            var right = nums.size
            while (left < right) {
                val mid = left + (right - left) / 2
                if (nums[mid] <= target) {
                    left = mid + 1
                } else {
                    right = mid
                }
            }
            return left
        }

        val first = findFirst()
        val last = findLast() - 1

        if (first <= last && first < nums.size && nums[first] == target && nums[last] == target) {
            return intArrayOf(first, last)
        }

        return intArrayOf(-1, -1)
    }
}

fun main() {
    val obj = FindFirstLastElementSortedArray()
    val arr = intArrayOf(5, 7, 7, 8, 8, 10)
    println(obj.searchRange(arr, 8).contentToString())
    println(obj.searchRangeApproach2(arr, 8).contentToString())
}