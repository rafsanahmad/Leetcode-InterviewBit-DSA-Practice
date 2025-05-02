/*
 * *
 *  * Kotlin Flow Coroutine Example.kt
 *  * Created by Rafsan Ahmad on 3/15/25, 7:49PM
 *  * Copyright (c) 2025 . All rights reserved.
 *  *
 *
 */

package kotlinclasses.Concepts

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class KotlinFlowCoroutineEx {

    //runBlocking ensures testFlow() completes before main() exits.
    fun testFlow() = runBlocking {
        val flow = flow {
            for (i in 1..10) {
                emit(i)
                delay(1000L)
            }
        }

        //Buffer is used in case of BackPressure
        flow.buffer().filter {
            it % 2 == 0
        }.map {
            it * it
        }.collect {
            println(it)
            //Prints 4 16 36 64 100
            delay(2000L)
        }
        println("Executed")
    }


    //Return a Job and join() It, Ensures testFlow2() finishes before main() exits.
    fun testFlow2(): Job {
        val flow = flow {
            for (i in 11..20) {
                emit(i)
                delay(1000L)
            }
        }

        return GlobalScope.launch {
            flow.buffer().filter {
                it % 2 == 0
            }.map {
                it * it
            }.collect {
                println(it)
                delay(2000L)
            }
            println("Executed")
        }
    }

    //Pass a Coroutine Scope from main
    //Instead of using GlobalScope, allow testFlow() to accept a coroutine scope.
    //Best approach
    suspend fun testFlow3(scope: CoroutineScope) {
        val flow = flow {
            for (i in 21..30) {
                emit(i)
                delay(1000L)
            }
        }

        scope.launch {
            flow.buffer().filter {
                it % 2 == 0
            }.map {
                it * it
            }.collect {
                println(it)
                delay(2000L)
            }
            println("Executed")
        }.join() // Ensure completion before returning
    }
}

fun main(args: Array<String>) = runBlocking {
/*
Output:
4
16
36
64
100
Executed
144
196
256
324
400
Executed
484
576
676
784
900
Executed
 */
    val ex = KotlinFlowCoroutineEx()
    ex.testFlow()

    val job = ex.testFlow2()
    job.join()  // Waits for the coroutine to complete

    ex.testFlow3(this) // Uses `runBlocking` scope

    /*
    Start 1
    Start 2
    Start 3
    End 3*/

    //collectLatest cancels the previous block if a new value arrives.
    val flow = flowOf(1, 2, 3)
    flow
        .onEach { delay(90) }
        .collectLatest { value ->
            println("Start $value")
            delay(100)
            println("End $value")
        }
}