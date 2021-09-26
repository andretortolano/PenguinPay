package com.penguinpay.feature.binaria.ui.countryselect

import com.penguinpay.domain.exchange.interactor.GetExchangeCountriesUseCase
import com.penguinpay.feature.binaria.ui.countryselect.CountrySelectionViewModel.CountrySelectionViewAction
import com.penguinpay.feature.binaria.ui.countryselect.CountrySelectionViewModel.CountrySelectionViewState
import com.penguinpay.libraries.coroutines.android.test.MockKViewModelTest
import io.mockk.impl.annotations.MockK

internal class CountrySelectionViewModelTest : MockKViewModelTest<CountrySelectionViewModel, CountrySelectionViewState, CountrySelectionViewAction>() {

    @MockK
    private lateinit var getExchangeCountriesUseCase: GetExchangeCountriesUseCase

}