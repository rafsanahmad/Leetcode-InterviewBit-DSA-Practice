/*
 * * Wave Array.java
 *  * Created by Rafsan Ahmad on 10/25/21, 9:49 PM
 *  * Copyright (c) 2021 . All rights reserved.
 *
 */

package javaclasses.Array;

import java.util.Arrays;

public class WaveArray {
    //https://www.interviewbit.com/problems/wave-array/
    /*Problem Description
Given an array of integers A, sort the array into a wave like array and return it, In other words,
arrange the elements into a sequence such that
a1 >= a2 <= a3 >= a4 <= a5.....
NOTE : If there are multiple answers possible, return the one that's lexicographically smallest.

Input Format
First argument is an integer array A.

Output Format
Return an array arranged in the sequence as described.

Example Input
Input 1:
A = [1, 2, 3, 4]

Input 2:
A = [1, 2]

Example Output
Output 1:
[2, 1, 4, 3]

Output 2:
[2, 1]

Example Explanation
Explanation 1:
One possible answer : [2, 1, 4, 3]
Another possible answer : [4, 1, 3, 2]
First answer is lexicographically smallest. So, return [2, 1, 4, 3].

Explanation 2:
Only possible answer is [2, 1].*/

    public int[] wave(int[] A) {
        int[] result = new int[A.length];
        int len = A.length;
        Arrays.sort(A);
        for (int i = 0; i < len - 1; i = i + 2) {
            int temp = A[i + 1];
            result[i + 1] = A[i];
            result[i] = temp;
        }
        if (len % 2 != 0) result[len - 1] = A[len - 1];
        return result;
    }

    public static void main(String[] args) {
        int[] ar = {1, 2, 3, 4, 5};
        System.out.println(Arrays.toString(new WaveArray().wave(ar)));
    }
}
