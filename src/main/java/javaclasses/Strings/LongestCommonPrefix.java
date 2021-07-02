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

    public static void main(String[] args) {

        String[] strgs = {"cat", "cable", "camera"};
        String result = findLongestPrefix(strgs);

        System.out.println(result);
    }
}