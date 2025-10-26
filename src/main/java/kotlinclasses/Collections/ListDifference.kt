/*
 * *
 *  * ListDifference.kt
 *  * Created by Rafsan Ahmad on 3/15/25, 7:51PM
 *  * Copyright (c) 2025 . All rights reserved.
 *  *
 *
 */

package kotlinclasses.Collections

class ListDifference {

    //Find difference between two lists.
    data class User(val id: Int, val name: String)

    var list1 = listOf(User(1, "John"), User(2, "Fil"))
    var list2 = listOf(User(1, "John"), User(3, "Nappy"), User(2, "Fil"))

    /*Complexity:
n = list1.size
m = list2.size
Time: O(n × m)
Space: O(n + m) (for result list)*/
    fun bruteForce() {
        //Brute force
        val res = mutableListOf<User>()

        for (user in list1) {
            if (!list2.contains(user))
                res.add(user)
        }
        for (user in list2) {
            if (!list1.contains(user))
                res.add(user)
        }

        println(res)
    }

    /*Complexity:
Time: O(n + m)
Space: O(n + m) (for grouping map)*/
    fun usingGroupBy() {
        //Using groupBy
        val sum = list1 + list2
        val res2 = sum.groupBy { it.id }
            .filter { it.value.size == 1 }
            .flatMap { it.value }

        println(res2)
    }

    /*Time: O(n + m)
Space: O(n + m) (for hashmap)*/
    fun usingHashMap() {
        //Using HashMap
        val sum = list1 + list2
        val userOccurrences = HashMap<User, Int>()
        for (user in sum) {
            userOccurrences[user] = userOccurrences.getOrDefault(user, 0) + 1;
        }
        val res3 = sum.filter { user -> userOccurrences[user] == 1 }
        println(res3)
    }

    /*Time: O(n + m)
Space: O(n + m)*/
    fun usingIDIntersect() {
        //Using Intersect ID's
        val sum = list1 + list2
        val list1Ids = list1.map { it.id }
        val list2Ids = list2.map { it.id }
        val commonIds = list1Ids.intersect(list2Ids)
        val res4 = sum.filter { it.id !in commonIds }
        println(res4)
    }
}

fun main(args: Array<String>) {
    val listDiff = ListDifference()
    listDiff.bruteForce()
    listDiff.usingGroupBy()
    listDiff.usingHashMap()
    listDiff.usingIDIntersect()
}