package com.penguinpay.feature.binaria.gateway

import com.penguinpay.data.remote.exchange.ExchangeRemoteSource
import com.penguinpay.domain.exchange.gateway.ExchangeRateGateway

internal class NetworkExchangeRateGatewayImpl(private val remoteSource: ExchangeRemoteSource) : ExchangeRateGateway {

    override suspend fun getExchangeRate(fromCurrency: String, toCurrency: String): Double {
        return remoteSource.getExchangeRates(fromCurrency, toCurrency).rate
    }
}
