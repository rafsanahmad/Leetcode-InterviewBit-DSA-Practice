/*
 * * Rotate Image.java
 *  * Created by Rafsan Ahmad on 10/25/21, 9:49 PM
 *  * Copyright (c) 2021 . All rights reserved.
 *
 */

package javaclasses.Matrix;

public class RotateImage {
    //Leetcode 48
    /*
     * You are given an n x n 2D matrix representing an image, rotate the image by 90 degrees (clockwise).
     * You have to rotate the image in-place, which means you have to modify the input 2D matrix directly.
     * DO NOT allocate another 2D matrix and do the rotation.
     * Example 1:
     * Input: matrix = [[1,2,3],[4,5,6],[7,8,9]]
     * Output: [[7,4,1],[8,5,2],[9,6,3]]
     * Example 2:
     * Input: matrix = [[1]]
     * Output: [[1]]
     * Example 3:
     * Input: matrix = [[1,2],[3,4]]
     * Output: [[3,1],[4,2]]

 1|2|3    Rotate    7|4|1
 4|5|6   ------->   8|5|2
 7|8|9              9|6|3
     */

    public void rotate(int[][] matrix) {
        int len = matrix.length;

        //Transpose matrix
        //Swap matrix[i][j] with matrix[j][i]
        for (int i = 0; i < len; i++) {
            for (int j = i; j < len; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }

        //Flip Horizontally
        //Swap martix[i][j] with matrix[i][len-1-j]
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len / 2; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[i][len - 1 - j];
                matrix[i][len - 1 - j] = temp;
            }
        }
    }

    public static void main(String[] args) {
        RotateImage image = new RotateImage();
        int[][] arr = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}};
        image.rotate(arr);
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }
}
