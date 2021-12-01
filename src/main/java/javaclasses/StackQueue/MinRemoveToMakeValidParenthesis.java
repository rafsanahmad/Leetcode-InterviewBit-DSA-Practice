/*
 * *
 *  * Created by Rafsan Ahmad on 10/25/21, 9:50 PM
 *  * Copyright (c) 2021 . All rights reserved.
 *
 */

package javaclasses.StackQueue;

import java.util.Stack;

public class MinRemoveToMakeValidParenthesis {
    //Leetcode 1249

    /*Given a string s of '(' , ')' and lowercase English characters.

Your task is to remove the minimum number of parentheses ( '(' or ')', in any positions )
so that the resulting parentheses string is valid and return any valid string.

Formally, a parentheses string is valid if and only if:

It is the empty string, contains only lowercase characters, or
It can be written as AB (A concatenated with B), where A and B are valid strings, or
It can be written as (A), where A is a valid string.


Example 1:
Input: s = "lee(t(c)o)de)"
Output: "lee(t(c)o)de"
Explanation: "lee(t(co)de)" , "lee(t(c)ode)" would also be accepted.

Example 2:
Input: s = "a)b(c)d"
Output: "ab(c)d"

Example 3:
Input: s = "))(("
Output: ""
Explanation: An empty string is also valid.

Example 4:
Input: s = "(a(b(c)d)"
Output: "a(b(c)d)"*/

    public String minRemoveToMakeValid(String s) {
        String resultString = s;
        Stack<Character> stack = new Stack<>();
        Stack<Integer> indexes = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(s.charAt(i));
                indexes.push(i);
            } else if (s.charAt(i) == ')') {
                if (!stack.isEmpty() && stack.peek() == '(') {
                    stack.pop();
                    indexes.pop();
                } else {
                    stack.push(s.charAt(i));
                    indexes.push(i);
                }
            }
        }
        StringBuilder sb = new StringBuilder(resultString);
        while (!indexes.empty()) {
            int index = indexes.pop();
            sb.deleteCharAt(index);
        }
        resultString = sb.toString();
        return resultString;
    }

    public static void main(String[] args) {
        MinRemoveToMakeValidParenthesis parenthesis = new MinRemoveToMakeValidParenthesis();
        System.out.println(parenthesis.minRemoveToMakeValid("lee(t(c)o)de)"));
    }
}
