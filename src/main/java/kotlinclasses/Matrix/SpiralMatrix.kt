/*
 * *
 *  * Spiral Matrix.kt
 *  * Created by Rafsan Ahmad on 7/31/25, 5:14PM
 *  * Copyright (c) 2025 . All rights reserved.
 *  *
 *
 */

package kotlinclasses.Matrix

class SpiralMatrix {
    //https://leetcode.com/problems/spiral-matrix/description/
    /*Given an m x n matrix, return all elements of the matrix in spiral order.



Example 1:
 1 →  2 →  3
 ↑         ↓
 4    5    6
 ↑         ↓
 7 ←  8 ←  9

Input: matrix = [[1,2,3],[4,5,6],[7,8,9]]
Output: [1,2,3,6,9,8,7,4,5]

Example 2:
 1 →  2 →  3 →  4
 ↑               ↓
 5    6 →  7     8
 ↑               ↓
 9 ← 10 ← 11 ← 12
Input: matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
Output: [1,2,3,4,8,12,11,10,9,5,6,7]


Constraints:
m == matrix.length
n == matrix[i].length
1 <= m, n <= 10
-100 <= matrix[i][j] <= 100*/

    fun spiralOrder(matrix: Array<IntArray>): List<Int> {
        if (matrix.isEmpty()) return emptyList()

        val m = matrix.size
        val n = matrix[0].size
        val result: MutableList<Int> = mutableListOf()
        var k = 0
        var top = 0
        var left = 0
        var right = n - 1
        var bottom = m - 1

        while (k < m * n) {
            for (i in left..right) {
                result.add(matrix[top][i])
                k++
            }
            top++
            for (i in top..bottom) {
                result.add(matrix[i][right])
                k++
            }
            right--
            if (top <= bottom) {
                for (i in right downTo left) {
                    result.add(matrix[bottom][i])
                    k++
                }
                bottom--
            }

            if (left <= right) {
                for (i in bottom downTo top) {
                    result.add(matrix[i][left])
                    k++
                }
                left++
            }
        }

        return result
    }
}

fun main() {
    val obj = SpiralMatrix()
    val matrix1 = arrayOf(
        intArrayOf(1, 2, 3),
        intArrayOf(4, 5, 6),
        intArrayOf(7, 8, 9)
    )

    val matrix2 = arrayOf(
        intArrayOf(1, 2, 3, 4),
        intArrayOf(5, 6, 7, 8),
        intArrayOf(9, 10, 11, 12)
    )

    println(obj.spiralOrder(matrix1))
    println(obj.spiralOrder(matrix2))
}