package com.penguinpay.feature.binaria.ui

import com.google.common.truth.Truth.assertThat
import com.penguinpay.domain.exchange.entity.ExchangeCountryEntity
import com.penguinpay.domain.transfer.entity.TransferBinaryReceiptEntity
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
            onActions {
                get(0) isA BinariaViewAction.NavigateToRecipientInfo::class
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
            onActions {
                get(0) isA BinariaViewAction.NavigateToSendRecipient::class
            }
        }
    }

    @Test
    fun `onTransactionSent SHOULD send NavigateToReceipt AND set receipt`() {
        val receipt = mockk<TransferBinaryReceiptEntity>()

        whenViewModel {
            onTransactionSent(receipt)
        } then {
            onActions {
                get(0) isA BinariaViewAction.NavigateToReceipt::class
            }

            assertThat(viewModel.receipt).isEqualTo(receipt)
        }
    }
}