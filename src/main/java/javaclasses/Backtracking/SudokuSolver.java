/*
 * *
 *  * Sudoku Solver.java
 *  * Created by Rafsan Ahmad on 12/17/22, 8:23 PM
 *  * Copyright (c) 2022 . All rights reserved.
 *
 */

package javaclasses.Backtracking;

public class SudokuSolver {
    //https://leetcode.com/problems/sudoku-solver/description/
    /*Write a program to solve a Sudoku puzzle by filling the empty cells.

A sudoku solution must satisfy all of the following rules:

Each of the digits 1-9 must occur exactly once in each row.
Each of the digits 1-9 must occur exactly once in each column.
Each of the digits 1-9 must occur exactly once in each of the 9 3x3 sub-boxes of the grid.
The '.' character indicates empty cells.


Example 1:
res/sudoku_1.png
Input: board =
[["5","3",".",".","7",".",".",".","."],
["6",".",".","1","9","5",".",".","."],
[".","9","8",".",".",".",".","6","."],
["8",".",".",".","6",".",".",".","3"],
["4",".",".","8",".","3",".",".","1"],
["7",".",".",".","2",".",".",".","6"],
[".","6",".",".",".",".","2","8","."],
[".",".",".","4","1","9",".",".","5"],
[".",".",".",".","8",".",".","7","9"]]

Output:
res/sudoku_2.png
[["5","3","4","6","7","8","9","1","2"],
["6","7","2","1","9","5","3","4","8"],
["1","9","8","3","4","2","5","6","7"],
["8","5","9","7","6","1","4","2","3"],
["4","2","6","8","5","3","7","9","1"],
["7","1","3","9","2","4","8","5","6"],
["9","6","1","5","3","7","2","8","4"],
["2","8","7","4","1","9","6","3","5"],
["3","4","5","2","8","6","1","7","9"]]

Explanation: The input board is shown above and the only valid solution is shown below:

*/

    public void solveSudoku(char[][] board) {
        solveSudokuHelper(board);
    }

    public boolean solveSudokuHelper(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] != '.') continue;

                for (char k = '1'; k <= '9'; k++) {
                    if (isValidCombination(board, i, j, k)) {
                        board[i][j] = k;
                        if (solveSudokuHelper(board)) return true;
                    }
                    board[i][j] = '.';
                }
                return false;
            }
        }

        return true;
    }

    public boolean isValidCombination(char[][] board, int row, int col, char ch) {
        for (int i = 0; i < 9; i++) {
            if (board[i][col] != '.' && board[i][col] == ch) return false;
        }

        for (int j = 0; j < 9; j++) {
            if (board[row][j] != '.' && board[row][j] == ch) return false;
        }

        int m = row / 3 * 3;
        int n = col / 3 * 3;

        for (int x = m; x < m + 3; x++) {
            for (int y = n; y < n + 3; y++) {
                if (board[x][y] != '.' && board[x][y] == ch) return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        SudokuSolver solver = new SudokuSolver();
        char[][] board =
                {
                        {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                        {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                        {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                        {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                        {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                        {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                        {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                        {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                        {'.', '.', '.', '.', '8', '.', '.', '7', '9'}

                };

        solver.solveSudoku(board);

        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {
                System.out.print(board[x][y] + " ");
            }
            System.out.println();
        }
    }
}
