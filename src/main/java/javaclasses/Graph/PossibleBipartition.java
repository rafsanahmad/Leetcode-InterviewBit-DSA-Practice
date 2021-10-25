/*
 * *
 *  * Created by Rafsan Ahmad on 10/25/21, 9:49 PM
 *  * Copyright (c) 2021 . All rights reserved.
 *
 */

package javaclasses.Graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PossibleBipartition {
    //Leetcode 886
    /*We want to split a group of n people (labeled from 1 to n) into two groups of any size.
    Each person may dislike some other people, and they should not go into the same group.

Given the integer n and the array dislikes where dislikes[i] = [ai, bi] indicates that the person labeled ai
does not like the person labeled bi, return true if it is possible to split everyone into two groups in this way.


Example 1:
Input: n = 4, dislikes = [[1,2],[1,3],[2,4]]
Output: true
Explanation: group1 [1,4] and group2 [2,3].

Example 2:
Input: n = 3, dislikes = [[1,2],[1,3],[2,3]]
Output: false

Example 3:
Input: n = 5, dislikes = [[1,2],[2,3],[3,4],[4,5],[1,5]]
Output: false
 */

    //Graph coloring problem
    public boolean possibleBipartition(int n, int[][] dislikes) {
        HashMap<Integer, List<Integer>> adj = new HashMap<>();
        for (int i = 0; i < n; i++) {
            adj.put(i, new ArrayList<>());
        }
        //Add edges - undirected graph, two way
        for (int[] array : dislikes) {
            adj.get(array[0] - 1).add(array[1] - 1);
            adj.get(array[1] - 1).add(array[0] - 1);
        }

        //Create a visited array - 0 = unvisited, 1 = group A, -1 = group B
        int[] visited = new int[n];
        for (int i = 0; i < n; i++) {
            //if the dfs find two nodes with same group return false
            //Put initial node to 1
            if (visited[i] == 0 && !dfs(adj, visited, i, 1)) {
                return false;
            }
        }
        //Everything checks out
        return true;
    }

    public boolean dfs(HashMap<Integer, List<Integer>> adj, int[] visited, int index, int group) {
        //Put the node to group
        visited[index] = group;
        //check all the neighbor
        for (int j : adj.get(index)) {
            //if any of the neighbor belongs to same group - return false
            if (visited[j] == group) {
                return false;
            }
            //if any neighbor is not visited yet DFS into it & put it to the opposite group
            if (visited[j] == 0 && !dfs(adj, visited, j, -group)) {
                return false;
            }
        }
        //if dfs run without any issues with the neighbors
        return true;
    }

    public static void main(String[] args) {
        PossibleBipartition bipartition = new PossibleBipartition();
        int[][] array = {{1, 2}, {2, 3}, {3, 4}, {4, 5}, {1, 5}};
        System.out.println(bipartition.possibleBipartition(5, array));
    }

}
