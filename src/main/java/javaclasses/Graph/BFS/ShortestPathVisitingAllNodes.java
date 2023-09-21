/*
 * *
 *  * ShortestPathVisitingAllNodes.java
 *  * Created by Rafsan Ahmad on 9/21/23, 10:25 PM
 *  * Copyright (c) 2023 . All rights reserved.
 *  *
 *
 */

package javaclasses.Graph.BFS;

import java.util.LinkedList;
import java.util.Queue;

public class ShortestPathVisitingAllNodes {
    /*You have an undirected, connected graph of n nodes labeled from 0 to n - 1.
    You are given an array graph where graph[i] is a list of all the nodes connected with node
    i by an edge.

Return the length of the shortest path that visits every node. You may start and stop at any node,
 you may revisit nodes multiple times, and you may reuse edges.

Example 1:
Input: graph = [[1,2,3],[0],[0],[0]]
Output: 4
Explanation: One possible path is [1,0,2,0,3]

Example 2:
Input: graph = [[1],[0,2,4],[1,3,4],[2],[1,2]]
Output: 4
Explanation: One possible path is [0,1,4,2,3]

Constraints:
n == graph.length
1 <= n <= 12
0 <= graph[i].length < n
graph[i] does not contain i.
If graph[a] contains b, then graph[b] contains a.
The input graph is always connected.

Method: bfs time complexity O(n * 2 ^ n)
Since we can visit a node multiple times, we need to have 2 variables to record breaking
condition for a node:
cur_node
state: for current node, how many nodes were visited.
for a single node, if that state is visited means it is a duplicate condition and we can continue.

*/

    public int shortestPathLength(int[][] graph) {
        int len = graph.length;
        int expect = (1 << len) - 1; //This set all bits to 1.
        Queue<int[]> q = new LinkedList<>();    // int[] saves current node, visited states
        for (int i = 0; i < len; i++)
            q.offer(new int[]{i, 1 << i});  // We could start from any points.

        // if for current node and state, we visit it again will be dulplicate, we can continue.
        boolean[][] visited = new boolean[len][1 << len];
        int step = -1;
        while (!q.isEmpty()) {
            int size = q.size();
            ++step;
            for (int i = 0; i < size; i++) {
                int[] pair = q.poll();
                int node = pair[0];
                int state = pair[1];
                // We've visited all of the nodes
                if (state == expect) return step;
                if (visited[node][state]) continue;
                visited[node][state] = true;
                for (int next : graph[node]) {
                    q.offer(new int[]{next, state | (1 << next)});
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        ShortestPathVisitingAllNodes allNodes = new ShortestPathVisitingAllNodes();
        int[][] graph = {{1, 2, 3}, {0}, {0}, {0}};

        int[][] graph2 = {{1}, {0, 2, 4}, {1, 3, 4}, {2}, {1, 2}};
        System.out.println(allNodes.shortestPathLength(graph));
        System.out.println(allNodes.shortestPathLength(graph2));
    }
}
