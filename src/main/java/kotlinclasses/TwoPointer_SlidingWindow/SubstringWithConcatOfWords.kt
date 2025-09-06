/*
 * *
 *  * Substring with Concatenation of All Words.kt
 *  * Created by Rafsan Ahmad on 9/2/25, 9:26PM
 *  * Copyright (c) 2025 . All rights reserved.
 *  *
 *
 */

package kotlinclasses.TwoPointer_SlidingWindow

class SubstringWithConcatOfWords {
    //https://leetcode.com/problems/substring-with-concatenation-of-all-words/description/
    /*You are given a string s and an array of strings words. All the strings of words are of the same
     length.

A concatenated string is a string that exactly contains all the strings of any permutation of words
concatenated.

For example, if words = ["ab","cd","ef"], then "abcdef", "abefcd", "cdabef", "cdefab", "efabcd",
and "efcdab" are all concatenated strings. "acdbef" is not a concatenated string because it is not
the concatenation of any permutation of words.
Return an array of the starting indices of all the concatenated substrings in s. You can return the
answer in any order.

Example 1:
Input: s = "barfoothefoobarman", words = ["foo","bar"]

Output: [0,9]

Explanation:

The substring starting at 0 is "barfoo". It is the concatenation of ["bar","foo"] which is a
permutation of words.
The substring starting at 9 is "foobar". It is the concatenation of ["foo","bar"] which is a
permutation of words.

Example 2:
Input: s = "wordgoodgoodgoodbestword", words = ["word","good","best","word"]
Output: []

Explanation:

There is no concatenated substring.

Example 3:
Input: s = "barfoofoobarthefoobarman", words = ["bar","foo","the"]
Output: [6,9,12]

Explanation:
The substring starting at 6 is "foobarthe". It is the concatenation of ["foo","bar","the"].
The substring starting at 9 is "barthefoo". It is the concatenation of ["bar","the","foo"].
The substring starting at 12 is "thefoobar". It is the concatenation of ["the","foo","bar"].


Constraints:
1 <= s.length <= 10^4
1 <= words.length <= 5000
1 <= words[i].length <= 30
s and words[i] consist of lowercase English letters.*/


    /*Variables:
n = s.length → length of the input string
m = words.size → number of words
k = words[0].length → length of each word (all words same length)

Total Time Complexity:
O(m + n * k)
m for building countMap
n*k for sliding window and substring extraction

Space Complexity:
countMap: O(m) → stores frequency of words
seen: O(m) → at most number of unique words in current window
result: O(n) → worst case all positions are valid starting indices
Total Space Complexity:
O(m + n)  (m for words, n for output list)
*/
    fun findSubstring(s: String, words: Array<String>): List<Int> {
        // Edge case: return empty list if string or words array is empty
        if (s.isEmpty() || words.isEmpty()) return listOf()

        val result: MutableList<Int> = mutableListOf()
        val countMap: MutableMap<String, Int> = mutableMapOf()

        // Build a frequency map of words
        for (i in words.indices) {
            val str = words[i]
            countMap[str] = countMap.getOrDefault(str, 0) + 1
        }

        val wordLen = words[0].length // All words have the same length
        val totalWords = words.size

        // Traverse the string starting at each offset within the word length
        for (i in 0 until wordLen) {
            traverse(s, i, countMap, result, wordLen, totalWords)
        }

        return result
    }

    fun traverse(
        s: String,
        index: Int,
        countMap: MutableMap<String, Int>,
        result: MutableList<Int>,
        wordLen: Int,
        totalWords: Int
    ) {
        var seen: MutableMap<String, Int> = mutableMapOf() // Tracks words in the current window
        var left = index  // Left boundary of sliding window
        var right = index // Right boundary (exclusive) of window

        while (right + wordLen <= s.length) {
            val subStr = s.substring(right, right + wordLen) // Get the next word of length wordLen

            // If the word is not in the original list, reset seen map and move both pointers
            if (!countMap.containsKey(subStr)) {
                seen = mutableMapOf() // Clear seen counts
                right += wordLen       // Move right pointer forward
                left = right           // Reset left pointer to match right
                continue
            }

            // Add current word to seen map
            seen[subStr] = seen.getOrDefault(subStr, 0) + 1

            val originalCount = countMap[subStr] ?: 0
            var currCount = seen[subStr] ?: 0

            // Shrink the window from the left if the word occurs more than allowed
            while (currCount > originalCount) {
                val leftStr = s.substring(left, left + wordLen) // Get the leftmost word
                seen[leftStr] = seen.getOrDefault(leftStr, 0) - 1 // Reduce its count
                currCount = seen[subStr] ?: 0                     // Update current word count
                left += wordLen                                   // Move left pointer forward
            }

            right += wordLen // Move right pointer forward to include next word

            // If current window matches total length of all words, record starting index
            if (right - left == wordLen * totalWords)
                result.add(left)
        }
    }
}

fun main() {
    val s = "barfoofoobarthefoobarman"
    val words = arrayOf("bar", "foo", "the")
    val obj = SubstringWithConcatOfWords()
    println(obj.findSubstring(s, words))
}
