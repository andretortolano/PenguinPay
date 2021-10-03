package com.penguinpay.domain.transfer

import com.google.common.truth.Truth.assertThat
import com.penguinpay.domain.transfer.TransferUSDBinaryUseCase.TransferUSDBinaryRequest
import com.penguinpay.domain.transfer.TransferUSDUseCase.TransferUSDRequest
import com.penguinpay.domain.transfer.TransferUSDUseCase.TransferUSDResponse
import com.penguinpay.domain.transfer.entity.TransferReceiptEntity
import com.penguinpay.libraries.extensions.binary.asBinaryToDouble
import com.penguinpay.libraries.extensions.binary.toBinaryString
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class TransferUSDBinaryUseCaseTest {

    @MockK
    private lateinit var transferUSDUseCase: TransferUSDUseCase

    @InjectMockKs
    private lateinit var useCase: TransferUSDBinaryUseCase

    @Before
    fun setup() {
        MockKAnnotations.init(this)
    }

    @Test
    fun `useCase SHOULD call Return TransferBinaryReceiptEntity WITH proper values`() = runBlockingTest {
        // Given
        val name = "Andre"
        val phone = "1234"
        val usdBinaryAmount = "101010"
        val currency = "BRA"
        val currencyAmount = 6.0
        coEvery {
            transferUSDUseCase(
                TransferUSDRequest(
                    recipientName = name,
                    recipientPhone = phone,
                    usdAmount = usdBinaryAmount.asBinaryToDouble(),
                    currency = currency,
                )
            )
        } returns TransferUSDResponse(
            receipt =  TransferReceiptEntity(
                recipientName = name,
                recipientPhone = phone,
                amount = currencyAmount,
                currency = currency,
            )
        )
        // When
        val result = TransferUSDBinaryRequest(
            recipientName = name,
            recipientPhone = phone,
            usdBinaryAmount = usdBinaryAmount,
            currency = currency,
        ).let { useCase(it) }
        // Then
        with(result.receipt) {
            assertThat(recipientName).isEqualTo(name)
            assertThat(recipientPhone).isEqualTo(phone)
            assertThat(amount).isEqualTo(currencyAmount.toBinaryString())
            assertThat(this@with.currency).isEqualTo(currency)
        }
    }
}