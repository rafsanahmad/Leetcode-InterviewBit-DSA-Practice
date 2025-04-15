/*
 * *
 *  * Largest Outlier In Array.kt
 *  * Created by Rafsan Ahmad on 4/14/25, 4:37PM
 *  * Copyright (c) 2025 . All rights reserved.
 *  *
 *
 */

package kotlinclasses.Hashing

class LargestOutlierInArray {
    //https://leetcode.com/problems/identify-the-largest-outlier-in-an-array/description/
    /*You are given an integer array nums. This array contains n elements, where exactly n - 2
    elements are special numbers. One of the remaining two elements is the sum of these special
    numbers, and the other is an outlier.

An outlier is defined as a number that is neither one of the original special numbers nor the
element representing the sum of those numbers.

Note that special numbers, the sum element, and the outlier must have distinct indices, but may
share the same value.

Return the largest potential outlier in nums.


Example 1:
Input: nums = [2,3,5,10]
Output: 10

Explanation:
The special numbers could be 2 and 3, thus making their sum 5 and the outlier 10.

Example 2:
Input: nums = [-2,-1,-3,-6,4]
Output: 4

Explanation:
The special numbers could be -2, -1, and -3, thus making their sum -6 and the outlier 4.

Example 3:
Input: nums = [1,1,1,1,1,5,5]
Output: 5

Explanation:
The special numbers could be 1, 1, 1, 1, and 1, thus making their sum 5 and the other 5 as the
outlier.



Constraints:

3 <= nums.length <= 10^5
-1000 <= nums[i] <= 1000
The input is generated such that at least one potential outlier exists in nums.*/

    /*To solve this problem, we need to find the largest potential outlier in an array where:

( n - 2 ) elements are special numbers.
One element is the sum of the special numbers.
One element is the outlier, which is distinct from both the sum element and the special numbers.
The problem hints that we can leverage the relationship between the sum of all elements and the
sum of special numbers. Specifically, the sum of all elements in the array is related to the sum
 of the special numbers, their sum, and the outlier. This relationship can be described by the
 equation:

total_sum=2×(sum of special numbers)+(outlier)

The task is to find the largest outlier, given that there is one candidate that is the sum of the
 special numbers and the rest are special numbers.
 */
    fun getLargestOutlier(nums: IntArray): Int {
        var countMap: HashMap<Int, Int> = hashMapOf()
        var total = 0
        var result = Integer.MIN_VALUE

        for (i in 0..<nums.size) {
            total += nums[i]
            countMap[nums[i]] = countMap.getOrDefault(nums[i], 0) + 1
        }

        for (i in 0..<nums.size) {
            val potentialOutlier = total - 2 * nums[i]

            countMap[potentialOutlier]?.let {
                val count = countMap.getOrDefault(potentialOutlier, 0)
                if (potentialOutlier != nums[i] || count > 1) {
                    result = Math.max(result, potentialOutlier)
                }
            }
        }

        return result
    }
}

fun main(args: Array<String>) {
    val outlier = LargestOutlierInArray()
    println(outlier.getLargestOutlier(intArrayOf(-2, -1, -3, -6, 4)))
}