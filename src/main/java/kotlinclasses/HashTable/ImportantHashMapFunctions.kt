/*
 *
 *  * ImportantHashMapFunctions.kt
 *  *
 *  * Created by Rafsan Ahmad on 04/23/26, 12:48 PM
 *  * Copyright (c) 2026. All rights reserved.
 *
 */

package kotlinclasses.HashTable

/*computeIfPresent vs getOrPut

This is a very important distinction

getOrPut
map.getOrPut("x") { 10 }

Used when:

You want to initialize if missing
Doesn't touch existing values

computeIfPresent
map.computeIfPresent("x") { key, value -> value + 1 }

Used when:
You want to update ONLY if key exists
Does NOTHING if missing
*/
fun main() {
    val map = mutableMapOf<String, Int>()

    // put / []
    map["apple"] = 1
    map.put("banana", 2)

    println(map) // {apple=1, banana=2}

    // get
    println(map["apple"]) // 1
    println(map.get("orange")) // null

    // getOrDefault
    println(map.getOrDefault("orange", 0)) // 0

    // getOrPut (IMPORTANT)
    val value1 = map.getOrPut("orange") { 10 }
    println(value1) // 10
    println(map) // {apple=1, banana=2, orange=10}

    // getOrPut when key exists
    val value2 = map.getOrPut("apple") { 99 }
    println(value2) // 1 (NOT 99)

    // contains
    println(map.containsKey("banana")) // true
    println(map.containsValue(2)) // true

    // putIfAbsent
    map.putIfAbsent("banana", 100)
    println(map["banana"]) // 2 (unchanged)

    // replace
    map.replace("banana", 20)
    println(map["banana"]) // 20

    // replace with condition
    map.replace("banana", 20, 200)
    println(map["banana"]) // 200

    // remove
    map.remove("apple")
    println(map) // {banana=200, orange=10}

    // remove with condition
    map.remove("banana", 100)
    println(map) // still {banana=200, orange=10}

    // iteration
    for ((key, value) in map) {
        println("$key -> $value")
    }


    ///GetOrPut VS computeIfAbsent
    val lMap = LinkedHashMap<String, Int>()

    lMap["apple"] = 1
    lMap["banana"] = 2

    println(lMap) // {apple=1, banana=2}

    // getOrPut (Kotlin style)
    val v1 = lMap.getOrPut("orange") { 10 }
    println(v1) // 10
    println(lMap) // {apple=1, banana=2, orange=10}

    // getOrPut when exists
    val v2 = lMap.getOrPut("apple") { 99 }
    println(v2) // 1 (NOT 99)

    // computeIfAbsent (Java style in Kotlin)
    val v3 = lMap.computeIfAbsent("grape") { key ->
        println("computing for $key")
        50
    }
    println(v3) // 50
    println(lMap) // {apple=1, banana=2, orange=10, grape=50}

    // computeIfAbsent when exists
    val v4 = lMap.computeIfAbsent("apple") { 999 }
    println(v4) // 1 (NOT 999)

    // computeIfPresent
    lMap.computeIfPresent("banana") { key, value ->
        value + 100
    }
    println(map["banana"]) // 102

    // computeIfPresent when key missing
    lMap.computeIfPresent("unknown") { _, v -> v + 1 }
    println(lMap.containsKey("unknown")) // false

    // compute (always runs)
    lMap.compute("banana") { key, value ->
        if (value == null) 0 else value * 2
    }
    println(lMap["banana"]) // 204

    // remove using compute (return null = delete)
    lMap.compute("apple") { _, _ -> null }
    println(lMap.containsKey("apple")) // false
}