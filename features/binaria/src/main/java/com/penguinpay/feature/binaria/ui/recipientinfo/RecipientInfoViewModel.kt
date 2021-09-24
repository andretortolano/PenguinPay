package com.penguinpay.feature.binaria.ui.recipientinfo

import com.penguinpay.domain.exchange.entity.ExchangeCountryEntity
import com.penguinpay.feature.binaria.ui.recipientinfo.RecipientInfoViewModel.RecipientInfoViewAction
import com.penguinpay.feature.binaria.ui.recipientinfo.RecipientInfoViewModel.RecipientInfoViewAction.GoNext
import com.penguinpay.feature.binaria.ui.recipientinfo.RecipientInfoViewModel.RecipientInfoViewState
import com.penguinpay.feature.binaria.ui.recipientinfo.validator.FormFieldState
import com.penguinpay.libraries.coroutines.CoroutineService
import com.penguinpay.libraries.coroutines.android.CoroutinesViewModel

internal class RecipientInfoViewModel(
    coroutineService: CoroutineService
) : CoroutinesViewModel<RecipientInfoViewState, RecipientInfoViewAction>(coroutineService) {

    lateinit var country: ExchangeCountryEntity

    data class RecipientInfoViewState(
        val firstNameField: FormFieldState<String> = FormFieldState.Prune(),
        val lastNameField: FormFieldState<String> = FormFieldState.Prune(),
        val phoneField: FormFieldState<String> = FormFieldState.Prune(),
    )

    sealed class RecipientInfoViewAction {
        data class GoNext(val firstName: String, val lastName: String, val phone: String) : RecipientInfoViewAction()
    }

    fun onStart(country: ExchangeCountryEntity) {
        this.country = country

        _state.value = RecipientInfoViewState()
    }

    fun onPhoneChanged(phone: String) {
        if (phone.length == country.phoneValidSize) {
            _state.value = _state.value!!.copy(phoneField = FormFieldState.Valid(phone))
        } else {
            _state.value = _state.value!!.copy(phoneField = FormFieldState.Invalid())
        }

        goNextWhenAllValid()
    }

    fun onFirstNameChanged(firstName: String) {
        if (firstName.isNotBlank()) {
            _state.value = _state.value!!.copy(firstNameField = FormFieldState.Valid(firstName))
        } else {
            _state.value = _state.value!!.copy(firstNameField = FormFieldState.Invalid())
        }

        goNextWhenAllValid()
    }

    fun onLastNameChanged(lastName: String) {
        if (lastName.isNotBlank()) {
            _state.value = _state.value!!.copy(lastNameField = FormFieldState.Valid(lastName))
        } else {
            _state.value = _state.value!!.copy(lastNameField = FormFieldState.Invalid())
        }

        goNextWhenAllValid()
    }

    private fun goNextWhenAllValid() {
        with(_state.value!!) {
            if (firstNameField is FormFieldState.Valid
                && lastNameField is FormFieldState.Valid
                && phoneField is FormFieldState.Valid
            ) {
                _action.postValue(
                    GoNext(
                        firstName = firstNameField.value,
                        lastName = lastNameField.value,
                        phone = phoneField.value,
                    )
                )
            }
        }
    }

}