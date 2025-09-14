/*
 * *
 *  * Array Deque.kt
 *  * Created by Rafsan Ahmad on 9/14/25, 12:04AM
 *  * Copyright (c) 2025 . All rights reserved.
 *  *
 *
 */

package kotlinclasses.DataStructure

/*Java Deque Interface:
In Java, Deque is just an interface in java.util.
It defines operations for double-ended queues:
addFirst, addLast, removeFirst, removeLast
peekFirst, peekLast
offer, poll, push, pop
It does not implement anything itself. You need a concrete class.

Common Implementations
(a) LinkedList<E>
LinkedList implements Deque (and List and Queue).
Internally, it’s a doubly linked list.

Node {
    E item
    Node<E> prev
    Node<E> next
}

Insertion/removal at both ends → O(1)
Random access (get by index) → O(n)

Usage:
Deque<Integer> deque = new LinkedList<>();
deque.addFirst(10);
deque.addLast(20);
int first = deque.removeFirst();

(b) ArrayDeque<E>
This is the more efficient default for most deque use-cases.
Internally, it uses a resizable circular array.
array = [   ,   ,   , ...   ]
head -> index of first element
tail -> index of next empty slot at end

When adding to front/back:
Move head backward or tail forward in a circular manner.
When array is full → resizes (like ArrayList)

Performance:
addFirst / addLast → amortized O(1)
removeFirst / removeLast → O(1)
Random access → O(n) (not designed for index access)

Usage:
Deque<Integer> deque = new ArrayDeque<>();
deque.addFirst(10);
deque.addLast(20);
int last = deque.removeLast();
*/
class MyArrayDeque<T>(initialCapacity: Int = 8) {
    private var array: Array<Any?> = Array(initialCapacity) { null }
    private var head = 0    // index of first element
    private var tail = 0    // index of next empty slot
    private var size = 0

    // Resize the array when full
    private fun resize() {
        val newCapacity = array.size * 2
        val newArray = Array<Any?>(newCapacity) { null }
        for (i in 0 until size) {
            newArray[i] = array[(head + i) % array.size]
        }
        array = newArray
        head = 0
        tail = size
    }

    // Add to front
    fun addFirst(value: T) {
        if (size == array.size) resize()
        head = (head - 1 + array.size) % array.size
        array[head] = value
        size++
    }

    // Add to back
    fun addLast(value: T) {
        if (size == array.size) resize()
        array[tail] = value
        tail = (tail + 1) % array.size
        size++
    }

    // Remove from front
    fun removeFirst(): T? {
        if (size == 0) return null
        val value = array[head] as T
        array[head] = null
        head = (head + 1) % array.size
        size--
        return value
    }

    // Remove from back
    fun removeLast(): T? {
        if (size == 0) return null
        tail = (tail - 1 + array.size) % array.size
        val value = array[tail] as T
        array[tail] = null
        size--
        return value
    }

    fun peekFirst(): T? = if (size == 0) null else array[head] as T
    fun peekLast(): T? = if (size == 0) null else array[(tail - 1 + array.size) % array.size] as T
    fun isEmpty() = size == 0
    fun getSize() = size
}

fun main() {
    val deque = MyArrayDeque<Int>()

    deque.addLast(10)   // [10]
    deque.addFirst(5)   // [5, 10]
    deque.addLast(20)   // [5, 10, 20]

    println(deque.removeFirst())  // 5
    println(deque.removeLast())   // 20
    println(deque.peekFirst())    // 10
    println(deque.peekLast())     // 10
    println(deque.getSize())      // 1
}

