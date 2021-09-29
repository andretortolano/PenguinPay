package com.penguinpay.domain.exchange

import com.penguinpay.domain.exchange.entity.ExchangeCountryEntity

class GetExchangeCountriesUseCase {

    data class GetExchangeCountriesResult(
        val countries: List<ExchangeCountryEntity>
    )

    suspend operator fun invoke(): GetExchangeCountriesResult {
        return GetExchangeCountriesResult(
            arrayListOf(
                ExchangeCountryEntity("Kenya", "KES", "+254", 9),
                ExchangeCountryEntity("Nigeria", "NGN", "+234", 7),
                ExchangeCountryEntity("Tanzania", "TZS", "+255", 9),
                ExchangeCountryEntity("Uganda", "UGX", "+256", 7),
            )
        )
    }
}