/*
 * *
 *  * Snake And Ladder.java
 *  * Created by Rafsan Ahmad on 8/14/22, 12:11 AM
 *  * Copyright (c) 2023 . All rights reserved.
 *
 */

package javaclasses.Graph.BFS;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class SnakeAndLadder {
    //https://www.interviewbit.com/problems/snake-ladder-problem/
    /*Problem Description

Rishabh takes out his Snakes and Ladders Game, stares the board and wonders: "If I can always roll the die to whatever
 number I want, what would be the least number of rolls to reach the destination?"

RULES:

The game is played with cubic dice of 6 faces numbered from 1 to A.
Starting from 1 , land on square 100 with the exact roll of the die. If moving the number rolled would place the
player beyond square 100, no move is made.
If a player lands at the base of a ladder, the player must climb the ladder. Ladders go up only.
If a player lands at the mouth of a snake, the player must go down the snake and come out through the tail. Snakes go
down only.

BOARD DESCRIPTION:

The board is always 10 x 10 with squares numbered from 1 to 100.
The board contains N ladders given in a form of 2D matrix A of size N * 2 where (A[i][0], A[i][1]) denotes a ladder
that has its base on square A[i][0] and end at square A[i][1].
The board contains M snakes given in a form of 2D matrix B of size M * 2 where (B[i][0], B[i][1]) denotes a snake that
 has its mouth on square B[i][0] and tail at square B[i][1].


Problem Constraints
1 <= N, M <= 15
1 <= A[i][0], A[i][1], B[i][0], B[i][1] <= 100
A[i][0] < A[i][1] and B[i][0] > B[i][1]

Neither square 1 nor square 100 will be the starting point of a ladder or snake.
A square will have at most one endpoint from either a snake or a ladder.

Input Format
First argument is a 2D matrix A of size N * 2 where (A[i][0], A[i][1]) denotes a ladder that has its base on
square A[i][0] and end at square A[i][1].

Second argument is a 2D matrix B of size M * 2 where (B[i][0], B[i][1]) denotes a snake that has its mouth on
square B[i][0] and tail at square B[i][1].

Output Format
Return the least number of rolls to move from start to finish on a separate line. If there is no solution, return -1.

Example Input
Input 1:

 A = [  [32, 62]
        [42, 68]
        [12, 98]
     ]
 B = [  [95, 13]
        [97, 25]
        [93, 37]
        [79, 27]
        [75, 19]
        [49, 47]
        [67, 17]
Input 2:

 A = [  [8, 52]
        [6, 80]
        [26, 42]
        [2, 72]
     ]
 B = [  [51, 19]
        [39, 11]
        [37, 29]
        [81, 3]
        [59, 5]
        [79, 23]
        [53, 7]
        [43, 33]
        [77, 21]


Example Output
Output 1:

 3
Output 2:

 5

Example Explanation
Explanation 1:

 The player can roll a 5 and a 6 to land at square 12. There is a ladder to square 98. A roll of 2 ends the traverse
  in 3 rolls.

Explanation 2:

 The player first rolls 5 and climbs the ladder to square 80. Three rolls of 6 get to square 98.
 A final roll of 2 lands on the target square in 5 total rolls.*/

    class Edge {
        int src, dest;

        public Edge(int src, int dest) {
            this.src = src;
            this.dest = dest;
        }
    }

    class Node {
        // stores number associated with graph node
        int ver;

        // `min_dist` stores the minimum distance of a node from the starting vertex
        int min_dist;

        public Node(int ver, int min_dist) {
            this.ver = ver;
            this.min_dist = min_dist;
        }
    }

    // A class to represent a graph object
    class Graph {
        // A list of lists to represent an adjacency list
        List<List<Integer>> adjList = null;

        // Constructor
        Graph(List<Edge> edges, int n) {
            adjList = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                adjList.add(new ArrayList<>());
            }

            // add edges to the graph
            for (Edge edge : edges) {
                // Please note that the graph is directed
                adjList.get(edge.src).add(edge.dest);
            }
        }
    }

    //A -> Ladder array, B -> Snake array
    public int snakeLadder(int[][] A, int[][] B) {
        // total number of nodes in the graph
        int n = 10 * 10;

        // snakes and ladders are represented using a map.
        Map<Integer, Integer> ladder = new HashMap<>();
        Map<Integer, Integer> snake = new HashMap<>();

        for (int i = 0; i < A.length; i++) {
            ladder.put(A[i][0], A[i][1]);
        }

        for (int i = 0; i < B.length; i++) {
            snake.put(B[i][1], B[i][0]);
        }

        // find all edges involved and store them in a list
        List<Edge> edges = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 1; j <= 6 && i + j <= n; j++) {
                int src = i;

                // update destination if there is any ladder
                // or snake from the current position.
                int dest;

                int _ladder = (ladder.get(i + j) != null) ? ladder.get(i + j) : 0;
                int _snake = (snake.get(i + j) != null) ? snake.get(i + j) : 0;

                if (_ladder != 0 || _snake != 0) {
                    dest = _ladder + _snake;
                } else {
                    dest = i + j;
                }

                // add an edge from src to dest
                edges.add(new Edge(src, dest));
            }
        }

        // construct a directed graph
        Graph g = new Graph(edges, n);

        // Find the shortest path between 1 and 100 using BFS
        return BFS(g, 0, n);
    }

    // Perform BFS on graph `g` starting from a given source vertex
    public int BFS(Graph g, int source, int n) {
        // create a queue for doing BFS
        Queue<Node> q = new ArrayDeque<>();

        // to keep track of whether a vertex is discovered or not
        boolean[] discovered = new boolean[n + 1];

        // mark the source vertex as discovered
        discovered[source] = true;

        // assign the minimum distance of the source vertex as 0 and
        // enqueue it
        Node node = new Node(source, 0);
        q.add(node);

        // loop till queue is empty
        while (!q.isEmpty()) {
            // dequeue front node
            node = q.poll();

            // Stop BFS if the last node is reached
            if (node.ver == n) {
                return node.min_dist;
            }

            // do for every adjacent node of the current node
            for (int u : g.adjList.get(node.ver)) {
                if (!discovered[u]) {
                    // mark it as discovered and enqueue it
                    discovered[u] = true;

                    // assign the minimum distance of the current node
                    // one more than the minimum distance of the parent node
                    q.add(new Node(u, node.min_dist + 1));
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        SnakeAndLadder snakeAndLadder = new SnakeAndLadder();

        int[][] A = {{32, 62},
                {42, 68},
                {12, 98}
        };

        int[][] B = {{95, 13},
                {97, 25},
                {93, 37},
                {79, 27},
                {75, 19},
                {49, 47},
                {67, 17}
        };

        System.out.println(snakeAndLadder.snakeLadder(A, B));

        int[][] A2 = {{8, 52},
                {6, 80},
                {26, 42},
                {2, 72}
        };

        int[][] B2 = {{51, 19},
                {39, 11},
                {37, 29},
                {81, 3},
                {59, 5},
                {79, 23},
                {53, 7},
                {43, 33},
                {77, 21}
        };

        System.out.println(snakeAndLadder.snakeLadder(A2, B2));
    }
}
