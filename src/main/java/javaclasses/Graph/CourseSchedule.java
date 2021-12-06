/*
 * * Course Schedule.java
 *  * Created by Rafsan Ahmad on 10/25/21, 9:49 PM
 *  * Copyright (c) 2021 . All rights reserved.
 *
 */

package javaclasses.Graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CourseSchedule {
    //Leetcode 207
    /*There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1.
    You are given an array prerequisites where prerequisites[i] = [ai, bi] indicates that you must take course
    bi first if you want to take course ai.

For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.
Return true if you can finish all courses. Otherwise, return false.

Example 1:
Input: numCourses = 2, prerequisites = [[1,0]]
Output: true
Explanation: There are a total of 2 courses to take.
To take course 1 you should have finished course 0. So it is possible.

Example 2:
Input: numCourses = 2, prerequisites = [[1,0],[0,1]]
Output: false
Explanation: There are a total of 2 courses to take.
To take course 1 you should have finished course 0, and to take course 0 you should also have finished course 1.
So it is impossible.*/

    //Using graph cycle detection
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        if (numCourses == 0 || prerequisites.length == 0) {
            return true;
        }
        HashMap<Integer, List<Integer>> adj = new HashMap<>();
        for (int i = 0; i < numCourses; i++) {
            adj.put(i, new ArrayList<>());
        }
        //Add edges
        for (int[] prerequisite : prerequisites) {
            adj.get(prerequisite[1]).add(prerequisite[0]); //directed graph one way
        }

        //create a visited array where 0=unvisited, -1=visiting, 1=visited
        int[] visited = new int[numCourses];
        for (int i = 0; i < numCourses; i++) {
            if (!dfs(adj, visited, i)) {
                return false;
            }
        }
        //No cycle found
        return true;
    }

    public boolean dfs(HashMap<Integer, List<Integer>> adj, int[] visited, int index) {
        //If we ran into a node that we saw earlier on this dfs - graph contain a cycle
        if (visited[index] == -1) {
            return false;
        }
        //if we ran into a node that we saw in a separate earlier dfs - it's okay
        if (visited[index] == 1) {
            return true;
        }
        //Mark the current node as currently visiting
        visited[index] = -1;

        if (adj.containsKey(index)) {
            //Get all neighbors
            for (int j : adj.get(index)) {
                //dfs through neighbors - if cycle found return false
                if (!dfs(adj, visited, j)) {
                    return false;
                }
            }
        }
        //Mark node as visited
        visited[index] = 1;
        return true;
    }

    public static void main(String[] args) {
        int[][] array = {{0, 1}, {1, 2}, {3, 2}, {4, 3}, {2, 4}};
        CourseSchedule schedule = new CourseSchedule();
        System.out.println(schedule.canFinish(5, array));
    }
}
