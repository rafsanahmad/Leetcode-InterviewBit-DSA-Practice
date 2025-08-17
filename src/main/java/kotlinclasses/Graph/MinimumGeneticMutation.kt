/*
 * *
 *  * Minimum Genetic Mutation.kt
 *  * Created by Rafsan Ahmad on 8/15/25, 2:23PM
 *  * Copyright (c) 2025 . All rights reserved.
 *  *
 *
 */

package kotlinclasses.Graph

import java.util.LinkedList
import java.util.Queue

class MinimumGeneticMutation {
    //https://leetcode.com/problems/minimum-genetic-mutation/description/
    /*A gene string can be represented by an 8-character long string, with choices from
    'A', 'C', 'G', and 'T'.

Suppose we need to investigate a mutation from a gene string startGene to a gene string endGene where
 one mutation is defined as one single character changed in the gene string.

For example, "AACCGGTT" --> "AACCGGTA" is one mutation.
There is also a gene bank bank that records all the valid gene mutations. A gene must be in bank to
make it a valid gene string.

Given the two gene strings startGene and endGene and the gene bank bank, return the minimum number
of mutations needed to mutate from startGene to endGene. If there is no such a mutation, return -1.

Note that the starting point is assumed to be valid, so it might not be included in the bank.


Example 1:
Input: startGene = "AACCGGTT", endGene = "AACCGGTA", bank = ["AACCGGTA"]
Output: 1

Example 2:
Input: startGene = "AACCGGTT", endGene = "AAACGGTA", bank = ["AACCGGTA","AACCGCTA","AAACGGTA"]
Output: 2


Constraints:
0 <= bank.length <= 10
startGene.length == endGene.length == bank[i].length == 8
startGene, endGene, and bank[i] consist of only the characters ['A', 'C', 'G', 'T'].*/

    /*Time Complexity:
Let:
N = number of genes in bank
L = length of each gene (here L = 8)
There are 4 possible nucleotides (A, C, G, T)

In BFS:
For each gene dequeued, we try L positions × 4 possible replacements → O(L) operations to generate
neighbors.

Each valid neighbor is checked against visited (O(1) with HashSet) and bankSet (O(1) lookup).

In the worst case, we visit all genes in the bank once.
Time = O(N × L × 4) ⇒ O(N × L)
Given L is constant (8), complexity is O(N) in practice.

Space Complexity:
visited set: O(N)
queue: O(N)
bankSet: O(N)
Space = O(N)*/
    fun minMutation(startGene: String, endGene: String, bank: Array<String>): Int {
        val bankSet = bank.toHashSet()
        if (!bankSet.contains(endGene)) return -1

        val availGene = arrayOf('A', 'C', 'G', 'T')
        val queue: Queue<String> = LinkedList()
        val visited: HashSet<String> = hashSetOf()
        queue.add(startGene)
        visited.add(startGene)
        var mutationCount = 0


        while (!queue.isEmpty()) {
            for (i in 0 until queue.size) {
                val currGene = queue.poll()
                if (currGene == endGene) return mutationCount

                for (i in currGene.indices) {
                    val currCharArray = currGene.toCharArray()
                    for (item in availGene) {
                        currCharArray[i] = item
                        val newGene = String(currCharArray)
                        if (!visited.contains(newGene) && bankSet.contains(newGene)) {
                            queue.add(newGene)
                            visited.add(newGene)
                        }
                    }
                }
            }

            //Increment the step count after processing all nodes of the current BFS level
            //because all nodes in that level are exactly the same "distance" from the start.
            mutationCount++
        }

        return -1
    }
}

fun main() {
    val obj = MinimumGeneticMutation()
    val bank = arrayOf("AACCGGTA", "AACCGCTA", "AAACGGTA")
    println(obj.minMutation("AACCGGTT", "AAACGGTA", bank))
}