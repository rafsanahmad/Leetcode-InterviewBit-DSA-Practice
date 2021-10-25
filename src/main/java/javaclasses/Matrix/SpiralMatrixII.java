/*
 * *
 *  * Created by Rafsan Ahmad on 10/25/21, 9:49 PM
 *  * Copyright (c) 2021 . All rights reserved.
 *
 */

package javaclasses.Matrix;

import java.util.ArrayList;
import java.util.List;

public class SpiralMatrixII {
    //Leetcode 59
    /*Given an integer A, generate a square matrix filled with elements from 1 to A2 in spiral order.

Input Format:

The first and the only argument contains an integer, A.
Output Format:

Return a 2-d matrix of size A x A satisfying the spiral order.
Constraints:

1 <= A <= 1000
Examples:

Input 1:
    A = 3

Output 1:
    [   [ 1, 2, 3 ],
        [ 8, 9, 4 ],
        [ 7, 6, 5 ]   ]

Input 2:
    4

Output 2:
    [   [1, 2, 3, 4],
        [12, 13, 14, 5],
        [11, 16, 15, 6],
        [10, 9, 8, 7]   ]*/

    public int[][] generateMatrix(int n) {
        int[][] result = new int[n][n];
        int k = 1;
        int top = 0;
        int bottom = n - 1;
        int left = 0;
        int right = n - 1;
        while (k <= n * n) {
            for (int i = left; i <= right; i++) {
                result[top][i] = k;
                k++;
            }
            top++;
            for (int i = top; i <= bottom; i++) {
                result[i][right] = k;
                k++;
            }
            right--;
            for (int i = right; i >= left; i--) {
                result[bottom][i] = k;
                k++;
            }
            bottom--;
            for (int i = bottom; i >= top; i--) {
                result[i][left] = k;
                k++;
            }
            left++;
        }
        return result;
    }


    //Leetcode 54
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> list = new ArrayList<>();

        int m = matrix.length;
        int n = matrix[0].length;
        int k = 1;
        int top = 0;
        int bottom = m - 1;
        int left = 0;
        int right = n - 1;
        while (k <= m * n) {
            for (int i = left; i <= right; i++) {
                int val = matrix[top][i];
                list.add(val);
                ++k;
            }
            ++top;
            for (int i = top; i <= bottom; i++) {
                int val = matrix[i][right];
                list.add(val);
                ++k;
            }
            --right;
            for (int i = right; i >= left; i--) {
                int val = matrix[bottom][i];
                list.add(val);
                ++k;
            }
            --bottom;
            for (int i = bottom; i >= top; i--) {
                int val = matrix[i][left];
                list.add(val);
                ++k;
            }
            ++left;
        }

        return list;
    }

    public static void main(String[] args) {
        int[][] result = new SpiralMatrixII().generateMatrix(3);
        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[i].length; j++) {
                System.out.print(result[i][j] + " ");
            }
            System.out.println();
        }
    }
}
