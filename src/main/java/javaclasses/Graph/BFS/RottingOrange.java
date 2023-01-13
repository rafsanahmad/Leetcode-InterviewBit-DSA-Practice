/*
 * *
 *  * Rotting Orange.java
 *  * Created by Rafsan Ahmad on 4/24/22, 9:05 PM
 *  * Copyright (c) 2023 . All rights reserved.
 *
 */

package javaclasses.Graph.BFS;

import java.util.LinkedList;
import java.util.Queue;

public class RottingOrange {
    //Leetcode 994
    //res/rotting_orange.png
    /*You are given an m x n grid where each cell can have one of three values:

0 representing an empty cell,
1 representing a fresh orange, or
2 representing a rotten orange.
Every minute, any fresh orange that is 4-directionally adjacent to a rotten orange becomes rotten.

Return the minimum number of minutes that must elapse until no cell has a fresh orange. If this is impossible, return -1.

Example 1:
Input: grid = [[2,1,1],[1,1,0],[0,1,1]]
Output: 4

Example 2:
Input: grid = [[2,1,1],[0,1,1],[1,0,1]]
Output: -1
Explanation: The orange in the bottom left corner (row 2, column 0) is never rotten, because rotting only happens
4-directionally.

Example 3:
Input: grid = [[0,2]]
Output: 0
Explanation: Since there are already no fresh oranges at minute 0, the answer is just 0.*/

    //Using BFS
    public int orangesRottingUsingBFS(int[][] grid) {
        int rows = grid.length, cols = grid[0].length;
        Queue<int[]> queue = new LinkedList<>();
        int freshCount = 0;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 1) {
                    freshCount++;
                } else if (grid[i][j] == 2) {
                    queue.offer(new int[]{i, j});
                }
            }
        }

        int[][] DIRS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        int step = 0;
        while (!queue.isEmpty() && freshCount > 0) {
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                int[] curr = queue.poll();
                for (int[] dir : DIRS) {
                    int nextX = curr[0] + dir[0];
                    int nextY = curr[1] + dir[1];
                    if (nextX < 0 || nextX >= rows || nextY < 0 || nextY >= cols || grid[nextX][nextY] != 1) {
                        continue;
                    }
                    queue.offer(new int[]{nextX, nextY});
                    grid[nextX][nextY] = 2;
                    freshCount--;
                }
            }

            step++;
        }

        return freshCount == 0 ? step : -1;
    }

    private static final int FRESH = 1;
    private static final int ROTTEN = 2;
    private static final int TIME_OFFSET = 3;
    private static final int CANNOT_BE_ROTTEN = -1;

    //Using DFS
    public int orangesRottingUsingDFS(int[][] grid) {
        setMinTimeToRotten(grid);
        int maxTime = getMaxTimeToRotten(grid);
        if (maxTime == CANNOT_BE_ROTTEN) return CANNOT_BE_ROTTEN;
        return Math.max(0, (maxTime - TIME_OFFSET) + 1);
    }

    private void setMinTimeToRotten(int[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == ROTTEN) {
                    dfsInAllDirections(grid, i, j, TIME_OFFSET);
                }
            }
        }
    }

    private int getMaxTimeToRotten(int[][] grid) {
        int maxTime = 0;
        for (int[] rows : grid) {
            for (int value : rows) {
                if (value == FRESH) return CANNOT_BE_ROTTEN;
                maxTime = Math.max(value, maxTime);
            }
        }
        return maxTime;
    }

    private void dfs(int[][] grid, int i, int j, int time) {
        if (!isValid(grid, i, j) || !isFresh(grid[i][j])) return;
        int originalOrangeValue = grid[i][j];
        grid[i][j] = -1;
        dfsInAllDirections(grid, i, j, time + 1);
        grid[i][j] = getMinTimeToRotten(time, originalOrangeValue);
    }

    private void dfsInAllDirections(int[][] grid, int i, int j, int time) {
        dfs(grid, i + 1, j, time);
        dfs(grid, i - 1, j, time);
        dfs(grid, i, j + 1, time);
        dfs(grid, i, j - 1, time);
    }

    private int getMinTimeToRotten(int time, int originalOrange) {
        return originalOrange == FRESH
                ? time
                : Math.min(originalOrange, time);
    }

    private boolean isFresh(int orange) {
        return orange == FRESH || orange > ROTTEN;
    }

    private boolean isValid(int[][] grid, int i, int j) {
        return i >= 0 && i < grid.length && j >= 0 && j < grid[i].length;
    }

    public static void main(String[] args) {
        RottingOrange rottingOrange = new RottingOrange();

        int[][] grid1 = {
                {2, 1, 1},
                {1, 1, 0},
                {0, 1, 1}
        };
        int[][] grid2 = {
                {2, 1, 0},
                {0, 1, 1},
                {1, 0, 1}
        };
        System.out.println(rottingOrange.orangesRottingUsingBFS(grid1));
        System.out.println(rottingOrange.orangesRottingUsingBFS(grid2));
        System.out.println();

        int[][] grid3 = {
                {2, 1, 1},
                {1, 1, 0},
                {0, 1, 1}
        };
        int[][] grid4 = {
                {2, 1, 0},
                {0, 1, 1},
                {1, 0, 1}
        };
        System.out.println(rottingOrange.orangesRottingUsingDFS(grid3));
        System.out.println(rottingOrange.orangesRottingUsingDFS(grid4));
    }

}
