/*
 * *
 *  * Created by Rafsan Ahmad on 10/25/21, 9:49 PM
 *  * Copyright (c) 2021 . All rights reserved.
 *
 */

package javaclasses.Recursion;

/*Write a java program to reverse a string using recursion. In this tutorial, I am going to explain how to write a java code which reverse an input string using recursion. I have also added the video at the end of this tutorial.

For example :

Input String: Object
Output String: tcejbO*/

/*In this code example, we write a function reverse which takes a string as an argument and reverses it recursively. The time complexity of this approach is O(n).*/

/**
 * Reverse a String using Recursion
 */
public class ReverseString {

    //Method which reverse a string
    private static String reverse(String str) {

        //Terminating condition
        if (str == null || str.length() <= 1) {
            return str;
        }

        //Recursive function call
        return reverse(str.substring(1)) + str.charAt(0);
    }

    public static void main(String[] args) {

        String str = "Object";
        String revStr = reverse(str);

        System.out.println(revStr);

    }
}

