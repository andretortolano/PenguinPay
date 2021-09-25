package com.penguinpay.feature.binaria.gateway

import com.penguinpay.data.remote.exchange.ExchangeRateDTO
import com.penguinpay.data.remote.exchange.ExchangeRemoteSource
import com.penguinpay.domain.exchange.gateway.ExchangeRateGateway

class BinariaExchangeRateGateway(private val remoteSource: ExchangeRemoteSource) : ExchangeRateGateway {

    private data class Exchange(
        val fromCurrency: String,
        val toCurrency: String,
        val rate: Double
    )

    private var list = arrayListOf<Exchange>()

    private fun findInMemoryExchange(fromCurrency: String, toCurrency: String): Exchange? {
        return list.firstOrNull { it.fromCurrency == fromCurrency && it.toCurrency == toCurrency }
    }

    override suspend fun getExchangeRate(fromCurrency: String, toCurrency: String): Double {
        val exchange = findInMemoryExchange(fromCurrency, toCurrency) ?: run {
            remoteSource.getExchangeRates(fromCurrency, toCurrency).toExchange().apply {
                list.add(this)
            }
        }
        return exchange.rate
    }

    private fun ExchangeRateDTO.toExchange(): Exchange {
        return Exchange(baseCurrency, exchangeCurrency, rate)
    }
}
