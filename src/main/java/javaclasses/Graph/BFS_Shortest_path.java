/*
 * *
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
