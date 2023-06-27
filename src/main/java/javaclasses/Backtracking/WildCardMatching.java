/*
 * *
 *  * WildCardMatching.java
 *  * Created by Rafsan Ahmad on 6/27/23, 5:08 PM
 *  * Copyright (c) 2023 . All rights reserved.
 *  *
 *
 */

package javaclasses.Backtracking;

public class WildCardMatching {
    //https://leetcode.com/problems/wildcard-matching/
    /*Given an input string (s) and a pattern (p), implement wildcard pattern matching with
    support for '?' and '*' where:

'?' Matches any single character.
'*' Matches any sequence of characters (including the empty sequence).
The matching should cover the entire input string (not partial).



Example 1:

Input: s = "aa", p = "a"
Output: false
Explanation: "a" does not match the entire string "aa".
Example 2:

Input: s = "aa", p = "*"
Output: true
Explanation: '*' matches any sequence.
Example 3:

Input: s = "cb", p = "?a"
Output: false
Explanation: '?' matches 'c', but the second letter is 'a', which does not match 'b'.


Constraints:

0 <= s.length, p.length <= 2000
s contains only lowercase English letters.
p contains only lowercase English letters, '?' or '*'.*/

    public boolean isMatch(String s, String p) {
        int sLen = s.length();
        int pLen = p.length();
        int i = 0, j = 0, prevWildChar = -1, prevMatch = 0;
        while (i < sLen) {
            if (j < pLen && (p.charAt(j) == '?' || s.charAt(i) == p.charAt(j))) {
                i++;
                j++;
            } else if (j < pLen && p.charAt(j) == '*') {
                prevWildChar = j;
                prevMatch = i;
                j++;
            } else if (prevWildChar != -1) {
                j = prevWildChar + 1;
                i = prevMatch++;
            } else
                return false;
        }

        while (j < pLen && p.charAt(j) == '*') {
            j++;
        }

        return j == pLen;
    }

    public static void main(String[] args) {
        WildCardMatching matching = new WildCardMatching();
        System.out.println(matching.isMatch("abcdef","ab*"));
        System.out.println(matching.isMatch("abcdef","ab*?f"));
        System.out.println(matching.isMatch("abcdef","ab***?r"));

    }
}