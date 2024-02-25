/*
 * *
 *  * Word Break II.java
 *  * Created by Rafsan Ahmad on 2/26/24, 1:32 AM
 *  * Copyright (c) 2024 . All rights reserved.
 *  *
 *
 */

package javaclasses.Graph.Trie;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WordBreakII {
    /*Given a string s and a dictionary of strings wordDict, add spaces in s to construct a
    sentence where each word is a valid dictionary word. Return all such possible sentences in
    any order.

Note that the same word in the dictionary may be reused multiple times in the segmentation.



Example 1:

Input: s = "catsanddog", wordDict = ["cat","cats","and","sand","dog"]
Output: ["cats and dog","cat sand dog"]

Example 2:
Input: s = "pineapplepenapple", wordDict = ["apple","pen","applepen","pine","pineapple"]
Output: ["pine apple pen apple","pineapple pen apple","pine applepen apple"]
Explanation: Note that you are allowed to reuse a dictionary word.

Example 3:
Input: s = "catsandog", wordDict = ["cats","dog","sand","and","cat"]
Output: []


Constraints:

1 <= s.length <= 20
1 <= wordDict.length <= 1000
1 <= wordDict[i].length <= 10
s and wordDict[i] consist of only lowercase English letters.
All the strings of wordDict are unique.
Input is generated in a way that the length of the answer doesn't exceed 10^5.*/

    class TrieNode {
        TrieNode[] childern;
        boolean endOfWord;

        TrieNode() {
            childern = new TrieNode[26];
            endOfWord = false;
        }
    }

    public List<String> wordBreak(String s, List<String> wordDict) {
        List<String> result = new ArrayList<>();
        if (s.length() == 0 || wordDict.isEmpty()) {
            return result;
        }

        TrieNode node = buildTrie(wordDict);
        for (int i = 0; i < s.length(); i++) {
            String sub = s.substring(0, i + 1);
            if (searchTrie(sub, node)) {
                solve(s, sub, result, i + 1, node);
            }
        }
        return result;
    }

    public void solve(String str, String sub, List<String> res, int pos, TrieNode node) {
        if (pos == str.length()) {
            res.add(sub);
            return;
        }

        for (int j = pos; j < str.length(); j++) {
            StringBuilder builder = new StringBuilder(sub);
            builder.append(" ");
            String s = str.substring(pos, j + 1);
            builder.append(s);
            if (searchTrie(s, node))
                solve(str, builder.toString(), res, j + 1, node);
        }
    }

    public boolean searchTrie(String word, TrieNode root) {
        TrieNode p = root;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            if (p.childern[ch - 'a'] == null) return false;
            p = p.childern[ch - 'a'];
        }

        return p.endOfWord;
    }

    public TrieNode buildTrie(List<String> dict) {
        TrieNode root = new TrieNode();

        for (String str : dict) {
            TrieNode p = root;
            for (char ch : str.toCharArray()) {
                if (p.childern[ch - 'a'] == null) {
                    p.childern[ch - 'a'] = new TrieNode();
                }
                p = p.childern[ch - 'a'];
            }
            p.endOfWord = true;
        }

        return root;
    }

    public static void main(String[] args) {
        WordBreakII breakII = new WordBreakII();
        List<String> words = Arrays.asList("apple", "pen", "applepen", "pine", "pineapple");
        System.out.println(breakII.wordBreak("pineapplepenapple", words));
    }
}
