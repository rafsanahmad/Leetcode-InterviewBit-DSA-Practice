package javaclasses.Matrix;

import java.util.HashSet;

public class SetMatrixZeroes {
    //Leetcode 73
    /*
    * Given an m x n integer matrix matrix, if an element is 0, set its entire row and column to 0's,
    and return the matrix.

You must do it in place.
Example 1:
Input: matrix = [[1,1,1],[1,0,1],[1,1,1]]
Output: [[1,0,1],[0,0,0],[1,0,1]]*/

    /*  1 1 1           1 0 1
        1 0 1  ----->   0 0 0
        1 1 1           1 0 1
    * */

    public void setZeroes(int[][] matrix) {
        HashSet<Integer> rows = new HashSet<>();
        HashSet<Integer> columns = new HashSet<>();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == 0) {
                    rows.add(i);
                    columns.add(j);
                }
            }
        }

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (rows.contains(i) || columns.contains(j)) {
                    matrix[i][j] = 0;
                }
            }
        }
    }
}
