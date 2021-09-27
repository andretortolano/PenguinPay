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
        // Given
        val entity1 = mockk<ExchangeCountryEntity>()
        val entity2 = mockk<ExchangeCountryEntity>()
        val list = arrayListOf(entity1, entity2)
        coEvery { getExchangeCountriesUseCase() } returns list
        // When
        viewModel.onStart()
        // Then
        with(states) {
            assertThat(size).isEqualTo(3)
            assertThat(this[1]).isInstanceOf(CountrySelectionViewState.Loading::class.java)
            assertThat(this[2]).isInstanceOf(CountrySelectionViewState.Success::class.java)
            (this[2] as CountrySelectionViewState.Success).run {
                assertThat(countryList).isEqualTo(list)
            }
        }
        with(actions) {
            assertThat(size).isEqualTo(0)
        }
    }
}