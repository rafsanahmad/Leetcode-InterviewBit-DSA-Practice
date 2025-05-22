/*
 * *
 *  * DeadlockPreventation.kt
 *  * Created by Rafsan Ahmad on 5/22/25, 2:15 PM
 *  * Copyright (c) 2025 . All rights reserved.
 *  *
 *
 */

package kotlinclasses.Concepts.Thread

import kotlinclasses.Concepts.Thread.DeadlockPreventation.Resource
import java.util.concurrent.locks.ReentrantLock

class DeadlockPreventation {
    class Resource {
        fun use() = println("Resource used")
    }
}

fun main() {
    // Deadlock code
    //: If t1 locks res1 and t2 locks res2, they will wait indefinitely.
    /*val res1 = Resource()
    val res2 = Resource()

    val t1 = Thread {
        synchronized(res1) {
            Thread.sleep(100)
            synchronized(res2) { res2.use() }
        }
    }

    val t2 = Thread {
        synchronized(res2) {
            Thread.sleep(100)
            synchronized(res1) { res1.use() }
        }
    }

    t1.start()
    t2.start()*/

    //Solution 1: Always Lock Resources in the Same Order
    val res1 = Resource()
    val res2 = Resource()

    val t1 = Thread {
        synchronized(res1) {  // Always lock res1 first
            Thread.sleep(100)
            synchronized(res2) { res2.use() }
        }
    }

    val t2 = Thread {
        synchronized(res1) {  // Ensure res1 is locked before res2
            Thread.sleep(100)
            synchronized(res2) { res2.use() }
        }
    }

    t1.start()
    t2.start()


    //Solution 2: Use ReentrantLock with tryLock()

    val lock1 = ReentrantLock()
    val lock2 = ReentrantLock()

    val t3 = Thread {
        while (true) {
            if (lock1.tryLock()) {
                try {
                    Thread.sleep(100)
                    if (lock2.tryLock()) {
                        try {
                            res2.use()
                            break
                        } finally {
                            lock2.unlock()
                        }
                    }
                } finally {
                    lock1.unlock()
                }
            }
        }
    }

    val t4 = Thread {
        while (true) {
            if (lock2.tryLock()) {
                try {
                    Thread.sleep(100)
                    if (lock1.tryLock()) {
                        try {
                            res1.use()
                            break
                        } finally {
                            lock1.unlock()
                        }
                    }
                } finally {
                    lock2.unlock()
                }
            }
        }
    }

    t3.start()
    t4.start()

    //Solution 3: Use a Single Lock for Both Resources
    val lock = Any()  // One common lock for both resources

    val t5 = Thread {
        synchronized(lock) {
            Thread.sleep(100)
            res1.use()
            res2.use()
        }
    }

    val t6 = Thread {
        synchronized(lock) {
            Thread.sleep(100)
            res1.use()
            res2.use()
        }
    }

    t5.start()
    t6.start()
}