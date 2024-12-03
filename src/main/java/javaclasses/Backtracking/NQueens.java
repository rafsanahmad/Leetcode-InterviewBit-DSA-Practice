/*
 * *
 *  * N Queens.java
 *  * Created by Rafsan Ahmad on 12/4/24, 12:10 AM
 *  * Copyright (c) 2024 . All rights reserved.
 *  *
 *
 */

package javaclasses.Backtracking;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class NQueens {
    //https://leetcode.com/problems/n-queens/description/
    //res/queens.jpg
    /*The n-queens puzzle is the problem of placing n queens on an n x n chessboard such that no
    two queens attack each other.

Given an integer n, return all distinct solutions to the n-queens puzzle. You may return the answer
in any order.

Each solution contains a distinct board configuration of the n-queens' placement, where 'Q' and '.'
both indicate a queen and an empty space, respectively.

Example 1:
Input: n = 4
Output: [[".Q..","...Q","Q...","..Q."],["..Q.","Q...","...Q",".Q.."]]
Explanation: There exist two distinct solutions to the 4-queens puzzle as shown above

Example 2:
Input: n = 1
Output: [["Q"]]

Constraints:
1 <= n <= 9*/

    /*
    * Time Complexity: Exponential in nature since we are trying out all ways, to be precise
    * its O(N! * N).
    * Space Complexity: O(N^2)
    * */
    public List<List<String>> solveNQueens(int n) {
        char[][] board = new char[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                board[i][j] = '.';
        List<List<String>> res = new ArrayList<List<String>>();
        dfs(0, board, res);
        return res;
    }

    boolean checkValidPosition(char[][] board, int row, int col) {
        int duprow = row;
        int dupcol = col;
        //UP row
        while (row >= 0 && col >= 0) {
            if (board[row][col] == 'Q') return false;
            row--;
            col--;
        }

        row = duprow;
        col = dupcol;

        //Left columns
        while (col >= 0) {
            if (board[row][col] == 'Q') return false;
            col--;
        }

        row = duprow;
        col = dupcol;

        //Bottom rows
        while (col >= 0 && row < board.length) {
            if (board[row][col] == 'Q') return false;
            col--;
            row++;
        }
        return true;
    }

    void dfs(int col, char[][] board, List<List<String>> res) {
        if (col == board.length) {
            res.add(construct(board));
            return;
        }

        for (int row = 0; row < board.length; row++) {
            if (checkValidPosition(board, row, col)) {
                board[row][col] = 'Q';
                dfs(col + 1, board, res);
                board[row][col] = '.';
            }
        }
    }


    List<String> construct(char[][] board) {
        List<String> res = new LinkedList<String>();
        for (int i = 0; i < board.length; i++) {
            String s = new String(board[i]);
            res.add(s);
        }
        return res;
    }

    public static void main(String[] args) {
        int N = 4;
        NQueens queens = new NQueens();
        List<List<String>> queen = queens.solveNQueens(N);
        System.out.println(queen);
    }
}
