/*
 * * Search Range.java
 *  * Created by Rafsan Ahmad on 10/25/21, 9:50 PM
 *  * Copyright (c) 2021 . All rights reserved.
 *
 */

package javaclasses.BinarySearch;

import java.util.Arrays;

public class SearchRange {
    //Leetcode 34
    /*
    Given an array of integers nums sorted in ascending order, find the starting and ending position of a
    given target value.

    If target is not found in the array, return [-1, -1].
    You must write an algorithm with O(log n) runtime complexity.

    Example 1:

    Input: nums = [5,7,7,8,8,10], target = 8
    Output: [3,4]
    Example 2:

    Input: nums = [5,7,7,8,8,10], target = 6
    Output: [-1,-1]
    Example 3:

    Input: nums = [], target = 0
    Output: [-1,-1]*/

    public int[] searchRange(int[] nums, int target) {
        int start = 0;
        int[] result = new int[2];
        Arrays.fill(result, -1);
        int end = nums.length - 1;
        while (start <= end) {
            int m = (start + end) / 2;
            if (target == nums[m]) {
                int sindex = m;
                int eindex = m;
                for (sindex = m; sindex >= 0; sindex--) {
                    if (target == nums[sindex]) {
                        continue;
                    } else {
                        break;
                    }
                }
                result[0] = sindex + 1;
                for (eindex = m; eindex < nums.length; eindex++) {
                    if (target == nums[eindex]) {
                        continue;
                    } else {
                        break;
                    }
                }
                result[1] = eindex - 1;
                break;
            }
            if (nums[m] < target) {
                start = m + 1;
            } else {
                end = m - 1;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        SearchRange range = new SearchRange();
        int[] arr = {3, 4, 6, 7, 8, 9, 10, 13, 14, 15, 15, 15, 15, 16, 18, 20};
        System.out.println(Arrays.toString(range.searchRange(arr, 15)));
    }
}
