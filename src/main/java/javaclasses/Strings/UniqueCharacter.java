/*
 * *
 *  * Created by Rafsan Ahmad on 10/25/21, 9:49 PM
 *  * Copyright (c) 2021 . All rights reserved.
 *
 */

package javaclasses.Strings;

/*Given a string, find the first non-repeating character in it and return its index.
If it doesn't exist, return -1.

Examples:

s = "leetcode"
return 0.
Explanation: First Non repeating character is l

s = "loveleetcode"
return 2.
Explanation: First Non repeating character is v

Note: You may assume the string contains only lowercase English letters.*/

import java.util.HashMap;

public class UniqueCharacter {

    public static int findUniqueCharacter(String str) {

        HashMap<Character, Integer> charCountMap = new HashMap<>();
		/*Traverse a string and create a map of
		character and its count*/
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            charCountMap.put(ch, charCountMap.getOrDefault(ch, 0) + 1);
        }

		/*Now we know each character and its count present in a string
		Next step is to simply traverse a string & check the character whose count is 1*/

        for (int j = 0; j < str.length(); j++) {
            char ch = str.charAt(j);
            int count = charCountMap.get(ch);
            if (count == 1) {
                return j;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(findUniqueCharacter("character"));
        System.out.println(findUniqueCharacter("loveleetcode"));
    }

}