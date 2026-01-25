/*
 * *
 *  * Product of Array Except Self.kt
 *  * Created by Rafsan Ahmad on 7/11/25, 8:57PM
 *  * Copyright (c) 2025 . All rights reserved.
 *  *
 *
 */

package kotlinclasses.Array

class ProductOfArrayExceptSelf {
    //https://leetcode.com/problems/product-of-array-except-self/description/
    /*Given an integer array nums, return an array answer such that answer[i] is equal to the
    product of all the elements of nums except nums[i].

The product of any prefix or suffix of nums is guaranteed to fit in a 32-bit integer.
You must write an algorithm that runs in O(n) time and without using the division operation.

Example 1:
Input: nums = [1,2,3,4]
Output: [24,12,8,6]

Example 2:
Input: nums = [-1,1,0,-3,3]
Output: [0,0,9,0,0]


Constraints:
2 <= nums.length <= 10^5
-30 <= nums[i] <= 30
The input is generated such that answer[i] is guaranteed to fit in a 32-bit integer.

Follow up: Can you solve the problem in O(1) extra space complexity? (The output array does not
count as extra space for space complexity analysis.)*/

    //Input: [1,2,3,4]
    //prefixProduct = [1, 1, 2, 6]
    //suffixProduct = [24, 12, 4, 1]
    //result = [24, 12, 8, 6]
    fun productExceptSelf(nums: IntArray): IntArray {
        if (nums.isEmpty()) return intArrayOf()

        val len = nums.size
        val result = IntArray(len)
        val prefixProduct = IntArray(len)
        val suffixProduct = IntArray(len)

        // Step 1: Build prefix product array
        prefixProduct[0] = 1
        for (i in 1 until len) {
            prefixProduct[i] = prefixProduct[i - 1] * nums[i - 1]
        }

        // Step 2: Build suffix product array
        suffixProduct[len - 1] = 1
        for (i in len - 2 downTo 0) {
            suffixProduct[i] = suffixProduct[i + 1] * nums[i + 1]
        }

        // Step 3: Multiply prefix and suffix products for result
        for (i in 0 until len) {
            result[i] = prefixProduct[i] * suffixProduct[i]
        }

        return result
    }

    /*Optimized Solution
We can eliminate the need for both prefixProduct and suffixProduct arrays by:
Using the result array to first store prefix products.
Then iterate backwards to multiply with suffix products on the fly.
*/
    fun productExceptSelfSpaceOptimized(nums: IntArray): IntArray {
        val len = nums.size
        val result = IntArray(len)

        // Step 1: Compute prefix products in result
        result[0] = 1
        for (i in 1 until len) {
            result[i] = result[i - 1] * nums[i - 1]
        }

        // Step 2: Multiply with suffix products on the fly
        var suffix = 1
        for (i in len - 1 downTo 0) {
            result[i] *= suffix
            suffix *= nums[i]
        }

        return result
    }
}

fun main() {
    val obj = ProductOfArrayExceptSelf()
    val arr = intArrayOf(1, 2, 3, 4)
    println(obj.productExceptSelf(arr).contentToString())
    println(obj.productExceptSelfSpaceOptimized(arr).contentToString())
}