/*
 * *
 *  * Created by Rafsan Ahmad on 12/5/21, 4:27 PM
 *  * Copyright (c) 2021 . All rights reserved.
 *
 */

package javaclasses.OutputQuiz

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
}

fun main(args: Array<String>) {
    val someClass = OutputKotlin.SomeClass()
    println("SomeClass initialised")
    someClass.accessObject() //Prints same object
    someClass.accessObject()  //Prints same object
}