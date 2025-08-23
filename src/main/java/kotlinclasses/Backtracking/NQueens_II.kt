/*
 * *
 *  * N-Queens II.kt
 *  * Created by Rafsan Ahmad on 8/19/25, 4:06AM
 *  * Copyright (c) 2025 . All rights reserved.
 *  *
 *
 */

package kotlinclasses.Backtracking

class NQueens_II {
    //https://leetcode.com/problems/n-queens-ii/description/
    /*The n-queens puzzle is the problem of placing n queens on an n x n chessboard such that no
    two queens attack each other.

Given an integer n, return the number of distinct solutions to the n-queens puzzle.

Example 1:
Input: n = 4
Output: 2
Explanation: There are two distinct solutions to the 4-queens puzzle as shown.

Example 2:
Input: n = 1
Output: 1


Constraints:
1 <= n <= 9

*/

    /*Time Complexity
Your algorithm has two parts:
1. dfs(col, board)
This is the backtracking part.
At each column you try all n rows.
In the worst case, you explore all possible queen placements, which is O(n!) states.
Why? Because in column 1 → n choices, column 2 → at most n-1 choices, etc. = n!.
So the search tree size is O(n!).

2. checkValidPosition(board, row, col)
You check row: O(n)
You check column: O(n)
You check 4 diagonals: each O(n)
Total = O(n) per check.

Time complexity: O(n × n!)

Space Complexity:
Board: n × n grid → O(n²).
Recursion depth: n (one queen per column) → O(n).
Space complexity: O(n²)
*/
    var queenCount = 0
    fun totalNQueens(n: Int): Int {
        val board: Array<CharArray> = Array(n) { CharArray(n) { '.' } }
        dfs(0, board)
        return queenCount
    }

    fun checkValidPosition(board: Array<CharArray>, row: Int, col: Int): Boolean {
        //Search row
        var r = row
        var c = col

        //Search row
        for (col in 0 until board.size) {
            if (board[r][col] == 'Q') {
                return false
            }
        }

        c = col
        //Search col
        for (row in 0 until board.size) {
            if (board[row][c] == 'Q') {
                return false
            }
        }

        r = row
        c = col

        //top left
        while (r >= 0 && c >= 0) {
            if (board[r][c] == 'Q') return false
            r--
            c--
        }

        r = row
        c = col

        //bottom left
        while (r < board.size && c >= 0) {
            if (board[r][c] == 'Q') return false
            r++
            c--
        }

        r = row
        c = col
        //top right
        while (c < board.size && r >= 0) {
            if (board[r][c] == 'Q') return false
            r--
            c++
        }

        r = row
        c = col
        //bottom right
        while (r < board.size && c < board.size) {
            if (board[r][c] == 'Q') return false
            r++
            c++
        }

        return true
    }

    fun dfs(col: Int, board: Array<CharArray>) {
        if (col == board.size) {
            queenCount++
            return
        }

        for (row in 0 until board.size) {
            if (checkValidPosition(board, row, col)) {
                board[row][col] = 'Q'
                dfs(col + 1, board)
                board[row][col] = '.'
            }
        }
    }
}

fun main() {
    val obj = NQueens_II()
    println(obj.totalNQueens(4))
}