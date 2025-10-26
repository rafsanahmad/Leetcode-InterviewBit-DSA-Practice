/*
 * *
 *  * Longest Consecutive Sequence.kt
 *  * Created by Rafsan Ahmad on 7/17/25, 4:00PM
 *  * Copyright (c) 2025 . All rights reserved.
 *  *
 *
 */

package kotlinclasses.HashTable

import java.lang.Integer.max
import java.util.TreeMap

class LongestConsecutiveSequence {
    //https://leetcode.com/problems/longest-consecutive-sequence/description/
    /*Given an unsorted array of integers nums, return the length of the longest consecutive elements sequence.

You must write an algorithm that runs in O(n) time.

Example 1:
Input: nums = [100,4,200,1,3,2]
Output: 4
Explanation: The longest consecutive elements sequence is [1, 2, 3, 4]. Therefore its length is 4.

Example 2:
Input: nums = [0,3,7,2,5,8,4,6,0,1]
Output: 9

Example 3:
Input: nums = [1,0,1,2]
Output: 3


Constraints:
0 <= nums.length <= 10^5
-10^9 <= nums[i] <= 10^9*/

    //Time complexity - O(nlogn)
    fun longestConsecutive(nums: IntArray): Int {
        if (nums.isEmpty()) return 0
        val map = TreeMap<Int, Int>()
        var result = 0

        for (i in nums.indices) {
            val num = nums[i]
            map[num] = map.getOrDefault(num, 0) + 1
        }

        for (entry in map.entries) {
            var num = entry.key
            var count = 1
            //map[num] = 0
            while ((map[num + 1] ?: 0) > 0) {
                map[num + 1]?.let {
                    count += 1
                }
                map[num + 1] = 0
                num++
            }

            result = max(count, result)
        }

        return result
    }

    //Time complexity - O(n)
    fun longestConsecutiveOptimized(nums: IntArray): Int {
        if (nums.isEmpty()) return 0
        var result = Integer.MIN_VALUE
        val hashSet: HashSet<Int> = hashSetOf()

        for (i in nums.indices)
            hashSet.add(nums[i])

        for (i in nums.indices) {
            var num = nums[i]
            if (!hashSet.contains(num)) continue
            var count = 1
            //We only want to start counting when num is the beginning of a sequence —
            //that is, when there’s no number immediately before it in the set.
            if (!hashSet.contains(num - 1)) {
                while (hashSet.contains(num + 1)) {
                    num++
                    count++
                    hashSet.remove(num)
                }
            }

            result = max(result, count)
        }

        return result
    }
}

fun main() {
    val obj = LongestConsecutiveSequence()
    val arr = intArrayOf(0, 3, 7, 2, 5, 8, 4, 6, 0, 1)
    println(obj.longestConsecutive(arr))
    println(obj.longestConsecutiveOptimized(arr))
}