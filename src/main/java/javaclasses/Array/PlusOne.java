/*
 * * Plus One.java
 *  * Created by Rafsan Ahmad on 10/25/21, 9:49 PM
 *  * Copyright (c) 2021 . All rights reserved.
 *
 */

package javaclasses.Array;

import java.util.Arrays;
import java.util.Stack;

public class PlusOne {
    //Leetcode 66
    /*
    * Given a non-empty array of decimal digits representing a non-negative integer, increment one to the integer.
The digits are stored such that the most significant digit is at the head of the list,
and each element in the array contains a single digit.
You may assume the integer does not contain any leading zero, except the number 0 itself.

    Example 1:
    Input: digits = [1,2,3]
    Output: [1,2,4]
    Explanation: The array represents the integer 123.

    Example 2:
    Input: digits = [4,3,2,1]
    Output: [4,3,2,2]
    Explanation: The array represents the integer 4321.

    Example 3:
    Input: digits = [0]
    Output: [1]
*/
    public int[] plusOne(int[] digits) {
        int carry = 0;
        int sum = 0;
        int digit = 0;
        int len = digits.length;
        Stack<Integer> stack = new Stack<>();
        for (int i = len - 1; i >= 0; i--) {
            if (i == len - 1) {
                //plus one
                digit = digits[i] + 1;
            } else {
                digit = digits[i];
            }
            sum = digit + carry;
            if (sum / 10 >= 1) {
                carry = 1;
            } else {
                carry = 0;
            }
            stack.push(sum % 10);
        }
        if (carry > 0) {
            stack.push(1);
        }
        int[] resultArr = new int[stack.size()];
        int index = 0;
        while (!stack.empty()) {
            int value = stack.pop();
            resultArr[index] = value;
            index++;
        }
        return resultArr;
    }

    public static void main(String[] args) {
        PlusOne one = new PlusOne();
        int[] arr = {1, 2, 9};
        System.out.println(Arrays.toString(one.plusOne(arr)));
    }
}
