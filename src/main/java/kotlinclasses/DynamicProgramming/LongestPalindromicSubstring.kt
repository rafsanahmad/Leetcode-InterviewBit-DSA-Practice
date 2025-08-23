/*
 * *
 *  * Longest Palindromic Substring.kt
 *  * Created by Rafsan Ahmad on 8/22/25, 8:43PM
 *  * Copyright (c) 2025 . All rights reserved.
 *  *
 *
 */

package kotlinclasses.DynamicProgramming

class LongestPalindromicSubstring {
    //https://leetcode.com/problems/longest-palindromic-substring/description/
    /*Given a string s, return the longest palindromic substring in s.

Example 1:
Input: s = "babad"
Output: "bab"
Explanation: "aba" is also a valid answer.

Example 2:
Input: s = "cbbd"
Output: "bb"

Constraints:
1 <= s.length <= 1000
s consist of only digits and English letters.*/

    /*What does dp[i][j] mean?
In the 2D DP table,
👉 dp[i][j] = true means the substring from index i to j (inclusive) is a palindrome.
👉 dp[i][j] = false means it’s not a palindrome.
👉 If it’s null in your top-down version, it means we haven’t computed it yet.


Overlapping subproblems
Example: "ababa"
To check if "ababa" (0..4) is palindrome, we need to know if "bab" (1..3) is palindrome.
To check if "bab" is palindrome, we need to know if "a" (2..2) is palindrome.
Now, "bab" might also be needed when checking another substring, say "cbabc" (0..4 in another context).
So "bab" gets recomputed multiple times without DP. Memoization (your dp) avoids that.

Time complexity = O(n²)
Space complexity = O(n²) (to store the DP table).
*/
    fun longestPalindrome(s: String): String {
        if (s.isEmpty()) return s
        val len = s.length
        var start = 0
        var maxLen = 0
        val dp: Array<Array<Boolean?>> = Array(len) {
            Array(len) { null }
        }
        for (i in 0 until len) {
            for (j in i until len) {
                if (longestPalindromeDP(s, i, j, dp)) {
                    val currLen = j - i + 1
                    if (currLen > maxLen) {
                        maxLen = currLen
                        start = i
                    }
                }
            }
        }

        return s.substring(start, start + maxLen)
    }

    fun longestPalindromeDP(s: String, left: Int, right: Int, dp: Array<Array<Boolean?>>): Boolean {
        if (left >= right) {
            dp[left][right] = true
            return true
        }

        dp[left][right]?.let {
            return it
        }

        var result = false
        if (s[left] == s[right]) {
            result = longestPalindromeDP(s, left + 1, right - 1, dp)
        }

        dp[left][right] = result
        return result
    }


    fun longestPalindromeBottomUp(s: String): String {
        if (s.isEmpty()) return s
        val len = s.length
        var start = 0      // track starting index of longest palindrome
        var maxLen = 1     // track length of longest palindrome
        val dp: Array<Array<Boolean?>> = Array(len) {
            Array(len) { null }
        }

        // Every single character is a palindrome of length 1
        for (i in 0 until len) {
            dp[i][i] = true
        }

        // Check substrings of length 2
        for (i in 0 until len - 1) {
            if (s[i] == s[i + 1]) {
                dp[i][i + 1] = true
                start = i
                maxLen = 2
            }
        }

        // Check substrings of length >= 3
        for (right in 2 until len) {
            for (left in 0 until right) {
                // Palindrome if ends match AND (inner substring is palindrome OR length <= 3)
                if (s[left] == s[right] && (right - left <= 2 || dp[left + 1][right - 1] == true)) {
                    dp[left][right] = true

                    // Update longest palindrome if current is longer
                    val curLen = right - left + 1
                    if (curLen > maxLen) {
                        maxLen = curLen
                        start = left
                    }
                }
            }
        }

        // Return the longest palindromic substring
        return s.substring(start, start + maxLen)
    }
}

fun main() {
    val obj = LongestPalindromicSubstring()
    println(obj.longestPalindrome("babad"))
    println(obj.longestPalindromeBottomUp("babad"))
}
