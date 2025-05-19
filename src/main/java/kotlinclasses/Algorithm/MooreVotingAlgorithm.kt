/*
 * *
 *  * Moore's Voting Algorithm.kt
 *  * Created by Rafsan Ahmad on 5/19/25, 6:52PM
 *  * Copyright (c) 2025 . All rights reserved.
 *  *
 *
 */

package kotlinclasses.Algorithm

class MooreVotingAlgorithm {
    /*Given an array nums of size n, return the majority element.

The majority element is the element that appears more than ⌊n / 2⌋ times. You may assume that
the majority element always exists in the array.



Example 1:

Input: nums = [3,2,3]
Output: 3
Example 2:

Input: nums = [2,2,1,1,1,2,2]
Output: 2


Constraints:

n == nums.length
1 <= n <= 5 * 10^4
-10^9 <= nums[i] <= 10^9


Follow-up: Could you solve the problem in linear time and in O(1) space?*/

    /*Intuition:
The intuition behind the Moore's Voting Algorithm is based on the fact that if there is a
majority element in an array, it will always remain in the lead, even after encountering other
elements.

Explanation:
Algorithm:

Initialize two variables: count and candidate. Set count to 0 and candidate to an arbitrary value.
Iterate through the array nums:
a. If count is 0, assign the current element as the new candidate and increment count by 1.
b. If the current element is the same as the candidate, increment count by 1.
c. If the current element is different from the candidate, decrement count by 1.
After the iteration, the candidate variable will hold the majority element.*/

    fun majorityElement(nums: IntArray): Int {
        var element = -1
        var count = 0

        for (i in nums.indices) {
            if (count == 0)
                element = nums[i]

            if (nums[i] == element) {
                count++
            } else {
                count--
            }
        }

        return element
    }
}

fun main(args: Array<String>) {
    val votingAlgorithm = MooreVotingAlgorithm()
    println(votingAlgorithm.majorityElement(intArrayOf(2, 2, 1, 1, 1, 2, 2)))
}