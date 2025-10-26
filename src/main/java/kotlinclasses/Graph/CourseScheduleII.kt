/*
 * *
 *  * CourseScheduleII.kt
 *  * Created by Rafsan Ahmad on 6/30/25, 4:33PM
 *  * Copyright (c) 2025 . All rights reserved.
 *  *
 *
 */

package kotlinclasses.Graph

class CourseScheduleII {
    //https://leetcode.com/problems/course-schedule-ii/
    /*There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1.
    You are given an array prerequisites where prerequisites[i] = [ai, bi] indicates that you must
     take course bi first if you want to take course ai.

For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.
Return the ordering of courses you should take to finish all courses. If there are many valid
answers, return any of them. If it is impossible to finish all courses, return an empty array.

Example 1:
Input: numCourses = 2, prerequisites = [[1,0]]
Output: [0,1]
Explanation: There are a total of 2 courses to take. To take course 1 you should have finished
course 0. So the correct course order is [0,1].

Example 2:
Input: numCourses = 4, prerequisites = [[1,0],[2,0],[3,1],[3,2]]
Output: [0,2,1,3]
Explanation: There are a total of 4 courses to take. To take course 3 you should have finished
both
courses 1 and 2. Both courses 1 and 2 should be taken after you finished course 0.
So one correct course order is [0,1,2,3]. Another correct ordering is [0,2,1,3].

Example 3:
Input: numCourses = 1, prerequisites = []
Output: [0]


Constraints:
1 <= numCourses <= 2000
0 <= prerequisites.length <= numCourses * (numCourses - 1)
prerequisites[i].length == 2
0 <= ai, bi < numCourses
ai != bi
All the pairs [ai, bi] are distinct.
*/

    fun findOrder(numCourses: Int, prerequisites: Array<IntArray>): IntArray {
        if (numCourses == 0) return intArrayOf()

        val map: MutableMap<Int, MutableList<Int>> = hashMapOf()
        val visited = IntArray(numCourses)
        val result = IntArray(numCourses)
        courseIndex = numCourses - 1

        for (i in prerequisites.indices) {
            val course = prerequisites[i]
            if (map[course[1]] != null) {
                map[course[1]]?.add(course[0])
            } else {
                map[course[1]] = mutableListOf(course[0])
            }
        }

        for (i in 0 until numCourses) {
            if (!courseDFS(map, visited, result, i)) {
                return intArrayOf()
            }
        }

        return result
    }

    var courseIndex = 0
    fun courseDFS(
        map: MutableMap<Int, MutableList<Int>>,
        visited: IntArray,
        result: IntArray,
        index: Int
    ): Boolean {
        if (visited[index] == -1) {
            return false
        }

        if (visited[index] == 1) {
            return true
        }

        visited[index] = -1
        map[index]?.let {
            for (neighbour in it) {
                if (!courseDFS(map, visited, result, neighbour)) {
                    return false
                }
            }
        }

        visited[index] = 1
        result[courseIndex--] = index
        return true
    }
}

fun main() {
    val obj = CourseScheduleII()
    val courses =
        arrayOf(intArrayOf(1, 0), intArrayOf(2, 0), intArrayOf(3, 1), intArrayOf(3, 2))
    val res = obj.findOrder(4, courses)
    println(res.contentToString())
}