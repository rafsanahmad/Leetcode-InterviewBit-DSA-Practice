/*
 * *
 *  * MaxSumWithoutAdjacentElement.java
 *  * Created by Rafsan Ahmad on 4/9/23, 6:37 PM
 *  * Copyright (c) 2023 . All rights reserved.
 *
 */

package javaclasses.DynamicProgramming;

import java.util.Arrays;

public class MaxSumWithoutAdjacentElement {
    //https://leetcode.com/problems/house-robber-ii/
    /*You are a professional robber planning to rob houses along a street. Each house has a certain amount of money
    stashed. All houses at this place are arranged in a circle. That means the first house is the neighbor of the last
    one. Meanwhile, adjacent houses have a security system connected, and it will automatically contact the police if
    two adjacent houses were broken into on the same night.

Given an integer array nums representing the amount of money of each house, return the maximum amount of money you can
rob tonight without alerting the police.


Example 1:
Input: nums = [2,3,2]
Output: 3
Explanation: You cannot rob house 1 (money = 2) and then rob house 3 (money = 2), because they are adjacent houses.

Example 2:
Input: nums = [1,2,3,1]
Output: 4
Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
Total amount you can rob = 1 + 3 = 4.

Example 3:
Input: nums = [1,2,3]
Output: 3

Constraints:
1 <= nums.length <= 100
0 <= nums[i] <= 1000*/

    public int rob(int[] nums) {
        if (nums.length == 1) return nums[0];
        int[] dp = new int[nums.length];
        Arrays.fill(dp, -1);
        int firstTake = robHelper(nums, 0, nums.length - 1, dp);
        Arrays.fill(dp, -1);
        int firstNotTake = robHelper(nums, 1, nums.length, dp);
        return Math.max(firstTake, firstNotTake);
    }

    int robHelper(int[] arr, int i, int n, int[] dp) {
        if (i == n - 1) return dp[i] = arr[i];
        if (i >= n) return 0;
        if (dp[i] != -1) return dp[i];
        int take = arr[i] + robHelper(arr, i + 2, n, dp);
        int notTake = robHelper(arr, i + 1, n, dp);
        return dp[i] = Math.max(take, notTake);
    }

    public static void main(String[] args) {
        MaxSumWithoutAdjacentElement element = new MaxSumWithoutAdjacentElement();
        int[] arr = {1, 2, 3, 1};
        System.out.println(element.rob(arr));
    }
}
