package com.penguinpay.data.remote.exchange.retrofit

import com.penguinpay.data.remote.exchange.ExchangeRateDTO
import com.penguinpay.data.remote.exchange.ExchangeRemoteSource

class RetrofitExchangeSource(private val service: OpenExchangeService) : ExchangeRemoteSource {

    companion object {
        private const val SYMBOL_SEPARATOR = ","
    }

    override suspend fun getUSDExchangeRates(symbols: Set<String>): List<ExchangeRateDTO> {
        return service.getLatestUSDExchanges(symbols.joinToString(SYMBOL_SEPARATOR)).toDTO()
    }

    override suspend fun getExchangeRates(fromCurrency: String, toCurrency: String): ExchangeRateDTO {
        return service.getLatestExchanges(fromCurrency, toCurrency).toDTO().first()
    }

    private fun OpenExchangeResponse.toDTO(): List<ExchangeRateDTO> {
        return rates.map { ExchangeRateDTO(base, it.key, it.value) }
    }
}