/*
 * *
 *  * SealedClassEx.kt
 *  * Created by Rafsan Ahmad on 3/15/25, 7:53PM
 *  * Copyright (c) 2025 . All rights reserved.
 *  *
 *
 */

package kotlinclasses.Concepts

class SealedClassEx {
    /*Sealed classes let you restrict the use of inheritance. Once you declare a class sealed,
    it can only be subclassed from inside the same file where the sealed class is declared.
    It cannot be subclassed outside of the file where the sealed class is declared.*/

    sealed class Mammal(val name: String)                                                   // 1

    class Cat(val catName: String) : Mammal(catName)                                        // 2
    class Human(val humanName: String, val job: String = "Engineer") : Mammal(humanName)

    fun greetMammal(mammal: Mammal): String {
        when (mammal) {                                                                     // 3
            is Human -> return "Hello ${mammal.name}; You're working as a ${mammal.job}"    // 4
            is Cat -> return "Hello ${mammal.name}"                                         // 5
        }                                                                                   // 6
    }
    /*
    1.Defines a sealed class.
    2.Defines subclasses. Note that all subclasses must be in the same file.
    3.Uses an instance of the sealed class as an argument in a when expression.
    4.A smartcast is performed, casting Mammal to Human.
    5. A smartcast is performed, casting Mammal to Cat.
    6. The else-case is not necessary here since all possible subclasses of the sealed class are covered.
    With a non-sealed superclass else would be required.*/

}

fun main() {
    var ex = SealedClassEx()
    println(ex.greetMammal(SealedClassEx.Cat("Snowy")))
    println(ex.greetMammal(SealedClassEx.Human("Rafsan")))
}

