/*
 * *
 *  * Line Sweep.kt
 *  * Created by Rafsan Ahmad on 4/15/25, 2:49PM
 *  * Copyright (c) 2025 . All rights reserved.
 *  *
 *
 */

package kotlinclasses.Algorithm


class LineSweep {
    //https://leetcode.com/discuss/post/2166045/line-sweep-algorithms-by-c0d3m-8ebq/
    /*Line Sweep Algorithms
Line Sweep (or Sweep Line) is an algorithmic technique where we sweep an imaginary line (x or y
axis) and solve various problem.
There would be an event (entry or event) and based on that we update the information and then
return result.*/

    /*
    You are given an integer array nums of length n and a 2D array queries, where queries[i] =
    [li, ri].

    For each queries[i]:

    Select a subset of indices within the range [li, ri] in nums.
    Decrement the values at the selected indices by 1.
    A Zero Array is an array where all elements are equal to 0.

    Return true if it is possible to transform nums into a Zero Array after processing all the queries
     sequentially, otherwise return false.


    Example 1:
    Input: nums = [1,0,1], queries = [[0,2]]
    Output: true

    Explanation:
    For i = 0:
    Select the subset of indices as [0, 2] and decrement the values at these indices by 1.
    The array will become [0, 0, 0], which is a Zero Array.

    Example 2:
    Input: nums = [4,3,2,1], queries = [[1,3],[0,2]]
    Output: false

    Explanation:
    For i = 0:
    Select the subset of indices as [1, 2, 3] and decrement the values at these indices by 1.
    The array will become [4, 2, 1, 0].
    For i = 1:
    Select the subset of indices as [0, 1, 2] and decrement the values at these indices by 1.
    The array will become [3, 1, 0, 0], which is not a Zero Array.


    Constraints:
    1 <= nums.length <= 10^5
    0 <= nums[i] <= 10^5
    1 <= queries.length <= 10^5
    queries[i].length == 2
    0 <= li <= ri < nums.length
    */

    /*Simplified idea:

We leverage the idea of range updates with the prefix sum technique. Instead of directly modifying
 large segments of the nums array multiple times, we record changes at the boundaries and later
 apply those changes cumulatively.

Building the solution:

Marking Events: For each query [i, j], we mark the start of the increment at index i (freq[i]++),
 and we mark the end of the increment at index j + 1 (freq[j + 1]--).
This is similar to marking "events" in the line sweep algorithm, where the effect starts at i and
 ends after j.

Sweeping Over the Line (or Array): Once all the queries are processed, we iterate through the
array and compute the cumulative effect (curFreq) from the frequency array freq.
At each step, we accumulate the frequency from previous operations and check if the result meets
 the condition (i.e., whether curFreq >= nums[i]).

 Index:         0     1     2
---------------------------------
freq:          1     1    -1
prefix sum:    1     2     1
nums:          2     2     1
check:         ❌    ✅    ✅
 */

    fun isZeroArray(nums: IntArray, queries: Array<IntArray>): Boolean {
        val n = nums.size
        val freq = IntArray(n)

        for (q in queries) {
            freq[q[0]]++
            if (q[1] + 1 < n) {
                freq[q[1] + 1]--
            }
        }

        var curFreq = 0
        for (i in 0 until n) {
            curFreq += freq[i]
            if (curFreq < nums[i]) {
                return false
            }
        }
        return true
    }
}

fun main(args: Array<String>) {
    val lineSweep = LineSweep()
    val arr = intArrayOf(2, 2, 1)
    var queries: Array<IntArray> = arrayOf(
        intArrayOf(0, 1),
        intArrayOf(1, 2)
    )
    println(lineSweep.isZeroArray(arr, queries))
}