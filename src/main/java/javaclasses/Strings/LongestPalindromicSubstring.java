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

    public String expand(int low, int high, String str) {
        String palindrome = "";
        int maxLength = Integer.MIN_VALUE;
        int len = str.length();
        while (low >= 0 && high < len && str.charAt(low) == str.charAt(high)) {
            int pLength = (high + 1) - low;
            if (pLength > maxLength) {
                maxLength = pLength;
                palindrome = str.substring(low, high + 1);
            }
            low--;
            high++;
        }
        return palindrome;
    }

    public String longestPalindrome(String s) {
        if (s.length() == 0) return "";

        int maxLength = Integer.MIN_VALUE;
        String res = "";
        for (int i = 0; i < s.length(); i++) {
            String oddPalindrome = expand(i, i, s);

            if (oddPalindrome.length() > maxLength) {
                maxLength = oddPalindrome.length();
                res = oddPalindrome;
            }

            String evenPalindrome = expand(i, i + 1, s);

            if (evenPalindrome.length() > maxLength) {
                maxLength = evenPalindrome.length();
                res = evenPalindrome;
            }

        }
        return res;
    }

    public static void main(String[] args) {
        LongestPalindromicSubstring substring = new LongestPalindromicSubstring();
        System.out.println(substring.longestPalindrome("babad"));
        System.out.println(substring.longestPalindrome("cbbd"));
    }
}
