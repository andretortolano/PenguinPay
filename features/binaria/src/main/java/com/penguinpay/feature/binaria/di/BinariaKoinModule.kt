package com.penguinpay.feature.binaria.di

import com.penguinpay.di.KoinModule
import com.penguinpay.domain.exchange.ExchangeUSDBinaryUseCase
import com.penguinpay.domain.exchange.ExchangeUSDUseCase
import com.penguinpay.domain.exchange.GetExchangeCountriesUseCase
import com.penguinpay.domain.transfer.TransferUSDBinaryUseCase
import com.penguinpay.domain.transfer.TransferUSDUseCase
import com.penguinpay.feature.binaria.Navigation
import com.penguinpay.feature.binaria.gateway.CacheExchangeRateGatewayImpl
import com.penguinpay.feature.binaria.gateway.NetworkExchangeRateGatewayImpl
import com.penguinpay.feature.binaria.gateway.TransferGatewayImpl
import com.penguinpay.feature.binaria.ui.BinariaViewModel
import com.penguinpay.feature.binaria.ui.countryselect.CountrySelectionModel
import com.penguinpay.feature.binaria.ui.countryselect.CountrySelectionViewModel
import com.penguinpay.feature.binaria.ui.receipt.ReceiptViewModel
import com.penguinpay.feature.binaria.ui.recipientinfo.RecipientInfoViewModel
import com.penguinpay.feature.binaria.ui.send.SendModel
import com.penguinpay.feature.binaria.ui.send.SendViewModel
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
            val model = CountrySelectionModel(
                coroutineService = get(),
                getExchangeCountriesUseCase = GetExchangeCountriesUseCase(),
            )

            CountrySelectionViewModel(
                coroutineService = get(),
                model = model,
            )
        }

        viewModel {
            RecipientInfoViewModel(get())
        }

        viewModel {
            val exchangeUSDUseCase = ExchangeUSDUseCase(NetworkExchangeRateGatewayImpl(get()))
            val transferUSDUseCase = TransferUSDUseCase(TransferGatewayImpl(), exchangeUSDUseCase)
            val cachingExchangeUSDUseCase = ExchangeUSDUseCase(CacheExchangeRateGatewayImpl(get()))

            val model = SendModel(
                coroutineService = get(),
                transferUSDBinaryUseCase = TransferUSDBinaryUseCase(transferUSDUseCase),
                exchangeUSDBinaryUseCase = ExchangeUSDBinaryUseCase(cachingExchangeUSDUseCase),
            )
            SendViewModel(
                coroutineService = get(),
                model = model
            )
        }

        viewModel { ReceiptViewModel(get()) }
    }
}