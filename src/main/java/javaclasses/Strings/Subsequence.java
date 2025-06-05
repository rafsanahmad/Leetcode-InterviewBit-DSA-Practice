/*
 * * Is Subsequence.java
 *  * Created by Rafsan Ahmad on 10/25/21, 9:49 PM
 *  * Copyright (c) 2021 . All rights reserved.
 *
 */

package javaclasses.Strings;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Subsequence {
    //https://leetcode.com/problems/is-subsequence/description/
    /*Given two strings s and t, return true if s is a subsequence of t, or false otherwise.
A subsequence of a string is a new string that is formed from the original string by deleting some
 (can be none) of the characters without disturbing the relative positions of the remaining
 characters. (i.e., "ace" is a subsequence of "abcde" while "aec" is not).

Example 1:
Input: s = "abc", t = "ahbgdc"
Output: true

Example 2:
Input: s = "axc", t = "ahbgdc"
Output: false


Constraints:
0 <= s.length <= 100
0 <= t.length <= 10^4
s and t consist only of lowercase English letters.


Follow up: Suppose there are lots of incoming s, say s1, s2, ..., sk where k >= 109, and you
want to check one by one to see if t has its subsequence. In this scenario, how would you
change your code?*/

    //Check If a String is Subsequence of Another String - Java Code
    //S = subsequence string, t = main string
    public boolean isSubsequence(String s, String t) {

        int sLen = s.length();
        int tLen = t.length();

        if (sLen == 0)
            return true;

        int sIndex = 0;
        int tIndex = 0;

        while (sIndex < sLen && tIndex < tLen) {

            if (s.charAt(sIndex) == t.charAt(tIndex))
                sIndex++;

            tIndex++;
        }

        return sIndex == sLen;
    }

    //Follow UP
    /*🔄 Idea:
Preprocess t once, storing the indices of each character in t.
Then for each s_i, use binary search to efficiently find the next matching index.

🧠 Why It Works:
You build a map of character → list of indices.

For each character in s_i, you find the next available index after the previous match using
binary search.*/

    public static class SubsequenceChecker {
        // Map each character in 't' to a list of indices where it appears
        private Map<Character, List<Integer>> indexMap;

        /**
         * Constructor: Preprocesses the string 't' and stores the indices of each character
         *
         * @param t The target string in which subsequences will be checked
         */
        public SubsequenceChecker(String t) {
            indexMap = new HashMap<>();
            for (int i = 0; i < t.length(); i++) {
                char c = t.charAt(i);
                // Add the index to the list corresponding to character c
                indexMap.computeIfAbsent(c, k -> new ArrayList<>()).add(i);
            }
        }

        /**
         * Checks whether string 's' is a subsequence of 't'
         *
         * @param s The string to be checked as a subsequence
         * @return true if s is a subsequence of t, false otherwise
         */
        public boolean isSubsequence(String s) {
            int prevIndex = -1; // Previous matched character's index in 't'

            for (char c : s.toCharArray()) {
                // If character not present in 't', s cannot be a subsequence
                if (!indexMap.containsKey(c)) return false;

                List<Integer> positions = indexMap.get(c);
                int nextIndex = findNextIndex(positions, prevIndex);
                if (nextIndex == -1) return false; // No valid next position found

                prevIndex = nextIndex; // Move to the next character's index
            }

            return true;
        }

        /**
         * Helper function to find the smallest index in 'positions' that is > prevIndex
         * Uses binary search for efficiency
         *
         * @param positions List of indices where the current character appears in 't'
         * @param prevIndex The index of the previous matched character
         * @return The next index after prevIndex, or -1 if not found
         */
        private int findNextIndex(List<Integer> positions, int prevIndex) {
            int left = 0, right = positions.size() - 1;
            int result = -1;

            // Binary search to find the first index > prevIndex
            while (left <= right) {
                int mid = left + (right - left) / 2;
                if (positions.get(mid) > prevIndex) {
                    result = positions.get(mid);
                    right = mid - 1; // Try to find a smaller valid index
                } else {
                    left = mid + 1;
                }
            }

            return result;
        }
    }

    public static void main(String[] args) {
        Subsequence subsequence = new Subsequence();
        System.out.println(subsequence.isSubsequence("abd", "abcde"));

        SubsequenceChecker checker = new SubsequenceChecker("abcde");
        System.out.println(checker.isSubsequence("ace")); // true
        System.out.println(checker.isSubsequence("aec")); // false
    }
}

 