/*
 * *
 *  * Search Insert Position.kt
 *  * Created by Rafsan Ahmad on 6/14/25, 7:36PM
 *  * Copyright (c) 2025 . All rights reserved.
 *  *
 *
 */

package kotlinclasses.BinarySearch

class SearchInsertPosition {
    //https://leetcode.com/problems/search-insert-position/description/
    /*Given a sorted array of distinct integers and a target value, return the index if the target
    is found. If not, return the index where it would be if it were inserted in order.

You must write an algorithm with O(log n) runtime complexity.

Example 1:
Input: nums = [1,3,5,6], target = 5
Output: 2

Example 2:
Input: nums = [1,3,5,6], target = 2
Output: 1

Example 3:
Input: nums = [1,3,5,6], target = 7
Output: 4

Constraints:
1 <= nums.length <= 10^4
-10^4 <= nums[i] <= 10^4
nums contains distinct values sorted in ascending order.
-10^4 <= target <= 10^4*/

    fun searchInsert(nums: IntArray, target: Int): Int {
        var l = 0
        var r = nums.size
        while (l < r) {
            val mid = l + (r - l) / 2
            if (nums[mid] == target) return mid
            else if (nums[mid] > target) {
                //Search Left half
                r = mid
            } else {
                //Search right half
                l = mid + 1
            }
        }

        return l //or r
    }
}

fun main() {
    val obj = SearchInsertPosition()
    println(obj.searchInsert(intArrayOf(1, 3, 5, 6), 0))
    println(obj.searchInsert(intArrayOf(1, 3, 5, 6), 2))
    println(obj.searchInsert(intArrayOf(1, 3, 5, 6), 7))
    println(obj.searchInsert(intArrayOf(1, 3, 5, 6, 7, 10, 11), 9))
}