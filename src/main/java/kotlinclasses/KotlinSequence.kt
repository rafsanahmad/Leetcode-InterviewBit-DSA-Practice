/*
 * *
 *  * Created by Rafsan Ahmad on 10/25/21, 9:50 PM
 *  * Copyright (c) 2021 . All rights reserved.
 *
 */

package kotlinclasses

class KotlinSequence {
    /* Sequences are very similar to lists. The difference between a list and sequence is that the sequences are
    lazily evaluated.*/

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
                it.toUpperCase()

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
                it.toUpperCase()

            }
            .take(2)
            .toList()
        println("Size: ${result.size}")
    }
}

fun main(args: Array<String>) {
    val obj = KotlinSequence()
    println("Without Sequence:")
    obj.withoutSequence()
    println("With Sequence:")
    obj.withSequence()
}