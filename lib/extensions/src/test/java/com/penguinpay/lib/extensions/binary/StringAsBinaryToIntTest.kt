package com.penguinpay.lib.extensions.binary

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class StringAsBinaryToIntTest {

    @Test
    fun `asBinaryToString SHOULD Successfully convert this values`() {
        assertThat("10110".asBinaryToString()).isEqualTo(22)
        assertThat("1111100010001".asBinaryToString()).isEqualTo(7953)
        assertThat("01111100010001".asBinaryToString()).isEqualTo(7953)
        assertThat("1010111100".asBinaryToString()).isEqualTo(700)
    }

    @Test(expected = NumberFormatException::class)
    fun `asBinaryToString SHOULD throw NumberFormatException if String is not just 0 and 1`() {
        println("123".asBinaryToString())
        println("012310".asBinaryToString())
        println("01010101A".asBinaryToString())
        println("B1010101".asBinaryToString())
        println("201010101".asBinaryToString())
    }
}