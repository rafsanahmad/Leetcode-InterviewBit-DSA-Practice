/*
 * *
 *  * ContinuousSubarraySum.java
 *  * Created by Rafsan Ahmad on 12/16/21, 1:47 PM
 *  * Copyright (c) 2021 . All rights reserved.
 *
 */

package javaclasses.Hashing;

import java.util.HashMap;
import java.util.Map;

public class ContinuousSubarraySum {
    //Leetcode 523
    /*Given an integer array nums and an integer k, return true if nums has a continuous subarray of size at
    least two whose elements sum up to a multiple of k, or false otherwise.

An integer x is a multiple of k if there exists an integer n such that x = n * k. 0 is always a multiple of k.

Example 1:
Input: nums = [23,2,4,6,7], k = 6
Output: true
Explanation: [2, 4] is a continuous subarray of size 2 whose elements sum up to 6.

Example 2:
Input: nums = [23,2,6,4,7], k = 6
Output: true
Explanation: [23, 2, 6, 4, 7] is an continuous subarray of size 5 whose elements sum up to 42.
42 is a multiple of 6 because 42 = 7 * 6 and 7 is an integer.

Example 3:
Input: nums = [23,2,6,4,7], k = 13
Output: false*/

    //Time limit exceeded
    public boolean checkSubarraySum(int[] nums, int k) {
        int sum = 0;
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            sum = nums[i];
            for (int j = i + 1; j < len; j++) {
                sum = sum + nums[j];
                if (sum % k == 0) {
                    return true;
                }
            }
        }
        for (int i = len - 1; i > 0; i--) {
            sum = nums[i];
            for (int j = i - 1; j > 0; j--) {
                sum = sum + nums[j];
                if (sum % k == 0) {
                    return true;
                }
            }
        }
        return false;
    }

    //Using reminder theory
    public boolean checkSubarraySumOptimized(int[] nums, int k) {
        Map<Integer, Integer> remainderMap = new HashMap<>();

        remainderMap.put(0, -1);
        int runningSum = 0;
        int minLen = 2;

        for (int i = 0; i < nums.length; i++) {
            runningSum += nums[i];
            if (k != 0) {
                runningSum = runningSum % k;
            }
            if (remainderMap.containsKey(runningSum)) {
                if (i - remainderMap.get(runningSum) >= minLen)
                    return true;
            } else {
                remainderMap.put(runningSum, i);
            }
        }
        return false;
    }

    public static void main(String[] args) {
        ContinuousSubarraySum sum = new ContinuousSubarraySum();
        int[] arr = {23, 2, 6, 4, 7};
        System.out.println(sum.checkSubarraySumOptimized(arr, 13));
        System.out.println(sum.checkSubarraySum(arr, 13));
    }
}
