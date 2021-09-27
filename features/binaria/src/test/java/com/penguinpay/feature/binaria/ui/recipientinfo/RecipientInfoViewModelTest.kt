package com.penguinpay.feature.binaria.ui.recipientinfo

import com.google.common.truth.Truth.assertThat
import com.penguinpay.domain.exchange.entity.ExchangeCountryEntity
import com.penguinpay.feature.binaria.ui.recipientinfo.RecipientInfoViewModel.RecipientInfoViewAction
import com.penguinpay.feature.binaria.ui.recipientinfo.RecipientInfoViewModel.RecipientInfoViewState
import com.penguinpay.feature.binaria.ui.recipientinfo.validator.FormFieldState
import com.penguinpay.libraries.coroutines.android.test.MockKViewModelTest
import io.mockk.mockk
import org.junit.Test

internal class RecipientInfoViewModelTest : MockKViewModelTest<RecipientInfoViewModel, RecipientInfoViewState, RecipientInfoViewAction>() {

    @Test
    fun `onViewCreated SHOULD set property country`() {
        // Given
        val country = mockk<ExchangeCountryEntity>()
        // When
        viewModel.onViewCreated(country)
        // Then
        assertThat(viewModel.country).isEqualTo(country)
    }

    @Test(expected = UninitializedPropertyAccessException::class)
    fun `ViewModel SHOULD throw UninitializedPropertyAccessException WHEN country is accessed before onViewCreated`() {
        // When
        viewModel.country
    }

    @Test
    fun `onPhoneChanged SHOULD set phoneField as Valid WHEN properly filled`() {
        given {
            val country = ExchangeCountryEntity(
                countryName = "Brazil",
                countryAcronym = "BRA",
                phonePrefix = "+55",
                phoneValidSize = 9
            )
            viewModel.onViewCreated(country)
        } whenViewModel {
            onPhoneChanged("123456789")
        } then {
            onStates {
                get(1) {
                    assertThat(isFormValid).isEqualTo(false)
                    assertThat(firstNameField).isInstanceOf(FormFieldState.Prune::class.java)
                    assertThat(lastNameField).isInstanceOf(FormFieldState.Prune::class.java)
                    assertThat(phoneField).isInstanceOf(FormFieldState.Valid::class.java)
                }
            }
        }
    }

    @Test
    fun `onFirstNameChanged SHOULD set firstName as Valid WHEN properly filled`() {
        whenViewModel {
            onFirstNameChanged("Andre")
        } then {
            onStates {
                get(1) {
                    assertThat(isFormValid).isEqualTo(false)
                    assertThat(firstNameField).isInstanceOf(FormFieldState.Valid::class.java)
                    assertThat(lastNameField).isInstanceOf(FormFieldState.Prune::class.java)
                    assertThat(phoneField).isInstanceOf(FormFieldState.Prune::class.java)
                }
            }
        }
    }

    @Test
    fun `onLastNameChanged SHOULD set lastName as Valid WHEN properly filled`() {
        whenViewModel {
            onLastNameChanged("Andre")
        } then {
            onStates {
                get(1) {
                    assertThat(isFormValid).isEqualTo(false)
                    assertThat(firstNameField).isInstanceOf(FormFieldState.Prune::class.java)
                    assertThat(lastNameField).isInstanceOf(FormFieldState.Valid::class.java)
                    assertThat(phoneField).isInstanceOf(FormFieldState.Prune::class.java)
                }
            }
        }
    }

    @Test
    fun `isFormValid SHOULD be true WHEN all fields are Valid`() {
        given {
            val country = ExchangeCountryEntity(
                countryName = "Brazil",
                countryAcronym = "BRA",
                phonePrefix = "+55",
                phoneValidSize = 9
            )
            viewModel.onViewCreated(country)
        } whenViewModel {
            onPhoneChanged("123456789")
            onFirstNameChanged("Andre")
            onLastNameChanged("Tortolano")
        } then {
            onStates {
                hasSize(6)
                getLast() and {
                    assertThat(isFormValid).isTrue()
                }
            }
        }
    }

    @Test
    fun `onContinueButtonClick SHOULD send GoNext if all fields are Valid and FormIsValid`() {
        given {
            val country = ExchangeCountryEntity(
                countryName = "Brazil",
                countryAcronym = "BRA",
                phonePrefix = "+55",
                phoneValidSize = 9
            )
            viewModel.onViewCreated(country)
            viewModel.onPhoneChanged("123456789")
            viewModel.onFirstNameChanged("Andre")
            viewModel.onLastNameChanged("Tortolano")
        } whenViewModel {
            onContinueButtonClick()
        } then {
            onActions {
                get(0) isA RecipientInfoViewAction.GoNext::class and {
                    assertThat(firstName).isEqualTo("Andre")
                    assertThat(lastName).isEqualTo("Tortolano")
                    assertThat(phone).isEqualTo("123456789")
                }
            }
        }
    }
}