/*
 * *
 *  * Water Supply Route.java
 *  * Created by Rafsan Ahmad on 7/12/22, 11:51 PM
 *  * Copyright (c) 2023 . All rights reserved.
 *
 */

package javaclasses.Graph.BFS;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class WaterSupplyRoute {
    /*Given N cities that are connected using N-1 roads. Between Cities [i, i+1], there exists an
    edge for all i from 1 to N-1.
The task is to set up a connection for water supply. Set the water supply in one city and water gets transported from
it to other cities using road transport. Certain cities are blocked which means that water cannot pass through that
particular city. Determine the maximum number of cities to which water can be supplied.

Input format:
The first line contains an integer >strong>N denoting the number of cities.
The next N-1 lines contain two space-separated integers u v denoting a road between
city u and v.
The next line contains N space-separated integers where it is 1 if the ith city is
blocked, else it is 0.

Examples:
Input :
4
1 2
2 3
3 4
0 1 1 0
Output :
2

Explanation : If city 1 is chosen, then water is supplied from
city 1 to 2. If city 4 is chosen, water is supplied from city 4 to 3
hence maximum of 2 cities can be supplied with water.

Input :
7
1 2
2 3
3 4
4 5
5 6
6 7
0 1 1 0 0 0 0
Output :
5
Explanation : If city 1 is chosen than water is supplied from
city 1 to 2 or if city 4 is chosen water is supplied from city 4 to
3, 5, 6 and 7 hence maximum of 5 cities are supplied with water.*/

    /*For this problem, a BFS based solution is discussed.
We run a breadth-first search on each city and check for two things: The city is not blocked and 
the city is not visited.
If both these conditions return true then we run a breadth-first search from that city and count
the number of cities up to which water can be supplied.
This solution can also be achieved using a depth-first search.
Below is the implementation of the above approach:*/

    // Function to perform BFS
    static int bfsUtil(int[] v, boolean[] vis, ArrayList<Integer>[] adj, int src) {
        // Mark current source visited
        vis[src] = true;

        // Queue for BFS
        Queue<Integer> q = new LinkedList<>();

        // Push src to queue
        q.add(src);

        int count = 0;
        while (!q.isEmpty()) {
            int p = q.peek();

            for (int i = 0; i < adj[p].size(); i++) {

                // When the adjacent city not visited and not blocked, push city in the queue.
                if (!vis[adj[p].get(i)] && v[adj[p].get(i)] == 0) {
                    count++;
                    vis[adj[p].get(i)] = true;
                    q.add(adj[p].get(i));
                }

                // When the adjacent city is not visited but blocked so the blocked city is not pushed in queue
                else if (!vis[adj[p].get(i)] && v[adj[p].get(i)] == 1) {
                    count++;
                }
            }
            q.remove();
        }
        return count + 1;
    }

    // Utility function to perform BFS
    static int bfs(int N, int[] v, ArrayList<Integer>[] adj) {
        boolean[] vis = new boolean[N + 1];
        int max = 1, res;

        // Marking visited array false
        for (int i = 1; i <= N; i++)
            vis[i] = false;

        // Check for each and every city
        for (int i = 1; i <= N; i++) {

            // Checks that city is not blocked and not visited.
            if (v[i] == 0 && !vis[i]) {
                res = bfsUtil(v, vis, adj, i);
                if (res > max) {
                    max = res;
                }
            }
        }
        return max;
    }

    public static void main(String[] args) {

        // Denotes the number of cities
        int N = 4;

        @SuppressWarnings("unchecked")
        ArrayList<Integer>[] adj = new ArrayList[N + 1];
        for (int i = 0; i < adj.length; i++)
            adj[i] = new ArrayList<Integer>();

        int[] v = new int[N + 1];

        // Adjacency list denoting road between city u and v
        adj[1].add(2);
        adj[2].add(1);
        adj[2].add(3);
        adj[3].add(2);
        adj[3].add(4);
        adj[4].add(3);

        // Array for storing whether ith city is blocked or not
        v[1] = 0;
        v[2] = 1;
        v[3] = 1;
        v[4] = 0;

        System.out.print(bfs(N, v, adj));
    }
}
