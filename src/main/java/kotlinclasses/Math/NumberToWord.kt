/*
 * *
 *  * Number To Word.kt
 *  * Created by Rafsan Ahmad on 10/26/25, 4:19PM
 *  * Copyright (c) 2025 . All rights reserved.
 *  *
 *
 */

package kotlinclasses.Math

class NumberToWord {

    //https://www.careercup.com/question?id=5177754057179136
    /*Input : 123456789
    Output : One hundred twenty three million four hundred fifty six thousand seven hundred eighty nine*/

    private val specialNames = arrayOf("",
        " thousand",
        " million",
        " billion",
        " trillion",
        " quadrillion",
        " quintillion")

    private val tensNames = arrayOf(
        "",
        " ten",
        " twenty",
        " thirty",
        " forty",
        " fifty",
        " sixty",
        " seventy",
        " eighty",
        " ninety"
    )

    private val numNames = arrayOf(
        "",
        " one",
        " two",
        " three",
        " four",
        " five",
        " six",
        " seven",
        " eight",
        " nine",
        " ten",
        " eleven",
        " twelve",
        " thirteen",
        " fourteen",
        " fifteen",
        " sixteen",
        " seventeen",
        " eighteen",
        " nineteen"
    )

    private fun convertLessThanOneThousand(number: Int): String {
        var number = number
        var current: String
        if (number % 100 < 20) {
            current = numNames.get(number % 100)
            number /= 100
        } else {
            current = numNames.get(number % 10)
            number /= 10
            current = tensNames.get(number % 10).toString() + current
            number /= 10
        }
        return if (number == 0) current else numNames.get(number).toString() + " hundred" + current
    }

    fun convert(number: Int) : String {
        var num = number
        if (num == 0) {
            return "zero"
        }
        var prefix = ""
        if(num < 0) {
            num = -number
            prefix = "negative"
        }
        var current = ""
        var place = 0

        do {
            val n: Int = num % 1000
            if (n != 0) {
                val s: String = convertLessThanOneThousand(n)
                current = s + specialNames[place] + current
            }
            place++
            num /= 1000
        } while (num > 0)

        return (prefix + current).trim { it <= ' ' }
    }
}

fun main(args: Array<String>) {
    val obj = NumberToWord()
    println("" + obj.convert(1234));
}