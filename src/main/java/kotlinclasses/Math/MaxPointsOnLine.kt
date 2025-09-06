/*
 * *
 *  * Max Points on a Line.kt
 *  * Created by Rafsan Ahmad on 9/6/25, 2:18PM
 *  * Copyright (c) 2025 . All rights reserved.
 *  *
 *
 */

package kotlinclasses.Math

import kotlin.math.abs

class MaxPointsOnLine {
    //https://leetcode.com/problems/max-points-on-a-line/description/
    /*Given an array of points where points[i] = [xi, yi] represents a point on the X-Y plane,
    return the maximum number of points that lie on the same straight line.

Example 1:
Input: points = [[1,1],[2,2],[3,3]]
Output: 3


Example 2:
y
5 |
4 |  o
3 |        o                 o
2 |              o
1 |  o                 o
0 +----------------------------
    0  1  2  3  4  5   x

Input: points = [[1,1],[3,2],[5,3],[4,1],[2,3],[1,4]]
Output: 4


Constraints:
1 <= points.length <= 300
points[i].length == 2
-104 <= xi, yi <= 104
All the points are unique.*/

    /*Time Complexity:
Outer loop: O(n) for each base point
Inner loop: O(n) for each comparison → slope calculation
Slope normalization (gcd) is O(log(max(dx, dy)))
Total: O(n^2 * logM) where M = max(|xi - xj|, |yi - yj|)

Space Complexity:
HashMap stores slopes for one base point → worst case O(n)
Total space: O(n) extra*/

    /*What GCD Does:
Compute GCD of dy and dx → reduce fraction to simplest form.
For AB: gcd(4,2) = 2 → (4/2, 2/2) = (2,1)
For AC: gcd(2,1) = 1 → (2/1,1/1) = (2,1)
Now both slopes become (2,1) → same key in hashmap, correctly counted as the same line.*/
    fun maxPoints(points: Array<IntArray>): Int {
        // If 2 or fewer points, all points lie on a line
        if (points.size <= 2) return points.size

        var result = 0

        for (i in points.indices) {
            val map: MutableMap<Pair<Int, Int>, Int> = mutableMapOf() // stores slope counts
            var samePoint = 0 // counts duplicate points
            var maxSlope = 0 // max points with same slope for this base point

            val (x1, y1) = points[i] // base point

            for (j in i + 1 until points.size) {
                val (x2, y2) = points[j]

                var dx = x2 - x1 // difference in x
                var dy = y2 - y1 // difference in y

                // Case: same point as base
                if (dx == 0 && dy == 0) {
                    samePoint++
                    continue
                }

                // Reduce slope to simplest fraction using GCD
                // Why? To avoid floating point precision errors and ensure uniqueness
                val g = gcd(dx, dy)
                dx /= g
                dy /= g

                // Ensure unique representation of slope
                // Why? Lines in opposite directions should be treated the same
                if (dx < 0) {
                    dx = -dx
                    dy = -dy
                }

                // Handle vertical line: dx = 0
                // Why? Any vertical line should have same representation (1,0)
                if (dx == 0) dy = 1

                // Handle horizontal line: dy = 0
                // Why? Any horizontal line should have same representation (0,1)
                if (dy == 0) dx = 1

                val slope = Pair(dy, dx) // slope represented as a pair of integers (rise/run)
                map[slope] = map.getOrDefault(slope, 0) + 1
                maxSlope = maxOf(maxSlope, map[slope]!!) // update max slope count
            }

            // Add base point and duplicate points to maxSlope for this iteration
            result = maxOf(result, maxSlope + samePoint + 1)
        }

        return result
    }

    // Euclid's algorithm to find GCD of two integers
    fun gcd(a: Int, b: Int): Int {
        var x = abs(a)
        var y = abs(b)
        while (y != 0) {
            val temp = y
            y = x % y
            x = temp
        }
        return x
    }
}

fun main() {
    val obj = MaxPointsOnLine()
    val points = arrayOf(
        intArrayOf(1, 1),
        intArrayOf(3, 2),
        intArrayOf(5, 3),
        intArrayOf(4, 1),
        intArrayOf(2, 3),
        intArrayOf(1, 4)
    )

    println(obj.maxPoints(points)) // Output: 4
}