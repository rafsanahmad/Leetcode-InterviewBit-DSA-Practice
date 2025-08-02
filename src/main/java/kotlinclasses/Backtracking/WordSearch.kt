/*
 * *
 *  * Word Search.kt
 *  * Created by Rafsan Ahmad on 7/30/25, 1:56AM
 *  * Copyright (c) 2025 . All rights reserved.
 *  *
 *
 */

package kotlinclasses.Backtracking

class WordSearch {
    //https://leetcode.com/problems/word-search/description/
    /*Given an m x n grid of characters board and a string word, return true if word exists in the
    grid.

The word can be constructed from letters of sequentially adjacent cells, where adjacent cells are
horizontally or vertically neighboring. The same letter cell may not be used more than once.

Example 1:
Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCCED"
Output: true

Example 2:
Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "SEE"
Output: true

Example 3:
Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCB"
Output: false


Constraints:
m == board.length
n = board[i].length
1 <= m, n <= 6
1 <= word.length <= 15
board and word consists of only lowercase and uppercase English letters.

Follow up: Could you use search pruning to make your solution faster with a larger board?*/

    /*Time Complexity
Worst-case:
We try to start the word from each cell: m × n possibilities
From each cell, we try to explore in 4 directions (up/down/left/right)
We can’t revisit the same cell — so the maximum depth of the recursion is L (length of the word)
So: Time = O(m × n × 4^L)

Space Complexity
1. Visited matrix
O(m × n) for visited array
2. Call stack (Recursion)
At most, the recursion stack goes L deep
So call stack: O(L)
*/
    fun exist(board: Array<CharArray>, word: String): Boolean {
        if (word.isEmpty()) return true
        val m = board.size
        val n = board[0].size

        val visited = Array(m) { IntArray(n) { 0 } }
        for (i in 0 until m) {
            for (j in 0 until n) {
                if (existBacktracking(board, visited, i, j, word, 0)) {
                    return true
                }
            }
        }
        return false
    }

    fun existBacktracking(
        board: Array<CharArray>,
        visited: Array<IntArray>,
        row: Int,
        col: Int,
        word: String,
        index: Int
    ): Boolean {
        val m = board.size
        val n = board[0].size
        if (index == word.length) return true

        if (row >= m || col >= n || row < 0 || col < 0) {
            return false
        }

        if (visited[row][col] == 1 || word[index] != board[row][col])
            return false

        visited[row][col] = 1

        val found = existBacktracking(board, visited, row + 1, col, word, index + 1) ||
                existBacktracking(board, visited, row - 1, col, word, index + 1) ||
                existBacktracking(board, visited, row, col + 1, word, index + 1) ||
                existBacktracking(board, visited, row, col - 1, word, index + 1)
        visited[row][col] = 0

        return found
    }
}

fun main() {
    val obj = WordSearch()
    val board = arrayOf(
        charArrayOf('A', 'B', 'C', 'E'),
        charArrayOf('S', 'F', 'C', 'S'),
        charArrayOf('A', 'D', 'E', 'E')
    )
    println(obj.exist(board, "ABCCED"))
}

