/*
 * *
 *  * Longest Substring with At Most K Distinct Characters.java
 *  * Created by Rafsan Ahmad on 9/29/22, 6:19 PM
 *  * Copyright (c) 2022 . All rights reserved.
 *
 */

package javaclasses.TwoPointer;

import java.util.Arrays;
import java.util.HashMap;

public class LongestSubstringWithKDistinctCharacters {
    /*Given a string you need to print longest possible substring that has exactly M unique characters.
    If there are more than one substring of longest possible length, then print any one of them.

Examples:

Input: Str = “aabbcc”, k = 1
Output: 2
Explanation: Max substring can be any one from {“aa” , “bb” , “cc”}.

Input: Str = “aabbcc”, k = 2
Output: 4
Explanation: Max substring can be any one from {“aabb” , “bbcc”}.

Input: Str = “aabbcc”, k = 3
Output: 6
Explanation:
There are substrings with exactly 3 unique characters
{“aabbcc” , “abbcc” , “aabbc” , “abbc” }
Max is “aabbcc” with length 6.

Input: Str = “aaabbb”, k = 3
Output: Not enough unique characters
Explanation: There are only two unique characters, thus show error message.*/

    //Time Complexity = O(n^2)
    public int kDistinctChars(int k, String str) {
        int result = 0;
        HashMap<Character, Integer> map = new HashMap<>();
        int len = str.length();
        for (int i = 0; i < len; i++) {
            char ch = str.charAt(i);
            map.put(ch, map.getOrDefault(ch, 0) + 1);

            for (int j = i + 1; j < len; j++) {
                char inner = str.charAt(j);
                map.put(inner, map.getOrDefault(inner, 0) + 1);

                if (map.size() <= k) {
                    result = Math.max(result, j - i);
                } else {
                    map.remove(str.charAt(i));
                    break;
                }
            }
        }
        return result + 1;
    }

    final static int MAX_CHARS = 26;

    boolean isValid(int[] count, int k) {
        int val = 0;
        for (int i = 0; i < MAX_CHARS; i++) {
            if (count[i] > 0) {
                val++;
            }
        }

        // Return true if k is greater than or equal to val
        return (k >= val);
    }

    /*The problem can be solved in O(n).
    Idea is to maintain a window and add elements to the window till it contains less or equal k,
    update our result if required while doing so. If unique elements exceeds than required in window,
    start removing the elements from left side.
    Below are the implementations of above. The implementations assume that the input string alphabet
    contains only 26 characters (from ‘a’ to ‘z’). The code can be easily extended to 256 characters. */
    
    // Time Complexity = O(n)
    void kDistinctCharsOptimized(int k, String s) {
        int u = 0;
        int n = s.length();

        // Associative array to store the count of characters
        int[] count = new int[MAX_CHARS];
        Arrays.fill(count, 0);
        // Traverse the string, Fills the associative array count[] and count number
        // of unique characters
        for (int i = 0; i < n; i++) {
            if (count[s.charAt(i) - 'a'] == 0) {
                u++;
            }
            count[s.charAt(i) - 'a']++;
        }

        // If there are not enough unique characters, show an error message.
        if (u < k) {
            System.out.print("Not enough unique characters");
            return;
        }

        // Otherwise take a window with first element in it. start and end variables.
        int curr_start = 0, curr_end = 0;

        // Also initialize values for result longest window
        int max_window_size = 1;
        int max_window_start = 0;

        // Initialize associative array count[] with zero
        Arrays.fill(count, 0);

        // put the first character
        count[s.charAt(0) - 'a']++;

        // Start from the second character and add characters in window according to above explanation
        for (int i = 1; i < n; i++) {
            // Add the character 's[i]' to current window
            count[s.charAt(i) - 'a']++;
            curr_end++;

            // If there are more than k unique characters in current window, remove from left side
            while (!isValid(count, k)) {
                count[s.charAt(curr_start) - 'a']--;
                curr_start++;
            }

            // Update the max window size if required
            if (curr_end - curr_start + 1 > max_window_size) {
                max_window_size = curr_end - curr_start + 1;
                max_window_start = curr_start;
            }
        }

        System.out.println("Max substring is : "
                + s.substring(max_window_start,
                max_window_start + max_window_size)
                + " with length " + max_window_size);
    }

    public static void main(String[] args) {
        LongestSubstringWithKDistinctCharacters characters = new LongestSubstringWithKDistinctCharacters();
        System.out.println(characters.kDistinctChars(2, "aabbcc"));
        characters.kDistinctCharsOptimized(2, "aabbcc");

        System.out.println(characters.kDistinctChars(3, "abcddefg"));
        characters.kDistinctCharsOptimized(3, "abcddefg");
    }
}
