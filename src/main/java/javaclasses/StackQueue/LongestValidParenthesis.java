/*
 * *
 *  * Longest Valid Parenthesis.java
 *  * Created by Rafsan Ahmad on 12/16/24, 2:14PM
 *  * Copyright (c) 2024 . All rights reserved.
 *  *
 *
 */

package javaclasses.StackQueue;

import java.util.Stack;

public class LongestValidParenthesis {
    //https://leetcode.com/problems/longest-valid-parentheses/description/
    /*Given a string containing just the characters '(' and ')', return the length of the longest
    valid (well-formed) parentheses substring.

Example 1:
Input: s = "(()"
Output: 2
Explanation: The longest valid parentheses substring is "()".

Example 2:
Input: s = ")()())"
Output: 4
Explanation: The longest valid parentheses substring is "()()".

Example 3:
Input: s = ""
Output: 0

Constraints:
0 <= s.length <= 3 * 10^4
s[i] is '(', or ')'.*/

    public int longestValidParentheses(String s) {
        if (s.length() <= 1) return 0;

        int result = 0;
        int left = -1;
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(')
                stack.push(i);

            else {
                if (stack.isEmpty()) {
                    left = i;
                } else {
                    stack.pop();
                    if (stack.isEmpty()) {
                        result = Math.max(result, i - left);
                    } else {
                        result = Math.max(result, i - stack.peek());
                    }
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        LongestValidParenthesis parenthesis = new LongestValidParenthesis();
        System.out.println(parenthesis.longestValidParentheses(")()())"));
    }
}
