/*
 * *
 *  * Created by Rafsan Ahmad on 10/25/21, 9:50 PM
 *  * Copyright (c) 2021 . All rights reserved.
 *
 */

package kotlinclasses.Problems

import java.util.*

class MergeInterval {

    /* Given an array of intervals where intervals[i] = [starti, endi],
     merge all overlapping intervals, and return an array of the non-overlapping intervals
     that cover all the intervals in the input.

     Example 1:

     Input: intervals = [[1,3],[2,6],[8,10],[15,18]]
     Output: [[1,6],[8,10],[15,18]]
     Explanation: Since intervals [1,3] and [2,6] overlaps, merge them into [1,6].*/

    //Merge Overlapping inerval - (LeetCode - 56)
    fun merge(intervals: Array<IntArray>): Array<IntArray>? {
        Arrays.asList(*intervals).sortWith(Comparator { a, b -> Integer.compare(a[0], b[0]) })
        val merged = ArrayList<IntArray>()
        var count = 0
        for (interval in intervals) {
            if (merged.isEmpty() || merged[count - 1][1] < interval[0]) {
                //if the list of merged intervals is empty or if the current interval
                //does not overlap with previous interval - append it
                merged.add(interval)
                ++count
            } else {
                //there is a overlap, so merge the current and previous interval
                merged[count - 1][1] =
                    Math.max(merged[count - 1][1], interval[1])
            }
        }
        return merged.toTypedArray()
    }
}