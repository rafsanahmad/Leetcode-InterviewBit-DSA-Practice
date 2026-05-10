/*
 *
 *  * DetectCycleIn2DGrid.kt
 *  *
 *  * Created by Rafsan Ahmad on 04/29/26, 12:28 PM
 *  * Copyright (c) 2026. All rights reserved.
 *
 */

package kotlinclasses.Graph.UnionFind

class DetectCycleIn2DGrid {
    //https://leetcode.com/problems/detect-cycles-in-2d-grid/description/
    /*Given a 2D array of characters grid of size m x n, you need to find if there exists any
    cycle consisting of the same value in grid.

A cycle is a path of length 4 or more in the grid that starts and ends at the same cell. From a
given cell, you can move to one of the cells adjacent to it - in one of the four directions
(up, down, left, or right), if it has the same value of the current cell.

Also, you cannot move to the cell that you visited in your last move. For example, the cycle
(1, 1) -> (1, 2) -> (1, 1) is invalid because from (1, 2) we visited (1, 1) which was the last
visited cell.

Return true if any cycle of the same value exists in grid, otherwise, return false.

Example 1:
+---+---+---+---+
| a | a | a | a |
+---+---+---+---+
| a | b | b | a |
+---+---+---+---+
| a | b | b | a |
+---+---+---+---+
| a | a | a | a |
+---+---+---+---+
Input: grid = [["a","a","a","a"],["a","b","b","a"],["a","b","b","a"],["a","a","a","a"]]
Output: true
Explanation: There are two valid cycles shown in different colors in the image below:

Example 2:
Input: grid = [["c","c","c","a"],["c","d","c","c"],["c","c","e","c"],["f","c","c","c"]]
Output: true
Explanation: There is only one valid cycle highlighted in the image below:

Example 3:
Input: grid = [["a","b","b"],["b","z","b"],["b","b","a"]]
Output: false


Constraints:

m == grid.length
n == grid[i].length
1 <= m, n <= 500
grid consists only of lowercase English letters.*/

    //Using DFS
    private fun dfs(
        curX: Int,
        curY: Int,
        lastX: Int,
        lastY: Int,
        n: Int,
        m: Int,
        vis: Array<BooleanArray>,
        grid: Array<CharArray>,
        startChar: Char
    ): Boolean {
        val DIR_X = intArrayOf(1, -1, 0, 0)
        val DIR_Y = intArrayOf(0, 0, 1, -1)
        vis[curX][curY] = true
        var hasCycle = false
        // Visit all directions
        for (i in 0..3) {
            val newX = curX + DIR_X[i]
            val newY = curY + DIR_Y[i]
            // Valid point?
            if (newX >= 0 && newX < n && newY >= 0 && newY < m) {
                // Don't visit last visited point
                if (!(newX == lastX && newY == lastY)) {
                    // Only visit nodes that equal start character
                    if (grid[newX][newY] == startChar) {
                        if (vis[newX][newY]) {
                            // Still visited? There is a cycle.
                            return true
                        } else {
                            hasCycle =
                                hasCycle or dfs(newX, newY, curX, curY, n, m, vis, grid, startChar)
                        }
                    }
                }
            }
        }
        return hasCycle
    }

    fun containsCycle(grid: Array<CharArray>): Boolean {
        val n = grid.size
        val m = grid[0].size
        val vis = Array<BooleanArray>(n) { BooleanArray(m) }
        var hasCycle = false
        for (i in grid.indices) {
            for (j in grid[i].indices) {
                if (!vis[i][j]) {
                    hasCycle = hasCycle or dfs(i, j, -1, -1, n, m, vis, grid, grid[i][j])
                    if (hasCycle) break
                }
            }
        }
        return hasCycle
    }

    //Using Union Find
    class DSU(size: Int) {
        val parent = IntArray(size) { it }
        val rank = IntArray(size)

        fun find(x: Int): Int {
            if (parent[x] != x) {
                parent[x] = find(parent[x])
            }
            return parent[x]
        }

        fun union(x: Int, y: Int): Boolean {
            val rootX = find(x)
            val rootY = find(y)

            if (rootX == rootY) return true   // cycle found

            if (rank[rootX] < rank[rootY]) {
                parent[rootX] = rootY
            } else if (rank[rootX] > rank[rootY]) {
                parent[rootY] = rootX
            } else {
                parent[rootY] = rootX
                rank[rootX]++
            }

            return false
        }
    }

    fun containsCycle2(grid: Array<CharArray>): Boolean {
        val rows = grid.size
        val cols = grid[0].size

        val dsu = DSU(rows * cols)

        for (i in 0 until rows) {
            for (j in 0 until cols) {

                val current = i * cols + j

                // RIGHT
                if (j + 1 < cols && grid[i][j] == grid[i][j + 1]) {
                    val right = i * cols + (j + 1)
                    if (dsu.union(current, right)) return true
                }

                // DOWN
                if (i + 1 < rows && grid[i][j] == grid[i + 1][j]) {
                    val down = (i + 1) * cols + j
                    if (dsu.union(current, down)) return true
                }
            }
        }

        return false
    }
}

fun main() {
    val grid = arrayOf(
        charArrayOf('a', 'a', 'a', 'a'),
        charArrayOf('a', 'b', 'b', 'a'),
        charArrayOf('a', 'b', 'b', 'a'),
        charArrayOf('a', 'a', 'a', 'a')
    )

    val solution = DetectCycleIn2DGrid()
    val result = solution.containsCycle(grid)
    println("Output: $result")
    val result2 = solution.containsCycle2(grid)
    println("Output: $result2")
}