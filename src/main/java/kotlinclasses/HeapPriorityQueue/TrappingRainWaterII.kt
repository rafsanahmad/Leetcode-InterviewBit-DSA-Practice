/*
 * *
 *  * Trapping Rain Water II.kt
 *  * Created by Rafsan Ahmad on 10/4/25, 9:20PM
 *  * Copyright (c) 2025 . All rights reserved.
 *  *
 *
 */

package kotlinclasses.HeapPriorityQueue

import java.util.PriorityQueue

class TrappingRainWaterII {
    //https://leetcode.com/problems/trapping-rain-water-ii/description/
    /*Given an m x n integer matrix heightMap representing the height of each unit cell in a 2D elevation
     map, return the volume of water it can trap after raining.

Example 1:
Input: heightMap = [[1,4,3,1,3,2],[3,2,1,3,2,4],[2,3,3,2,3,1]]
Output: 4
Explanation: After the rain, water is trapped between the blocks.
We have two small ponds 1 and 3 units trapped.
The total volume of water trapped is 4.

Example 2:
Input: heightMap = [[3,3,3,3,3],[3,2,2,2,3],[3,2,1,2,3],[3,2,2,2,3],[3,3,3,3,3]]
Output: 10


Constraints:
m == heightMap.length
n == heightMap[i].length
1 <= m, n <= 200
0 <= heightMap[i][j] <= 2 * 10^4*/

    /*What the algorithm does:
We use a min-heap (priority queue) and BFS/DFS idea:
Push all border cells into a min-heap.
Pop the smallest height cell.
Visit its unvisited neighbors.
For each neighbor:
Possibly trap water (maxBoundary - height)
Push that neighbor into the heap with updated boundary height
maxOf(neighborHeight, currentHeight)
Repeat until all cells processed.*/

    data class Cell(val row: Int, val col: Int, val height: Int)

    //Time Complexity: O(m * n * log(m * n))
    //Space Complexity: O(m * n)
    /*Compare side by side:
Case	            Data Structure	         Cost per operation	        Total complexity
Normal BFS / DFS	Queue / Stack	         O(1) per pop/push	        O(V + E)
Rain Water II BFS	Min-Heap (PriorityQueue) O(log V) per pop/push	    O(V log V)
Why log V per operation?

Because in this algorithm:
You always extract the smallest cell height first → that’s why you need a priority queue.
Each insertion and removal in a heap costs O(log V), not O(1).
And since every cell is inserted and removed once:
#cells = V = m * n
→ total = V * log V = O(m * n * log(m * n))*/

    fun trapRainWater(heightMap: Array<IntArray>): Int {
        val m = heightMap.size
        if (m == 0) return 0
        val n = heightMap[0].size
        if (n == 0) return 0

        val visited = Array(m) { BooleanArray(n) }
        val pq = PriorityQueue<Cell>(compareBy { it.height })

        // Step 1: Add all border cells
        for (i in 0 until m) {
            for (j in 0 until n) {
                if (i == 0 || i == m - 1 || j == 0 || j == n - 1) {
                    pq.offer(Cell(i, j, heightMap[i][j]))
                    visited[i][j] = true
                }
            }
        }

        // Directions (up, down, left, right)
        val dirs = arrayOf(
            intArrayOf(1, 0),
            intArrayOf(-1, 0),
            intArrayOf(0, 1),
            intArrayOf(0, -1)
        )

        var totalWater = 0
        var maxBoundary = 0

        // Step 2: Process heap
        while (pq.isNotEmpty()) {
            val cell = pq.poll()
            maxBoundary = maxOf(maxBoundary, cell.height)

            for (dir in dirs) {
                val nr = cell.row + dir[0]
                val nc = cell.col + dir[1]
                if (nr < 0 || nc < 0 || nr >= m || nc >= n || visited[nr][nc]) continue

                visited[nr][nc] = true

                val neighborHeight = heightMap[nr][nc]
                if (neighborHeight < maxBoundary) {
                    totalWater += maxBoundary - neighborHeight
                }

                pq.offer(Cell(nr, nc, maxOf(neighborHeight, maxBoundary)))
            }
        }

        return totalWater
    }
}

fun main() {
    val obj = TrappingRainWaterII()
    val heightMap = arrayOf(
        intArrayOf(1, 4, 3, 1, 3, 2),
        intArrayOf(3, 2, 1, 3, 2, 4),
        intArrayOf(2, 3, 3, 2, 3, 1)
    )

    println(obj.trapRainWater(heightMap)) // Output: 4
}