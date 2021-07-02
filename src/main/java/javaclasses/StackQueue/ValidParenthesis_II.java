package javaclasses.StackQueue;

/*Given a string containing only three types of characters: '(', ')' and '*',
write a function to check whether this string is valid. We define the validity of a string by these rules:

Any left parenthesis '(' must have a corresponding right parenthesis ')'.
Any right parenthesis ')' must have a corresponding left parenthesis '('.
Left parenthesis '(' must go before the corresponding right parenthesis ')'.
'*' could be treated as a single right parenthesis ')' or a single left parenthesis '(' or an empty string.
An empty string is also valid.
Example 1:
Input: "()"
Output: True
Example 2:
Input: "(*)"
Output: True
Example 3:
Input: "(*))"
Output: True
Note:
The string size will be in the range [1, 100].*/

/*LeetCode 678*/

import java.util.Stack;

public class ValidParenthesis_II {

    public static boolean checkValidString(String s) {
        Stack<Integer> pair = new Stack<>();
        Stack<Integer> star = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == '(') {
                pair.push(i); //push index
            } else if (ch == '*') {
                star.push(i);
            } else {
                if (!pair.isEmpty()) {
                    pair.pop();
                } else if (!star.isEmpty()) {
                    star.pop();
                } else {
                    return false;
                }
            }
        }
        return isBalanced(pair, star);
    }

    public static boolean isBalanced(Stack<Integer> pair, Stack<Integer> star) {
        while (!pair.isEmpty()) {
            if (star.isEmpty()) {
                return false;
            } else if (star.peek() > pair.peek()) {
                star.pop();
                pair.pop();
            } else {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {

    }
}