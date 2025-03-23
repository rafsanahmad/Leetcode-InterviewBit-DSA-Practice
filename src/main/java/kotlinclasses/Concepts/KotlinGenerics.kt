/*
 * *
 *  * Kotlin Generics.kt
 *  * Created by Rafsan Ahmad on 3/23/25, 2:49PM
 *  * Copyright (c) 2025 . All rights reserved.
 *  *
 *
 */

package kotlinclasses.Concepts

class KotlinGenerics {
    /*
    Explanation:
Star Projection (*): When you don’t know the exact type, but want to allow any type.

Covariance (out): Allows returning generic type T, but not passing it as a parameter.

Contravariance (in): Allows passing generic type T as a parameter, but not returning it.

Reified Type Parameters: Enables checking the type at runtime, which is normally erased.

Generic Constraints (T : Number): Restricts T to a specific type (e.g., Number).
    */

    // 1. Star Projection (*)
    fun printListElements(list: List<*>) {
        for (element in list) {
            println(element) // Can only read elements, cannot modify list
        }
    }

    // 2. Covariance (out) - Can return T but cannot accept T as input
    class Producer<out T>(private val value: T) {
        fun getValue(): T = value
    }

    // 3. Contravariance (in) - Can accept T as input but cannot return T
    class Consumer<in T> {
        fun consume(value: T) {
            println("Consumed: $value")
        }
    }

    // 4. Reified Type Parameter
    inline fun <reified T> isTypeMatched(value: Any): Boolean {
        return value is T
    }

    // 5. Generic Constraint
    fun <T : Number> addNumbers(a: T, b: T): Double {
        return a.toDouble() + b.toDouble()
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val generics = KotlinGenerics()
            // Using star projection
            val mixedList: List<Any> = listOf(1, "Hello", 3.14)
            generics.printListElements(mixedList)
            /*
            1
            Hello
            3.14
            */

            // Using covariance (out)
            val intProducer = Producer(100)
            println("Produced: ${intProducer.getValue()}")
            //Produced: 100

            // Using contravariance (in)
            val anyConsumer: Consumer<Any> = Consumer()
            anyConsumer.consume("Kotlin")
            //Consumed: Kotlin

            // Checking reified type
            println("Is '123' a String? ${generics.isTypeMatched<String>("123")}")
            //Is '123' a String? true

            // Generic constraint usage
            println("Sum: ${generics.addNumbers(4, 5.6)}")
            //Sum: 9.6
        }
    }
}