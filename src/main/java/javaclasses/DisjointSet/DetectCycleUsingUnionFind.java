/*
 * *
 *  * Detect Cycle Using Union Find.java
 *  * Created by Rafsan Ahmad on 1/7/23, 1:35 PM
 *  * Copyright (c) 2023 . All rights reserved.
 *
 */

package javaclasses.DisjointSet;

import java.util.ArrayList;

public class DetectCycleUsingUnionFind {
    /*The Union-Find algorithm (or disjoint-set data structure) is an algorithmic technique that keeps track of a set of
    elements partitioned or split into a number of disjoint (non-overlapping) subsets.

There's a set of basic abstractions to understand first. We'll need a set of objects-- when dealing with graphs, these
will be vertices/nodes-- but they can be any object.

The Union-Find algorithm performs two operations on its sets of object elements. They are the Find and Union methods,
and here's what they do:

Find: This is used to determine which subset a particular element is in. This can be used for determining if two elements
are in the same subset. It answers the question of whether there is a path connecting one object to another.

Union: Used to join two subsets into a single subset. This models the actual connection/path between the two objects.*/


    int MAX_VERTEX = 101;

    // Arr to represent parent of index i
    int[] Arr = new int[MAX_VERTEX];

    // Size to represent the number of nodes in subgraph rooted at index i
    int[] size = new int[MAX_VERTEX];

    // set parent of every node to itself and size of node to one
    void initialize(int n) {
        for (int i = 0; i <= n; i++) {
            Arr[i] = i;
            size[i] = 1;
        }
    }

    // Each time we follow a path, find function compresses it further until the path length is greater than or equal to 1.
    int find(int i) {
        // while we reach a node whose parent is equal to itself
        while (Arr[i] != i) {
            Arr[i] = Arr[Arr[i]]; // Skip one level
            i = Arr[i]; // Move to the new level
        }
        return i;
    }

    // A function that does union of two nodes x and y
    // where xr is root node of x and yr is root node of y
    void union(int xr, int yr) {
        // Make yr parent of xr
        if (size[xr] < size[yr]) {
            Arr[xr] = Arr[yr];
            size[yr] += size[xr];
        } else {
            // Make xr parent of yr
            Arr[yr] = Arr[xr];
            size[xr] += size[yr];
        }
    }

    // The main function to check whether a given graph contains cycle or not
    int isCycle(ArrayList<Integer>[] adj, int V) {
        // Iterate through all edges of graph, find nodes connecting them.
        // If root nodes of both are same, then there is cycle in graph.
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < adj[i].size(); j++) {
                int x = find(i); // find root of i

                // find root of adj[i][j]
                int y = find(adj[i].get(j));

                if (x == y)
                    return 1; // If same parent
                union(x, y); // Make them connect
            }
        }
        return 0;
    }


    public static void main(String[] args) {
        int V = 3;
        DetectCycleUsingUnionFind usingUnionFind = new DetectCycleUsingUnionFind();

        // Initialize the values for array Arr and Size
        usingUnionFind.initialize(V);

    /* Let us create following graph
         0
        |  \
        |   \
        1-----2 */

        // Adjacency list for graph
        ArrayList<Integer>[] adj = new ArrayList[V];
        for (int i = 0; i < V; i++)
            adj[i] = new ArrayList<Integer>();

        adj[0].add(1);
        adj[0].add(2);
        adj[1].add(2);

        // call is_cycle to check if it contains cycle
        if (usingUnionFind.isCycle(adj, V) == 1)
            System.out.print("Graph contains Cycle.\n");
        else
            System.out.print("Graph does not contain Cycle.\n");
    }
}
