/*
 * *
 *  * Minimum Path Sum.kt
 *  * Created by Rafsan Ahmad on 7/22/25, 1:53PM
 *  * Copyright (c) 2025 . All rights reserved.
 *  *
 *
 */

package kotlinclasses.DynamicProgramming

class MinimumPathSum {
    //https://leetcode.com/problems/minimum-path-sum/description/
    /*Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right,
     which minimizes the sum of all numbers along its path.

Note: You can only move either down or right at any point in time.

Example 1:
1 3 1
1 5 1
4 2 1
Input: grid = [[1,3,1],[1,5,1],[4,2,1]]
Output: 7
Explanation: Because the path 1 → 3 → 1 → 1 → 1 minimizes the sum.

Example 2:
Input: grid = [[1,2,3],[4,5,6]]
Output: 12


Constraints:
m == grid.length
n == grid[i].length
1 <= m, n <= 200
0 <= grid[i][j] <= 200*/

    //dp[i][j] represents:
    //The minimum path sum from cell (i, j) to the bottom-right cell (m-1, n-1) of the grid.
    fun minPathSum(grid: Array<IntArray>): Int {
        if (grid.isEmpty()) return 0
        val m = grid.size
        val n = grid[0].size
        val dp = Array(m) { IntArray(n) { Integer.MAX_VALUE } }
        return minPathSumHelper(grid, dp, 0, 0)
    }

    fun minPathSumHelper(grid: Array<IntArray>, dp: Array<IntArray>, i: Int, j: Int): Int {
        val m = grid.size
        val n = grid[0].size

        if (i >= m || j >= n) return Integer.MAX_VALUE
        if (i == m - 1 && j == n - 1) return grid[i][j]
        if (dp[i][j] != Integer.MAX_VALUE) return dp[i][j]

        val value = grid[i][j]
        val right = minPathSumHelper(grid, dp, i, j + 1)
        val down = minPathSumHelper(grid, dp, i + 1, j)
        dp[i][j] = value + minOf(right, down)
        return dp[i][j]
    }

    fun minPathSumBottomUp(grid: Array<IntArray>): Int {
        if (grid.isEmpty()) return 0
        val m = grid.size
        val n = grid[0].size
        val dp = Array(m + 1) { IntArray(n + 1) { Integer.MAX_VALUE } }

        dp[1][1] = grid[0][0]
        for (i in 1..m) {
            for (j in 1..n) {
                if (i == 1 && j == 1) continue
                val right = dp[i - 1][j]
                val down = dp[i][j - 1]
                dp[i][j] = grid[i - 1][j - 1] + minOf(right, down)
            }
        }

        return dp[m][n]
    }
}

fun main() {
    val obj = MinimumPathSum()
    val arr = arrayOf(
        intArrayOf(1, 3, 1),
        intArrayOf(1, 5, 1),
        intArrayOf(4, 2, 1)
    )

    println(obj.minPathSum(arr))
    println(obj.minPathSumBottomUp(arr))
}