/*
 * *
 *  * Common Longest Substring Pattern.java
 *  * Created by Rafsan Ahmad on 1/29/24, 12:31 AM
 *  * Copyright (c) 2024 . All rights reserved.
 *  *
 *
 */

package javaclasses.TwoPointer;

public class CommonLongestSubstringPattern {
/*Algorithm:
1. Use two pointers: start and end to represent a window.
2. Move end to find a valid window.
3. When a valid window is found, move start to find a smaller window.
*/

    //Longest Substring - at most K distinct characters
    /*Given a string S, find the length of the longest substring T that contains at most k
    distinct characters.

Example
Example 1:
Input: S = "eceba" and k = 3
Output: 4
Explanation: T = "eceb"

Example 2:
Input: S = "WORLD" and k = 4
Output: 4
Explanation: T = "WORL" or "ORLD"*/
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        int[] map = new int[256];
        int start = 0, end = 0, maxLen = Integer.MIN_VALUE, counter = 0;

        while (end < s.length()) {
            final char c1 = s.charAt(end);
            if (map[c1] == 0) counter++;
            map[c1]++;
            end++;

            while (counter > k) {
                final char c2 = s.charAt(start);
                if (map[c2] == 1) counter--;
                map[c2]--;
                start++;
            }

            maxLen = Math.max(maxLen, end - start);
        }

        return maxLen;
    }


    //Longest Substring - at most 2 distinct characters
    /*Given a string, find the length of the longest substring T that contains at most 2
    distinct characters.

Example
Example 1
Input: “eceba”
Output: 3
Explanation:
T is "ece" which its length is 3.

Example 2
Input: “aaa”
Output: 3*/
    public int lengthOfLongestSubstringTwoDistinct(String s) {
        int[] map = new int[128];
        int start = 0, end = 0, maxLen = 0, counter = 0;

        while (end < s.length()) {
            final char c1 = s.charAt(end);
            if (map[c1] == 0) counter++;
            map[c1]++;
            end++;

            while (counter > 2) {
                final char c2 = s.charAt(start);
                if (map[c2] == 1) counter--;
                map[c2]--;
                start++;
            }

            maxLen = Math.max(maxLen, end - start);
        }

        return maxLen;
    }


    //LongestSubstring - without repeating characters
    public int lengthOfLongestSubstringWithoutRepeating(String s) {
        int[] map = new int[128];
        int start = 0, end = 0, maxLen = 0, counter = 0;

        while (end < s.length()) {
            final char c1 = s.charAt(end);
            if (map[c1] > 0) counter++;
            map[c1]++;
            end++;

            while (counter > 0) {
                final char c2 = s.charAt(start);
                if (map[c2] > 1) counter--;
                map[c2]--;
                start++;
            }

            maxLen = Math.max(maxLen, end - start);
        }

        return maxLen;
    }

    public static void main(String[] args) {
        CommonLongestSubstringPattern pattern = new CommonLongestSubstringPattern();
        System.out.println(pattern.lengthOfLongestSubstringKDistinct("WORLD", 4));
        System.out.println(pattern.lengthOfLongestSubstringTwoDistinct("eceba"));
        System.out.println(pattern.lengthOfLongestSubstringTwoDistinct("aaa"));

        System.out.println(pattern.lengthOfLongestSubstringWithoutRepeating("abcabcbb"));
    }
}
