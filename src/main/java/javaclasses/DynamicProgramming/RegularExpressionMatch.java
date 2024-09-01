/*
 * *
 *  * Regular Expression Matching.java
 *  * Created by Rafsan Ahmad on 9/2/24, 3:32 AM
 *  * Copyright (c) 2024 . All rights reserved.
 *  *
 *
 */

package javaclasses.DynamicProgramming;

public class RegularExpressionMatch {
    //https://leetcode.com/problems/regular-expression-matching/description/
    /*Given an input string s and a pattern p, implement regular expression matching with support
    for '.' and '*' where:

'.' Matches any single character.
'*' Matches zero or more of the preceding element.
The matching should cover the entire input string (not partial).


Example 1:
Input: s = "aa", p = "a"
Output: false
Explanation: "a" does not match the entire string "aa".

Example 2:
Input: s = "aa", p = "a*"
Output: true
Explanation: '*' means zero or more of the preceding element, 'a'.
Therefore, by repeating 'a' once, it becomes "aa".

Example 3:
Input: s = "ab", p = ".*"
Output: true
Explanation: ".*" means "zero or more (*) of any character (.)".


Constraints:
1 <= s.length <= 20
1 <= p.length <= 20
s contains only lowercase English letters.
p contains only lowercase English letters, '.', and '*'.
It is guaranteed for each appearance of the character '*', there will be a previous valid
character to match.*/
    public boolean isMatch(String s, String p) {
        boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];

        return dfs(s, p, 0, 0, dp);
    }

    public boolean dfs(String s, String p, int i, int j, boolean[][] dp) {
        if (i < s.length() && j < p.length() && dp[i][j]) return dp[i][j];

        if (i >= s.length() && j >= p.length()) return true;
        if (j >= p.length()) return false;

        boolean match = (i < s.length() && (s.charAt(i) == p.charAt(j) || p.charAt(j) == '.'));
        if (j + 1 < p.length() && p.charAt(j + 1) == '*') {
            dp[i][j] = dfs(s, p, i, j + 2, dp) || (match && dfs(s, p, i + 1, j, dp)); // use * and don't use *
            return dp[i][j];
        }
        if (match) {
            dp[i][j] = dfs(s, p, i + 1, j + 1, dp);
            return dp[i][j];
        }
        dp[i][j] = false;
        return dp[i][j];
    }

    public static void main(String[] args) {
        RegularExpressionMatch match = new RegularExpressionMatch();
        System.out.println(match.isMatch("aa", "*"));
        System.out.println(match.isMatch("aa", "a"));
        System.out.println(match.isMatch("ab", ".*"));
    }
}
