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
}

fun main() {
    val obj = C()
    obj.show() // Output: "From A"
}