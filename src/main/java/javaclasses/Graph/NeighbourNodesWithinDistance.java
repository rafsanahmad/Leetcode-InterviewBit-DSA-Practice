/*
 * *
 *  * Created by Rafsan Ahmad on 11/30/21, 10:24 PM
 *  * Copyright (c) 2021 . All rights reserved.
 *
 */

package javaclasses.Graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class NeighbourNodesWithinDistance {
    //Leetcode 863
    //https://www.geeksforgeeks.org/print-all-neigbour-nodes-within-distance-k/
/*Given the root of a binary tree, the value of a target node target, and an integer k,
return an array of the values of all nodes that have a distance k from the target node.

You can return the answer in any order.

Example:
                   2
                /    \
               5      1
            /  |     /  \
           6   2    0    8
             /  \
            7    4

Input: root = [3,5,1,6,2,0,8,null,null,7,4], target = 5, k = 2
Output: [7,4,1]
Explanation: The nodes that are a distance 2 from the target node (with value 5) have values 7, 4, and 1.

Approach:
To print all the nodes that are at distance K or less than K. We can do it by applying dfs variation,
that takes K node from where we have to print the distance until distance K.

dfs(K, node, -1, tree)
Here -1 indicates node parent.
This recursive function basically prints the node and then calls the dfs(K-1, neighbour of node, node, tree).
Base condition is K>0.
*/

    static List<Integer> neighbourNodes = new ArrayList<>();

    public void neighBourNodes(int[][] edges, int numOfNodes, int start, int k) {
        //Setup the adjacency list
        HashMap<Integer, List<Integer>> adj = new HashMap<>();
        for (int i = 0; i < numOfNodes; i++) {
            adj.put(i, new ArrayList<>());
        }
        //Populate the adjacency list with all nodes neighbors
        for (int i = 0; i < edges.length; i++) {
            if (edges[i].length == 2) {
                adj.get(edges[i][0]).add(edges[i][1]);
                adj.get(edges[i][1]).add(edges[i][0]);
            }
        }

        //created a visited array where false = unvisited and true = visited
        boolean[] visited = new boolean[numOfNodes];

        dfs(k, start, -1, adj, visited);
    }

    void dfs(int k, int node,
             int parent, HashMap<Integer, List<Integer>> adj, boolean[] visited) {

        // Base condition
        if (k < 0)
            return;

        // add the node
        if (k == 0)
            neighbourNodes.add(node);

        visited[node] = true;
        //get all neighbors
        for (Integer i : adj.get(node)) {
            if (i != parent && !visited[i]) {
                // Node i becomes the parent
                // of its child node
                dfs(k - 1, i, node, adj, visited);
            }
        }
    }

    public static void main(String[] args) {
        NeighbourNodesWithinDistance nodes = new NeighbourNodesWithinDistance();
        int[][] edges = {{2, 1}, {2, 5}, {5, 6}, {5, 2},
                {1, 0}, {1, 8}, {2, 7}, {2, 4}};
        int startNode = 5;
        nodes.neighBourNodes(edges, 9, startNode, 2);
        System.out.println(neighbourNodes);
    }
}
