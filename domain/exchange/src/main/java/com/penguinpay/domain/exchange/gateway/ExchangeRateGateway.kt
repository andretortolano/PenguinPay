package com.penguinpay.domain.exchange.gateway

interface ExchangeRateGateway {

    suspend fun getExchangeRate(fromCurrency: String, toCurrency: String): Double
}
