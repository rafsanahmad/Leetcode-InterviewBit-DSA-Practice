/*
 * *
 *  * Created by Rafsan Ahmad on 10/25/21, 9:49 PM
 *  * Copyright (c) 2021 . All rights reserved.
 *
 */

package javaclasses.Strings;

/*Write a java program to reverse a string using stack data structure.
Given an input string, We have to write a java code to reverse a string using stack.*/

/*To reverse a string using stack, 
First we push all the characters of a string in a stack. 
After that we do a pop operation on a stack. So character which inserted last in a stack is the first to be popped out.*/

import java.util.Scanner;
import java.util.Stack;

/**
 * Java Program to Reverse a String using Stack
 * Source - https://webrewrite.com
 * <p>
 * Input String : Java
 * <p>
 * Output String: avaJ
 */

public class ReverseStringUsingStack {

    public static void main(String[] args) {

        //Take input string
        Scanner in = new Scanner(System.in);
        System.out.println("Enter a string");

        String str = in.nextLine();

        //Declare a stack
        Stack stack = new Stack<>();

        /**
         * Traverse a string and push each character
         * of a string in a stack.
         */
        for (int i = 0; i < str.length(); i++) {
            stack.push(str.charAt(i));
        }

        System.out.println("Reverse of a string");

        //When stack is not empty, pop each character
        while (!stack.empty()) {
            System.out.print(stack.pop());
        }
    }
}