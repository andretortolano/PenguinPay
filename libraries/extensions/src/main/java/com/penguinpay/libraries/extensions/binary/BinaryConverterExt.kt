package com.penguinpay.libraries.extensions.binary

import kotlin.math.pow
import kotlin.math.roundToInt

fun Int.toBinaryString(): String {
    return Integer.toBinaryString(this)
}

fun Double.toBinaryString(): String {
    return roundToInt().toBinaryString()
}

@Throws(NumberFormatException::class)
fun String.asBinaryToInt(): Int {
    if(matches("^[0-1]+$".toRegex()).not()) {
        throw NumberFormatException()
    }

    var num = this.toLong()
    var decimalNumber = 0
    var i = 0
    var remainder: Long

    while (num.toInt() != 0) {
        remainder = num % 10
        num /= 10
        decimalNumber += (remainder * 2.0.pow(i.toDouble())).toInt()
        ++i
    }
    return decimalNumber
}

fun String.asBinaryToDouble(): Double {
    return asBinaryToInt().toDouble()
}