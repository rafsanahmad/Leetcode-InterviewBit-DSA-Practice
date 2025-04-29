/*
 * *
 *  * Count Special Subsequence.kt
 *  * Created by Rafsan Ahmad on 4/30/25, 1:11AM
 *  * Copyright (c) 2025 . All rights reserved.
 *  *
 *
 */

package kotlinclasses.HashTable

class CountSpecialSubsequence {
    /*You are given an array nums consisting of positive integers.

A special subsequence is defined as a subsequence of length 4, represented by indices (p, q, r, s),
where p < q < r < s. This subsequence must satisfy the following conditions:

nums[p] * nums[r] == nums[q] * nums[s]
There must be at least one element between each pair of indices. In other words, q - p > 1, r - q > 1
 and s - r > 1.
Return the number of different special subsequences in nums.



Example 1:
Input: nums = [1,2,3,4,3,6,1]
Output: 1

Explanation:
There is one special subsequence in nums.

(p, q, r, s) = (0, 2, 4, 6):
This corresponds to elements (1, 3, 3, 1).
nums[p] * nums[r] = nums[0] * nums[4] = 1 * 3 = 3
nums[q] * nums[s] = nums[2] * nums[6] = 3 * 1 = 3
Example 2:

Input: nums = [3,4,3,4,3,4,3,4]
Output: 3

Explanation:
There are three special subsequences in nums.

(p, q, r, s) = (0, 2, 4, 6):
This corresponds to elements (3, 3, 3, 3).
nums[p] * nums[r] = nums[0] * nums[4] = 3 * 3 = 9
nums[q] * nums[s] = nums[2] * nums[6] = 3 * 3 = 9
(p, q, r, s) = (1, 3, 5, 7):
This corresponds to elements (4, 4, 4, 4).
nums[p] * nums[r] = nums[1] * nums[5] = 4 * 4 = 16
nums[q] * nums[s] = nums[3] * nums[7] = 4 * 4 = 16
(p, q, r, s) = (0, 2, 5, 7):
This corresponds to elements (3, 3, 4, 4).
nums[p] * nums[r] = nums[0] * nums[5] = 3 * 4 = 12
nums[q] * nums[s] = nums[2] * nums[7] = 3 * 4 = 12


Constraints:

7 <= nums.length <= 1000
1 <= nums[i] <= 1000*/

    /*Intuition
From A[p] * A[r] == A[q] * A[s]
we can have A[p] / A[q] == A[s] / A[r]

Explanation
cnt will count the frequent of A[p] / A[q].

Enumerate indice r,
update the cnt first,
for each s from r + 2 to n - 1,
res += cnt[A[s] / A[r]].

Return res in the end

Complexity
Time O(n^2)
*/
    fun numberOfSubsequences(nums: IntArray): Long {
        val freq: MutableMap<Double, Int> = HashMap<Double, Int>()
        var ans: Long = 0
        for (r in 4..<nums.size) {
            val q = r - 2
            for (p in 0 until q - 1) {
                val key: Double = 1.0 * nums[p] / nums[q]
                freq.put(key, freq.getOrDefault(key, 0) + 1)
            }

            for (s in r + 2..<nums.size) {
                val key = 1.0 * nums[s] / nums[r]
                ans += freq.getOrDefault(key, 0)
            }
        }

        return ans
    }
}

fun main() {
    val specialSubsequence = CountSpecialSubsequence()
    println(specialSubsequence.numberOfSubsequences(intArrayOf(3, 4, 3, 4, 3, 4, 3, 4)))
}