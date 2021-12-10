/*
 * * Next Greater Element.java
 *  * Created by Rafsan Ahmad on 10/25/21, 9:50 PM
 *  * Copyright (c) 2021 . All rights reserved.
 *
 */

package javaclasses.StackQueue;

import java.util.Arrays;
import java.util.Stack;

public class NextGreaterElement {
    //Leetcode 503
    /*Given a circular integer array nums (i.e., the next element of nums[nums.length - 1] is nums[0]),
    return the next greater number for every element in nums.

The next greater number of a number x is the first greater number to its traversing-order next in the array,
which means you could search circularly to find its next greater number. If it doesn't exist, return -1 for this number.


Example 1:
Input: nums = [1,2,1]
Output: [2,-1,2]
Explanation: The first 1's next greater number is 2;
The number 2 can't find next greater number.
The second 1's next greater number needs to search circularly, which is also 2.

Example 2:
Input: nums = [1,2,3,4,3]
Output: [2,3,4,-1,4]*/

    public int[] nextGreaterElementsCircular(int[] nums) {
        int[] output = new int[nums.length];
        Arrays.fill(output, -1);

        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < 2 * nums.length - 1; i++) {
            int index = i % nums.length;
            while (!stack.empty() && nums[stack.peek()] < nums[index]) {
                output[stack.pop()] = nums[index];
            }
            stack.push(index);
        }
        return output;
    }

    public static void main(String[] args) {
        int[] ar = {1, 2, 3, 4, 3};
        NextGreaterElement element = new NextGreaterElement();
        System.out.println(Arrays.toString(element.nextGreaterElementsCircular(ar)));
    }

}
