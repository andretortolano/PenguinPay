package com.penguinpay.data.remote.exchange

interface ExchangeRemoteSource {

    suspend fun getUSDExchangeRates(): List<ExchangeRateDTO>
}