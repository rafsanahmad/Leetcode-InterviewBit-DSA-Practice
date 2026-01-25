/*
 *
 *  * NonOverlappingInterval.kt
 *  *
 *  * Created by Rafsan Ahmad on 01/19/26, 12:41 AM
 *  * Copyright (c) 2026. All rights reserved.
 *
 */

package kotlinclasses.Intervals

import java.util.Arrays
import kotlin.math.min

class NonOverlappingInterval {
    /*Given an array of intervals intervals where intervals[i] = [starti, endi], return the
    minimum number of intervals you need to remove to make the rest of the intervals
    non-overlapping.

Note that intervals which only touch at a point are non-overlapping. For example, [1, 2] and
[2, 3] are non-overlapping.

Example 1:
Input: intervals = [[1,2],[2,3],[3,4],[1,3]]
Output: 1
Explanation: [1,3] can be removed and the rest of the intervals are non-overlapping.

Example 2:
Input: intervals = [[1,2],[1,2],[1,2]]
Output: 2
Explanation: You need to remove two [1,2] to make the rest of the intervals non-overlapping.

Example 3:
Input: intervals = [[1,2],[2,3]]
Output: 0
Explanation: You don't need to remove any of the intervals since they're already non-overlapping.


Constraints:
1 <= intervals.length <= 10^5
intervals[i].length == 2
-5 * 10^4 <= starti < endi <= 5 * 10^4*/

    fun eraseOverlapIntervals(intervals: Array<IntArray>): Int {
        if (intervals.isEmpty()) return 0

        var count = 0

        //sort by start time
        Arrays.sort(intervals) { a, b -> a[0] - b[0] }

        var curEnd = intervals[0][1]
        for (i in 1 until intervals.size) {
            val curInterval = intervals[i]
            if (curInterval[0] < curEnd) {
                //overlaps occurs - remove interval with max end
                curEnd = min(curEnd, curInterval[1])
                count++
            } else {
                curEnd = curInterval[1]
            }
        }

        return count
    }
}

fun main() {
    val obj = NonOverlappingInterval()
    // Test Case 1
    val intervals1 = arrayOf(
        intArrayOf(1, 2),
        intArrayOf(2, 3),
        intArrayOf(3, 4),
        intArrayOf(1, 3)
    )
    println(obj.eraseOverlapIntervals(intervals1)) // Expected: 1

    // Test Case 2
    val intervals2 = arrayOf(
        intArrayOf(1, 2),
        intArrayOf(1, 2),
        intArrayOf(1, 2)
    )
    println(obj.eraseOverlapIntervals(intervals2)) // Expected: 2
}