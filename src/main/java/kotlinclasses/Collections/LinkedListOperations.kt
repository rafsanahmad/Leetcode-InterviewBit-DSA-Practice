/*
 * *
 *  * LinkedList Operations.kt
 *  * Created by Rafsan Ahmad on 10/2/25, 4:36PM
 *  * Copyright (c) 2025 . All rights reserved.
 *  *
 *
 */

package kotlinclasses.Collections

import java.util.LinkedList

/*Complexity Table for LinkedList (Kotlin/Java)
add(e) → O(1) → adds at the tail
addFirst(e) → O(1) → push at head
addLast(e) → O(1) → push at tail
add(index, e) → O(n) → must traverse to index
removeFirst() → O(1) → remove head
removeLast() → O(1) → remove tail
remove(e) → O(n) → must search for element
removeAt(index) → O(n) → traverse to index
get(index) → O(n) → traverse to index
set(index, e) → O(n) → traverse + replace
iteration (for-each) → O(n) → walk through nodes

Comparison vs ArrayList
ArrayList:
get(i), set(i, v) → O(1) (direct indexing)
add/remove at end → O(1) amortized
insert/remove in middle → O(n) (shifting elements)

LinkedList:
get(i), set(i, v) → O(n) (walk nodes)
add/remove at head/tail → O(1)
insert/remove in middle (with iterator) → O(1), but finding iterator is O(n)

So:
Use ArrayList when you need random access.
Use LinkedList when you need frequent head/tail insert/remove.*/

fun main() {
    val list = LinkedList<Int>()

    // Add elements
    list.add(10)        // O(1) amortized (add to end)
    list.addFirst(5)    // O(1)
    list.addLast(20)    // O(1)

    println(list) // [5, 10, 20]

    // Remove elements
    list.removeFirst()  // O(1)
    list.removeLast()   // O(1)
    list.remove(10)     // O(n) -> must search for the value

    println(list) // [20]

    // Insert at index
    list.add(0, 99)     // O(n) -> must traverse to index
    println(list) // [99, 20]

    // Update (set element by index)
    list.add(10)
    list[1] = 100       // O(n) -> traversal to index, then O(1) update
    println(list) // [99, 100]

    // Access by index
    val x = list[0]     // O(n) -> traversal
    println("First element = $x")
}