/*
 * *
 *  * RightLengthEncoding.kt
 *  * Created by Rafsan Ahmad on 3/15/25, 7:54PM
 *  * Copyright (c) 2025 . All rights reserved.
 *  *
 *
 */

package kotlinclasses.Utility

class RightLengthEncoding {

    fun encode(str: String): String {
        val withoutSpaceStr = str.filter { !it.isWhitespace() }
        if (withoutSpaceStr.isEmpty()) return ""
        val strArray = withoutSpaceStr.toCharArray()
        var currentChar = strArray[0]
        var count = 0
        val builder = StringBuilder()
        for (i in 0 until strArray.size) {
            if (currentChar == strArray[i]) {
                count++
            } else {
                builder.append(count)
                builder.append(currentChar)
                currentChar = strArray[i]
                count = 1
            }
        }
        //for last character
        builder.append(count)
        builder.append(currentChar)
        return builder.toString()
    }
}

fun main(args: Array<String>) {
    var s = "jjjjjjjjjdddddddddiiiuusskkpppuuutttrewqnbhyj"
    var s2 = "  "
    var s3 = " aa b cc "
    var encoded = RightLengthEncoding().encode(s3)
    println(encoded)
}