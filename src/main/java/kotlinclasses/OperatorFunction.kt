/*
 * *
 *  * Created by Rafsan Ahmad on 10/25/21, 9:50 PM
 *  * Copyright (c) 2021 . All rights reserved.
 *
 */

package kotlinclasses

class OperatorFunction {
}

fun main() {
    val bluePen = Pen(inkColor = "Blue")
    bluePen.showInkColor()

    val blackPen = Pen(inkColor = "Black")
    blackPen.showInkColor()

    val blueBlackPen = bluePen + blackPen
    blueBlackPen.showInkColor()
}

operator fun Pen.plus(otherPen: Pen): Pen {
    val ink = "$inkColor, ${otherPen.inkColor}"
    return Pen(inkColor = ink)
}

data class Pen(val inkColor: String) {
    fun showInkColor() {
        println(inkColor)
    }
}