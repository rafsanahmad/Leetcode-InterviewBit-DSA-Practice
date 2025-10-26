/*
 * *
 *  * Binary Addition.kt
 *  * Created by Rafsan Ahmad on 10/26/25, 4:24PM
 *  * Copyright (c) 2025 . All rights reserved.
 *  *
 *
 */

package kotlinclasses.Strings

class BinaryAddition {
    /*You are given two binary strings s1 and s2, representing two non-negative binary numbers
    (composed only of '0' and '1').

Return a new binary string representing their sum, also in binary form.
The result should not have any leading zeros, except if the result itself is "0".

Examples
Example 1
Input:
s1 = "11"
s2 = "1"

Output:
"100"

Explanation:
   11
+   1
-----
  100

Example 2
Input:
s1 = "1010"
s2 = "1011"

Output:
"10101"

Explanation:
   1010
+  1011
--------
  10101

Example 3
Input:
s1 = "0"
s2 = "0"

Output:
"0"
*/
    fun addBinaryString(s1: String, s2: String): String {
        val result: StringBuilder = StringBuilder("")
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