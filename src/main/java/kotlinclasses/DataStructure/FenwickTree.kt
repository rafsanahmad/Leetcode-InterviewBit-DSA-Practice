/*
 * *
 *  * Fenwick Tree.kt
 *  * Created by Rafsan Ahmad on 7/3/25, 2:22PM
 *  * Copyright (c) 2025 . All rights reserved.
 *  *
 *
 */

package kotlinclasses.DataStructure

class FenwickTree(private val size: Int) {
    /*Fenwick Tree (Binary Indexed Tree)
📘 What is it?
A Fenwick Tree, also known as a Binary Indexed Tree (BIT), is a data structure that provides
efficient methods for prefix sum queries and updates in a 1D array.

It is designed to:
Query the sum of the first i elements in O(log n) time.
Update the value at an index in O(log n) time.

🔧 Supported Operations
update(i, delta) – adds delta to the element at index i.
query(i) – returns the sum of elements from 1 to i.
rangeSum(l, r) – returns the sum from index l to r.

🎯 Use Case Examples
Dynamic prefix sums (e.g., running totals).
Counting inversions in an array.
Frequency tables (e.g., number of values less than X).
2D extensions for matrix sums.

✅ Advantages
Much faster than segment trees in practice for simple prefix sums.
Uses only O(n) space.
Simple and elegant implementation.
Ideal for competitive programming and interview problems.*/

    private val tree = IntArray(size + 1) // 1-based indexing

    // Point update: add `delta` to index `i`
    fun update(i: Int, delta: Int) {
        var index = i
        while (index <= size) {
            tree[index] += delta
            index += index and -index
        }
    }

    // Prefix sum query: sum from 1 to `i`
    fun query(i: Int): Int {
        var index = i
        var sum = 0
        while (index > 0) {
            sum += tree[index]
            index -= index and -index
        }
        return sum
    }

    // Range sum: sum from l to r (both inclusive)
    fun rangeSum(l: Int, r: Int): Int {
        return query(r) - query(l - 1)
    }

    // Find kth smallest number using prefix sums (used for frequency-based queries)
    fun kthSmallest(k: Int): Int {
        var idx = 0
        var bitMask = Integer.highestOneBit(size)

        var kTemp = k
        while (bitMask != 0) {
            val nextIdx = idx + bitMask
            if (nextIdx <= size && tree[nextIdx] < kTemp) {
                kTemp -= tree[nextIdx]
                idx = nextIdx
            }
            bitMask = bitMask shr 1
        }
        return idx + 1 // index of k-th smallest
    }
}

fun main() {
    val maxValue = 100
    val bit = FenwickTree(maxValue)

    // Insert numbers: 5, 1, 7, 3, 5
    bit.update(5, 1)
    bit.update(1, 1)
    bit.update(7, 1)
    bit.update(3, 1)
    bit.update(5, 1) // 5 appears twice

    println("Prefix sum (1 to 5): " + bit.query(5)) // Should be 4
    println("Range sum (3 to 7): " + bit.rangeSum(3, 7)) // Should be 4
    println("3rd smallest: " + bit.kthSmallest(3)) // Should be 5
}