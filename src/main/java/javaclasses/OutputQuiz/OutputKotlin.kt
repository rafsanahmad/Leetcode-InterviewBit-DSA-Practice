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

    //Invoke example
    class Config {
        var count = 0;
        operator fun invoke(): Config {
            count++
            return this
        }
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
}