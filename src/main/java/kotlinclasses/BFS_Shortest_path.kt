/*
 * *
 *  * Created by Rafsan Ahmad on 10/25/21, 9:50 PM
 *  * Copyright (c) 2021 . All rights reserved.
 *
 */

package kotlinclasses

import java.util.*

class BFS_Shortest_path {

    class Graph(size: Int) {
        private val adj: Array<ArrayList<Int>>
        private val marked: BooleanArray
        private val edgeTo: IntArray
        private val size: Int
        private var startPoint = 0
        fun addEdge(first: Int, second: Int) {
            adj[first].add(second)
            adj[second].add(first)
        }

        private fun bfs(start: Int) {
            val q: Queue<Int> = LinkedList()
            q.add(start)
            while (!q.isEmpty()) {
                val v = q.poll()
                marked[v] = true
                for (w in adj[v]) {
                    if (!marked[w]) {
                        marked[w] = true
                        edgeTo[w] = v
                        q.add(w)
                    }
                }
            }
        }

        fun pathTo(v: Int): Iterable<Int>? {
            if (!hasPathTo(v)) return null
            val path = Stack<Int>()
            var x = v
            while (x != startPoint) {
                path.push(x)
                x = edgeTo[x]
            }
            path.push(startPoint)
            return path
        }

        fun hasPathTo(v: Int): Boolean {
            return marked[v]
        }

        fun shortestReach(startId: Int): IntArray {
            val result = IntArray(size)
            startPoint = startId
            bfs(startId)
            for (v in 0 until size) {
                if (startId != v) {
                    if (hasPathTo(v)) {
                        val st = pathTo(v) as Stack<Int>?
                        var sum = 0
                        for (x in 1 until st!!.size) {
                            sum += 6
                        }
                        result[v] = sum
                    } else {
                        result[v] = -1
                    }
                }
            }
            return result
        }

        init {
            adj = Array(size) {ArrayList<Int>(0)}
            for (v in 0 until size) {
                adj[v] = ArrayList<Int>(size)
            }
            this.size = size
            marked = BooleanArray(size)
            edgeTo = IntArray(size)
        }
    }

}

fun main(args: Array<String>) {
    val scanner = Scanner(System.`in`)
    val queries = scanner.nextInt()
    for (t in 0 until queries) {
        //Create a graph of size n where each edge weight is 6
        val graph = BFS_Shortest_path.Graph(scanner.nextInt())
        val m = scanner.nextInt()

        //read and set edges
        for (i in 0 until m) {
            val u = scanner.nextInt() - 1
            val v = scanner.nextInt() - 1

            //add edge to the graph
            graph.addEdge(u, v)
        }

        //Find shortest reach from node s
        val startId = scanner.nextInt() - 1
        val distances = graph.shortestReach(startId)
        for (i in distances.indices) {
            if (i != startId) {
                print(distances[i])
                print(" ")
            }
        }
        println()
    }
    scanner.close()
}