/*
 * *
 *  * Implement Trie (Prefix Tree).kt
 *  * Created by Rafsan Ahmad on 7/28/25, 3:56AM
 *  * Copyright (c) 2025 . All rights reserved.
 *  *
 *
 */

package kotlinclasses.DataStructure.Trie

class ImplementTrie {
    /*A trie (pronounced as "try") or prefix tree is a tree data structure used to efficiently
    store and retrieve keys in a dataset of strings. There are various applications of this data
    tructure, such as autocomplete and spellchecker.

Implement the Trie class:

Trie() Initializes the trie object.
void insert(String word) Inserts the string word into the trie.
boolean search(String word) Returns true if the string word is in the trie (i.e., was inserted
before), and false otherwise.
boolean startsWith(String prefix) Returns true if there is a previously inserted string word that
has the prefix prefix, and false otherwise.


Example 1:
Input
["Trie", "insert", "search", "search", "startsWith", "insert", "search"]
[[], ["apple"], ["apple"], ["app"], ["app"], ["app"], ["app"]]
Output
[null, null, true, false, true, null, true]

Explanation
Trie trie = new Trie();
trie.insert("apple");
trie.search("apple");   // return True
trie.search("app");     // return False
trie.startsWith("app"); // return True
trie.insert("app");
trie.search("app");     // return True


Constraints:

1 <= word.length, prefix.length <= 2000
word and prefix consist only of lowercase English letters.
At most 3 * 10^4 calls in total will be made to insert, search, and startsWith.*/

    /* Trie:
    root
     └── a
         └── p
             └── p (endOfWord = true)
                 └── l
                     └── e (endOfWord = true)

    "app" is a complete word
    "apple" is also a complete word
    */

    class TrieNode {
        val children: Array<TrieNode?> = Array(26) { null }
        var endOfWord = false
    }

    private val root: TrieNode = TrieNode()

    /*Time: O(n)
    Space: O(n)
    n = length of the input word or prefix
    */
    fun insert(word: String) {
        var node = root
        for (i in word.indices) {
            val index = word[i] - 'a'
            if (node.children[index] == null) {
                node.children[index] = TrieNode()
            }
            node.children[index]?.let {
                node = it
            }
        }

        node.endOfWord = true
    }

    /*Time: O(n)
    Space: O(n)*/
    fun match(index: Int, node: TrieNode?, word: String, fullMatch: Boolean): Boolean {
        if (node == null) return false
        //if (index == word.length && node.endOfWord && fullMatch) return true
        //if (index == word.length && !fullMatch) return true
        //if (index >= word.length) return false
        if (index == word.length) {
            return if (fullMatch) node.endOfWord else true
        }
        val nextNode = node.children[word[index] - 'a']
        return match(index + 1, nextNode, word, fullMatch)
    }

    /*Time: O(n)
    Space: O(1)*/
    fun search(word: String): Boolean {
        return match(0, root, word, true)
    }

    /*Time: O(n)
    Space: O(1)*/
    fun startsWith(prefix: String): Boolean {
        return match(0, root, prefix, false)
    }
}

fun main() {
    val obj = ImplementTrie()
    obj.insert("apple")
    println(obj.search("apple"))   // return True
    println(obj.search("app"))     // return False
    println(obj.startsWith("app")) // return True
    obj.insert("app");
    println(obj.search("app"))     // return True
}