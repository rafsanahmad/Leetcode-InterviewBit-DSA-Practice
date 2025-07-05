/*
 * *
 *  * Surrounded Regions.kt
 *  * Created by Rafsan Ahmad on 7/5/25, 10:22PM
 *  * Copyright (c) 2025 . All rights reserved.
 *  *
 *
 */

package kotlinclasses.Graph

class SurroundedRegions {
    //https://leetcode.com/problems/surrounded-regions/description/
    /*You are given an m x n matrix board containing letters 'X' and 'O', capture regions
    that are surrounded:

Connect: A cell is connected to adjacent cells horizontally or vertically.
Region: To form a region connect every 'O' cell.
Surround: The region is surrounded with 'X' cells if you can connect the region with 'X'
cells and none of the region cells are on the edge of the board.
To capture a surrounded region, replace all 'O's with 'X's in-place within the original
board. You do not need to return anything.



Example 1:

Input: board = [["X","X","X","X"],["X","O","O","X"],["X","X","O","X"],["X","O","X","X"]]

Output: [["X","X","X","X"],["X","X","X","X"],["X","X","X","X"],["X","O","X","X"]]

Explanation:
//res/surrounded_regions.jpg
In the above diagram, the bottom region is not captured because it is on the edge of the
board and cannot be surrounded.

Example 2:
Input: board = [["X"]]
Output: [["X"]]


Constraints:
m == board.length
n == board[i].length
1 <= m, n <= 200
board[i][j] is 'X' or 'O'.*/

    fun solve(board: Array<CharArray>): Unit {
        if (board.isEmpty()) return
        val rows = board.size
        val cols = board[0].size
        var visited = Array(rows) { IntArray(cols) { 0 } }

        //Row
        for (i in 0 until cols) {
            if (board[0][i] == 'O') {
                boardDFS(board, visited, 0, i)
            }
            if (board[rows - 1][i] == 'O') {
                boardDFS(board, visited, rows - 1, i)
            }
        }

        //Col
        for (i in 0 until rows) {
            if (board[i][0] == 'O') {
                boardDFS(board, visited, i, 0)
            }
            if (board[i][cols - 1] == 'O') {
                boardDFS(board, visited, i, cols - 1)
            }
        }

        for (i in 0 until rows) {
            for (j in 0 until cols) {
                if (visited[i][j] == 0) {
                    board[i][j] = 'X'
                }
            }
        }
    }

    fun boardDFS(board: Array<CharArray>, visited: Array<IntArray>, i: Int, j: Int) {
        if (i > board.size - 1 || i < 0 || j > board[0].size - 1 || j < 0 || board[i][j] == 'X')
            return

        if (visited[i][j] == 1) return

        visited[i][j] = 1
        boardDFS(board, visited, i + 1, j)
        boardDFS(board, visited, i - 1, j)
        boardDFS(board, visited, i, j + 1)
        boardDFS(board, visited, i, j - 1)
    }
}

fun main() {
    val obj = SurroundedRegions()
    val board = arrayOf(
        charArrayOf('O', 'O', 'O'),
        charArrayOf('O', 'O', 'O'),
        charArrayOf('O', 'O', 'O')
    )
    println(obj.solve(board))
    println(board.contentDeepToString())

    val board2 = arrayOf(
        charArrayOf('X', 'X', 'X', 'X'),
        charArrayOf('X', 'O', 'O', 'X'),
        charArrayOf('X', 'X', 'O', 'X'),
        charArrayOf('X', 'O', 'X', 'X')
    )

    println(obj.solve(board2))
    println(board2.contentDeepToString())
}