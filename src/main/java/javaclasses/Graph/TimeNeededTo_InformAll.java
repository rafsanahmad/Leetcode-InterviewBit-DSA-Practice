/*
 * *
 *  * TimeNeededTo_InformAll.java
 *  * Created by Rafsan Ahmad on 6/27/23, 5:08 PM
 *  * Copyright (c) 2023 . All rights reserved.
 *  *
 *
 */

package javaclasses.Graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class TimeNeededTo_InformAll {
    //https://leetcode.com/problems/time-needed-to-inform-all-employees/
    /*A company has n employees with a unique ID for each employee from 0 to n - 1. The head of
    the company is the one with headID.

Each employee has one direct manager given in the manager array where manager[i] is the direct
manager of the i-th employee, manager[headID] = -1. Also, it is guaranteed that the subordination
relationships have a tree structure.

The head of the company wants to inform all the company employees of an urgent piece of news. He
will inform his direct subordinates, and they will inform their subordinates, and so on until all
employees know about the urgent news.

The i-th employee needs informTime[i] minutes to inform all of his direct subordinates
(i.e., After informTime[i] minutes, all his direct subordinates can start spreading the news).

Return the number of minutes needed to inform all the employees about the urgent news.



Example 1:

Input: n = 1, headID = 0, manager = [-1], informTime = [0]
Output: 0
Explanation: The head of the company is the only employee in the company.

Example 2:
                2
         /  /   /  \   \
        0   1   3   4   5

Input: n = 6, headID = 2, manager = [2,2,-1,2,2,2], informTime = [0,0,1,0,0,0]
Output: 1
Explanation: The head of the company with id = 2 is the direct manager of all the employees
 in the company and needs 1 minute to inform them all.
The tree structure of the employees in the company is shown.


Constraints:

1 <= n <= 10^5
0 <= headID < n
manager.length == n
0 <= manager[i] < n
manager[headID] == -1
informTime.length == n
0 <= informTime[i] <= 1000
informTime[i] == 0 if employee i has no subordinates.
It is guaranteed that all the employees can be informed.*/

    //Using BFS
    public int numOfMinutesUsingBFS(int n, int headID, int[] manager, int[] informTime) {
        int result = 0;
        HashMap<Integer, List<Integer>> map = new HashMap<>();

        for (int i = 0; i < manager.length; i++) {
            map.put(i, new ArrayList<>());
        }

        for (int i = 0; i < manager.length; i++) {
            if (manager[i] != -1) {
                map.get(manager[i]).add(i);
            }
        }

        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{headID, 0});

        while (!queue.isEmpty()) {
            int[] q = queue.poll();
            int currentID = q[0];
            int currentTime = q[1];
            result = Math.max(result, currentTime);

            for (int id : map.get(currentID)) {
                queue.add(new int[]{id,
                        currentTime + informTime[currentID]});
            }
        }

        return result;
    }

    //Using DFS
    public int numOfMinutesUsingDFS(int n, int headID, int[] manager, int[] informTime) {
        List<Integer>[] list = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            list[i] = new ArrayList<>();
        }

        for (int i = 0; i < n; i++) {
            if (manager[i] != -1) {
                list[manager[i]].add(i);
            }
        }

        return dfs(headID, list, informTime);
    }

    public int dfs(int id, List<Integer>[] adj, int[] informTime) {
        int ans = 0;
        for (int v : adj[id]) {
            ans = Math.max(ans, dfs(v, adj, informTime));
        }

        return ans + informTime[id];
    }

    public static void main(String[] args) {
        TimeNeededTo_InformAll informAll = new TimeNeededTo_InformAll();
        int[] manager = {2, 2, -1, 2, 2, 2};
        int[] informTime = {0, 0, 1, 0, 0, 0};
        System.out.println(informAll.numOfMinutesUsingBFS(6, 2, manager, informTime));
        System.out.println(informAll.numOfMinutesUsingBFS(6, 2, manager, informTime));
    }
}