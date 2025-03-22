/*
 * *
 *  * Constructors.kt
 *  * Created by Rafsan Ahmad on 3/21/25, 3:50PM
 *  * Copyright (c) 2025 . All rights reserved.
 *  *
 *
 */

package kotlinclasses.Concepts

import kotlinclasses.Concepts.Constructors.Person

class Constructors {
    /*In Kotlin, a class can have:

Primary Constructor – Defined in the class header.
Secondary Constructor(s) – Defined inside the class body.

Primary Constructor
a. Declared in the class header.
b. Can have parameters.
c. Cannot contain logic (use init block for initialization).


Secondary Constructor
a. Declared inside the class with constructor keyword.
b. Can include logic and default values.
c. Must call the primary constructor (if present) using this(...).
*/

    // Primary constructor
    class Person(val name: String) {

        var age: Int = 1  // Default value

        // Secondary constructor
        constructor(name: String, age: Int) : this(name) {
            this.age = age
            println("Person Created: $name is $age years old")
        }
    }
}

fun main() {
    val person1 = Person("Alan")
    val person2 = Person("Bob", 29)
    // Output:
    // Person Created: Bob is 29 years old
}