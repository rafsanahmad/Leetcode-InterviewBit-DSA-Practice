/*
 * *
 *  * Maximum Width Ramp.java
 *  * Created by Rafsan Ahmad on 4/17/22, 12:22 PM
 *  * Copyright (c) 2022 . All rights reserved.
 *
 */

package javaclasses.StackQueue;

import java.util.Stack;

public class MaximumWidthRamp {
    //Leetcode 962
    /*A ramp in an integer array nums is a pair (i, j) for which i < j and nums[i] <= nums[j].
    The width of such a ramp is j - i.

Given an integer array nums, return the maximum width of a ramp in nums. If there is no ramp in nums, return 0.

Example 1:
Input: nums = [6,0,8,2,1,5]
Output: 4
Explanation: The maximum width ramp is achieved at (i, j) = (1, 5): nums[1] = 0 and nums[5] = 5.

Example 2:
Input: nums = [9,8,1,0,1,9,4,0,4,1]
Output: 7
Explanation: The maximum width ramp is achieved at (i, j) = (2, 9): nums[2] = 1 and nums[9] = 1.*/

    //Using Two Pointer
    public int maxWidthRamp(int[] nums) {
        int n = nums.length;
        int[] min_left = new int[n];
        int[] max_right = new int[n];

        min_left[0] = nums[0];
        max_right[n - 1] = nums[n - 1];

        for (int i = 1; i < n; ++i) {
            min_left[i] = Math.min(min_left[i - 1], nums[i]);
            max_right[n - 1 - i] = Math.max(nums[n - 1 - i], max_right[n - i]);
        }

        int i = 0, j = 0;
        int max_width = 0;
        while (i < n && j < n) {
            if (max_right[j] >= min_left[i]) {
                max_width = Math.max(max_width, j - i);
                ++j;
            } else
                ++i;

        }
        return max_width;
    }

    //Using Stack
    public int maxWidthRampUsingStack(int[] nums) {
        Stack<Integer> s = new Stack<>();
        // constructing monotonic decreasing Stack of Given Array
        for (int i = 0; i < nums.length; i++) {
            if (s.isEmpty() || nums[s.peek()] > nums[i]) {
                s.push(i);
            }
        }
        // finding width of ramp
        int max = 0;
        for (int i = nums.length - 1; i >= 0; i--) {
            while (!s.isEmpty() && nums[s.peek()] <= nums[i]) {
                max = Math.max(i - s.pop(), max);
            }
        }
        return max;
    }

    public static void main(String[] args) {
        MaximumWidthRamp ramp = new MaximumWidthRamp();
        int[] arr = {9, 8, 1, 0, 1, 9, 4, 0, 4, 1};
        int[] arr2 = {9, 9, 3, 5, 4, 0, 2, 0, 4, 1};
        System.out.println(ramp.maxWidthRamp(arr));
        System.out.println(ramp.maxWidthRamp(arr2));

        System.out.println(ramp.maxWidthRampUsingStack(arr));  //7
        System.out.println(ramp.maxWidthRampUsingStack(arr2)); //6
    }
}
