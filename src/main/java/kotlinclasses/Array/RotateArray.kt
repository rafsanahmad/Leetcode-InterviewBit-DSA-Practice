/*
 * *
 *  * Rotate Array.kt
 *  * Created by Rafsan Ahmad on 5/20/25, 5:05PM
 *  * Copyright (c) 2025 . All rights reserved.
 *  *
 *
 */

package kotlinclasses.Array

class RotateArray {
    //https://leetcode.com/problems/rotate-array/
    /*Given an integer array nums, rotate the array to the right by k steps, where k is non-
    negative.

Example 1:

Input: nums = [1,2,3,4,5,6,7], k = 3
Output: [5,6,7,1,2,3,4]
Explanation:
rotate 1 steps to the right: [7,1,2,3,4,5,6]
rotate 2 steps to the right: [6,7,1,2,3,4,5]
rotate 3 steps to the right: [5,6,7,1,2,3,4]

Example 2:
Input: nums = [-1,-100,3,99], k = 2
Output: [3,99,-1,-100]
Explanation:
rotate 1 steps to the right: [99,-1,-100,3]
rotate 2 steps to the right: [3,99,-1,-100]


Constraints:
1 <= nums.length <= 10^5
-2^31 <= nums[i] <= 2^31 - 1
0 <= k <= 10^5


Follow up:

Try to come up with as many solutions as you can. There are at least three different ways to
solve this problem.
Could you do it in-place with O(1) extra space?*/

    /*Approach 1: Using a temp array
For Rotating the Elements to right:
Step 1: Copy the first len - k elements into the temp array.
Step 2: Shift len-k elements from the beginning by k position to the right
Step 3: Copy the elements into the main array from the temp array.
Time Complexity: O(n)
Space Complexity: O(k) since k array element needs to be stored in temp array
*/
    fun rotate(nums: IntArray, k: Int): Unit {
        val len = nums.size
        if (len <= 1) return

        val kMod = k % len
        val s = len - kMod
        var tempArr = IntArray(s)
        //populate temp array
        for (i in 0..<s) {
            tempArr[i] = nums[i]
        }

        var j = 0
        //Shift array element to beginning
        for (i in s..<len) {
            nums[j++] = nums[i]
        }

        //Add element from temp array at end
        for (i in 0..<tempArr.size) {
            nums[i + kMod] = tempArr[i]
        }
    }

    /*Approach 2: Space optimized, Using ” Reversal Algorithm “
For Rotating Elements to right
Step 1: Reverse the last k elements of the array
Step 2: Reverse the first n-k elements of the array.
Step 3: Reverse the whole array.

Time Complexity - O(N) where N is the number of elements in an array
Space Complexity - O(1) since no extra space is required
*/

    fun reverse(arr: IntArray, start: Int, end: Int) {
        var start = start
        var end = end
        while (start <= end) {
            val temp = arr[start]
            arr[start] = arr[end]
            arr[end] = temp
            start++
            end--
        }
    }

    // Function to Rotate k elements to right
    fun rotateApproach2(arr: IntArray, k: Int) {
        val n = arr.size
        val kMod = k % n
        // Reverse first n-k elements
        reverse(arr, 0, n - kMod - 1)
        // Reverse last k elements
        reverse(arr, n - kMod, n - 1)
        // Reverse whole array
        reverse(arr, 0, n - 1)
    }
}

fun main(args: Array<String>) {
    val rotateArray = RotateArray()
    val l1 = intArrayOf(1, 2, 3, 4, 5, 6, 7)
    rotateArray.rotate(l1, 3)
    println(l1.contentToString())

    val l2 = intArrayOf(1, 2, 3, 4, 5, 6, 7)
    rotateArray.rotateApproach2(l2, 3)
    println(l2.contentToString())

    val l3 = intArrayOf(-1, -100, 3, 99)
    rotateArray.rotate(l3, 2)
    println(l3.contentToString())

    val l4 = intArrayOf(-1, -100, 3, 99)
    rotateArray.rotateApproach2(l4, 2)
    println(l4.contentToString())
}