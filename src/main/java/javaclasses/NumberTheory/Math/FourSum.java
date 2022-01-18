/*
 * * FourSum.java
 *  * Created by Rafsan Ahmad on 10/25/21, 9:50 PM
 *  * Copyright (c) 2021 . All rights reserved.
 *
 */

package javaclasses.NumberTheory.Math;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FourSum {
    //Leetcode 18
    /*
    * Given an array nums of n integers, return an array of all the unique quadruplets
    * [nums[a], nums[b], nums[c], nums[d]] such that:
0 <= a, b, c, d < n
a, b, c, and d are distinct.
nums[a] + nums[b] + nums[c] + nums[d] == target
You may return the answer in any order.

Example 1:
Input: nums = [1,0,-1,0,-2,2], target = 0
Output: [[-2,-1,1,2],[-2,0,0,2],[-1,0,0,1]]

Example 2:
Input: nums = [2,2,2,2,2], target = 8
Output: [[2,2,2,2]]
 */
    //Time complexity = O(n^3)
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return result;
        }
        int len = nums.length;
        Arrays.sort(nums);
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                int front = j + 1;
                int back = len - 1;
                int target2 = target - nums[j] - nums[i];
                while (front < back) {
                    int two_sum = nums[front] + nums[back];
                    if (two_sum > target2) {
                        back--;
                    } else if (two_sum < target2) {
                        front++;
                    } else {
                        List<Integer> quad = new ArrayList<>();
                        quad.add(nums[i]);
                        quad.add(nums[j]);
                        quad.add(nums[front]);
                        quad.add(nums[back]);
                        result.add(quad);

                        //Discard duplicate
                        while (front < back && nums[front] == quad.get(2)) ++front;
                        while (front < back && nums[back] == quad.get(3)) --back;
                    }
                }
                //remove duplicate
                while (j + 1 < len && nums[j] == nums[j + 1]) ++j;
            }
            //remove duplicate
            while (i + 1 < len && nums[i] == nums[i + 1]) ++i;
        }

        return result;
    }

    public List<List<Integer>> fourSumGeneralized(int[] nums, int target) {
        Arrays.sort(nums);
        //K = 4
        return kSum(nums, 0, 4, target);
    }

    public List<List<Integer>> kSum(int[] nums, int start, int k, int target) {
        int len = nums.length;
        List<List<Integer>> res = new ArrayList<>();
        if (k == 2) {
            //two pointers from left and right
            int left = start, right = len - 1;
            while (left < right) {
                int sum = nums[left] + nums[right];
                if (sum == target) {
                    List<Integer> pair = new ArrayList<>();
                    pair.add(nums[left]);
                    pair.add(nums[right]);
                    res.add(pair);
                    left++;
                    right--;

                    //Ignore duplicate
                    while (left < right && nums[left] == nums[left - 1]) left++;
                    while (left < right && nums[right] == nums[right - 1]) right--;
                } else if (sum < target) {
                    //Move left
                    left++;
                } else {
                    //Move right
                    right--;
                }
            }
        } else {
            for (int i = start; i < len - (k - 1); i++) {
                if (i > start && nums[i] == nums[i - 1]) continue;
                List<List<Integer>> temp = kSum(nums, i + 1, k - 1, target - nums[i]);
                for (List<Integer> t : temp) {
                    t.add(0, nums[i]);
                }
                res.addAll(temp);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] ar = {1, 0, -1, 0, -2, 2};
        FourSum sum = new FourSum();
        List<List<Integer>> result = sum.fourSum(ar, 0);
        for (int i = 0; i < result.size(); i++) {
            System.out.print("[");
            for (int j = 0; j < result.get(i).size(); j++) {
                System.out.print(result.get(i).get(j) + " ");
            }
            System.out.print("]");
        }
        System.out.println();
        
        List<List<Integer>> result2 = sum.fourSumGeneralized(ar, 0);
        for (int i = 0; i < result2.size(); i++) {
            System.out.print("[");
            for (int j = 0; j < result2.get(i).size(); j++) {
                System.out.print(result2.get(i).get(j) + " ");
            }
            System.out.print("]");
        }
    }
}
