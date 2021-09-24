package com.penguinpay.libraries.extensions.binary

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class StringAsBinaryToIntTest {

    @Test
    fun `asBinaryToString SHOULD Successfully convert this values`() {
        assertThat("10110".asBinaryToInt()).isEqualTo(22)
        assertThat("1111100010001".asBinaryToInt()).isEqualTo(7953)
        assertThat("01111100010001".asBinaryToInt()).isEqualTo(7953)
        assertThat("1010111100".asBinaryToInt()).isEqualTo(700)
    }

    @Test(expected = NumberFormatException::class)
    fun `asBinaryToString SHOULD throw NumberFormatException if String is not just 0 and 1`() {
        println("123".asBinaryToInt())
        println("012310".asBinaryToInt())
        println("01010101A".asBinaryToInt())
        println("B1010101".asBinaryToInt())
        println("201010101".asBinaryToInt())
    }
}