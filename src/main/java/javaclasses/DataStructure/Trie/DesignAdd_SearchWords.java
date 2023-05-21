package javaclasses.DataStructure.Trie;


class TrieNode {
    boolean endOfWord;
    TrieNode[] children;

    public TrieNode() {
        endOfWord = false;
        children = new TrieNode[26];
    }
}

public class DesignAdd_SearchWords {
    //https://leetcode.com/problems/design-add-and-search-words-data-structure/
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

    static class WordDictionary {
        TrieNode root;

        public WordDictionary() {
            root = new TrieNode();
        }

        public void addWord(String word) {
            TrieNode node = root;

            for (char ch : word.toCharArray()) {
                if (node.children[ch - 'a'] == null) {
                    node.children[ch - 'a'] = new TrieNode();
                }

                node = node.children[ch - 'a'];
            }

            node.endOfWord = true;
        }

        public boolean search(String word) {
            return searchHelper(word, root, 0);
        }

        public boolean searchHelper(String word, TrieNode node, int index) {
            if (node == null) return false;

            if (index == word.length() && node.endOfWord) return true;

            if (index > word.length() - 1 && !node.endOfWord) return false;

            char ch = word.charAt(index);
            if (ch == '.') {
                for (char c = 'a'; c <= 'z'; c++) {
                    if (searchHelper(word, node.children[c - 'a'], index + 1)) {
                        return true;
                    }
                }
                return false;
            } else {
                return searchHelper(word, node.children[ch - 'a'], index + 1);
            }
        }
    }

    public static void main(String[] args) {
        DesignAdd_SearchWords words = new DesignAdd_SearchWords();
        WordDictionary wordDictionary = new WordDictionary();
        wordDictionary.addWord("bad");
        wordDictionary.addWord("dad");
        wordDictionary.addWord("mad");
        System.out.println(wordDictionary.search("pad")); // return False
        System.out.println(wordDictionary.search("bad")); // return True
        System.out.println(wordDictionary.search(".ad")); // return True
        System.out.println(wordDictionary.search("b..")); // return True

    }
}
