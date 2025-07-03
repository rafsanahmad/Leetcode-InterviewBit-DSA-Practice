/*
 * *
 *  * Set Matrix Zeroes.kt
 *  * Created by Rafsan Ahmad on 7/2/25, 4:34PM
 *  * Copyright (c) 2025 . All rights reserved.
 *  *
 *
 */

package kotlinclasses.Matrix

class SetMatrixZeroes {
    //https://leetcode.com/problems/set-matrix-zeroes/description/
    /*Given an m x n integer matrix matrix, if an element is 0, set its entire row and
    column to 0's.

You must do it in place.

Example 1:
        1 1 1           1 0 1
        1 0 1  ----->   0 0 0
        1 1 1           1 0 1
Input: matrix = [[1,1,1],[1,0,1],[1,1,1]]
Output: [[1,0,1],[0,0,0],[1,0,1]]

Example 2:
Input: matrix = [[0,1,2,0],[3,4,5,2],[1,3,1,5]]
Output: [[0,0,0,0],[0,4,5,0],[0,3,1,0]]


Constraints:
m == matrix.length
n == matrix[0].length
1 <= m, n <= 200
-2^31 <= matrix[i][j] <= 2^31 - 1


Follow up:
A straightforward solution using O(mn) space is probably a bad idea.
A simple improvement uses O(m + n) space, but still not the best solution.
Could you devise a constant space solution?*/

    /*Total Time Complexity:O(m × n)
    Total Space Complexity:O(m + n)
    */
    fun setZeroes(matrix: Array<IntArray>): Unit {
        val rowSet = hashSetOf<Int>()
        val colSet = hashSetOf<Int>()

        for (i in matrix.indices) {
            for (j in matrix[0].indices) {
                if (matrix[i][j] == 0) {
                    rowSet.add(i)
                    colSet.add(j)
                }
            }
        }

        for (i in matrix.indices) {
            for (j in matrix[0].indices) {
                if (rowSet.contains(i))
                    matrix[i][j] = 0
                else if (colSet.contains(j))
                    matrix[i][j] = 0
            }
        }
    }

    /*Time: O(m * n)
    Space: O(1) (in-place, no extra space used except for a few flags)*/
    fun setZeroesSpaceOptimized(matrix: Array<IntArray>) {
        val rows = matrix.size
        val cols = matrix[0].size

        var firstRowZero = false
        var firstColZero = false

        // Step 1: Check if first row and first column need to be zeroed
        for (i in 0 until rows) {
            if (matrix[i][0] == 0) firstColZero = true
        }
        for (j in 0 until cols) {
            if (matrix[0][j] == 0) firstRowZero = true
        }

        // Step 2: Use first row and column to mark zeros
        for (i in 1 until rows) {
            for (j in 1 until cols) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = 0
                    matrix[0][j] = 0
                }
            }
        }

        // Step 3: Zero out cells based on marks
        for (i in 1 until rows) {
            for (j in 1 until cols) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0
                }
            }
        }

        // Step 4: Zero out first row if needed
        if (firstRowZero) {
            for (j in 0 until cols) {
                matrix[0][j] = 0
            }
        }

        // Step 5: Zero out first column if needed
        if (firstColZero) {
            for (i in 0 until rows) {
                matrix[i][0] = 0
            }
        }
    }

    fun printMatrix(matrix: Array<IntArray>) {
        for (row in matrix) {
            println(row.joinToString(", ", "[", "]"))
        }
    }
}

fun main() {
    val obj = SetMatrixZeroes()
    val matrix = arrayOf(
        intArrayOf(0, 1, 2, 0),
        intArrayOf(3, 4, 5, 2),
        intArrayOf(1, 3, 1, 5)
    )
    obj.printMatrix(matrix)
    println()
    obj.setZeroes(matrix)
    obj.printMatrix(matrix)
    println()

    val matrix2 = arrayOf(
        intArrayOf(0, 1, 2, 0),
        intArrayOf(3, 4, 5, 2),
        intArrayOf(1, 3, 1, 5)
    )
    obj.printMatrix(matrix2)
    println()
    obj.setZeroes(matrix2)
    obj.printMatrix(matrix2)
}