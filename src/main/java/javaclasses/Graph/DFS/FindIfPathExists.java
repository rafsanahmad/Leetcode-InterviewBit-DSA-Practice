/*
 * *
 *  * Find If Path Exists.java
 *  * Created by Rafsan Ahmad on 12/25/22, 3:49 PM
 *  * Copyright (c) 2023 . All rights reserved.
 *
 */

package javaclasses.Graph.DFS;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FindIfPathExists {
    //https://leetcode.com/problems/find-if-path-exists-in-graph/description/
    /*There is a bi-directional graph with n vertices, where each vertex is labeled from 0 to n - 1 (inclusive).
    The edges in the graph are represented as a 2D integer array edges, where each edges[i] = [ui, vi] denotes a
    bi-directional edge between vertex ui and vertex vi. Every vertex pair is connected by at most one edge, and no
    vertex has an edge to itself.

You want to determine if there is a valid path that exists from vertex source to vertex destination.

Given edges and the integers n, source, and destination, return true if there is a valid path from source to destination,
 or false otherwise.


Example 1:
     0 __ 1
     \   /
       2
Input: n = 3, edges = [[0,1],[1,2],[2,0]], source = 0, destination = 2
Output: true
Explanation: There are two paths from vertex 0 to vertex 2:
- 0 → 1 → 2
- 0 → 2

Example 2:
     1         4
   / |       / |
  0  |      3  |
   \ |       \ |
     2         5

Input: n = 6, edges = [[0,1],[0,2],[3,5],[5,4],[4,3]], source = 0, destination = 5
Output: false
Explanation: There is no path from vertex 0 to vertex 5.
*/

    boolean foundPath = false;

    public boolean validPath(int n, int[][] edges, int source, int destination) {
        HashMap<Integer, List<Integer>> map = new HashMap<>();

        boolean[] visited = new boolean[n];

        for (int i = 0; i < n; i++) {
            map.put(i, new ArrayList<>());
        }

        for (int i = 0; i < edges.length; i++) {
            if (edges[i].length == 2) {
                //bi-directional
                map.get(edges[i][0]).add(edges[i][1]);
                map.get(edges[i][1]).add(edges[i][0]);
            }
        }

        dfsUtil(map, source, visited, destination);
        return foundPath;
    }

    public void dfsUtil(HashMap<Integer, List<Integer>> map, int index, boolean[] visited, int dest) {
        if (!visited[index] && !foundPath) {
            if (index == dest) {
                foundPath = true;
                return;
            }
            visited[index] = true;
            for (Integer adj : map.get(index)) {
                dfsUtil(map, adj, visited, dest);
            }
        }
    }

    public static void main(String[] args) {
        FindIfPathExists findIfPathExists = new FindIfPathExists();
        int[][] edges = {{0, 1}, {1, 2}, {2, 0}};
        System.out.println(findIfPathExists.validPath(3, edges, 0, 2));

        int[][] edges2 = {
                {4, 3}, {1, 4}, {4, 8}, {1, 7}, {6, 4},
                {4, 2}, {7, 4}, {4, 0}, {0, 9}, {5, 4}
        };
        System.out.println(findIfPathExists.validPath(10, edges2, 5, 9));
    }
}
