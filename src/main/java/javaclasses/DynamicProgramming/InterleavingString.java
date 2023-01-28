/*
 * *
 *  * Interleaving String.java
 *  * Created by Rafsan Ahmad on 1/28/23, 1:11 PM
 *  * Copyright (c) 2023 . All rights reserved.
 *
 */

package javaclasses.DynamicProgramming;

import java.util.HashMap;

public class InterleavingString {
    /*Given strings s1, s2, and s3, find whether s3 is formed by an interleaving of s1 and s2.

An interleaving of two strings s and t is a configuration where s and t are divided into n and m
substrings
 respectively, such that:

s = s1 + s2 + ... + sn
t = t1 + t2 + ... + tm
|n - m| <= 1
The interleaving is s1 + t1 + s2 + t2 + s3 + t3 + ... or t1 + s1 + t2 + s2 + t3 + s3 + ...
Note: a + b is the concatenation of strings a and b.


Example 1:
Input: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac"
Output: true

Explanation: One way to obtain s3 is:
Split s1 into s1 = "aa" + "bc" + "c", and s2 into s2 = "dbbc" + "a".
Interleaving the two splits, we get "aa" + "dbbc" + "bc" + "a" + "c" = "aadbbcbcac".
Since s3 can be obtained by interleaving s1 and s2, we return true.

Example 2:
Input: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbbaccc"
Output: false
Explanation: Notice how it is impossible to interleave s2 with any other string to obtain s3.

Example 3:
Input: s1 = "", s2 = "", s3 = ""
Output: true

Constraints:

0 <= s1.length, s2.length <= 100
0 <= s3.length <= 200
s1, s2, and s3 consist of lowercase English letters.*/

    //Top Down Approach - Time Limit Exceeded
    //Using hashmap
    public boolean isInterleave(String s1, String s2, String s3) {
        return helper(s1, s2, s3, new HashMap<>());
    }

    public boolean helper(String s1, String s2, String s3, HashMap<String, Boolean> map) {
        if (s1.length() == 0 && s2.length() == 0 && s3.length() == 0) {
            return true;
        }

        if (s3.length() == 0) {
            return false;
        }

        StringBuilder builder = new StringBuilder();
        builder.append(s1).append("|").append(s2).append("|").append(s3);

        String key = builder.toString();

        if (!map.containsKey(key)) {
            boolean x = s1.length() != 0 && s1.charAt(0) == s3.charAt(0) &&
                    isInterleave(s1.substring(1), s2, s3.substring(1));

            boolean y = s2.length() != 0 && s2.charAt(0) == s3.charAt(0) &&
                    isInterleave(s1, s2.substring(1), s3.substring(1));

            map.put(key, x || y);
        }
        return map.get(key);
    }


    //Bottom Up Approach
    //Time Complexity - O(m*n)
    public boolean isInterleaveOptimized(String s1, String s2, String s3) {
        int M = s1.length();
        int N = s2.length();

        // base case: length of given strings doesn't match
        if (M + N != s3.length()) {
            return false;
        }

        boolean[][] T = new boolean[M + 1][N + 1];

        // fill the lookup table in a bottom-up manner
        for (int i = 0; i <= M; i++) {
            for (int j = 0; j <= N; j++) {
                if (i == 0 && j == 0) {
                    T[i][j] = true;
                }
                // if the current char of 'S' matches the current char of both
                // 'A' and 'B'
                else if (i != 0 && j != 0 && s1.charAt(i - 1) == s3.charAt(i + j - 1)
                        && s2.charAt(j - 1) == s3.charAt(i + j - 1)) {
                    T[i][j] = T[i - 1][j] || T[i][j - 1];
                }

                // if the current char of 'X' matches with the current char of 'S'
                else if (i != 0 && s1.charAt(i - 1) == s3.charAt(i + j - 1)) {
                    T[i][j] = T[i - 1][j];
                }

                // if the current char of 'Y' matches with the current char of 'S'
                else if (j != 0 && s2.charAt(j - 1) == s3.charAt(i + j - 1)) {
                    T[i][j] = T[i][j - 1];
                }
            }
        }

        // T[M][N] stores the result
        return T[M][N];
    }

    public static void main(String[] args) {
        InterleavingString string = new InterleavingString();
        System.out.println(string.isInterleaveOptimized("aabcc", "dbbca", "aadbbcbcac"));
        System.out.println(string.isInterleaveOptimized("aabcc", "dbbca", "aadbbbaccc"));
    }
}
