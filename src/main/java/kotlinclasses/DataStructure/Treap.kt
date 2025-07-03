/*
 * *
 *  * Treap.kt
 *  * Created by Rafsan Ahmad on 7/3/25, 2:30PM
 *  * Copyright (c) 2025 . All rights reserved.
 *  *
 *
 */

package kotlinclasses.DataStructure

import kotlin.random.Random

class Treap {
    /*Treap (Tree + Heap)
📘 What is it?
A Treap is a randomized balanced binary search tree (BST) that maintains the BST property on
keys and the heap property on randomly assigned priorities.

Each node has a key (like in a BST).
Each node has a randomly assigned priority (like in a heap).

How it works:
It behaves like a BST based on the key.
It behaves like a heap based on the random priority.
During insertion or deletion, rotations (left/right) are done to maintain the heap order.
Priorities are chosen randomly to balance the tree with high probability.

🔧 Operations Supported
insert(key) – Insert a key with random priority.
delete(key) – Remove a key.
kthSmallest(k) – Find k-th smallest element.
rank(x) – Find how many elements are < x.

🎯 Use Case Examples
Dynamically maintaining a sorted set.
Supporting duplicates (via node counts).
Finding k-th order statistics on-the-fly.
Useful in online algorithms, games, and competitive programming.

✅ Advantages
Self-balancing without explicit rebalancing logic.
Simpler than AVL or Red-Black trees.
Fast expected time: O(log n) for insert, delete, and search.
Supports additional queries like rank or k-th smallest efficiently.
*/

    class TreapNode(
        val key: Int,
        val priority: Int = Random.nextInt(),
        var count: Int = 1,
        var size: Int = 1,
        var left: TreapNode? = null,
        var right: TreapNode? = null
    )

    private fun getSize(node: TreapNode?): Int {
        return node?.size ?: 0
    }

    private fun updateSize(node: TreapNode?) {
        if (node == null) return
        node.size = node.count + getSize(node.left) + getSize(node.right)
    }

    private fun rotateRight(pivot: TreapNode): TreapNode {
        val newRoot = pivot.left ?: return pivot
        pivot.left = newRoot.right
        newRoot.right = pivot
        updateSize(pivot)
        updateSize(newRoot)
        return newRoot
    }

    private fun rotateLeft(pivot: TreapNode): TreapNode {
        val newRoot = pivot.right ?: return pivot
        pivot.right = newRoot.left
        newRoot.left = pivot
        updateSize(pivot)
        updateSize(newRoot)
        return newRoot
    }

    fun insert(root: TreapNode?, key: Int): TreapNode {
        if (root == null) return TreapNode(key)

        if (key < root.key) {
            root.left = insert(root.left, key)
            if ((root.left?.priority ?: Int.MIN_VALUE) > root.priority) {
                return rotateRight(root)
            }
        } else if (key > root.key) {
            root.right = insert(root.right, key)
            if ((root.right?.priority ?: Int.MIN_VALUE) > root.priority) {
                return rotateLeft(root)
            }
        } else {
            root.count++
        }

        updateSize(root)
        return root
    }

    fun delete(node: TreapNode?, key: Int): TreapNode? {
        if (node == null) return null

        var root = node  // Create a mutable reference to work with

        when {
            key < root.key -> root.left = delete(root.left, key)
            key > root.key -> root.right = delete(root.right, key)
            else -> {
                if (root.count > 1) {
                    root.count--
                } else {
                    if (root.left == null) return root.right
                    if (root.right == null) return root.left

                    // Rebalance the tree
                    root = if ((root.left?.priority ?: 0) > (root.right?.priority ?: 0)) {
                        rotateRight(root)
                    } else {
                        rotateLeft(root)
                    }

                    // Recursively delete from the updated root
                    root = delete(root, key)
                }
            }
        }

        updateSize(root)
        return root
    }

    fun kthSmallest(root: TreapNode?, k: Int): Int {
        if (root == null) throw IllegalArgumentException("k is too large")

        val leftSize = getSize(root.left)

        return when {
            k <= leftSize -> kthSmallest(root.left, k)
            k > leftSize + root.count -> kthSmallest(root.right, k - leftSize - root.count)
            else -> root.key
        }
    }
}

fun main() {
    val obj = Treap()
    var root: Treap.TreapNode? = null
    val input = listOf(5, 3, 8, 3, 10, 1)

    for (x in input) {
        root = obj.insert(root, x)
    }

    println("3rd smallest: ${obj.kthSmallest(root, 3)}") // Should be 3
    root = obj.delete(root, 3)
    root = obj.insert(root, -1)
    println("3rd smallest after deleting one '3': ${obj.kthSmallest(root, 3)}") // 3
    root = obj.delete(root, 3)
    println("3rd smallest after deleting second '3': ${obj.kthSmallest(root, 3)}") // 5
}