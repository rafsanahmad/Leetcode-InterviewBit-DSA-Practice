/*
 * *
 *  * CustomHashMap.kt
 *  * Created by Rafsan Ahmad on 3/15/25, 7:48PM
 *  * Copyright (c) 2025 . All rights reserved.
 *  *
 *
 */

package kotlinclasses.Hashing

import kotlin.math.abs

private const val size = 1024

private class Cache {
    private val table = arrayOfNulls<Entry>(size)

    class Entry(val key: String, var value: String) {
        var next: Entry? = null
        fun getEntryValue(): String {
            return value
        }

        fun setEntryValue(value: String) {
            this.value = value
        }
    }

    fun add(k: String, v: String): String {
        val hash = abs(k.hashCode() % size)
        var e: Entry? = table.get(hash)

        if (e != null) {
            if (e.key == k) {
                e.value = v
            } else {
                //collision
                while (e!!.next != null) {
                    e = e.next
                }

                val oldEntry = Entry(k, v)
                e.next = oldEntry
            }
            return "overwritten"
        } else {
            //Create new entry
            val newEntry = Entry(k, v)
            table[hash] = newEntry
            return "added"
        }
    }

    fun get(k: String): String {
        val hash = Math.abs(k.hashCode() % size)
        var e: Entry? = table.get(hash)
        while (e != null) {
            if (e.key == k) return e.value
            e = e.next
        }

        return "miss"
    }

    fun size(): Int {
        var count = 0;
        for (i in 0..table.size - 1) {
            if (table[i] != null) count++
        }

        return count
    }
}

fun main(args: Array<String>) {
    val cache = Cache()
    // modifying the cache (keep this here)
    println(cache.add("article-123", "https://coderbyte.com/article-123"))
    println(cache.add("article-456", "https://coderbyte.com/article-456"))
    println(cache.add("how-to-code-444", "https://coderbyte.com/how-to-code-444"))
    println(cache.get("first-article"))
    println(cache.get("second-article"))
    println(cache.get("article-456"))
    println(cache.add("article-123", "https://coderbyte.com/article-123"))
    println(cache.size())
}