package javaclasses.Strings;
/*Given a string, find the length of the longest substring without repeating characters.

For example –

Example 1:

Input: “abcabcbb” Output: 3

The output string is “abc”, with a length of 3.

Example 2:

Input: “bbbbb” Output: 1

The longest substring in this example is “b”. Its length is 1.

Example 3:

Input: “pwwkew” Output: 3

The answer is “wke”. Its length is 3.

*/

/*In this example, I am going to explain how to find the length of longest non-repeating substring using sliding window approach.


The idea here is to maintain a window of unique character. Each window has start and end index, based on that we know the window size.
To check unique character in a window, i am using set data structure. Set does not allow duplicate values and also the lookup time is O(1).

Here are the following steps to solve this problem –

Declare two indexes i and j and initialize with zero. Variable i indicate index of the start window and j indicate end index.
Traverse a string and pick one character at a time.
First check, if a character exists in a set. If it doesn’t exist in a set then add them in a set and increment the count of j and the index of i remain as it is.
Also, keep track of the length of a window.
If character exists in a set then remove the character from a set and increment the count of i until all the characters in a window is unique again.
Repeat this step until the string is traversed completely.
The time complexity of this approach is O(n) and it’s space complexity is also O(n).*/


/*
Java code to find the length of longest substring 
without repeating characters.
*/

import java.util.HashSet;
import java.util.Set;

public class LongestSubstringWithoutRepeating {

    public static int lengthOfLongestSubstring(String s) {

        int maxCount = 0;
        int i = 0;
        int j = 0;
        int strLen = s.length();

        //Declare set of characters
        Set<Character> st = new HashSet<>();

        //Traverse a string
        while (i < strLen && j < strLen) {

            //If it's a unique character add in a set
            if (!st.contains(s.charAt(j))) {
                st.add(s.charAt(j));
                j++;
                maxCount = Math.max(maxCount, j - i);
            } else {
                st.remove(s.charAt(i));
                i++;
            }
        }

        return maxCount;
    }

    public static void main(String[] args) {

        String str = "abcdabcdbb";
        int len = lengthOfLongestSubstring(str);

        System.out.println(" Length " + len);
    }
}

