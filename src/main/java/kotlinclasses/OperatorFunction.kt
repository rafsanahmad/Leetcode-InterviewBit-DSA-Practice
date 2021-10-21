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