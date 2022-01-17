/*
 * *
 *  * Search a 2d Matrix.java
 *  * Created by Rafsan Ahmad on 1/17/22, 3:21 PM
 *  * Copyright (c) 2022 . All rights reserved.
 *
 */

package javaclasses.Matrix;

public class Search2dMatrix {
    //Leetcode 74
    /*Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following
    properties:

Integers in each row are sorted from left to right.
The first integer of each row is greater than the last integer of the previous row.


Example 1:
    1  | 3  | 5  |7
    10 | 11 | 16 | 20
    23 | 30 | 34 | 60

Input: matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 3
Output: true

Example 2:

Input: matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 13
Output: false
*/
    public boolean searchMatrix(int[][] matrix, int target) {
        int len = matrix[0].length;
        for (int i = 0; i < matrix.length; i++) {
            int first = matrix[i][0];
            int last = matrix[i][len - 1];
            if (target >= first && target <= last) {
                //In range
                int left = 0;
                int right = len - 1;
                while (left <= right) {
                    int mid = (left + right) / 2;
                    if (matrix[i][mid] == target) {
                        return true;
                    } else if (matrix[i][mid] <= target) {
                        left = mid + 1;
                    } else {
                        right = mid - 1;
                    }
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Search2dMatrix matrix = new Search2dMatrix();
        int[][] arr = {{1, 3, 5, 7}, {10, 11, 16, 20}, {23, 30, 34, 60}};
        System.out.println(matrix.searchMatrix(arr, 3));
        System.out.println(matrix.searchMatrix(arr, 13));

        int[][] arr2 = {{1, 3}};
        System.out.println(matrix.searchMatrix(arr2, 3));
    }
}
