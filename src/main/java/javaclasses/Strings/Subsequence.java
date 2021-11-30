/*
 * *
 *  * Created by Rafsan Ahmad on 10/25/21, 9:49 PM
 *  * Copyright (c) 2021 . All rights reserved.
 *
 */

package javaclasses.Strings;

/*Given two strings str1 and str2, check if str1 is a subsequence of str2. Both strings consists only of
lowercase characters.

Example 1:

Input: str1 = “abc”, str2 = “ahbgdc”

Output: true

Example 2:

Input: s = “axc”, t = “ahbgdc”

Output: false

What is Subsequence?
A subsequence is a sequence that can be derived from another sequence by deleting some or no elements without
changing the order of the remaining elements.

For example : abd is a subsequence of abcde.

/*In this example, we are going to solve this problem using two pointers.

Take two pointers sIndex and tIndex. The pointer sIndex points to String str1 and pointer tIndex points to string str2.
The initial value is zero for both the pointers.

Then compare the character present at both the indexes. If it is equal then increment the value of both the pointers.
Else increment the value of pointer which points to String str2.

If all the characters of string str1 is present in string str2 it means the value of sIndex is equal to s.length.

The time complexity of this approach is O(n), where n is the length of string s. It’s space complexity is O(1).*/

public class Subsequence {

    //Check If a String is Subsequence of Another String - Java Code
    //S = subsequence string, t = main string
    public boolean isSubsequence(String s, String t) {

        int sLen = s.length();
        int tLen = t.length();

        if (sLen == 0)
            return true;

        int sIndex = 0;
        int tIndex = 0;

        while (sIndex < sLen && tIndex < tLen) {

            if (s.charAt(sIndex) == t.charAt(tIndex))
                sIndex++;

            tIndex++;
        }

        return sIndex == sLen;
    }

    public static void main(String[] args) {
        Subsequence subsequence = new Subsequence();
        System.out.println(subsequence.isSubsequence("abd", "abcde"));
    }
}

 