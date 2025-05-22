/*
 * *
 *  * Kotlin Flow Operators.kt
 *  * Created by Rafsan Ahmad on 5/22/25, 2:16PM
 *  * Copyright (c) 2025 . All rights reserved.
 *  *
 *
 */

package kotlinclasses.Concepts.Flow

import kotlinx.coroutines.flow.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

class KotlinFlowOperators {
    /*1. merge
Emits values from multiple flows as they come.
Order is not guaranteed.
Think of it as interleaving emissions from both flows.

2. zip
Pairs elements from two flows in order.
Waits for both flows to emit before combining.
Stops when either flow completes.

3. combine
Combines the latest value from each flow whenever either emits.
Waits for all flows to emit at least once, then updates on any emission.
*/
}

fun main() = runBlocking {
    val flow1 = flow {
        emit(1)
        delay(100)
        emit(2)
    }
    val flow2 = flow {
        emit(10)
        delay(50)
        emit(20)
    }

    merge(flow1, flow2).collect {
        /*
        1
        10
        20
        2*/
        println(it)
    }

    val flow3 = flowOf(1, 2, 3)
    val flow4 = flowOf("A", "B")

    flow3.zip(flow4) { a, b -> "$a-$b" }.collect {
        /*
        1-A
        2-B
        */
        //Note: 3 from flow1 is not emitted, since flow2 only has 2 elements.
        println(it)
    }

    val flow5 = flow {
        emit(1)
        delay(100)
        emit(2)
    }
    val flow6 = flow {
        delay(50)
        emit("A")
        delay(100)
        emit("B")
    }

    flow5.combine(flow6) { a, b -> "$a-$b" }.collect {
        /*
        1-A
        2-A
        2-B
        */
        println(it)
    }
}