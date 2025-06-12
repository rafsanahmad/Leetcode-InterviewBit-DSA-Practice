/*
 * *
 *  * Length of Last Word.kt
 *  * Created by Rafsan Ahmad on 6/11/25, 1:56PM
 *  * Copyright (c) 2025 . All rights reserved.
 *  *
 *
 */

package kotlinclasses.Strings

class LengthOfLastWord {
    //https://leetcode.com/problems/length-of-last-word/description/
    /*Given a string s consisting of words and spaces, return the length of the last word in the
    string.

A word is a maximal substring consisting of non-space characters only.

Example 1:
Input: s = "Hello World"
Output: 5
Explanation: The last word is "World" with length 5.

Example 2:
Input: s = "   fly me   to   the moon  "
Output: 4
Explanation: The last word is "moon" with length 4.

Example 3:
Input: s = "luffy is still joyboy"
Output: 6
Explanation: The last word is "joyboy" with length 6.


Constraints:
1 <= s.length <= 10^4
s consists of only English letters and spaces ' '.
There will be at least one word in s.*/

    fun lengthOfLastWord(s: String): Int {
        if (s.isEmpty()) return 0

        var len = s.length - 1

        // Skip trailing spaces
        while (len >= 0 && s[len] == ' ') {
            len--
        }

        // Count characters of the last word
        var count = 0
        while (len >= 0 && s[len] != ' ') {
            count++
            len--
        }

        return count
    }
}

fun main() {
    val obj = LengthOfLastWord()
    println(obj.lengthOfLastWord("Hello World"))  // Output: 5
    println(obj.lengthOfLastWord(" a"))           // Output: 1
    println(obj.lengthOfLastWord("   fly me   to   the moon  "))         // Output: 4
    println(obj.lengthOfLastWord("   "))          // Output: 0
    println(obj.lengthOfLastWord(""))             // Output: 0
}