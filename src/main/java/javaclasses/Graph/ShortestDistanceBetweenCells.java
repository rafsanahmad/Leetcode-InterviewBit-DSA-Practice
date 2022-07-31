/*
 * *
 *  * Shortest distance between two cells in a matrix or grid.java
 *  * Created by Rafsan Ahmad on 7/30/22, 8:53 PM
 *  * Copyright (c) 2022 . All rights reserved.
 *
 */

package javaclasses.Graph;

import java.util.LinkedList;
import java.util.Queue;

public class ShortestDistanceBetweenCells {
    /*Given a matrix of N*M order. Find the shortest distance from a source cell to a destination cell, traversing
    through limited cells only. Also you can move only up, down, left and right. If found output the distance else -1.
s represents ‘source’
d represents ‘destination’
* represents cell you can travel
0 represents cell you can not travel
This problem is meant for single source and destination.
Examples:


Input : {'0', '*', '0', 's'},
        {'*', '0', '*', '*'},
        {'0', '*', '*', '*'},
        {'d', '*', '*', '*'}
Output : 6

Input :  {'0', '*', '0', 's'},
         {'*', '0', '*', '*'},
         {'0', '*', '*', '*'},
         {'d', '0', '0', '0'}
Output :  -1
 */

    /*The idea is to BFS (breadth first search) on matrix cells. Note that we can always use BFS to find shortest path
    if graph is unweighted.

Store each cell as a node with their row, column values and distance from source cell.
Start BFS with source cell.
Make a visited array with all having “false” values except ‘0’cells which are assigned “true” values as they can not be
traversed.
Keep updating distance from source value in each move.
Return distance when destination is met, else return -1 (no path exists in between source and destination).
*/

    // QItem for current location and distance from source location
    static class QItem {
        int row;
        int col;
        int dist;

        public QItem(int row, int col, int dist) {
            this.row = row;
            this.col = col;
            this.dist = dist;
        }
    }

    private static int minDistance(char[][] grid) {
        QItem source = new QItem(0, 0, 0);

        // To keep track of visited QItems. Marking blocked cells as visited.
        firstLoop:
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {

                // Finding source
                if (grid[i][j] == 's') {
                    source.row = i;
                    source.col = j;
                    break firstLoop;
                }
            }
        }

        // applying BFS on matrix cells starting from source
        Queue<QItem> queue = new LinkedList<>();
        queue.add(new QItem(source.row, source.col, 0));

        boolean[][] visited
                = new boolean[grid.length][grid[0].length];
        visited[source.row][source.col] = true;

        while (!queue.isEmpty()) {
            QItem p = queue.remove();

            // Destination found;
            if (grid[p.row][p.col] == 'd')
                return p.dist;

            // moving up
            if (isValid(p.row - 1, p.col, grid, visited)) {
                queue.add(new QItem(p.row - 1, p.col, p.dist + 1));
                visited[p.row - 1][p.col] = true;
            }

            // moving down
            if (isValid(p.row + 1, p.col, grid, visited)) {
                queue.add(new QItem(p.row + 1, p.col, p.dist + 1));
                visited[p.row + 1][p.col] = true;
            }

            // moving left
            if (isValid(p.row, p.col - 1, grid, visited)) {
                queue.add(new QItem(p.row, p.col - 1, p.dist + 1));
                visited[p.row][p.col - 1] = true;
            }

            // moving right
            if (isValid(p.row, p.col + 1, grid, visited)) {
                queue.add(new QItem(p.row, p.col + 1, p.dist + 1));
                visited[p.row][p.col + 1] = true;
            }
        }
        return -1;
    }

    // checking where it's valid or not
    private static boolean isValid(int x, int y,
                                   char[][] grid,
                                   boolean[][] visited) {
        if (x >= 0 && y >= 0 && x < grid.length
                && y < grid[0].length && grid[x][y] != '0'
                && !visited[x][y]) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        char[][] grid = {
                {'0', '*', '0', 's'},
                {'*', '0', '*', '*'},
                {'0', '*', '*', '*'},
                {'d', '*', '*', '*'}
        };

        System.out.println(minDistance(grid));
    }
}
