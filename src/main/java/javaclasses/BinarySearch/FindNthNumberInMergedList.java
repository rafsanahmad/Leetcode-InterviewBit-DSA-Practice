/*
 * *
 *  * Find the Nth number in the merged and sorted lists of given ranges.java
 *  * Created by Rafsan Ahmad on 6/20/22, 12:10 PM
 *  * Copyright (c) 2022 . All rights reserved.
 *
 */

package javaclasses.BinarySearch;

public class FindNthNumberInMergedList {
    /*Given two integer array, L and R and an integer N. Each range from the given array denote that every number in
    the range [L[i], R[i]] is present. The task is to calculate the N-th(0-based indexing) element when numbers from given
    ranges are ordered in their sorted order.

Examples:

Input: L = {1, 5}, R = {3, 7}, N = 4
Output: 6
Explanation: The numbers present are {1, 2, 3, 5, 6, 7}. Therefore 4-th element(0-indexed) is 6.

Input: L = {1, 3}, R = {4, 5}, N = 3
Output: 3
Explanation: The numbers present are {1, 2, 3, 4, 3, 4, 5} and their sorted order is {1, 2, 3, 3, 4, 4, 5}.
Therefore 3-rd element(0-indexed) is 3.

Approach: The task can be solved with the help of a binary search over the answer.
Follow the below steps to solve the problem:

Calculate two variables min and max which store the minimum element from the L array and maximum element from the R array.
The range of the binary search will be [min, max].
For each mid = (min + max) / 2 calculate the position of the current element.
To calculate the position, iterate over all ranges and make a variable t = 0 to store the position. Check below two conditions
if L[i] >= mid
If mid <= R[i], update t += mid – L[i] + 1.
else t += R[i] – L[i] + 1
The binary search range can be updated as
if(t > n) max = mid – 1.
else min = mid + 1.
The final answer will be stored in the variable min.*/

    public static long nthElement(int[] L, int[] R, int n) {
        // Store the size of the ranges
        int K = L.length;

        // Calculate the max and min values
        long min = 2000000000, max = -2000000000;
        for (int i = 0; i < K; i++) {
            if (L[i] < min)
                min = L[i];
            if (R[i] > max)
                max = R[i];
        }

        // Do a binary search over answer
        while (min <= max) {
            long mid = (min + max) / 2;
            long t = 0;

            for (int i = 0; i < K; i++) {
                if (mid >= L[i]) {
                    if (mid <= R[i]) {
                        t += mid - L[i] + 1;
                    } else {
                        t += R[i] - L[i] + 1;
                    }
                }
            }

            // Update the binary Search range.
            if (t > n) {
                max = mid - 1;
            } else {
                min = mid + 1;
            }
        }
        return min;
    }

    public static void main(String args[]) {
        int[] L = {1, 5}, R = {3, 7};
        int N = 4;
        System.out.println(nthElement(L, R, N));
    }
}
