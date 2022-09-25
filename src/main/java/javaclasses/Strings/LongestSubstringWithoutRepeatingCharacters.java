/*
 * *
 *  * Longest Substring Without Repeating Characters.java
 *  * Created by Rafsan Ahmad on 9/25/22, 4:32 PM
 *  * Copyright (c) 2022 . All rights reserved.
 *
 */

package javaclasses.Strings;

import java.util.HashMap;
import java.util.Map;

public class LongestSubstringWithoutRepeatingCharacters {
    /*Given a string str, find the length of the longest substring without repeating characters.

Example:

For “ABDEFGABEF”, the longest substring are “BDEFGA” and “DEFGAB”, with length 6.

For “BBBB” the longest substring is “B”, with length 1.
*/

    public int lengthOfLongestSubstring(String str) {
        StringBuilder test = new StringBuilder();

        // Result
        int maxLength = -1;

        // Return zero if string is empty
        if (str.isEmpty()) {
            return 0;
        }
        // Return one if string length is one
        else if (str.length() == 1) {
            return 1;
        }
        for (char c : str.toCharArray()) {
            String current = String.valueOf(c);

            // If string already contains the character Then substring after repeating character
            if (test.toString().contains(current)) {
                test = new StringBuilder(test.substring(test.indexOf(current) + 1));
            }
            test.append(c);
            maxLength = Math.max(test.length(), maxLength);
        }

        return maxLength;
    }

    public int lengthOfLongestSubstringUsingMap(String A) {
        int result = 0;
        int start = 0;
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < A.length(); i++) {
            char ch = A.charAt(i);
            if (map.containsKey(ch)) {
                int pos = map.get(ch);
                start = Math.max(start, pos + 1);
            }
            map.put(ch, i);
            result = Math.max(result, i - start);
        }
        return result + 1;
    }

    //Print Longest substring without repeating characters
    /*Examples:

Input : GEEKSFORGEEKS
Output : EKSFORG

Input : ABDEFGABEF
Output : BDEFGA
*/
    /*
The idea is to traverse the string and for each already visited character store its last occurrence in a hash table
(Here unordered_map is used as a hash with key as character and value as its last position).
The variable st stores the starting point of the current substring, maxlen stores the length of maximum length substring,
and start stores the starting index of maximum length substring. While traversing the string, check whether the current
character is present in the hash table or not.

If it is not present, then store the current character in the hash table with value as the current index.
If it is already present in the hash table, this means the current character could repeat in the current substring.
For this check, if the previous occurrence of character is before or after the starting point st of the current substring.
If it is before st, then only update the value in the hash table. If it is after st, then find the length of current
substring currlen as i-st, where i is the current index. Compare currlen with maxlen.
If maxlen is less than currlen, then update maxlen as currlen and start as st.
After complete traversal of the string, the required the longest substring without repeating characters is from
s[start] to s[start+maxlen-1].
*/
    // Function to find and print longest substring without repeating characters.
    public String printLongestSubstring(String str) {
        int i;
        int n = str.length();

        // Starting point of current substring.
        int st = 0;

        // length of current substring.
        int currlen = 0;

        // maximum length substring without repeating characters.
        int maxlen = 0;

        // starting index of maximum length substring.
        int start = 0;

        // Hash Map to store last occurrence of each already visited character.
        HashMap<Character, Integer> pos = new HashMap<>();

        // Last occurrence of first character is index 0;
        pos.put(str.charAt(0), 0);

        for (i = 1; i < n; i++) {
            // If this character is not present in hash, then this is first occurrence of this
            // character, store this in hash.
            if (!pos.containsKey(str.charAt(i))) {
                pos.put(str.charAt(i), i);
            } else {
                // If this character is present in hash then this character has previous occurrence,
                // check if that occurrence is before or after starting point of current substring.
                if (pos.get(str.charAt(i)) >= st) {
                    // find length of current substring and update maxlen and start accordingly.
                    currlen = i - st;
                    if (maxlen < currlen) {
                        maxlen = currlen;
                        start = st;
                    }

                    // Next substring will start after the last occurrence of current character to avoid its repetition.
                    st = pos.get(str.charAt(i)) + 1;
                }

                // Update last occurrence of current character.
                pos.replace(str.charAt(i), i);
            }
        }

        // Compare length of last substring with maxlen and update maxlen and start accordingly.
        if (maxlen < i - st) {
            maxlen = i - st;
            start = st;
        }

        // The required longest substring without repeating characters is from str[start] to str[start+maxlen-1].
        return str.substring(start, start + maxlen);
    }

    public static void main(String[] args) {
        LongestSubstringWithoutRepeatingCharacters characters = new LongestSubstringWithoutRepeatingCharacters();
        System.out.println(characters.lengthOfLongestSubstring("dadbc"));
        System.out.println(characters.lengthOfLongestSubstringUsingMap("dadbc"));
        System.out.println(characters.lengthOfLongestSubstring("ABDEFGABEF"));
        System.out.println(characters.lengthOfLongestSubstringUsingMap("ABDEFGABEF"));
        System.out.println(characters.lengthOfLongestSubstring("BBBB"));
        System.out.println(characters.lengthOfLongestSubstringUsingMap("BBBB"));

        System.out.println(characters.printLongestSubstring("GEEKSFORGEEKS"));
        System.out.println(characters.printLongestSubstring("ABDEFGABEF"));
    }
}
