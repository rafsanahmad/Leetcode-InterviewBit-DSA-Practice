/*
 * *
 *  * Letter Combinations of a Phone Number.java
 *  * Created by Rafsan Ahmad on 12/6/21, 4:25 PM
 *  * Copyright (c) 2023 . All rights reserved.
 *
 */

package javaclasses.Graph.DFS;

import java.util.ArrayList;
import java.util.List;

public class LetterCombinationPhoneNumber {
    //https://leetcode.com/problems/letter-combinations-of-a-phone-number/description/
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

    /*Time Complexity:
O(k^n), where:
n = number of digits
k = max letters per digit (usually ≤ 4)
*/
    public List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<>();

        if (digits != null && !digits.isEmpty()) {
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
            /*Why index + 1 & not i+1
            Because each recursive call moves to the next digit (not the next letter),
            i is used within one digit’s letters only, not across digits.*/
            dfs(digits, map, result, sb, index + 1);
            //delete last added character
            sb.deleteCharAt(sb.length() - 1);
        }

    }

    public static void main(String[] args) {
        LetterCombinationPhoneNumber number = new LetterCombinationPhoneNumber();
        System.out.println(number.letterCombinations("23"));
    }

}
