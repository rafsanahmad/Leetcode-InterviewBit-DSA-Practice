/*
 * *
 *  * Maximum Ascending Subarray Sum.java
 *  * Created by Rafsan Ahmad on 12/19/21, 5:05 AM
 *  * Copyright (c) 2021 . All rights reserved.
 *
 */

package javaclasses.Array;

public class MaxAscendingSubarraySum {
    /*Given an array of positive integers nums, return the maximum possible sum of an ascending subarray in nums.

A subarray is defined as a contiguous sequence of numbers in an array.

A subarray [numsl, numsl+1, ..., numsr-1, numsr] is ascending if for all i where l <= i < r, numsi < numsi+1.
 Note that a subarray of size 1 is ascending.

Example 1:
Input: nums = [10,20,30,5,10,50]
Output: 65
Explanation: [5,10,50] is the ascending subarray with the maximum sum of 65.

Example 2:
Input: nums = [10,20,30,40,50]
Output: 150
Explanation: [10,20,30,40,50] is the ascending subarray with the maximum sum of 150.

Example 3:
Input: nums = [12,17,15,13,10,11,12]
Output: 33
Explanation: [10,11,12] is the ascending subarray with the maximum sum of 33.

Example 4:
Input: nums = [100,10,1]
Output: 100
*/

    public int maxAscendingSum(int[] nums) {
        int sum = nums[0];
        int result = sum;
        int total = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[i - 1]) {
                sum = sum + nums[i];
                total = Math.max(nums[i], sum);
            } else {
                total = Math.max(nums[i], sum);
                sum = nums[i];
            }
            result = Math.max(total, result);
        }
        return result;
    }

    public static void main(String[] args) {
        MaxAscendingSubarraySum sum = new MaxAscendingSubarraySum();
        int[] arr = {12, 17, 15, 13, 10, 11, 12};
        int[] arr2 = {100, 10, 1};
        int[] arr3 = {1, 2, 3, 10, 2, 6, 40, 60, 9, 80, 100, 10, 100, 100};

        System.out.println(sum.maxAscendingSum(arr));
        System.out.println(sum.maxAscendingSum(arr2));
        System.out.println(sum.maxAscendingSum(arr3));
    }
}
