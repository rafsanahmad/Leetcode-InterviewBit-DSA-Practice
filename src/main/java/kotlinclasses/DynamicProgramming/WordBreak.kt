/*
 * *
 *  * Word Break.kt
 *  * Created by Rafsan Ahmad on 7/27/25, 6:20PM
 *  * Copyright (c) 2025 . All rights reserved.
 *  *
 *
 */

package kotlinclasses.DynamicProgramming

class WordBreak {
    //https://leetcode.com/problems/word-break/description/
    /*Given a string s and a dictionary of strings wordDict, return true if s can be segmented into
    a space-separated sequence of one or more dictionary words.

Note that the same word in the dictionary may be reused multiple times in the segmentation.

Example 1:
Input: s = "leetcode", wordDict = ["leet","code"]
Output: true
Explanation: Return true because "leetcode" can be segmented as "leet code".

Example 2:
Input: s = "applepenapple", wordDict = ["apple","pen"]
Output: true
Explanation: Return true because "applepenapple" can be segmented as "apple pen apple".
Note that you are allowed to reuse a dictionary word.

Example 3:
Input: s = "catsandog", wordDict = ["cats","dog","sand","and","cat"]
Output: false


Constraints:
1 <= s.length <= 300
1 <= wordDict.length <= 1000
1 <= wordDict[i].length <= 20
s and wordDict[i] consist of only lowercase English letters.
All the strings of wordDict are unique.*/

    /*What does dp[i] mean?
dp[i] means:
Can the substring starting at index i (i.e. s[i..end)) be segmented into valid dictionary words?
dp[i] == true → Yes, from i to the end of the string can be segmented.
dp[i] == false → No valid segmentation starting at index i.
dp[i] == null → Not yet computed.
*/
    //Time complexity: O(n) (recursion) × O(n) (inner loop) × O(n) (substring) = O(n³)
    //Space Complexity: O(n) for dp[] + O(m) for dictionary + O(n) for recursion stack = O(n + m)
    fun wordBreak(s: String, wordDict: List<String>): Boolean {
        // Edge case: empty string is considered segmented
        if (s.isEmpty()) return true

        val sLen = s.length

        // dp[i] = true/false/null
        // dp[i] stores whether s[i..end) can be segmented
        val dp: Array<Boolean?> = Array(sLen) { null }

        // Convert list to set for O(1) lookup
        val dictSet = wordDict.toHashSet()

        // Start checking from index 0
        return wordBreakDP(s, dictSet, dp, 0)
    }

    fun wordBreakDP(
        s: String,
        wordDict: HashSet<String>,
        dp: Array<Boolean?>,
        start: Int
    ): Boolean {
        // Base case: reached end of string, so it was successfully segmented
        if (start == s.length) return true

        // If already computed, return cached result
        dp[start]?.let { return it }

        // Try all possible end indices to split s[start..end)
        for (end in start + 1..s.length) {
            val substring = s.substring(start, end)

            // If substring is in dictionary and rest of string can be segmented
            if (wordDict.contains(substring) && wordBreakDP(s, wordDict, dp, end)) {
                dp[start] = true
                return true
            }
        }

        // No valid segmentation from start
        dp[start] = false
        return false
    }

    // Time Complexity = O(n³)
    // Space Complexity = O(n + m)
    fun wordBreakBottomUp(s: String, wordDict: List<String>): Boolean {
        // Edge case: empty string is considered segmentable
        if (s.isEmpty()) return true

        val sLen = s.length

        // dp[i] means: can the substring s[0 until i] be segmented using wordDict?
        val dp = BooleanArray(sLen + 1) { false }

        // Convert dictionary to HashSet for O(1) lookup
        val dictSet = wordDict.toHashSet()

        // Empty string can always be segmented
        dp[0] = true

        // Build up dp[i] from smaller segments
        for (start in 0..sLen) {
            for (end in 0..start) {
                val substring = s.substring(end, start)
                // If s[0 until end] is segmentable and s[end until start] is in dict,
                // then s[0 until start] is segmentable
                if (dp[end] && dictSet.contains(substring)) {
                    dp[start] = true
                    break  // Optional: early stop since we found a valid split
                }
            }
        }

        // Final answer: can the full string be segmented?
        return dp[sLen]
    }
}

fun main() {
    val obj = WordBreak()
    println(obj.wordBreakBottomUp("applepenapple", listOf("apple", "pen")))
}