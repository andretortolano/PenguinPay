package com.penguinpay.domain.exchange.interactor

import com.google.common.truth.Truth.assertThat
import com.penguinpay.domain.exchange.exception.ExchangeRateException
import com.penguinpay.domain.exchange.gateway.ExchangeRateGateway
import com.penguinpay.domain.exchange.interactor.ExchangeUSDUseCase.Request
import com.penguinpay.domain.exchange.interactor.ExchangeUSDUseCase.Result
import com.penguinpay.libraries.coroutines.test.MockKCoroutinesTest
import io.mockk.coEvery
import io.mockk.coVerifySequence
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test

@ExperimentalCoroutinesApi
class ExchangeUSDUseCaseTest : MockKCoroutinesTest() {

    @MockK
    private lateinit var exchangeGateway: ExchangeRateGateway

    @InjectMockKs
    private lateinit var useCase: ExchangeUSDUseCase


    @Test
    fun `UseCase SHOULD call exchangeGateway getExchangeRate from USD to ABC`() = runBlockingTest {
        // Given
        coEvery { exchangeGateway.getExchangeRate("USD", "ABC") } returns 0.0
        // When
        useCase(Request("ABC", 1.0))
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
        val result = useCase(Request("ABC", amount))
        // Then
        assertThat(result).isEqualTo(Result.Success(expectedExchanged))
    }

    @Test
    fun `UseCase SHOULD return SomethingWentWrong`() = runBlockingTest {
        // Given
        val amount = 5.0
        coEvery { exchangeGateway.getExchangeRate("USD", "ABC") } throws ExchangeRateException("A", "B")
        // When
        val result = useCase(Request("ABC", amount))
        // Then
        assertThat(result).isEqualTo(Result.SomethingWentWrong)
    }
}