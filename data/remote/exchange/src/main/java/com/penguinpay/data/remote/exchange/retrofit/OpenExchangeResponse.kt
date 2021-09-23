package com.penguinpay.data.remote.exchange.retrofit

data class OpenExchangeResponse(
    val base: String,
    val rates: Map<String, Double>
)
