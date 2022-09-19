/*
 * *
 *  * Longest Substring With K Repeated Characters.java
 *  * Created by Rafsan Ahmad on 1/17/22, 1:32 PM
 *  * Copyright (c) 2022 . All rights reserved.
 *
 */

package javaclasses.Divide_and_Conquer;

import java.util.Arrays;

public class LongestSubstringWith_K_RepeatedChars {
    //Leetcode 395
    /*Given a string s and an integer k, return the length of the longest substring of s such that the frequency
    of each character in this substring is greater than or equal to k.

Example 1:

Input: s = "aaabb", k = 3
Output: 3
Explanation: The longest substring is "aaa", as 'a' is repeated 3 times.

Example 2:
Input: s = "ababbc", k = 2
Output: 5
Explanation: The longest substring is "ababb", as 'a' is repeated 2 times and 'b' is repeated 3 times.*/

    //Brute Force
    //Time Complexity = O(n^2)
    public int longestSubstring(String s, int k) {
        int[] countMap = new int[32];
        int maxLength = 0;
        int len = s.length();
        for (int start = 0; start < len; start++) {
            // reset the count map
            Arrays.fill(countMap, 0);
            for (int end = start; end < len; end++) {
                countMap[s.charAt(end) - 'a']++;
                if (checkValue(k, countMap)) {
                    maxLength = Math.max(maxLength, end - start + 1);
                }
            }
        }
        return maxLength;
    }

    private boolean checkValue(int k, int[] countMap) {
        int countLetters = 0, countAtLeastK = 0;
        for (int freq : countMap) {
            if (freq > 0) countLetters++;
            if (freq >= k) countAtLeastK++;
        }
        return countAtLeastK == countLetters;
    }


    //Approach 2: Using Divide and conquer
    //res/divide_and_conquer.png
    public int longestSubstring2(String s, int k) {
        return longestSubstringUtil(s, 0, s.length(), k);
    }

    int longestSubstringUtil(String s, int start, int end, int k) {
        if (end < k) return 0;
        int[] countMap = new int[26];
        // update the countMap with the count of each character
        for (int i = start; i < end; i++)
            countMap[s.charAt(i) - 'a']++;
        for (int mid = start; mid < end; mid++) {
            if (countMap[s.charAt(mid) - 'a'] >= k) continue;
            int midNext = mid + 1;
            while (midNext < end && countMap[s.charAt(midNext) - 'a'] < k) midNext++;
            return Math.max(longestSubstringUtil(s, start, mid, k),
                    longestSubstringUtil(s, midNext, end, k));
        }
        return (end - start);
    }

    public static void main(String[] args) {
        LongestSubstringWith_K_RepeatedChars chars = new LongestSubstringWith_K_RepeatedChars();
        System.out.println(chars.longestSubstring("aaabb", 3));
        System.out.println(chars.longestSubstring("ababbc", 2));
        System.out.println(chars.longestSubstring2("ababbc", 2));
    }
}
