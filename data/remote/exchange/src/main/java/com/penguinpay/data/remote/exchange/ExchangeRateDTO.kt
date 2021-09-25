package com.penguinpay.data.remote.exchange

data class ExchangeRateDTO(
    val baseCurrency: String,
    val exchangeCurrency: String,
    val rate: Double,
)