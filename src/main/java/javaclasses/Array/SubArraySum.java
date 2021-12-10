/*
 * * Sub Array Sum.java
 *  * Created by Rafsan Ahmad on 10/25/21, 9:49 PM
 *  * Copyright (c) 2021 . All rights reserved.
 *
 */

package javaclasses.Array;

import java.util.HashMap;

public class SubArraySum {
    //Leetcode 560
    /*Given an array of integers nums and an integer k, return the total number
    of continuous subarrays whose sum equals to k.

Example 1:
Input: nums = [1,1,1], k = 2
Output: 2

Example 2:
Input: nums = [1,2,3], k = 3
Output: 2*/

    public int subarraySum(int[] nums, int k) {
        int count = 0;
        int sum = 0;
        //Key - Sum, Value - Number of occurances of sum
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);

        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (map.containsKey(sum - k))
                count += map.get(sum - k);
            int occurances = map.getOrDefault(sum, 0) + 1;
            map.put(sum, occurances);
        }
        return count;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3};
        SubArraySum sum = new SubArraySum();
        System.out.println(sum.subarraySum(arr, 3));
    }
}
