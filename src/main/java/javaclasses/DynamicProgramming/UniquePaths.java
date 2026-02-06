/*
 * * Unique Paths.java
 *  * Created by Rafsan Ahmad on 10/25/21, 9:50 PM
 *  * Copyright (c) 2021 . All rights reserved.
 *
 */

package javaclasses.DynamicProgramming;

public class UniquePaths {
    //Leetcode 62
    /*A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).
    //src/unique_paths.png
The robot can only move either down or right at any point in time. The robot is trying to reach
the bottom-right corner of the grid (marked 'Finish' in the diagram below).

How many possible unique paths are there?

Example 1:
Input: m = 3, n = 7
Output: 28

Example 2:
Input: m = 3, n = 2
Output: 3
Explanation:
From the top-left corner, there are a total of 3 ways to reach the bottom-right corner:
1. Right -> Down -> Down
2. Down -> Down -> Right
3. Down -> Right -> Down

Example 3:
Input: m = 7, n = 3
Output: 28

Example 4:
Input: m = 3, n = 3
Output: 6

Using DP we can solve the problem
Time complexity = O(mn).
Space complexity = O(mn)

*/

    public int uniquePaths(int m, int n) {
        int[][] memo = new int[m][n];
        return uniquePathsHelper(m, n, 0, 0, memo);
    }

    private int uniquePathsHelper(int m, int n, int currX, int currY, int[][] memo) {
        // Out of bounds
        if (currX >= m || currY >= n) {
            return 0;
        }
        // Reached bottom or right edge
        if (currX == m - 1 || currY == n - 1) {
            return 1;
        }
        // Already computed
        if (memo[currX][currY] > 0) {
            return memo[currX][currY];
        }

        // Recursive calls: move down or right
        memo[currX][currY] =
                uniquePathsHelper(m, n, currX + 1, currY, memo)
                        + uniquePathsHelper(m, n, currX, currY + 1, memo);

        return memo[currX][currY];
    }

    int uniquePathsDp(int m, int n) {
        // Create a 2D table to store results
        // of subproblems
        int[][] count = new int[m][n];

        // Count of paths to reach any cell in
        // first column is 1
        for (int i = 0; i < m; i++)
            count[i][0] = 1;

        // Count of paths to reach any cell in
        // first row is 1
        for (int j = 0; j < n; j++)
            count[0][j] = 1;

        // Calculate count of paths for other
        // cells in bottom-up manner using
        // the recursive solution
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++)

                // By uncommenting the last part the
                // code calculates the total possible paths
                // if the diagonal Movements are allowed
                count[i][j] = count[i - 1][j] + count[i][j - 1]; //+ count[i-1][j-1];
        }
        return count[m - 1][n - 1];
    }

    public static void main(String[] args) {
        UniquePaths up = new UniquePaths();
        System.out.println(up.uniquePaths(3, 7));
        System.out.println(up.uniquePathsDp(3, 7));
    }
}
