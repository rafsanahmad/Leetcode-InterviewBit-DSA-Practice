/*
 * *
 *  * Map Values.kt
 *  * Created by Rafsan Ahmad on 9/29/25, 2:04PM
 *  * Copyright (c) 2025 . All rights reserved.
 *  *
 *
 */

package kotlinclasses.Collections

class MapValues {
    /*In Kotlin, mapValues is a handy function that transforms the values of a Map, while keeping
    the keys unchanged.
    Syntax:
val newMap = oldMap.mapValues { (key, value) -> transform(value) }
Keys stay the same.
Only values are modified.
    */
}

fun main() {
    val numbers = mapOf(1 to 2, 2 to 3, 3 to 4)
    // Square each value
    val squared = numbers.mapValues { (_, v) -> v * v }
    println(squared) // {1=4, 2=9, 3=16}

    //Convert values to uppercase
    val names = mapOf(1 to "alice", 2 to "bob", 3 to "charlie")
    val upper = names.mapValues { it.value.uppercase() }
    println(upper) // {1=ALICE, 2=BOB, 3=CHARLIE}

    //Example 3: With groupingBy
    val words = listOf("apple", "apricot", "banana", "blueberry", "cherry")
    val grouped = words.groupBy { it.first() }
    // grouped = {a=[apple, apricot], b=[banana, blueberry], c=[cherry]}
    // Count how many words per letter
    val counts = grouped.mapValues { it.value.size }
    println(counts) // {a=2, b=2, c=1}

    //Example 4: Replace with computed objects
    data class Employee(val name: String, val salary: Double)

    val employees = listOf(
        Employee("Alice", 5000.0),
        Employee("Bob", 7000.0),
        Employee("Charlie", 6000.0)
    )

    // Map name -> salary with a 10% bonus
    val salaryMap = employees.associateBy { it.name }
        .mapValues { (_, emp) -> emp.salary * 1.1 }

    println(salaryMap)
}