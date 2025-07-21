/*
 * *
 *  * Coroutine Output.kt
 *  * Created by Rafsan Ahmad on 7/21/25, 12:15PM
 *  * Copyright (c) 2025 . All rights reserved.
 *  *
 *
 */

package kotlinclasses.OutputQuiz

import kotlinx.coroutines.*

class CoroutineOutput {
    fun coroutineLaunchParallel(): Job {
        val customScope = CoroutineScope(Dispatchers.Default)
        return customScope.launch {
            launch {
                delay(1000)
                printAll("Case1", "Coroutine A done")
            }
            launch {
                delay(500)
                printAll("Case1", "Coroutine B done")
            }
            printAll("Case1", "Parent coroutine done")
        }
    }

    fun coroutineLaunchSerial(): Job {
        val customScope = CoroutineScope(Dispatchers.Default)
        return customScope.launch {
            launch {
                printAll("Case2", "Start A")
                delay(1000)
                printAll("Case2", "Done A")

                launch {
                    printAll("Case2", "Start B")
                    delay(500)
                    printAll("Case2", "Done B")
                }
            }
        }
    }

    fun coroutineLaunchSupervisorScope(): Job {
        val customScope = CoroutineScope(Dispatchers.Default)
        return customScope.launch {
            try {
                supervisorScope {
                    launch {
                        delay(500)
                        throw Exception("Child A failed")
                    }

                    launch {
                        delay(1000)
                        printAll("SupervisorScope", "Child B still running")
                    }
                }
            } catch (e: Exception) {
                printAll("SupervisorScope", "Caught error: ${e.message}")
            }
        }
    }

    fun printAll(tag: String, message: String) {
        println("[$tag] $message - ${Thread.currentThread().name}")
    }
}

fun main() = runBlocking {
    val obj = CoroutineOutput()
    val job1 = obj.coroutineLaunchParallel()
    val job2 = obj.coroutineLaunchSerial()
    val job3 = obj.coroutineLaunchSupervisorScope()

    //Wait for all jobs to end
    job1.join()
    job2.join()
    job3.join()
    /*Output:
    [Case2] Start A - DefaultDispatcher-worker-5
    [Case1] Parent coroutine done - DefaultDispatcher-worker-1
    [Case1] Coroutine B done - DefaultDispatcher-worker-8
    Exception in thread "DefaultDispatcher-worker-1" java.lang.Exception: Child A failed
    [Case2] Done A - DefaultDispatcher-worker-8
    [Case1] Coroutine A done - DefaultDispatcher-worker-1
    [SupervisorScope] Child B still running - DefaultDispatcher-worker-2
    [Case2] Start B - DefaultDispatcher-worker-8
    [Case2] Done B - DefaultDispatcher-worker-1
    */
}