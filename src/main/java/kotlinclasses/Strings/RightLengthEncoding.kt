/*
 * *
 *  * Right Length Encoding.kt
 *  * Created by Rafsan Ahmad on 10/26/25, 4:25PM
 *  * Copyright (c) 2025 . All rights reserved.
 *  *
 *
 */

package kotlinclasses.Strings

class RightLengthEncoding {
    /*Given a string str, implement an encoding algorithm that compresses the string by replacing
     sequences of the same character with the count followed by the character itself.
All whitespace characters should be ignored during encoding.

Return the encoded string.

Example
Example 1
Input: str = "aaabbc"
Output: 3a2b1c

Explanation:
a appears 3 times
b appears 2 times
c appears 1 time
→ Combine to "3a2b1c"

Example 2
Input: str = "a   a bbb   c"
Output: 2a3b1c

Explanation:
Spaces are ignored
The sequence (after removing spaces) is "aabbbc"
→ Encoding gives "2a3b1c"

Example 3
Input: str = "   "
Output: ""

Explanation:
All characters are spaces → ignored → empty string.*/
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