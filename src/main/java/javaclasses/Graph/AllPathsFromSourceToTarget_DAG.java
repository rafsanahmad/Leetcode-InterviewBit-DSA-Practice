/*
 * *
 *  * All Paths From Source To Target.java
 *  * Created by Rafsan Ahmad on 12/31/22, 5:31 PM
 *  * Copyright (c) 2022 . All rights reserved.
 *
 */

package javaclasses.Graph;

import java.util.ArrayList;
import java.util.List;

public class AllPathsFromSourceToTarget_DAG {
    //https://leetcode.com/problems/all-paths-from-source-to-target/description/
    /*Given a directed acyclic graph (DAG) of n nodes labeled from 0 to n - 1, find all possible paths from node 0 to
    node n - 1 and return them in any order.

The graph is given as follows: graph[i] is a list of all nodes you can visit from node i (i.e., there is a directed edge
from node i to node graph[i][j]).


Example 1:

    0 -----→ 1
    ↓        ↓
    2 ------→ 3

Input: graph = [[1,2],[3],[3],[]]
Output: [[0,1,3],[0,2,3]]
Explanation: There are two paths: 0 -> 1 -> 3 and 0 -> 2 -> 3.


Example 2:
Input: graph = [[4,3,1],[3,2,4],[3],[4],[]]
Output: [[0,4],[0,3,4],[0,1,3,4],[0,1,2,3,4],[0,1,4]]
*/
    List<List<Integer>> paths = new ArrayList<>();

    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        //Perform DFS
        List<Integer> currPath = new ArrayList<>();
        currPath.add(0);
        dfsAllPathFromSource(graph, 0, currPath);

        return paths;
    }

    public void dfsAllPathFromSource(int[][] graph, int index, List<Integer> currPath) {
        if (index == graph.length - 1) {
            paths.add(new ArrayList<>(currPath));
            return;
        }

        for (int i : graph[index]) {
            currPath.add(i);
            dfsAllPathFromSource(graph, i, currPath);
            currPath.remove(currPath.size() - 1);
        }
    }

    public static void main(String[] args) {
        AllPathsFromSourceToTarget_DAG dag = new AllPathsFromSourceToTarget_DAG();
        int[][] graph = {{4, 3, 1}, {3, 2, 4}, {3}, {4}, {}};
        System.out.println(dag.allPathsSourceTarget(graph));
    }
}
