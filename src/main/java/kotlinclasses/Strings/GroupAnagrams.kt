/*
 * *
 *  * Group Anagrams.kt
 *  * Created by Rafsan Ahmad on 3/31/25, 2:26PM
 *  * Copyright (c) 2025 . All rights reserved.
 *  *
 *
 */

package kotlinclasses.Strings

class GroupAnagrams {
    //https://leetcode.com/problems/group-anagrams/
    /*Given an array of strings strs, group the anagrams together. You can return the answer in
    any order.

Example 1:

Input: strs = ["eat","tea","tan","ate","nat","bat"]

Output: [["bat"],["nat","tan"],["ate","eat","tea"]]

Explanation:

There is no string in strs that can be rearranged to form "bat".
The strings "nat" and "tan" are anagrams as they can be rearranged to form each other.
The strings "ate", "eat", and "tea" are anagrams as they can be rearranged to form each other.

Example 2:
Input: strs = [""]
Output: [[""]]

Example 3:
Input: strs = ["a"]
Output: [["a"]]

 Constraints:
1 <= strs.length <= 10^4
0 <= strs[i].length <= 100
strs[i] consists of lowercase English letters.*/

    fun groupAnagrams(strs: Array<String>): List<List<String>> {
        val result: MutableList<List<String>> = mutableListOf()
        val map: MutableMap<String, MutableList<String>> = mutableMapOf()
        //val map = hashMapOf<String, MutableList<String>>()
        if (strs.isEmpty()) return result

        for (i in strs.indices) {
            val str = strs[i]
            val countArr = IntArray(26) { 0 }
            for (j in str.indices) {
                countArr[str[j] - 'a']++
            }
            val newStr = countArr.contentToString()
            if (map.containsKey(newStr)) {
                map[newStr]?.add(str)
            } else {
                map[newStr] = mutableListOf(str)
            }
        }

        for (entry in map.entries) {
            result.add(entry.value)
        }
        //result.addAll(map.values)

        return result
    }
}

fun main(args: Array<String>) {
    val anagrams = GroupAnagrams()
    val strArray = arrayOf("eat", "tea", "tan", "ate", "nat", "bat")
    println(anagrams.groupAnagrams(strArray))
}