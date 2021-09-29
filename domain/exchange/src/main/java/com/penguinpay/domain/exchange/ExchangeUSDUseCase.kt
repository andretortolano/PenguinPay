package com.penguinpay.domain.exchange

import com.penguinpay.domain.exchange.gateway.ExchangeRateGateway

class ExchangeUSDUseCase(private val exchangeRateGateway: ExchangeRateGateway) {

    data class ExchangeUSDRequest(
        val currency: String,
        val amount: Double,
    )

    data class ExchangeUSDResult(
        val exchangedAmount: Double
    )

    suspend operator fun invoke(request: ExchangeUSDRequest): ExchangeUSDResult {
        return ExchangeUSDResult(request.amount * exchangeRateGateway.getExchangeRate("USD", request.currency))
    }
}