package com.penguinpay.feature.binaria.ui.send

import com.penguinpay.domain.exchange.interactor.ExchangeUSDBinaryUseCase
import com.penguinpay.domain.transfer.interactor.SendTransferUSDBinaryUseCase
import com.penguinpay.domain.transfer.entity.TransferReceiptEntity
import com.penguinpay.feature.binaria.ui.send.SendViewModel.SendViewAction
import com.penguinpay.feature.binaria.ui.send.SendViewModel.SendViewState
import com.penguinpay.libraries.coroutines.CoroutineService
import com.penguinpay.libraries.coroutines.android.CoroutinesViewModel

internal class SendViewModel(
    coroutineService: CoroutineService,
    private val exchangeUSDBinaryUseCase: ExchangeUSDBinaryUseCase,
    private val sendTransferUSDBinaryUseCase: SendTransferUSDBinaryUseCase
) : CoroutinesViewModel<SendViewState, SendViewAction>(coroutineService, SendViewState()) {

    data class SendViewState(
        val isLoadingExchangeValue: Boolean = false,
        val isFormValid: Boolean = false,
        val isSending: Boolean = false,
        val usdBinary: String = String(),
        val recipientBinary: String = String(),
    )

    sealed class SendViewAction {
        data class SendTransfer(val receipt: TransferReceiptEntity) : SendViewAction()
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

            exchangeUSDBinaryUseCase(ExchangeUSDBinaryUseCase.Request(toCurrency, usdBinary)).run {
                when (this) {
                    ExchangeUSDBinaryUseCase.Result.AmountIsNotBinaryError -> {
                        _action.value = SendViewAction.NumberNotBinaryError
                    }
                    ExchangeUSDBinaryUseCase.Result.SomethingWentWrong -> {
                        _action.value = SendViewAction.SomethingWentWrong
                    }
                    is ExchangeUSDBinaryUseCase.Result.Success -> {
                        _state.value = stateValue.copy(
                            isLoadingExchangeValue = false,
                            isFormValid = true,
                            usdBinary = usdBinary,
                            recipientBinary = this.amount,
                        )
                    }
                }

            }
        }
    }

    fun onSendButtonClick(recipientName: String, recipientPhone: String, currency: String) {
        scope.launchIdling {
            _state.value = stateValue.copy(isSending = true)

            val receipt = sendTransferUSDBinaryUseCase(
                SendTransferUSDBinaryUseCase.Request(
                    recipientName = recipientName,
                    recipientPhone = recipientPhone,
                    sentUSDBinary = stateValue.usdBinary,
                    currency = currency,
                    receivedBinary = stateValue.recipientBinary,
                )
            )

            _state.value = stateValue.copy(isSending = false)
            _action.value = SendViewAction.SendTransfer(receipt)
        }
    }
}