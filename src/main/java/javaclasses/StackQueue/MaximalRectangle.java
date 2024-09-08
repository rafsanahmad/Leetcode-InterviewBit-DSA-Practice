/*
 * *
 *  * Maximal Rectangle.java
 *  * Created by Rafsan Ahmad on 9/8/24, 9:59 PM
 *  * Copyright (c) 2024 . All rights reserved.
 *  *
 *
 */

package javaclasses.StackQueue;

import java.util.Stack;

public class MaximalRectangle {
    //https://leetcode.com/problems/maximal-rectangle/description/
    //res/maximal_rectangle.jpg
    /*Given a rows x cols binary matrix filled with 0's and 1's, find the largest rectangle
    containing only 1's and return its area.

Example 1:
Input: matrix = [
    ["1","0","1","0","0"],
    ["1","0","1","1","1"],
    ["1","1","1","1","1"],
    ["1","0","0","1","0"]
]
Output: 6
Explanation: The maximal rectangle is shown in the above picture.

Example 2:
Input: matrix = [["0"]]
Output: 0
Example 3:

Input: matrix = [["1"]]
Output: 1

Constraints:
rows == matrix.length
cols == matrix[i].length
1 <= row, cols <= 200
matrix[i][j] is '0' or '1'.*/

    public int maximalRectangle(char[][] matrix) {
        int res = Integer.MIN_VALUE;
        int row = matrix.length;
        int col = matrix[0].length;
        int[][] mat = new int[row][col];

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                mat[i][j] = matrix[i][j] - '0';
            }
        }

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (i > 0 && mat[i][j] == 1) {
                    mat[i][j] += mat[i - 1][j];
                }
            }
            res = Math.max(res, largestRectangleArea(mat[i]));
        }

        return res;
    }

    int largestRectangleArea(int[] heights) {
        Stack<Integer> st = new Stack<>();
        int maxA = 0;
        int n = heights.length;
        for (int i = 0; i <= n; i++) {
            int h = i == n ? 0 : heights[i];
            while (!st.empty() && (heights[st.peek()] >= h)) {
                int height = heights[st.peek()];
                st.pop();
                int width = st.empty() ? i : i - st.peek() - 1;
                maxA = Math.max(maxA, width * height);
            }
            st.push(i);
        }
        return maxA;
    }

    public static void main(String[] args) {
        MaximalRectangle maximalRectangle = new MaximalRectangle();
        char[][] matrix = {
                {'1', '0', '1', '0', '0'},
                {'1', '0', '1', '1', '1'},
                {'1', '1', '1', '1', '1'},
                {'1', '0', '0', '1', '0'}
        };

        System.out.println(maximalRectangle.maximalRectangle(matrix));
    }
}
