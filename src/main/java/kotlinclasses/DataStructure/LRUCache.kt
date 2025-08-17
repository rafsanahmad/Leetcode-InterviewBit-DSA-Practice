/*
 * *
 *  * LRU Cache.kt
 *  * Created by Rafsan Ahmad on 3/15/25, 7:56PM
 *  * Copyright (c) 2025 . All rights reserved.
 *  *
 *
 */

package kotlinclasses.DataStructure

class LRUCache(capacity: Int) {
    //https://leetcode.com/problems/lru-cache/description/
    class Node(var key: Int, var value: Int) {
        var prev: Node? = null
        var next: Node? = null
    }

    val map: MutableMap<Int, Node> = mutableMapOf() // Stores key to Node mapping for O(1) access
    var head: Node? = null  // Most recently used node
    var tail: Node? = null  // Least recently used node
    val cap = capacity      // Capacity limit of cache

    //Time Complexity: O(1)
    fun get(key: Int): Int {
        map[key]?.let {
            deleteNode(it)    // Remove node from its current position in the list
            insertNode(it)    // Insert node at head (mark as most recently used)
            return it.value   // Return the value associated with the key
        }
        return -1             // Key not found
    }

    //Time Complexity: O(1)
    fun put(key: Int, value: Int) {
        map[key]?.let {
            it.value = value  // Update existing node's value
            deleteNode(it)    // Remove node from its current position
            insertNode(it)    // Insert node at head (MRU)
            return
        }

        if (map.size >= cap) {
            tail?.let {
                map.remove(it.key)  // Remove least recently used key from map
                deleteNode(it)      // Remove least recently used node from list
            }
        }

        val newNode = Node(key, value) // Create new node for new key-value
        map[key] = newNode             // Add to map for O(1) access
        insertNode(newNode)            // Insert at head (most recently used)
    }

    //Time Complexity: O(1)
    fun insertNode(node: Node) {
        if (head == null) {
            head = node              // If list empty, new node becomes head
        } else {
            head?.prev = node       // Link current head prev to new node
            node.next = head        // New node points next to current head
            head = node             // Update head to new node
        }
        if (tail == null)
            tail = node             // If list was empty, tail is also new node
        node.prev = null            // IMPORTANT: New head always has null prev
    }

    //Time Complexity: O(1)
    fun deleteNode(node: Node) {
        if (node.prev == null) {
            // Node is head
            head = node.next
        } else {
            node.prev?.next = node.next // Link previous node's next to node's next
        }

        if (node.next == null) {
            // Node is tail
            tail = node.prev
        } else {
            node.next?.prev = node.prev // Link next node's prev to node's prev
        }
    }
}

fun main() {
    val lru = LRUCache(2)  // capacity 2

    lru.put(1, 1)          // cache = {1=1}
    lru.put(2, 2)          // cache = {1=1, 2=2}

    println(lru.get(1))    // returns 1, cache = {2=2, 1=1}

    lru.put(3, 3)          // evicts key 2, cache = {1=1, 3=3}

    println(lru.get(2))    // returns -1 (not found)

    lru.put(4, 4)          // evicts key 1, cache = {3=3, 4=4}

    println(lru.get(1))    // returns -1 (not found)
    println(lru.get(3))    // returns 3
    println(lru.get(4))    // returns 4
}