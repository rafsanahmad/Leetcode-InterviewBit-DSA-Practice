/*
 * *
 *  * Created by Rafsan Ahmad on 10/25/21, 9:50 PM
 *  * Copyright (c) 2021 . All rights reserved.
 *
 */

package kotlinclasses

class LRUCache {
    class Node(var key: Int, var value: Int) {
        var prev: Node? = null
        var next: Node? = null
    }

    var head: Node? = null
    var tail: Node? = null
    var map: HashMap<Int, Node> = HashMap()

    fun solution(n: Array<String>): Array<String> {
        val result = ArrayList<String>()
        if (n.size == 0) {
            return result.toTypedArray()
        }
        for (i in 0..n.size) {
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
            val t = map[key]
            t?.value = value
            //move to tail
            if (t != null) {
                removeNode(t)
            }
            if (t != null) {
                insertNode(t)
            }
        } else {
            //add to tail
            val node = Node(key, value)
            insertNode(node)
            map[key] = node
        }
    }

    fun getValue(key: Int): Int {
        if (map[key] == null) {
            return -1
        }
        //mode to tail
        val t = map[key]
        if (t != null) {
            removeNode(t)
        }
        if (t != null) {
            insertNode(t)
        }
        if (t != null) {
            return t.value
        } else {
            return -1
        }
    }

    fun removeNode(n: Node) {
        if (n.prev != null) {
            n.prev?.next = n.next
        } else {
            head = n.next
        }
        if (n.next != null) {
            n.next?.prev = n.prev
        } else {
            tail = n.prev
        }

    }

    fun insertNode(n: Node) {
        if (tail != null) {
            tail?.next = n
        }
        n.prev = tail
        n.next = null
        tail = n
        if (head == null) {
            head = tail
        }
    }

    fun removeValue(key: Int): Int {
        if (map[key] == null) {
            return -1
        }
        val node = map[key]
        if (node != null) {
            removeNode(node)
            return node.value
        } else {
            return -1
        }
    }

    fun removeEldestEntry() {
        //delete head
        if (head != null) {
            map.remove(head?.key)
            removeNode(head!!)
        }
    }
}

fun main(args: Array<String>) {
    var str = arrayOf("add 5 3", "add 1 2", "get 5", "evict", "get 1", "remove 5", "exit");
    val kt = LRUCache()
    val result = kt.solution(str)
    for (i in 0..result.size - 1) {
        System.out.println(result[i]);
    }
}