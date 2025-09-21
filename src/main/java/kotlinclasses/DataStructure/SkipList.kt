/*
 * *
 *  * Skip List.kt
 *  * Created by Rafsan Ahmad on 9/20/25, 1:39AM
 *  * Copyright (c) 2025 . All rights reserved.
 *  *
 *
 */

package kotlinclasses.DataStructure

import kotlin.random.Random

/*
How node level is assigned in a skip list:
Skip lists rely on randomization to decide how “tall” (number of levels) each node will be.

The standard method:
Start with level = 1.

Flip a (biased) coin with probability p (usually 0.5).

While the coin is heads, increase the level by 1.

Stop when the coin is tails.

This gives a geometric distribution of heights.

Probability distribution of levels (p = 0.5)

Level 1: probability = 50% of nodes
Level 2: probability = 25% of nodes
Level 3: probability = 12.5% of nodes
… and so on.

So taller nodes are exponentially rarer.
That’s what keeps the structure balanced.

Example:
Insert numbers 1 to 10, with p = 0.5. Suppose the coin flips went like this:

Value	Random level	Explanation
1	1	tails immediately
2	2	heads once, then tails
3	1	tails immediately
4	3	heads twice, then tails
5	1	tails immediately
6	2	heads once, then tails
7	1	tails immediately
8	1	tails immediately
9	1	tails immediately
10	4	heads 3 times, then tails

So the skip list looks like:

Level 4:                          [10]
Level 3:                 [4] ------------- [10]
Level 2:         [2] -------- [4] -------- [6] -------- [10]
Level 1: [1] -- [2] -- [3] -- [4] -- [5] -- [6] -- [7] -- [8] -- [9] -- [10]

Notice:
Level 1 has everyone.
Level 2 has ~25% of them.
Level 3 has fewer.
Level 4 only has 1 lucky node.
This gives the “express lanes” effect that makes search fast.

Total expected complexity:
Levels = log₁/ₚ(N) (≈ log₂N if p=0.5).
Each level ≈ 2 steps.
Total expected steps ≈ 2 · log₂N.
Search, insert, delete = O(log N) expected.
*/

class Node(val value: Int, lvl: Int) {
    val forward: Array<Node> = Array(lvl + 1) { this } // init with self, will be overwritten
}

class SkipList(private val maxLevel: Int = 16, private val p: Double = 0.5) {
    private val head = Node(Int.MIN_VALUE, maxLevel)
    private val tail = Node(Int.MAX_VALUE, maxLevel)
    private var level = 0
    private val rand = Random(System.nanoTime())

    init {
        for (i in 0..maxLevel) head.forward[i] = tail
    }

    private fun randomLevel(): Int {
        var lvl = 0
        while (lvl < maxLevel && rand.nextDouble() < p) lvl++
        return lvl
    }

    /** Search */
    fun search(value: Int): Boolean {
        var x = head
        for (i in level downTo 0) {
            while (x.forward[i].value < value) {
                x = x.forward[i]
            }
        }
        return x.forward[0].value == value
    }

