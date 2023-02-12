/*
 *  * Topological Sort.java
 *  * Created by Rafsan Ahmad on 10/25/21, 9:49 PM
 *  * Copyright (c) 2021 . All rights reserved.
 *
 */

package javaclasses.Algorithm;

import java.util.ArrayList;
import java.util.Stack;

public class TopologicalSort {
    //res/topological_Sort.png

    /*Topological sorting is a linear ordering of vertices such that for every directed edge ij,
    vertex i comes before j in the ordering.
Topological sorting is only possible for Directed Acyclic Graph (DAG).
Applications:
jobs scheduling from the given dependencies among jobs.
ordering of formula cell evaluation in spreadsheets
ordering of compilation tasks to be performed in make files,
data serialization
resolving symbol dependencies in linkers.*/

    //Time Complexity: O(V+E).
    static class Graph {
        // No. of vertices
        private final int V;

        // Adjacency List as ArrayList of ArrayList's
        private final ArrayList<ArrayList<Integer>> adj;

        // Constructor
        Graph(int v) {
            V = v;
            adj = new ArrayList<>(v);
            for (int i = 0; i < v; ++i)
                adj.add(new ArrayList<Integer>());
        }

        // Function to add an edge into the graph
        void addEdge(int v, int w) {
            adj.get(v).add(w);
        }

        // A recursive function used by topologicalSort
        void topologicalSortUtil(int v, boolean[] visited,
                                 Stack<Integer> stack) {
            // Mark the current node as visited.
            visited[v] = true;
            Integer i;

            // Recur for all the vertices adjacent
            // to this vertex
            for (Integer integer : adj.get(v)) {
                i = integer;
                if (!visited[i])
                    topologicalSortUtil(i, visited, stack);
            }

            // Push current vertex to stack
            // which stores result
            stack.push(v);
        }

        // The function to do Topological Sort.
        // It uses recursive topologicalSortUtil()
        void topologicalSort() {
            Stack<Integer> stack = new Stack<Integer>();

            // Mark all the vertices as not visited
            boolean[] visited = new boolean[V];
            for (int i = 0; i < V; i++)
                visited[i] = false;

            // Call the recursive helper
            // function to store
            // Topological Sort starting
            // from all vertices one by one
            for (int i = 0; i < V; i++)
                if (!visited[i])
                    topologicalSortUtil(i, visited, stack);

            // Print contents of stack
            while (!stack.empty())
                System.out.print(stack.pop() + " ");
        }
    }

    public static void main(String[] args) {
        // Create a graph given in the above diagram
        Graph g = new Graph(6);
        g.addEdge(5, 2);
        g.addEdge(5, 0);
        g.addEdge(4, 0);
        g.addEdge(4, 1);
        g.addEdge(2, 3);
        g.addEdge(3, 1);

        System.out.println("Following is a Topological "
                + "sort of the given graph");
        // Function Call
        g.topologicalSort();
    }
}
