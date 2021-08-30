package kotlinclasses

import kotlin.system.measureTimeMillis
import kotlinx.coroutines.*
import kotlinx.coroutines.android.*

class CoroutineExample {
    fun meaninglessCounter(): Int {
        var counter = 0
        for (i in 1..10_000_000_000) {
            counter += 1
        }
        return counter
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