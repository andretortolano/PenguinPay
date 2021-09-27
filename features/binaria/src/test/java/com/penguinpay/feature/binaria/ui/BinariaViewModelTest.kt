package com.penguinpay.feature.binaria.ui

import com.google.common.truth.Truth.assertThat
import com.penguinpay.domain.exchange.entity.ExchangeCountryEntity
import com.penguinpay.domain.transfer.entity.TransferReceiptEntity
import com.penguinpay.feature.binaria.ui.BinariaViewModel.BinariaViewAction
import com.penguinpay.feature.binaria.ui.BinariaViewModel.BinariaViewState
import com.penguinpay.libraries.coroutines.android.test.MockKViewModelTest
import io.mockk.mockk
import org.junit.Test

internal class BinariaViewModelTest : MockKViewModelTest<BinariaViewModel, BinariaViewState, BinariaViewAction>() {

    @Test
    fun `onCountrySelected SHOULD send NavigateToRecipientInfo AND set country`() {
        val country = mockk<ExchangeCountryEntity>()

        whenViewModel {
            onCountrySelected(country)
        } then {
            withLastAction(1) {
                assertThat(this).isInstanceOf(BinariaViewAction.NavigateToRecipientInfo::class.java)
            }

            assertThat(viewModel.country).isEqualTo(country)
        }
    }

    @Test
    fun `onRecipientInfoFilled SHOULD send NavigateToSendRecipient AND set completeName AND set completePhone`() {
        val phonePrefix = "+55"
        val firstName = "Andre"
        val lastName = "Tortolano"
        val phone = "1234"

        given {
            viewModel.onCountrySelected(ExchangeCountryEntity(String(), String(), phonePrefix, 8))
        } whenViewModel {
            onRecipientInfoFilled(firstName, lastName, phone)
        } then {
            withLastAction(1) {
                assertThat(this).isInstanceOf(BinariaViewAction.NavigateToSendRecipient::class.java)
            }
        }
    }

    @Test
    fun `onTransactionSent SHOULD send NavigateToReceipt AND set receipt`() {
        val receipt = mockk<TransferReceiptEntity>()

        whenViewModel {
            onTransactionSent(receipt)
        } then {
            withLastAction(1) {
                assertThat(this).isInstanceOf(BinariaViewAction.NavigateToReceipt::class.java)
            }

            assertThat(viewModel.receipt).isEqualTo(receipt)
        }
    }
}