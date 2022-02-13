/*
 * *
 *  * Smallest Subsequence Of Distinct Characters.java
 *  * Created by Rafsan Ahmad on 1/31/22, 10:07 PM
 *  * Copyright (c) 2022 . All rights reserved.
 *
 */

package javaclasses.GreedyAlgorithm;

public class SmallestSubsequenceOfDistinctCharacters {
    //Leetcode 1081 same as Leetcode 306
    /*Given a string s, return the lexicographically smallest subsequence of s that contains all the distinct characters
    of s exactly once.

Example 1:
Input: s = "bcabc"
Output: "abc"

Example 2:
Input: s = "cbacdcbc"
Output: "acdb"


Constraints:
1 <= s.length <= 1000
s consists of lowercase English letters.*/

    public String smallestSubsequence(String s) {
        char[] chars = s.toCharArray();
        // record the index of last occurrence for each character
        int[] lastIndex = new int[26];
        for (int i = 0; i < chars.length; i++) {
            lastIndex[chars[i] - 'a'] = i;
        }

        boolean[] used = new boolean[26];
        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < chars.length; i++) {
            // if the current character has been used, skip it
            if (used[chars[i] - 'a']) {
                continue;
            }
            //System.out.println(ans.toString());
            // if the current character is smaller than the last character in StringBuilder (mark it as c)
            // and the last index of c is larger than i, it means we can use it later,
            // so we delete it(c) and mark used as false
            while (ans.length() > 0 && ans.charAt(ans.length() - 1) > chars[i] &&
                    lastIndex[ans.charAt(ans.length() - 1) - 'a'] > i) {
                used[ans.charAt(ans.length() - 1) - 'a'] = false;
                ans.deleteCharAt(ans.length() - 1);
            }
            ans.append(chars[i]);
            used[chars[i] - 'a'] = true;
        }
        return ans.toString();
    }

    public static void main(String[] args) {
        SmallestSubsequenceOfDistinctCharacters characters = new SmallestSubsequenceOfDistinctCharacters();
        System.out.println(characters.smallestSubsequence("bcabc"));
        System.out.println(characters.smallestSubsequence("cbacdcbc"));
    }

}
