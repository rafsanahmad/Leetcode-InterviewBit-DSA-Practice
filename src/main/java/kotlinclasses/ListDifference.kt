/*
 * *
 *  * ListDifference.kt
 *  * Created by Rafsan Ahmad on 5/12/24, 6:10 PM
 *  * Copyright (c) 2024 . All rights reserved.
 *  *
 *
 */

package kotlinclasses

class ListDifference {

    //Find difference between two lists.
    data class User(val id: Int, val name: String)

    var list1 = listOf(User(1, "John"), User(2, "Fil"))
    var list2 = listOf(User(1, "John"), User(3, "Nappy"), User(2, "Fil"))

    fun bruteForce() {
        //Brute force
        var res = mutableListOf<User>()

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

    fun usingGroupBy() {
        //Using groupBy
        val sum = list1 + list2
        var res2 = sum.groupBy { it.id }
            .filter { it.value.size == 1 }
            .flatMap { it.value }

        println(res2)
    }

    fun usingHashMap() {
        //Using HashMap
        val sum = list1 + list2
        val userOccurrences = HashMap<User, Int>()
        for (user in sum) {
            val numberOfOccurrences = userOccurrences[user]
            userOccurrences[user] = if (numberOfOccurrences == null) 1 else numberOfOccurrences + 1
        }
        var res3 = sum.filter { user -> userOccurrences[user] == 1 }
        println(res3)
    }

    fun usingIDIntersect() {
        //Using Intersect ID's
        val sum = list1 + list2
        val list1Ids = list1.map { it.id }
        val list2Ids = list2.map { it.id }
        val commonIds = list1Ids.intersect(list2Ids)
        var res4 = sum.filter { it.id !in commonIds }
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