/*
 * *
 *  * Multiple Inheritance.kt
 *  * Created by Rafsan Ahmad on 3/22/25, 10:37AM
 *  * Copyright (c) 2025 . All rights reserved.
 *  *
 *
 */

package kotlinclasses.Concepts

import kotlinclasses.Concepts.MultipleInheritance.C

class MultipleInheritance {
    interface A {
        fun show() {
            println("From A")
        }
    }

    interface B {
        fun show() {
            println("From B")
        }
    }

    // Class implementing two interfaces with the same method
    class C : A, B {
        override fun show() {
            super<A>.show() // Explicitly choosing A's implementation
        }
    }


    open class Parent(value: Int = 3)
    class Child1(value1: Int, value2: Int) : Parent(value1)
    class Child2 : Parent()
    class Child3() : Parent(2)
    class Child4 : Parent {
        constructor(value: Int) : super(value)
    }
}

fun main() {
    val obj = C()
    obj.show() // Output: "From A"

    val child1 = MultipleInheritance.Child1(2, 4)
    val child2 = MultipleInheritance.Child2()
    val child3 = MultipleInheritance.Child3()
    val child4 = MultipleInheritance.Child4(5)
}