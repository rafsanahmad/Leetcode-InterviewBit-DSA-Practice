/*
 * *
 *  * Substring with Concatenation of All Words.java
 *  * Created by Rafsan Ahmad on 9/1/25, 3:12PM
 *  * Copyright (c) 2025 . All rights reserved.
 *  *
 *
 */

package javaclasses.TwoPointer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SubstringWithConcatOfWords {
    /*You are given a string s and an array of strings words. All the strings of words are of the
     same length.

A concatenated substring in s is a substring that contains all the strings of any permutation of
words concatenated.

For example, if words = ["ab","cd","ef"], then "abcdef", "abefcd", "cdabef", "cdefab", "efabcd",
and "efcdab" are all concatenated strings. "acdbef" is not a concatenated substring because
it is not the concatenation of any permutation of words.
Return the starting indices of all the concatenated substrings in s. You can return the answer
in any order.



Example 1:
Input: s = "barfoothefoobarman", words = ["foo","bar"]
Output: [0,9]
Explanation: Since words.length == 2 and words[i].length == 3, the concatenated substring has to be
 of length 6.
The substring starting at 0 is "barfoo". It is the concatenation of ["bar","foo"] which is a
permutation of words.
The substring starting at 9 is "foobar". It is the concatenation of ["foo","bar"] which is a
permutation of words.
The output order does not matter. Returning [9,0] is fine too.

Example 2:
Input: s = "wordgoodgoodgoodbestword", words = ["word","good","best","word"]
Output: []
Explanation: Since words.length == 4 and words[i].length == 4, the concatenated substring has to
be of length 16.
There is no substring of length 16 in s that is equal to the concatenation of any permutation
of words.
We return an empty array.

Example 3:
Input: s = "barfoofoobarthefoobarman", words = ["bar","foo","the"]
Output: [6,9,12]
Explanation: Since words.length == 3 and words[i].length == 3, the concatenated substring has to
be of length 9.
The substring starting at 6 is "foobarthe". It is the concatenation of ["foo","bar","the"] which
is a permutation of words.
The substring starting at 9 is "barthefoo". It is the concatenation of ["bar","the","foo"] which
is a permutation of words.
The substring starting at 12 is "thefoobar". It is the concatenation of ["the","foo","bar"] which
is a permutation of words.


Constraints:

1 <= s.length <= 10^4
1 <= words.length <= 5000
1 <= words[i].length <= 30
s and words[i] consist of lowercase English letters.*/


    public List<Integer> findSubstring(String s, String[] words) {
        Map<String, Integer> freq = new HashMap<>();
        Map<String, Integer> curr;
        for (String word : words) {
            freq.put(word, freq.getOrDefault(word, 0) + 1);
        }

        int len = s.length();
        int n = words.length;
        int wordSize = words[0].length();
        int windowSize = wordSize * n;

        List<Integer> ans = new ArrayList<>();
        for (int startPos = 0; startPos < wordSize; startPos++) { // Try all starting positions
            int start = startPos;
            do {
                curr = new HashMap<>(freq); // Make a copy of freq map to edit
                String currWord;
                boolean matched = true; // Presume that match happened
                for (int i = 0; i < n; i++) { // Find each word
                    int wordStart = start + i * wordSize;
                    if (wordStart + wordSize > len) {
                        matched = false;
                        break;
                    }
                    currWord = s.substring(wordStart, wordStart + wordSize); // Extract current word
                    if (!curr.containsKey(currWord) || curr.get(currWord) == 0) { // Match word
                        matched = false; // Current word did not match
                        break;
                    }
                    curr.put(currWord, curr.get(currWord) - 1); // Remove word after having found
                }
                if (matched) { // Found match
                    ans.add(start);
                }
                start += wordSize; // Sliding Window
            } while (start + windowSize - 1 < len); // Bound check
        }
        return ans;
    }

    public List<Integer> findSubstringOptimized(String s, String[] words) {
        Map<String, Integer> dict = new HashMap<>();
        for (String word : words) {
            dict.put(word, dict.getOrDefault(word, 0) + 1);
        }

        List<Integer> result = new ArrayList<>();
        int m = words.length, len = words[0].length();
        for (int i = 0; i < len; ++i) {
            traverse(s, i, dict, m, len, result);
        }
        return result;
    }

    private void traverse(String s, int start, Map<String, Integer> dict, int m, int len, List<Integer> result) {
        Map<String, Integer> seen = new HashMap<>();
        int left = start, right = start;
        while (right + len <= s.length()) {
            String word = s.substring(right, right + len);
            if (!dict.containsKey(word)) {
                seen = new HashMap<>();
                right += len;
                left = right;
                continue;
            }
            seen.put(word, seen.getOrDefault(word, 0) + 1);
            while (seen.get(word) > dict.get(word)) {
                String substring = s.substring(left, left + len);
                seen.put(substring, seen.get(substring) - 1);
                left += len;
            }
            right += len;
            if (right - left == m * len) result.add(left);
        }
    }

    public static void main(String[] args) {
        SubstringWithConcatOfWords words = new SubstringWithConcatOfWords();
        String[] w1 = {"foo", "bar"};
        System.out.println(words.findSubstring("barfoothefoobarman", w1));
        System.out.println(words.findSubstring("foobarfoobar", w1));
        System.out.println(words.findSubstringOptimized("barfoothefoobarman", w1));
        System.out.println(words.findSubstringOptimized("foobarfoobar", w1));

        String[] w2 = {"a", "a"};
        System.out.println(words.findSubstring("aaa", w2));

        String[] w3 = {"a"};
        System.out.println(words.findSubstring("a", w3));
    }
}
