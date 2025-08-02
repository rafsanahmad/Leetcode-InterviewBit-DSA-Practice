/*
 * *
 *  * Design Add and Search Words Data Structure.kt
 *  * Created by Rafsan Ahmad on 7/29/25, 3:38PM
 *  * Copyright (c) 2025 . All rights reserved.
 *  *
 *
 */

package kotlinclasses.DataStructure.Trie

class DesignAddSearchWord {
    //https://leetcode.com/problems/design-add-and-search-words-data-structure/description/
    /*Design a data structure that supports adding new words and finding if a string matches any
    previously added string.

Implement the WordDictionary class:

WordDictionary() Initializes the object.
void addWord(word) Adds word to the data structure, it can be matched later.
bool search(word) Returns true if there is any string in the data structure that matches word or
false otherwise. word may contain dots '.' where dots can be matched with any letter.


Example:

Input
["WordDictionary","addWord","addWord","addWord","search","search","search","search"]
[[],["bad"],["dad"],["mad"],["pad"],["bad"],[".ad"],["b.."]]
Output
[null,null,null,null,false,true,true,true]

Explanation
WordDictionary wordDictionary = new WordDictionary();
wordDictionary.addWord("bad");
wordDictionary.addWord("dad");
wordDictionary.addWord("mad");
wordDictionary.search("pad"); // return False
wordDictionary.search("bad"); // return True
wordDictionary.search(".ad"); // return True
wordDictionary.search("b.."); // return True


Constraints:

1 <= word.length <= 25
word in addWord consists of lowercase English letters.
word in search consist of '.' or lowercase English letters.
There will be at most 2 dots in word for search queries.
At most 10^4 calls will be made to addWord and search.*/

    /*Intuition
A Trie acts like a decision tree, where every decision is a character, and you walk down paths that
match your query — character by character.

📦 Visualization Example:
Adding: "app", "apple", "apt", "bat", "banana"

root
├── a
│   ├── p
│   │   ├── p (end)
│   │   │   └── l
│   │   │       └── e (end)
│   └── t (end)
└── b
    ├── a
        ├── t (end)
        └── n
            └── a
                └── n
                    └── a (end)

Search "app" → fast!
Autocomplete "ap" → collect ["app", "apple", "apt"]

🔚 Summary
🔥 Tries are efficient for:
✅ Word existence check (fast, predictable)
✅ Prefix search and autocomplete
✅ Wildcard searches (like .at, c..)
✅ Space-saving for shared prefixes*/

    /*Time and Space Complexity
addWord(word)
Time: O(L), where L is the length of the word
Space: O(L) per word (new nodes created only if necessary)

search(word)
Time (worst case):
Without .: O(L)
With .: O(26^d) where d is number of dots (wildcards)
Space: O(1) auxiliary, or O(L) if recursion stack is counted
*/
    val root = TrieNode() // Root node of the Trie

    fun addWord(word: String) {
        var node = root

        for (ch in word) {
            val index = ch - 'a' // Calculate index (0–25) for the character
            if (node.children[index] == null) {
                node.children[index] = TrieNode() // Create new TrieNode if missing
            }
            node.children[index]?.let {
                node = it // Move to the next node
            }
        }
        node.endOfWord = true // Mark end of the word
    }

    fun search(word: String): Boolean {
        return searchHelper(0, word, root) // Start recursive search
    }

    fun searchHelper(index: Int, word: String, node: TrieNode?): Boolean {
        if (node == null) return false // No path, invalid

        // If reached end of word and this node marks a valid word
        if (index == word.length && node.endOfWord)
            return true

        // If past end but not a valid word
        if (index > word.length - 1 && !node.endOfWord) return false

        if (word[index] == '.') {
            // If current character is '.', try all possible children
            for (ch in 'a'..'z') {
                if (searchHelper(index + 1, word, node.children[ch - 'a'])) {
                    return true // If any path works, return true
                }
            }
        } else {
            val newNodeIndex = word[index] - 'a'
            val nextNode = node.children[newNodeIndex]
            return searchHelper(index + 1, word, nextNode) // Continue to next character
        }

        return false // No match found
    }
}

fun main() {
    val obj = DesignAddSearchWord()
    obj.addWord("bad")
    obj.addWord("dad")
    obj.addWord("mad")
    println(obj.search("pad")) // return False
    println(obj.search("bad")) // return True
    println(obj.search(".ad")) // return True
    println(obj.search("b..")) // return True
}