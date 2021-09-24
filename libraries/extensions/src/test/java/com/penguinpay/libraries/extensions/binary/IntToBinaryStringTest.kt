package com.penguinpay.libraries.extensions.binary

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class IntToBinaryStringTest {

    @Test
    fun `ToBinaryString SHOULD Successfully convert this values`() {
        assertThat(22.toBinaryString()).isEqualTo("10110")
        assertThat(7953.toBinaryString()).isEqualTo("1111100010001")
        assertThat(700.toBinaryString()).isEqualTo("1010111100")
    }
}