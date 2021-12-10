/*
 * * Maximum Contiguous Subarray.java
 *  * Created by Rafsan Ahmad on 10/25/21, 9:49 PM
 *  * Copyright (c) 2021 . All rights reserved.
 *
 */

package javaclasses.Array;

public class MaximumContiguousSubarray {
    //Leetcode 53
    /*
    Given an integer array nums, find the contiguous subarray (containing at least one number)
    which has the largest sum and return its sum.

    Example 1:
    Input: nums = [-2,1,-3,4,-1,2,1,-5,4]
    Output: 6
    Explanation: [4,-1,2,1] has the largest sum = 6.

    Example 2:
    Input: nums = [1]
    Output: 1

    Example 3:
    Input: nums = [5,4,-1,7,8]
    Output: 23
    * */

    public int maxSubArray(int[] nums) {
        int sum = nums[0];
        int result = 0;

        for (int i = 1; i < nums.length; i++) {
            sum = Math.max(nums[i], sum + nums[i]);
            result = Math.max(sum, result);
        }
        return result;
    }

    public static void main(String[] args) {
        MaximumContiguousSubarray array = new MaximumContiguousSubarray();
        int[] arr = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        System.out.println(array.maxSubArray(arr));
    }
}
