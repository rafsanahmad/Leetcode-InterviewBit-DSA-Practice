/*
 * *
 *  * Reverse Integer.java
 *  * Created by Rafsan Ahmad on 12/22/21, 12:21 AM
 *  * Copyright (c) 2021 . All rights reserved.
 *
 */

package javaclasses.NumberTheory.Math;

public class ReverseInteger {
    //Leetcode 7
    /*Given a signed 32-bit integer x, return x with its digits reversed. If reversing x causes the value to go
    outside the signed 32-bit integer range [-231, 231 - 1], then return 0.

Assume the environment does not allow you to store 64-bit integers (signed or unsigned).

Example 1:
Input: x = 123
Output: 321

Example 2:
Input: x = -123
Output: -321

Example 3:
Input: x = 120
Output: 21*/

    public int reverse(int x) {
        StringBuilder res = new StringBuilder();
        int abs = Math.abs(x);
        while (abs > 0) {
            int rem = abs % 10;
            abs = abs / 10;
            res.append(rem);
        }
        try {
            int ans = x;
            if (res.length() > 0) {
                ans = Integer.parseInt(res.toString());
            }
            if (x < 0) ans = -ans;
            boolean check = checkRange(ans);
            if (check) return ans;
            return 0;
        } catch (Exception e) {
            return 0;
        }
    }

    public boolean checkRange(int v) {
        int lower = (int) Math.pow(-2, 31);
        int upper = (int) Math.pow(2, 31) - 1;
        if (v > lower && v < upper) return true;
        return false;
    }

    public int reverseOptimized(int x) {
        String reversed = new StringBuilder().append(Math.abs(x)).reverse().toString();
        try {
            return (x < 0) ? Integer.parseInt(reversed) * -1 : Integer.parseInt(reversed);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    public static void main(String[] args) {
        ReverseInteger integer = new ReverseInteger();
        int lower = (int) Math.pow(-2, 31);
        int upper = (int) Math.pow(2, 31) - 1;
        System.out.println(lower);
        System.out.println(upper);

        System.out.println(integer.reverse(0));
        System.out.println(integer.reverse(123));
        System.out.println(integer.reverse(-123));
        System.out.println(integer.reverse(120));
        System.out.println(integer.reverse(10000));
        System.out.println(integer.reverse(1534236469)); //0
        System.out.println(integer.reverse(-2147483648)); //0

        System.out.println(integer.reverseOptimized(-2147483648)); //0
        System.out.println(integer.reverseOptimized(10000));
    }
}
