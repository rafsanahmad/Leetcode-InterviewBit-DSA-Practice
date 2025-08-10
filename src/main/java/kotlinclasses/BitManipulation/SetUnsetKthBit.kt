/*
 * *
 *  * Set Unset Kth Bit.kt
 *  * Created by Rafsan Ahmad on 8/4/25, 1:41PM
 *  * Copyright (c) 2025 . All rights reserved.
 *  *
 *
 */

package kotlinclasses.BitManipulation

class SetUnsetKthBit {
    fun setBit(num: Int, k: Int): Int {
        return num or (1 shl k)
    }

    fun unsetBit(num: Int, k: Int): Int {
        return num and (1 shl k).inv()
    }

    fun checkBitStatus(num: Int, k: Int): Int {
        return (num shr k) and 1
    }

    fun printBinary(label: String, num: Int) {
        println("$label = $num (binary: ${num.toString(2).padStart(8, '0')})")
    }
}

fun main() {
    val obj = SetUnsetKthBit()
    val num = 10  // binary: 00001010
    var k = 2     // SET 2nd bit

    obj.printBinary("Original", num)
    var bitStatus = obj.checkBitStatus(num, k)
    println("Check bit status $k: $bitStatus")
    // Set the 2nd bit
    val setResult = obj.setBit(num, k)
    //After setting bit 2 = 14 (binary: 00001110)
    obj.printBinary("After setting bit $k", setResult)

    bitStatus = obj.checkBitStatus(setResult, k)
    println("Check bit status $k: $bitStatus")

    // Unset the 3rd bit
    k = 3
    val unsetResult = obj.unsetBit(setResult, k)
    //After unsetting bit 3 = 6 (binary: 00000110)
    obj.printBinary("After unsetting bit $k", unsetResult)
    bitStatus = obj.checkBitStatus(unsetResult, k)
    println("Check bit status $k: $bitStatus")
}