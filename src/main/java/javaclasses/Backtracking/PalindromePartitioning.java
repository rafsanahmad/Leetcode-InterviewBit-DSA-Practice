/*
 * *
 *  * Palindrome Partitioning.java
 *  * Created by Rafsan Ahmad on 1/28/23, 6:02 PM
 *  * Copyright (c) 2023 . All rights reserved.
 *
 */

package javaclasses.Backtracking;

import java.util.ArrayList;
import java.util.List;

public class PalindromePartitioning {
    /*Given a string s, partition s such that every
substring
 of the partition is a
palindrome
. Return all possible palindrome partitioning of s.

Example 1:

Input: s = "aab"
Output: [["a","a","b"],["aa","b"]]
Example 2:

Input: s = "a"
Output: [["a"]]*/

    public List<List<String>> partition(String s) {
        List<List<String>> result = new ArrayList<>();

        helper(result, new ArrayList<>(), s, 0);

        return result;
    }


    public void helper(List<List<String>> list, List<String> tempList, String s, int start) {

        if (start == s.length()) {
            list.add(new ArrayList<>(tempList));
        }

        for (int i = start; i < s.length(); i++) {
            if (isPalindrome(s, start, i)) {
                tempList.add(s.substring(start, i + 1));
                helper(list, tempList, s, i + 1);
                tempList.remove(tempList.size() - 1);
            }
        }
    }

    public boolean isPalindrome(String s, int left, int right) {

        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) return false;
            left++;
            right--;
        }

        return true;
    }

    public static void main(String[] args) {
        PalindromePartitioning partitioning = new PalindromePartitioning();
        System.out.println(partitioning.partition("aab"));
    }

}
