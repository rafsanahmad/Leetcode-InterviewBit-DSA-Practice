/*
 * *
 *  * BinaryAddition.kt
 *  * Created by Rafsan Ahmad on 3/15/25, 7:46PM
 *  * Copyright (c) 2025 . All rights reserved.
 *  *
 *
 */

package kotlinclasses.Utility

class BinaryAddition {

    fun addBinaryString(s1: String, s2: String): String {
        var result: StringBuilder = StringBuilder("")
        var carry = 0
        var total = 0
        var len1 = s1.length - 1
        var len2 = s2.length - 1

        while (len1 >= 0 || len2 >= 0) {
            total = 0
            if (len1 >= 0) {
                total += s1.get(len1) - '0'
            }
            if (len2 >= 0) {
                total += s2.get(len2) - '0'
            }
            total += carry
            //total = s1.get(i).toInt() + s2.get(i).toInt() + carry
            if (total % 2 == 0) {
                result.insert(0, "0")
            } else {
                result.insert(0, total % 2)
            }
            if(total >= 2) {
                carry = 1
            } else {
                carry = 0
            }
            len1--
            len2--
        }
        if (carry > 0) {
            result.insert(0, carry)
        }
        return result.toString()
    }
}

fun main(args: Array<String>) {
    var ba = BinaryAddition()
    var result = ba.addBinaryString("111010", "101001")
    println(result)
}