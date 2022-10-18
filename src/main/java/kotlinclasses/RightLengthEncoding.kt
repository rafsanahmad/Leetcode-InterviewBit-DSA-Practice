/*
 * *
 *  * Created by Rafsan Ahmad on 10/25/21, 9:50 PM
 *  * Copyright (c) 2021 . All rights reserved.
 *
 */

package kotlinclasses

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