package com.penguinpay.data.remote.exchange

interface ExchangeRemoteSource {

    suspend fun getUSDExchangeRates(symbols: Set<String>): List<ExchangeRateDTO>

    suspend fun getExchangeRates(fromCurrency: String, toCurrency: String): ExchangeRateDTO
}