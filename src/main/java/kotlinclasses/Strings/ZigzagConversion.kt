/*
 * *
 *  * Zigzag Conversion.kt
 *  * Created by Rafsan Ahmad on 8/8/25, 3:37AM
 *  * Copyright (c) 2025 . All rights reserved.
 *  *
 *
 */

package kotlinclasses.Strings

class ZigzagConversion {
    //https://leetcode.com/problems/zigzag-conversion/description/
    /*The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this:
     (you may want to display this pattern in a fixed font for better legibility)

P   A   H   N
A P L S I I G
Y   I   R
And then read line by line: "PAHNAPLSIIGYIR"

Write the code that will take a string and make this conversion given a number of rows:

string convert(string s, int numRows);

Example 1:
Input: s = "PAYPALISHIRING", numRows = 3
Output: "PAHNAPLSIIGYIR"

Example 2:
Input: s = "PAYPALISHIRING", numRows = 4
Output: "PINALSIGYAHRPI"
Explanation:
P     I    N
A   L S  I G
Y A   H R
P     I

Example 3:
Input: s = "A", numRows = 1
Output: "A"


Constraints:
1 <= s.length <= 1000
s consists of English letters (lower-case and upper-case), ',' and '.'.
1 <= numRows <= 1000*/

    fun convert(s: String, numRows: Int): String {
        if (numRows == 1 || s.length <= numRows) return s

        val rows = MutableList(numRows) { StringBuilder() }
        var currentRow = 0
        var goingDown = false

        for (char in s) {
            rows[currentRow].append(char)

            // Change direction if at top or bottom row
            if (currentRow == 0 || currentRow == numRows - 1) {
                goingDown = !goingDown
            }

            currentRow += if (goingDown) 1 else -1
        }

        // Combine all rows
        return rows.joinToString("") { it.toString() }
    }
}

fun main() {
    val obj = ZigzagConversion()
    println(obj.convert("PAYPALISHIRING", 3))
}