/*
 * *
 *  * Find Duplicate.java
 *  * Created by Rafsan Ahmad on 1/14/22, 12:03 AM
 *  * Copyright (c) 2022 . All rights reserved.
 *
 */

package javaclasses.Array;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class FindDuplicate {
    //Leetcode 287
    /*Given an array of integers nums containing n + 1 integers where each integer is in the range [1, n]
    inclusive.

There is only one repeated number in nums, return this repeated number.

You must solve the problem without modifying the array nums and uses only constant extra space.

Example 1:
Input: nums = [1,3,4,2,2]
Output: 2

Example 2:
Input: nums = [3,1,3,4,2]
Output: 3
*/
    //Using Sort - O(nlogn)
    public int findDuplicate(int[] nums) {
        Arrays.sort(nums);
        int len = nums.length;
        for (int i = 0; i < len - 1; i++) {
            if (nums[i] == nums[i + 1]) {
                return nums[i];
            }
        }
        return -1;
    }

    //Using Set - O(n)
    public int findDuplicateOptimized(int[] nums) {
        Set<Integer> seen = new HashSet<>();
        for (int num : nums) {
            if (seen.contains(num))
                return num;
            seen.add(num);
        }
        return -1;
    }

    public static void main(String[] args) {
        FindDuplicate duplicate = new FindDuplicate();
        int[] nums = {7, 9, 7, 4, 2, 8, 7, 7, 1, 5};
        System.out.println(duplicate.findDuplicate(nums));
        System.out.println(duplicate.findDuplicateOptimized(nums));
    }
}
