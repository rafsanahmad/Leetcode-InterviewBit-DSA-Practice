/*
 * *
 *  * VisibilityModifiers.kt
 *  * Created by Rafsan Ahmad on 3/15/25, 7:53PM
 *  * Copyright (c) 2025 . All rights reserved.
 *  *
 *
 */

package kotlinclasses.Concepts

class VisibilityModifiers {
}

open class Outer {
    private val a = 1
    protected open val b = 2
    internal open val c = 3
    val d = 4  // public/final by default

    protected open class Nested {
        public open var e: Int = 5
    }
}

class Subclass : Outer() {
    // a is not visible
    // b, c and d are visible
    // Nested and e are visible
    override val b = 5   // 'b' is protected
    override val c = 7   // 'c' is internal

    fun test() {
        val n = Nested()
        n.e = 90
    }
}

class Unrelated(o: Outer) {
    // o.a, o.b are not visible
    // o.c and o.d are visible (same module)
    // Outer.Nested is not visible, and Nested::e is not visible either
}

open class AA() {
    open var price: Int = 0
        get() = field + 10
}

class BB() : AA() {
    override var price: Int = 0
        get() = field + 20
}