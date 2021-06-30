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

sealed class MySealedClass {
    class OP1 : MySealedClass() // MyExmaple class can be of two types only
    class OP2 : MySealedClass()
}

fun printMessage(message: String): Unit {
    println(message)
}

fun printMessageWithPrefix(message: String, prefix: String = "Info") {
    println("[$prefix] $message")
}

fun sum(x: Int, y: Int): Int {
    return x + y
}

fun multiply(x: Int, y: Int) = x * y

//Infix functions
class LikablePerson(val name: String) {
    private val likedPeople = mutableListOf<LikablePerson>()
    infix fun likes(other: LikablePerson) {
        likedPeople.add(other)
        for (item in 0..likedPeople.size - 1) {
            print(likedPeople.get(item).name + " ")
        }
        println()
    }
}

//operator functions
//The operator symbol for times() is * so that you can call the function using 2 * "Bye".
operator fun Int.times(str: String) = str.repeat(this)
operator fun String.get(range: IntRange) = substring(range)

//Functions with vararg Parameters
fun printAll(vararg messages: String) {
    for (m in messages) print("$m ")
    println()
}

fun printAllWithPrefix(vararg messages: String, prefix: String) {
    for (m in messages) print("$prefix$m ")
    println()
}

class BigBen {                                  //1
    companion object Bonger {                   //2
        fun getBongs(nTimes: Int) {             //3
            for (i in 1..nTimes) {
                print("BONG ")
            }
            println()
        }
    }
}

//Kotlin Primary Constructor, Init, Secondary Constructor
class Student(var name: String) {
    var id = -1

    init {
        println("Student has got a name as $name")
    }

    constructor(secname: String, id: Int) : this(secname) {
        this.id = id
        println("Student has got a name as $name & Student id $id")
    }
}

//Higher-Order Functions
/*A higher-order function is a function that takes another
function as parameter and/or returns a function.*/
fun calculate(x: Int, y: Int, operation: (Int, Int) -> Int): Int {
    return operation(x, y)
}

fun sumNumber(x: Int, y: Int) = x + y

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

    val obj: MySealedClass = MySealedClass.OP2()

    val output = when (obj) { // defining the object of the class depending on the inuputs
        is MySealedClass.OP1 -> "Option One has been chosen"
        is MySealedClass.OP2 -> "option Two has been chosen"
    }
    println(output)

    printMessage("Hello")
    printMessageWithPrefix("Hello", "Log")
    printMessageWithPrefix("Hello")
    printMessageWithPrefix(prefix = "Log", message = "Hello")
    println(sum(1, 2))
    println(multiply(2, 4))

    infix fun Int.times(str: String) = str.repeat(this)
    println(2 times "Bye")
    val pair = "Ferrari" to "Katrina"
    println(pair)

    val sophia = LikablePerson("Sophia")
    val claudia = LikablePerson("Claudia")
    sophia likes claudia

    println(2 * "Bye ")
    val str = "Always forgive your enemies; nothing annoys them so much."
    println(str[0..14])

    printAll("Hello", "Hallo", "Salut", "Hola", "你好")
    printAllWithPrefix(
        "Hello", "Hallo", "Salut", "Hola", "你好",
        prefix = "Greeting: "
    )
    BigBen.getBongs(12)

    var student1 = Student("Rafsan")
    var student2 = Student("Rafsan", 5)

    val sumResult = calculate(4, 5, ::sumNumber)
    val mulResult = calculate(4, 5) { a, b -> a * b }
    println("sumResult $sumResult, mulResult $mulResult")

    val strr = "exit"
    val splited = strr.split(" ")[0]
    println(splited.toString())
}