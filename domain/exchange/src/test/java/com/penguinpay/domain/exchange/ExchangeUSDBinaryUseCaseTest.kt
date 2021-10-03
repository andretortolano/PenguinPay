package com.penguinpay.domain.exchange

import com.google.common.truth.Truth.assertThat
import com.penguinpay.domain.exchange.ExchangeUSDBinaryUseCase.ExchangeUSDBinaryRequest
import com.penguinpay.libraries.extensions.binary.toBinaryString
import io.mockk.coEvery
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test

@ExperimentalCoroutinesApi
class ExchangeUSDBinaryUseCaseTest {

    @MockK
    private lateinit var exchangeUSDUseCase: ExchangeUSDUseCase

    @InjectMockKs
    private lateinit var useCase: ExchangeUSDBinaryUseCase

    @Test
    fun `UseCase SHOULD call return exchangeUSDUseCase WITH amount 22 properly converted `() = runBlockingTest {
        // Given
        val currency = "BRA"
        val amount = 22.0
        val exchangedAmount = 30.0
        coEvery { exchangeUSDUseCase(ExchangeUSDUseCase.ExchangeUSDRequest(currency, amount)) } returns
                ExchangeUSDUseCase.ExchangeUSDResult(
                    exchangedAmount = exchangedAmount
                )
        // When
        val result = ExchangeUSDBinaryRequest(
            currency = currency,
            amount = amount.toBinaryString(),
        ).let { useCase(it) }
        // Then
        with(result) {
            assertThat(exchangedBinaryAmount).isEqualTo(exchangedAmount.toBinaryString())
        }
    }

}