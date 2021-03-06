/*
 * *
 *  * Created by Rafsan Ahmad on 10/25/21, 9:50 PM
 *  * Copyright (c) 2021 . All rights reserved.
 *
 */

package kotlinclasses

class BinarySearchExample {

    fun PerformbinarySearch(arr: IntArray, x: Int): Int {
        var start = 0
        var end = arr.size - 1
        while (start <= end) {
            var m = (start + end) / 2
            if (arr[m] == x) {
                return m
            }
            if (arr[m] < x) {
                //if x is greater = ignore left
                start = m + 1
            } else {
                //if x is smaller, ignore right
                end = m - 1
            }
        }
        return -1
    }
}

fun main(args: Array<String>) {
    var ob = BinarySearchExample()
    var arr = intArrayOf(2, 3, 4, 6, 8, 10, 40, 45, 60)
    var result = ob.PerformbinarySearch(arr, 47)
    if (result == -1) {
        println("Not found")
    } else {
        println(
            "Element found at "
                    + "index " + result
        )
    }
}