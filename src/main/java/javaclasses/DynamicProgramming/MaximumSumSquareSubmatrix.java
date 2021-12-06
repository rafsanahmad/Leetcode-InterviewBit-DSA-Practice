/*
 * * Maximum Sum Square Submatrix.java
 *  * Created by Rafsan Ahmad on 10/25/21, 9:50 PM
 *  * Copyright (c) 2021 . All rights reserved.
 *
 */

package javaclasses.DynamicProgramming;

public class MaximumSumSquareSubmatrix {
    //https://www.interviewbit.com/problems/maximum-sum-square-submatrix/
    /*
    Problem Description

Given a 2D integer matrix A of size N x N find a B x B submatrix where B<= N and B>= 1,
such that sum of all the elements in submatrix is maximum.

Input Format
First arguement is an 2D integer matrix A.
Second argument is an integer B.

Output Format
Return a single integer denoting the maximum sum of submatrix of size B x B.

Example Input
Input 1:

 A = [
        [1, 1, 1, 1, 1]
        [2, 2, 2, 2, 2]
        [3, 8, 6, 7, 3]
        [4, 4, 4, 4, 4]
        [5, 5, 5, 5, 5]
     ]
 B = 3

Input 2:

 A = [
        [2, 2]
        [2, 2]
    ]
 B = 2


Example Output
Output 1:
 48

Output 2:
 8

Example xplanation
Explanation 1:
    Maximum sum 3 x 3 matrix is
    8 6 7
    4 4 4
    5 5 5
    Sum = 48

Explanation 2:
 Maximum sum 2 x 2 matrix is
  2 2
  2 2
  Sum = 8
*/
    //Using DP
    public int solve(int[][] A, int B) {
        int r = A.length;
        int c = A[0].length;
        int sum = Integer.MIN_VALUE;
        int[][] dp = new int[r + 1][c + 1];
        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {
                dp[i][j] = A[i - 1][j - 1] + dp[i - 1][j] + dp[i][j - 1] - dp[i - 1][j - 1];
            }
        }
        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {
                if (i - B >= 0 && j - B >= 0) {
                    sum = Math.max(sum, dp[i][j] - dp[i - B][j] - dp[i][j - B] + dp[i - B][j - B]);
                }
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        int[][] arr = {
                {1, 1, 1, 1, 1},
                {2, 2, 2, 2, 2},
                {3, 8, 6, 7, 3},
                {4, 4, 4, 4, 4},
                {5, 5, 5, 5, 5}
        };
        MaximumSumSquareSubmatrix submatrix = new MaximumSumSquareSubmatrix();
        System.out.println(submatrix.solve(arr, 3));
    }

}
