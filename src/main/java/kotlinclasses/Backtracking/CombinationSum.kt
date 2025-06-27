/*
 * *
 *  * Combination Sum.kt
 *  * Created by Rafsan Ahmad on 6/27/25, 11:22PM
 *  * Copyright (c) 2025 . All rights reserved.
 *  *
 *
 */

package kotlinclasses.Backtracking

class CombinationSum {
    //https://leetcode.com/problems/combination-sum/
    /*Given an array of distinct integers candidates and a target integer target, return a
     list of all unique combinations of candidates where the chosen numbers sum to target.
     You may return the combinations in any order.

The same number may be chosen from candidates an unlimited number of times. Two
combinations are unique if the frequency of at least one of the chosen numbers is different.

The test cases are generated such that the number of unique combinations that sum up to
target is less than 150 combinations for the given input.


Example 1:
Input: candidates = [2,3,6,7], target = 7
Output: [[2,2,3],[7]]
Explanation:
2 and 3 are candidates, and 2 + 2 + 3 = 7. Note that 2 can be used multiple times.
7 is a candidate, and 7 = 7.
These are the only two combinations.

Example 2:
Input: candidates = [2,3,5], target = 8
Output: [[2,2,2,2],[2,3,3],[3,5]]

Example 3:
Input: candidates = [2], target = 1
Output: []


Constraints:
1 <= candidates.length <= 30
2 <= candidates[i] <= 40
All elements of candidates are distinct.
1 <= target <= 40*/

    /*
    Time Complexity:
🔸 O(2^t) — Exponential in the worst case due to branching and backtracking.
where t = target

At each recursive call, we have a choice:
Either include the current number (and recurse again with the same index),
Or try the next number (by advancing the index).

Space Complexity:
🔸 O(t) auxiliary (call stack + temp list)
🔸 O(k × m) for final result storage

    */
    fun combinationSum(candidates: IntArray, target: Int): List<List<Int>> {
        val result: MutableList<List<Int>> = mutableListOf()
        val list = mutableListOf<Int>()
        combinationSumHelper(candidates, target, 0, 0, list, result)
        return result
    }

    fun combinationSumHelper(
        nums: IntArray,
        target: Int,
        index: Int,
        sum: Int,
        list: MutableList<Int>,
        result: MutableList<List<Int>>
    ) {
        if (sum > target) return
        if (sum == target) {
            result.add(ArrayList(list))
            return
        }

        for (i in index until nums.size) {
            list.add(nums[i])
            combinationSumHelper(nums, target, i, sum + nums[i], list, result)
            list.removeAt(list.size - 1)
        }
    }
}

fun main() {
    val obj = CombinationSum()
    println(obj.combinationSum(intArrayOf(2, 3, 5), 8))
}