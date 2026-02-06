/*
 * * Longest Common Subsequence.java
 *  * Created by Rafsan Ahmad on 10/25/21, 9:50 PM
 *  * Copyright (c) 2021 . All rights reserved.
 *
 */

package javaclasses.DynamicProgramming;

public class LongestCommonSubsequence {
    //Leetcode 1143
    /*Given two strings text1 and text2, return the length of their longest common subsequence.
    If there is no common subsequence, return 0.

A subsequence of a string is a new string generated from the original string with some characters
(can be none) deleted without changing the relative order of the remaining characters.

For example, "ace" is a subsequence of "abcde".
A common subsequence of two strings is a subsequence that is common to both strings.

Example 1:
Input: text1 = "abcde", text2 = "ace"
Output: 3
Explanation: The longest common subsequence is "ace" and its length is 3.

Example 2:
Input: text1 = "abc", text2 = "abc"
Output: 3
Explanation: The longest common subsequence is "abc" and its length is 3.

Example 3:
Input: text1 = "abc", text2 = "def"
Output: 0
Explanation: There is no such common subsequence, so the result is 0.
 */
    /*dp[i][j] = length of the Longest Common Subsequence between
text1[0 ... i-1]  and  text2[0 ... j-1]
So when you are computing dp[i][j], you’re deciding:
what is the best LCS I can form using the first i chars of text1 and first j chars of text2?
*/
    public int longestCommonSubsequence(String text1, String text2) {
        int max = 0;
        int len1 = text1.length();
        int len2 = text2.length();
        int[][] dp = new int[len1 + 1][len2 + 1];

        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[i].length; j++) {
                //If characters match, we can safely use them in the subsequence.
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    /*Option 1: Ignore text1[i-1]
                    Keep all of text2, but drop the current char from text1.
                    That gives:dp[i - 1][j]

                    Option 2: Ignore text2[j-1]
                    Keep all of text1, but drop the current char from text2.
                    That gives: dp[i][j - 1]*/
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
                }
                max = Math.max(dp[i][j], max);
            }
        }
        return max;
    }

    //Printing Longest Common Subsequence
    /*Given two sequences, print the longest subsequence present in both of them.

Examples:
LCS for input Sequences “ABCDGH” and “AEDFHR” is “ADH” of length 3.
LCS for input Sequences “AGGTAB” and “GXTXAYB” is “GTAB” of length 4.

Approach:
1. Construct L[m+1][n+1] using the steps discussed in previous post.
2. The value L[m][n] contains length of LCS. Create a character array lcs[] of length equal to the length of lcs plus
1 (one extra to store \0).
3. Traverse the 2D array starting from L[m][n]. Do following for every cell L[i][j]
4. If characters (in X and Y) corresponding to L[i][j] are same (Or X[i-1] == Y[j-1]), then include this character as
part of LCS.
Else compare values of L[i-1][j] and L[i][j-1] and go in direction of greater value.

*/

    public String longestCommonSubsequenceStr(String text1, String text2) {
        int max = 0;
        int len1 = text1.length();
        int len2 = text2.length();
        int[][] dp = new int[len1 + 1][len2 + 1];

        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[i].length; j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
                }
                max = Math.max(dp[i][j], max);
            }
        }

        // Following code is used to print LCS
        int index = dp[len1][len2];

        // Create a character array to store the lcs string
        char[] lcs = new char[index];

        // Start from the right-most-bottom-most corner and one by one store characters in lcs[]
        while (len1 > 0 && len2 > 0) {
            // If current character in X[] and Y are same, then current character is part of LCS
            if (text1.charAt(len1 - 1) == text2.charAt(len2 - 1)) {
                // Put current character in result
                lcs[index - 1] = text1.charAt(len1 - 1);

                // reduce values of i, j and index
                len1--;
                len2--;
                index--;
            }

            // If not same, then find the larger of two and go in the direction of larger value
            else if (dp[len1 - 1][len2] > dp[len1][len2 - 1])
                len1--;
            else
                len2--;
        }

        return new String(lcs);
    }

    public static void main(String[] args) {
        LongestCommonSubsequence lcs = new LongestCommonSubsequence();
        String str1 = "ABCDGHLQR";
        String str2 = "AEDPHR";

        int result = lcs.longestCommonSubsequence(str1, str2);
        System.out.println(result);

        System.out.println(lcs.longestCommonSubsequenceStr(str1, str2));
    }
}
