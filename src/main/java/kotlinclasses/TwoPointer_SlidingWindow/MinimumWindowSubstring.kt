/*
 * *
 *  * Minimum Window Substring.kt
 *  * Created by Rafsan Ahmad on 8/27/25, 4:41PM
 *  * Copyright (c) 2025 . All rights reserved.
 *  *
 *
 */

package kotlinclasses.TwoPointer_SlidingWindow

class MinimumWindowSubstring {
    //https://leetcode.com/problems/minimum-window-substring/description/
    /*Given two strings s and t of lengths m and n respectively, return the minimum window substring
    of s such that every character in t (including duplicates) is included in the window. If there is
    no such substring, return the empty string "".

The testcases will be generated such that the answer is unique.

Example 1:
Input: s = "ADOBECODEBANC", t = "ABC"
Output: "BANC"
Explanation: The minimum window substring "BANC" includes 'A', 'B', and 'C' from string t.

Example 2:
Input: s = "a", t = "a"
Output: "a"
Explanation: The entire string s is the minimum window.

Example 3:
Input: s = "a", t = "aa"
Output: ""
Explanation: Both 'a's from t must be included in the window.
Since the largest window of s only has one 'a', return empty string.


Constraints:
m == s.length
n == t.length
1 <= m, n <= 10^5
s and t consist of uppercase and lowercase English letters.


Follow up: Could you find an algorithm that runs in O(m + n) time?*/

    /*Algorithm:
1. Use two pointers: start and end to represent a window.
2. Move end to find a valid window.
3. When a valid window is found, move start to find a smaller window.
*/
    //Time Complexity: O(|s| + |t|)
    //Space Complexity: O(1)
    fun minWindow(s: String, t: String): String {
        val sLen = s.length
        val tLen = t.length

        if (s.isEmpty() || t.isEmpty() || tLen > sLen)
            return ""

        val sCount = IntArray(128) { 0 }
        val tCount = IntArray(128) { 0 }
        for (i in t.indices) {
            tCount[t[i].code]++
        }

        var start = 0
        var end = 0
        var minLen = Integer.MAX_VALUE
        var matchCount = 0
        var minStart = 0

        while (end < sLen) {
            val ch = s[end].code
            sCount[ch]++
            if (sCount[ch] <= tCount[ch]) {
                matchCount++
            }

            while (matchCount == tLen) {
                val windowLen = end - start + 1
                if (windowLen < minLen) {
                    minLen = windowLen
                    minStart = start
                }

                val removeChar = s[start].code
                sCount[removeChar]--
                if (tCount[removeChar] > sCount[removeChar])
                    matchCount--
                start++
            }

            end++
        }

        return if (minLen == Integer.MAX_VALUE) "" else
            s.substring(minStart, minStart + minLen)
    }


    //Supports Unicode - using MAP
    /*128 → enough for ASCII-only problems (typical interview/LeetCode assumption).
256 → extended ASCII, but still doesn’t cover Unicode.
HashMap → best if you want general correctness for all inputs.
*/

    //Time Complexity: O(|s| + |t|)
    //Space Complexity: O(|s| + |t|)
    fun minWindowApproach2(s: String, t: String): String {
        val sLen = s.length
        val tLen = t.length

        if (s.isEmpty() || t.isEmpty() || tLen > sLen)
            return ""

        val sMap: MutableMap<Char, Int> = mutableMapOf()
        val tMap: MutableMap<Char, Int> = mutableMapOf()
        for (i in t.indices) {
            tMap.put(t[i], tMap.getOrDefault(t[i], 0) + 1)
        }

        var start = 0
        var end = 0
        var minLen = Integer.MAX_VALUE
        var matchCount = 0
        var minStart = 0

        while (end < sLen) {
            val ch = s[end]
            sMap.put(ch, sMap.getOrDefault(ch, 0) + 1)
            if (tMap.contains(ch) && sMap[ch] == tMap[ch]) {
                matchCount++
            }

            // matchCount should count number of unique characters fully matched, not total characters.
            // So instead of matchCount == tLen, you should check matchCount == tMap.size
            while (matchCount == tMap.size) {
                val windowLen = end - start + 1
                if (windowLen < minLen) {
                    minLen = windowLen
                    minStart = start
                }

                val removeChar = s[start]
                sMap[removeChar]?.let {
                    sMap[removeChar] = it - 1
                }

                val tCount = tMap[removeChar] ?: 0
                val sCount = sMap[removeChar] ?: 0
                if (tCount > sCount)
                    matchCount--
                start++
            }

            end++
        }

        return if (minLen == Integer.MAX_VALUE) "" else
            s.substring(minStart, minStart + minLen)
    }
}

fun main() {
    val obj = MinimumWindowSubstring()
    val s = "ADOBECODEBANC"
    val t = "ABC"
    println(obj.minWindow(s, t))
    println(obj.minWindowApproach2(s, t))
}