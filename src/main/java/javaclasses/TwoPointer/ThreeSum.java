/*
 * * Three Sum.java
 *  * Created by Rafsan Ahmad on 10/25/21, 9:50 PM
 *  * Copyright (c) 2021 . All rights reserved.
 *  
 */

package javaclasses.TwoPointer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//Leetcode 15
/*
* Given an integer array nums, return all the triplets [nums[i], nums[j], nums[k]] such that i != j, i != k, and j != k,
* and nums[i] + nums[j] + nums[k] == 0.

Notice that the solution set must not contain duplicate triplets.

Example 1:
Input: nums = [-1,0,1,2,-1,-4]
Output: [[-1,-1,2],[-1,0,1]]

Example 2:
Input: nums = []
Output: []
*/
public class ThreeSum {
    //use two pointer at start and end (Complexity = 0(n2))
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);

        for (int i = 0; i < nums.length; i++) {
            int start = i + 1;
            int end = nums.length - 1;

            //discard duplicate
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            while (start < end) {
                //discard duplicate
                if (end < nums.length - 1 && nums[end] == nums[end + 1]) {
                    end--;
                    continue;
                }
                if (nums[i] + nums[start] + nums[end] == 0) {
                    List<Integer> val = Arrays.asList(nums[i], nums[start], nums[end]);
                    result.add(val);
                    start++;
                    end--;
                } else if (nums[i] + nums[start] + nums[end] < 0) {
                    start++;
                } else {
                    end--;
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        ThreeSum sum = new ThreeSum();
        int[] nums = {-1, 0, 1, 2, -1, -4};
        System.out.println(sum.threeSum(nums));
    }
}
