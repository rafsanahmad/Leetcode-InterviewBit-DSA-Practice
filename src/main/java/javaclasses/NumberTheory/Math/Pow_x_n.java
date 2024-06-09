/*
 * *
 *  * pow(x, n).java
 *  * Created by Rafsan Ahmad on 6/10/24, 12:57 AM
 *  * Copyright (c) 2024 . All rights reserved.
 *  *
 *
 */

package javaclasses.NumberTheory.Math;

public class Pow_x_n {
    /*Implement pow(x, n), which calculates x raised to the power n (i.e., xn).

Example 1:

Input: x = 2.00000, n = 10
Output: 1024.00000
Example 2:

Input: x = 2.10000, n = 3
Output: 9.26100
Example 3:

Input: x = 2.00000, n = -2
Output: 0.25000
Explanation: 2-2 = 1/22 = 1/4 = 0.25


Constraints:

-100.0 < x < 100.0
-2^31 <= n <= 2^31-1
n is an integer.
Either x is not zero or n > 0.
-10^4 <= x^n <= 10^4
*/

    /*
     * Time complexity: O(logn)
     * Solution:
     * 2^10 can be written as:
     * n=10 is even: n = n/2
     * (2*2)^5 = 4^5
     * n=5 is odd: n = n-1
     * 4*4^4
     * 4^4 can be written as:
     * n=4 is even: n = n/2
     * (4*4)^2 = 16^2
     * 16*16 = 256
     * 4*256 = 1024
     * */

    public double myPow(double x, int n) {
        long power = (long) n;
        power = Math.abs(power);
        double result = 1.0;
        while (power > 0) {
            if (power % 2 == 1) {
                //Odd
                result = result * x;
                power--;
            } else {
                //Even
                x = x * x;
                power /= 2;
            }
        }

        if (n < 0) return 1 / result;
        return result;
    }

    public static void main(String[] args) {
        Pow_x_n powXN = new Pow_x_n();
        System.out.println(powXN.myPow(2, 10));
        System.out.println(powXN.myPow(2.00000, -2));
    }
}
