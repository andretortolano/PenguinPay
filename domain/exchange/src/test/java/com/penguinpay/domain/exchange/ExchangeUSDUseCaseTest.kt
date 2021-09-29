package com.penguinpay.domain.exchange

import com.google.common.truth.Truth.assertThat
import com.penguinpay.domain.exchange.ExchangeUSDUseCase.ExchangeUSDRequest
import com.penguinpay.domain.exchange.gateway.ExchangeRateGateway
import io.mockk.coEvery
import io.mockk.coVerifySequence
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test

@ExperimentalCoroutinesApi
class ExchangeUSDUseCaseTest {

    @MockK
    private lateinit var exchangeGateway: ExchangeRateGateway

    @InjectMockKs
    private lateinit var useCase: ExchangeUSDUseCase


    @Test
    fun `UseCase SHOULD call exchangeGateway getExchangeRate from USD to ABC`() = runBlockingTest {
        // Given
        coEvery { exchangeGateway.getExchangeRate("USD", "ABC") } returns 0.0
        // When
        ExchangeUSDRequest(
            currency = "BRA",
            amount = 1.0,
        ).let { useCase(it) }
        // Then
        coVerifySequence {
            exchangeGateway.getExchangeRate("USD", "ABC")
        }
    }

    @Test
    fun `UseCase SHOULD return Success`() = runBlockingTest {
        // Given
        val rate = 2.0
        val amount = 5.0
        val expectedExchanged = 10.0
        coEvery { exchangeGateway.getExchangeRate("USD", "ABC") } returns rate
        // When
        val result = ExchangeUSDRequest(
            currency = "BRA",
            amount = amount,
        ).let { useCase(it) }
        // Then
        assertThat(result.exchangedAmount).isEqualTo(expectedExchanged)
    }
}