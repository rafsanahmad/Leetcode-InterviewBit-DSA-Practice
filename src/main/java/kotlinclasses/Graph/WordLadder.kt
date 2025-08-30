/*
 * *
 *  * Word Ladder.kt
 *  * Created by Rafsan Ahmad on 8/28/25, 12:24AM
 *  * Copyright (c) 2025 . All rights reserved.
 *  *
 *
 */

package kotlinclasses.Graph

import java.util.LinkedList
import java.util.Queue

class WordLadder {
    //https://leetcode.com/problems/word-ladder/description/
    /*A transformation sequence from word beginWord to word endWord using a dictionary wordList is a
    sequence of words beginWord -> s1 -> s2 -> ... -> sk such that:

Every adjacent pair of words differs by a single letter.
Every si for 1 <= i <= k is in wordList. Note that beginWord does not need to be in wordList.
sk == endWord
Given two words, beginWord and endWord, and a dictionary wordList, return the number of words in the
shortest transformation sequence from beginWord to endWord, or 0 if no such sequence exists.


Example 1:
Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log","cog"]
Output: 5
Explanation: One shortest transformation sequence is "hit" -> "hot" -> "dot" -> "dog" -> cog",
which is 5 words long.

Example 2:
Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log"]
Output: 0
Explanation: The endWord "cog" is not in wordList, therefore there is no valid transformation sequence.


Constraints:
1 <= beginWord.length <= 10
endWord.length == beginWord.length
1 <= wordList.length <= 5000
wordList[i].length == beginWord.length
beginWord, endWord, and wordList[i] consist of lowercase English letters.
beginWord != endWord
All the words in wordList are unique.*/

    /*Time & Space Complexity:
Let:
m = wordList.size
L = max word length

Time Complexity:
For each word we pop from the queue (at most m words),
we try changing each of its L positions,
and for each position we try 26 letters.
Checking set/visited is O(1).
So: O(m × L × 26) ≈ O(m × L)

Space Complexity:
set stores up to m words → O(m).
visited also up to m.
Queue can hold up to m.
Each word uses O(L) space when converted to char array.
So: O(m × L) total.
*/
    fun ladderLength(beginWord: String, endWord: String, wordList: List<String>): Int {
        val set: MutableSet<String> = mutableSetOf()
        set.addAll(wordList)

        if (!wordList.contains(endWord)) return 0
        val visited: MutableSet<String> = mutableSetOf()
        var result = 1
        val queue: Queue<String> = LinkedList()
        queue.add(beginWord)
        visited.add(beginWord)

        while (!queue.isEmpty()) {
            var size = queue.size
            while (size > 0) {
                val currWord = queue.poll()
                if (currWord == endWord) return result + 1
                for (i in currWord.indices) {
                    val wordArr = currWord.toCharArray()
                    for (ch in 'a'..'z') {
                        wordArr[i] = ch
                        val newWord = String(wordArr)
                        if (newWord == endWord) return result + 1
                        if (!set.contains(newWord)) continue
                        if (!visited.contains(newWord)) {
                            queue.add(newWord)
                            visited.add(newWord)
                        }
                    }
                }
                size--
            }
            result++
        }

        return 0
    }
}

fun main() {
    val obj = WordLadder()
    val beginWord = "hit"
    val endWord = "cog"
    val wordList = listOf("hot", "dot", "dog", "lot", "log", "cog")
    println(obj.ladderLength(beginWord, endWord, wordList))
}