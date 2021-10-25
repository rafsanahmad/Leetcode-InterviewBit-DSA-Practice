/*
 * *
 *  * Created by Rafsan Ahmad on 10/25/21, 9:49 PM
 *  * Copyright (c) 2021 . All rights reserved.
 *
 */

package javaclasses.Graph;

import java.util.ArrayList;
import java.util.List;

public class CombinationPhoneNumber {
    //Leetcode 17
    //res/keypad.png
    /*Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the
    number could represent. Return the answer in any order.

A mapping of digit to letters (just like on the telephone buttons) is given below.
Note that 1 does not map to any letters.

Example 1:
Input: digits = "23"
Output: ["ad","ae","af","bd","be","bf","cd","ce","cf"]

Example 2:
Input: digits = ""
Output: []

Example 3:
Input: digits = "2"
Output: ["a","b","c"]
*/
    public List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<>();

        if (digits != null && digits.length() > 0) {
            String[] map = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
            dfs(digits, map, result, new StringBuilder(), 0);
        }
        return result;
    }

    public void dfs(String digits, String[] map, List<String> result, StringBuilder sb, int index) {
        //When reached end - add to result list
        if (index == digits.length()) {
            result.add(sb.toString());
            return;
        }
        int digit = Character.getNumericValue(digits.charAt(index));
        String letters = map[digit];

        //add each letter to the string
        for (int i = 0; i < letters.length(); i++) {
            char ch = letters.charAt(i);
            sb.append(ch);
            //move onto next digit
            dfs(digits, map, result, sb, index + 1);
            //delete last added character
            sb.deleteCharAt(sb.length() - 1);
        }

    }

    public static void main(String[] args) {
        CombinationPhoneNumber number = new CombinationPhoneNumber();
        System.out.println(number.letterCombinations("23"));
    }

}
