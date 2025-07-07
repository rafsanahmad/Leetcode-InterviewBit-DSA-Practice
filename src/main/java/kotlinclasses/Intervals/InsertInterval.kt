/*
 * *
 *  * Insert Interval.kt
 *  * Created by Rafsan Ahmad on 7/6/25, 4:56PM
 *  * Copyright (c) 2025 . All rights reserved.
 *  *
 *
 */

package kotlinclasses.Intervals

class InsertInterval {
    //https://leetcode.com/problems/insert-interval/description/
    /*You are given an array of non-overlapping intervals intervals where
    intervals[i] = [starti, endi] represent the start and the end of the ith interval and
    intervals is sorted in ascending order by starti. You are also given an interval
    newInterval = [start, end] that represents the start and end of another interval.

Insert newInterval into intervals such that intervals is still sorted in ascending order by
starti and intervals still does not have any overlapping intervals (merge overlapping
intervals if necessary).

Return intervals after the insertion.

Note that you don't need to modify intervals in-place. You can make a new array and return
it.


Example 1:
Input: intervals = [[1,3],[6,9]], newInterval = [2,5]
Output: [[1,5],[6,9]]

Example 2:
Input: intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
Output: [[1,2],[3,10],[12,16]]
Explanation: Because the new interval [4,8] overlaps with [3,5],[6,7],[8,10].


Constraints:
0 <= intervals.length <= 10^4
intervals[i].length == 2
0 <= starti <= endi <= 10^5
intervals is sorted by starti in ascending order.
newInterval.length == 2
0 <= start <= end <= 10^5*/

    fun insert(intervals: Array<IntArray>, newInterval: IntArray): Array<IntArray> {
        if (intervals.isEmpty()) return arrayOf(newInterval)
        val list: MutableList<IntArray> = mutableListOf()
        val len = intervals.size

        var i = 0
        // Add all intervals before newInterval
        while (i < len && newInterval[0] > intervals[i][1]) {
            list.add(intervals[i])
            i++
        }

        // Merge overlapping intervals
        var start = newInterval[0]
        var end = newInterval[1]
        while (i < len && intervals[i][0] <= end && intervals[i][1] >= start) {
            start = minOf(start, intervals[i][0])
            end = maxOf(end, intervals[i][1])
            i++
        }
        list.add(intArrayOf(start, end))

        // Add all intervals after newInterval
        while (i < len) {
            list.add(intervals[i])
            i++
        }

        val arr = Array(list.size) { IntArray(2) }
        for (i in list.indices) {
            arr[i] = list[i]
        }

        return arr
    }
}

fun main() {
    val obj = InsertInterval()
    val arr1 = arrayOf(
        intArrayOf(1, 3),
        intArrayOf(6, 9)
    )
    println(obj.insert(arr1, intArrayOf(2, 5)).contentDeepToString())

    val arr2 = arrayOf(
        intArrayOf(1, 2),
        intArrayOf(3, 5),
        intArrayOf(6, 7),
        intArrayOf(8, 10),
        intArrayOf(12, 16)
    )
    println(obj.insert(arr2, intArrayOf(4, 8)).contentDeepToString())
}