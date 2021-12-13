/*
 * * Balanced Parenthesis.java
 *  * Created by Rafsan Ahmad on 10/25/21, 9:49 PM
 *  * Copyright (c) 2021 . All rights reserved.
 *
 */

package javaclasses.Strings;
/*Write a java code to check balanced parentheses in an expression using stack.

Given an expression containing characters ‘{‘,’}’,'(‘,’)’,'[‘,’]’.
We have to write a code to check whether an input string has valid parentheses.

An input string is valid if:

Open brackets must be closed by the same type of brackets.
Open brackets must be closed in the correct order.

Example –

i) {[]}) – Invalid

ii) {()}[] – Valid*/

/*We can use stack data structure to solve this problem efficiently. Here are the following steps.
 
i) First, we need to traverse an input string and pick each character at a time.

ii) If the current character is starting bracket ‘{‘, ‘(‘, ‘[‘ then push it in a stack.

iii) If the current character is closing bracket ‘}’, ‘)’, ‘]’ and the top of the stack is starting bracket
then pop from the stack.

iv) After complete traversal, if the stack is empty then it is balanced parentheses otherwise it is not balanced.*/

/*The time complexity of this approach is O(n) and it’s space complexity is also O(n).*/

import java.util.Stack;

public class BalancedParenthesis {

    public static boolean checkParenthesis(String str) {
        //Declare a stack
        Stack<Character> st = new Stack<>();

        //Traverse a string
        for (int i = 0; i < str.length(); i++) {

            /* If the current character is starting bracket,
               then push them in a stack
             */
            if (str.charAt(i) == '{' || str.charAt(i) == '[' || str.charAt(i) == '(') {
                st.push(str.charAt(i));

            /*
               Else, If the stack is not empty,
               And current character is a closing bracket
               and top character of a stack is matching open bracket
               then pop it.
             */
            } else if (!st.empty()) {
                if ((str.charAt(i) == ']' && (Character) st.peek() == '[') ||
                        (str.charAt(i) == '}' && (Character) st.peek() == '{') ||
                        (str.charAt(i) == ')' && (Character) st.peek() == '(')) {

                    st.pop();
                }

            } else {
                st.push(str.charAt(i));
            }
        }

        if (st.empty()) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        String str = "{[]})";
        String str2 = "{()}[]";
        System.out.println(checkParenthesis(str));
        System.out.println(checkParenthesis(str2));
    }
}