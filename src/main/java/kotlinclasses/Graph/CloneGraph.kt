/*
 * *
 *  * Clone Graph.kt
 *  * Created by Rafsan Ahmad on 8/7/25, 9:42PM
 *  * Copyright (c) 2025 . All rights reserved.
 *  *
 *
 */

package kotlinclasses.Graph

class Node(var `val`: Int) {
    var neighbors: ArrayList<Node?> = ArrayList<Node?>()
}

class CloneGraph {
    //https://leetcode.com/problems/clone-graph/
    /*Given a reference of a node in a connected undirected graph.

Return a deep copy (clone) of the graph.

Each node in the graph contains a value (int) and a list (List[Node]) of its neighbors.

class Node {
    public int val;
    public List<Node> neighbors;
}


Test case format:

For simplicity, each node's value is the same as the node's index (1-indexed). For example, the first
node with val == 1, the second node with val == 2, and so on. The graph is represented in the test
case using an adjacency list.

An adjacency list is a collection of unordered lists used to represent a finite graph. Each list
describes the set of neighbors of a node in the graph.

The given node will always be the first node with val = 1. You must return the copy of the given
node as a reference to the cloned graph.

Example 1:
Input: adjList = [[2,4],[1,3],[2,4],[1,3]]
Output: [[2,4],[1,3],[2,4],[1,3]]
Explanation: There are 4 nodes in the graph.
1st node (val = 1)'s neighbors are 2nd node (val = 2) and 4th node (val = 4).
2nd node (val = 2)'s neighbors are 1st node (val = 1) and 3rd node (val = 3).
3rd node (val = 3)'s neighbors are 2nd node (val = 2) and 4th node (val = 4).
4th node (val = 4)'s neighbors are 1st node (val = 1) and 3rd node (val = 3).

Example 2:
Input: adjList = [[]]
Output: [[]]
Explanation: Note that the input contains one empty list. The graph consists of only one node with
val = 1 and it does not have any neighbors.

Example 3:
Input: adjList = []
Output: []
Explanation: This an empty graph, it does not have any nodes.


Constraints:
The number of nodes in the graph is in the range [0, 100].
1 <= Node.val <= 100
Node.val is unique for each node.
There are no repeated edges and no self-loops in the graph.
The Graph is connected and all nodes can be visited starting from the given node.*/

    fun cloneGraph(node: Node?): Node? {
        if (node == null) return null
        val map: MutableMap<Node, Node> = hashMapOf()
        return cloneHelper(node, map)
    }

    fun cloneHelper(node: Node?, map: MutableMap<Node, Node>): Node? {
        if (node == null) return null
        val clone = Node(node.`val`)
        map[node] = clone
        val list: ArrayList<Node?> = arrayListOf()

        for (item in node.neighbors) {
            if (map.contains(item)) {
                map[item]?.let { list.add(it) }
            } else {
                list.add(cloneHelper(item, map))
            }
        }
        clone.neighbors = list
        return clone
    }

    fun printGraph(node: Node?) {
        if (node == null) return
        val visited = mutableSetOf<Node>()
        val queue = ArrayDeque<Node>()
        queue.add(node)

        while (queue.isNotEmpty()) {
            val curr = queue.removeFirst()
            if (!visited.add(curr)) continue

            print("Node ${curr.`val`} neighbors: ")
            for (nbr in curr.neighbors) {
                print("${nbr?.`val`} ")
                if (nbr != null && !visited.contains(nbr)) {
                    queue.add(nbr)
                }
            }
            println()
        }
    }
}

fun main() {
    val obj = CloneGraph()
    // Step 1: Create nodes
    val node1 = Node(1)
    val node2 = Node(2)
    val node3 = Node(3)
    val node4 = Node(4)

    // Step 2: Build graph connections as per [[2,4],[1,3],[2,4],[1,3]]
    node1.neighbors.addAll(listOf(node2, node4))
    node2.neighbors.addAll(listOf(node1, node3))
    node3.neighbors.addAll(listOf(node2, node4))
    node4.neighbors.addAll(listOf(node1, node3))

    // Step 3: Clone the graph
    val cloned = obj.cloneGraph(node1)

    // Step 4: Print the cloned graph to verify
    obj.printGraph(cloned)
}