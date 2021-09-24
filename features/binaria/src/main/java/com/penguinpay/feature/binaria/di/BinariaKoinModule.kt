package com.penguinpay.feature.binaria.di

import com.penguinpay.di.KoinModule
import com.penguinpay.domain.exchange.interactor.GetExchangeCountriesUseCase
import com.penguinpay.feature.binaria.Navigation
import com.penguinpay.feature.binaria.ui.BinariaViewModel
import com.penguinpay.feature.binaria.ui.countryselect.CountrySelectionViewModel
import com.penguinpay.navigation.BinariaNavigation
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

class BinariaKoinModule : KoinModule {
    override val module: Module = module {
        single<BinariaNavigation> { Navigation() }

        viewModel {
            BinariaViewModel(
                coroutineService = get(),
            )
        }

        viewModel {
            CountrySelectionViewModel(
                coroutineService = get(),
                getExchangeCountriesUseCase = GetExchangeCountriesUseCase(get()),
            )
        }
    }
}