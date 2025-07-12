/*
 * *
 *  * Search in Rotated Sorted Array.kt
 *  * Created by Rafsan Ahmad on 7/11/25, 2:02AM
 *  * Copyright (c) 2025 . All rights reserved.
 *  *
 *
 */

package kotlinclasses.BinarySearch

class SearchRotatedSortedArray {
    //https://leetcode.com/problems/search-in-rotated-sorted-array/description/
    /*There is an integer array nums sorted in ascending order (with distinct values).

Prior to being passed to your function, nums is possibly rotated at an unknown pivot index k
(1 <= k < nums.length) such that the resulting array is
[nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]] (0-indexed).
For example, [0,1,2,4,5,6,7] might be rotated at pivot index 3 and become [4,5,6,7,0,1,2].

Given the array nums after the possible rotation and an integer target, return the index of
target if it is in nums, or -1 if it is not in nums.

You must write an algorithm with O(log n) runtime complexity.


Example 1:
Input: nums = [4,5,6,7,0,1,2], target = 0
Output: 4

Example 2:
Input: nums = [4,5,6,7,0,1,2], target = 3
Output: -1

Example 3:
Input: nums = [1], target = 0
Output: -1


Constraints:
1 <= nums.length <= 5000
-10^4 <= nums[i] <= 10^4
All values of nums are unique.
nums is an ascending array that is possibly rotated.
-10^4 <= target <= 10^4*/

    //Time complexity: O(log n)
    fun search(nums: IntArray, target: Int): Int {
        if (nums.isEmpty()) return -1

        var left = 0
        var right = nums.lastIndex

        // Step 1: Find the rotation point using binary search
        while (left < right) {
            val mid = (left + right) / 2
            if (nums[mid] > nums[right]) {
                left = mid + 1
            } else {
                right = mid
            }
        }
        val rotation = left

        // Step 2: Standard binary search with adjusted indices
        left = 0
        right = nums.lastIndex

        while (left <= right) {
            val mid = (left + right) / 2
            //wrapping mid index
            val realMid = (mid + rotation) % nums.size

            when {
                nums[realMid] == target -> return realMid
                nums[realMid] < target -> left = mid + 1
                else -> right = mid - 1
            }
        }

        return -1
    }


    //Total Time Complexity = O(n) + O(log n) = O(n)
    //But in Leetcode submission this beats 100%
    fun searchApproach2(nums: IntArray, target: Int): Int {
        if (nums.isEmpty()) return -1
        var rotatePoint = 0

        val prev = nums[0]
        for (i in 1 until nums.size) {
            if (prev > nums[i]) {
                rotatePoint = i - 1
                break
            }
        }

        var left = 0
        var right = 0
        if (target >= nums[0] && target <= nums[rotatePoint]) {
            left = 0
            right = rotatePoint + 1
        } else {
            left = rotatePoint + 1
            right = nums.size
        }

        while (left < right) {
            var mid = (right + left) / 2
            if (nums[mid] == target)
                return mid
            else if (nums[mid] < target) {
                left = mid + 1
            } else {
                right = mid
            }
        }

        return -1
    }
}

fun main() {
    val obj = SearchRotatedSortedArray()
    val arr1 = intArrayOf(4, 5, 6, 7, 0, 1, 2)
    println(obj.search(arr1, 0))
    println(obj.search(arr1, 3))
    println(obj.searchApproach2(arr1, 0))
    println(obj.searchApproach2(arr1, 3))
}