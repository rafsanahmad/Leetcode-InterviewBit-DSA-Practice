/*
 * *
 *  * Longest Path From Node.java
 *  * Created by Rafsan Ahmad on 3/25/24, 6:25 PM
 *  * Copyright (c) 2024 . All rights reserved.
 *  *
 *
 */

package javaclasses.Graph.DFS;

import java.util.ArrayList;
import java.util.Stack;

public class LongestPathFromNode {
    // Graph is represented using adjacency list. Every
    // node of adjacency list contains vertex number of
    // the vertex to which edge connects. It also
    // contains weight of the edge
    static class AdjListNode {
        int v;
        int weight;

        AdjListNode(int v, int w) {
            this.v = v;
            weight = w;
        }

        int getV() {
            return v;
        }

        int getWeight() {
            return weight;
        }
    }

    // Class to represent a graph using adjacency list
    // representation
    static class Graph {
        int V; // No. of vertices'

        // Pointer to an array containing adjacency lists
        ArrayList<ArrayList<AdjListNode>> adj;

        Graph(int V) {
            this.V = V;
            adj = new ArrayList<ArrayList<AdjListNode>>(V);

            for (int i = 0; i < V; i++) {
                adj.add(new ArrayList<AdjListNode>());
            }
        }

        void addEdge(int u, int v, int weight) {
            AdjListNode node = new AdjListNode(v, weight);
            adj.get(u).add(node); // Add v to u's list
        }

        // A recursive function used by longestPath. See below
        // link for details
        void topologicalSortUtil(int v, boolean[] visited, Stack<Integer> stack) {
            // Mark the current node as visited
            visited[v] = true;

            // Recur for all the vertices adjacent to this vertex
            for (int i = 0; i < adj.get(v).size(); i++) {
                AdjListNode node = adj.get(v).get(i);
                if (!visited[node.getV()])
                    topologicalSortUtil(node.getV(), visited, stack);
            }

            // Push current vertex to stack which stores topological sort
            stack.push(v);
        }

        // The function to find longest distances from a given vertex.
        // It uses recursive topologicalSortUtil() to get topological sorting.
        void longestPath(int s) {
            Stack<Integer> stack = new Stack<Integer>();
            int[] dist = new int[V];

            // Mark all the vertices as not visited
            boolean[] visited = new boolean[V];
            for (int i = 0; i < V; i++)
                visited[i] = false;

            // Call the recursive helper function to store Topological
            // Sort starting from all vertices one by one
            for (int i = 0; i < V; i++)
                if (!visited[i])
                    topologicalSortUtil(i, visited, stack);

            // Initialize distances to all vertices as infinite and
            // distance to source as 0
            for (int i = 0; i < V; i++)
                dist[i] = Integer.MIN_VALUE;

            dist[s] = 0;

            // Process vertices in topological order
            while (!stack.isEmpty()) {

                // Get the next vertex from topological order
                int u = stack.peek();
                stack.pop();

                // Update distances of all adjacent vertices ;
                if (dist[u] != Integer.MIN_VALUE) {
                    for (int i = 0; i < adj.get(u).size(); i++) {
                        AdjListNode node = adj.get(u).get(i);
                        if (dist[node.getV()] < dist[u] + node.getWeight())
                            dist[node.getV()] = dist[u] + node.getWeight();
                    }
                }
            }

            // Print the calculated longest distances
            for (int i = 0; i < V; i++)
                if (dist[i] == Integer.MIN_VALUE)
                    System.out.print("INF ");
                else
                    System.out.print(dist[i] + " ");
        }
    }

    public static void main(String[] args) {
        // Create a graph given in the above diagram.
        // Here vertex numbers are 0, 1, 2, 3, 4, 5 with
        // following mappings:
        // 0=r, 1=s, 2=t, 3=x, 4=y, 5=z
        Graph g = new Graph(5);
        g.addEdge(0, 1, 5);
        g.addEdge(0, 2, 3);
        g.addEdge(1, 4, 4);
        g.addEdge(1, 3, 1);
        g.addEdge(2, 3, 5);
        g.addEdge(3, 2, 4);
        //g.addEdge(2, 3, 7);
        //g.addEdge(3, 5, 1);
        //g.addEdge(3, 4, -1);
        //g.addEdge(4, 5, -2);

        int s = 1;
        System.out.print("Following are longest distances from source vertex " + s + " \n");
        g.longestPath(s);

    }
}
