/*
 * *
 *  * Created by Rafsan Ahmad on 10/25/21, 9:50 PM
 *  * Copyright (c) 2021 . All rights reserved.
 *
 */

package kotlinclasses

class KotlinSequence {

}

class Lambda : () -> Unit {
    override fun invoke() {
        println("Invoked from an instance")
    }

}

fun main(args: Array<String>) {
    val chars = ('a'..'z').toList()
    println(chars.drop(23)) // [x, y, z]
    println(chars.dropLast(23)) // [a, b, c]
    println(chars.dropWhile { it < 'x' }) // [x, y, z]
    println(chars.dropLastWhile { it > 'c' }) // [a, b, c]

    val numbers = (1..50).toList()
    println(numbers.drop(5).take(10).sortedDescending().toList())

    try {
        Lambda()()
    } catch (ex: Exception) {
        println("An error occurred")
    }
}