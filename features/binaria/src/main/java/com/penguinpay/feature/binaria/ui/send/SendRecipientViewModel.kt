package com.penguinpay.feature.binaria.ui.send

import com.penguinpay.domain.exchange.interactor.ExchangeUSDBinaryUseCase
import com.penguinpay.domain.transfer.SendTransferBinaryUseCase
import com.penguinpay.domain.transfer.entity.TransferReceiptEntity
import com.penguinpay.feature.binaria.ui.send.SendRecipientViewModel.SendRecipientViewAction
import com.penguinpay.feature.binaria.ui.send.SendRecipientViewModel.SendRecipientViewState
import com.penguinpay.libraries.coroutines.CoroutineService
import com.penguinpay.libraries.coroutines.android.CoroutinesViewModel
import kotlinx.coroutines.delay

internal class SendRecipientViewModel(
    coroutineService: CoroutineService,
    private val exchangeUSDBinaryUseCase: ExchangeUSDBinaryUseCase,
    private val sendTransferBinaryUseCase: SendTransferBinaryUseCase
) : CoroutinesViewModel<SendRecipientViewState, SendRecipientViewAction>(coroutineService, SendRecipientViewState()) {

    data class SendRecipientViewState(
        val isLoadingExchangeValue: Boolean = false,
        val isFormValid: Boolean = false,
        val isSending: Boolean = false,
        val usdBinary: String = String(),
        val recipientBinary: String = String(),
    )

    sealed class SendRecipientViewAction {
        data class SendTransfer(val receipt: TransferReceiptEntity) : SendRecipientViewAction()
    }

    fun onUSDBinaryChanged(usdBinary: String) {
        if (usdBinary.trimStart('0').isEmpty() || stateValue.usdBinary == usdBinary) {
            return
        }

        scope.launchIdling {
            _state.value = stateValue.copy(
                isLoadingExchangeValue = true,
                isFormValid = false
            )

            // TODO insert Usecase
            delay(2000)

            _state.value = stateValue.copy(
                isLoadingExchangeValue = false,
                isFormValid = true,
                usdBinary = usdBinary,
                recipientBinary = "10101011010",
            )
        }
    }

    fun onSendButtonClick(recipientName: String, recipientPhone: String, recipientCountryName: String) {
        scope.launchIdling {
            _state.value = stateValue.copy(isSending = true)

            val receipt = sendTransferBinaryUseCase(
                SendTransferBinaryUseCase.Request(
                    recipientName = recipientName,
                    recipientPhone = recipientPhone,
                    recipientCountryName = recipientCountryName,
                    sentUSDBinary = stateValue.usdBinary,
                    receivedBinary = stateValue.recipientBinary,
                )
            )

            _state.value = stateValue.copy(isSending = false)
            _action.value = SendRecipientViewAction.SendTransfer(receipt)
        }
    }
}