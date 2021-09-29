package com.penguinpay.feature.binaria.ui.countryselect

import com.google.common.truth.Truth.assertThat
import com.penguinpay.domain.exchange.entity.ExchangeCountryEntity
import com.penguinpay.domain.exchange.GetExchangeCountriesUseCase
import com.penguinpay.domain.exchange.GetExchangeCountriesUseCase.GetExchangeCountriesResult
import com.penguinpay.feature.binaria.ui.countryselect.CountrySelectionViewModel.CountrySelectionViewAction
import com.penguinpay.feature.binaria.ui.countryselect.CountrySelectionViewModel.CountrySelectionViewState
import com.penguinpay.libraries.coroutines.android.test.MockKViewModelTest
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import org.junit.Test

internal class CountrySelectionViewModelTest :
    MockKViewModelTest<CountrySelectionViewModel, CountrySelectionViewState, CountrySelectionViewAction>() {

    @MockK
    private lateinit var model: CountrySelectionModel

    @Test
    fun `viewModel Init States`() {
        withInitState {
            assertThat(this).isInstanceOf(CountrySelectionViewState.Loading::class.java)
        }
    }

    @Test
    fun `onStart SHOULD set Loading AND set Success WHEN getExchangeCountries returns`() {
        val entity1 = mockk<ExchangeCountryEntity>()
        val entity2 = mockk<ExchangeCountryEntity>()
        val list = arrayListOf(entity1, entity2)
        given {
            coEvery { model.getExchangeCountries() } returns list
        } whenViewModel {
            onStart()
        } then {
            onStates {
                hasSize(2)
                get(0) isA CountrySelectionViewState.Loading::class
                get(1) isA CountrySelectionViewState.Success::class and {
                    assertThat(countryList).isEqualTo(list)
                }
            }
        }
    }
}