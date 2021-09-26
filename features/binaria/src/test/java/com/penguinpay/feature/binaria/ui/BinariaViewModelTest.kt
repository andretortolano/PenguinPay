package com.penguinpay.feature.binaria.ui

import com.google.common.truth.Truth.assertThat
import com.penguinpay.domain.exchange.entity.ExchangeCountryEntity
import com.penguinpay.domain.transfer.entity.TransferReceiptEntity
import com.penguinpay.feature.binaria.ui.BinariaViewModel.BinariaViewAction
import com.penguinpay.feature.binaria.ui.BinariaViewModel.BinariaViewState
import com.penguinpay.libraries.coroutines.android.test.MockKViewModelTest
import io.mockk.every
import io.mockk.mockk
import org.junit.Test

internal class BinariaViewModelTest : MockKViewModelTest<BinariaViewModel, BinariaViewState, BinariaViewAction>() {

    @Test
    fun `onCountrySelected SHOULD send NavigateToRecipientInfo AND set country`() {
        // Given
        val country = mockk<ExchangeCountryEntity>()
        // When
        viewModel.onCountrySelected(country)
        // Then
        with(states) {
            assertThat(size).isEqualTo(1)
        }
        with(actions) {
            assertThat(size).isEqualTo(1)
            assertThat(this[0]).isInstanceOf(BinariaViewAction.NavigateToRecipientInfo::class.java)
        }
        assertThat(viewModel.country).isEqualTo(country)
    }

    @Test
    fun `onRecipientInfoFilled SHOULD send NavigateToSendRecipient AND set completeName AND set completePhone`() {
        // Given
        val phonePrefix = "+55"
        val firstName = "Andre"
        val lastName = "Tortolano"
        val phone = "1234"
        val country = ExchangeCountryEntity(String(), String(), phonePrefix, 8)
        // When
        viewModel.onCountrySelected(country)
        viewModel.onRecipientInfoFilled(firstName, lastName, phone)
        // Then
        with(states) {
            assertThat(size).isEqualTo(1)
        }
        with(actions) {
            assertThat(size).isEqualTo(2)
            assertThat(this[1]).isInstanceOf(BinariaViewAction.NavigateToSendRecipient::class.java)
        }
        assertThat(viewModel.completeName).isEqualTo("$firstName $lastName")
        assertThat(viewModel.completePhone).isEqualTo("$phonePrefix$phone")
    }

    @Test
    fun `onTransactionSent SHOULD send NavigateToReceipt AND set receipt`() {
        // Given
        val receipt = mockk<TransferReceiptEntity>()
        // When
        viewModel.onTransactionSent(receipt)
        // Then
        with(states) {
            assertThat(size).isEqualTo(1)
        }
        with(actions) {
            assertThat(size).isEqualTo(1)
            assertThat(this[0]).isInstanceOf(BinariaViewAction.NavigateToReceipt::class.java)
        }
        assertThat(viewModel.receipt).isEqualTo(receipt)
    }
}