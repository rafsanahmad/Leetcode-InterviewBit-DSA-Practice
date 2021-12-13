/*
 * *
 *  * Shift 2D Grid.java
 *  * Created by Rafsan Ahmad on 12/14/21, 12:23 AM
 *  * Copyright (c) 2021 . All rights reserved.
 *
 */

package javaclasses.Matrix;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Shift2D_Grid {
    //Leetcode 1260
    /*Given a 2D grid of size m x n and an integer k. You need to shift the grid k times.

In one shift operation:

Element at grid[i][j] moves to grid[i][j + 1].
Element at grid[i][n - 1] moves to grid[i + 1][0].
Element at grid[m - 1][n - 1] moves to grid[0][0].
Return the 2D grid after applying shift operation k times.

Example 1:
    1 2 3       9 1 2
    4 5 6   ->  3 4 5
    7 8 9       6 7 8

Input: grid = [[1,2,3],[4,5,6],[7,8,9]], k = 1
Output: [[9,1,2],[3,4,5],[6,7,8]]

Example 2:
Input: grid = [[3,8,1,9],[19,7,2,5],[4,6,11,10],[12,0,21,13]], k = 4
Output: [[12,0,21,13],[3,8,1,9],[19,7,2,5],[4,6,11,10]]

Example 3:
Input: grid = [[1,2,3],[4,5,6],[7,8,9]], k = 9
Output: [[1,2,3],[4,5,6],[7,8,9]]*/

    public List<List<Integer>> shiftGrid(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] temp = new int[m][n]; // took temp grid
        int mod = m * n;
        k = k % mod; // minimize the k value
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int p = j + k; // defines which col
                int r = p / (n); // defines which row
                if (p < n) {
                    temp[i][p] = grid[i][j];
                } else {
                    temp[(i + r) % n][p % n] = grid[i][j];
                }
            }
        }
        // making temp grid into list
        List<List<Integer>> result = new LinkedList<>();
        for (int[] t : temp) {
            LinkedList<Integer> c = new LinkedList<>();
            for (int i : t) {
                c.add(i);
            }
            result.add(c);
        }
        return result;
    }

    public List<List<Integer>> shiftGridApproach2(int[][] grid, int k) {
        int m = grid.length, n = grid[0].length, mod = m * n;
        k = k % mod;
        Integer[][] newGrid = new Integer[m][n];
        for (int i = 0; i < mod; i++) {
            int pre = (i - k + mod) % mod;
            newGrid[i / n][i % n] = grid[pre / n][pre % n];
        }

        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            ans.add(Arrays.asList(newGrid[i]));
        }
        return ans;
    }

    public static void main(String[] args) {
        Shift2D_Grid grid = new Shift2D_Grid();
        int[][] arr = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        List<List<Integer>> result = grid.shiftGrid(arr, 9);
        List<List<Integer>> result2 = grid.shiftGridApproach2(arr, 9);

        //Print list
        for (int i = 0; i < result.size(); i++) {
            for (int j = 0; j < result.get(i).size(); j++) {
                System.out.print(result.get(i).get(j) + " ");
            }
            System.out.println();
        }
        System.out.println();
        //Print list
        for (int i = 0; i < result2.size(); i++) {
            for (int j = 0; j < result2.get(i).size(); j++) {
                System.out.print(result2.get(i).get(j) + " ");
            }
            System.out.println();
        }
    }
}
