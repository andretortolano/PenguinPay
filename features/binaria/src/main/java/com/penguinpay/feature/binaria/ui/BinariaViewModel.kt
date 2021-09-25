package com.penguinpay.feature.binaria.ui

import com.penguinpay.domain.exchange.entity.ExchangeCountryEntity
import com.penguinpay.domain.transfer.entity.TransferReceiptEntity
import com.penguinpay.feature.binaria.ui.BinariaViewModel.BinariaViewAction
import com.penguinpay.feature.binaria.ui.BinariaViewModel.BinariaViewAction.NavigateToRecipientInfo
import com.penguinpay.feature.binaria.ui.BinariaViewModel.BinariaViewAction.NavigateToSendRecipient
import com.penguinpay.feature.binaria.ui.BinariaViewModel.BinariaViewState
import com.penguinpay.libraries.coroutines.CoroutineService
import com.penguinpay.libraries.coroutines.android.CoroutinesViewModel

internal class BinariaViewModel(
    coroutineService: CoroutineService,
) : CoroutinesViewModel<BinariaViewState, BinariaViewAction>(coroutineService, BinariaViewState()) {

    var country: ExchangeCountryEntity? = null
    var completeName = String()
    var completePhone: String = String()
    var receipt: TransferReceiptEntity? = null

    class BinariaViewState

    sealed class BinariaViewAction {
        object NavigateToRecipientInfo : BinariaViewAction()
        object NavigateToSendRecipient : BinariaViewAction()
        object NavigateToReceipt : BinariaViewAction()
    }

    fun onCountrySelected(country: ExchangeCountryEntity) {
        this.country = country

        _action.value = NavigateToRecipientInfo
    }

    fun onRecipientInfoFilled(firstName: String, lastName: String, phoneNumber: String) {
        this.completeName = "$firstName $lastName"

        this.country?.let {
            this.completePhone = it.phonePrefix + phoneNumber
        }

        _action.value = NavigateToSendRecipient
    }

    fun onTransactionSent(receipt: TransferReceiptEntity) {
        this.receipt = receipt

        _action.value = BinariaViewAction.NavigateToReceipt
    }
}