package com.penguinpay.feature.binaria.gateway

import com.google.common.truth.Truth.assertThat
import com.penguinpay.data.remote.exchange.ExchangeRateDTO
import com.penguinpay.data.remote.exchange.ExchangeRemoteSource
import com.penguinpay.domain.exchange.gateway.ExchangeRateGateway
import com.penguinpay.libraries.coroutines.test.MockKCoroutinesTest
import io.mockk.coEvery
import io.mockk.coVerifySequence
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test

@ExperimentalCoroutinesApi
class CachingNetworkExchangeRateGatewayImplTest : MockKCoroutinesTest() {

    private val remoteSource = mockk<ExchangeRemoteSource>()

    private val gateway = CacheExchangeRateGatewayImpl(remoteSource)

    @Test
    fun `gateway SHOULD extend ExchangeRateGateway`() {
        assertThat(gateway).isInstanceOf(ExchangeRateGateway::class.java)
    }

    @Test
    fun `getExchangeRate SHOULD return proper exchange rate`() = runBlockingTest {
        // Given
        val baseCurrency = "USD"
        val recipientCurrency = "BRA"
        val rate = 2.0
        val exchangeRateDTO = ExchangeRateDTO(baseCurrency, recipientCurrency, rate)
        coEvery { remoteSource.getExchangeRates(baseCurrency, recipientCurrency) } returns exchangeRateDTO
        // When
        val result = gateway.getExchangeRate("USD", "BRA")
        // Then
        coVerifySequence {
            remoteSource.getExchangeRates(baseCurrency, recipientCurrency)
        }
        assertThat(result).isEqualTo(rate)
    }

    @Test
    fun `getExchangeRate SHOULD return from memory proper exchange rate WHEN called twice`() = runBlockingTest {
        // Given
        val baseCurrency = "USD"
        val recipientCurrency = "BRA"
        val rate = 2.0
        val exchangeRateDTO = ExchangeRateDTO(baseCurrency, recipientCurrency, rate)
        coEvery { remoteSource.getExchangeRates(baseCurrency, recipientCurrency) } returns exchangeRateDTO
        // When
        val result = gateway.getExchangeRate("USD", "BRA")
        val result2 = gateway.getExchangeRate("USD", "BRA")
        // Then
        coVerifySequence {
            remoteSource.getExchangeRates(baseCurrency, recipientCurrency)
        }
        assertThat(result).isEqualTo(rate)
        assertThat(result2).isEqualTo(rate)
    }
}