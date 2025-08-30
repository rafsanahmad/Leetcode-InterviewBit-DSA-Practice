/*
 * *
 *  * Two Sum II - Input Array Is Sorted.kt
 *  * Created by Rafsan Ahmad on 6/26/25, 12:32AM
 *  * Copyright (c) 2025 . All rights reserved.
 *  *
 *
 */

package kotlinclasses.TwoPointer_SlidingWindow

class TwoSum_II {
    //https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/description/
    /*Given a 1-indexed array of integers numbers that is already sorted in non-decreasing order,
    find two numbers such that they add up to a specific target number. Let these two numbers be
    numbers[index1] and numbers[index2] where 1 <= index1 < index2 <= numbers.length.

Return the indices of the two numbers, index1 and index2, added by one as an integer array
[index1, index2] of length 2.

The tests are generated such that there is exactly one solution. You may not use the same element
twice.

Your solution must use only constant extra space.

Example 1:
Input: numbers = [2,7,11,15], target = 9
Output: [1,2]
Explanation: The sum of 2 and 7 is 9. Therefore, index1 = 1, index2 = 2. We return [1, 2].

Example 2:
Input: numbers = [2,3,4], target = 6
Output: [1,3]
Explanation: The sum of 2 and 4 is 6. Therefore index1 = 1, index2 = 3. We return [1, 3].

Example 3:
Input: numbers = [-1,0], target = -1
Output: [1,2]
Explanation: The sum of -1 and 0 is -1. Therefore index1 = 1, index2 = 2. We return [1, 2].


Constraints:
2 <= numbers.length <= 3 * 10^4
-1000 <= numbers[i] <= 1000
numbers is sorted in non-decreasing order.
-1000 <= target <= 1000
The tests are generated such that there is exactly one solution.*/

    fun twoSum(numbers: IntArray, target: Int): IntArray {
        var resultArr: IntArray = IntArray(2)
        var left = 0
        var right = numbers.size - 1

        while (left <= right) {
            val sum = numbers[left] + numbers[right]
            if (sum == target) {
                resultArr = intArrayOf(left + 1, right + 1)
                break
            } else if (sum < target) {
                left++
            } else {
                right--
            }
        }

        return resultArr
    }
}

fun main() {
    val obj = TwoSum_II()
    println(obj.twoSum(intArrayOf(2, 7, 11, 15), 9).contentToString())
    println(obj.twoSum(intArrayOf(-1, 0), -1).contentToString())
}