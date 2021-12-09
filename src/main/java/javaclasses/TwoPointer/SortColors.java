/*
 * * Sort Colors.java
 *  * Created by Rafsan Ahmad on 10/25/21, 9:50 PM
 *  * Copyright (c) 2021 . All rights reserved.
 *
 */

package javaclasses.TwoPointer;

import java.util.Arrays;

public class SortColors {
    //Leetcode 75
    //https://www.interviewbit.com/problems/sort-by-color
    /*Given an array nums with n objects colored red, white, or blue,
    sort them in-place so that objects of the same color are adjacent, with the colors in the order red, white, and blue.

We will use the integers 0, 1, and 2 to represent the color red, white, and blue, respectively.

You must solve this problem without using the library's sort function.

Example 1:
Input: nums = [2,0,2,1,1,0]
Output: [0,0,1,1,2,2]

Example 2:
Input: nums = [2,0,1]
Output: [0,1,2]

Example 3:
Input: nums = [0]
Output: [0]*/

    //Using three pointer - sliding window
    public void sortColors(int[] nums) {
        int start = 0;
        int mid = 0;
        int end = nums.length - 1;
        int temp;
        while (mid <= end) {
            switch (nums[mid]) {
                case 0:
                    temp = nums[start];
                    nums[start] = nums[mid];
                    nums[mid] = temp;
                    start++;
                    mid++;
                    break;
                case 1:
                    mid++;
                    break;
                case 2:
                    temp = nums[mid];
                    nums[mid] = nums[end];
                    nums[end] = temp;
                    end--;
                    break;
            }
        }
    }

    //Approach two - Count array
    public void sortColors2(int[] nums) {
        int[] arr = new int[3];

        for (int num : nums) {
            arr[num]++;
        }
        for (int i = 0; i < nums.length; i++) {
            if (arr[0] != 0) {
                nums[i] = 0;
                arr[0]--;
            } else if (arr[1] != 0) {
                nums[i] = 1;
                arr[1]--;
            } else {
                nums[i] = 2;
                arr[2]--;
            }
        }
    }

    public static void main(String[] args) {
        SortColors colors = new SortColors();
        int[] arr = {2, 0, 2, 1, 1, 0};
        colors.sortColors(arr);
        System.out.println(Arrays.toString(arr));

        int[] arr2 = {2, 0, 2, 1, 1, 0};
        colors.sortColors2(arr2);
        System.out.println(Arrays.toString(arr2));
    }
}
