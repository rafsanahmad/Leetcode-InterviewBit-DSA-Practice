/*
 * *
 *  * Created by Rafsan Ahmad on 10/25/21, 9:49 PM
 *  * Copyright (c) 2021 . All rights reserved.
 *
 */

package javaclasses.Array;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

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

    public int[] repeatedNumber(final int[] A) {
        int dup = 0;
        int missing = 0;
        int[] nums = new int[10];
        Arrays.fill(nums, 0);

        for (int i = 0; i < A.length; i++) {
            nums[A[i]] = nums[A[i]] + 1;
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 1) {
                dup = i;
            }
        }
        missing = firstMissingPositive(A);

        return new int[]{dup, missing};
    }

    public int[] repeatedNumberUsingMap(final int[] A) {
        int dup = 0;
        int missing = 0;
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < A.length; i++) {
            map.put(A[i], map.getOrDefault(A[i], 0) + 1);
        }
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() > 1) {
                dup = entry.getKey();
                break;
            }
        }
        missing = firstMissingPositive(A);

        return new int[]{dup, missing};
    }

    public static void main(String[] args) {
        FindMissingPositive findMissingPositive = new FindMissingPositive();
        int[] arr = {3, 4, -1, 1};
        System.out.println(findMissingPositive.firstMissingPositive(arr));

        int[] arr2 = {3, 1, 2, 5, 3};
        System.out.println(Arrays.toString(findMissingPositive.repeatedNumber(arr2)));
    }
}
