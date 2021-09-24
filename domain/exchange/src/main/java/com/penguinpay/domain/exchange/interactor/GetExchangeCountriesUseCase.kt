package com.penguinpay.domain.exchange.interactor

import com.penguinpay.domain.exchange.entity.ExchangeCountryEntity
import com.penguinpay.libraries.coroutines.CoroutineService
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class GetExchangeCountriesUseCase(private val coroutines: CoroutineService) {

    suspend operator fun invoke(): List<ExchangeCountryEntity> = withContext(coroutines.IO) {
        return@withContext arrayListOf(
            ExchangeCountryEntity("Kenya", "KES", "+254", 9),
            ExchangeCountryEntity("Nigeria", "NGN", "+234", 7),
            ExchangeCountryEntity("Tanzania", "TZS", "+255", 9),
            ExchangeCountryEntity("Uganda", "UGX", "+256", 7),
        )
    }
}