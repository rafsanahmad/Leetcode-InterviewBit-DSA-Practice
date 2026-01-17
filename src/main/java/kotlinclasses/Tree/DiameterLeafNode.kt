/*
 *
 *  * DiameterLeafNode.kt
 *  *
 *  * Created by Rafsan Ahmad on 01/13/26, 3:31 PM
 *  * Copyright (c) 2026. All rights reserved.
 *
 */

package kotlinclasses.Tree

class DiameterLeafNode {
    /*Given an undirected tree with N nodes labeled 0 to N-1, find all leaf nodes that lie on at
    least one diameter of the tree.

         A(0)
              \
               B(1)
              /   \
          E(4)     C(2)
                   /  \
                 F(5)  D(3)

Edges:
A - B
E - B
B - C
C - D
C - F
Diameter paths:
A → B → C → D
E → B → C → F

Output
Leaf nodes on at least one diameter:
A, E, D, F
(Using indices: [0, 4, 3, 5])

    We need all leaf nodes that lie on any diameter path of the tree, in O(n) time.

From your diagram, the diameter paths are like:

A → B → C → D

E → B → C → F

So the leaf nodes that participate in at least one diameter are:
A, E, D, F

Key Idea (O(n))

A tree diameter can be found using two DFS passes:

Pick any node → DFS to find the farthest node U

DFS from U to find the farthest node V

The path U → V is a diameter

But ❗ there can be multiple diameter paths, so we must find all leaves whose
longest-distance-to-either-end equals the diameter length.

Trick
For every node:
distFromU[node]
distFromV[node]
Let:
diameter = distFromU[V]

A leaf node L lies on some diameter iff:
max(distFromU[L], distFromV[L]) == diameter

This works because:
A leaf is on a longest path if it reaches one diameter end with full length

Algorithm Steps
Build adjacency list
DFS from any node → get U
DFS from U → get V + distFromU
DFS from V → get distFromV
Iterate all nodes:
If node is a leaf
And max(distFromU, distFromV) == diameter
Add to result
⏱ Time Complexity: O(n)
Space: O(n)*/
    class DiameterLeafNodes(private val n: Int) {

        private val graph = Array(n) { mutableListOf<Int>() }

        fun addEdge(u: Int, v: Int) {
            graph[u].add(v)
            graph[v].add(u)
        }

        private fun dfs(start: Int): Pair<IntArray, Int> {
            val dist = IntArray(n) { -1 }
            var farthestNode = start

            fun dfsUtil(node: Int, parent: Int) {
                for (next in graph[node]) {
                    if (next != parent) {
                        dist[next] = dist[node] + 1
                        if (dist[next] > dist[farthestNode]) {
                            farthestNode = next
                        }
                        dfsUtil(next, node)
                    }
                }
            }

            dist[start] = 0
            dfsUtil(start, -1)
            return Pair(dist, farthestNode)
        }

        fun getDiameterLeafNodes(): List<Int> {

            // Find one end of diameter
            val (_, u) = dfs(0)

            // Find the other end and distances from U
            val (distFromU, v) = dfs(u)

            // Distances from V
            val (distFromV, _) = dfs(v)

            val diameter = distFromU[v]
            val result = mutableListOf<Int>()

            for (i in 0 until n) {
                val isLeaf = graph[i].size == 1
                if (isLeaf && maxOf(distFromU[i], distFromV[i]) == diameter) {
                    result.add(i)
                }
            }

            return result
        }
    }
}

fun main() {

    /*
        Tree structure:

            A(0)
              \
               B(1)
              /   \
          E(4)     C(2)
                   /  \
                 F(5)  D(3)


     */

    val tree = DiameterLeafNode.DiameterLeafNodes(6)

    tree.addEdge(0, 1) // A - B
    tree.addEdge(4, 1) // E - B
    tree.addEdge(1, 2) // B - C
    tree.addEdge(2, 3) // C - D
    tree.addEdge(2, 5) // C - F

    val result = tree.getDiameterLeafNodes()

    println("Leaf nodes on diameter:")
    result.forEach { node ->
        println("Node $node")
    }
}
