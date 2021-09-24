package com.penguinpay.domain.exchange.interactor

import com.google.common.truth.Truth.assertThat
import com.penguinpay.domain.exchange.interactor.ExchangeUSDBinaryUseCase.Request
import com.penguinpay.domain.exchange.interactor.ExchangeUSDBinaryUseCase.Result
import com.penguinpay.domain.exchange.testing.MockKCoroutinesTest
import com.penguinpay.libraries.extensions.binary.toBinaryString
import io.mockk.coEvery
import io.mockk.coVerifySequence
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test
import kotlin.math.roundToInt

@ExperimentalCoroutinesApi
class ExchangeUSDBinaryUseCaseTest : MockKCoroutinesTest() {

    @MockK
    private lateinit var exchangeUSDUseCase: ExchangeUSDUseCase

    @InjectMockKs
    private lateinit var useCase: ExchangeUSDBinaryUseCase

    @Test
    fun `UseCase SHOULD call exchangeUSDUseCase WITH amount 22 properly converted `() = runBlockingTest {
        // Given
        val amount = 22.0
        val binaryAmount = amount.roundToInt().toBinaryString()
        coEvery { exchangeUSDUseCase(ExchangeUSDUseCase.Request("ABC", amount)) } returns ExchangeUSDUseCase.Result.Success(1.0)
        // When
        useCase(Request("ABC", binaryAmount))
        // Then
        coVerifySequence {
            exchangeUSDUseCase(ExchangeUSDUseCase.Request("ABC", amount))
        }
    }

    @Test
    fun `UseCase SHOULD exchange Successfully`() = runBlockingTest {
        // Given
        val amount = 22.0
        val binaryAmount = amount.roundToInt().toBinaryString()
        val exchangedAmount = 26.0
        val binaryExchangedAmount = exchangedAmount.roundToInt().toBinaryString()
        coEvery { exchangeUSDUseCase(ExchangeUSDUseCase.Request("ABC", amount)) } returns ExchangeUSDUseCase.Result.Success(exchangedAmount)
        // When
        val result = useCase(Request("ABC", binaryAmount))
        // Then
        assertThat(result).isEqualTo(Result.Success(binaryExchangedAmount))
    }

    @Test
    fun `UseCase SHOULD return AmountIsNotBinaryError WHEN amount is invalid`() = runBlockingTest {
        // Given
        val amount = 22.0
        val binaryAmount = "A1234"
        val exchangedAmount = 26.0
        coEvery { exchangeUSDUseCase(ExchangeUSDUseCase.Request("ABC", amount)) } returns ExchangeUSDUseCase.Result.Success(exchangedAmount)
        // When
        val result = useCase(Request("ABC", binaryAmount))
        // Then
        assertThat(result).isEqualTo(Result.AmountIsNotBinaryError)
    }

    @Test
    fun `UseCase SHOULD return SomethingWentWrong WHEN exchangeUSDUseCase returns error`() = runBlockingTest {
        // Given
        val amount = 22.0
        val binaryAmount = amount.roundToInt().toBinaryString()
        val exchangedAmount = 26.0
        coEvery { exchangeUSDUseCase(ExchangeUSDUseCase.Request("ABC", amount)) } returns ExchangeUSDUseCase.Result.SomethingWentWrong
        // When
        val result = useCase(Request("ABC", binaryAmount))
        // Then
        assertThat(result).isEqualTo(Result.SomethingWentWrong)
    }
}