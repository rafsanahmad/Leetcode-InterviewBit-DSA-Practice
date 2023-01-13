/*
 * *
 *  * Number Of Islands.java
 *  * Created by Rafsan Ahmad on 4/24/22, 4:38 AM
 *  * Copyright (c) 2023 . All rights reserved.
 *
 */

package javaclasses.Graph.DFS;

public class NumberOfIslands {
    //Leetcode 200
    /*Given an m x n 2D binary grid grid which represents a map of '1's (land) and '0's (water),
    return the number of islands.

An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically.
You may assume all four edges of the grid are all surrounded by water.

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
*/
    int row;
    int column;

    public int numIslands(char[][] grid) {
        int count = 0;
        row = grid.length;
        column = grid[0].length;
        if (row == 0 || column == 0) {
            return 0;
        }

        //Go-through entire matrix and DFS into when we found '1'
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                if (grid[i][j] == '1') {
                    dfs(grid, i, j);
                    ++count;
                }
            }
        }

        return count;
    }

    public void dfs(char[][] array, int i, int j) {
        //If we are out of bound or on '0' no need to DFS down
        if (i < 0 || j < 0 || i >= row || j >= column || array[i][j] == '0') {
            return;
        }
        //Mark the node as visited 1-->0
        array[i][j] = '0';
        //All the possible path we can dfs
        dfs(array, i + 1, j);
        dfs(array, i - 1, j);
        dfs(array, i, j + 1);
        dfs(array, i, j - 1);
    }

    public static void main(String[] args) {
        NumberOfIslands islands = new NumberOfIslands();
        char[][] array =
                {{'1', '1', '0', '0', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '1', '0', '0'},
                {'0', '0', '0', '1', '1'}};
        System.out.println(islands.numIslands(array));
    }

}
