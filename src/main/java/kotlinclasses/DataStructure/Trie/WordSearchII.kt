/*
 * *
 *  * Word Search II.kt
 *  * Created by Rafsan Ahmad on 8/28/25, 4:07PM
 *  * Copyright (c) 2025 . All rights reserved.
 *  *
 *
 */

package kotlinclasses.DataStructure.Trie

class WordSearchII {
    //https://leetcode.com/problems/word-search-ii/description/
    /*Given an m x n board of characters and a list of strings words, return all words on the board.

Each word must be constructed from letters of sequentially adjacent cells, where adjacent cells are
horizontally or vertically neighboring. The same letter cell may not be used more than once in a
word.



Example 1:
o a a n
e t a e
i h k r
i f l v
Input: board = [["o","a","a","n"],["e","t","a","e"],["i","h","k","r"],["i","f","l","v"]],
words = ["oath","pea","eat","rain"]
Output: ["eat","oath"]

Example 2:
Input: board = [["a","b"],["c","d"]], words = ["abcb"]
Output: []


Constraints:
m == board.length
n == board[i].length
1 <= m, n <= 12
board[i][j] is a lowercase English letter.
1 <= words.length <= 3 * 104
1 <= words[i].length <= 10
words[i] consists of lowercase English letters.
All the strings of words are unique.*/

    /*Time Complexity:
Trie build: inserting W words of average length L → O(W * L)
DFS traversal: Each cell (i, j) starts a DFS
Max DFS depth = length of longest word = L
At each step, lookup in trie is O(1)
So per cell, worst case O(4^L) (4 directions at each depth), but pruned heavily by trie
Total DFS: O(M * N * 4^L) worst case
Overall expected complexity: O(W * L + M * N * 4^L)
Practically faster because trie pruning cuts invalid paths early
In practice, the time complexity is closer to O(W * L + M * N * L).

Space Complexity:
L = Word average length
Trie storage: O(W * L) (all words)
Recursion stack: at most O(L) deep
Board modification: in-place (no extra space)
Result list: up to O(W) words
Total space: O(W * L + L) ≈ O(W * L)*/
    class TrieNode {
        // store 26 possible children for each letter
        val children = Array<TrieNode?>(26) { null }

        // store a complete word when we reach its end
        var word: String? = null
    }

    fun findWords(board: Array<CharArray>, words: Array<String>): List<String> {
        if (words.isEmpty() || board.isEmpty()) return listOf()

        val m = board.size
        val n = board[0].size
        val list: MutableList<String> = mutableListOf()

        // build Trie of all words
        val root = TrieNode()
        buildTrie(root, words)

        // try DFS from every cell in the board
        for (i in 0 until m) {
            for (j in 0 until n) {
                dfs(board, root, i, j, list)
            }
        }
        return list
    }

    fun buildTrie(root: TrieNode, words: Array<String>) {
        for (currWord in words) {
            var p: TrieNode? = root
            for (ch in currWord) {
                val index = ch - 'a'
                if (p?.children[index] == null) {
                    p?.children[index] = TrieNode() // create new node if needed
                }
                p = p?.children[index]
            }
            p?.word = currWord // mark end of word
        }
    }

    fun dfs(board: Array<CharArray>, node: TrieNode?, i: Int, j: Int, list: MutableList<String>) {
        // stop if out of bounds, already visited, or null trie node
        if (i >= board.size || i < 0 || j >= board[0].size || j < 0 || board[i][j] == '#' || node == null)
            return

        // move to the child corresponding to board[i][j]
        val index = board[i][j] - 'a'
        val nextNode = node.children[index] ?: return

        // found a word!
        nextNode.word?.let {
            list.add(it)
            nextNode.word = null // avoid duplicate entries
        }

        // mark current cell as visited
        val ch = board[i][j]
        board[i][j] = '#'

        // explore 4 directions
        dfs(board, nextNode, i + 1, j, list)
        dfs(board, nextNode, i - 1, j, list)
        dfs(board, nextNode, i, j + 1, list)
        dfs(board, nextNode, i, j - 1, list)

        // backtrack
        board[i][j] = ch
    }
}

fun main() {
    val obj = WordSearchII()
    val board = arrayOf(
        charArrayOf('o', 'a', 'a', 'n'),
        charArrayOf('e', 't', 'a', 'e'),
        charArrayOf('i', 'h', 'k', 'r'),
        charArrayOf('i', 'f', 'l', 'v')
    )
    val words = arrayOf("oath", "pea", "eat", "rain")

    println(obj.findWords(board, words))
}