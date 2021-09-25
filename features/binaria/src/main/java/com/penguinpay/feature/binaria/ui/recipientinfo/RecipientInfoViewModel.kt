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
) : CoroutinesViewModel<RecipientInfoViewState, RecipientInfoViewAction>(coroutineService, RecipientInfoViewState()) {

    lateinit var country: ExchangeCountryEntity

    data class RecipientInfoViewState(
        val isFormValid: Boolean = false,
        val firstNameField: FormFieldState<String> = FormFieldState.Prune(),
        val lastNameField: FormFieldState<String> = FormFieldState.Prune(),
        val phoneField: FormFieldState<String> = FormFieldState.Prune(),
    )

    sealed class RecipientInfoViewAction {
        data class GoNext(val firstName: String, val lastName: String, val phone: String) : RecipientInfoViewAction()
    }

    fun onViewCreated(country: ExchangeCountryEntity) {
        this.country = country
    }

    fun onPhoneChanged(phone: String) {
        with(stateValue) {
            if (phoneField.isValidEquals(phone).not()) {
                if (phone.length == country.phoneValidSize) {
                    _state.value = copy(phoneField = FormFieldState.Valid(phone))
                } else {
                    _state.value = copy(phoneField = FormFieldState.Invalid())
                }

                updateFormValidationState()
            }
        }
    }

    fun onFirstNameChanged(firstName: String) {
        with(stateValue) {
            if (firstNameField.isValidEquals(firstName).not()) {
                if (firstName.isNotBlank()) {
                    _state.value = copy(firstNameField = FormFieldState.Valid(firstName))
                } else {
                    _state.value = copy(firstNameField = FormFieldState.Invalid())
                }

                updateFormValidationState()
            }
        }
    }

    fun onLastNameChanged(lastName: String) {
        with(stateValue) {
            if (lastNameField.isValidEquals(lastName).not()) {
                if (lastName.isNotBlank()) {
                    _state.value = copy(lastNameField = FormFieldState.Valid(lastName))
                } else {
                    _state.value = copy(lastNameField = FormFieldState.Invalid())
                }

                updateFormValidationState()
            }
        }
    }

    private fun updateFormValidationState() {
        _state.value = stateValue.copy(isFormValid = stateValue.isFormValid())
    }

    fun onContinueButtonClick() {
        with(stateValue) {
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

    private fun RecipientInfoViewState.isFormValid() =
        firstNameField is FormFieldState.Valid
                && lastNameField is FormFieldState.Valid
                && phoneField is FormFieldState.Valid

    private fun <T> FormFieldState<T>.isValidEquals(value: T) = this is FormFieldState.Valid && this.value == value
}