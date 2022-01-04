/*
 * *
 *  * Product Of Array Except Self.java
 *  * Created by Rafsan Ahmad on 1/4/22, 3:24 PM
 *  * Copyright (c) 2022 . All rights reserved.
 *
 */

package javaclasses.NumberTheory.Math;

import java.util.Arrays;

public class ProductOfArrayExceptSelf {
    //Leetcode 238
    /*Given an integer array nums, return an array answer such that answer[i] is equal to the product of all the
    elements of nums except nums[i].

The product of any prefix or suffix of nums is guaranteed to fit in a 32-bit integer.

You must write an algorithm that runs in O(n) time and without using the division operation.

Example 1:
Input: nums = [1,2,3,4]
Output: [24,12,8,6]

Example 2:
Input: nums = [-1,1,0,-3,3]
Output: [0,0,9,0,0]

Solution:
Use prefix & suffix calculation of the product of the array
 nums   = [1,2,3,4]
 prefix = [1,1,2,6]
 suffix = [24,12,4,1]
 Result = [24,12,8,6]
*/

    public int[] productExceptSelf(int[] nums) {
        int len = nums.length;
        int[] result = new int[len];

        int[] t1 = new int[len];
        int[] t2 = new int[len];

        t1[0] = 1;
        t2[len - 1] = 1;

        //scan from left to right
        for (int i = 0; i < len - 1; i++) {
            t1[i + 1] = nums[i] * t1[i];
        }

        //scan from right to left
        for (int i = len - 1; i > 0; i--) {
            t2[i - 1] = t2[i] * nums[i];
        }

        //multiply
        for (int i = 0; i < nums.length; i++) {
            result[i] = t1[i] * t2[i];
        }

        return result;
    }

    //Without Extra space
    public int[] productExceptSelf2(int[] nums) {
        int len = nums.length;
        int[] result = new int[len];

        result[len - 1] = 1;
        for (int i = len - 2; i >= 0; i--) {
            result[i] = result[i + 1] * nums[i + 1];
        }

        int left = 1;
        for (int i = 0; i < len; i++) {
            result[i] = result[i] * left;
            left = left * nums[i];
        }

        return result;
    }

    public static void main(String[] args) {
        ProductOfArrayExceptSelf array = new ProductOfArrayExceptSelf();
        int[] nums = {1, 2, 3, 4};
        System.out.println(Arrays.toString(array.productExceptSelf(nums)));
        System.out.println(Arrays.toString(array.productExceptSelf2(nums)));
    }
}
