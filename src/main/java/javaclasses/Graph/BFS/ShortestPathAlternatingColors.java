/*
 * *
 *  * Shortest Path with Alternating Colors.java
 *  * Created by Rafsan Ahmad on 2/17/23, 6:19 PM
 *  * Copyright (c) 2023 . All rights reserved.
 *
 */

package javaclasses.Graph.BFS;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Queue;

public class ShortestPathAlternatingColors {
    //https://leetcode.com/problems/shortest-path-with-alternating-colors/description/
    /*You are given an integer n, the number of nodes in a directed graph where the nodes are labeled from 0 to n - 1.
    Each edge is red or blue in this graph, and there could be self-edges and parallel edges.

You are given two arrays redEdges and blueEdges where:

redEdges[i] = [ai, bi] indicates that there is a directed red edge from node ai to node bi in the graph, and
blueEdges[j] = [uj, vj] indicates that there is a directed blue edge from node uj to node vj in the graph.
Return an array answer of length n, where each answer[x] is the length of the shortest path from node 0 to node x such
that the edge colors alternate along the path, or -1 if such a path does not exist.


Example 1
Input: n = 3, redEdges = [[0,1],[1,2]], blueEdges = []
Output: [0,1,-1]

Example 2:
Input: n = 3, redEdges = [[0,1]], blueEdges = [[2,1]]
Output: [0,1,-1]

Constraints:

1 <= n <= 100
0 <= redEdges.length, blueEdges.length <= 400
redEdges[i].length == blueEdges[j].length == 2
0 <= ai, bi, uj, vj < n

*/

    class Edge {
        int val;
        int color; //1 = red, 0 = blue

        Edge(int v, int c) {
            this.val = v;
            this.color = c;
        }
    }

    class Item {
        Edge edge;
        int dist;

        Item(Edge e, int d) {
            this.edge = e;
            this.dist = d;
        }
    }

    public int[] shortestAlternatingPaths(int n, int[][] redEdges, int[][] blueEdges) {
        int[] result = new int[n];
        Arrays.fill(result, -1);
        HashMap<Integer, List<Edge>> map = new HashMap<>();

        for (int i = 0; i < n; i++) {
            map.put(i, new ArrayList<>());
        }

        for (int[] e : redEdges) {
            map.get(e[0]).add(new Edge(e[1], 1));
        }

        for (int[] e : blueEdges) {
            map.get(e[0]).add(new Edge(e[1], 0));
        }

        boolean[][] visited = new boolean[n][2];

        Edge src = new Edge(0, -1);
        result[0] = 0;
        bfsUtil(map, visited, result, src);

        return result;
    }

    public void bfsUtil(HashMap<Integer, List<Edge>> map, boolean[][] visited, int[] result, Edge src) {
        visited[src.val][0] = visited[src.val][1] = true;

        Queue<Item> queue = new ArrayDeque<>();
        queue.add(new Item(src, 0));

        while (!queue.isEmpty()) {
            Item curr = queue.remove();
            int prevColor = curr.edge.color;
            for (Edge edge : map.get(curr.edge.val)) {
                int color = edge.color;
                int val = edge.val;
                if (!visited[val][color] && edge.color != prevColor) {
                    if (result[val] == -1) {
                        result[val] = curr.dist + 1;
                    }
                    visited[val][color] = true;
                    queue.add(new Item(edge, curr.dist + 1));
                }
            }
        }
    }

    public static void main(String[] args) {
        ShortestPathAlternatingColors colors = new ShortestPathAlternatingColors();
        int[][] red = {{0, 1}, {1, 2}, {2, 3}, {3, 4}};
        int[][] blue = {{1, 2}, {2, 3}, {3, 1}};
        System.out.println(Arrays.toString(colors.shortestAlternatingPaths(5, red, blue)));
    }
}
