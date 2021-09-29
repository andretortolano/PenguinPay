package com.penguinpay.feature.binaria.ui.countryselect

import com.penguinpay.domain.exchange.entity.ExchangeCountryEntity
import com.penguinpay.domain.exchange.ExchangeUSDBinaryUseCase
import com.penguinpay.domain.exchange.GetExchangeCountriesUseCase
import com.penguinpay.libraries.coroutines.CoroutineService
import kotlinx.coroutines.withContext

internal class CountrySelectionModel(
    private val coroutineService: CoroutineService,
    private val getExchangeCountriesUseCase: GetExchangeCountriesUseCase
) {

    suspend fun getExchangeCountries(): List<ExchangeCountryEntity> = withContext(coroutineService.IO) {
        return@withContext getExchangeCountriesUseCase().countries
    }

}