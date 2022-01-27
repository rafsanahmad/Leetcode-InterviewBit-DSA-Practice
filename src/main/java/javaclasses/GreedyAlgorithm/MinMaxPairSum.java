/*
 * *
 *  * Minimize Maximize Pair Sum.java
 *  * Created by Rafsan Ahmad on 1/28/22, 12:23 AM
 *  * Copyright (c) 2022 . All rights reserved.
 *
 */

package javaclasses.GreedyAlgorithm;

import java.util.Arrays;

public class MinMaxPairSum {
    //Leetcode 1877
    /*The pair sum of a pair (a,b) is equal to a + b. The maximum pair sum is the largest pair sum in a list of
    pairs.

For example, if we have pairs (1,5), (2,3), and (4,4), the maximum pair sum would be max(1+5, 2+3, 4+4) =
max(6, 5, 8) = 8.
Given an array nums of even length n, pair up the elements of nums into n / 2 pairs such that:

Each element of nums is in exactly one pair, and
The maximum pair sum is minimized.
Return the minimized maximum pair sum after optimally pairing up the elements.



Example 1:
Input: nums = [3,5,2,3]
Output: 7
Explanation: The elements can be paired up into pairs (3,3) and (5,2).
The maximum pair sum is max(3+3, 5+2) = max(6, 7) = 7.

Example 2:
Input: nums = [3,5,4,2,4,6]
Output: 8
Explanation: The elements can be paired up into pairs (3,5), (4,4), and (6,2).
The maximum pair sum is max(3+5, 4+4, 6+2) = max(8, 8, 8) = 8.
 */

    public int minPairSum(int[] nums) {
        int result = Integer.MIN_VALUE;
        Arrays.sort(nums);
        int len = nums.length;
        int[] pairs = new int[len / 2];
        int i = 0;
        int j = len - 1;
        int index = 0;
        while (j >= len / 2 && i <= len / 2) {
            pairs[index] = nums[i] + nums[j];
            j--;
            i++;
            index++;
        }

        for (int k = 0; k < pairs.length; k++) {
            result = Math.max(result, pairs[k]);
        }

        return result;
    }

    public static void main(String[] args) {
        int[] nums = {3, 5, 4, 2, 4, 6};
        int[] nums2 = {3, 5, 2, 3};
        int[] nums3 = {4, 1, 5, 1, 2, 5, 1, 5, 5, 4};
        MinMaxPairSum sum = new MinMaxPairSum();
        System.out.println(sum.minPairSum(nums));
        System.out.println(sum.minPairSum(nums2));
        System.out.println(sum.minPairSum(nums3));
    }
}
