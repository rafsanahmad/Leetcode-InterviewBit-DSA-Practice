/*
 * *
 *  * MergeInterval.kt
 *  * Created by Rafsan Ahmad on 7/6/25, 5:05PM
 *  * Copyright (c) 2025 . All rights reserved.
 *  *
 *
 */

package kotlinclasses.Intervals

import java.util.*
import kotlin.math.max

class MergeInterval {
    //https://leetcode.com/problems/merge-intervals/description/
    /*Given an array of intervals where intervals[i] = [starti, endi], merge all overlapping
    intervals, and return an array of the non-overlapping intervals that cover all the
    intervals in the input.

Example 1:
Input: intervals = [[1,3],[2,6],[8,10],[15,18]]
Output: [[1,6],[8,10],[15,18]]
Explanation: Since intervals [1,3] and [2,6] overlap, merge them into [1,6].

Example 2:
Input: intervals = [[1,4],[4,5]]
Output: [[1,5]]
Explanation: Intervals [1,4] and [4,5] are considered overlapping.


Constraints:
1 <= intervals.length <= 10^4
intervals[i].length == 2
0 <= starti <= endi <= 10^4

*/
    fun merge(intervals: Array<IntArray>): Array<IntArray> {
        var list: MutableList<IntArray> = arrayListOf()

        Arrays.sort(intervals) { a, b -> a[0] - b[0] }

        for (i in 0..<intervals.size) {
            val curInterval = intervals[i]

            if (list.isEmpty()) {
                list.add(curInterval)
            } else if (curInterval[0] <= list.get(list.size - 1)[1]) {
                //conflict occurs update end
                val end = max(curInterval[1], list.get(list.size - 1)[1])
                list.get(list.size - 1)[1] = end
            } else {
                list.add(curInterval)
            }
        }

        return list.toTypedArray()
    }
}

fun main(args: Array<String>) {
    val mergeInterval = MergeInterval()
    var intervals = arrayOf(
        intArrayOf(1, 3),
        intArrayOf(8, 10),
        intArrayOf(2, 6),
        intArrayOf(15, 18)
    )
    println(mergeInterval.merge(intervals).contentDeepToString())
}