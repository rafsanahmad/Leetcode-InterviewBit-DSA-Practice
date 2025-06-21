/*
 * *
 *  * Rotate Image.kt
 *  * Created by Rafsan Ahmad on 6/13/25, 12:54AM
 *  * Copyright (c) 2025 . All rights reserved.
 *  *
 *
 */

package kotlinclasses.Matrix


class RotateImage {
    //https://leetcode.com/problems/rotate-image/description/
    /*You are given an n x n 2D matrix representing an image, rotate the image by 90 degrees
    (clockwise).

You have to rotate the image in-place, which means you have to modify the input 2D matrix
directly. DO NOT allocate another 2D matrix and do the rotation.


Example 1:
Input: matrix = [[1,2,3],[4,5,6],[7,8,9]]
Output: [[7,4,1],[8,5,2],[9,6,3]]

 1|2|3    Rotate    7|4|1
 4|5|6   ------->   8|5|2
 7|8|9              9|6|3

Example 2:
Input: matrix = [[5,1,9,11],[2,4,8,10],[13,3,6,7],[15,14,12,16]]
Output: [[15,13,2,5],[14,3,4,1],[12,6,8,9],[16,7,10,11]]


Constraints:
n == matrix.length == matrix[i].length
1 <= n <= 20
-1000 <= matrix[i][j] <= 1000*/

    /*
 * clockwise rotate
 * first reverse up to down, then swap across the diagonal
 * 1 2 3     7 8 9     7 4 1
 * 4 5 6  => 4 5 6  => 8 5 2
 * 7 8 9     1 2 3     9 6 3
  */
    fun rotate(matrix: Array<IntArray>) {
        val rows = matrix.size
        val cols = matrix[0].size

        // Step 1: Reverse each column (vertical flip)
        for (col in 0 until cols) {
            var top = 0
            var bottom = rows - 1
            while (top < bottom) {
                val temp = matrix[top][col]
                matrix[top][col] = matrix[bottom][col]
                matrix[bottom][col] = temp
                top++
                bottom--
            }
        }

        // Step 2: Transpose the matrix (swap across the main diagonal)
        for (j in 0 until cols) {
            var row = j + 1
            var col = j + 1
            while (row < rows && col < cols) {
                val temp = matrix[j][col]
                matrix[j][col] = matrix[row][j]
                matrix[row][j] = temp
                row++
                col++
            }
        }
    }
}

fun main() {
    val obj = RotateImage()
    val matrix = arrayOf(
        intArrayOf(5, 1, 9, 11),
        intArrayOf(2, 4, 8, 10),
        intArrayOf(13, 3, 6, 7),
        intArrayOf(15, 14, 12, 16)
    )

    obj.rotate(matrix)

    println("Rotated matrix:")
    for (row in matrix) {
        println(row.joinToString(", "))
    }
}