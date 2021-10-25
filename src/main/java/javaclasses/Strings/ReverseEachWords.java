/*
 * *
 *  * Created by Rafsan Ahmad on 10/25/21, 9:49 PM
 *  * Copyright (c) 2021 . All rights reserved.
 *
 */

package javaclasses.Strings;

/*Write a java program to reverse each words of a string.  Suppose, If an input string is Java Programming then the output string should be avaJ gnimmargorP.*/

/*We split the input string into words using split() method. 
Then we reverse each individual word and append to a final string. Finally, we print the output.
*/


public class ReverseEachWords {

    public static void main(String[] args) {

        String str = "Java Programming";

        //Create an array of words
        //[Java,Programming]
        String[] words = str.split(" ");
        String revString = "";

        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            String revWord = "";

            for (int j = word.length() - 1; j >= 0; j--) {
                revWord = revWord + word.charAt(j);
            }

            revString = revString + revWord + " ";
        }

        System.out.println(revString);
    }
}