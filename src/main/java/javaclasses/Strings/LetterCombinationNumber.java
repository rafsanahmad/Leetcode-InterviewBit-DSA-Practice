package javaclasses.Strings;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class LetterCombinationNumber {

    //Leetcode 17
    /*Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could represent.
    Return the answer in any order.

    A mapping of digit to letters (just like on the telephone buttons) is given below. Note that 1 does not map to any letters.

    Example 1:

    Input: digits = "23"
    Output: ["ad","ae","af","bd","be","bf","cd","ce","cf"]
    Example 2:

    Input: digits = ""
    Output: []
    Example 3:

    Input: digits = "2"
    Output: ["a","b","c"]*/

    public List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<>();
        if (digits.isEmpty() || digits == null) {
            return result;
        }
        String[] table
                = {"0", "1", "abc", "def", "ghi",
                "jkl", "mno", "pqrs", "tuv", "wxyz"};

        int len = digits.length();
        Queue<String> q = new LinkedList<>();
        q.add("");

        while (!q.isEmpty()) {
            String s = q.remove();

            // If complete word is generated
            // push it in the list
            if (s.length() == len) {
                result.add(s);
            } else {
                if (s.length() <= digits.length()) {
                    int index = digits.charAt(s.length()) - '0';
                    String val = table[index];
                    for (int i = 0; i < val.length(); i++) {
                        q.add(s + val.charAt(i));
                    }
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        LetterCombinationNumber lcn = new LetterCombinationNumber();
        List<String> result = new ArrayList<>();
        result = lcn.letterCombinations("23");
        for (int i = 0; i < result.size(); i++) {
            System.out.print(result.get(i) + " ");
        }
    }
}
