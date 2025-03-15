/*
 * *
 *  * KotlinFlowEx.kt
 *  * Created by Rafsan Ahmad on 3/15/25, 7:49PM
 *  * Copyright (c) 2025 . All rights reserved.
 *  *
 *
 */

package kotlinclasses.Concepts

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class KotlinFlowEx {

    fun testFlow() {
        val flow = flow {
            for (i in 1..10) {
                emit(i)
                delay(1000L)
            }
        }

        val job = GlobalScope.launch {
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
    }
}

fun main(args: Array<String>) {
    val ex = KotlinFlowEx()
    ex.testFlow()
}