/*
 * *
 *  * Valid Palindrome.kt
 *  * Created by Rafsan Ahmad on 6/6/25, 3:14AM
 *  * Copyright (c) 2025 . All rights reserved.
 *  *
 *
 */

package kotlinclasses.TwoPointer_SlidingWindow

class ValidPalindrome {
    //https://leetcode.com/problems/valid-palindrome/
    /*A phrase is a palindrome if, after converting all uppercase letters into lowercase letters
    and removing all non-alphanumeric characters, it reads the same forward and backward.
    Alphanumeric characters include letters and numbers.

Given a string s, return true if it is a palindrome, or false otherwise.


Example 1:
Input: s = "A man, a plan, a canal: Panama"
Output: true
Explanation: "amanaplanacanalpanama" is a palindrome.

Example 2:
Input: s = "race a car"
Output: false
Explanation: "raceacar" is not a palindrome.

Example 3:
Input: s = " "
Output: true
Explanation: s is an empty string "" after removing non-alphanumeric characters.
Since an empty string reads the same forward and backward, it is a palindrome.


Constraints:
1 <= s.length <= 2 * 10^5
s consists only of printable ASCII characters.*/

    fun isPalindrome(s: String): Boolean {
        var str = s.replace(Regex("[^a-zA-Z0-9]"), "")
        var left = 0
        var right = str.length - 1

        while (left < right) {
            if (str[left].lowercase() != str[right].lowercase()) {
                return false
            }
            left++
            right--
        }

        return true
    }

    /*Optimization	Impact
Avoids replace and creating a filtered string - Saves memory & time
Uses .isLetterOrDigit() inline - Avoids full regex scan
Uses .lowercaseChar() - Faster than .lowercase()
No extra allocation - Space-efficient (O(1))
*/
    fun isPalindromeOptimized(s: String): Boolean {
        var left = 0
        var right = s.length - 1

        while (left < right) {
            while (left < right && !s[left].isLetterOrDigit()) left++
            while (left < right && !s[right].isLetterOrDigit()) right--

            if (s[left].lowercaseChar() != s[right].lowercaseChar()) return false

            left++
            right--
        }

        return true
    }
}

fun main() {
    val obj = ValidPalindrome()
    println(obj.isPalindrome("A man, a plan, a canal: Panama"))
    println(obj.isPalindromeOptimized("A man, a plan, a canal: Panama"))
}