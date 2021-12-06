/*
 * * Word Search II.java
 *  * Created by Rafsan Ahmad on 10/25/21, 9:49 PM
 *  * Copyright (c) 2021 . All rights reserved.
 *
 */

package javaclasses.Graph;

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
    }
}
