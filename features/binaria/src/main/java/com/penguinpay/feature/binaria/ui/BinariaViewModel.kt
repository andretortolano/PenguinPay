package com.penguinpay.feature.binaria.ui

import com.penguinpay.domain.exchange.entity.ExchangeCountryEntity
import com.penguinpay.feature.binaria.ui.BinariaViewModel.BinariaViewAction
import com.penguinpay.feature.binaria.ui.BinariaViewModel.BinariaViewAction.NavigateToRecipientInfo
import com.penguinpay.feature.binaria.ui.BinariaViewModel.BinariaViewAction.NavigateToSendRecipient
import com.penguinpay.feature.binaria.ui.BinariaViewModel.BinariaViewState
import com.penguinpay.libraries.coroutines.CoroutineService
import com.penguinpay.libraries.coroutines.android.CoroutinesViewModel

internal class BinariaViewModel(
    coroutineService: CoroutineService,
) : CoroutinesViewModel<BinariaViewState, BinariaViewAction>(coroutineService) {

    var country: ExchangeCountryEntity? = null
    var firstName: String = String()
    var lastName: String = String()
    var completePhone: String = String()
    var transferBinaryUSDAmount: String = String()
    var transferBinaryCurrencyAmount: String = String()

    class BinariaViewState

    sealed class BinariaViewAction {
        object NavigateToRecipientInfo : BinariaViewAction()
        object NavigateToSendRecipient : BinariaViewAction()
        object NavigateToReceipt : BinariaViewAction()
    }

    fun onCountrySelected(country: ExchangeCountryEntity) {
        this.country = country

        _action.postValue(NavigateToRecipientInfo)
    }

    fun onRecipientInfoFilled(firstName: String, lastName: String, phoneNumber: String) {
        this.firstName = firstName
        this.lastName = lastName
        this.country?.let {
            this.completePhone = it.phonePrefix + phoneNumber
        }

        _action.postValue(NavigateToSendRecipient)
    }
}