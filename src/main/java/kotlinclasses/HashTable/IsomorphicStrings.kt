/*
 * *
 *  * Isomorphic Strings.kt
 *  * Created by Rafsan Ahmad on 5/24/25, 2:07AM
 *  * Copyright (c) 2025 . All rights reserved.
 *  *
 *
 */

package kotlinclasses.HashTable

class IsomorphicStrings {
    //https://leetcode.com/problems/isomorphic-strings/description/
    /*Given two strings s and t, determine if they are isomorphic.

Two strings s and t are isomorphic if the characters in s can be replaced to get t.

All occurrences of a character must be replaced with another character while preserving the
order of characters. No two characters may map to the same character, but a character may
map to itself.



Example 1:
Input: s = "egg", t = "add"
Output: true

Explanation:
The strings s and t can be made identical by:

Mapping 'e' to 'a'.
Mapping 'g' to 'd'.

Example 2:
Input: s = "foo", t = "bar"
Output: false

Explanation:

The strings s and t can not be made identical as 'o' needs to be mapped to both 'a' and 'r'.

Example 3:
Input: s = "paper", t = "title"
Output: true

Constraints:

1 <= s.length <= 5 * 10^4
t.length == s.length
s and t consist of any valid ascii character.*/

    /*Why We Need Two Maps:
We need to:
Check s[i] → t[i] (one direction)
Check t[i] → s[i] (reverse direction)
Now:

If mapST[s[i]] is already mapped and ≠ t[i], return false.
If mapTS[t[i]] is already mapped and ≠ s[i], return false.

That ensures both:
One character in s maps to only one in t.
One character in t maps to only one in s.
This prevents duplicates in either direction.
*/
    fun isIsomorphic(s: String, t: String): Boolean {
        if (s.length != t.length) return false

        val mapS = HashMap<Char, Char>()
        val mapT = HashMap<Char, Char>()

        for (i in s.indices) {
            val chS = s[i]
            val chT = t[i]

            if (mapS.containsKey(chS)) {
                if (mapS[chS] != chT) return false
            } else {
                mapS[chS] = chT
            }

            if (mapT.containsKey(chT)) {
                if (mapT[chT] != chS) return false
            } else {
                mapT[chT] = chS
            }
        }

        return true
    }
}

fun main(args: Array<String>) {
    val isomorphicString = IsomorphicStrings()
    println(isomorphicString.isIsomorphic("foo", "bar"))
    println(isomorphicString.isIsomorphic("egg", "add"))
}