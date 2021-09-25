package com.penguinpay.feature.binaria.di

import com.penguinpay.di.KoinModule
import com.penguinpay.domain.exchange.interactor.ExchangeUSDBinaryUseCase
import com.penguinpay.domain.exchange.interactor.ExchangeUSDUseCase
import com.penguinpay.domain.exchange.interactor.GetExchangeCountriesUseCase
import com.penguinpay.domain.transfer.SendTransferUSDBinaryUseCase
import com.penguinpay.feature.binaria.Navigation
import com.penguinpay.feature.binaria.gateway.BinariaExchangeRateGateway
import com.penguinpay.feature.binaria.ui.BinariaViewModel
import com.penguinpay.feature.binaria.ui.countryselect.CountrySelectionViewModel
import com.penguinpay.feature.binaria.ui.receipt.ReceiptViewModel
import com.penguinpay.feature.binaria.ui.recipientinfo.RecipientInfoViewModel
import com.penguinpay.feature.binaria.ui.send.SendRecipientViewModel
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

        viewModel {
            RecipientInfoViewModel(get())
        }

        viewModel {
            val exchangeRateGateway = BinariaExchangeRateGateway(get())
            val exchangeUSDUseCase = ExchangeUSDUseCase(get(), exchangeRateGateway)

            SendRecipientViewModel(
                coroutineService = get(),
                exchangeUSDBinaryUseCase = ExchangeUSDBinaryUseCase(get(), exchangeUSDUseCase),
                sendTransferUSDBinaryUseCase = SendTransferUSDBinaryUseCase(get())
            )
        }

        viewModel { ReceiptViewModel(get()) }
    }
}