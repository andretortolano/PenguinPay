package com.penguinpay.domain.transfer.interactor

import com.google.common.truth.Truth.assertThat
import com.penguinpay.libraries.coroutines.test.MockKCoroutinesTest
import io.mockk.impl.annotations.InjectMockKs
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test

@ExperimentalCoroutinesApi
class SendTransferUSDBinaryUseCaseTest : MockKCoroutinesTest() {

    @InjectMockKs
    private lateinit var useCase: SendTransferUSDBinaryUseCase

    @Test
    fun `useCase SHOULD return receipt`() = runBlockingTest(dispatching.IO) {
        // Given
        val request = SendTransferUSDBinaryUseCase.Request(
            recipientName = "Andre",
            recipientPhone = "1234",
            sentUSDBinary = "1111",
            currency = "BRA",
            receivedBinary = "101010"
        )
        // When
        val result = useCase(request)
        // Then
        with(result) {
            assertThat(recipientName).isEqualTo(request.recipientName)
            assertThat(recipientPhone).isEqualTo(request.recipientPhone)
            assertThat(valueBinary).isEqualTo(request.receivedBinary)
            assertThat(currency).isEqualTo(request.currency)
        }
    }
}