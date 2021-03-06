/*
 * * Remove Adjacent Duplicate.java
 *  * Created by Rafsan Ahmad on 10/25/21, 9:49 PM
 *  * Copyright (c) 2021 . All rights reserved.
 *
 */

package javaclasses.Strings;

import java.util.Stack;

/*LeetCode - 1047*/
/*Given a string S of lowercase letters, a duplicate removal consists of choosing two adjacent and equal letters,
and removing them.

We repeatedly make duplicate removals on S until we no longer can.

Return the final string after all such duplicate removals have been made.  It is guaranteed the answer is unique.

Example 1:

Input: "abbaca"
Output: "ca"
Explanation: 
For example, in "abbaca" we could remove "bb" since the letters are adjacent and equal, and this is the only
possible move.
The result of this move is that the string is "aaca", of which only "aa" is possible, so the final string is "ca".*/

public class RemoveAdjacentDuplicate {

    public static String removeAdjacentDuplicate(String str) {
        Stack<Character> st = new Stack<>();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < str.length(); i++) {
            if (!st.isEmpty() && st.peek() == str.charAt(i)) {
                st.pop();
            } else {
                st.push(str.charAt(i));
            }
        }

        for (Character ch : st) {
            sb.append(ch);
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        String str = "caaabbc";
        String result = removeAdjacentDuplicate(str);
        System.out.println(result);
    }
}

