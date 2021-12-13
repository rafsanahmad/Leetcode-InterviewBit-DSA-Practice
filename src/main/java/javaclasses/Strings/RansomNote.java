/*
 * * Ransom Note.java
 *  * Created by Rafsan Ahmad on 10/25/21, 9:49 PM
 *  * Copyright (c) 2021 . All rights reserved.
 *
 */

package javaclasses.Strings;

/*Given an arbitrary ransom note string and another string containing letters from all the magazines,
write a function that will return true if the ransom note can be constructed from the magazines ;
otherwise, it will return false.

Each letter in the magazine string can only be used once in your ransom note.

Example 1:
Input: ransomNote = "a", magazine = "b"
Output: false

Example 2:
Input: ransomNote = "aa", magazine = "ab"
Output: false

Example 3:
Input: ransomNote = "aa", magazine = "aab"
Output: true
 

Constraints:

You may assume that both strings contain only lowercase letters.
*/

/*LeetCode 383*/

public class RansomNote {
    public static boolean canConstruct(String ransomNote, String magazine) {
        int[] arr = new int[26];
        int mgLne = magazine.length();
        int ranLen = ransomNote.length();

        for (int i = 0; i < mgLne; i++) {
            arr[magazine.charAt(i) - 97]++; //a ascii code is 97
        }

        //if value is less than zero, it means
        //character is not available for ransom note
        for (int i = 0; i < ranLen; i++) {
            if (--arr[ransomNote.charAt(i) - 97] < 0)
                return false;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(canConstruct("aa", "aab"));
        System.out.println(canConstruct("aa", "ab"));
        System.out.println(canConstruct("rafsan", "ahmadrafsen"));
        System.out.println(canConstruct("bbcd", "aabbbbc"));
    }
}