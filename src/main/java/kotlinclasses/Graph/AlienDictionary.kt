/*
 * *
 *  * Alien Dictionary.kt
 *  * Created by Rafsan Ahmad on 4/10/25, 7:41PM
 *  * Copyright (c) 2025 . All rights reserved.
 *  *
 *
 */

package kotlinclasses.Graph

import java.util.LinkedList
import java.util.Queue
import kotlin.math.min


class AlienDictionary {
    //res/topo_sort_dictionary.png
    /*Problem Statement: Given a sorted dictionary of an alien language having N words and k
    starting alphabets of a standard dictionary. Find the order of characters in the alien
    language.

Note: Many orders may be possible for a particular test case, thus you may return any valid order.

Examples:

Example 1:
Input: N = 5, K = 4
dict = {"baa","abcd","abca","cab","cad"}
Output: b d a c
Explanation:
We will analyze every consecutive pair to find out the order of the characters.
The pair “baa” and “abcd” suggests ‘b’ appears before ‘a’ in the alien dictionary.
The pair “abcd” and “abca” suggests ‘d’ appears before ‘a’ in the alien dictionary.
The pair “abca” and “cab” suggests ‘a’ appears before ‘c’ in the alien dictionary.
The pair “cab” and “cad” suggests ‘b’ appears before ‘d’ in the alien dictionary.
So, [‘b’, ‘d’, ‘a’, ‘c’] is a valid ordering.

Example 2:
Input: N = 3, K = 3
dict = {"caa","aaa","aab"}
Output: c a b
Explanation: Similarly, if we analyze the consecutive pair
for this example, we will figure out [‘c’, ‘a’, ‘b’] is
a valid ordering.*/

    /*
Note: The intuition is to check every consecutive pair of words and find out the differentiating
factor. With these factors, we will form a directed graph, and the whole problem balls down to
a topological sort of problem.

Edge Case: The problem arises when the value of K becomes 5 and there is no word in the dictionary
containing the letter ‘e’. In this case, we will add a separate node with the value ‘e’ in the
graph and it will be considered a component of the directed graph like the following,
and the same algorithm will work fine for multiple components.
    
The follow-up question for the interview:
When is the ordering not possible?
There are two such cases when ordering is not possible:
If every character matches and the largest word appears before the shortest word: For example,
if “abcd” appears before “abc”, we can say the ordering is not possible.

If there exists a cyclic dependency between the characters: For example, in the dictionary:
dict: {“abc”, “bat”, “ade”} there exists a cyclic dependency between ‘a’ and ‘b’ because the
dictionary states ‘a’ < ‘b’ < ‘a’, which is not possible.*/

    private fun topoSort(v: Int, adj: MutableList<MutableList<Int>>): MutableList<Int> {
        val inDegree = IntArray(v)
        for (i in 0..<v) {
            for (it in adj[i]) {
                inDegree[it] = inDegree[it] + 1
            }
        }

        val queue: Queue<Int> = LinkedList<Int>()
        for (i in 0..<v) {
            if (inDegree[i] == 0) {
                queue.add(i)
            }
        }
        val topoSort: MutableList<Int> = mutableListOf()
        while (!queue.isEmpty()) {
            val node: Int = queue.peek()
            queue.remove()
            topoSort.add(node)
            // node is in your topo sort
            // so please remove it from the inDegree
            for (it in adj[node]) {
                inDegree[it] = inDegree[it] - 1
                if (inDegree[it] == 0) queue.add(it)
            }
        }

        return topoSort
    }

    fun findOrder(dict: Array<String>, n: Int, k: Int): String {
        val adj: MutableList<MutableList<Int>> = MutableList(k) { mutableListOf<Int>() }
        for (i in 0..<n - 1) {
            val s1 = dict[i]
            val s2 = dict[i + 1]
            val len = min(s1.length, s2.length)
            for (j in 0..<len) {
                if (s1[j] != s2[j]) {
                    adj[s1[j] - 'a'].add(s2[j] - 'a')
                    break
                }
            }
        }

        val topo = topoSort(k, adj)
        var stringBuilder = StringBuilder()
        for (it in topo) {
            val char = (it + 'a'.code).toChar()
            stringBuilder.append(char)
            stringBuilder.append(" ")
        }

        return stringBuilder.toString()
    }
}

fun main(args: Array<String>) {
    val dict = arrayOf<String>("baa", "abcd", "abca", "cab", "cad")
    val obj = AlienDictionary()
    println(obj.findOrder(dict, 5, 4))

    val dict2 = arrayOf<String>("caa", "aaa", "aab")
    println(obj.findOrder(dict2, 3, 4))
}