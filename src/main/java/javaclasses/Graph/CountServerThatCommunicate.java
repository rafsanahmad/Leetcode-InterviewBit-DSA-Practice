/*
 * *
 *  * Count Servers that Communicate.java
 *  * Created by Rafsan Ahmad on 7/5/22, 11:06 PM
 *  * Copyright (c) 2022 . All rights reserved.
 *
 */

package javaclasses.Graph;

import java.util.HashMap;

public class CountServerThatCommunicate {
    //Leetcode 1267
    //res/leetcode-1267.jpeg
    /*You are given a map of a server center, represented as a m * n integer matrix grid, where 1 means that on that
    cell there is a server and 0 means that it is no server. Two servers are said to communicate if they are on the
    same row or on the same column.

Return the number of servers that communicate with any other server.

Example 1:
Input: grid = [[1,0],[0,1]]
Output: 0
Explanation: No servers can communicate with others.

Example 2:
Input: grid = [[1,0],[1,1]]
Output: 3
Explanation: All three servers can communicate with at least one other server.

Example 3:
Input: grid = [[1,1,0,0],[0,0,1,0],[0,0,1,0],[0,0,0,1]]
Output: 4
Explanation: The two servers in the first row can communicate with each other. The two servers in the third column can
communicate with each other. The server at right bottom corner can't communicate with any other server.*/

    //Approach 1: using DFS
    //dfs travel along row and column and just turn 1 into 0
    public int countServersUsingDFS(int[][] grid) {
        int row = grid.length;
        int col = grid[0].length;
        boolean[][] visited = new boolean[row + 1][col + 1];
        int total = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == 1) {
                    int count = dfs(grid, i, j, visited);
                    if (count > 1) total += count;
                }
                visited[i][j] = true;
            }
        }
        return total;
    }

    private int dfs(int[][] grid, int i, int j, boolean[][] visited) {
        if (visited[i][j] || grid[i][j] == 0) return 0; // skip 0 or visited[i][j]

        visited[i][j] = true;
        int count = 1;
        for (int x = 0; x < grid.length; x++) { // dfs on row
            count += dfs(grid, x, j, visited);
        }
        for (int y = 0; y < grid[0].length; y++) { // dfs on col
            count += dfs(grid, i, y, visited);
        }
        return count;
    }

    //Approach 2: Using count map
    public int countServers(int[][] grid) {
        HashMap<Integer, Integer> rowCount = new HashMap();
        HashMap<Integer, Integer> colCount = new HashMap();
        int totalServer = 0;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    totalServer++;
                    rowCount.put(i, rowCount.getOrDefault(i, 0) + 1);
                    colCount.put(j, colCount.getOrDefault(j, 0) + 1);
                }
            }
        }

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    if (rowCount.get(i) == 1 && colCount.get(j) == 1) totalServer--;
                }
            }
        }
        return totalServer;
    }

    public static void main(String[] args) {
        CountServerThatCommunicate communicate = new CountServerThatCommunicate();
        int[][] grid = {{1, 1, 0, 0}, {0, 0, 1, 0}, {0, 0, 1, 0}, {0, 0, 0, 1}};
        int[][] grid2 = {{1, 0}, {0, 1}};
        System.out.println(communicate.countServersUsingDFS(grid));
        System.out.println(communicate.countServersUsingDFS(grid2));

        int[][] grid3 = {{1, 1, 0, 0}, {0, 0, 1, 0}, {0, 0, 1, 0}, {0, 0, 0, 1}};
        int[][] grid4 = {{1, 0}, {0, 1}};
        System.out.println(communicate.countServers(grid3));
        System.out.println(communicate.countServers(grid4));
    }
}
