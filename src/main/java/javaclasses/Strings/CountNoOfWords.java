/*
 * *
 *  * Created by Rafsan Ahmad on 10/25/21, 9:49 PM
 *  * Copyright (c) 2021 . All rights reserved.
 *
 */

package javaclasses.Strings;

/*In this tutorial, I have explained a java program to count number of words in a string.
Given an input string, write a code to count number of words in a string. The words in a string are separated by space(‘ ‘).

For example –

Example 1 –
Input – “Java Programming question”
Output – 3

Example 2 –
Input String – “Programming Tutorials”
Output – 2*/

/*Using the split() method in java we can solve this problem in single line of code.*/

//Using split method in java

public class CountNoOfWords {

    public static void main(String[] args) {

        String str = "Java programming question";

        //print number of words
        System.out.println(str.split("\\s+").length);
    }
}