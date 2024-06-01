/*
 * *
 *  * Divide Two Integers.java
 *  * Created by Rafsan Ahmad on 6/2/24, 2:25 AM
 *  * Copyright (c) 2024 . All rights reserved.
 *  *
 *
 */

package javaclasses.BitManipulation;

public class DivideTwoIntegers {
    /*Given two integers dividend and divisor, divide two integers without using multiplication,
    division, and mod operator.

The integer division should truncate toward zero, which means losing its fractional part.
For example, 8.345 would be truncated to 8, and -2.7335 would be truncated to -2.

Return the quotient after dividing dividend by divisor.

Note: Assume we are dealing with an environment that could only store integers within the
32-bit signed integer range: [−2^31, 2^31 − 1]. For this problem, if the quotient is strictly greater
 than 2^31 - 1, then return 2^31 - 1, and if the quotient is strictly less than -2^31, then
 return -2^31.



Example 1:
Input: dividend = 10, divisor = 3
Output: 3
Explanation: 10/3 = 3.33333.. which is truncated to 3.

Example 2:
Input: dividend = 7, divisor = -3
Output: -2
Explanation: 7/-3 = -2.33333.. which is truncated to -2.


Constraints:
-2^31 <= dividend, divisor <= 2^31 - 1
divisor != 0
*/

    public int divide(int dividend, int divisor) {
        //Input: dividend = 10, divisor = 3
        //10 = 3 * 2^1 + 3 * 2^0

        if (dividend == divisor) return 1;

        boolean sign = true;
        if (dividend < 0 && divisor > 0) {
            sign = false;
        } else if (dividend > 0 && divisor < 0) {
            sign = false;
        }

        long n = Math.abs((long) dividend);
        long d = Math.abs((long) divisor);

        long result = 0;
        while (n >= d) {
            int count = 0;

            while (n > (d << count + 1)) {
                count++;
            }

            result += 1L << count;

            n -= d << (count);
        }

        if (result == 1L << 31 && sign) {
            return Integer.MAX_VALUE;
        } else if (result == 1L << 31 && !sign) {
            return Integer.MIN_VALUE;
        }

        return sign ? (int) result : -(int) result;
    }

    public static void main(String[] args) {
        DivideTwoIntegers integers = new DivideTwoIntegers();
        System.out.println(integers.divide(10, 3));
        System.out.println(integers.divide(-2147483648, -1));
    }
}
