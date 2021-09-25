package com.penguinpay.feature.binaria.gateway

import com.penguinpay.domain.exchange.gateway.ExchangeRateGateway

class BinariaExchangeRateGateway : ExchangeRateGateway {
    override suspend fun getExchangeRate(fromCurrency: String, toCurrency: String): Double {
        return 2.0
    }
}