/*
 * * BFS Shortest path.java
 *  * Created by Rafsan Ahmad on 10/25/21, 9:49 PM
 *  * Copyright (c) 2021 . All rights reserved.
 *
 */

package javaclasses.Graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

//https://www.hackerrank.com/challenges/ctci-bfs-shortest-reach/problem?h_l=interview&playlist_slugs%5B%5D%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D%5B%5D=graphs
public class BFS_Shortest_path {
    /*Consider an undirected graph consisting of n nodes where each node is labeled from 1 to n and the edge between
    any two nodes is always of length 6. We define node s to be the starting position for a BFS.
    Given a graph, determine the distances from the start node to each of its descendants and return the list in node
    number order, ascending. If a node is disconnected, it's distance should be -1.

For example, there are n=6 nodes in the graph with a starting node s. The list of edges = [[1,2],[1,5],[2,3],[3,4]]
, and each has a weight of 6.

           1          6
         /   \
        5     2
              \
               3
                \
                 4

  Starting from node 1 and creating a list of distances, for nodes 2 through 6 we have [6,12,18,6,-1]


  Function Description

Define a Graph class with the required methods to return a list of distances.

Input Format

The first line contains an integer, q , the number of queries.

Each of the following q sets of lines is as follows:

The first line contains two space-separated integers, n and m, the number of nodes and the number of edges.
Each of the next m lines contains two space-separated integers, u and v, describing an edge connecting node u to node v.
The last line contains a single integer, s , the index of the starting node.
Constraints

Output Format

For each of the q queries, print a single line of n-1 space-separated integers denoting the shortest distances to each
of the n-1 other nodes from starting position s.
These distances should be listed sequentially by node number (i.e. 1,2,..n-1), but should not include node s.
If some node is unreachable from s, print -1 as the distance to that node.

Sample Input

2
4 2
1 2
1 3
1
3 1
2 3
2
Sample Output

6 6 -1
-1 6
*/
    public static class Graph {

        private ArrayList<Integer>[] adj;
        private boolean marked[];
        private int edgeTo[];
        private int size;
        private int startPoint;

        public Graph(int size) {
            adj = new ArrayList[size];
            for (int v = 0; v < size; v++) {
                adj[v] = new ArrayList<>(size);
            }
            this.size = size;
            marked = new boolean[size];
            edgeTo = new int[size];
        }

        public void addEdge(int first, int second) {
            adj[first].add(second);
            adj[second].add(first);
        }

        private void bfs(int start) {
            Queue<Integer> q = new LinkedList<Integer>();
            q.add(start);
            while (!q.isEmpty()) {
                int v = q.poll();
                marked[v] = true;
                for (int w : adj[v]) {
                    if (!marked[w]) {
                        marked[w] = true;
                        edgeTo[w] = v;
                        q.add(w);
                    }
                }
            }
        }

        public Iterable<Integer> pathTo(int v) {
            if (!hasPathTo(v)) return null;
            Stack<Integer> path = new Stack<>();
            for (int x = v; x != startPoint; x = edgeTo[x]) {
                path.push(x);
            }
            path.push(startPoint);
            return path;
        }

        public boolean hasPathTo(int v) {
            return marked[v];
        }

        public int[] shortestReach(int startId) {
            int[] result = new int[size];
            startPoint = startId;
            bfs(startId);
            for (int v = 0; v < size; v++) {
                if (startId != v) {
                    if (hasPathTo(v)) {
                        Stack<Integer> st = (Stack<Integer>) pathTo(v);
                        int sum = 0;

                        for (int x = 1; x < st.size(); x++) {
                            sum += 6;
                        }
                        result[v] = sum;
                    } else {
                        result[v] = -1;
                    }
                }
            }
            return result;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int queries = scanner.nextInt();

        for (int t = 0; t < queries; t++) {
            //Create a graph of size n where each edge weight is 6
            Graph graph = new Graph(scanner.nextInt());
            int m = scanner.nextInt();

            //read and set edges
            for (int i = 0; i < m; i++) {
                int u = scanner.nextInt() - 1;
                int v = scanner.nextInt() - 1;

                //add edge to the graph
                graph.addEdge(u, v);
            }

            //Find shortest reach from node s
            int startId = scanner.nextInt() - 1;
            int[] distances = graph.shortestReach(startId);

            for (int i = 0; i < distances.length; i++) {
                if (i != startId) {
                    System.out.print(distances[i]);
                    System.out.print(" ");
                }
            }
            System.out.println();
        }

        scanner.close();
    }

}
