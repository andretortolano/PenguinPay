package com.penguinpay.feature.binaria.ui.receipt

import com.google.common.truth.Truth.assertThat
import com.penguinpay.feature.binaria.ui.receipt.ReceiptViewModel.ReceiptViewAction
import com.penguinpay.feature.binaria.ui.receipt.ReceiptViewModel.ReceiptViewState
import com.penguinpay.libraries.coroutines.android.test.MockKViewModelTest
import org.junit.Test

internal class ReceiptViewModelTest : MockKViewModelTest<ReceiptViewModel, ReceiptViewState, ReceiptViewAction>() {

    @Test
    fun `onBackPressed SHOULD send Close`() {
        whenViewModel {
            onBackPressed()
        } then {
            withLastAction(1) {
                assertThat(this).isInstanceOf(ReceiptViewAction.Close::class.java)
            }
        }
    }

    @Test
    fun `onCloseClick SHOULD send Close`() {
        whenViewModel {
            onCloseClick()
        } then {
            withLastAction(1) {
                assertThat(this).isInstanceOf(ReceiptViewAction.Close::class.java)
            }
        }
    }
}