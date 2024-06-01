/*
 * *
 *  * TestClassKotlin.kt
 *  * Created by Rafsan Ahmad on 10/9/22, 2:40 PM
 *  * Copyright (c) 2022 . All rights reserved.
 *
 */

package kotlinclasses

fun bingo(board: Array<IntArray>, inputs: IntArray): String {
    // write your code in Kotlin
    val result = ""
    val size = board.size
    val allSets: MutableList<HashSet<Int>> = arrayListOf()
    var set: HashSet<Int>

    var visited = Array(size) { Array(size) { 0 } }

    //Add horizontal sets
    for (i in 0 until size) {
        set = hashSetOf()
        for (j in 0 until size) {
            set.add(board[i][j])
        }
        allSets.add(set)
    }

    //Add vertical sets
    for (i in 0 until size) {
        set = hashSetOf()
        for (j in 0 until size) {
            set.add(board[j][i])
        }
        allSets.add(set)
    }

    //Add Diagonal sets
    for (j in 0 until inputs.size) {

    }

    //Loop through markers & find if it exits in AllSets & check if all sets element is found
    //If found return sum of those element
    //Otherwise return -1
    return result
}

fun printFinalTemperature(
    initialMeasurement: Double,
    initialUnit: String,
    finalUnit: String,
    conversionFormula: (Double) -> Double
) {
    val finalMeasurement = String.format("%.2f", conversionFormula(initialMeasurement))
    println("Conversion from $initialUnit to $finalUnit is $finalMeasurement")
}

fun main(args: Array<String>) {
    printFinalTemperature(
        20.0,
        "C",
        "F"
    ) { it * 0.8 }
}