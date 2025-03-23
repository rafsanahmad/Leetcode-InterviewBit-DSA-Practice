/*
 * *
 *  * OperatorFunction.kt
 *  * Created by Rafsan Ahmad on 3/15/25, 7:53PM
 *  * Copyright (c) 2025 . All rights reserved.
 *  *
 *
 */

package kotlinclasses.Concepts

class OperatorFunction {
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

class Colors(val name: String, val value: Int, val usable: Boolean) {
    operator fun component1(): String = name
    operator fun component2(): Int = value
    operator fun component3(): Boolean = usable
}

val colorMap = mapOf(
    1 to Colors("Red", 0, true),
    2 to Colors("Green", 1, false),
    3 to Colors("Blue", 2, true)
)

val showColor: (Colors) -> Unit = { (name, value, usable) ->
    if (usable) println("$name, $value is usable")
}

fun main() {
    val bluePen = Pen(inkColor = "Blue")
    bluePen.showInkColor()

    val blackPen = Pen(inkColor = "Black")
    blackPen.showInkColor()

    val blueBlackPen = bluePen + blackPen
    blueBlackPen.showInkColor()

    colorMap.mapValues { (_, value) -> showColor(value) }
}