/*
 * * Find Missing Positive.java
 *  * Created by Rafsan Ahmad on 10/25/21, 9:49 PM
 *  * Copyright (c) 2021 . All rights reserved.
 *
 */

package javaclasses.Array;

import java.util.Arrays;
import java.util.Stack;

public class FindMissingPositive {
    //Leetcode 41
    /*Given an unsorted integer array nums, return the smallest missing positive integer.

You must implement an algorithm that runs in O(n) time and uses constant extra space.

Example 1:
Input: nums = [1,2,0]
Output: 3

Example 2:
Input: nums = [3,4,-1,1]
Output: 2

Example 3:
Input: nums = [7,8,9,11,12]
Output: 1*/

    //Time complexity: 0(n)
    //Space complexity: 0(1)
    public int firstMissingPositive(int[] nums) {
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            while (nums[i] != i + 1) {
                //Nums don't match position
                if (nums[i] <= 0 || nums[i] >= len)
                    break;
                if (nums[i] == nums[nums[i] - 1])
                    break;
                //Swap
                int temp = nums[i];
                nums[i] = nums[temp - 1];
                nums[temp - 1] = temp;
            }
        }
        for (int i = 0; i < len; i++) {
            if (nums[i] != i + 1) {
                return i + 1;
            }
        }
        return len + 1;
    }

    //Approach 2 - More readable
    //Time complexity : O(n)
    public int firstMissingPositive2(int[] nums) {
        int len = nums.length;
        int i = 0;
        while (i < len) {
            if (nums[i] >= 1 && nums[i] <= len && nums[nums[i] - 1] != nums[i]) {
                swap(nums, i, nums[i] - 1);
            } else {
                i++;
            }
        }

        i = 0;
        while (i < len && nums[i] == i + 1) i++;
        return i + 1;
    }

    public void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        FindMissingPositive findMissingPositive = new FindMissingPositive();
        int[] arr = {3, 4, -1, 1};
        int[] arr2 = {-40, -39, -46, -38, -13, -30, -26, -28, -37, -36};
        int[] arr3 = {3, 4, -6, 1, 0};
        int[] arr4 = {2};
        //Approach 1
        System.out.println(findMissingPositive.firstMissingPositive(arr));
        System.out.println(findMissingPositive.firstMissingPositive(arr2));
        System.out.println(findMissingPositive.firstMissingPositive(arr3));
        System.out.println(findMissingPositive.firstMissingPositive(arr4));

        //Approach 2
        System.out.println(findMissingPositive.firstMissingPositive2(arr));
        System.out.println(findMissingPositive.firstMissingPositive2(arr2));
        System.out.println(findMissingPositive.firstMissingPositive2(arr3));
        System.out.println(findMissingPositive.firstMissingPositive2(arr4));
    }
}
