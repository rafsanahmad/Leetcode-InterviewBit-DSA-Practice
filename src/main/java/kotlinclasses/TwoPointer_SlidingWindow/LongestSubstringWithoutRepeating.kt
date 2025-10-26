/*
 * *
 *  * Longest Substring Without Repeating Characters.kt
 *  * Created by Rafsan Ahmad on 6/24/25, 8:08PM
 *  * Copyright (c) 2025 . All rights reserved.
 *  *
 *
 */

package kotlinclasses.TwoPointer_SlidingWindow

import kotlin.math.max

class LongestSubstringWithoutRepeating {
    //https://leetcode.com/problems/longest-substring-without-repeating-characters/description/
    /*Given a string s, find the length of the longest substring without duplicate characters.

Example 1:
Input: s = "abcabcbb"
Output: 3
Explanation: The answer is "abc", with the length of 3.

Example 2:
Input: s = "bbbbb"
Output: 1
Explanation: The answer is "b", with the length of 1.

Example 3:
Input: s = "pwwkew"
Output: 3
Explanation: The answer is "wke", with the length of 3.
Notice that the answer must be a substring, "pwke" is a subsequence and not a substring.

Constraints:
0 <= s.length <= 5 * 10^4
s consists of English letters, digits, symbols and spaces.*/

    fun lengthOfLongestSubstring(s: String): Int {
        var maxCount = 0
        var i = 0
        var j = 0
        val strLen = s.length

        //Declare set of characters
        val st: MutableSet<Char> = hashSetOf()

        //Traverse a string
        while (i < strLen && j < strLen) {
            //If it's a unique character add in a set

            if (!st.contains(s[j])) {
                st.add(s[j])
                j++
                maxCount = max(maxCount, (j - i))
            } else {
                st.remove(s[i])
                i++
            }
        }

        return maxCount
    }

    fun lengthOfLongestSubstring2(s: String): Int {
        val map = mutableMapOf<Char, Int>()
        var result = 0
        var left = 0

        for (right in s.indices) {
            val ch = s[right]
            val prevIndex = map[ch]
            if (prevIndex != null) {
                left = maxOf(left, prevIndex + 1)
            }
            map[ch] = right
            result = maxOf(result, right - left + 1)
        }

        return result
    }
}

fun main() {
    val obj = LongestSubstringWithoutRepeating()
    println(obj.lengthOfLongestSubstring("abcabcbb"))
    println(obj.lengthOfLongestSubstring("pwwkew"))
    println(obj.lengthOfLongestSubstring2("abcabcbb"))
    println(obj.lengthOfLongestSubstring2("pwwkew"))
}