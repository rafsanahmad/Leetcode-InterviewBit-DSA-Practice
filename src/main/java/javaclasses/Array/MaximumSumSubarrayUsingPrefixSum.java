/*
 * *
 *  * Maximum subarray sum in O(n) using prefix sum.java
 *  * Created by Rafsan Ahmad on 6/20/22, 1:17 AM
 *  * Copyright (c) 2022 . All rights reserved.
 *
 */

package javaclasses.Array;

public class MaximumSumSubarrayUsingPrefixSum {
    //res/maximum-subarray-sum.jpeg
    /*Given an Array of Positive and Negative Integers, find out the Maximum Subarray Sum in that Array.
    Examples:
Input1 : arr = {-2, -3, 4, -1, -2, 1, 5, -3}
Output1 : 7

Input2 : arr = {4, -8, 9, -4, 1, -8, -1, 6}
Output2 : 9

Implementation:
1. Calculate the prefix sum of the input array.
2. Initialize : min_prefix_sum = 0, res = -infinite
3. Maintain a loop for i = 0 to n. (n is the size of the input array).
     a) cand = prefix_sum[i] – mini
     b) If cand is greater than res (maximum subarray sum so far), then update res by cand.
     c) If prefix_sum[i] is less than min_prefix_sum (minimum prefix sum so far), then update min_prefix_sum by prefix_sum[i].
4. Return res.
   */

    static int maximumSumSubarray(int arr[], int n) {
        // Initialize minimum prefix sum to 0.
        int min_prefix_sum = 0;

        // Initialize maximum subarray sum so far to -infinity.
        int res = Integer.MIN_VALUE;

        // Initialize and compute the prefix sum array.
        int[] prefix_sum = new int[n];
        prefix_sum[0] = arr[0];
        for (int i = 1; i < n; i++)
            prefix_sum[i] = prefix_sum[i - 1] + arr[i];

        // loop through the array, keep track of minimum prefix sum so far and maximum subarray sum.
        for (int i = 0; i < n; i++) {
            res = Math.max(res, prefix_sum[i] - min_prefix_sum);
            min_prefix_sum = Math.min(min_prefix_sum, prefix_sum[i]);
        }

        return res;
    }

    public static void main(String[] args) {
        // Test case 1
        int[] arr1 = {-2, -3, 4, -1, -2, 1, 5, -3};
        int n1 = arr1.length;
        System.out.println(maximumSumSubarray(arr1, n1));

        // Test case 2
        int[] arr2 = {4, -8, 9, -4, 1, -8, -1, 6};
        int n2 = arr2.length;
        System.out.println(maximumSumSubarray(arr2, n2));
    }
}
