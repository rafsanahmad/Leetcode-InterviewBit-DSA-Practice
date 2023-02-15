/*
 * *
 *  * Dijkstra Algorithm.java
 *  * Created by Rafsan Ahmad on 2/15/23, 7:18 PM
 *  * Copyright (c) 2023 . All rights reserved.
 *
 */

package javaclasses.Algorithm;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

// Representing a node in the graph
class Node implements Comparator<Node> {

    // Member variables of this class
    public int node;
    public int cost;

    public Node() {
    }

    public Node(int node, int cost) {
        this.node = node;
        this.cost = cost;
    }

    @Override
    public int compare(Node node1, Node node2) {
        return Integer.compare(node1.cost, node2.cost);
    }
}

public class DijkstraAlgorithm {
    /*Problem statement

Given a graph with adjacency list representation of the edges between the nodes, the task is to implement Dijkstra’s
Algorithm for single-source shortest path using Priority Queue in Java. Given a graph and a source vertex in the graph,
find the shortest paths from the source to all vertices in the given graph.

Illustration:

Input  : Source = 0
Output :
     Vertex   Distance from Source
        0                0
        1                4
        2                12
        3                19
        4                21
        5                11
        6                9
        7                8
        8                14
*/
    // Member variables of this class
    private final int[] dist;
    private final Set<Integer> settled;
    private final PriorityQueue<Node> pq;
    // Number of vertices
    private final int V;
    List<List<Node>> adj;

    // Constructor of this class
    public DijkstraAlgorithm(int V) {

        // This keyword refers to current object itself
        this.V = V;
        dist = new int[V];
        settled = new HashSet<>();
        pq = new PriorityQueue<>(V, new Node());
    }

    // Dijkstra's Algorithm
    public void dijkstra(List<List<Node>> adj, int src) {
        this.adj = adj;

        for (int i = 0; i < V; i++)
            dist[i] = Integer.MAX_VALUE;

        // Add source node to the priority queue
        pq.add(new Node(src, 0));

        // Distance to the source is 0
        dist[src] = 0;

        while (settled.size() != V) {

            // Terminating condition check when the priority queue is empty, return
            if (pq.isEmpty())
                return;

            // Removing the minimum distance node from the priority queue
            int u = pq.remove().node;

            // Adding the node whose distance is finalized
            if (settled.contains(u))

                // Continue keyword skips execution for following check
                continue;

            // We don't have to call e_Neighbors(u) if u is already present in the settled set.
            settled.add(u);
            e_Neighbours(u);
        }
    }

    // To process all the neighbours of the passed node
    private void e_Neighbours(int u) {

        int edgeDistance = -1;
        int newDistance = -1;

        // All the neighbors of v
        for (int i = 0; i < adj.get(u).size(); i++) {
            Node v = adj.get(u).get(i);

            // If current node hasn't already been processed
            if (!settled.contains(v.node)) {
                edgeDistance = v.cost;
                newDistance = dist[u] + edgeDistance;

                // If new distance is cheaper in cost
                if (newDistance < dist[v.node])
                    dist[v.node] = newDistance;

                // Add the current node to the queue
                pq.add(new Node(v.node, dist[v.node]));
            }
        }
    }

    public static void main(String[] arg) {

        int V = 5;
        int source = 0;

        List<List<Node>> adj = new ArrayList<List<Node>>();

        // Initialize list for every node
        for (int i = 0; i < V; i++) {
            List<Node> item = new ArrayList<Node>();
            adj.add(item);
        }

        // Inputs for the graph
        adj.get(0).add(new Node(1, 9));
        adj.get(0).add(new Node(2, 6));
        adj.get(0).add(new Node(3, 5));
        adj.get(0).add(new Node(4, 3));

        adj.get(2).add(new Node(1, 2));
        adj.get(2).add(new Node(3, 4));

        // Calculating the single source shortest path
        DijkstraAlgorithm algorithm = new DijkstraAlgorithm(V);
        algorithm.dijkstra(adj, source);

        // Printing the shortest path to all the nodes from the source node
        System.out.println("The shorted path from node :");

        for (int i = 0; i < algorithm.dist.length; i++)
            System.out.println(source + " to " + i + " is " + algorithm.dist[i]);
    }
}

