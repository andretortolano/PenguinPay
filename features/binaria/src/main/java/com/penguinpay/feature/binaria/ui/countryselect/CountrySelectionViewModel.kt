package com.penguinpay.feature.binaria.ui.countryselect

import com.penguinpay.domain.exchange.entity.ExchangeCountryEntity
import com.penguinpay.domain.exchange.interactor.GetExchangeCountriesUseCase
import com.penguinpay.feature.binaria.ui.countryselect.CountrySelectionViewModel.CountrySelectionViewAction
import com.penguinpay.feature.binaria.ui.countryselect.CountrySelectionViewModel.CountrySelectionViewState
import com.penguinpay.libraries.coroutines.CoroutineService
import com.penguinpay.libraries.coroutines.android.CoroutinesViewModel

internal class CountrySelectionViewModel(
    coroutineService: CoroutineService,
    private val getExchangeCountriesUseCase: GetExchangeCountriesUseCase
) : CoroutinesViewModel<CountrySelectionViewState, CountrySelectionViewAction>(coroutineService, CountrySelectionViewState.Loading) {

    sealed class CountrySelectionViewState {
        object Loading : CountrySelectionViewState()
        data class Success(val countryList: List<ExchangeCountryEntity>) : CountrySelectionViewState()
    }

    sealed class CountrySelectionViewAction {
        object Nothing : CountrySelectionViewAction()
    }

    fun onStart() {
        scope.launchIdling {
            _state.postValue(CountrySelectionViewState.Loading)

            getExchangeCountriesUseCase().run {
                _state.postValue(CountrySelectionViewState.Success(this))
            }
        }
    }
}