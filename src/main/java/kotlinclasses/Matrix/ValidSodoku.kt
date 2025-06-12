/*
 * *
 *  * Valid Sudoku.kt
 *  * Created by Rafsan Ahmad on 6/12/25, 2:12PM
 *  * Copyright (c) 2025 . All rights reserved.
 *  *
 *
 */

package kotlinclasses.Matrix

class ValidSodoku {
    //https://leetcode.com/problems/valid-sudoku/description/
    /*Determine if a 9 x 9 Sudoku board is valid. Only the filled cells need to be validated
    according to the following rules:

Each row must contain the digits 1-9 without repetition.
Each column must contain the digits 1-9 without repetition.
Each of the nine 3 x 3 sub-boxes of the grid must contain the digits 1-9 without repetition.
Note:

A Sudoku board (partially filled) could be valid but is not necessarily solvable.
Only the filled cells need to be validated according to the mentioned rules.


Example 1:
Input: board =
[["5","3",".",".","7",".",".",".","."]
,["6",".",".","1","9","5",".",".","."]
,[".","9","8",".",".",".",".","6","."]
,["8",".",".",".","6",".",".",".","3"]
,["4",".",".","8",".","3",".",".","1"]
,["7",".",".",".","2",".",".",".","6"]
,[".","6",".",".",".",".","2","8","."]
,[".",".",".","4","1","9",".",".","5"]
,[".",".",".",".","8",".",".","7","9"]]
Output: true


Example 2:
Input: board =
[["8","3",".",".","7",".",".",".","."]
,["6",".",".","1","9","5",".",".","."]
,[".","9","8",".",".",".",".","6","."]
,["8",".",".",".","6",".",".",".","3"]
,["4",".",".","8",".","3",".",".","1"]
,["7",".",".",".","2",".",".",".","6"]
,[".","6",".",".",".",".","2","8","."]
,[".",".",".","4","1","9",".",".","5"]
,[".",".",".",".","8",".",".","7","9"]]
Output: false
Explanation: Same as Example 1, except with the 5 in the top left corner being modified to 8.
Since there are two 8's in the top left 3x3 sub-box, it is invalid.


Constraints:
board.length == 9
board[i].length == 9
board[i][j] is a digit 1-9 or '.'.*/

    fun isValidSudoku(board: Array<CharArray>): Boolean {
        //Row
        for (i in 0 until board.size) {
            val rowSet = mutableSetOf<Char>()
            for (j in 0 until board.size) {
                if (board[i][j] == '.') continue
                if (rowSet.contains(board[i][j])) {
                    return false
                }
                rowSet.add(board[i][j])
            }
        }

        //column
        for (j in 0 until board.size) {
            val colSet = mutableSetOf<Char>()
            for (i in 0 until board.size) {
                if (board[i][j] == '.') continue
                if (colSet.contains(board[i][j])) {
                    return false
                }
                colSet.add(board[i][j])
            }
        }

        //check 3X3
        for (row in 0 until 3) {
            for (col in 0 until 3) {
                val boxSet = mutableSetOf<Char>()

                for (i in 0 until 3) {
                    for (j in 0 until 3) {
                        val boxRow = row * 3 + i
                        val boxCol = col * 3 + j
                        val ch = board[boxRow][boxCol]
                        if (ch == '.') continue
                        if (boxSet.contains(ch)) return false
                        boxSet.add(ch)
                    }
                }
            }
        }

        return true
    }


    fun isValidSudokuApproach2(board: Array<CharArray>): Boolean {
        val N = 9

        // Create 9 sets for rows, columns, and boxes
        val rows = Array(N) { mutableSetOf<Char>() }
        val cols = Array(N) { mutableSetOf<Char>() }
        val boxes = Array(N) { mutableSetOf<Char>() }

        for (r in 0 until N) {
            for (c in 0 until N) {
                val value = board[r][c]
                if (value == '.') continue

                // Check row
                if (!rows[r].add(value)) return false

                // Check column
                if (!cols[c].add(value)) return false

                // Check box index
                val idx = (r / 3) * 3 + (c / 3)
                if (!boxes[idx].add(value)) return false
            }
        }

        return true
    }
}

fun main() {
    val obj = ValidSodoku()
    val board = arrayOf(
        charArrayOf('5', '3', '.', '.', '7', '.', '.', '.', '.'),
        charArrayOf('6', '.', '.', '1', '9', '5', '.', '.', '.'),
        charArrayOf('.', '9', '8', '.', '.', '.', '.', '6', '.'),
        charArrayOf('8', '.', '.', '.', '6', '.', '.', '.', '3'),
        charArrayOf('4', '.', '.', '8', '.', '3', '.', '.', '1'),
        charArrayOf('7', '.', '.', '.', '2', '.', '.', '.', '6'),
        charArrayOf('.', '6', '.', '.', '.', '.', '2', '8', '.'),
        charArrayOf('.', '.', '.', '4', '1', '9', '.', '.', '5'),
        charArrayOf('.', '.', '.', '.', '8', '.', '.', '7', '9')
    )

    println(obj.isValidSudoku(board))
    println(obj.isValidSudokuApproach2(board))
}

