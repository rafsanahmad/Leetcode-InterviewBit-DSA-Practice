/*
 * *
 *  * Maximum Matrix Sum.java
 *  * Created by Rafsan Ahmad on 2/18/22, 12:27 AM
 *  * Copyright (c) 2022 . All rights reserved.
 *
 */

package javaclasses.GreedyAlgorithm;

public class MaximumMatrixSum {
    //Leetcode 1975
    /*You are given an n x n integer matrix. You can do the following operation any number of times:

Choose any two adjacent elements of matrix and multiply each of them by -1.
Two elements are considered adjacent if and only if they share a border.

Your goal is to maximize the summation of the matrix's elements. Return the maximum sum of the matrix's elements using
the operation mentioned above.



Example 1:
//res/matrix-sum.png
Input: matrix = [[1,-1],[-1,1]]
Output: 4
Explanation: We can follow the following steps to reach sum equals 4:
- Multiply the 2 elements in the first row by -1.
- Multiply the 2 elements in the first column by -1.


Example 2:
Input: matrix = [[1,2,3],[-1,-2,-3],[1,2,3]]
Output: 16
Explanation: We can follow the following step to reach sum equals 16:
- Multiply the 2 last elements in the second row by -1.*/

    public long maxMatrixSum(int[][] matrix) {
        int negativeCount = 0;
        long result = 0;
        int min = Integer.MAX_VALUE;

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] < 0)
                    negativeCount++;

                min = Math.min(min, Math.abs(matrix[i][j]));
                result = result + Math.abs(matrix[i][j]);
            }
        }

        if (negativeCount % 2 != 0) {
            result = result - (2 * min);
        }

        return result;
    }

    public static void main(String[] args) {
        MaximumMatrixSum sum = new MaximumMatrixSum();
        int[][] matrix = {{1, 2, 3}, {-1, -2, -3}, {1, 2, 3}};
        System.out.println(sum.maxMatrixSum(matrix));
    }
}
