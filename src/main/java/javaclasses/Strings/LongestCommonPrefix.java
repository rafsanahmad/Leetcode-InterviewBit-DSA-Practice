/*
 * *
 *  * Created by Rafsan Ahmad on 10/25/21, 9:49 PM
 *  * Copyright (c) 2021 . All rights reserved.
 *
 */

package javaclasses.Strings;

/*Given an array of strings, write a method to find the longest common prefix string.
If no common prefix is found, return an empty string ” “.

For example:

Example 1:
Input: [“cat”,”cable”,”camera”]
Output: “ca”

The common prefix is ca.

Example 2:
Input: [“rat”,”dog”,”elephant”]
Output: “”

No common prefix is found.

In this tutorial, I am going to discuss the algorithm and their java implementation to find the longest common prefix amongst an array of strings.*/


/*
Find the longest common prefix java code
*/
//Leetcode 14
public class LongestCommonPrefix {

    public static String findLongestPrefix(String[] strs) {

        //If string array is null or empty
        if (strs == null || strs.length == 0) {
            return "";
        }

        //Assign first word of an array string
        String lcp = strs[0];

        if (strs.length == 1) {
            return lcp;
        }

        //Traverse an array from 1 to n-1
        for (int i = 1; i < strs.length; i++) {

            String currentWord = strs[i];
            int j = 0;
 
          /*
            While common character is found,
            increment the value of j
          */
            while (j < currentWord.length() &&
                    j < lcp.length() &&
                    currentWord.charAt(j) == lcp.charAt(j)) {

                j++;
            }

            //If no common character is found
            if (j == 0) {
                return "";
            }

            //Assign common substring
            lcp = currentWord.substring(0, j);

        }

        return lcp;
    }

    //Approach -2
    public static String longestCommonPrefix(String[] A) {
        String res = "";
        int len = A.length;
        if (len == 0) return "";
        String first = A[0];
        for (int i = 0; i < first.length(); i++) {
            char ch = first.charAt(i);
            int c = 0;
            for (int j = 1; j < len; j++) {
                String str = A[j];
                if ((i >= str.length()) || (str.charAt(i) != ch)) {
                    break;
                } else {
                    ++c;
                }
            }
            if (c == len - 1) res += ch;
            else break;
        }
        return res;
    }

    public static void main(String[] args) {

        String[] strgs = {"cat", "cable", "camera"};
        String result = findLongestPrefix(strgs);
        String result2 = longestCommonPrefix(strgs);
        System.out.println(result);
        System.out.println(result2);
    }
}