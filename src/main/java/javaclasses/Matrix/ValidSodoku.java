/*
 * * Valid Sodoku.java
 *  * Created by Rafsan Ahmad on 10/25/21, 9:49 PM
 *  * Copyright (c) 2021 . All rights reserved.
 *
 */

package javaclasses.Matrix;

import java.util.HashSet;

public class ValidSodoku {
    //Leetcode 36
    /*Determine if a 9 x 9 Sudoku board is valid. Only the filled cells need to be validated according
    to the following rules:

Each row must contain the digits 1-9 without repetition.
Each column must contain the digits 1-9 without repetition.
Each of the nine 3 x 3 sub-boxes of the grid must contain the digits 1-9 without repetition.
Note:

A Sudoku board (partially filled) could be valid but is not necessarily solvable.
Only the filled cells need to be validated according to the mentioned rules.

Example 1:
Input: board =
[["5","3",".",".","7",".",".",".","."]
,["6",".",".","1","9","5",".",".","."]
,[".","9","8",".",".",".",".","6","."]
,["8",".",".",".","6",".",".",".","3"]
,["4",".",".","8",".","3",".",".","1"]
,["7",".",".",".","2",".",".",".","6"]
,[".","6",".",".",".",".","2","8","."]
,[".",".",".","4","1","9",".",".","5"]
,[".",".",".",".","8",".",".","7","9"]]
Output: true

Example 2:
Input: board =
[["8","3",".",".","7",".",".",".","."]
,["6",".",".","1","9","5",".",".","."]
,[".","9","8",".",".",".",".","6","."]
,["8",".",".",".","6",".",".",".","3"]
,["4",".",".","8",".","3",".",".","1"]
,["7",".",".",".","2",".",".",".","6"]
,[".","6",".",".",".",".","2","8","."]
,[".",".",".","4","1","9",".",".","5"]
,[".",".",".",".","8",".",".","7","9"]]
Output: false
Explanation: Same as Example 1, except with the 5 in the top left corner being modified to 8.
 Since there are two 8's in the top left 3x3 sub-box, it is invalid.*/

    public boolean isValidSudoku(char[][] board) {
        int N = 9;

        //A valid sudoku board should satisfy three conditions:
        // (1) each row,
        // (2) each column, and
        // (3) each box (3X3) has no duplicate numbers.

        //Using hash set to record status
        HashSet<Character>[] rows = new HashSet[N];
        HashSet<Character>[] cols = new HashSet[N];
        HashSet<Character>[] boxes = new HashSet[N];

        for (int r = 0; r < N; r++) {
            rows[r] = new HashSet<Character>();
            cols[r] = new HashSet<Character>();
            boxes[r] = new HashSet<Character>();
        }

        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                char val = board[r][c];
                if (val == '.') {
                    continue;
                }

                //Check row
                if (rows[r].contains(val)) {
                    return false;
                }
                rows[r].add(val);

                //check the column
                if (cols[c].contains(val)) {
                    return false;
                }
                cols[c].add(val);

                //Check the box
                int idx = (r / 3) * 3 + (c / 3);
                if (boxes[idx].contains(val)) {
                    return false;
                }
                boxes[idx].add(val);

            }
        }
        return true;
    }
}
