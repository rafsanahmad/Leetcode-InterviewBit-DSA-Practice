/*
 * *
 *  * Minimum Number of Arrows to Burst Balloons.kt
 *  * Created by Rafsan Ahmad on 8/2/25, 1:42AM
 *  * Copyright (c) 2025 . All rights reserved.
 *  *
 *
 */

package kotlinclasses.Intervals

import java.util.Arrays

class MinimumNumberArrowsBurstBallons {
    //https://leetcode.com/problems/minimum-number-of-arrows-to-burst-balloons/description/
    /*There are some spherical balloons taped onto a flat wall that represents the XY-plane. The
    balloons are represented as a 2D integer array points where points[i] = [xstart, xend] denotes
    a balloon whose horizontal diameter stretches between xstart and xend. You do not know the exact
    y-coordinates of the balloons.

Arrows can be shot up directly vertically (in the positive y-direction) from different points along
the x-axis. A balloon with xstart and xend is burst by an arrow shot at x if xstart <= x <= xend.
There is no limit to the number of arrows that can be shot. A shot arrow keeps traveling up
infinitely, bursting any balloons in its path.

Given the array points, return the minimum number of arrows that must be shot to burst all balloons.

Example 1:
Input: points = [[10,16],[2,8],[1,6],[7,12]]
Output: 2
Explanation: The balloons can be burst by 2 arrows:
- Shoot an arrow at x = 6, bursting the balloons [2,8] and [1,6].
- Shoot an arrow at x = 11, bursting the balloons [10,16] and [7,12].

Example 2:
Input: points = [[1,2],[3,4],[5,6],[7,8]]
Output: 4
Explanation: One arrow needs to be shot for each balloon for a total of 4 arrows.

Example 3:
Input: points = [[1,2],[2,3],[3,4],[4,5]]
Output: 2
Explanation: The balloons can be burst by 2 arrows:
- Shoot an arrow at x = 2, bursting the balloons [1,2] and [2,3].
- Shoot an arrow at x = 4, bursting the balloons [3,4] and [4,5].


Constraints:
1 <= points.length <= 10^5
points[i].length == 2
-2^31 <= xstart < xend <= 2^31 - 1*/

    /*Time Complexity:
Sorting: O(n log n) for sorting the points array.
Loop: O(n) for one pass through the sorted list.
Total: O(n log n)*/
    fun findMinArrowShots(points: Array<IntArray>): Int {
        // If there are no balloons, no arrows are needed
        if (points.isEmpty()) return 0

        var count = 1  // At least one arrow is needed

        // Sort balloons by their start point
        Arrays.sort(points) { a, b -> a[0].compareTo(b[0]) }

        // Track the current minimum ending point of overlapping balloons
        var curMinEnd = points[0][1]

        // Iterate through all the balloons
        for (i in 1 until points.size) {
            if (points[i][0] > curMinEnd) {
                // No overlap with previous group, need a new arrow
                count++
                curMinEnd = points[i][1]
            } else {
                // Balloons overlap, narrow the overlapping window
                curMinEnd = minOf(curMinEnd, points[i][1])
            }
        }

        return count
    }
}

fun main() {
    val obj = MinimumNumberArrowsBurstBallons()
    val input1 = arrayOf(
        intArrayOf(10, 16),
        intArrayOf(2, 8),
        intArrayOf(1, 6),
        intArrayOf(7, 12)
    )
    println(obj.findMinArrowShots(input1))

    val input2 = arrayOf(
        intArrayOf(1, 2),
        intArrayOf(2, 3),
        intArrayOf(3, 4),
        intArrayOf(4, 5)
    )
    println(obj.findMinArrowShots(input2))
}