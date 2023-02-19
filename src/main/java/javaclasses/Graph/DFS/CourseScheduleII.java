/*
 * *
 *  * Course Schedule II.java
 *  * Created by Rafsan Ahmad on 12/6/21, 4:25 PM
 *  * Copyright (c) 2023 . All rights reserved.
 *
 */

package javaclasses.Graph.DFS;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class CourseScheduleII {
    //Leetcode 210
    /*There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1.
    You are given an array prerequisites where prerequisites[i] = [ai, bi] indicates that you must take
    course bi first if you want to take course ai.

For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.
Return the ordering of courses you should take to finish all courses. If there are many valid answers,
return any of them. If it is impossible to finish all courses, return an empty array.

Example 1:
Input: numCourses = 2, prerequisites = [[1,0]]
Output: [0,1]
Explanation: There are a total of 2 courses to take. To take course 1 you should have finished course 0.
So the correct course order is [0,1].

Example 2:
Input: numCourses = 4, prerequisites = [[1,0],[2,0],[3,1],[3,2]]
Output: [0,2,1,3]
Explanation: There are a total of 4 courses to take. To take course 3 you should have finished both
courses 1 and 2. Both courses 1 and 2 should be taken after you finished course 0.
So one correct course order is [0,1,2,3]. Another correct ordering is [0,2,1,3].

Example 3:
Input: numCourses = 1, prerequisites = []
Output: [0]*/

    int seq = 0;
    int[] result;

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        result = new int[numCourses];
        HashMap<Integer, List<Integer>> adj = new HashMap();

        for (int i = 0; i < numCourses; i++) {
            adj.put(i, new ArrayList());
        }

        //Add edges
        for (int[] requisite : prerequisites) {
            adj.get(requisite[1]).add(requisite[0]);
        }

        int[] visited = new int[numCourses];
        seq = numCourses - 1;

        for (int i = 0; i < numCourses; i++) {
            if (!dfs(adj, visited, i)) {
                return new int[]{};
            }
        }
        return result;
    }

    public boolean dfs(HashMap<Integer, List<Integer>> adj, int[] visited, int index) {
        if (visited[index] == -1) {
            return false;
        }

        if (visited[index] == 1) {
            return true;
        }

        //Mark as visiting
        visited[index] = -1;

        if (adj.containsKey(index)) {
            //Loop through all neighbor
            for (int j : adj.get(index)) {
                if (!dfs(adj, visited, j)) {
                    return false;
                }
            }
        }

        //mark as visited
        visited[index] = 1;
        result[seq--] = index;
        return true;
    }

    public boolean contains(final Integer[] array, final int key) {
        return Arrays.asList(array).contains(key);
    }

    public static void main(String[] args) {
        CourseScheduleII scheduleII = new CourseScheduleII();
        int[][] courses = {{1, 0}, {2, 0}, {3, 1}, {3, 2}};
        int[] res = scheduleII.findOrder(4, courses);
        System.out.println(Arrays.toString(res));
    }
}
