/*
 * *
 *  * Created by Rafsan Ahmad on 11/7/21, 2:48 PM
 *  * Copyright (c) 2021 . All rights reserved.
 *
 */

package javaclasses.Hashing;

import java.util.Arrays;
import java.util.HashMap;

public class DistinctNumbersInWindow {
    //https://www.interviewbit.com/problems/distinct-numbers-in-window
    /*Problem Description

You are given an array of N integers, A1, A2 ,..., AN and an integer B. Return the of count of distinct
numbers in all windows of size B.

Formally, return an array of size N-B+1 where i'th element in this array contains number of distinct
elements in sequence Ai, Ai+1 ,..., Ai+B-1.

NOTE:  if B > N, return an empty array.

Input Format
First argument is an integer array A
Second argument is an integer B.

Output Format
Return an integer array.

Example Input
Input 1:

 A = [1, 2, 1, 3, 4, 3]
 B = 3
Input 2:

 A = [1, 1, 2, 2]
 B = 1

Example Output
Output 1:

 [2, 3, 3, 2]
Output 2:

 [1, 1, 1, 1]

Example Explanation
Explanation 1:

 A=[1, 2, 1, 3, 4, 3] and B = 3
 All windows of size B are
 [1, 2, 1]
 [2, 1, 3]
 [1, 3, 4]
 [3, 4, 3]
 So, we return an array [2, 3, 3, 2].
Explanation 2:

 Window size is 1, so the output array is [1, 1, 1, 1].
*/

    //Time limit Exceeded
    public int[] dNums(int[] A, int B) {
        int len = A.length;
        int diffLen = (len - B) + 1;
        int[] result = new int[diffLen];
        HashMap<Integer, Integer> map;
        if (B > len) {
            return result;
        }
        int start = 0;
        int end = 0;
        int index = 0;

        while (start < len && end < len) {
            if ((end - start) == B - 1) {
                map = new HashMap<>();
                int total = 0;
                for (int i = start; i <= end; i++) {
                    int val = A[i];
                    int count = map.getOrDefault(val, 0) + 1;
                    map.put(val, count);
                    if (count == 1) {
                        ++total;
                    }
                }
                if (index < diffLen) {
                    result[index] = total;
                    index++;
                }
                start++;
            }
            end++;
        }

        return result;
    }

    //Time complexity - O(n)
    public int[] dNumsOptimized(int[] A, int B) {
        int len = A.length;
        int index = 0;
        int diffLen = (len - B) + 1;
        int[] result = new int[diffLen];
        HashMap<Integer, Integer> map = new HashMap<>();
        if (B > len) {
            return result;
        }
        //First Window size
        for (int i = 0; i < B; i++) {
            map.put(A[i], map.getOrDefault(A[i], 0) + 1);
        }
        result[index] = map.size();
        index++;
        for (int i = B; i < len; i++) {
            // Remove first element of previous window
            // If there was only one occurrence
            if (map.get(A[i - B]) == 1) {
                map.remove(A[i - B]);
            } else {
                // reduce count of the removed element
                map.put(A[i - B], map.get(A[i - B]) - 1);
            }

            // Add new element of current window
            // If this element appears first time,
            // set its count as 1,
            map.put(A[i], map.getOrDefault(A[i], 0) + 1);
            result[index] = map.size();
            index++;
        }
        return result;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 1, 3, 4, 3};
        DistinctNumbersInWindow window = new DistinctNumbersInWindow();
        System.out.println(Arrays.toString(window.dNums(arr, 3)));
        System.out.println(Arrays.toString(window.dNumsOptimized(arr, 3)));
    }
}
