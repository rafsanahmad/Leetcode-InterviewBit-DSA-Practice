/*
 * *
 *  * InlineClass_Functions.kt
 *  * Created by Rafsan Ahmad on 3/22/25, 11:27AM
 *  * Copyright (c) 2025 . All rights reserved.
 *  *
 *
 */

package kotlinclasses.Concepts

import kotlinclasses.Concepts.InlineClass_Functions.UserId

class InlineClass_Functions {
    /*An inline class is a special kind of class that wraps a single primitive or reference type but is optimized at runtime to avoid extra memory allocations.

📌 Key Benefits:
✅ Performance Boost – Avoids creating wrapper objects.
✅ Type Safety – Distinguishes different types at compile time.
✅ No Additional Overhead – Compiled code behaves like the underlying type.
*/

    // Example: Without Inline Class (Normal Wrapper)
    class UserId(val id: String)

    //Example: With Inline Class
    @JvmInline
    value class UserIdInline(val id: String)

    fun printUserId(userId: UserId) {
        println("User ID: ${userId.id}")
    }

    /* How It Works:
The value class (formerly called inline class) removes the wrapper object at runtime.
The function printUserId(userId: UserId) will be compiled as printUserId(id: String),
eliminating overhead.


Summary
Feature	Normal Class	                   Inline Class
Object Creation	Creates an extra object	   Avoids extra object creation
Memory Overhead	Higher	                   Lower
Performance	Slightly slower	               Faster
Usage	General-purpose	Optimized for single-value types

*/

    fun printUserId(userId: UserIdInline) {
        println("User ID: ${userId.id}")
    }

    //Basic Example of an Inline Function
    inline fun greet(name: String) {
        println("Hello, $name!")
    }

    //Inline Function with Lambda Parameter
    inline fun performOperation(a: Int, b: Int, operation: (Int, Int) -> Int): Int {
        return operation(a, b)
    }

    //Using noinline (To Prevent Inlining Specific Lambdas)
    //Here, task1() will be inlined, but task2() will not.
    inline fun execute(task1: () -> Unit, noinline task2: () -> Unit) {
        task1()
        task2()
    }

    //Using crossinline (To Enforce Execution in Inline Functions)
    //crossinline ensures task() is executed but not inlined into another function.
    inline fun executeTask(crossinline task: () -> Unit) {
        Thread { task() }.start()
    }

    inline fun <reified T : Number> append(arr: Array<T>, add: T): Array<T> {
        val temp: MutableList<T> = arr.toMutableList()
        temp.add(add)
        return temp.toTypedArray()
    }
}

fun main() {
    val inlineclassFunctions = InlineClass_Functions()
    val id = UserId("12345")
    inlineclassFunctions.printUserId(id)

    val id2 = InlineClass_Functions.UserIdInline("43343")
    inlineclassFunctions.printUserId(id2)

    inlineclassFunctions.greet("Rafsan")

    val sum = inlineclassFunctions.performOperation(5, 3) { x, y -> x + y }
    println("Sum: $sum") // Output: Sum: 8

    inlineclassFunctions.execute(
        { println("Task 1 executed") },
        { println("Task 2 executed") }
    )

    inlineclassFunctions.executeTask { println("Running in a new thread!") }

    var start = arrayOf(1, 2, 3)
    start = inlineclassFunctions.append(start, 4)
    println(start[3]) //4
}