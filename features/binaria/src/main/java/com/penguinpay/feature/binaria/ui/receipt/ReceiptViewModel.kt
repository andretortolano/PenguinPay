package com.penguinpay.feature.binaria.ui.receipt

import com.penguinpay.feature.binaria.ui.receipt.ReceiptViewModel.ReceiptViewAction
import com.penguinpay.feature.binaria.ui.receipt.ReceiptViewModel.ReceiptViewState
import com.penguinpay.libraries.coroutines.CoroutineService
import com.penguinpay.libraries.coroutines.android.CoroutinesViewModel

internal class ReceiptViewModel(
    coroutineService: CoroutineService,
) : CoroutinesViewModel<ReceiptViewState, ReceiptViewAction>(coroutineService, ReceiptViewState) {

    object ReceiptViewState

    sealed class ReceiptViewAction {
        object Close : ReceiptViewAction()
    }

    fun onBackPressed() {
        _action.value = ReceiptViewAction.Close
    }

    fun onCloseClick() {
        _action.value = ReceiptViewAction.Close
    }
}