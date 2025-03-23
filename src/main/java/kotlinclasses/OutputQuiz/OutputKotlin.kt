/*
 * *
 *  * Output Kotlin.kt
 *  * Created by Rafsan Ahmad on 3/22/25, 12:37PM
 *  * Copyright (c) 2025 . All rights reserved.
 *  *
 *
 */

package kotlinclasses.OutputQuiz

import java.util.concurrent.Executors
import java.util.concurrent.Callable
import java.util.concurrent.TimeUnit

class OutputKotlin {

    class HeavyClass {

    }

    class SomeClass {
        private val heavyObject: HeavyClass by lazy {
            println("Heavy Object initialised")
            HeavyClass()
        }

        fun accessObject() {
            println(heavyObject)
        }
    }

    //Invoke example
    class Config {
        var count = 0;
        operator fun invoke(): Config {
            count++
            return this
        }
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            println("Main function inside class")
        }
    }
}

class Car(val brand: String) {
    constructor() : this("Toyota") {
        println("Default constructor called")
    }
}

object Android {
    val version: String = "Jelly Bean"

    val email = provideEmail()

    fun provideEmail(): String {
        val email = "test@gmail.com"
        println(email)
        return email
    }
}

val myLazyValue: String by lazy {
    println("Calculating value...")
    "Hello, Kotlin!"
}

open class Parent {
    init {
        println("Parent Init Block")
    }
}

class Child : Parent() {
    init {
        println("Child Init Block")
    }
}

data class User(val name: String, val age: Int)

interface A {
    fun show() {
        println("Interface A")
    }
}

interface B {
    fun show() {
        println("Interface B")
    }
}

class C : A, B {
    override fun show() {
        println("Resolving conflict...")
        super<A>.show() // Explicitly calling A's method
    }
}

sealed class Operation

data class Add(val value: Int) : Operation()
data class Subtract(val value: Int) : Operation()

class MyThread : Thread() {
    override fun run() {
        println("Thread running")
    }
}

class Counter {
    private var count = 0

    @Synchronized
    fun increment() {
        count++
    }

    fun getCount() = count
}

class Shared {
    @Synchronized
    fun waitForIt() {
        println("Waiting...")
        (this as java.lang.Object).wait()
        println("Resumed")
    }

    @Synchronized
    fun notifyIt() {
        println("Notifying...")
        (this as java.lang.Object).notify()
    }
}

fun printStudents(vararg students: String) {
    for(student in students) println(student)
}

fun main(args: Array<String>) {
    //Quiz 1
    val someClass = OutputKotlin.SomeClass()
    println("SomeClass initialised")
    someClass.accessObject() //Prints same object
    someClass.accessObject()  //Prints same object

    //Quiz 2
    val numbers = mutableListOf("One", "Two", "Three", "Four", "Five")
    val resultsList = numbers.map { it.length }.filter { it > 3 }
    println(resultsList)

    //Quiz 3
    val config = OutputKotlin.Config()
    config()()()()()()()()()()
    println("config was called ${config.count} times")

    //Quiz 4
    //prints:
    //test@gmail.com
    //Jelly Bean
    println(Android.version)


    //Quiz 5
    // Lazy initialization
    println("Before accessing lazy value")
    println(myLazyValue)  // Value is calculated only once when accessed
    println(myLazyValue)  // Uses the cached value after the first access

    //Quiz 6
    //Constructors
    val c = Car()
    println(c.brand)

    //Quiz 7
    // Class Initialization order
    val obj = Child()

    //Quiz 8
    //Data class Copy
    val u1 = User("Alice", 30)
    val u2 = u1.copy(age = 35)
    println("${u2.name} is ${u2.age} years old")

    //Quiz 9
    //Interface default implementation
    val obj2 = C()
    obj2.show()
    // Output:
    // Resolving conflict...
    // Interface A

    //Quiz 10
    //Sealed class
    val op: Operation = Add(10)
    when (op) {
        is Add -> println("Add: ${op.value}")
        is Subtract -> println("Subtract: ${op.value}")
    }

    //Quiz 11 - Thread run
    val t = MyThread()
    //run() is called directly instead of start(),
    // so it runs on the main thread instead of a new thread.
    t.run()

    //Quiz 12  - Thread Join
    val thread = Thread {
        Thread.sleep(1000)
        println("Thread finished")
    }
    thread.start()
    thread.join()
    println("Main thread finished")

    //Quiz 13 - Thread Synchronized
    val counter = Counter()
    val t1 = Thread { repeat(1000) { counter.increment() } }
    val t2 = Thread { repeat(1000) { counter.increment() } }
    t1.start()
    t2.start()
    t1.join()
    t2.join()
    println(counter.getCount()) // prints 2000

    //Quiz 14 - Thread wait & notify
    //Output: Waiting... then Notifying... then Resumed
    val shared = Shared()
    val t3 = Thread { shared.waitForIt() }
    val t4 = Thread {
        Thread.sleep(1000)
        //wait() makes the thread pause until notify() is called
        shared.notifyIt()
    }
    t3.start()
    t4.start()

    //Quiz 15: Daemon thread
    //Daemon threads exit when the main thread ends
    //Output: Running... then Main exits
    val t5 = Thread {
        while (true) {
            println("Running...")
        }
    }
    t5.isDaemon = true
    //t5.start()
    Thread.sleep(100)
    println("Main exits")

    //Quiz 16: Using ExecutorService to Execute Threads
    //Executors.newFixedThreadPool(2) runs both tasks in parallel
    //Output:
    /*Task 1 executed
      Task 2 executed
      */
    val executor = Executors.newFixedThreadPool(2)
    executor.execute { println("Task 1 executed") }
    executor.execute { println("Task 2 executed") }
    executor.shutdown()

    //Quiz 17: Using Callable and Future
    //Callable returns a result, which Future.get() retrieves.
    val executor2 = Executors.newSingleThreadExecutor()
    val future = executor2.submit(Callable { "Task completed" })
    println(future.get())
    executor2.shutdown()

    //Quiz 18: Scheduled Thread Execution
    //schedule() delays execution by 1 second.
    val scheduler = Executors.newScheduledThreadPool(1)
    scheduler.schedule({ println("Task executed") }, 1, TimeUnit.SECONDS)
    Thread.sleep(1500)
    scheduler.shutdown()

    //Quiz 19: Fixed Thread Pool
    //The thread pool of size 3 handles 5 tasks concurrently - Task order is not maintained
    val pool = Executors.newFixedThreadPool(3)
    repeat(5) { i ->
        pool.execute { println("Task $i executed") }
    }
    pool.shutdown()

    //Quiz 20: Sequence
    val lessThan99 = generateSequence(1){ if (it < 99) it + 1 else null }.toList()
    println(lessThan99)

    //Quiz 21: Vararg
    val students = arrayOf("Abel", "Bill", "Cindy", "Darla")
    printStudents(*students)
}