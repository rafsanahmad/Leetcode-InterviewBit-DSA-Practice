/*
 * *
 *  * Created by Rafsan Ahmad on 10/25/21, 9:50 PM
 *  * Copyright (c) 2021 . All rights reserved.
 *  
 */

package javaclasses.TwoPointer;

public class SortColors {
    //Leetcode 75
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

}
