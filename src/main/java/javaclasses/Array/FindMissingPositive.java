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

    //Approach 2
    //Using sort & stack
    //Time complexity : O(nlogn)
    public int firstMissing(int[] arr) {
        Arrays.sort(arr);
        Stack<Integer> stack = new Stack<>();
        int i = 0;
        int n = arr.length;
        boolean negativeProcessed = false;
        while (i < n) {
            if (arr[i] <= 0) {
                i++;
                negativeProcessed = true;
                continue;
            }
            if (negativeProcessed || i == 0) {
                if (arr[i] > 1) return 1;
                negativeProcessed = false;
            }
            stack.push(arr[i]);
            if (!stack.isEmpty() && i < n - 1 && (arr[i + 1] - stack.peek()) > 1) {
                return arr[i + 1] - 1;
            }
            i++;
        }
        if (arr[n - 1] <= 0) return 1;
        return arr[n - 1] + 1;
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
        System.out.println(findMissingPositive.firstMissing(arr));
        System.out.println(findMissingPositive.firstMissing(arr2));
        System.out.println(findMissingPositive.firstMissing(arr3));
        System.out.println(findMissingPositive.firstMissing(arr4));
    }
}
