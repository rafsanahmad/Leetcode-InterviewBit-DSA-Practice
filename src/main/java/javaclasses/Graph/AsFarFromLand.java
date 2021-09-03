package javaclasses.Graph;

import java.util.LinkedList;
import java.util.Queue;

public class AsFarFromLand {
    //Leetcode 1162
    //res/grid_ex1.jpeg
    //res/grid_ex2.jpeg
    //res/bfs_traversal.png
    /*Given an n x n grid containing only values 0 and 1, where 0 represents water and 1 represents
    land, find a water cell such that its distance to the nearest land cell is maximized, and return the distance.
    If no land or water exists in the grid, return -1.

The distance used in this problem is the Manhattan distance: the distance between two cells (x0, y0)
and (x1, y1) is |x0 - x1| + |y0 - y1|.


Example 1:
Input: grid = [[1,0,1],[0,0,0],[1,0,1]]
Output: 2
Explanation: The cell (1, 1) is as far as possible from all the land with distance 2.

Example 2:
Input: grid = [[1,0,0],[0,0,0],[0,0,0]]
Output: 4
Explanation: The cell (2, 2) is as far as possible from all the land with distance 4.
 */

    //Time complexity = o(m*n)
    public int maxDistance(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    queue.offer(new int[]{i, j});
                    visited[i][j] = true;
                }
            }
        }

        if (queue.isEmpty() || queue.size() == m * n) {
            return -1;
        }

        int[][] DIRS = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};
        int step = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] curr = queue.poll();
                for (int[] dir : DIRS) {
                    int nextRow = curr[0] + dir[0];
                    int nextCol = curr[1] + dir[1];
                    if (nextRow < 0 || nextCol < 0 || nextRow >= m || nextCol >= n ||
                            visited[nextRow][nextCol] || grid[nextRow][nextCol] == 1) {
                        continue;
                    }
                    queue.offer(new int[]{nextRow, nextCol});
                    visited[nextRow][nextCol] = true;
                }
            }
            step++;
        }
        return step - 1;
    }

    public static void main(String[] args) {
        AsFarFromLand land = new AsFarFromLand();
        int[][] arr = {
                {1, 0, 1},
                {0, 0, 0},
                {1, 0, 1}
        };
        System.out.println(land.maxDistance(arr));
    }
}
