/*
 * *
 *  * Created by Rafsan Ahmad on 10/25/21, 9:49 PM
 *  * Copyright (c) 2021 . All rights reserved.
 *
 */

package javaclasses.ArrayList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PascalTriangle {
    //Leetcode 118
    /*Given an integer numRows, return the first numRows of Pascal's triangle.

In Pascal's triangle, each number is the sum of the two numbers directly above it as shown:

1
1 1
1 2 1
1 3 3 1
1 4 6 4 1
1 5 10 10 5 1

Example 1:
Input: numRows = 5
Output: [[1],[1,1],[1,2,1],[1,3,3,1],[1,4,6,4,1]]

Example 2:
Input: numRows = 1
Output: [[1]]*/

    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            List<Integer> nums = new ArrayList<>();
            for (int j = 0; j < i + 1; j++) {
                if (j == 0) nums.add(1);
                else if (j < i) {
                    int num = result.get(i - 1).get(j - 1) + result.get(i - 1).get(j);
                    nums.add(num);
                } else if (j == i) nums.add(1);
            }
            result.add(nums);
        }

        return result;
    }

    //https://www.interviewbit.com/problems/kth-row-of-pascals-triangle/
    /*Given an index k, return the kth row of the Pascal's triangle.
Pascal's triangle: To generate A[C] in row R, sum up A'[C] and A'[C-1] from previous row R - 1.

Example:
Input : k = 3

Return : [1,3,3,1]

Note: k is 0 based. k = 0, corresponds to the row [1].
*/
    public int[] getRow(int A) {
        int[][] result = new int[A + 1][];

        for (int i = 0; i < A + 1; i++) {
            int[] array = new int[i + 1];
            for (int j = 0; j < i + 1; j++) {
                if (j == 0) array[j] = 1;
                else if (j < i) {
                    int val = result[i - 1][j - 1] + result[i - 1][j];
                    array[j] = val;
                } else if (j == i) {
                    array[j] = 1;
                }
            }
            result[i] = array;
        }

        return result[A];
    }

    public static void main(String[] args) {
        PascalTriangle pascalTriangle = new PascalTriangle();
        System.out.println(pascalTriangle.generate(5));
        System.out.println(Arrays.toString(pascalTriangle.getRow(5)));
    }
}
