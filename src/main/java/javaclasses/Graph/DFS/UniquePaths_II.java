/*
 * *
 *  * Unique Paths II.java
 *  * Created by Rafsan Ahmad on 11/6/23, 1:39 AM
 *  * Copyright (c) 2023 . All rights reserved.
 *  *
 *
 */

package javaclasses.Graph.DFS;

import java.util.Arrays;

public class UniquePaths_II {
    //https://leetcode.com/problems/unique-paths-ii/description/
    /*You are given an m x n integer array grid. There is a robot initially located at the top-left corner
    (i.e., grid[0][0]). The robot tries to move to the bottom-right corner (i.e., grid[m - 1][n - 1]).
    The robot can only move either down or right at any point in time.

An obstacle and space are marked as 1 or 0 respectively in grid. A path that the robot takes cannot include
 any square that is an obstacle.

Return the number of possible unique paths that the robot can take to reach the bottom-right corner.
The testcases are generated so that the answer will be less than or equal to 2 * 10^9.

Example 1:
Input: obstacleGrid = [[0,0,0],[0,1,0],[0,0,0]]
Output: 2
Explanation: There is one obstacle in the middle of the 3x3 grid above.
There are two ways to reach the bottom-right corner:
1. Right -> Right -> Down -> Down
2. Down -> Down -> Right -> Right

Example 2:
Input: obstacleGrid = [[0,1],[0,0]]
Output: 1

Constraints:
m == obstacleGrid.length
n == obstacleGrid[i].length
1 <= m, n <= 100
obstacleGrid[i][j] is 0 or 1.*/

    //Using DFS - Time limit Exceeded
    int result = 0;

    public int uniquePathsWithObstaclesUsingDFS(int[][] obstacleGrid) {
        dfsAtGrid(obstacleGrid, 0, 0);
        return result;
    }

    public void dfsAtGrid(int[][] grid, int x, int y) {
        if (x < 0 || y < 0 || x >= grid.length || y >= grid[0].length || grid[x][y] == 1) {
            return;
        }

        //Already visited
        if (grid[x][y] < 0) {
            return;
        }

        if (x == grid.length - 1 && y == grid[0].length - 1) {
            //Reached end
            result++;
            return;
        }

        //mark visited
        grid[x][y] = -1;

        dfsAtGrid(grid, x + 1, y);
        dfsAtGrid(grid, x, y + 1);

        grid[x][y] = 0;
    }

    //Using Dynamic programming - Accepted
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int rowL = obstacleGrid.length;
        if (rowL <= 0) return 0;

        int col = obstacleGrid[0].length;
        int[][] dp = new int[rowL][col];
        for (int i = 0; i < dp.length; i++) {
            Arrays.fill(dp[i], -1);
        }
        return uniquePathRec(obstacleGrid, 0, 0, dp);
    }

    public int uniquePathRec(int[][] grid, int x, int y, int[][] dp) {
        if (x >= grid.length || y == grid[0].length)
            return 0;

        if (dp[x][y] != -1) return dp[x][y];

        if (grid[x][y] == 1) return 0;

        if (x == grid.length - 1 && y == grid[0].length - 1)
            return 1;

        return dp[x][y] = uniquePathRec(grid, x + 1, y, dp) +
                uniquePathRec(grid, x, y + 1, dp);
    }

    public static void main(String[] args) {
        UniquePaths_II uniquePaths_ii = new UniquePaths_II();
        int[][] grid = {{0, 0, 0}, {0, 1, 0}, {0, 0, 0}};
        System.out.println(uniquePaths_ii.uniquePathsWithObstaclesUsingDFS(grid));
        System.out.println(uniquePaths_ii.uniquePathsWithObstacles(grid));
    }
}
