package com.penguinpay.feature.binaria.ui.countryselect

import com.google.common.truth.Truth.assertThat
import com.penguinpay.domain.exchange.entity.ExchangeCountryEntity
import com.penguinpay.domain.exchange.interactor.GetExchangeCountriesUseCase
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
    private lateinit var getExchangeCountriesUseCase: GetExchangeCountriesUseCase

    @Test
    fun `onStart SHOULD set Loading AND set Success WHEN getExchangeCountries returns`() {
        val entity1 = mockk<ExchangeCountryEntity>()
        val entity2 = mockk<ExchangeCountryEntity>()
        val list = arrayListOf(entity1, entity2)
        given {
            coEvery { getExchangeCountriesUseCase() } returns list
        } whenViewModel {
            onStart()
        } then {
            assertStatesSize(2)
            withState(0) {
                assertThat(this).isInstanceOf(CountrySelectionViewState.Loading::class.java)
            }
            withState(1) {
                assertThat(this).isInstanceOf(CountrySelectionViewState.Success::class.java)
                (this as CountrySelectionViewState.Success).run {
                    assertThat(countryList).isEqualTo(list)
                }
            }
        }
    }
}