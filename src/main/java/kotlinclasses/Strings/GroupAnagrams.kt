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
        val result: MutableList<List<String>> = arrayListOf()
        val map = hashMapOf<String, MutableList<String>>()

        if (strs.isEmpty()) {
            return result
        }

        val len = strs.size
        for (i in 0..<len) {
            val countArr = IntArray(26)
            for (j in 0..<strs[i].length) {
                countArr[strs[i][j] - 'a']++
            }

            val arrString = countArr.contentToString()

            if (map.containsKey(arrString)) {
                map[arrString]?.add(strs[i])
            } else {
                val list: MutableList<String> = arrayListOf()
                list.add(strs[i])
                map[arrString] = list
            }
        }

        result.addAll(map.values)
        return result
    }
}

fun main(args: Array<String>) {
    val anagrams = GroupAnagrams()
    val strArray = arrayOf("eat", "tea", "tan", "ate", "nat", "bat")
    println(anagrams.groupAnagrams(strArray))
}