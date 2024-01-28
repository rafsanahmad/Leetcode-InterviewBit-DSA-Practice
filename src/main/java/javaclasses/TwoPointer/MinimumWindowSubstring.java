/*
 * *
 *  * Minimum Window Substring.java
 *  * Created by Rafsan Ahmad on 1/28/24, 11:45 PM
 *  * Copyright (c) 2024 . All rights reserved.
 *  *
 *
 */

package javaclasses.TwoPointer;

public class MinimumWindowSubstring {
    //https://leetcode.com/problems/minimum-window-substring/
    /*Given two strings s and t of lengths m and n respectively, return the minimum window
substring
 of s such that every character in t (including duplicates) is included in the window.
 If there is no such substring, return the empty string "".

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
s and t consist of uppercase and lowercase English letters.*/

/*Algorithm:
1. Use two pointers: start and end to represent a window.
2. Move end to find a valid window.
3. When a valid window is found, move start to find a smaller window.
*/
    public String minWindow(String s, String t) {
        // Array to store the frequency of characters needed from the target string
        int[] charFrequencyInTarget = new int[128];
        // Array to store the frequency of characters in the current window of the source string
        int[] charFrequencyInWindow = new int[128];

        int sLen = s.length();
        int tLen = t.length();

        // Populate the frequency array for the target string
        for (int i = 0; i < tLen; ++i) {
            charFrequencyInTarget[t.charAt(i)]++;
        }

        int matchCount = 0; // Count of characters that matches from target
        int start = 0; // The start index of the current window
        int end = 0;
        int minStart = -1; // The start index of the minimum window
        int minLength = Integer.MAX_VALUE; // Length of the smallest window

        // Iterate over the source string
        while (end < sLen) {
            char ch = s.charAt(end);
            // Include the current character in the window
            charFrequencyInWindow[ch]++;

            // If the character is needed and we have not more than needed, increase the match count
            if (charFrequencyInTarget[ch] >= charFrequencyInWindow[ch]) {
                matchCount++;
            }

            // When we have all characters from the target in our window
            while (matchCount == tLen) {
                int windowLength = end - start + 1; // Get the current window's length

                // Update minimum length and starting index if a new minimum is found
                if (windowLength < minLength) {
                    minLength = windowLength;
                    minStart = start;
                }

                // The character at window start is going to be removed since window is moving forward
                char charAtStart = s.charAt(start);

                // If the character is one that is needed and after
                // removing there are not enough of it, decrease match count
                if (charFrequencyInTarget[charAtStart] >= charFrequencyInWindow[charAtStart]) {
                    matchCount--;
                }

                // Remove the character from the window
                charFrequencyInWindow[charAtStart]--;
                start++; // Move the window forward
            }

            end++;
        }

        // Return the minimum window substring or an empty string if no such window exists
        return minStart < 0 ? "" : s.substring(minStart, minStart + minLength);
    }

    public static void main(String[] args) {
        MinimumWindowSubstring substring = new MinimumWindowSubstring();
        String s = "ADOBECODEBANC", t = "ABC";
        System.out.println(substring.minWindow(s, t));
    }
}
