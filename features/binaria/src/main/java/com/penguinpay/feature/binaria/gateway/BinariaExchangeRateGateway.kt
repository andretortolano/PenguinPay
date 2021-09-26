package com.penguinpay.feature.binaria.gateway

import com.penguinpay.data.remote.exchange.ExchangeRateDTO
import com.penguinpay.data.remote.exchange.ExchangeRemoteSource
import com.penguinpay.domain.exchange.gateway.ExchangeRateGateway

class BinariaExchangeRateGateway(private val remoteSource: ExchangeRemoteSource) : ExchangeRateGateway {

    private var list = arrayListOf<ExchangeRateDTO>()

    private fun findInMemoryExchange(fromCurrency: String, toCurrency: String): ExchangeRateDTO? {
        return list.firstOrNull { it.baseCurrency == fromCurrency && it.exchangeCurrency == toCurrency }
    }

    override suspend fun getExchangeRate(fromCurrency: String, toCurrency: String): Double {
        return findInMemoryExchange(fromCurrency, toCurrency)?.rate
            ?: run {
                remoteSource.getExchangeRates(fromCurrency, toCurrency).also {
                    list.add(it)
                }.rate
            }
    }
}
