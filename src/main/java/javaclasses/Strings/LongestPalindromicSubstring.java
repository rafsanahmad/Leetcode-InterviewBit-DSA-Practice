/*
 * *
 *  * Longest Palindromic Substring.java
 *  * Created by Rafsan Ahmad on 10/19/22, 2:02 AM
 *  * Copyright (c) 2022 . All rights reserved.
 *
 */

package javaclasses.Strings;

public class LongestPalindromicSubstring {
    //https://leetcode.com/problems/longest-palindromic-substring/
    /*Given a string s, return the longest palindromic substring in s.

A string is called a palindrome string if the reverse of that string is the same as the original string.

Example 1:

Input: s = "babad"
Output: "bab"
Explanation: "aba" is also a valid answer.

Example 2:
Input: s = "cbbd"
Output: "bb"

Constraints:

1 <= s.length <= 1000
s consist of only digits and English letters.*/

    class Substring {
        int start;
        int length;

        Substring(int s, int l) {
            this.start = s;
            this.length = l;
        }
    }

    public Substring expand(int left, int right, String s, Substring sub) {
        int len = s.length();
        while (left >= 0 && right < len && s.charAt(left) == s.charAt(right)) {
            if (right - left + 1 > sub.length) {
                sub.start = left;
                sub.length = right - left + 1;
            }
            left--;
            right++;
        }

        return sub;
    }

    public String longestPalindrome(String s) {
        if (s == null || s.length() == 0) return "";

        String result = "";

        Substring sub = new Substring(0, 1);
        for (int i = 0; i < s.length(); i++) {
            // For even length palindrome
            sub = expand(i, i + 1, s, sub);
            // For odd length palindrome
            sub = expand(i - 1, i + 1, s, sub);
        }

        result = s.substring(sub.start, sub.start + sub.length);

        return result;
    }

    public static void main(String[] args) {
        LongestPalindromicSubstring substring = new LongestPalindromicSubstring();
        System.out.println(substring.longestPalindrome("babad"));
        System.out.println(substring.longestPalindrome("cbbd"));
    }
}
