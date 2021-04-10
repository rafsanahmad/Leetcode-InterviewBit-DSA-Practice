package javaclasses.Strings;
/*Given a word, you need to judge whether the usage of capitals in it is right or not.

We define the usage of capitals in a word to be right when one of the following cases holds:

All letters in this word are capitals, like "USA".
All letters in this word are not capitals, like "leetcode".
Only the first letter in this word is capital, like "Google".
Otherwise, we define that this word doesnt use capitals in a right way 

Example 1:

Input: "USA"
Output: True
 

Example 2:

Input: "FlaG"
Output: False
 

Note: The input will be a non-empty word consisting of uppercase and lowercase latin letters.*/

/*LeetCode 520*/

public class DetectCapital {
    public boolean detectCapitalUse(String word) {
        int small = 0;
        int capital = 0;

        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            if (ch >= 65 && ch <= 90) {
                //Ascii A-Z
                capital++;
            } else {
                small++;
            }
        }
        return capital == word.length() || small == word.length()
                || (capital == 1 && word.charAt(0) >= 65 && word.charAt(0) <= 90);
    }

    public static void main(String[] args) {

    }
}

