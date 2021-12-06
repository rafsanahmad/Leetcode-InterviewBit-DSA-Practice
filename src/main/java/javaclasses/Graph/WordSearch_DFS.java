/*
 * * Word Search DFS.java
 *  * Created by Rafsan Ahmad on 10/25/21, 9:49 PM
 *  * Copyright (c) 2021 . All rights reserved.
 *
 */

package javaclasses.Graph;

public class WordSearch_DFS {
    //Leetcode 79
    /*Given an m x n grid of characters board and a string word, return true if word exists in the grid.

The word can be constructed from letters of sequentially adjacent cells, where adjacent cells are horizontally
or vertically neighboring.
The same letter cell may not be used more than once.

Example 1:
Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCCED"
Output: true
*/
    /*This problem can be solve by using a typical DFS algorithm.*/
    public boolean exist(char[][] board, String word) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (dfs(board, word, i, j, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean dfs(char[][] board, String word, int i, int j, int k) {
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
            int pi = i + di[m];
            int pj = j + dj[m];
            if (pi >= 0 && pi < board.length && pj >= 0 && pj < board[0].length &&
                    board[pi][pj] == word.charAt(k + 1)) {
                if (dfs(board, word, pi, pj, k + 1)) {
                    return true;
                }
            }
        }
        board[i][j] = t;
        return false;
    }

    public static void main(String[] args) {
        WordSearch_DFS dfs = new WordSearch_DFS();
        char[][] chars = {
                {'A', 'B', 'C', 'E'},
                {'S', 'F', 'C', 'S'},
                {'A', 'D', 'E', 'E'}
        };
        System.out.println(dfs.exist(chars, "BCCED"));
    }
}
