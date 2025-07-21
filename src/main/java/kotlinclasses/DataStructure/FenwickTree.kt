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

    private val tree = IntArray(size + 1) // size+1 for safe indexing

    // Build Fenwick Tree from 0-based array
    fun build(arr: IntArray) {
        for (i in arr.indices) {
            update(i, arr[i])
        }
    }

    // Update index i by delta (0-based)
    fun update(i: Int, delta: Int) {
        var index = i + 1  // shift to 1-based inside tree
        while (index <= size) {
            tree[index] += delta
            index += index and -index
        }
    }

    // Prefix sum from 0 to i (inclusive)
    fun prefixSum(i: Int): Int {
        var index = i + 1 // shift to 1-based
        var sum = 0
        while (index > 0) {
            sum += tree[index]
            index -= index and -index
        }
        return sum
    }

    // Range sum from l to r (both 0-based, inclusive)
    fun rangeSum(l: Int, r: Int): Int {
        if (l > r) return 0
        return prefixSum(r) - prefixSum(l - 1)
    }

    // Set value at index i (0-based) to newValue, given original arr
    fun set(i: Int, newValue: Int, originalArray: IntArray) {
        val delta = newValue - originalArray[i]
        update(i, delta)
        originalArray[i] = newValue
    }

    fun printTree() {
        println(tree.joinToString(prefix = "Fenwick Tree: ", separator = ", "))
    }
}

fun main() {
    val arr = intArrayOf(5, 3, 7, 9, 6)
    val ft = FenwickTree(arr.size)
    ft.build(arr)

    println("Prefix sum up to index 2: ${ft.prefixSum(2)}")  // 5 + 3 + 7 = 15
    println("Range sum (1 to 3): ${ft.rangeSum(1, 3)}")      // 3 + 7 + 9 = 19

    ft.set(2, 10, arr) // Change arr[2] from 7 to 10

    println("After update:")
    println("Prefix sum up to index 2: ${ft.prefixSum(2)}")  // 5 + 3 + 10 = 18
    println("Range sum (1 to 3): ${ft.rangeSum(1, 3)}")      // 3 + 10 + 9 = 22

    ft.printTree()
}