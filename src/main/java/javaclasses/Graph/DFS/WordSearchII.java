/*
 * *
 *  * Word Search II.java
 *  * Created by Rafsan Ahmad on 12/6/21, 4:28 PM
 *  * Copyright (c) 2023 . All rights reserved.
 *
 */

package javaclasses.Graph.DFS;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class WordSearchII {
    //Leetcode 212
    //res/word_search.jpeg
    /*Given an m x n board of characters and a list of strings words, return all words on the board.

Each word must be constructed from letters of sequentially adjacent cells, where adjacent cells are
horizontally or vertically neighboring. The same letter cell may not be used more than once in a word.

Example 1:
Input: board = [["o","a","a","n"],["e","t","a","e"],["i","h","k","r"],["i","f","l","v"]],
 words = ["oath","pea","eat","rain"]
Output: ["eat","oath"]

Example 2:
Input: board = [["a","b"],["c","d"]], words = ["abcb"]
Output: []
*/

    //Time Limit Exceeded
    public List<String> findWords(char[][] board, String[] words) {
        HashSet<String> set = new HashSet();

        for (int k = 0; k < words.length; k++) {
            String word = words[k];
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[0].length; j++) {
                    if (dfs(board, i, j, 0, word)) {
                        set.add(word);
                    }
                }
            }
        }
        List<String> mainList = new ArrayList<String>();
        mainList.addAll(set);
        return mainList;
    }

    public boolean dfs(char[][] board, int i, int j, int k, String word) {
        if (board[i][j] != word.charAt(k)) {
            return false;
        }

        if (k >= word.length() - 1) {
            return true;
        }

        int[] di = {-1, 0, 1, 0};
        int[] dj = {0, 1, 0, -1};

        char t = board[i][j];
        board[i][j] = '#';

        for (int m = 0; m < 4; m++) {
            int pi = di[m] + i;
            int pj = dj[m] + j;

            if (pi >= 0 && pi < board.length && pj >= 0 && pj < board[0].length &&
                    board[pi][pj] == word.charAt(k + 1)) {
                if (dfs(board, pi, pj, k + 1, word)) {
                    board[i][j] = t;
                    return true;
                }
            }
        }

        board[i][j] = t;
        return false;
    }


    /*Backtracking + Trie
Intuitively, start from every cell and try to build a word in the dictionary. Backtracking (dfs) is the powerful way to
exhaust every possible ways. Apparently, we need to do pruning when current character is not in any word.

How do we instantly know the current character is invalid? HashMap?
How do we instantly know what's the next valid character? LinkedList?
But the next character can be chosen from a list of characters. "Mutil-LinkedList"?
Combing them, Trie is the natural choice. Notice that:

TrieNode is all we need. search and startsWith are useless.
No need to store character at TrieNode. c.next[i] != null is enough.
Never use c1 + c2 + c3. Use StringBuilder.
No need to use O(n^2) extra space visited[m][n].
No need to use StringBuilder. Storing word itself at leaf node is enough.
No need to use HashSet to de-duplicate. Use "one time search" trie.

Code Optimization

59ms: Use search and startsWith in Trie class like this popular solution.
33ms: Remove Trie class which unnecessarily starts from root in every dfs call.
30ms: Use w.toCharArray() instead of w.charAt(i).
22ms: Use StringBuilder instead of c1 + c2 + c3.
20ms: Remove StringBuilder completely by storing word instead of boolean in TrieNode.
20ms: Remove visited[m][n] completely by modifying board[i][j] = '#' directly.
18ms: check validity, e.g., if(i > 0) dfs(...), before going to the next dfs.
17ms: De-duplicate c - a with one variable i.
15ms: Remove HashSet completely. dietpepsi's idea is awesome.
*/
    class TrieNode {
        TrieNode[] next = new TrieNode[26];
        String word;
    }

    public List<String> findWordsOptimized(char[][] board, String[] words) {
        List<String> res = new ArrayList<>();
        TrieNode root = buildTrie(words);
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                dfs(board, i, j, root, res);
            }
        }
        return res;
    }

    public void dfs(char[][] board, int i, int j, TrieNode p, List<String> res) {
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length) return;

        char c = board[i][j];
        if (c == '#' || p.next[c - 'a'] == null) return;

        p = p.next[c - 'a'];
        if (p.word != null) {   // found one
            res.add(p.word);
            p.word = null;     // de-duplicate
        }

        board[i][j] = '#';

        //All four direction
        dfs(board, i, j + 1, p, res);
        dfs(board, i, j - 1, p, res);
        dfs(board, i - 1, j, p, res);
        dfs(board, i + 1, j, p, res);

        board[i][j] = c;
    }

    public TrieNode buildTrie(String[] words) {
        TrieNode root = new TrieNode();
        for (String w : words) {
            TrieNode p = root;
            for (char c : w.toCharArray()) {
                int i = c - 'a';
                if (p.next[i] == null) p.next[i] = new TrieNode();
                p = p.next[i];
            }
            p.word = w;
        }
        return root;
    }

    public static void main(String[] args) {
        WordSearchII searchII = new WordSearchII();
        char[][] chArray = {
                {'o', 'a', 'a', 'n'},
                {'e', 't', 'a', 'e'},
                {'i', 'h', 'k', 'r'},
                {'i', 'f', 'l', 'v'}
        };
        String[] strArray = {"oath", "pea", "eat", "rain"};
        System.out.println(searchII.findWords(chArray, strArray));
        System.out.println(searchII.findWordsOptimized(chArray, strArray));
    }
}
