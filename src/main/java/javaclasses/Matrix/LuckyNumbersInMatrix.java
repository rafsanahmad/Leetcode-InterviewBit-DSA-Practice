/*
 * *
 *  * Lucky Numbers In Matrix.java
 *  * Created by Rafsan Ahmad on 12/10/21, 3:43 AM
 *  * Copyright (c) 2021 . All rights reserved.
 *
 */

package javaclasses.Matrix;

import java.util.ArrayList;
import java.util.List;

public class LuckyNumbersInMatrix {
    //Leetcode 1380
    /*Given an m x n matrix of distinct numbers, return all lucky numbers in the matrix in any order.

A lucky number is an element of the matrix such that it is the minimum element in its row and maximum in its column.


Example 1:
Input: matrix = [[3,7,8],[9,11,13],[15,16,17]]
Output: [15]
Explanation: 15 is the only lucky number since it is the minimum in its row and the maximum in its column

Example 2:
Input: matrix = [[1,10,4,2],[9,3,8,7],[15,16,17,12]]
Output: [12]
Explanation: 12 is the only lucky number since it is the minimum in its row and the maximum in its column.

Example 3:
Input: matrix = [[7,8],[1,2]]
Output: [7]
Explanation: 7 is the only lucky number since it is the minimum in its row and the maximum in its column.

Example 4:
Input: matrix = [[3,6],[7,1],[5,2],[4,8]]
Output: []
Explanation: There is no lucky number.*/

    public List<Integer> luckyNumbers(int[][] matrix) {
        List<Integer> result = new ArrayList<>();
        int min;
        int minColumn = 0;
        for (int i = 0; i < matrix.length; i++) {
            min = matrix[i][0];
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] < min) {
                    min = matrix[i][j];
                    minColumn = j;
                }
            }
            //Check if min value is maximum in column
            boolean check = true;
            for (int k = 0; k < matrix.length; k++) {
                if (min < matrix[k][minColumn]) {
                    check = false;
                    break;
                }
            }
            if (check) result.add(min);
        }

        return result;
    }

    public static void main(String[] args) {
        LuckyNumbersInMatrix luckyNumbersInMatrix = new LuckyNumbersInMatrix();
        int[][] matrix = {{3, 7, 8}, {9, 11, 13}, {15, 16, 17}};
        System.out.println(luckyNumbersInMatrix.luckyNumbers(matrix));
    }
}
