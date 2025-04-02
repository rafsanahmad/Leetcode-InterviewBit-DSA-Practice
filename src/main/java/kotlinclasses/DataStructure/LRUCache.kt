/*
 * *
 *  * LRU Cache.kt
 *  * Created by Rafsan Ahmad on 3/15/25, 7:56PM
 *  * Copyright (c) 2025 . All rights reserved.
 *  *
 *
 */

package kotlinclasses.DataStructure

class LRUCache {
    class Node(var key: Int, var value: Int) {
        var prev: Node? = null
        var next: Node? = null
    }

    var head: Node? = null
    var tail: Node? = null
    var map = HashMap<Int, Node>()

    fun solution(n: Array<String>): Array<String> {
        val result = ArrayList<String>()
        if (n.isEmpty()) {
            return result.toTypedArray()
        }
        for (i in 0..<n.size) {
            val values = n[i].split(" ")
            val command = n[i].split(" ")[0]
            when (command) {
                "add" -> {
                    put(values[1].toInt(), values[2].toInt())
                }

                "get" -> {
                    val value = getValue(values[1].toInt())
                    result.add(value.toString())
                }

                "remove" -> {
                    val value = removeValue(values[1].toInt())
                    result.add(value.toString())
                }

                "exit" -> {
                    return result.toTypedArray()
                }

                "evict" -> {
                    removeEldestEntry()
                }
            }
        }
        return result.toTypedArray()
    }

    fun put(key: Int, value: Int) {
        if (map.containsKey(key)) {
            val node = map[key]
            node?.let {
                it.value = value
                //move to tail
                removeNode(it)
                insertNode(it)
            }
        } else {
            //Create new node and add to tail
            val newNode = Node(key, value)
            insertNode(newNode)
            map[key] = newNode
        }
    }

    fun getValue(key: Int): Int {
        map[key]?.let {
            removeNode(it)
            insertNode(it)
            return it.value
        }
        return -1
    }

    fun removeNode(node: Node) {
        if (node.prev != null) {
            node.prev?.next = node.next
        } else {
            //Head is remove node
            //update head
            head = node.next
        }
        if (node.next != null) {
            node.next?.prev = node.prev
        } else {
            //Tail is remove node
            //Update tail
            tail = node.prev
        }
    }

    fun insertNode(node: Node) {
        tail?.next = node
        node.prev = tail
        node.next = null
        tail = node
        if (head == null) {
            head = node
        }
    }

    fun removeValue(key: Int): Int {
        map[key]?.let {
            removeNode(it)
            return it.value
        }
        return -1
    }

    fun removeEldestEntry() {
        //delete head
        head?.let {
            map.remove(it.key)
            removeNode(it)
        }
    }
}

fun main(args: Array<String>) {
    var str = arrayOf("add 5 3", "add 1 2", "get 5", "evict", "get 1", "remove 5", "exit");
    val kt = LRUCache()
    val result = kt.solution(str)
    for (i in 0..<result.size) {
        println(result[i]);
    }
}