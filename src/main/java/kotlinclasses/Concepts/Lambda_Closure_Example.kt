/*
 * *
 *  * Lambda_Closure_Example.kt
 *  * Created by Rafsan Ahmad on 3/15/25, 7:50PM
 *  * Copyright (c) 2025 . All rights reserved.
 *  *
 *
 */

package kotlinclasses.Concepts

class Lambda_Closure_Example {
    fun operate(a: Int, b: Int, operation: (Int, Int) -> Int): Int {
        return operation(a, b)
    }

    //Asynchronous lambda
    fun fetchData(callBack: (String) -> Unit) {
        callBack("Data received")
    }

    //Return lambda
    fun multiplier(factor: Int): (Int) -> Int {
        return { num -> num * factor }
    }

    //Higher order function
    fun repeatAction(times: Int, action: (Int) -> Unit) {
        for (i in 1..times) {
            action(i)
        }
    }
}

fun main() {
    val sample = Lambda_Closure_Example()
    //Without trailing closure
    val result = sample.operate(5, 9, { x, y -> x + y })
    println(result)

    //With trailing closure
    val result2 = sample.operate(5, 9) { x, y -> x + y };
    println(result2)

    //Trailing lambda
    sample.fetchData { data -> println(data) }

    //Single parameter Lambda
    val square: (Int) -> Int = { it * it }
    println(square(3))

    //Lambda as a Return Value
    val triple = sample.multiplier(3)
    println(triple(5)) // Output 15

    //Filtering list with Lambda
    val numbers = listOf(1, 2, 3, 4, 5, 6)
    val evenNumbers = numbers.filter { it % 2 == 0 }
    println(evenNumbers)

    //Mapping a List with a Lambda
    val names = listOf("Alice", "Bob", "Charlie")
    val upperNames = names.map { it.toUpperCase() }
    println(upperNames)

    //Sorting a List with a Lambda
    val numbers2 = listOf(5, 2, 6, 7, 9)
    val sortedNumber = numbers2.sortedBy { it } // output: [2, 5, 6, 7, 9]
    println(sortedNumber)

    //Lambda in Higher-Order Function
    sample.repeatAction(3) {
        println("Iteration: $it")
    }

    //Lambda with run Scope Function
    val message = run {
        val name = "Rafsan"
        "Hello, $name"
    }
    println(message) // Output: Hello, Rafsan

    //Lambda in let, apply, and also
    val name: String? = "Jhon"

    name?.let { println("Hello, $it") }
    //using apply to modify an object
    val user = StringBuilder().apply {
        append("User")
        append("Jhon")
    }
    println(user)

    //using also for debugging
    val result3 = listOf(1, 2, 3).also { println("item: $it") }
    println(result3)
}