package javaclasses.NumberTheory;

import java.util.Arrays;

//Leetcode 16
/*
Given an array nums of n integers and an integer target,
find three integers in nums such that the sum is closest to target. Return the sum of the three integers.
You may assume that each input would have exactly one solution.

Example 1:

Input: nums = [-1,2,1,-4], target = 1
Output: 2
Explanation: The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).
* */
public class ThreeSumClosest {
    public int threeSumClosest(int[] nums, int target) {
        int diff = Integer.MAX_VALUE;
        int result = 0;
        Arrays.sort(nums);

        for (int i = 0; i < nums.length; i++) {
            int start = i + 1;
            int end = nums.length - 1;

            while (start < end && end > 0) {
                int sum = nums[i] + nums[start] + nums[end];

                int d = Math.abs(target - sum);
                if (d < diff) {
                    diff = d;
                    result = sum;
                }
                if (nums[i] + nums[start] + nums[end] < target) {
                    start++;
                } else {
                    end--;
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] arr = {1, 1, 1, 0};
        ThreeSumClosest closest = new ThreeSumClosest();
        System.out.println(closest.threeSumClosest(arr, -100));
    }
}
