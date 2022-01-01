/*
 * *
 *  * Created by Rafsan Ahmad on 10/25/21, 9:50 PM
 *  * Copyright (c) 2021 . All rights reserved.
 *
 */

package kotlinclasses

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
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
}

fun main(args: Array<String>) {
    // Sequential execution.
    var time = measureTimeMillis {
        val one = CoroutineExample().meaninglessCounter()
        val two = CoroutineExample().meaninglessCounter()
        println("The answer is ${one + two}")
    }
    println("Sequential completed in $time ms")

    val example = CoroutineExample()
    example.coroutineTest()
    // Concurrent execution.
    /*var time2 = measureTimeMillis {
        val one = async { CoroutineExample().meaninglessCounter() }
        val two = async { CoroutineExample().meaninglessCounter() }
        runBlocking {
            println("The answer is ${one.await() + two.await()}")
        }
    }
    println("Concurrent completed in $time2 ms\n")*/
}