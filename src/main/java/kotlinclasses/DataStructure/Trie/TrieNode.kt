/*
 * *
 *  * Trie Node.kt
 *  * Created by Rafsan Ahmad on 7/29/25, 3:38PM
 *  * Copyright (c) 2025 . All rights reserved.
 *  *
 *
 */

package kotlinclasses.DataStructure.Trie

class TrieNode {
    val children: Array<TrieNode?> = Array(26) { null }
    var endOfWord = false
}