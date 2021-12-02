/*
 * *
 *  * Created by Rafsan Ahmad on 10/25/21, 9:49 PM
 *  * Copyright (c) 2021 . All rights reserved.
 *
 */

package javaclasses.Strings;

import java.util.HashMap;

public class IsomorphicString {
    //Leetcode 205
    /*Given two strings s and t, determine if they are isomorphic.

Two strings s and t are isomorphic if the characters in s can be replaced to get t.

All occurrences of a character must be replaced with another character while preserving the order of characters.
No two characters may map to the same character, but a character may map to itself.

Example 1:
Input: s = "egg", t = "add"
Output: true

Example 2:
Input: s = "foo", t = "bar"
Output: false

Example 3:
Input: s = "paper", t = "title"
Output: true
 */
    public boolean isIsomorphic(String s, String t) {
        HashMap<Character, Character> map = new HashMap<>();
        if (s.length() != t.length()) {
            return false;
        }
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (map.containsKey(s.charAt(i))) {
                char ch = map.get(s.charAt(i));
                builder.append(ch);
            } else if (map.containsValue(t.charAt(i))) {
                return false;
            } else {
                map.put(s.charAt(i), t.charAt(i));
                builder.append(t.charAt(i));
            }
        }
        if (builder.toString().equals(t)) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        IsomorphicString string = new IsomorphicString();
        System.out.println(string.isIsomorphic("paper", "title"));
    }
}
