/*
 * *
 *  * Distinct Subsequence.java
 *  * Created by Rafsan Ahmad on 2/4/24, 7:38 PM
 *  * Copyright (c) 2024 . All rights reserved.
 *  *
 *
 */

package javaclasses.DynamicProgramming;

import java.util.Arrays;

public class DistinctSubsequence {
    //https://leetcode.com/problems/distinct-subsequences/description/
    /*Given two strings s and t, return the number of distinct subsequences of s which equals t.

The test cases are generated so that the answer fits on a 32-bit signed integer.



Example 1:

Input: s = "rabbbit", t = "rabbit"
Output: 3
Explanation:
As shown below, there are 3 ways you can generate "rabbit" from s.
rabbbit
rabbbit
rabbbit


Example 2:
Input: s = "babgbag", t = "bag"
Output: 5
Explanation:
As shown below, there are 5 ways you can generate "bag" from s.
babgbag
babgbag
babgbag
babgbag
babgbag


Constraints:

1 <= s.length, t.length <= 1000
s and t consist of English letters.*/


    //Using Memoization - Top Down DP
    public int numDistinctTopDown(String s, String t) {
        int n = s.length();
        int m = t.length();
        if (n == 0 || m == 0) return 0;
        int[][] dp = new int[n][m];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], -1);
        }
        return numDistinctRec(n - 1, m - 1, s, t, dp);
    }

    public int numDistinctRec(int i, int j, String s, String t, int[][] dp) {
        //Base case
        if (j < 0) return 1;
        else if (i < 0) return 0;

        if (dp[i][j] != -1) return dp[i][j];

        if (s.charAt(i) == t.charAt(j)) {
            int take = numDistinctRec(i - 1, j - 1, s, t, dp);
            int donotTake = numDistinctRec(i - 1, j, s, t, dp);
            return dp[i][j] = take + donotTake;
        } else {
            return dp[i][j] = numDistinctRec(i - 1, j, s, t, dp);
        }
    }

    //Tabulation - Bottom up DP
    public int numDistinctBottomUp(String s, String t) {
        int n = s.length();
        int m = t.length();
        if (n == 0 || m == 0) return 0;
        int[][] dp = new int[n + 1][m + 1];

        for (int i = 0; i < n; i++) {
            dp[i][0] = 1;
        }

        for (int j = 1; j < m; j++) {
            dp[0][j] = 0;
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        return dp[n][m];
    }

    public static void main(String[] args) {
        DistinctSubsequence subsequence = new DistinctSubsequence();
        System.out.println(subsequence.numDistinctTopDown("rabbbit", "rabbit"));
        System.out.println(subsequence.numDistinctTopDown("babgbag", "bag"));

        System.out.println(subsequence.numDistinctBottomUp("rabbbit", "rabbit"));
        System.out.println(subsequence.numDistinctBottomUp("babgbag", "bag"));
    }
}
