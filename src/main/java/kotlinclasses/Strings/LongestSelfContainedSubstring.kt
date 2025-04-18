/*
 * *
 *  * Longest Self Contained Substring.kt
 *  * Created by Rafsan Ahmad on 4/18/25, 5:17 PM
 *  * Copyright (c) 2025 . All rights reserved.
 *  *
 *
 */

package kotlinclasses.Strings

import java.util.Arrays
import kotlin.math.max

class LongestSelfContainedSubstring {
    /*Given a string s, your task is to find the length of the longest self-contained substring of s.

A substring t of a string s is called self-contained if t != s and for every character in t,
it doesn't exist in the rest of s.

Return the length of the longest self-contained substring of s if it exists, otherwise, return -1.


Example 1:
Input: s = "abba"
Output: 2

Explanation:
Let's check the substring "bb". You can see that no other "b" is outside of this substring.
Hence the answer is 2.

Example 2:
Input: s = "abab"
Output: -1

Explanation:
Every substring we choose does not satisfy the described property (there is some character
which is inside and outside of that substring). So the answer would be -1.

Example 3:

Input: s = "abacd"

Output: 4

Explanation:
Let's check the substring "abac". There is only one character outside of this substring and that
is "d". There is no "d" inside the chosen substring, so it satisfies the condition and the answer
 is 4.


Constraints:
2 <= s.length <= 5 * 10^4
s consists only of lowercase English letters.*/

    fun maxSubstringLength(s: String): Int {
        // Array to store the first occurrence index of each character
        val firstOccurrence = IntArray(26)
        // Array to store the last occurrence index of each character
        val lastOccurrence = IntArray(26)
        // Initialize all elements of the firstOccurrence array to -1
        Arrays.fill(firstOccurrence, -1)

        // Length of the input string
        val length = s.length


        // Populate the firstOccurrence and lastOccurrence arrays
        for (i in 0..<length) {
            // Calculate the index of the character in the alphabet
            val charIndex = s.get(i) - 'a'
            if (firstOccurrence[charIndex] == -1) {
                // Set first occurrence if it has not been set
                firstOccurrence[charIndex] = i
            }
            // Update last occurrence to the current index
            lastOccurrence[charIndex] = i
        }

        // Variable to store the maximum substring length found
        var maxSubstringLen = -1


        // Iterate over each character in the alphabet
        for (k in 0..25) {
            // Get the first occurrence index
            val firstIndex = firstOccurrence[k]
            if (firstIndex == -1) {
                // If the character does not appear in the string, skip
                continue
            }
            // Get the last occurrence index
            var maxLastIndex = lastOccurrence[k]


            // Iterate over the substring starting from the first occurrence of character k
            for (j in firstIndex..<length) {
                val start = firstOccurrence[s[j] - 'a']
                val end = lastOccurrence[s[j] - 'a']
                if (start < firstIndex) {
                    // If a character appears before the start of this substring, break
                    break
                }

                // Update maxLastIndex to ensure covering the full substring
                maxLastIndex = max(maxLastIndex, end)

                // Check if current segment is a valid substring and update the max length
                if (maxLastIndex == j && j - firstIndex + 1 < length) {
                    maxSubstringLen =
                        max(maxSubstringLen, (j - firstIndex + 1))
                }
            }
        }

        return maxSubstringLen
    }
}

fun main(strs: Array<String>) {
    val substring = LongestSelfContainedSubstring()
    println(substring.maxSubstringLength("abaghb"))
    println(substring.maxSubstringLength("abacd"))
}