/*
 * *
 *  * Created by Rafsan Ahmad on 10/25/21, 9:49 PM
 *  * Copyright (c) 2021 . All rights reserved.
 *
 */

package javaclasses.Strings;

/*Write a java program to reverse a string. In this tutorial, I am going to explain how to
write a java code which reverse an input string using recursion & stack.

For example :

Input String: Object
Output String: tcejbO*/


import java.util.Stack;

public class ReverseString {

    //Reverse a String using Recursion
    private static String reverseUsingRecursion(String str) {

        //Terminating condition
        if (str == null || str.length() <= 1) {
            return str;
        }

        //Recursive function call
        String res = str.substring(1);
        return reverseUsingRecursion(res) + str.charAt(0);
    }

    //Reverse a String using Stack
    private static String reverseUsingStack(String str) {
        //Declare a stack
        Stack<Character> stack = new Stack<>();

        /**
         * Traverse a string and push each character
         * of a string in a stack.
         */
        for (int i = 0; i < str.length(); i++) {
            stack.push(str.charAt(i));
        }

        StringBuilder result = new StringBuilder();
        //When stack is not empty, pop each character
        while (!stack.empty()) {
            result.append(stack.pop());
        }
        return result.toString();
    }

    public static void main(String[] args) {

        String str = "Object";
        System.out.println(reverseUsingRecursion(str));
        System.out.println(reverseUsingStack(str));

    }
}

