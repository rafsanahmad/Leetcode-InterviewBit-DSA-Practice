/*
 * *
 *  * Nodes In Sub Tree With Same Label.java
 *  * Created by Rafsan Ahmad on 1/13/23, 2:11 PM
 *  * Copyright (c) 2023 . All rights reserved.
 *
 */

package javaclasses.Graph.DFS;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class NodesInSubTreeWithSameLabel {
    //https://leetcode.com/problems/number-of-nodes-in-the-sub-tree-with-the-same-label/description/
    /*
    You are given a tree (i.e. a connected, undirected graph that has no cycles) consisting of n nodes numbered from 0 to
    n - 1 and exactly n - 1 edges. The root of the tree is the node 0, and each node of the tree has a label which is a
    lower-case character given in the string labels (i.e. The node with the number i has the label labels[i]).

The edges array is given on the form edges[i] = [ai, bi], which means there is an edge between nodes ai and bi in the tree.

Return an array of size n where ans[i] is the number of nodes in the subtree of the ith node which have the same label
as node i.

A subtree of a tree T is the tree consisting of a node in T and all of its descendant nodes.

Example 1:

          0(a)
       /      \
     1(b)      2(a)
   /   \      /   \
  4(d)  5(c) 3(e)  6(d)

Input: n = 7, edges = [[0,1],[0,2],[1,4],[1,5],[2,3],[2,6]], labels = "abaedcd"
Output: [2,1,1,1,1,1,1]
Explanation: Node 0 has label 'a' and its sub-tree has node 2 with label 'a' as well, thus the answer is 2. Notice that
any node is part of its sub-tree.
Node 1 has a label 'b'. The sub-tree of node 1 contains nodes 1,4 and 5, as nodes 4 and 5 have different labels than node 1,
 the answer is just 1 (the node itself).


Example 2:

          0(b)
       /      \
     1(b)      3(b)
   /
  2(b)

Input: n = 4, edges = [[0,1],[1,2],[0,3]], labels = "bbbb"
Output: [4,2,1,1]
Explanation: The sub-tree of node 2 contains only node 2, so the answer is 1.
The sub-tree of node 3 contains only node 3, so the answer is 1.
The sub-tree of node 1 contains nodes 1 and 2, both have label 'b', thus the answer is 2.
The sub-tree of node 0 contains nodes 0, 1, 2 and 3, all with label 'b', thus the answer is 4.


Example 3:


Input: n = 5, edges = [[0,1],[0,2],[1,3],[0,4]], labels = "aabab"
Output: [3,2,1,1,1]
    */

    public int[] countSubTrees(int n, int[][] edges, String labels) {
        int[] result = new int[n];
        HashMap<Integer, List<Integer>> map = new HashMap<>();

        for (int i = 0; i < n; i++) {
            map.put(i, new ArrayList<>());
        }

        for (int[] edge : edges) {
            map.get(edge[0]).add(edge[1]);
            map.get(edge[1]).add(edge[0]);
        }

        boolean[] visited = new boolean[n];
        dfsSubTree(map, 0, labels, visited, result);

        return result;
    }

    public int[] dfsSubTree(HashMap<Integer, List<Integer>> map, int node, String labels, boolean[] visited, int[] result) {
        int[] count = new int[26];
        char ch = labels.charAt(node);

        if (visited[node]) {
            result[node] = count[ch - 'a'];
            return count;
        }

        visited[node] = true;

        for (Integer m : map.get(node)) {
            int[] subTreeCount = dfsSubTree(map, m, labels, visited, result);

            for (int i = 0; i < 26; i++) {
                count[i] += subTreeCount[i];
            }

        }

        count[ch - 'a']++;
        result[node] = count[ch - 'a'];

        return count;
    }

    public static void main(String[] args) {
        NodesInSubTreeWithSameLabel sameLabel = new NodesInSubTreeWithSameLabel();
        int[][] edges = {{0, 1}, {1, 2}, {0, 3}};
        System.out.println(Arrays.toString(sameLabel.countSubTrees(4, edges, "bbbb")));

        int[][] edges2 = {{0, 1}, {0, 2}, {1, 3}, {3, 4}, {4, 5}};
        System.out.println(Arrays.toString(sameLabel.countSubTrees(6, edges2, "cbabaa")));

        int[][] edges3 = {{0, 1}, {0, 2}, {1, 4}, {1, 5}, {2, 3}, {2, 6}};
        System.out.println(Arrays.toString(sameLabel.countSubTrees(7, edges3, "abaedcd")));
    }
}
