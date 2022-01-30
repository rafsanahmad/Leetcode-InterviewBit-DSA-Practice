/*
 * *
 *  * Longest Palindrome.java
 *  * Created by Rafsan Ahmad on 1/30/22, 11:45 PM
 *  * Copyright (c) 2022 . All rights reserved.
 *
 */

package javaclasses.GreedyAlgorithm;

import java.util.HashMap;
import java.util.Map;

public class LongestPalindrome {
    /*Given a string s which consists of lowercase or uppercase letters, return the length of the longest palindrome
    that can be built with those letters.

Letters are case sensitive, for example, "Aa" is not considered a palindrome here.

Example 1:
Input: s = "abccccdd"
Output: 7
Explanation:
One longest palindrome that can be built is "dccaccd", whose length is 7.

Example 2:
Input: s = "a"
Output: 1

Example 3:
Input: s = "bb"
Output: 2*/

    public int longestPalindrome(String s) {
        int result = 0;
        int len = s.length();
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < len; i++) {
            char ch = s.charAt(i);
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }
        boolean oddFound = false;

        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            int value = entry.getValue();
            if (map.size() == 1) {
                return value;
            }
            if (value % 2 == 0) {
                result += value;
            } else {
                //Odd case
                oddFound = true;
                if (value > 1) {
                    //Take immediate even
                    result += (value - 1);
                }
            }
        }
        if (oddFound) result += 1;

        return result;
    }

    public static void main(String[] args) {
        LongestPalindrome palindrome = new LongestPalindrome();
        System.out.println(palindrome.longestPalindrome("abccccdd"));
        System.out.println(palindrome.longestPalindrome("a"));
        System.out.println(palindrome.longestPalindrome("bb"));
        System.out.println(palindrome.longestPalindrome("Aa"));
        System.out.println(palindrome.longestPalindrome("AaaBBBcCdda"));
        System.out.println(palindrome.longestPalindrome("ccc"));
        System.out.println(palindrome.longestPalindrome("ababababa"));
    }
}
