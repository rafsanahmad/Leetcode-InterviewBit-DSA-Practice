/*
 * *
 *  * Permutations.kt
 *  * Created by Rafsan Ahmad on 6/27/25, 9:51PM
 *  * Copyright (c) 2025 . All rights reserved.
 *  *
 *
 */

package kotlinclasses.Backtracking

class Permutations {
    //https://leetcode.com/problems/permutations/description/
    /*Given an array nums of distinct integers, return all the possible permutations.
    You can return the answer in any order.

Example 1:
Input: nums = [1,2,3]
Output: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]

Example 2:
Input: nums = [0,1]
Output: [[0,1],[1,0]]

Example 3:
Input: nums = [1]
Output: [[1]]


Constraints:
1 <= nums.length <= 6
-10 <= nums[i] <= 10
All the integers of nums are unique.*/

    /*Time Complexity:
🔸 O(n × n!)
Space Complexity:
🔸 O(n × n!) for the output
🔸 O(n) auxiliary for recursion + temp structures

Reason:
There are n! permutations of a list with n unique elements.
For example, for [1,2,3]: total permutations = 3! = 6
For each permutation, you copy the current list (list) into the result:

result.add(ArrayList(list)) → takes O(n) time.

So total work =
Number of permutations × cost of copying one permutation
→ n! × n → O(n × n!)

*/
    fun permute(nums: IntArray): List<List<Int>> {
        val result = mutableListOf<List<Int>>()
        val list = mutableListOf<Int>()
        val used = BooleanArray(nums.size)
        permuteHelper(nums, used, list, result)
        return result
    }

    fun permuteHelper(
        nums: IntArray,
        used: BooleanArray,
        list: MutableList<Int>,
        result: MutableList<List<Int>>
    ) {
        if (list.size == nums.size) {
            result.add(ArrayList(list))
            return
        }

        for (i in nums.indices) {
            if (used[i]) continue
            used[i] = true
            list.add(nums[i])
            permuteHelper(nums, used, list, result)
            used[i] = false
            list.removeAt(list.size - 1)
        }
    }
}

fun main() {
    val obj = Permutations()
    println(obj.permute(intArrayOf(1, 2, 3)))
}