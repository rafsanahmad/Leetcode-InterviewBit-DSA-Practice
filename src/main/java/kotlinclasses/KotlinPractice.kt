package kotlinclasses

import java.util.*
import kotlin.collections.HashMap

class KotlinPractice {
    val square = { number: Int -> number * number }

    val calculateGrade = fun(grade: Int): String {
        if (grade < 0 || grade > 100) {
            return "error"
        } else if (grade < 40) {
            return "Fail"
        } else if (grade < 70) {
            return "Pass"
        }
        return "Distinction"
    }

    fun cases(obj: Any) {
        when (obj) {
            1 -> println("one")
            "Hello" -> println("Greeting")
            is Long -> println("Long")
            !is String -> println("Not String")
            else -> println("Unknown")
        }
    }

    fun loops() {
        for (i in 0..3) {
            print(i)
        }
        for (i in 0 until 3) {
            print(i)
        }
        for (i in 2..8 step 2) {
            print(i)
        }
        for (i in 3 downTo 0) {
            print(i)
        }
    }

    fun range() {
        val x = 2
        if (x in 1..5) {
            print("x is in range from 1 to 5")
        }
        if (x !in 6..10) {
            print("x is not in range from 6 to 10")
        }
    }

    fun canMakeStr2(str1: String, str2: String): Boolean {
        // Create a count array and count frequencies
        // characters in str1.
        val MAX = 256
        val count = IntArray(MAX)
        val str3 = str1.toCharArray()
        for (i in str3.indices)
            count[str3[i].toInt()]++
        // Now traverse through str2 to check
        // if every character has enough counts
        val str4 = str2.toCharArray()
        for (i in str4.indices) {
            if (count[str4[i].toInt()] == 0)
                return false
            count[str4[i].toInt()]--
        }
        return true
    }

    //https://www.careercup.com/question?id=5702909641621504
    fun addArray(array: Array<Int>, addition: Int): Array<Int> {
        var resultArray = Array(array.size, { 0 })
        if (array.isEmpty()) {
            return resultArray
        }
        var carry = 0
        var size = array.size
        var sum = 0
        for (i in size - 1 downTo 0) {
            if (i == size - 1) {
                sum = array[i] + addition
            } else {
                sum = array[i] + carry
            }
            resultArray[i] = sum % 10
            carry = sum / 10
        }
        if (carry > 0) {
            val rst = Array(array.size + 1, { 0 })
            rst[0] = carry
            System.arraycopy(resultArray, 0, rst, 1, resultArray.size)
            return rst
        }
        return resultArray
    }


    //https://www.careercup.com/question?id=5739520096993280
    fun getCount(n: Int): Int {
        if (0 == n) return 0
        if (1 == n) return 1
        val sqrt = Math.sqrt(n.toDouble()).toInt()
        return 1 + getCount(Math.abs(n - sqrt * sqrt))
    }

    //https://leetcode.com/problems/roman-to-integer/
    fun convertRomanToInteger(str: String?): Int {

        //If string value is null or zero
        if (str == null || str.length == 0) {
            return 0
        }

        //Create a mapping of roman numerals and it's integer value
        val charMap: MutableMap<Char, Int> = HashMap()
        charMap['I'] = 1
        charMap['V'] = 5
        charMap['X'] = 10
        charMap['L'] = 50
        charMap['C'] = 100
        charMap['D'] = 500
        charMap['M'] = 1000
        var result = 0

        /*
         Traverse a string and pick each character at a time.
       */for (i in 0 until str.length - 1) {

            /*
               If current Roman numeral value is greater than
               then the next value, the do addition.
             */
            var index = charMap[str[i]]
            var plusIndex = charMap[str[i + 1]]
            if (index != null) {
                result = if (plusIndex != null && index >= plusIndex) {
                    result + index
                } else {
                    result - index
                }
            }
        }

        //Add the value of last numeral
        var lastValue = charMap[str[str.length - 1]]
        if (lastValue != null) {
            result = result + lastValue
        }
        return result
    }

    fun max(a: Int, b: Int) = if (a > b) a else b
}

class Animal(val name: String)

class Zoo(val animals: List<Animal>) {
    operator fun iterator(): Iterator<Animal> {
        return animals.iterator()
    }
}

class Greeter(val name: String) {
    fun greet() {
        println("Hello, $name")
    }
}

class Person {
    lateinit var name: String
    fun initializeName() {
        println(this::name.isInitialized)
        name = "MindOrks" // initializing name
        println(this::name.isInitialized)
    }
}

fun main(args: Array<String>) {
    //Greeter(args[0]).greet()
    Person().initializeName()
    val kt = KotlinPractice()
    val n = kt.square(7)
    println(n)
    println(kt.calculateGrade(50))

    val greeting = { println("HELLO") }
    greeting.invoke()

    val numbers = arrayOf(1, -2, -3, 4, -5)
    println(numbers.filter { item -> item > 0 })

    val zoo = Zoo(listOf(Animal("Cow"), Animal("Lion")))
    for (animal in zoo) {
        println("Watch out it's a ${animal.name}")
    }

    val result = listOf(1, 2, 3).fold(0) { sum, element ->
        sum + element
    }
    println("FOLD: ${result}")

    val result2 = listOf(1, 2, 3).reduce { sum, element ->
        sum + element
    }
    println("REDUCE: ${result2}")

    val lambda: (Int, Int) -> Int = { x, y ->
        x + y
    }

    println("Lambda Sum: ${lambda(4, 6)}")

    val checkString = kt.canMakeStr2("Hello world", "worlds")
    println(checkString)

    var resultArray = kt.addArray(arrayOf(9, 9, 8, 9), 1)

    var r = kt.getCount(99)
    var s = 10
}