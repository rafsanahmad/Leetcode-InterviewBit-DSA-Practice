/*
 * *
 *  * Search a 2D Matrix.kt
 *  * Created by Rafsan Ahmad on 6/21/25, 1:33PM
 *  * Copyright (c) 2025 . All rights reserved.
 *  *
 *
 */

package kotlinclasses.BinarySearch


class Search2DMatrix {
    //https://leetcode.com/problems/search-a-2d-matrix/description/
    /*You are given an m x n integer matrix matrix with the following two properties:

Each row is sorted in non-decreasing order.
The first integer of each row is greater than the last integer of the previous row.
Given an integer target, return true if target is in matrix or false otherwise.

You must write a solution in O(log(m * n)) time complexity.



Example 1:
Input: matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 3
Output: true

Example 2:
Input: matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 13
Output: false


Constraints:
m == matrix.length
n == matrix[i].length
1 <= m, n <= 100
-10^4 <= matrix[i][j], target <= 10^4*/

    fun searchMatrix(matrix: Array<IntArray>, target: Int): Boolean {
        val m = matrix.size
        val n = matrix[0].size

        var row = -1
        for (i in 0 until m) {
            if (matrix[i][0] <= target && matrix[i][n - 1] >= target) {
                row = i
            }
        }

        if (row == -1) return false
        var left = 0
        var right = n

        while (left < right) {
            val mid = (left + right) / 2
            if (matrix[row][mid] == target) {
                return true
            } else if (matrix[row][mid] < target) {
                left = mid + 1
            } else {
                right = mid
            }
        }

        return false
    }
}

fun main() {
    val obj = Search2DMatrix()
    val matrix = arrayOf(
        intArrayOf(1, 3, 5, 7),
        intArrayOf(10, 11, 16, 20),
        intArrayOf(23, 30, 34, 60)
    )

    val matrix2 = arrayOf(
        intArrayOf(1)
    )

    println(obj.searchMatrix(matrix2, 1))
    println(obj.searchMatrix(matrix, 34))
    println(obj.searchMatrix(matrix, 9))
    println(obj.searchMatrix(matrix, 24))
}