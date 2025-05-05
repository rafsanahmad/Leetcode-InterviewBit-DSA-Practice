/*
 * *
 *  * Unique Length-3 Palindromic Subsequences.kt
 *  * Created by Rafsan Ahmad on 5/4/25, 11:21PM
 *  * Copyright (c) 2025 . All rights reserved.
 *  *
 *
 */

package kotlinclasses.Strings

class UniquePalindromicSubsequence {
    /*Given a string s, return the number of unique palindromes of length three that are a
    subsequence of s.

Note that even if there are multiple ways to obtain the same subsequence, it is still only counted
 once.

A palindrome is a string that reads the same forwards and backwards.

A subsequence of a string is a new string generated from the original string with some characters
 (can be none) deleted without changing the relative order of the remaining characters.

For example, "ace" is a subsequence of "abcde".


Example 1:

Input: s = "aabca"
Output: 3
Explanation: The 3 palindromic subsequences of length 3 are:
- "aba" (subsequence of "aabca")
- "aaa" (subsequence of "aabca")
- "aca" (subsequence of "aabca")

Example 2:
Input: s = "adc"
Output: 0
Explanation: There are no palindromic subsequences of length 3 in "adc".
Example 3:

Input: s = "bbcbaba"
Output: 4
Explanation: The 4 palindromic subsequences of length 3 are:
- "bbb" (subsequence of "bbcbaba")
- "bcb" (subsequence of "bbcbaba")
- "bab" (subsequence of "bbcbaba")
- "aba" (subsequence of "bbcbaba")


Constraints:

3 <= s.length <= 10^5
s consists of only lowercase English letters.*/

    fun countPalindromicSubsequence(s: String): Int {
        val first = IntArray(26) { -1 }
        val last = IntArray(26) { -1 }
        var ans = 0
        for (i in s.indices) {
            val index = s[i] - 'a'
            if (first[index] == -1) first[index] = i

            last[index] = i
        }

        for (i in 0..25) {
            if (first[i] == -1) continue
            val charBetween: HashSet<Char> = hashSetOf()

            for (j in first[i] + 1 until last[i]) {
                charBetween.add(s[j])
            }

            ans += charBetween.size
        }
        return ans
    }
}

fun main() {
    val palindromicSubsequence = UniquePalindromicSubsequence()
    println(palindromicSubsequence.countPalindromicSubsequence("bbcbaba"))
}