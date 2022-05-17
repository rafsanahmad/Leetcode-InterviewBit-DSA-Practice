/*
 * * Find Path.java
 *  * Created by Rafsan Ahmad on 10/25/21, 9:49 PM
 *  * Copyright (c) 2021 . All rights reserved.
 *
 */

package javaclasses.Graph;

import java.util.ArrayList;
import java.util.List;

public class FindPath {

    /*Given a grid and a word, write a function that returns the location of the word in the grid as a list
    of coordinates. If there are multiple matches, return any one.

grid1 = [
	['c', 'c', 'c', 'a', 'r', 's'],
	['c', 'c', 'i', 't', 'n', 'b'],
	['a', 'c', 'n', 'n', 't', 'i'],
	['t', 'c', 'i', 'i', 'p', 't']
]

word1_1 = "catnip"
find_word_location(grid1, word1_1)-> [ (0, 2), (0, 3), (1, 3), (2, 3), (3, 3), (3, 4) ]

word1_2 = "cccc"
find_word_location(grid1, word1_2)-> [(0, 1), (1, 1), (2, 1), (3, 1)]
OR [(0, 0), (1, 0), (1, 1), (2, 1)]
OR [(0, 0), (0, 1), (1, 1), (2, 1)]
OR [(1, 0), (1, 1), (2, 1), (3, 1)]


grid2 = [
    ['c', 'p', 'a', 'n', 't', 's'],
    ['a', 'b', 'i', 't', 'a', 'b'],
    ['t', 'f', 'n', 'n', 'c', 'i'],
    ['x', 's', 'c', 'a', 't', 'n'],
    ['x', 's', 'd', 'd', 'e', 'a'],
    ['s', 'q', 'w', 'x', 's', 'p']
]


word2 = "catnap"
find_word_location(grid2, word2)-> [ (3, 2), (3, 3), (3, 4), (3, 5), (4, 5), (5, 5) ]
*/
    List<ArrayList> output = new ArrayList<>();

    private void search(char[][] grid1, String word) {
        boolean[][] visited = new boolean[grid1.length][grid1[0].length];

        for (int i = 0; i < grid1.length; i++) {
            for (int j = 0; j < grid1.length; j++) {
                if (grid1[i][j] == word.charAt(0)) {
                    dfs(grid1, i, j, new ArrayList<String>(), word, 0, visited);
                }
            }
        }

        if (output.size() == 0) {
            System.out.print("None");
        } else {
            System.out.print(output.toString());
        }
    }


    private void dfs(char[][] grid1, int i, int j, ArrayList<String> indexTracker, String word, int wordIndex, boolean[][] visited) {

        if (i < 0 || i >= grid1.length || j < 0 || j >= grid1[i].length || grid1[i][j] != word.charAt(wordIndex) || visited[i][j]) {
            return;
        }

        indexTracker.add("(" + i + ", " + j + ")");
        visited[i][j] = true;
        if (wordIndex == word.length() - 1) {
            System.out.println("Found word");
            output.add(new ArrayList<String>(indexTracker));
            return;
        }

        dfs(grid1, i + 1, j, indexTracker, word, wordIndex + 1, visited);
        dfs(grid1, i, j + 1, indexTracker, word, wordIndex + 1, visited);

    }

    public static void main(String[] args) {
        char[][] chArray = {
                {'c', 'p', 'a', 'n', 't', 's'},
                {'a', 'b', 'i', 't', 'a', 'b'},
                {'t', 'f', 'n', 'n', 'c', 'i'},
                {'x', 's', 'c', 'a', 't', 'n'},
                {'x', 's', 'd', 'd', 'e', 'a'},
                {'s', 'q', 'w', 'x', 's', 'p'}
        };
        new FindPath().search(chArray, "catnap");
    }
}
