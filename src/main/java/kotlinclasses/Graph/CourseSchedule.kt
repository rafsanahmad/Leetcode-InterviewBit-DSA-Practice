/*
 * *
 *  * Course Schedule.kt
 *  * Created by Rafsan Ahmad on 6/29/25, 10:38PM
 *  * Copyright (c) 2025 . All rights reserved.
 *  *
 *
 */

package kotlinclasses.Graph

class CourseSchedule {
    //https://leetcode.com/problems/course-schedule/description/
    /*There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1.
     You are given an array prerequisites where prerequisites[i] = [ai, bi] indicates that you
     must take course bi first if you want to take course ai.

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
To take course 1 you should have finished course 0, and to take course 0 you should also have
finished course 1. So it is impossible.


Constraints:
1 <= numCourses <= 2000
0 <= prerequisites.length <= 5000
prerequisites[i].length == 2
0 <= ai, bi < numCourses
All the pairs prerequisites[i] are unique.*/

    /*
    Total Time Complexity: O(n + m)
    n = numCourses
    m = number of prerequisites (i.e., number of directed edges)

    Total Space Complexity: O(n + m)
*/
    /*visited[index] values:
0 → Unvisited
-1 → Visiting (currently in the recursion stack)
1 → Visited and safe (no cycle found in this path)
*/
    fun canFinish(numCourses: Int, prerequisites: Array<IntArray>): Boolean {
        if (numCourses == 0 || prerequisites.isEmpty()) return true

        val map: MutableMap<Int, MutableList<Int>> = hashMapOf()
        val visited = IntArray(numCourses)

        for (i in prerequisites.indices) {
            val courses = prerequisites[i]
            if (map[courses[1]] != null) {
                map[courses[1]]?.add(courses[0])
            } else {
                map[courses[1]] = mutableListOf(courses[0])
            }
        }

        for (i in 0 until numCourses) {
            if (!courseDFS(map, visited, i)) {
                return false
            }
        }

        return true
    }

    fun courseDFS(map: MutableMap<Int, MutableList<Int>>, visited: IntArray, index: Int): Boolean {
        if (visited[index] == -1) {
            return false
        }

        /*"We have already visited this node, and we did not detect a cycle starting from it.
        So we don't need to explore it again."
        This is a memoization optimization:
        Without this, we would re-traverse the same subtrees multiple times.
        With it, we avoid unnecessary rework and prevent timeouts on large graphs.
        */
        if (visited[index] == 1) {
            return true
        }

        //Mark as visiting
        visited[index] = -1
        map[index]?.let {
            for (neighbour in it) {
                if (!courseDFS(map, visited, neighbour)) {
                    return false
                }
            }
        }

        //Mark as visited
        visited[index] = 1
        return true
    }
}

fun main() {
    val obj = CourseSchedule()
    println(obj.canFinish(2, arrayOf(intArrayOf(1, 0))))
    println(obj.canFinish(2, arrayOf(intArrayOf(1, 0), intArrayOf(0, 1))))
}