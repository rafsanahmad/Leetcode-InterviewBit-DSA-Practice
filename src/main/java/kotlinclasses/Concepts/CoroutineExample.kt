/*
 * *
 *  * CoroutineExample.kt
 *  * Created by Rafsan Ahmad on 3/15/25, 7:47PM
 *  * Copyright (c) 2025 . All rights reserved.
 *  *
 *
 */

package kotlinclasses.Concepts

import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

class CoroutineExample {
    fun meaninglessCounter(): Int {
        var counter = 0
        for (i in 1..10_000_000_000) {
            counter += 1
        }
        return counter
    }

    fun coroutineTest() {
        println("0")

        runBlocking {
            val job = GlobalScope.launch {
                // launch new coroutine and keep a reference to its Job
                delay(1200L)
                println("1")
            }
            val job2 = GlobalScope.launch {
                // launch new coroutine and keep a reference to its Job
                delay(1000L)
                println("2")
            }

            println("3")
            job.join() // wait until child coroutine completes
            println("4")
            job2.join() // wait until child coroutine
            println("5")
        }
        println("6")
    }

    fun coroutineLaunch() {
        var resultOne = "Android"
        var resultTwo = "Kotlin"
        println("Launch Before")
        GlobalScope.launch(Dispatchers.IO) { resultOne = function1() }
        GlobalScope.launch(Dispatchers.IO) { resultTwo = function2() }
        println("Launch After")
        val resultText = resultOne + resultTwo
        println("Launch {$resultText}")
    }

    suspend fun function1(): String {
        delay(1000L)
        val message = "function1"
        println("Launch {$message}")
        return message
    }

    suspend fun function2(): String {
        delay(100L)
        val message = "function2"
        println("Launch {$message}")
        return message
    }

    suspend fun coroutineAsync() {
        println("Async Before")
        val resultOne = GlobalScope.async(Dispatchers.IO) { function3() }
        val resultTwo = GlobalScope.async(Dispatchers.IO) { function4() }
        println("Async After")
        val resultText = resultOne.await() + resultTwo.await()
        println("Async {$resultText}")
    }

    suspend fun function3(): String {
        delay(1000L)
        val message = "function3"
        println("Async {$message}")
        return message
    }

    suspend fun function4(): String {
        delay(100L)
        val message = "function4"
        println("Async {$message}")
        return message
    }

    suspend fun function5() {
        GlobalScope.launch {
            delay(1000)
            val sum1 = async {
                8 % 2
            }

            val sum2 = async { 6 / 2 }
            println("Calculating sum")
            val total = sum1.await() + sum2.await()
            println("Sum is $total")
        }

        delay(2000)
        println("I am here")
    }
}

fun main(args: Array<String>) = runBlocking {
    // Sequential execution.
    var time = measureTimeMillis {
        val one = CoroutineExample().meaninglessCounter()
        val two = CoroutineExample().meaninglessCounter()
        println("The answer is ${one + two}")
    }
    println("Sequential completed in $time ms")

    // Concurrent execution.
    var time2 = measureTimeMillis {
        val one = async { CoroutineExample().meaninglessCounter() }
        val two = async { CoroutineExample().meaninglessCounter() }
        runBlocking {
            println("The answer is ${one.await() + two.await()}")
        }
    }
    println("Concurrent completed in $time2 ms\n")

    val example = CoroutineExample()
    example.coroutineTest()
    //Launch
    example.coroutineLaunch()
    //Async
    val job = GlobalScope.launch {
        example.coroutineAsync()
        example.function5()
    }
    job.join() // Wait for the coroutine to finish
}