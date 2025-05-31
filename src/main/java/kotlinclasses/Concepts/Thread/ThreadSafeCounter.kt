/*
 * *
 *  * Thread Safe Counter.kt
 *  * Created by Rafsan Ahmad on 6/1/25, 1:39AM
 *  * Copyright (c) 2025 . All rights reserved.
 *  *
 *
 */

package kotlinclasses.Concepts.Thread

import java.util.concurrent.atomic.AtomicInteger
import java.util.concurrent.locks.ReentrantLock
import kotlin.concurrent.withLock
import kotlinx.coroutines.*
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

interface Counter {
    fun increment()
    fun get(): Int
}

class UnsafeCounter : Counter {
    var count = 0

    override fun increment() {
        count++
    }

    override fun get(): Int = count
}

//@Volatile + synchronized block
/*
Benefits:
Simple to implement.
Good for low-contention scenarios.
@Volatile ensures visibility across threads.
*/
class SynchronizedCounter : Counter {
    @Volatile
    private var count = 0

    override fun increment() {
        synchronized(this) {
            count++
        }
    }

    override fun get(): Int = count
}

//AtomicInteger
/*Benefits:
Lock-free and faster in multi-threaded environments.
Best for high-concurrency.
*/
class AtomicCounter : Counter {
    private val count = AtomicInteger(0)

    override fun increment() {
        count.incrementAndGet()
    }

    override fun get(): Int = count.get()
}

//ReentrantLock
/*
* Benefits:
More flexible than synchronized (e.g., tryLock, interruptible).
Fine-grained control over locking.
*/
class ReentrantLockCounter : Counter {
    private var count = 0
    private val lock = ReentrantLock()

    override fun increment() {
        lock.withLock {
            count++
        }
    }

    override fun get(): Int = lock.withLock { count }
}

//Using Kotlin Coroutines + Mutex
/*Benefits:
Non-blocking (suspending instead of locking).
Ideal for coroutine-based architectures (like Android UI + ViewModel).
*/
class MutexCounter : Counter {
    private var count = 0
    private val mutex = Mutex()

    suspend fun incrementSuspend() {
        mutex.withLock {
            count++
        }
    }

    override fun increment() {
        throw UnsupportedOperationException("Use incrementSuspend() instead")
    }

    override fun get(): Int = count
}

fun runCounterTest(counter: Counter, label: String) {
    val threads = mutableListOf<Thread>()

    repeat(100) {
        val t = Thread {
            repeat(100) {
                counter.increment()
            }
        }
        threads.add(t)
        t.start()
    }

    threads.forEach { it.join() }
    println("$label result: ${counter.get()}")
}


suspend fun runMutexCounterTest(counter: MutexCounter, label: String) {
    val jobs = List(100) {
        GlobalScope.launch {
            repeat(100) {
                counter.incrementSuspend()
            }
        }
    }
    jobs.forEach { it.join() }
    println("$label result: ${counter.get()}")
}

fun main() = runBlocking {
    /*Output:
    * UnsafeCounter result: 9920
    * SynchronizedCounter result: 10000
    * AtomicCounter result: 10000
    * ReentrantLockCounter result: 10000
    * MutexCounter result: 10000
    * */
    runCounterTest(UnsafeCounter(), "UnsafeCounter")
    runCounterTest(SynchronizedCounter(), "SynchronizedCounter")
    runCounterTest(AtomicCounter(), "AtomicCounter")
    runCounterTest(ReentrantLockCounter(), "ReentrantLockCounter")
    runMutexCounterTest(MutexCounter(), "MutexCounter")
}