/*
 * *
 *  * Bellman Ford Algorithm.java
 *  * Created by Rafsan Ahmad on 3/24/24, 11:20 PM
 *  * Copyright (c) 2024 . All rights reserved.
 *  *
 *
 */

package javaclasses.Algorithm;

import java.util.Arrays;

public class BellmanFordAlgorithm {

    /*The Bellman-Ford algorithm is a single-source shortest-path algorithm that can handle
    negative weight edges. It works by iteratively relaxing all edges in the graph, reducing
    the estimated distance from the source vertex to all other vertices until the actual shortest
    path is found.

Here are the steps involved in the Bellman-Ford algorithm:

Step – 1 Initialize the distance to the source vertex as 0, and the distance to all other vertices
as infinity.

Step – 2 Relax all edges in the graph |V| – 1 times, where |V| is the number of vertices in the graph.
For each edge (u, v) with weight w, check if the distance from the source vertex to v can be reduced
by going through u. If so, update the distance to v to the new, shorter distance.

Step – 3 Check for negative weight cycles. If there is a negative weight cycle in the graph,
the algorithm will never converge and will keep reducing the distance to some vertices with each
iteration. To detect such cycles, repeat step 2 one more time. If any distance is updated in this
extra iteration, there must be a negative weight cycle in the graph.

Step – 4 If there is no negative weight cycle, the shortest distance to each vertex from the source
vertex has been found.*/
    static class Graph {
        class Edge {
            int src, dest, weight;

            Edge() {
                src = dest = weight = 0;
            }
        }

        ;
        int V, E;
        Edge[] edge;

        Graph(int v, int e) {
            V = v;
            E = e;
            edge = new Edge[e];
            for (int i = 0; i < e; ++i)
                edge[i] = new Edge();
        }

        void BellmanFord(Graph graph, int src) {
            int V = graph.V, E = graph.E;
            int[] dist = new int[V];
            Arrays.fill(dist, Integer.MAX_VALUE);
            dist[src] = 0;
            for (int i = 1; i < V; ++i) {
                for (int j = 0; j < E; ++j) {
                    int u = graph.edge[j].src;
                    int v = graph.edge[j].dest;
                    int weight = graph.edge[j].weight;
                    if (dist[u] != Integer.MAX_VALUE
                            && dist[u] + weight < dist[v])
                        dist[v] = dist[u] + weight;
                }
            }
            for (int j = 0; j < E; ++j) {
                int u = graph.edge[j].src;
                int v = graph.edge[j].dest;
                int weight = graph.edge[j].weight;
                if (dist[u] != Integer.MAX_VALUE
                        && dist[u] + weight < dist[v]) {
                    System.out.println(
                            "Graph contains negative weight cycle");
                    return;
                }
            }
            printArr(dist, V);
        }

        void printArr(int dist[], int V) {
            System.out.println("Vertex Distance from Source");
            for (int i = 0; i < V; ++i)
                System.out.println(i + "\t\t" + dist[i]);
        }

        public static void main(String[] args) {
            int V = 5;
            int E = 8;
            Graph graph = new Graph(V, E);
            // adding edge 0-1 (or A-B in above dry run)
            graph.edge[0].src = 0;
            graph.edge[0].dest = 1;
            graph.edge[0].weight = -1;
            // adding edge 0-2 (or A-C in above dry run)
            graph.edge[1].src = 0;
            graph.edge[1].dest = 2;
            graph.edge[1].weight = 4;
            // adding edge 1-2 (or B-C in above dry run)
            graph.edge[2].src = 1;
            graph.edge[2].dest = 2;
            graph.edge[2].weight = 3;
            // adding edge 1-3 (or B-D in above dry run)
            graph.edge[3].src = 1;
            graph.edge[3].dest = 3;
            graph.edge[3].weight = 2;
            // adding edge 1-4 (or B-E in above dry run)
            graph.edge[4].src = 1;
            graph.edge[4].dest = 4;
            graph.edge[4].weight = 2;
            // adding edge 3-2 (or D-C in above dry run)
            graph.edge[5].src = 3;
            graph.edge[5].dest = 2;
            graph.edge[5].weight = 5;
            // adding edge 3-1 (or D-B in above dry run)
            graph.edge[6].src = 3;
            graph.edge[6].dest = 1;
            graph.edge[6].weight = 1;
            // adding edge 4-3 (or E-D in above dry run)
            graph.edge[7].src = 4;
            graph.edge[7].dest = 3;
            graph.edge[7].weight = -3;
            graph.BellmanFord(graph, 0);
        }
    }
}
