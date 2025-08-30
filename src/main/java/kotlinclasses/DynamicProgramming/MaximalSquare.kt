/*
 * *
 *  * Maximal Square.kt
 *  * Created by Rafsan Ahmad on 8/30/25, 1:44PM
 *  * Copyright (c) 2025 . All rights reserved.
 *  *
 *
 */

package kotlinclasses.DynamicProgramming

import kotlin.math.max

class MaximalSquare {
    //res/maximal_square.jpg
    //https://leetcode.com/problems/maximal-square/
    /*Given an m x n binary matrix filled with 0's and 1's, find the largest square containing
    only 1's and return its area.

Example 1:
Input: matrix = [
["1","0","1","0","0"],
["1","0","1","1","1"],
["1","1","1","1","1"],
["1","0","0","1","0"]
]
Output: 4

Example 2:
Input: matrix = [["0","1"],["1","0"]]
Output: 1
Example 3:

Input: matrix = [["0"]]
Output: 0


Constraints:

m == matrix.length
n == matrix[i].length
1 <= m, n <= 300
matrix[i][j] is '0' or '1'.*/

    /*Complexity:
Time: O(m × n) → we fill every cell once.
Space: O(m × n) for dp.*/

    /*dp[i][j] = size of the largest square of 1’s **starting at (i,j)
     (where (i,j) is the top-left corner of that square).
So for every cell (i,j), dp[i][j] answers:
“If I start a square at this cell, what’s the biggest square I can build going down-right?”*/

    /*Intuition: How it maintains a square
Imagine this 2×2 block of 1’s:

1 1
1 1

At bottom-right: dp = 1 (just that cell).
At bottom-left: dp = 1.
At top-right: dp = 1.
At top-left: dp = 1 + min(1,1,1) = 2.

That’s exactly how we detect the full 2×2 square.
If even one cell were 0, the min would drop, and the top-left couldn’t claim a bigger square.*/
    fun maximalSquare(matrix: Array<CharArray>): Int {
        if (matrix.isEmpty()) return 0

        val m = matrix.size
        val n = matrix[0].size
        val dp: Array<IntArray> = Array(m + 1) { IntArray(n + 1) { -1 } }
        var maxSize = 0

        fun dfs(i: Int, j: Int, dp: Array<IntArray>): Int {
            if (i >= m || j >= n) return 0

            if (dp[i][j] != -1) return dp[i][j]
            if (matrix[i][j] == '1') {
                val size = 1 + minOf(
                    dfs(i + 1, j, dp),  // down
                    dfs(i, j + 1, dp),  // right
                    dfs(i + 1, j + 1, dp)   // diagonal
                )

                dp[i][j] = size
                maxSize = max(maxSize, size)
            } else {
                dp[i][j] = 0
            }

            return dp[i][j]
        }

        for (i in 0 until m) {
            for (j in 0 until n) {
                dfs(i, j, dp)
            }
        }

        return maxSize * maxSize
    }

    fun maximalSquareBottomUp(matrix: Array<CharArray>): Int {
        if (matrix.isEmpty()) return 0

        val m = matrix.size
        val n = matrix[0].size
        val dp = Array(m) { IntArray(n) }
        var maxSize = 0

        for (i in 0 until m) {
            for (j in 0 until n) {
                if (matrix[i][j] == '1') {
                    if (i == 0 || j == 0) {
                        // first row or first col → only 1x1 square possible
                        dp[i][j] = 1
                    } else {
                        dp[i][j] = 1 + minOf(
                            dp[i - 1][j],      // top
                            dp[i][j - 1],      // left
                            dp[i - 1][j - 1]   // top-left
                        )
                    }
                    maxSize = maxOf(maxSize, dp[i][j])
                }
            }
        }

        return maxSize * maxSize
    }
}

fun main() {
    val obj = MaximalSquare()
    val matrix = arrayOf(
        charArrayOf('1', '0', '1', '0', '0'),
        charArrayOf('1', '0', '1', '1', '1'),
        charArrayOf('1', '1', '1', '1', '1'),
        charArrayOf('1', '0', '0', '1', '0')
    )
    println(obj.maximalSquare(matrix)) // Expected output: 4
    println(obj.maximalSquareBottomUp(matrix)) // Expected output: 4
}