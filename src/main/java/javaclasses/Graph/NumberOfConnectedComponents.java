package javaclasses.Graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class NumberOfConnectedComponents {
    /*Given an undirected graph, print the total number of connected component.
    For example consider the following graph.
    res/image/SCCUndirected.png*/

    public int countComponents(int n, int[][] edges) {
        //Setup the adjacency list
        HashMap<Integer, List<Integer>> adj = new HashMap<>();
        for (int i = 0; i < n; i++) {
            adj.put(i, new ArrayList<>());
        }
        //Populate the adjacency list with all nodes neighbors
        //Graph is undirected - so add both edges
        for (int i = 0; i < edges.length; i++) {
            if (edges[i].length == 2) {
                adj.get(edges[i][0]).add(edges[i][1]);
                adj.get(edges[i][1]).add(edges[i][0]);
            }
        }

        //created a visited array where false = unvisited and true = visited
        boolean[] visited = new boolean[n];

        //solution variable to count # of connected components
        int count = 0;

        //Dfs each node
        for (int i = 0; i < n; i++) {
            if (visited[i] == false) {
                count++;
                //visit all nodes connected to this one
                dfs(adj, i, visited);
            }
        }

        return count;
    }

    public void dfs(HashMap<Integer, List<Integer>> adj, int index, boolean[] visited) {
        //mark the current node as visited
        visited[index] = true;
        //get all neighbors
        for (Integer i : adj.get(index)) {
            //if we haven't visited this neighbor before, DFS into it
            if (visited[i] == false) {
                dfs(adj, i, visited);
            }
        }
    }

    public static void main(String[] args) {
        NumberOfConnectedComponents components = new NumberOfConnectedComponents();
        int[][] array = {{0, 1}, {1, 2}, {3, 4}, {5}};
        System.out.println(components.countComponents(6, array));
    }
}
