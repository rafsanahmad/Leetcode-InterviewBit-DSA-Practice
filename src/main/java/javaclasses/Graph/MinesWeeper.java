/*
 * *
 *  * Minesweeper.java
 *  * Created by Rafsan Ahmad on 11/14/24, 10:11 PM
 *  * Copyright (c) 2024 . All rights reserved.
 *  *
 *
 */

package javaclasses.Graph;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class MinesWeeper {
    //https://leetcode.com/problems/minesweeper/description/
    //res/minesweeper-1.jpeg
    //res/minesweeper-2.jpeg
    /*Let's play the minesweeper game (Wikipedia, online game)!

You are given an m x n char matrix board representing the game board where:

'M' represents an unrevealed mine,
'E' represents an unrevealed empty square,
'B' represents a revealed blank square that has no adjacent mines (i.e., above, below, left, right,
 and all 4 diagonals),
digit ('1' to '8') represents how many mines are adjacent to this revealed square, and
'X' represents a revealed mine.
You are also given an integer array click where click = [clickr, clickc] represents the next click
 position among all the unrevealed squares ('M' or 'E').

Return the board after revealing this position according to the following rules:

1. If a mine 'M' is revealed, then the game is over. You should change it to 'X'.
2. If an empty square 'E' with no adjacent mines is revealed, then change it to a revealed blank
'B' and all of its adjacent unrevealed squares should be revealed recursively.
3. If an empty square 'E' with at least one adjacent mine is revealed, then change it to a digit
('1' to '8') representing the number of adjacent mines.
4. Return the board when no more squares will be revealed.


Example 1:


Input: board = [
["E","E","E","E","E"],
["E","E","M","E","E"],
["E","E","E","E","E"],
["E","E","E","E","E"]
],
click = [3,0]
Output: [
["B","1","E","1","B"],
["B","1","M","1","B"],
["B","1","1","1","B"],
["B","B","B","B","B"]
]

Example 2:
Input: board = [
["B","1","E","1","B"],
["B","1","M","1","B"],
["B","1","1","1","B"],
["B","B","B","B","B"]
],
click = [1,2]
Output: [
["B","1","E","1","B"],
["B","1","X","1","B"],
["B","1","1","1","B"],
["B","B","B","B","B"]
]


Constraints:
m == board.length
n == board[i].length
1 <= m, n <= 50
board[i][j] is either 'M', 'E', 'B', or a digit from '1' to '8'.
click.length == 2
0 <= clickr < m
0 <= clickc < n
board[clickr][clickc] is either 'M' or 'E'.
*/

    public char[][] updateBoard(char[][] board, int[] click) {
        int rowLen = board.length;
        int colLen = board[0].length;
        int[][] mineDirs = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}, {-1, -1}, {1, 1}, {-1, 1}, {1, -1}};

        int clickR = click[0];
        int clickC = click[1];

        if (board[clickR][clickC] == 'M') {
            board[clickR][clickC] = 'X';
            return board;
        }

        int count = 0;
        for (int i = 0; i < mineDirs.length; i++) {
            int newR = clickR + mineDirs[i][0];
            int newC = clickC + mineDirs[i][1];

            if (newR < 0 || newR >= rowLen || newC < 0 || newC >= colLen) continue;
            if (board[newR][newC] == 'M' || board[newR][newC] == 'X') count++;
        }

        if (count > 0) {
            board[clickR][clickC] = (char) (count + '0');
        } else {
            board[clickR][clickC] = 'B';
            //DFS
            for (int i = 0; i < mineDirs.length; i++) {
                int newR = clickR + mineDirs[i][0];
                int newC = clickC + mineDirs[i][1];

                if (newR < 0 || newR >= rowLen || newC < 0 || newC >= colLen) continue;

                if (board[newR][newC] == 'E')
                    updateBoard(board, new int[]{newR, newC});
            }
        }

        return board;
    }

    public char[][] updateBoardBFS(char[][] board, int[] click) {
        int rowLen = board.length;
        int colLen = board[0].length;
        int[][] mineDirs = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}, {-1, -1}, {1, 1}, {-1, 1}, {1, -1}};

        Queue<int[]> queue = new LinkedList<>();
        queue.add(click);

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int currentR = current[0], currentC = current[1];
            if (board[currentR][currentC] == 'M') {
                board[currentR][currentC] = 'X';
                return board;
            }

            int count = 0;
            for (int i = 0; i < mineDirs.length; i++) {
                int newR = currentR + mineDirs[i][0];
                int newC = currentC + mineDirs[i][1];

                if (newR < 0 || newR >= rowLen || newC < 0 || newC >= colLen) continue;
                if (board[newR][newC] == 'M' || board[newR][newC] == 'X') count++;
            }

            if (count > 0) {
                board[currentR][currentC] = (char) (count + '0');
            } else {
                board[currentR][currentC] = 'B';
                //BFS

                for (int i = 0; i < mineDirs.length; i++) {
                    int newR = currentR + mineDirs[i][0];
                    int newC = currentC + mineDirs[i][1];

                    if (newR < 0 || newR >= rowLen || newC < 0 || newC >= colLen) continue;

                    if (board[newR][newC] == 'E') {
                        queue.add(new int[]{newR, newC});
                        board[newR][newC] = 'B'; //Avoid again addition
                    }
                }
            }
        }

        return board;
    }

    public static void main(String[] args) {
        MinesWeeper minesWeeper = new MinesWeeper();
        char[][] board = {
                {'E', 'E', 'E', 'E', 'E'},
                {'E', 'E', 'M', 'E', 'E'},
                {'E', 'E', 'E', 'E', 'E'},
                {'E', 'E', 'E', 'E', 'E'}
        };

        char[][] board2 = {
                {'E', 'E', 'E', 'E', 'E'},
                {'E', 'E', 'M', 'E', 'E'},
                {'E', 'E', 'E', 'E', 'E'},
                {'E', 'E', 'E', 'E', 'E'}
        };

        int[] click = {3, 0};

        minesWeeper.updateBoard(board, click);
        for (char[] row : board) {
            System.out.println(Arrays.toString(row) + ' ');
        }
        System.out.println();

        minesWeeper.updateBoardBFS(board2, click);
        for (char[] row : board2) {
            System.out.println(Arrays.toString(row) + ' ');
        }
        System.out.println();
    }
}
