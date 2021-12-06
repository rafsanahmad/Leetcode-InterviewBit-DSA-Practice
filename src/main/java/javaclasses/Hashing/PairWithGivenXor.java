/*
 * * Pair With Given Xor.java
 *  * Created by Rafsan Ahmad on 11/9/21, 1:39 AM
 *  * Copyright (c) 2021 . All rights reserved.
 *
 */

package javaclasses.Hashing;

import java.util.HashMap;
import java.util.Map;

public class PairWithGivenXor {
    //https://www.interviewbit.com/problems/pairs-with-given-xor/
    /*Problem Description

Given an 1D integer array A containing N distinct integers.

Find the number of unique pairs of integers in the array whose XOR is equal to B.

NOTE:
Pair (a, b) and (b, a) is considered to be same and should be counted once.

Problem Constraints
1 <= N <= 10^5
1 <= A[i], B <= 10^7

Input Format
First argument is an 1D integer array A.
Second argument is an integer B.

Output Format
Return a single integer denoting the number of unique pairs of integers in the array A whose XOR is equal to B.


Example Input
Input 1:
 A = [5, 4, 10, 15, 7, 6]
 B = 5

Input 2:
 A = [3, 6, 8, 10, 15, 50]
 B = 5

Example Output
Output 1:
 1
Output 2:
 2

Example Explanation
Explanation 1:
 (10 ^ 15) = 5

Explanation 2:
 (3 ^ 6) = 5 and (10 ^ 15) = 5
*/
    public int solve(int[] A, int B) {
        /*if A^B = C then A^C = B and B^C=A. This is mathematical nature of xor.

Now the problem is similar to finding pair with given sum. Just do xor instead of sum.

Hash[B^A[i]] and in another loop find A[i] if indeices of these two numbers are different, add it to your set.

return set.size();*/
        int result = 0;
        int len = A.length;
        // create empty map that stores counts of
        // individual elements of array.
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < len; i++) {
            int curr_xor = B ^ A[i];

            // If there exist an element in map m
            // with XOR equals to x^arr[i], that means
            // there exist an element such that the
            // XOR of element with arr[i] is equal to
            // x, then increment count.
            if (map.containsKey(curr_xor))
                result += map.get(curr_xor);

            // Increment count of current element
            if (map.containsKey(A[i])) {
                map.put(A[i], map.get(A[i]) + 1);
            } else {
                map.put(A[i], 1);
            }
        }
        // return total count
        return result;
    }

    public static void main(String[] args) {
        PairWithGivenXor xor = new PairWithGivenXor();
        int[] arr = {3, 6, 8, 10, 15, 50};
        System.out.println(xor.solve(arr, 5));
    }
}
