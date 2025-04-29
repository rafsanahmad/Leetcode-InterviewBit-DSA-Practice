/*
 * *
 *  * Degree Of An Array.java
 *  * Created by Rafsan Ahmad on 12/17/21, 12:44 PM
 *  * Copyright (c) 2021 . All rights reserved.
 *
 */

package javaclasses.HashTable;

import java.util.HashMap;
import java.util.Map;

public class DegreeOfAnArray {
    /*Given a non-empty array of non-negative integers nums, the degree of this array is defined as the maximum
    frequency of any one of its elements.

Your task is to find the smallest possible length of a (contiguous) subarray of nums, that has the same degree as
 nums.

Example 1:
Input: nums = [1,2,2,3,1]
Output: 2
Explanation:
The input array has a degree of 2 because both elements 1 and 2 appear twice.
Of the subarrays that have the same degree:
[1, 2, 2, 3, 1], [1, 2, 2, 3], [2, 2, 3, 1], [1, 2, 2], [2, 2, 3], [2, 2]
The shortest length is 2. So return 2.

Example 2:
Input: nums = [1,2,2,3,1,4,2]
Output: 6
Explanation:
The degree is 3 because the element 2 is repeated 3 times.
So [2,2,3,1,4,2] is the shortest subarray, therefore returning 6.*/

    public int findShortestSubArray(int[] nums) {
        HashMap<Integer, Integer> countMap = new HashMap<>();
        HashMap<Integer, Integer> left = new HashMap<>();
        HashMap<Integer, Integer> right = new HashMap<>();
        int result = Integer.MAX_VALUE;
        int len = nums.length;
        int degree = 0;
        for (int i = 0; i < len; i++) {
            int count = countMap.getOrDefault(nums[i], 0) + 1;
            countMap.put(nums[i], count);

            if (left.get(nums[i]) == null) {
                left.put(nums[i], i);
            }
            right.put(nums[i], i);
            degree = Math.max(degree, count);
        }

        for (Map.Entry<Integer, Integer> entry : countMap.entrySet()) {
            int val = entry.getValue();
            int key = entry.getKey();
            if (val == degree) {
                result = Math.min(result, right.get(key) - left.get(key) + 1);
            }
        }

        return result;
    }

    public static void main(String[] args) {
        DegreeOfAnArray array = new DegreeOfAnArray();
        int[] arr = {1, 2, 2, 3, 1};
        int[] arr2 = {1, 2, 2, 3, 1, 4, 2};
        int[] arr3 = {1, 1, 2, 2, 2, 1};
        System.out.println(array.findShortestSubArray(arr));
        System.out.println(array.findShortestSubArray(arr2));
        System.out.println(array.findShortestSubArray(arr3));
        System.out.println(1 + 2 - 1);
    }
}
