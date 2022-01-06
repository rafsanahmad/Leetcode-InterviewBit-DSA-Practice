/*
 * *
 *  * Reverse Words Without Special Characters.java
 *  * Created by Rafsan Ahmad on 1/6/22, 9:45 PM
 *  * Copyright (c) 2022 . All rights reserved.
 *
 */

package javaclasses.Strings;

public class ReverseWordsWithoutSpecialCharacters {

    /*Given a string, that contains a special character together with alphabets (‘a’ to ‘z’ and ‘A’ to ‘Z’),
    reverse the string in a way that special characters are not affected.

Examples:
Input:   str = "a,b$c"
Output:  str = "c,b$a"
Note that $ and , are not moved anywhere.
Only subsequence "abc" is reversed

Input:   str = "Ab,c,de!$"
Output:  str = "ed,c,bA!$"*/

    public static String reverse(String str) {
        char[] chArray = str.toCharArray();
        // Initialize left and right pointers
        int r = chArray.length - 1, l = 0;

        // Traverse string from both ends until
        // 'l' and 'r'
        while (l < r) {
            // Ignore special characters
            if (!Character.isAlphabetic(chArray[l]))
                l++;
            else if (!Character.isAlphabetic(chArray[r]))
                r--;

                // Both str[l] and str[r] are not spacial
            else {
                char tmp = chArray[l];
                chArray[l] = chArray[r];
                chArray[r] = tmp;
                l++;
                r--;
            }
        }
        return String.valueOf(chArray);
    }

    public static void main(String[] args) {
        /*Input string: a!!!b.c.d,e'f,ghi
          Output string: i!!!h.g.f,e'd,cba*/
        String str = "a!!!b.c.d,e'f,ghi";
        String res = reverse(str);
        System.out.println("Output string: " + res);
    }
}
