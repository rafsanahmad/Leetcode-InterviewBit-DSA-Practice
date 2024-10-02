/*
 * * Largest Number.java
 *  * Created by Rafsan Ahmad on 10/25/21, 9:49 PM
 *  * Copyright (c) 2021 . All rights reserved.
 *
 */

package javaclasses.Array;

import java.util.Arrays;
import java.util.Comparator;

//Leetcode = 179
/*
Given a list of non-negative integers nums, arrange them such that they form the largest number.

Note: The result may be very large, so you need to return a string instead of an integer.

Example 1:
Input: nums = [10,2]
Output: "210"

Example 2:
Input: nums = [3,30,34,5,9]
Output: "9534330"

Example 3:
Input: nums = [1]
Output: "1"

Example 4:
Input: nums = [10]
Output: "10"
* */
public class LargestNumber {

    public String largestNumber(int[] nums) {
        if (nums == null || nums.length == 0) {
            return "";
        }

        int len = nums.length;
        String[] str = new String[len];
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < len; i++) {
            str[i] = String.valueOf(nums[i]);
        }

        Comparator<String> comparator = (o1, o2) -> {
            String s1 = o1 + o2;
            String s2 = o2 + o1;
            return s2.compareTo(s1);
        };

        Arrays.sort(str, comparator);
        if (str[0].charAt(0) == '0')
            return "0";

        for (int i = 0; i < len; i++) {
            builder.append(str[i]);
        }

        return builder.toString();
    }

    public static void main(String[] args) {
        int[] arr = {3, 30, 34, 5, 9};
        LargestNumber ln = new LargestNumber();
        String res = ln.largestNumber(arr);
        System.out.println(res);
    }
}
