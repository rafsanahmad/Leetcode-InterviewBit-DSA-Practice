/*
 * *
 *  * Sqrt(x).kt
 *  * Created by Rafsan Ahmad on 6/26/25, 1:37PM
 *  * Copyright (c) 2025 . All rights reserved.
 *  *
 *
 */

package kotlinclasses.BinarySearch

class Sqrt {
    //https://leetcode.com/problems/sqrtx/description/
    /*Given a non-negative integer x, return the square root of x rounded down to the nearest
    integer. The returned integer should be non-negative as well.

You must not use any built-in exponent function or operator.

For example, do not use pow(x, 0.5) in c++ or x ** 0.5 in python.


Example 1:
Input: x = 4
Output: 2
Explanation: The square root of 4 is 2, so we return 2.

Example 2:
Input: x = 8
Output: 2
Explanation: The square root of 8 is 2.82842..., and since we round it down to the nearest
integer, 2 is returned.


Constraints:
0 <= x <= 2^31 - 1*/

    fun mySqrt(x: Int): Int {
        if (x == 0 || x == 1) return x

        var left = 1
        var right = x
        var num = x.toLong()
        var ans = 0

        while (left <= right) {
            val mid = left + (right - left) / 2
            val sqrMid = 1L * mid * mid
            if (sqrMid == num) return mid
            else if (sqrMid < num) {
                ans = mid
                left = mid + 1
            } else {
                right = mid - 1
            }
        }

        return ans
    }
}

fun main() {
    val obj = Sqrt()
    println(obj.mySqrt(4))
    println(obj.mySqrt(8))
}