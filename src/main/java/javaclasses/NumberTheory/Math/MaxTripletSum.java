/*
 * * Max Triplet Sum.java
 *  * Created by Rafsan Ahmad on 10/25/21, 9:50 PM
 *  * Copyright (c) 2021 . All rights reserved.
 *
 */

package javaclasses.NumberTheory.Math;

import java.util.TreeSet;

public class MaxTripletSum {
    //https://www.interviewbit.com/problems/maximum-sum-triplet/
    /*Problem Description

Given an array A containing N integers.

You need to find the maximum sum of triplet ( Ai + Aj + Ak ) such that 0 <= i < j < k < N and A[i] < A[j] < A[k].

If no such triplet exist return 0.

Input Format
First argument is an integer array A.

Output Format
Return a single integer denoting the maximum sum of triplet as described in the question.

Example Input
Input 1:
 A = [2, 5, 3, 1, 4, 9]

Input 2:
 A = [1, 2, 3]

Example Output
Output 1:
 16

Output 2:
 6

Example Explanation
Explanation 1:
 All possible triplets are:-
    2 3 4 => sum = 9
    2 5 9 => sum = 16
    2 3 9 => sum = 14
    3 4 9 => sum = 16
    1 4 9 => sum = 14
  Maximum sum = 16

Explanation 2:
 All possible triplets are:-
    1 2 3 => sum = 6
 Maximum sum = 6
*/
    /*Approach 1
Instead of Traversing through every triplets with three nested loops, we can traverse through two nested loops.
While traversing through each number(assume as middle element(aj)), find maximum number(ai) smaller than aj
preceding it and maximum number(ak) greater than aj beyond it. Now after that, update the maximum answer with
calculated sum of ai + aj + ak
*/
    //Time complexity: O(n2)
    public int maxTripletSum(int[] arr) {
        int n = arr.length;
        // Initialize the answer
        int ans = 0;

        for (int i = 1; i < n - 1; ++i) {
            int max1 = 0, max2 = 0;

            // find maximum value(less than arr[i])
            // from 0 to i-1
            for (int j = 0; j < i; ++j)
                if (arr[j] < arr[i])
                    max1 = Math.max(max1, arr[j]);

            // find maximum value(greater than arr[i])
            // from i+1 to n-1
            for (int j = i + 1; j < n; ++j)
                if (arr[j] > arr[i])
                    max2 = Math.max(max2, arr[j]);

            // store maximum answer
            if (max1 > 0 && max2 > 0)
                ans = Math.max(ans, max1 + arr[i] + max2);
        }

        return ans;
    }

    /*Approach 2
Best and efficient approach is use the concept of maximum suffix-array and binary search.

For finding a maximum number greater than given number beyond it, we can maintain a maximum suffix-array such that
for any number(suffixi) it would contain maximum number from index i, i+1, … n-1. Suffix array can be calculated in
O(n) time.
For finding maximum number smaller than the given number preceding it, we can maintain a sorted list of numbers
before a given number such we can simply perform a binary search to find a number which is just smaller than the
given number.*/

    //Time complexity: O(n*log(n))
    public int maxTripletSumOptimized(int[] arr) {

        int n = arr.length;
        // Initialize suffix-array
        int[] maxSuffArr = new int[n + 1];

        // Set the last element
        maxSuffArr[n] = 0;

        // Calculate suffix-array containing maximum
        // value for index i, i+1, i+2, ... n-1 in
        // arr[i]
        for (int i = n - 1; i >= 0; --i)
            maxSuffArr[i] = Math.max(maxSuffArr[i + 1],
                    arr[i]);

        int ans = 0;

        // Initialize set container
        TreeSet<Integer> lowValue = new TreeSet<Integer>();

        // Insert minimum value for first comparison
        // in the set
        lowValue.add(Integer.MIN_VALUE);

        for (int i = 0; i < n - 1; ++i) {
            if (maxSuffArr[i + 1] > arr[i]) {
                ans = Math.max(ans, lowValue.lower(arr[i]) +
                        arr[i] + maxSuffArr[i + 1]);

                // Insert arr[i] in set<> for further
                // processing
                lowValue.add(arr[i]);
            }
        }
        return ans;
    }

    // Driver Code
    public static void main(String[] args) {
        int[] arr = {2, 5, 3, 1, 4, 9};
        System.out.println(new MaxTripletSum().maxTripletSumOptimized(arr));
    }
}
