package com.penguinpay.data.remote.exchange.retrofit

import com.penguinpay.data.remote.exchange.ExchangeRateDTO
import com.penguinpay.data.remote.exchange.ExchangeRemoteSource

class RetrofitExchangeSource : ExchangeRemoteSource {
    override suspend fun getUSDExchangeRates(): List<ExchangeRateDTO> {
        // TODO change to retrofit impl
        return arrayListOf(
            ExchangeRateDTO("USD", "KES", 110.410176),
            ExchangeRateDTO("USD", "NGN", 412.286896),
            ExchangeRateDTO("USD", "TZS", 2316.0),
            ExchangeRateDTO("USD", "UGX", 3537.525532),
        )
    }
}