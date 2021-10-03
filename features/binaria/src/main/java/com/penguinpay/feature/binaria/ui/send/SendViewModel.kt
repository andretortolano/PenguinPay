package com.penguinpay.feature.binaria.ui.send

import com.penguinpay.domain.transfer.entity.TransferBinaryReceiptEntity
import com.penguinpay.feature.binaria.ui.send.SendViewModel.SendViewAction
import com.penguinpay.feature.binaria.ui.send.SendViewModel.SendViewState
import com.penguinpay.libraries.coroutines.android.CoroutineService
import com.penguinpay.libraries.coroutines.android.CoroutinesViewModel

internal class SendViewModel(
    coroutineService: CoroutineService,
    private val model: SendModel,
) : CoroutinesViewModel<SendViewState, SendViewAction>(coroutineService, SendViewState()) {

    data class SendViewState(
        val isLoadingExchangeValue: Boolean = false,
        val isFormValid: Boolean = false,
        val isSending: Boolean = false,
        val usdBinary: String = String(),
        val recipientBinary: String = String(),
    )

    sealed class SendViewAction {
        data class SendTransfer(val receipt: TransferBinaryReceiptEntity) : SendViewAction()
        object NumberNotBinaryError : SendViewAction()
        object SomethingWentWrong : SendViewAction()
    }

    fun onUSDBinaryChanged(usdBinary: String, toCurrency: String) {
        if (usdBinary.trimStart('0').isEmpty() || stateValue.usdBinary == usdBinary) {
            return
        }

        scope.launchIdling {
            _state.value = stateValue.copy(
                isLoadingExchangeValue = true,
                isFormValid = false
            )

            model.getExchangedUSDBinaryAmount(toCurrency, usdBinary).run {
                _state.value = stateValue.copy(
                    isLoadingExchangeValue = false,
                    isFormValid = true,
                    usdBinary = usdBinary,
                    recipientBinary = this,
                )
            }
        }
    }

    fun onSendButtonClick(recipientName: String, recipientPhone: String, currency: String) {
        scope.launchIdling {
            _state.value = stateValue.copy(isSending = true)

            model.transferUSDBinary(
                recipientName = recipientName,
                recipientPhone = recipientPhone,
                usdBinaryAmount = stateValue.usdBinary,
                currency = currency
            ).run {
                _state.value = stateValue.copy(isSending = false)
                _action.value = SendViewAction.SendTransfer(this)
            }
        }
    }
}