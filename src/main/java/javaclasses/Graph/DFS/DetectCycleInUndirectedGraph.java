/*
 * *
 *  * Detect Cycle In Undirected Graph.java
 *  * Created by Rafsan Ahmad on 11/14/24, 10:11 PM
 *  * Copyright (c) 2024 . All rights reserved.
 *  *
 *
 */

package javaclasses.Graph.DFS;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class DetectCycleInUndirectedGraph {
    /*Problem Description

Given an undirected graph having A nodes labelled from 1 to A with M edges given in a form of matrix B of size M x 2
 where (B[i][0], B[i][1]) represents two nodes B[i][0] and B[i][1] connected by an edge.

Find whether the graph contains a cycle or not, return 1 if cycle is present else return 0.

NOTE:

The cycle must contain atleast three nodes.
There are no self-loops in the graph.
There are no multiple edges between two nodes.
The graph may or may not be connected.
Nodes are numbered from 1 to A.
Your solution will run on multiple test cases. If you are using global variables make sure to clear them.


Problem Constraints
1 <= A, M <= 310^5

1 <= B[i][0], B[i][1] <= A


Input Format
The first argument given is an integer A representing the number of nodes in the graph.

The second argument given is an matrix B of size M x 2 which represents the M edges such that there is a edge between
node B[i][0] and node B[i][1].


Output Format
Return 1 if cycle is present else return 0.

Example Input
Input 1:
 A = 5
 B = [  [1. 2]
        [1, 3]
        [2, 3]
        [1, 4]
        [4, 5]
     ]

Input 2:
 A = 3
 B = [  [1. 2]
        [1, 3]
     ]


Example Output
Output 1:
 1

Output 2:
 0

Example Explanation*
Explanation 1:
 There is a cycle in the graph i.e 1 -> 2 -> 3 -> 1 so we will return 1

Explanation 2:
 No cycle present in the graph so we will return 0.*/

    // Adjacency List Representation
    private LinkedList<Integer> adj[];

    public int detectCycleInUnDirectedGraph(int A, int[][] B) {
        adj = new LinkedList[A + 1];

        for (int i = 0; i <= A; i++) {
            adj[i] = new LinkedList<>();
        }

        for (int i = 0; i < B.length; i++) {
            //UnDirected graph - two way
            adj[B[i][0]].add(B[i][1]);
            adj[B[i][1]].add(B[i][0]);
        }

        boolean[] visited = new boolean[A + 1];
        Arrays.fill(visited, false);

        for (int i = 0; i < A; i++)
            if (!visited[i]) {
                if (isCyclicUtil(i, visited, -1)) {
                    return 1;
                }
            }

        return 0;
    }

    public boolean isCyclicUtil(int i, boolean[] visited, int parent) {
        // Mark the current node as visited
        visited[i] = true;

        List<Integer> children = adj[i];

        for (int j = 0; j < children.size(); j++) {
            int neighbour = children.get(j);
            // If an adjacent is not visited, then recur for that adjacent
            if (!visited[neighbour]) {
                if (isCyclicUtil(neighbour, visited, i))
                    return true;
            }

            // If an adjacent is visited and not parent of current vertex, then there is a cycle.
            else if (neighbour != parent)
                return true;
        }

        return false;
    }

    public static void main(String[] args) {
        DetectCycleInUndirectedGraph graph = new DetectCycleInUndirectedGraph();
        int[][] arr = {{1, 2}, {1, 3}, {2, 3}, {1, 4}, {4, 5}};
        int[][] arr2 = {{1, 2}, {1, 3}};
        System.out.println(graph.detectCycleInUnDirectedGraph(5, arr));
        System.out.println(graph.detectCycleInUnDirectedGraph(3, arr2));
    }
}
