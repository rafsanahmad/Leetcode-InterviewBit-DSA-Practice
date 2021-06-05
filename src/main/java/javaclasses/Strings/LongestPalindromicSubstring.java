package javaclasses.Strings;

/*
* Given a string s, return the longest palindromic substring in s.

Example 1:

Input: s = "babad"
Output: "bab"
Note: "aba" is also a valid answer.
Example 2:

Input: s = "cbbd"
Output: "bb"
Example 3:

Input: s = "a"
Output: "a"
Example 4:

Input: s = "ac"
Output: "a"
*/

//Dynamic Programming
public class LongestPalindromicSubstring {
    public String longestPalindrome(String s) {
        String result = "";
        int len = s.length();

        boolean table[][] = new boolean[len][len];
        int maxLength = 1;

        //All substring of length 1 are palindromes
        for (int i = 0; i < len; i++) {
            table[i][i] = true;
        }

        //check for substring of length 2
        int start = 0;
        for (int i = 0; i < len - 1; i++) {
            if (s.charAt(i) == s.charAt(i + 1)) {
                table[i][i + 1] = true;
                start = i;
                maxLength = 2;
            }
        }

        //check for Lengths greater than 2
        for (int i = 3; i <= len; i++) {
            for (int j = 0; j < len - i + 1; j++) {
                //Get ending index
                int k = j + i - 1;

                if (table[j + 1][k - 1] && s.charAt(j) == s.charAt(k)) {
                    table[j][k] = true;
                    if (i > maxLength) {
                        start = j;
                        maxLength = i;
                    }
                }
            }
        }
        int end = start + maxLength - 1;
        result = s.substring(start, end + 1);
        return result;
    }
}
