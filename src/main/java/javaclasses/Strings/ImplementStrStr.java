/*
 * *
 *  * Created by Rafsan Ahmad on 10/25/21, 9:49 PM
 *  * Copyright (c) 2021 . All rights reserved.
 *
 */

package javaclasses.Strings;

public class ImplementStrStr {
    //Leetcode 28
    /*Implement strStr().

Return the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.

Clarification:

What should we return when needle is an empty string? This is a great question to ask during an interview.

For the purpose of this problem, we will return 0 when needle is an empty string. This is consistent to
C's strstr() and Java's indexOf().


Example 1:

Input: haystack = "hello", needle = "ll"
Output: 2
Example 2:

Input: haystack = "aaaaa", needle = "bba"
Output: -1
Example 3:

Input: haystack = "", needle = ""
Output: 0*/

    public int strStr(final String hayStack, final String needle) {
        if (needle.isEmpty() || hayStack.isEmpty()) {
            return -1;
        }
        int lenA = hayStack.length();
        int lenB = needle.length();
        if (lenB > lenA) return -1;
        int index = 0;
        int count = 0;

        for (int i = 0; i < lenA; i++) {
            int current = i;
            count = 0;
            for (int j = 0; j < lenB; j++) {
                if (current < lenA && hayStack.charAt(current) == needle.charAt(j)) {
                    ++current;
                    ++count;
                    if (j == 0) index = i;
                }
                if (count == lenB) {
                    return index;
                }
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        ImplementStrStr strStr = new ImplementStrStr();
        System.out.println(strStr.strStr("bbaabbbbbaabbaabbbbbbabbbabaabbbabbabbbbababbbabbabaaababbbaabaaaba",
                "babaaa"));
    }
}
