package javaclasses.Tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class MinimumHeightTrees {
    //Leetcode 310
    //res/mht.jpeg
    /*A tree is an undirected graph in which any two vertices are connected by exactly one path.
    In other words, any connected graph without simple cycles is a tree.

Given a tree of n nodes labelled from 0 to n - 1, and an array of n - 1 edges where edges[i] = [ai, bi]
indicates that there is an undirected edge between the two nodes ai and bi in the tree, you can choose any node of the tree as the root.
When you select a node x as the root, the result tree has height h. Among all possible rooted trees, those with minimum height (i.e. min(h))
 are called minimum height trees (MHTs).

Return a list of all MHTs' root labels. You can return the answer in any order.

The height of a rooted tree is the number of edges on the longest downward path between the root and a leaf.

Example 1:
Input: n = 4, edges = [[1,0],[1,2],[1,3]]
Output: [1]
Explanation: As shown, the height of the tree is 1 when the root is the node with label 1 which is the only MHT.

Example 2:
Input: n = 6, edges = [[3,0],[3,1],[3,2],[3,4],[5,4]]
Output: [3,4]

Example 3:
Input: n = 1, edges = []
Output: [0]

Example 4:
Input: n = 2, edges = [[0,1]]
Output: [0,1]
*/

    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        //if there is only one node - node 0
        if (n == 1) {
            return Collections.singletonList(0);
        }
        //Setup adjacency list
        HashMap<Integer, List<Integer>> adj = new HashMap<>();
        for (int i = 0; i < n; i++) {
            adj.put(i, new ArrayList<>());
        }

        for (int[] array : edges) {
            adj.get(array[0]).add(array[1]);
            adj.get(array[1]).add(array[0]);
        }

        //Keep track of leaves nodes with only one neighbor
        List<Integer> leaves = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            //if the node has one edge, it's a leaf
            if (adj.get(i).size() == 1) {
                leaves.add(i);
            }
        }

        //Keep removing nodes until there is atmost 2 nodes left
        while (n > 2) {
            //update the total number of nodes
            n -= leaves.size();

            //Temporary array to hold new leaves
            List<Integer> newLeaves = new ArrayList<>();
            //Remove each of the current leaves
            for (int i : leaves) {
                //Get this leaf's one and only neighbor
                int j = adj.get(i).iterator().next();
                //Go to leaf's neighbor and remove it's leaf from it's list
                adj.get(j).removeIf(s -> s.equals(i));
                //if the neighbor has only one node left it's a new leaf
                if (adj.get(j).size() == 1) {
                    newLeaves.add(j);
                }
            }
            //Once the current leaves are removed, make the new leaves the current leaves
            leaves = newLeaves;
        }

        return leaves;
    }

    public static void main(String[] args) {
        MinimumHeightTrees mht = new MinimumHeightTrees();
        int[][] array = {{3, 0}, {3, 1}, {3, 2}, {3, 4}, {5, 4}};
        int[][] array2 = {{0, 1}};
        System.out.println(mht.findMinHeightTrees(2, array2));
    }
}
