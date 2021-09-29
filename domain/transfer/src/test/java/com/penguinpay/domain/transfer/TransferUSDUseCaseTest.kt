package com.penguinpay.domain.transfer

import com.google.common.truth.Truth.assertThat
import com.penguinpay.domain.exchange.ExchangeUSDUseCase
import com.penguinpay.domain.exchange.ExchangeUSDUseCase.ExchangeUSDRequest
import com.penguinpay.domain.exchange.ExchangeUSDUseCase.ExchangeUSDResult
import com.penguinpay.domain.transfer.TransferUSDUseCase.TransferUSDRequest
import com.penguinpay.domain.transfer.gateway.TransferGateway
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerifySequence
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class TransferUSDUseCaseTest {

    @MockK
    private lateinit var transferGateway: TransferGateway

    @MockK
    private lateinit var exchangeUSDUseCase: ExchangeUSDUseCase

    @InjectMockKs
    private lateinit var useCase: TransferUSDUseCase

    @Before
    fun setup() {
        MockKAnnotations.init(this)
    }

    @Test
    fun `useCase SHOULD return TransferReceiptEntity WITH proper params`() = runBlockingTest {
        // Given
        val name = "Andre"
        val phone = "1234"
        val usdAmount = 5.0
        val toCurrency = "BRA"
        val exchangeAmount = 15.0
        coEvery { exchangeUSDUseCase(ExchangeUSDRequest(toCurrency, usdAmount)) } returns ExchangeUSDResult(exchangeAmount)
        coEvery { transferGateway.transfer(name, phone, toCurrency, exchangeAmount) } returns Unit
        // When
        val result = TransferUSDRequest(
            recipientName = name,
            recipientPhone = phone,
            usdAmount = usdAmount,
            currency = toCurrency,
        ).let { useCase(it) }
        // Then
        coVerifySequence {
            transferGateway.transfer(name, phone, toCurrency, exchangeAmount)
        }
        with(result.receipt) {
            assertThat(recipientName).isEqualTo(name)
            assertThat(recipientPhone).isEqualTo(phone)
            assertThat(amount).isEqualTo(exchangeAmount)
            assertThat(currency).isEqualTo(toCurrency)
        }
    }
}