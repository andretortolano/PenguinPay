package com.penguinpay.feature.binaria.ui

import com.penguinpay.domain.exchange.entity.ExchangeCountryEntity
import com.penguinpay.feature.binaria.ui.BinariaViewModel.BinariaViewAction
import com.penguinpay.feature.binaria.ui.BinariaViewModel.BinariaViewState
import com.penguinpay.libraries.coroutines.CoroutineService
import com.penguinpay.libraries.coroutines.android.CoroutinesViewModel

internal class BinariaViewModel(
    coroutineService: CoroutineService,
) : CoroutinesViewModel<BinariaViewState, BinariaViewAction>(coroutineService) {

    data class BinariaViewState(
        val country: ExchangeCountryEntity? = null,
        val firstName: String? = String(),
        val lastName: String? = String(),
        val transferUSDAmount: String = String(),
        val transferCurrencyAmount: String = String(),
    )

    sealed class BinariaViewAction {
        object NavigateToRecipientInfo : BinariaViewAction()
        object NavigateToSendRecipient : BinariaViewAction()
        object NavigateToReceipt : BinariaViewAction()
    }

    fun onCountrySelected(country: ExchangeCountryEntity) {
        _state.postValue(_state.value?.copy(country = country) ?: BinariaViewState().copy(country = country))
        _action.postValue(BinariaViewAction.NavigateToRecipientInfo)
    }

    fun onCreate() {
        _state.postValue(BinariaViewState())
    }
}