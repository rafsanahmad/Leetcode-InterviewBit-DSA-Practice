/*
 * *
 *  * Increasing Triplet Subsequence.java
 *  * Created by Rafsan Ahmad on 1/5/22, 5:41 PM
 *  * Copyright (c) 2022 . All rights reserved.
 *
 */

package javaclasses.DynamicProgramming;

import java.util.ArrayList;

public class IncreasingTripletSubsequence {
    //Leetcode 334
    /*Given an integer array nums, return true if there exists a triple of indices (i, j, k) such that i < j < k and
    nums[i] < nums[j] < nums[k]. If no such indices exists, return false.

Example 1:
Input: nums = [1,2,3,4,5]
Output: true
Explanation: Any triplet where i < j < k is valid.


Example 2:
Input: nums = [5,4,3,2,1]
Output: false
Explanation: No triplet exists.

Example 3:
Input: nums = [2,1,5,0,4,6]
Output: true
Explanation: The triplet (3, 4, 5) is valid because nums[3] == 0 < nums[4] == 4 < nums[5] == 6.
*/

    public boolean increasingTriplet(int[] nums) {
        if (nums == null || nums.length <= 2)
            return false;

        ArrayList<Integer> list = new ArrayList<>();

        for (int num : nums) {
            if (list.size() == 0 || num > list.get(list.size() - 1)) {
                list.add(num);
                if (list.size() >= 3) return true;
            } else {
                int i = 0;
                int j = list.size() - 1;
                while (i < j) {
                    int mid = (i + j) / 2;
                    if (list.get(mid) < num) {
                        i = mid + 1;
                    } else {
                        j = mid;
                    }
                }
                list.set(i, num);
            }
        }
        return false;
    }

    //This problem can be formalized as finding a sequence x, y and z, such that x < y < z .
    public boolean increasingTripletOptimized(int[] nums) {
        int x = Integer.MAX_VALUE;
        int y = Integer.MAX_VALUE;

        for (int num : nums) {
            if (num <= x) {
                x = num;// update x to be a smaller value
            } else if (num <= y) {
                y = num; // update y to be a smaller value
            } else {
                return true;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        IncreasingTripletSubsequence subsequence = new IncreasingTripletSubsequence();
        int[] nums = {2, 1, 5, 0, 4, 6};
        System.out.println(subsequence.increasingTriplet(nums));
        System.out.println(subsequence.increasingTripletOptimized(nums));
    }
}
