/*
 * *
 *  * Detect Cycle in a Directed Graph.java
 *  * Created by Rafsan Ahmad on 8/5/22, 11:44 AM
 *  * Copyright (c) 2022 . All rights reserved.
 *
 */

package javaclasses.Graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class DetectCycleInGraph {
    /*Given a directed graph, check whether the graph contains a cycle or not. Your function should return true if
    the given graph contains at least one cycle, else return false.

Example: res/diagram1.png
Input: n = 4, e = 6
0 -> 1, 0 -> 2, 1 -> 2, 2 -> 0, 2 -> 3, 3 -> 3
Output: Yes
Explanation:
The diagram clearly shows a cycle 0 -> 2 -> 0

Example: res/diagram2.png
Input:n = 4, e = 4
0 -> 1, 0 -> 2, 1 -> 2, 2 -> 3
Output:No
Explanation:
The diagram clearly shows no cycle

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

    private final int V;
    private final List<List<Integer>> adj;

    public DetectCycleInGraph(int V) {
        this.V = V;
        adj = new ArrayList<>(V);

        for (int i = 0; i < V; i++)
            adj.add(new LinkedList<>());
    }

    private boolean isCyclicUtil(int i, boolean[] visited,
                                 boolean[] recStack) {

        // Mark the current node as visited and part of recursion stack
        if (recStack[i])
            return true;

        if (visited[i])
            return false;

        visited[i] = true;

        recStack[i] = true;
        List<Integer> children = adj.get(i);

        for (Integer c : children)
            if (isCyclicUtil(c, visited, recStack))
                return true;

        recStack[i] = false;

        return false;
    }

    private void addEdge(int source, int dest) {
        adj.get(source).add(dest);
    }

    // Returns true if the graph contains a cycle, else false.
    private boolean isCyclic() {

        // Mark all the vertices as not visited and not part of recursion stack
        boolean[] visited = new boolean[V];
        boolean[] recStack = new boolean[V];


        // Call the recursive helper function to detect cycle in different DFS trees
        for (int i = 0; i < V; i++)
            if (isCyclicUtil(i, visited, recStack))
                return true;

        return false;
    }

    // Driver code
    public static void main(String[] args) {
        DetectCycleInGraph graph = new DetectCycleInGraph(4);
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 2);
        graph.addEdge(2, 0);
        graph.addEdge(2, 3);
        graph.addEdge(3, 3);

        if (graph.isCyclic())
            System.out.println("Graph contains cycle");
        else
            System.out.println("Graph doesn't " + "contain cycle");
    }
}
