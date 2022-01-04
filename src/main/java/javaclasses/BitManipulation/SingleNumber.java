/*
 * *
 *  * Single Number.java
 *  * Created by Rafsan Ahmad on 1/4/22, 2:51 PM
 *  * Copyright (c) 2022 . All rights reserved.
 *
 */

package javaclasses.BitManipulation;

public class SingleNumber {
    //Leetcode 136
    /*Given a non-empty array of integers nums, every element appears twice except for one. Find that single one.

You must implement a solution with a linear runtime complexity and use only constant extra space.

Example 1:
Input: nums = [2,2,1]
Output: 1

Example 2:
Input: nums = [4,1,2,1,2]
Output: 4

Example 3:
Input: nums = [1]
Output: 1
*/

    /*Here we are finding the XOR (Exclusive OR) of all the elements present in the given array.
    Since we have only 1 variable 'ans', space complexity will be O(1) and time complexity is also linear,
    since we are only iterating over the array and not applying any extra algorithm.
    Why XOR ?
    Since all elements are occurring twice except one element, the XOR of these element will be 0 and as we know 0
    xor N = N, we get our desired output.*/
    public int singleNumber(int[] nums) {
        int ans = nums[0];
        for (int i = 1; i < nums.length; i++) {
            ans ^= nums[i];
        }
        return ans;
    }

    public static void main(String[] args) {
        SingleNumber number = new SingleNumber();
        int[] nums = {4, 1, 2, 1, 2};
        System.out.println(number.singleNumber(nums));

        System.out.println(3 ^ 3);   //0
        System.out.println(3 ^ 3 ^ 4); //4
    }
}
