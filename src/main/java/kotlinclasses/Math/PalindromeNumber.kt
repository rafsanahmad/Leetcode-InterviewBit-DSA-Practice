/*
 * *
 *  * Palindrome Number.kt
 *  * Created by Rafsan Ahmad on 6/5/25, 12:06PM
 *  * Copyright (c) 2025 . All rights reserved.
 *  *
 *
 */

package kotlinclasses.Math

class PalindromeNumber {
    //https://leetcode.com/problems/palindrome-number/description/
    /*Given an integer x, return true if x is a palindrome, and false otherwise.

Example 1:
Input: x = 121
Output: true
Explanation: 121 reads as 121 from left to right and from right to left.

Example 2:
Input: x = -121
Output: false
Explanation: From left to right, it reads -121. From right to left, it becomes 121-.
Therefore it is not a palindrome.

Example 3:
Input: x = 10
Output: false
Explanation: Reads 01 from right to left. Therefore it is not a palindrome.


Constraints:
-2^31 <= x <= 2^31 - 1


Follow up: Could you solve it without converting the integer to a string?*/

    fun isPalindrome(x: Int): Boolean {
        var temp = x
        var revInt: Long = 0
        if (x < 0) return false

        while (temp > 0) {
            val digit = temp % 10
            revInt = revInt * 10 + digit
            temp /= 10
        }

        return x.toLong() == revInt
    }
}

fun main() {
    val number = PalindromeNumber()
    println(number.isPalindrome(121))
    println(number.isPalindrome(10009901))
}