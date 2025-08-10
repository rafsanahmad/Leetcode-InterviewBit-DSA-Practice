/*
 * *
 *  * Game of Life.kt
 *  * Created by Rafsan Ahmad on 8/8/25, 11:06PM
 *  * Copyright (c) 2025 . All rights reserved.
 *  *
 *
 */

package kotlinclasses.Matrix

class GameOfLife {
    //https://leetcode.com/problems/game-of-life/description/
    /*According to Wikipedia's article: "The Game of Life, also known simply as Life, is a cellular
    automaton devised by the British mathematician John Horton Conway in 1970."

The board is made up of an m x n grid of cells, where each cell has an initial state: live
(represented by a 1) or dead (represented by a 0). Each cell interacts with its eight neighbors
(horizontal, vertical, diagonal) using the following four rules (taken from the above Wikipedia article):

Any live cell with fewer than two live neighbors dies as if caused by under-population.
Any live cell with two or three live neighbors lives on to the next generation.
Any live cell with more than three live neighbors dies, as if by over-population.
Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.
The next state of the board is determined by applying the above rules simultaneously to every cell in
the current state of the m x n grid board. In this process, births and deaths occur simultaneously.

Given the current state of the board, update the board to reflect its next state.

Note that you do not need to return anything.


0 1 0   0 0 0
0 0 1 → 1 0 1
1 1 1   0 1 1
0 0 0   0 1 0

Example 1:
Input: board = [[0,1,0],[0,0,1],[1,1,1],[0,0,0]]
Output: [[0,0,0],[1,0,1],[0,1,1],[0,1,0]]

Example 2:
Input: board = [[1,1],[1,0]]
Output: [[1,1],[1,1]]


Constraints:
m == board.length
n == board[i].length
1 <= m, n <= 25
board[i][j] is 0 or 1.


Follow up:
Could you solve it in-place? Remember that the board needs to be updated simultaneously: You cannot
update some cells first and then use their updated values to update other cells.
In this question, we represent the board using a 2D array. In principle, the board is infinite, which
would cause problems when the active area encroaches upon the border of the array (i.e., live cells
reach the border). How would you address these problems?*/

    // Time: Total = O(m × n)
    // Space: Total = O(m × n)
    fun gameOfLife(board: Array<IntArray>): Unit {
        val m = board.size
        val n = board[0].size
        val tempArray = Array(board.size) { i ->
            board[i].clone()
        }

        for (i in 0 until m) {
            for (j in 0 until n) {
                val liveCount = checkGameOfLife(tempArray, i, j)
                if (liveCount < 2 || liveCount > 3)
                    board[i][j] = 0
                else if (liveCount == 3)
                    board[i][j] = 1
                else
                    board[i][j] = tempArray[i][j]
            }
        }
    }

    fun checkGameOfLife(
        board: Array<IntArray>, row: Int, col: Int
    ): Int {
        val directions: Array<IntArray> = arrayOf(
            intArrayOf(-1, 0),
            intArrayOf(0, -1),
            intArrayOf(0, 1),
            intArrayOf(1, 0),
            intArrayOf(-1, -1),
            intArrayOf(1, 1),
            intArrayOf(1, -1),
            intArrayOf(-1, 1)
        )
        var liveCount = 0
        for (item in directions) {
            val newRow = row + item[0]
            val newCol = col + item[1]
            if (newRow < 0 || newCol < 0 || newRow >= board.size || newCol >= board[0].size) {
                continue
            }
            if (board[newRow][newCol] == 1)
                liveCount++
        }
        return liveCount
    }
}

fun main() {
    val obj = GameOfLife()
    val board = arrayOf(
        intArrayOf(0, 1, 0),
        intArrayOf(0, 0, 1),
        intArrayOf(1, 1, 1),
        intArrayOf(0, 0, 0)
    )

    obj.gameOfLife(board)
    println(board.contentDeepToString())
}