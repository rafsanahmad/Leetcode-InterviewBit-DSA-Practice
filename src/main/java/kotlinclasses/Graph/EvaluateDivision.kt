/*
 * *
 *  * Evaluate Division.kt
 *  * Created by Rafsan Ahmad on 5/3/25, 6:27PM
 *  * Copyright (c) 2025 . All rights reserved.
 *  *
 *
 */

package kotlinclasses.Graph

class EvaluateDivision {
    /*You are given an array of variable pairs equations and an array of real numbers values,
    where equations[i] = [Ai, Bi] and values[i] represent the equation Ai / Bi = values[i].
    Each Ai or Bi is a string that represents a single variable.

You are also given some queries, where queries[j] = [Cj, Dj] represents the jth query where you
must find the answer for Cj / Dj = ?.

Return the answers to all queries. If a single answer cannot be determined, return -1.0.

Note: The input is always valid. You may assume that evaluating the queries will not result in
division by zero and that there is no contradiction.

Note: The variables that do not occur in the list of equations are undefined, so the answer
cannot be determined for them.



Example 1:

Input: equations = [["a","b"],["b","c"]], values = [2.0,3.0],
queries = [["a","c"],["b","a"],["a","e"],["a","a"],["x","x"]]
Output: [6.00000,0.50000,-1.00000,1.00000,-1.00000]
Explanation:
Given: a / b = 2.0, b / c = 3.0
queries are: a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ?
return: [6.0, 0.5, -1.0, 1.0, -1.0 ]
note: x is undefined => -1.0
Example 2:

Input: equations = [["a","b"],["b","c"],["bc","cd"]], values = [1.5,2.5,5.0],
queries = [["a","c"],["c","b"],["bc","cd"],["cd","bc"]]
Output: [3.75000,0.40000,5.00000,0.20000]
Example 3:

Input: equations = [["a","b"]], values = [0.5],
queries = [["a","b"],["b","a"],["a","c"],["x","y"]]
Output: [0.50000,2.00000,-1.00000,-1.00000]


Constraints:
1 <= equations.length <= 20
equations[i].length == 2
1 <= Ai.length, Bi.length <= 5
values.length == equations.length
0.0 < values[i] <= 20.0
1 <= queries.length <= 20
queries[i].length == 2
1 <= Cj.length, Dj.length <= 5
Ai, Bi, Cj, Dj consist of lower case English letters and digits.*/

    /*Binary relationship is represented as a graph usually.
Does the direction of an edge matters? -- Yes. Take a / b = 2 for example, it
indicates a --2--> b as well as b --1/2--> a.
Thus, it is a directed weighted graph.
In this graph, how do we evaluate division?
Take a / b = 2, b / c = 3, a / c = ? for example,

a --2--> b --3--> c
We simply find a path using DFS from node a to node c and multiply the weights of edges passed,
i.e. 2 * 3 = 6.*/

    fun calcEquation(
        equations: List<List<String>>,
        values: DoubleArray,
        queries: List<List<String>>
    ): DoubleArray {
        val graph = buildGraph(equations, values)
        val result = DoubleArray(queries.size)

        for (i in queries.indices) {
            result[i] = getPathWeightDFS(queries[i][0], queries[i][1], HashSet<String>(), graph)
        }

        return result
    }

    private fun getPathWeightDFS(
        start: String,
        end: String,
        visited: MutableSet<String>,
        graph: MutableMap<String, MutableMap<String, Double>>
    ): Double {
        /* if start is not found. */
        if (!graph.containsKey(start)) return -1.0

        /* if end found - return weight. */
        graph[start]?.get(end)?.let {
            return it
        }

        visited.add(start)

        graph[start]?.let {
            for (neighbour in it.entries) {
                if (!visited.contains(neighbour.key)) {
                    val weight = getPathWeightDFS(neighbour.key, end, visited, graph)
                    if (weight != -1.0) return neighbour.value * weight
                }
            }
        }

        return -1.0
    }

    private fun buildGraph(
        equations: List<List<String>>,
        values: DoubleArray
    ): MutableMap<String, MutableMap<String, Double>> {
        val graph: MutableMap<String, MutableMap<String, Double>> =
            HashMap<String, MutableMap<String, Double>>()
        var u: String
        var v: String

        for (i in equations.indices) {
            u = equations[i][0]
            v = equations[i][1]
            graph.putIfAbsent(u, HashMap<String, Double>())
            graph[u]?.put(v, values[i])
            graph.putIfAbsent(v, HashMap<String, Double>())
            graph[v]?.put(u, 1 / values[i])
        }

        return graph
    }
}

fun main(str: Array<String>) {
    val evaluateDivision = EvaluateDivision()
    println(
        evaluateDivision.calcEquation(
            equations = listOf(listOf("a", "b"), listOf("b", "c")),
            values = doubleArrayOf(2.0, 3.0),
            queries = listOf(
                listOf("a", "c"), listOf("b", "a"), listOf("a", "e"),
                listOf("a", "a"), listOf("x", "x")
            )
        ).contentToString()
    )
}