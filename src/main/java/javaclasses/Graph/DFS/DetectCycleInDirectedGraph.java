/*
 * *
 *  * Detect Cycle In Directed Graph.java
 *  * Created by Rafsan Ahmad on 11/14/24, 10:10 PM
 *  * Copyright (c) 2024 . All rights reserved.
 *  *
 *
 */

package javaclasses.Graph.DFS;

import java.util.LinkedList;
import java.util.List;

public class DetectCycleInDirectedGraph {
    /*
Problem Description

Given an directed graph having A nodes. A matrix B of size M x 2 is given which represents the M edges such that there is
 a edge directed from node B[i][0] to node B[i][1].

Find whether the graph contains a cycle or not, return 1 if cycle is present else return 0.

NOTE:

The cycle must contain atleast two nodes.
There are no self-loops in the graph.
There are no multiple edges between two nodes.
The graph may or may not be connected.
Nodes are numbered from 1 to A.

Problem Constraints
2 <= A <= 10^5

1 <= M <= min(200000,A(A-1))

1 <= B[i][0], B[i][1] <= A

Input Format
The first argument given is an integer A representing the number of nodes in the graph.

The second argument given a matrix B of size M x 2 which represents the M edges such that there is a edge directed from
node B[i][0] to node B[i][1].

Output Format
Return 1 if cycle is present else return 0.


Example Input
Input 1:

 A = 5
 B = [  [1, 2]
        [4, 1]
        [2, 4]
        [3, 4]
        [5, 2]
        [1, 3]
     ]

Input 2:

 A = 5
 B = [  [1, 2]
        [2, 3]
        [3, 4]
        [4, 5]
     ]


Example Output
Output 1:
 1

Output 2:
 0

Example Explanation*
Explanation 1:
 The given graph contain cycle 1 -> 3 -> 4 -> 1 or the cycle 1 -> 2 -> 4 -> 1

Explanation 2:
 The given graph doesn't contain any cycle.

res/graph_cycle.png
Approach: Depth First Traversal can be used to detect a cycle in a Graph. DFS for a connected graph produces a tree.
There is a cycle in a graph only if there is a back edge present in the graph. A back edge is an edge that is from a
node to itself (self-loop) or one of its ancestors in the tree produced by DFS. In the following graph, there are 3
back edges, marked with a cross sign. We can observe that these 3 back edges indicate 3 cycles present in the graph.

For a disconnected graph, Get the DFS forest as output. To detect cycle, check for a cycle in individual trees by
checking back edges.
To detect a back edge, keep track of vertices currently in the recursion stack of function for DFS traversal.
If a vertex is reached that is already in the recursion stack, then there is a cycle in the tree. The edge that
connects the current vertex to the vertex in the recursion stack is a back edge. Use recStack[] array to keep track
of vertices in the recursion stack.


Algorithm:
1. Create the graph using the given number of edges and vertices.
2. Create a recursive function that initializes the current index or vertex, visited, and recursion stack.
3. Mark the current node as visited and also mark the index in recursion stack.
4. Find all the vertices which are not visited and are adjacent to the current node. Recursively call the function for
those vertices, If the recursive function returns true, return true.
5. If the adjacent vertices are already marked in the recursion stack then return true.
6. Create a wrapper class, that calls the recursive function for all the vertices and if any function returns true
return true. Else if for all vertices the function returns false return false.
*/

    // Adjacency List Representation
    private LinkedList<Integer> adj[];

    public int detectCycleInDirectedGraph(int A, int[][] B) {
        adj = new LinkedList[A + 1];

        for (int i = 0; i <= A; i++) {
            adj[i] = new LinkedList<>();
        }

        for (int i = 0; i < B.length; i++) {
            //Directed graph - one way
            adj[B[i][0]].add(B[i][1]);
        }

        boolean[] visited = new boolean[A + 1];
        boolean[] recStack = new boolean[A + 1];

        for (int i = 0; i < A; i++)
            if (isCyclicUtil(i, visited, recStack)) {
                return 1;
            }
        return 0;
    }

    public boolean isCyclicUtil(int i, boolean[] visited, boolean[] recStack) {
        // Mark the current node as visited and part of recursion stack
        if (recStack[i]) return true;
        if (visited[i]) return false;

        visited[i] = true;
        recStack[i] = true;

        List<Integer> children = adj[i];

        for (int j = 0; j < children.size(); j++) {
            if (isCyclicUtil(children.get(j), visited, recStack))
                return true;
        }

        recStack[i] = false;
        return false;
    }

    public static void main(String[] args) {
        DetectCycleInDirectedGraph graph = new DetectCycleInDirectedGraph();
        int[][] arr = {{1, 2}};
        int[][] arr2 = {{1, 2}, {2, 3}, {3, 1}};
        System.out.println(graph.detectCycleInDirectedGraph(2, arr));
        System.out.println(graph.detectCycleInDirectedGraph(3, arr2));
    }
}
