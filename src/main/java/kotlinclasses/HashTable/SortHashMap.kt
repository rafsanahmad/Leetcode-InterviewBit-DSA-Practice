/*
 * *
 *  * Sort HashMap.kt
 *  * Created by Rafsan Ahmad on 10/9/22, 9:35 PM
 *  * Copyright (c) 2022 . All rights reserved.
 *
 */

package kotlinclasses.HashTable

import java.util.*

class MapWithCustomComparator {

    companion object : Comparator<Map.Entry<String, Int>> {
        override fun compare(a: Map.Entry<String, Int>, b: Map.Entry<String, Int>): Int = when {
            a.value == b.value -> a.key.compareTo(b.key)
            else -> {
                b.value - a.value
            }
        }
    }
}

fun main(args: Array<String>) {

    val capitals = hashMapOf<String, String>()
    capitals.put("Nepal", "Kathmandu")
    capitals.put("India", "New Delhi")
    capitals.put("United States", "Washington")
    capitals.put("England", "London")
    capitals.put("Australia", "Canberra")

    //Sort Map By Values
    println("Sort by Value ascending")
    val result = capitals.toList().sortedBy { (_, value) -> value }.toMap()

    for (entry in result) {
        print("Key: " + entry.key)
        println(" Value: " + entry.value)
    }

    //Sort By Descending order of Values
    println("\nSort by Value Descending")
    val sortedMap = capitals.toSortedMap(compareByDescending { it })
    for (entry in sortedMap) {
        print("Key: " + entry.key)
        println(" Value: " + entry.value)
    }

    //Sort by Keys
    println("\nSort by Keys")
    val map: MutableMap<String, String> = HashMap()

    map["UNITED STATES"] = "WASHINGTON, D.C."
    map["UNITED KINGDOM"] = "LONDON"
    map["ITALY"] = "ROME"
    map["SPAIN"] = "MADRID"

    val sortedMap2: MutableMap<String, String> = LinkedHashMap()
    map.entries.sortedBy { it.key }.forEach { sortedMap2[it.key] = it.value }

    println(sortedMap2)

    //Sort Using custom comparator
    println("\nSort using custom comparator")
    val map2: MutableMap<String, Int> = mutableMapOf()

    map2["C"] = 4
    map2["A"] = 10
    map2["B"] = 50
    map2["D"] = 14

    var list: List<Map.Entry<String, Int>> = LinkedList(map2.entries)
    // Sort the list using Descending order of value & if value are equal sort by Key alphabetically
    //list.sortedWith(MapWithCustomComparator)
    list = list
        .sortedWith<Map.Entry<String, Int>>(object : Comparator<Map.Entry<String, Int>> {
            override fun compare(p0: Map.Entry<String, Int>, p1: Map.Entry<String, Int>): Int {
                if (p0.value == p1.value) {
                    return p0.key.compareTo(p1.key)
                }
                return p1.value - p0.value
            }
        })

    //prints the sorted HashMap
    println(list)
}