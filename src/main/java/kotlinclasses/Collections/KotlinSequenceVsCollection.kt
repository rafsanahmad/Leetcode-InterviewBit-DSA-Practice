/*
 * *
 *  * Kotlin Sequence Vs Collection.kt
 *  * Created by Rafsan Ahmad on 3/15/25, 7:49PM
 *  * Copyright (c) 2025 . All rights reserved.
 *  *
 *
 */

package kotlinclasses.Collections

class KotlinSequenceVsCollection {
    /* Sequences are very similar to lists. The difference between a list and sequence is that the sequences are
    lazily evaluated.*/

    /*When to Use What
✅ Use Collection when:
You work with small/medium datasets.
You want straightforward, readable code.
Performance is not a bottleneck.

✅ Use Sequence when:
You're chaining many operations (map, filter, etc.).
You have large datasets or expensive operations.
You want to avoid intermediate lists being created.
*/

    //Create Sequence
    fun createSequence() {
        //1 — From elements — By calling sequenceOf()
        val numbersSequence = sequenceOf("four", "three", "two", "one")

        //2- From iterable objects — By calling asSequence() function of an iterable object such as a List or Set.
        val numbers = listOf("one", "two", "three", "four")
        val numbersSequence2 = numbers.asSequence()

        //We can create a sequence by calling asSequence() function of an iterable object such as a List or Set.
        //3-From Chunks — sequence {}, yield, yieldAll()
        val seqFromChunks = sequence {
            yield(1)
            yieldAll((2..5).toList())
            yield(6)
        }
    }

    fun withoutSequence() {
        val result = listOf("a", "b", "ac", "d", "e", "f", "g", "h", "i", "j", "ak")
            .filter {
                println("filter: $it")
                it.startsWith("a", ignoreCase = true)
            }
            .map {
                println("map: $it")
                it.uppercase()

            }
            .take(2)
            .toList()
        println("Size: ${result.size}")
    }

    fun withSequence() {
        val result = listOf("a", "b", "ac", "d", "e", "f", "g", "h", "i", "j", "ak")
            .asSequence()
            .filter {
                println("filter: $it")
                it.startsWith("a", ignoreCase = true)
            }
            .map {
                println("map: $it")
                it.uppercase()

            }
            .take(2)
            .toList()
        println("Size: ${result.size}")
    }
}

fun main(args: Array<String>) {
    val obj = KotlinSequenceVsCollection()
    println("Without Sequence:")
    /*Without Sequence:
filter: a
filter: b
filter: ac
filter: d
filter: e
filter: f
filter: g
filter: h
filter: i
filter: j
filter: ak
map: a
map: ac
map: ak
Size: 2
*/
    obj.withoutSequence()
    println("With Sequence:")
    /*With Sequence:
filter: a
map: a
filter: b
filter: ac
map: ac
Size: 2
*/
    obj.withSequence()

    val list = listOf(1, 2, 3, 4, 5)

    println("Using Collection:")
    /*Using Collection:
map: 1
map: 2
map: 3
map: 4
map: 5
filter: 2
filter: 4
filter: 6
filter: 8
filter: 10
Result: [6, 8, 10]
*/
    val collectionResult = list
        .map {
            println("map: $it")
            it * 2
        }
        .filter {
            println("filter: $it")
            it > 5
        }
    println("Result: $collectionResult")

    println("\nUsing Sequence:")
    /*Using Sequence:
map: 1
filter: 2
map: 2
filter: 4
map: 3
filter: 6
map: 4
filter: 8
map: 5
filter: 10
Result: [6, 8, 10]
*/
    val sequenceResult = list.asSequence()
        .map {
            println("map: $it")
            it * 2
        }
        .filter {
            println("filter: $it")
            it > 5
        }
        .toList() // Terminal operation triggers the sequence
    println("Result: $sequenceResult")
}