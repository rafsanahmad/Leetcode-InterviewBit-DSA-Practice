/*
 * *
 *  * Text Justification.kt
 *  * Created by Rafsan Ahmad on 9/1/25, 1:36AM
 *  * Copyright (c) 2025 . All rights reserved.
 *  *
 *
 */

package kotlinclasses.Strings

class TextJustification {
    //https://leetcode.com/problems/text-justification/description/
    /*Given an array of strings words and a width maxWidth, format the text such that each line has
    exactly maxWidth characters and is fully (left and right) justified.

You should pack your words in a greedy approach; that is, pack as many words as you can in each line.
Pad extra spaces ' ' when necessary so that each line has exactly maxWidth characters.

Extra spaces between words should be distributed as evenly as possible. If the number of spaces on a
line does not divide evenly between words, the empty slots on the left will be assigned more spaces
than the slots on the right.

For the last line of text, it should be left-justified, and no extra space is inserted between words.

Note:

A word is defined as a character sequence consisting of non-space characters only.
Each word's length is guaranteed to be greater than 0 and not exceed maxWidth.
The input array words contains at least one word.


Example 1:

Input: words = ["This", "is", "an", "example", "of", "text", "justification."], maxWidth = 16
Output:
[
   "This    is    an",
   "example  of text",
   "justification.  "
]
Example 2:

Input: words = ["What","must","be","acknowledgment","shall","be"], maxWidth = 16
Output:
[
  "What   must   be",
  "acknowledgment  ",
  "shall be        "
]
Explanation: Note that the last line is "shall be    " instead of "shall     be", because the last
line must be left-justified instead of fully-justified.
Note that the second line is also left-justified because it contains only one word.
Example 3:

Input: words = ["Science","is","what","we","understand","well",
"enough","to","explain","to","a","computer.","Art","is","everything","else","we","do"], maxWidth = 20
Output:
[
  "Science  is  what we",
  "understand      well",
  "enough to explain to",
  "a  computer.  Art is",
  "everything  else  we",
  "do                  "
]


Constraints:
1 <= words.length <= 300
1 <= words[i].length <= 20
words[i] consists of only English letters and symbols.
1 <= maxWidth <= 100
words[i].length <= maxWidth*/

    //Time Complexity: O(n) (linear in the number of words).
    //Space Complexity: O(n + maxWidth).
    fun fullJustify(words: Array<String>, maxWidth: Int): List<String> {
        val result = mutableListOf<String>()
        var fromIndex = 0
        var indexCount = 0

        for (i in words.indices) {
            // count letters + at least one space between words
            indexCount += words[i].length + 1
            if (indexCount - 1 > maxWidth) { // -1 because no space after last word
                justifyLine(words, fromIndex, i - 1, result, maxWidth, false)
                fromIndex = i
                indexCount = words[i].length + 1
            }
        }

        // last line
        justifyLine(words, fromIndex, words.size - 1, result, maxWidth, true)
        return result
    }

    fun justifyLine(
        words: Array<String>,
        from: Int,
        to: Int,
        list: MutableList<String>,
        maxWidth: Int,
        isLastLine: Boolean
    ) {
        val builder = StringBuilder()
        val charCount = (from..to).sumOf { words[it].length }
        val totalSpaces = maxWidth - charCount
        val gaps = to - from

        if (isLastLine || gaps == 0) {
            // left-justified: one space between words, rest at end
            for (i in from..to) {
                builder.append(words[i])
                if (i != to) builder.append(" ")
            }
            val remaining = maxWidth - builder.length
            repeat(remaining) { builder.append(" ") }
        } else {
            // fully justified
            val spacePerGap = totalSpaces / gaps
            var extraSpace = totalSpaces % gaps

            for (i in from..to) {
                builder.append(words[i])
                if (i != to) {
                    var spacesToAdd = spacePerGap
                    if (extraSpace > 0) {
                        spacesToAdd += 1
                        extraSpace--
                    }
                    repeat(spacesToAdd) { builder.append(" ") }
                }
            }
        }

        list.add(builder.toString())
    }
}

fun main() {
    val obj = TextJustification()
    val words1 = arrayOf("This", "is", "an", "example", "of", "text", "justification.")
    val maxWidth1 = 16
    val result1 = obj.fullJustify(words1, maxWidth1)
    println(result1)

    val words2 = arrayOf("What", "must", "be", "acknowledgment", "shall", "be")
    val maxWidth2 = 16
    val result2 = obj.fullJustify(words2, maxWidth2)
    println(result2)
}