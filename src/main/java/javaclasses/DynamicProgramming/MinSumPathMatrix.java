/*
 * * Min Sum Path Matrix.java
 *  * Created by Rafsan Ahmad on 10/26/21, 2:21 PM
 *  * Copyright (c) 2021 . All rights reserved.
 *
 */

package javaclasses.DynamicProgramming;

public class MinSumPathMatrix {
    //Leetcode 64
    //https://www.interviewbit.com/problems/min-sum-path-in-matrix
    /*Problem Description

Given a 2D integer array A of size M x N, you need to find a path from top left to bottom right which
minimizes the sum of all numbers along its path.

NOTE: You can only move either down or right at any point in time.

Input Format
First and only argument is an 2D integer array A of size M x N.

Output Format
Return a single integer denoting the minimum sum of a path from cell (1, 1) to cell (M, N).

Example Input
Input 1:

 A = [  [1, 3, 2]
        [4, 3, 1]
        [5, 6, 1]
     ]


Example Output
Output 1:
8

Example Explanation
Explanation 1:

 The path is 1 -> 3 -> 2 -> 1 -> 1
 So ( 1 + 3 + 2 + 1 + 1) = 8
*/

    //Recursive Approach - Not optimal
    //Time Complexity: O(m * n)
    public int minPathSumRecursive(int[][] A) {
        int m = A.length - 1;
        int n = A[0].length - 1;
        return minPathSumRecursive(A, m, n);
    }

    public int minPathSumRecursive(int[][] A, int m, int n) {
        if (m < 0 || n < 0) return Integer.MAX_VALUE;
        else if (m == 0 && n == 0) return A[m][n];
        else {
            //Use m-1, n-1 if Diagonal movement is Allowed
            return A[m][n] + Math.min(minPathSumRecursive(A, m - 1, n), minPathSumRecursive(A, m, n - 1));
        }
    }

    //Dynamic Programming
    //Time Complexity = O(mn)
    //Space Complexity = O(mn)
    public int minPathSum(int[][] A) {
        int m = A.length;
        int n = A[0].length;
        int[][] dp = new int[m + 1][n + 1];

        dp[0][0] = A[0][0];
        
        //For first Column
        for (int i = 1; i < m; i++) {
            dp[i][0] += dp[i - 1][0] + A[i][0];
        }
        //For first Row
        for (int j = 1; j < n; j++) {
            dp[0][j] += dp[0][j - 1] + A[0][j];
        }

        //For the rest 2d matrix
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                //Use i-1, j-1 if Diagonal movement is Allowed
                int min = Math.min(dp[i - 1][j], dp[i][j - 1]);
                int sum = A[i][j] + min;
                dp[i][j] = sum;
            }
        }
        return dp[m - 1][n - 1];
    }

    //Dynamic Programming - With space optimized
    //Time Complexity = O(mn)
    //Space Complexity = O(1)
    public int minPathSumSpaceOptimized(int[][] A) {
        int m = A.length;
        int n = A[0].length;

        //For first Column
        for (int i = 1; i < m; i++) {
            A[i][0] += A[i - 1][0];
        }
        //For first Row
        for (int j = 1; j < n; j++) {
            A[0][j] += A[0][j - 1];
        }

        //For rest of the Matrix
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                int min = Math.min(A[i - 1][j], A[i][j - 1]);
                int sum = A[i][j] + min;
                A[i][j] = sum;
            }
        }
        return A[m - 1][n - 1];
    }

    public static void main(String[] args) {
        MinSumPathMatrix matrix = new MinSumPathMatrix();
        int[][] arr = {{1, 3, 2}, {4, 3, 1}, {5, 6, 1}};
        System.out.println(matrix.minPathSumRecursive(arr));
        System.out.println(matrix.minPathSum(arr));
        System.out.println(matrix.minPathSumSpaceOptimized(arr));
    }
}
