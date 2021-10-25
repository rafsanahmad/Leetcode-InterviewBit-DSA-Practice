/*
 * *
 *  * Created by Rafsan Ahmad on 10/25/21, 9:50 PM
 *  * Copyright (c) 2021 . All rights reserved.
 *
 */

package kotlinclasses

import java.util.*

class RandomPickWithWeight {

    lateinit var arr: IntArray
    var counter = 0

    fun RandomPickWithWeight(w: IntArray) {
        arr = IntArray(w.size)
        counter += arr[0]
        arr[0] = w[0]
        for (i in 1..w.size) {
            arr[i] = arr[i - 1] + w[i]
            counter += w[i]
        }
    }

    fun pickIndex(): Int {
        var idx = (1..this.counter).random()
        var res = Arrays.binarySearch(arr, idx)
        return res
    }

}