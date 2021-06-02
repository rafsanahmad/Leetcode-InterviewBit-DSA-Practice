package javaclasses.StackQueue;

//Leetcode 20
/*
* Given a string s containing just the characters '(', ')', '{', '}', '[' and ']',
determine if the input string is valid.

An input string is valid if:

Open brackets must be closed by the same type of brackets.
Open brackets must be closed in the correct order.


Example 1:

Input: s = "()"
Output: true
Example 2:

Input: s = "()[]{}"
Output: true
Example 3:

Input: s = "(]"
Output: false*/

import java.util.Stack;

public class ValidParenthesis {
    //Using Stack
    public boolean isValid(String s) {
        Stack<Character> st = new Stack<>();

        if (s.isEmpty()) {
            return true;
        }
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ')' && !st.isEmpty() && st.peek() == '(') {
                st.pop();
            } else if (s.charAt(i) == '}' && !st.isEmpty() && st.peek() == '{') {
                st.pop();
            } else if (s.charAt(i) == ']' && !st.isEmpty() && st.peek() == '[') {
                st.pop();
            } else {
                st.push(s.charAt(i));
            }
        }
        if (st.isEmpty()) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        ValidParenthesis vp = new ValidParenthesis();
        System.out.println(vp.isValid("()[]{}"));
    }
}