    /** Insert */
    /*Visual Walkthrough Example

Suppose current skip list (before insert):

Level 2:  [1] -------- [10]
Level 1:  [1] -- [5] -- [10]
Level 0:  [1] -- [5] -- [10]


Now we insert(7).

Step 1: Traverse & fill update[]
Start at top (Level 2).
From 1 → 10 (stop, since 10 > 7).
So update[2] = 1.
Drop to Level 1.
From 1 → 5 (ok).
Next is 10 (stop).
So update[1] = 5.

Drop to Level 0.
From 5 → stop before 10.
So update[0] = 5.

Now:
update[2] = node(1)
update[1] = node(5)
update[0] = node(5)

Step 2: Generate random level for 7

Say random gave lvl = 1.
So new node will appear in Level 0 and Level 1.

Step 3 & 4: Re-wire pointers

For each level ≤ 1:

Level 0:
update[0] = 5
newNode.forward[0] = update[0].forward[0]  // → 10
update[0].forward[0] = newNode             // 5 → 7

Level 1:
update[1] = 5
newNode.forward[1] = update[1].forward[1]  // → 10
update[1].forward[1] = newNode             // 5 → 7

Final Skip List:
Level 2:  [1] -------- [10]
Level 1:  [1] -- [5] -- [7] -- [10]
Level 0:  [1] -- [5] -- [7] -- [10]*/
    fun insert(value: Int) {
        // 1. Track predecessors ("update array") at each level where new node will be inserted
        val update = Array<Node>(maxLevel + 1) { head }
        var x = head

        // Start from top level → go down
        for (i in level downTo 0) {
            // Move forward while next node < value
            while (x.forward[i].value < value) x = x.forward[i]
            // Save where we stopped at this level
            update[i] = x
        }

        // 2. Decide random level for the new node
        val lvl = randomLevel()
        if (lvl > level) {
            // If new node is taller than current skip list height,
            // initialize missing update references with head
            for (i in level + 1..lvl) update[i] = head
            level = lvl
        }

        // 3. Create new node
        val newNode = Node(value, lvl)

        // 4. Re-wire pointers at each level up to 'lvl'
        for (i in 0..lvl) {
            // newNode points to what update[i] was pointing to
            newNode.forward[i] = update[i].forward[i]
            // update[i] now points to newNode
            update[i].forward[i] = newNode
        }
    }

    /** Delete */
    /*Visual Walkthrough Example
Suppose the skip list looks like this before deleting 7:
Level 2:  [1] -------- [10]
Level 1:  [1] -- [5] -- [7] -- [10]
Level 0:  [1] -- [5] -- [7] -- [10]

We want to delete(7).
Step 1: Fill update[] array
Level 2: head → 1 → 10
10 > 7 → stop, update[2] = 1
Level 1: 1 → 5 → 7 → 10
Stop before 7 → update[1] = 5
Level 0: 1 → 5 → 7 → 10
Stop before 7 → update[0] = 5

Now we have:
update[2] = node(1)
update[1] = node(5)
update[0] = node(5)

Step 2: Re-wire pointers
For each level where 7 exists:
Level 1:
update[1].forward[1] = target.forward[1]  // 5 → 10

Level 0:
update[0].forward[0] = target.forward[0]  // 5 → 10

Level 2:
update[2].forward[2] != target → skip, no change

Step 3: Update skip list height
Level 2: head → 10 → not tail → height remains Done.

Final Skip List After Deletion
Level 2:  [1] -------- [10]
Level 1:  [1] -- [5] -- [10]
Level 0:  [1] -- [5] -- [10]

Node 7 is removed from all levels it existed on.
*/
    fun delete(value: Int): Boolean {
        // 1. Track predecessors at each level where node might exist
        val update = Array<Node>(maxLevel + 1) { head }
        var x = head

        // Traverse from top level down to level 0
        for (i in level downTo 0) {
            // Move forward while next node < value
            while (x.forward[i].value < value) x = x.forward[i]
            // Store the last node before the target at this level
            update[i] = x
        }

        // Candidate node to delete
        val target = x.forward[0]
        if (target.value != value) return false  // Node not found

        // 2. Re-wire pointers at all levels where target exists
        for (i in 0..level) {
            if (update[i].forward[i] != target) break  // Stop if target not in this level
            update[i].forward[i] = target.forward[i]  // Skip over target
        }

        // 3. Update skip list height if needed
        while (level > 0 && head.forward[level] == tail) level--

        return true
    }


    /** Print */
    fun printList() {
        println("Skip List (maxLevel=$level)")
        for (i in level downTo 0) {
            var x = head.forward[i]
            print("Level $i: ")
            while (x != tail) {
                print("${x.value} ")
                x = x.forward[i]
            }
            println()
        }
    }
}

fun main() {
    val skipList = SkipList()

    skipList.insert(10)
    skipList.insert(20)
    skipList.insert(15)
    skipList.insert(30)

    skipList.printList()

    println("Search 15: ${skipList.search(15)}") // true
    println("Search 25: ${skipList.search(25)}") // false

    println("Delete 20: ${skipList.delete(20)}") // true
    println("Delete 25: ${skipList.delete(25)}") // false

    skipList.printList()
}
