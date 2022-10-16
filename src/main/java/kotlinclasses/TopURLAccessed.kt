/*
 * *
 *  * TopURLAccessed.kt
 *  * Created by Rafsan Ahmad on 10/9/22, 9:24 PM
 *  * Copyright (c) 2022 . All rights reserved.
 *
 */

package kotlinclasses

import java.util.*

class TopURLAccessed {
    /*Given n find the top n URLs accessed with 200 status code
    Log format: "10.10.0.2 200 https://www.google.com"
    Format: IP, StatusCode, URL
    Data is separated by single space
    If equal number of URLs accessed - sort them alphabetically & return the String Array
    */
    fun solution(n: Int, logs: Array<String>): Array<String> {
        // write your code in Kotlin
        val result: MutableList<String> = mutableListOf()
        val countMap: MutableMap<String, Int> = mutableMapOf()
        for (i in 0 until logs.size) {
            val logsSplitted = logs[i].split(" ")
            if (logsSplitted.get(1) == "200") {
                //Add to map
                val url = logsSplitted.get(2)
                countMap[url] = countMap.getOrDefault(url, 0) + 1
            }
        }
        // Create a list from elements of HashMap
        var list: List<Map.Entry<String, Int>> = LinkedList(countMap.entries)
        // Sort the list
        list = list
            .sortedWith<Map.Entry<String, Int>>(object : Comparator<Map.Entry<String, Int>> {
                override fun compare(p0: Map.Entry<String, Int>, p1: Map.Entry<String, Int>): Int {
                    if (p0.value == p1.value) {
                        return p0.key.compareTo(p1.key)
                    }
                    return p1.value - p0.value
                }
            })

        var total = n
        for (i in list.indices) {
            if (total > 0) {
                val mapEntry = list[i]
                result.add(mapEntry.key)
                total--
            }
        }

        return result.toTypedArray()
    }
}