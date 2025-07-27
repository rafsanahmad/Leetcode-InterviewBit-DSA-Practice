/*
 * *
 *  * Triangle.kt
 *  * Created by Rafsan Ahmad on 7/21/25, 9:19PM
 *  * Copyright (c) 2025 . All rights reserved.
 *  *
 *
 */

package kotlinclasses.DynamicProgramming

import kotlin.math.min

class Triangle {
    /*Given a triangle array, return the minimum path sum from top to bottom.

For each step, you may move to an adjacent number of the row below. More formally, if you are on
index i on the current row, you may move to either index i or index i + 1 on the next row.


Example 1:
Input: triangle = [[2],[3,4],[6,5,7],[4,1,8,3]]
Output: 11
Explanation: The triangle looks like:
   2
  3 4
 6 5 7
4 1 8 3
The minimum path sum from top to bottom is 2 + 3 + 5 + 1 = 11 (underlined above).

Example 2:
Input: triangle = [[-10]]
Output: -10


Constraints:
1 <= triangle.length <= 200
triangle[0].length == 1
triangle[i].length == triangle[i - 1].length + 1
-10^4 <= triangle[i][j] <= 10^4

Follow up: Could you do this using only O(n) extra space, where n is the total number of rows in
the triangle?
*/

    //Faster than Bottom up
    // Time Complexity: O(n²)
    // Space Complexity: O(n²) + O(n) = O(n²)
    //dp[i][j] stores the minimum path sum from cell (i, j) to the bottom of the triangle.
    fun minimumTotal(triangle: List<List<Int>>): Int {
        val n = triangle.size
        val dp = Array(n) { IntArray(n) { Int.MAX_VALUE } }
        return minimumTotalRec(triangle, dp, 0, 0)
    }

    fun minimumTotalRec(triangle: List<List<Int>>, dp: Array<IntArray>, i: Int, j: Int): Int {
        if (i == triangle.size - 1) return triangle[i][j]
        if (dp[i][j] != Int.MAX_VALUE) return dp[i][j]

        val down = minimumTotalRec(triangle, dp, i + 1, j)
        val diagonal = minimumTotalRec(triangle, dp, i + 1, j + 1)
        dp[i][j] = triangle[i][j] + minOf(down, diagonal)

        return dp[i][j]
    }

    fun minimumTotalBottomUp(triangle: List<List<Int>>): Int {
        if (triangle.isEmpty()) return 0
        val n = triangle.size
        val dp = Array(n + 1) { IntArray(n + 1) { Integer.MAX_VALUE } }
        dp[1][1] = triangle[0][0]

        for (i in 2..n) {
            for (j in 1..triangle[i - 1].size) {
                val value = triangle[i - 1][j - 1]
                dp[i][j] = minOf(dp[i - 1][j], dp[i - 1][j - 1]) + value
            }
        }

        // Find min value in the last row
        return dp[n].filter { it < Integer.MAX_VALUE }.minOrNull() ?: 0
    }
}

fun main() {
    val obj = Triangle()
    val list = listOf(
        listOf(2),
        listOf(3, 4),
        listOf(6, 5, 7),
        listOf(4, 1, 8, 3)
    )
    println(obj.minimumTotal(list))
    println(obj.minimumTotalBottomUp(list))
}