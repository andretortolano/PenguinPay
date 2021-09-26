package com.penguinpay.data.remote.exchange.retrofit

import com.google.common.truth.Truth.assertThat
import com.penguinpay.libraries.coroutines.test.MockKCoroutinesTest
import io.mockk.coEvery
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test

@ExperimentalCoroutinesApi
class RetrofitExchangeSourceTest : MockKCoroutinesTest() {

    @MockK
    private lateinit var service: OpenExchangeService

    @InjectMockKs
    private lateinit var source: RetrofitExchangeSource

    @Test
    fun `getUSDExchangeRates SHOULD return list of ExchangeRateDTO WITH proper values`() = runBlockingTest {
        // Given
        val response = OpenExchangeResponse(
            base = "USD",
            rates = mapOf(Pair("BRA", 6.0), Pair("JAP", 12.0))
        )
        coEvery { service.getLatestUSDExchanges("BRA,JAP") } returns response
        // When
        val result = source.getUSDExchangeRates(setOf("BRA", "JAP"))
        // Then
        with(result) {
            assertThat(size).isEqualTo(2)
            assertThat(this[0].baseCurrency).isEqualTo("USD")
            assertThat(this[0].exchangeCurrency).isEqualTo("BRA")
            assertThat(this[0].rate).isEqualTo(6.0)

            assertThat(this[1].baseCurrency).isEqualTo("USD")
            assertThat(this[1].exchangeCurrency).isEqualTo("JAP")
            assertThat(this[1].rate).isEqualTo(12.0)
        }
    }

    @Test
    fun `getExchangeRates SHOULD return ExchangeRate WITH proper values`() = runBlockingTest {
        // Given
        val response = OpenExchangeResponse(
            base = "USD",
            rates = mapOf(Pair("BRA", 6.0))
        )
        coEvery { service.getLatestExchanges("USD", "BRA") } returns response
        // When
        val result = source.getExchangeRates("USD", "BRA")
        // Then
        with(result) {
            assertThat(baseCurrency).isEqualTo("USD")
            assertThat(exchangeCurrency).isEqualTo("BRA")
            assertThat(rate).isEqualTo(6.0)
        }
    }
}