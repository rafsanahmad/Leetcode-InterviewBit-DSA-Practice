/*
 * *
 *  * Number of Islands.kt
 *  * Created by Rafsan Ahmad on 7/3/25, 8:25PM
 *  * Copyright (c) 2025 . All rights reserved.
 *  *
 *
 */

package kotlinclasses.Graph

class NumberOfIslands {
    //https://leetcode.com/problems/number-of-islands/description/
    /*Given an m x n 2D binary grid grid which represents a map of '1's (land) and '0's
    (water), return the number of islands.

An island is surrounded by water and is formed by connecting adjacent lands horizontally or
 vertically. You may assume all four edges of the grid are all surrounded by water.


Example 1:
Input: grid = [
  ["1","1","1","1","0"],
  ["1","1","0","1","0"],
  ["1","1","0","0","0"],
  ["0","0","0","0","0"]
]
Output: 1

Example 2:
Input: grid = [
  ["1","1","0","0","0"],
  ["1","1","0","0","0"],
  ["0","0","1","0","0"],
  ["0","0","0","1","1"]
]
Output: 3


Constraints:
m == grid.length
n == grid[i].length
1 <= m, n <= 300
grid[i][j] is '0' or '1'.*/

    fun numIslands(grid: Array<CharArray>): Int {
        var result: Int = 0
        var visited = Array(grid.size) { IntArray(grid[0].size) { -1 } }

        for (i in grid.indices) {
            for (j in grid[i].indices) {
                // DFS on '1' to explore and count islands.
                if (grid[i][j] == '1' && visited[i][j] == -1) {
                    dfs(grid, visited, i, j)
                    result++
                }
            }
        }
        return result
    }

    fun dfs(grid: Array<CharArray>, visited: Array<IntArray>, i: Int, j: Int) {
        if (i >= grid.size || i < 0 || j >= grid[0].size || j < 0 || grid[i][j] == '0') {
            return
        }

        if (visited[i][j] == 1)
            return

        visited[i][j] = 1

        dfs(grid, visited, i + 1, j)
        dfs(grid, visited, i - 1, j)
        dfs(grid, visited, i, j + 1)
        dfs(grid, visited, i, j - 1)
    }
}

fun main() {
    val obj = NumberOfIslands()
    val grid = arrayOf(
        charArrayOf('1', '1', '0', '0', '0'),
        charArrayOf('1', '1', '0', '0', '0'),
        charArrayOf('0', '0', '1', '0', '0'),
        charArrayOf('0', '0', '0', '1', '1')
    )

    println(obj.numIslands(grid))
}