/*
 * * Maximum Length Of Repeated Subarray.java
 *  * Created by Rafsan Ahmad on 10/25/21, 9:50 PM
 *  * Copyright (c) 2021 . All rights reserved.
 *
 */

package javaclasses.DynamicProgramming;

public class MaximumLengthOfRepeatedSubarray {
    //Leetcode 718
    /*Given two integer arrays nums1 and nums2, return the maximum length of a subarray that appears in both arrays.

Example 1:
Input: nums1 = [1,2,3,2,1], nums2 = [3,2,1,4,7]
Output: 3
Explanation: The repeated subarray with maximum length is [3,2,1].

Example 2:
Input: nums1 = [0,0,0,0,0], nums2 = [0,0,0,0,0]
Output: 5*/

    public int findLength(int[] nums1, int[] nums2) {
        int max = 0;
        int[][] dp = new int[nums1.length + 1][nums2.length + 1];
        for (int i = 1; i <= nums1.length; i++) {
            for (int j = 1; j <= nums2.length; j++) {
                if (nums1[i - 1] == nums2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    max = Math.max(dp[i][j], max);
                }
            }
        }
        return max;
    }

    public static void main(String[] args) {
        MaximumLengthOfRepeatedSubarray subarray = new MaximumLengthOfRepeatedSubarray();
        int[] ar1 = {1, 2, 3, 2, 1};
        int[] ar2 = {3, 2, 1, 4, 7};
        System.out.println(subarray.findLength(ar1, ar2));
    }

}
