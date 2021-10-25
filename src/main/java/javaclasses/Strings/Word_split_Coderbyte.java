/*
 * *
 *  * Created by Rafsan Ahmad on 10/25/21, 9:49 PM
 *  * Copyright (c) 2021 . All rights reserved.
 *
 */

package javaclasses.Strings;

import java.util.Arrays;
/*Problem: Have the function WordSplit(strArr) read the array off strings stored in strArr,
        which will contain 2 elements: the first element will be a sequence of characters,
        and the second element will be a long string of comma-seperated words, in alphabetical order,
        that represents a dictionary of some arbitrary length.
        For example: strArr can be: ["hellocat", "apple, bat,cat,goodbye,hello,yellow,why"].
        Your goal is to determine if the first element in the input can be split into two words,
        where both words in the dictionary that is provided in the second input.
        In this example, the firs element can be split into two words: hello and cat because both of those words are in the dictionary.
        Your program should return the two words that exist in the dictionary seperated by a comma.
        So for the example above, your program should return hello,cat. There will only be one correcy way to split the first element of characters into two words.
        I f there is no way to split string into two words that exist in the dictionary, return the string not possible.
        The first element itself will never exist in the dictionary as a real word.
        Answer Output:
        base, ball*/

public class Word_split_Coderbyte {

    public static String WordSplit(String[] strArr) {
        // code goes here
        String result = "";
        boolean found = false;
        String sentence = strArr[0];
        String strDictionary = strArr[1];

        String[] dictArray = strDictionary.split(",");

        for (int i = 0; i < dictArray.length; i++) {
            String[] sentanceArray = sentence.split(dictArray[i]);
            if (sentanceArray.length > 0) {
                for (int j = 0; j < sentanceArray.length; j++) {
                    String addedWord = dictArray[i] + sentanceArray[j];

                    //check other way around
                    StringBuilder builder = new StringBuilder(addedWord);
                    builder = builder.reverse();
                    String reverseWord = builder.toString();
                    if (addedWord.equals(sentence) || reverseWord.equals(sentence)) {
                        result = dictArray[i] + "," + sentanceArray[j];
                        found = true;
                    }
                }
            }
        }
        if (!found) result = "not possible";
        return result;
    }

    public static void main(String[] args) {
        // keep this function call here
        //Scanner s = new Scanner(System.in);
        //System.out.print(WordSplit(s.nextLine()));
        String[] array = new String[]{"hellocat", "apple, bat,cat,goodbye,hello,yellow,why"};
        String result = WordSplit(array);
        System.out.print(result);

        System.out.println();
        String str = "hellocat";
        System.out.println(Arrays.toString(str.split("hello")));
    }
}

